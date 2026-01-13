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

public class MaxHealthHandler {
    private static final ResourceLocation HEALTH_MODIFIER_ID = SOLMaid.resourceLocation("health_boost");

    public static void updateFoodHPModifier(EntityMaid maid){
        double totalHealthModifier = (double)((maid.getData(ModAttachmentTypes.FOOD_LIST).getCachedMilestone() + 1) * ModConfig.getHeartsPerStone() * 2);
        if (!maid.level().isClientSide) {
            AttributeModifier modifier = new AttributeModifier(HEALTH_MODIFIER_ID, totalHealthModifier, AttributeModifier.Operation.ADD_VALUE);
            updateHealthModifier(maid, modifier);
        }
    }

    private static AttributeInstance maxHealthAttribute(EntityMaid maid) {
        return (AttributeInstance) Objects.requireNonNull(maid.getAttribute(Attributes.MAX_HEALTH));
    }

    @Nullable
    private static AttributeModifier getHealthModifier(EntityMaid maid) {
        return maxHealthAttribute(maid).getModifier(HEALTH_MODIFIER_ID);
    }

    private static void updateHealthModifier(EntityMaid maid, AttributeModifier modifier) {
        float oldMax = maid.getMaxHealth();
        AttributeInstance attribute = maxHealthAttribute(maid);
        attribute.removeModifier(modifier);
        attribute.addPermanentModifier(modifier);
        float newHealth = maid.getHealth() * maid.getMaxHealth() / oldMax;

        maid.setHealth(newHealth);
    }
}
