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

public class ArmorHandler {
    private static final ResourceLocation ARMOR_MODIFIER_ID = SOLMaid.resourceLocation("armor_boost");

    public static void updateFoodArmorModifier(EntityMaid maid){
        double totalArmorModifier = (double)((maid.getData(ModAttachmentTypes.FOOD_LIST).getCachedMilestone() + 1) * ModConfig.getArmorPerStone());
        if (!maid.level().isClientSide) {
            AttributeModifier modifier = new AttributeModifier(ARMOR_MODIFIER_ID, totalArmorModifier, AttributeModifier.Operation.ADD_VALUE);
            updateArmorModifier(maid, modifier);
        }
    }

    private static AttributeInstance armorAttribute(EntityMaid maid) {
        return (AttributeInstance) Objects.requireNonNull(maid.getAttribute(Attributes.ARMOR));
    }

    @Nullable
    private static AttributeModifier getArmorModifier(EntityMaid maid) {
        return armorAttribute(maid).getModifier(ARMOR_MODIFIER_ID);
    }

    private static void updateArmorModifier(EntityMaid maid, AttributeModifier modifier) {
        AttributeInstance attribute = armorAttribute(maid);
        attribute.removeModifier(modifier);
        attribute.addPermanentModifier(modifier);
    }
}
