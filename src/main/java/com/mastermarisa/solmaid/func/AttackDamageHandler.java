package com.mastermarisa.solmaid.func;

import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.mastermarisa.solmaid.SOLMaid;
import com.mastermarisa.solmaid.config.ModConfig;
import com.mastermarisa.solmaid.registry.ModAttachmentTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import javax.annotation.Nullable;
import java.util.Objects;

public class AttackDamageHandler {
    private static final ResourceLocation ATTACK_DAMAGE_MODIFIER_ID = SOLMaid.resourceLocation("armor_boost");

    public static void updateFoodArmorModifier(EntityMaid maid){
        double totalAttackDamageModifier = (double)((maid.getData(ModAttachmentTypes.FOOD_LIST).getCachedMilestone() + 1) * ModConfig.getAttackDamagePerStone());
        if (!maid.level().isClientSide) {
            AttributeModifier modifier = new AttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, totalAttackDamageModifier, AttributeModifier.Operation.ADD_VALUE);
            updateAttackDamageModifier(maid, modifier);
        }
    }

    private static AttributeInstance attackDamageAttribute(EntityMaid maid) {
        return (AttributeInstance) Objects.requireNonNull(maid.getAttribute(Attributes.ATTACK_DAMAGE));
    }

    @Nullable
    private static AttributeModifier getAttackDamageModifier(EntityMaid maid) {
        return attackDamageAttribute(maid).getModifier(ATTACK_DAMAGE_MODIFIER_ID);
    }

    private static void updateAttackDamageModifier(EntityMaid maid, AttributeModifier modifier) {
        AttributeInstance attribute = attackDamageAttribute(maid);
        attribute.removeModifier(modifier);
        attribute.addPermanentModifier(modifier);
    }
}
