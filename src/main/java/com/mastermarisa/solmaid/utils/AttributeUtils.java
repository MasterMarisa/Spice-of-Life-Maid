package com.mastermarisa.solmaid.utils;

import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.mastermarisa.solmaid.SOLMaid;
import com.mastermarisa.solmaid.data.FoodRecord;
import com.mastermarisa.solmaid.init.InitConfig;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class AttributeUtils {
    public static final ResourceLocation HEALTH_MODIFIER = SOLMaid.resourceLocation("health_boost");
    public static final ResourceLocation ARMOR_MODIFIER = SOLMaid.resourceLocation("armor_boost");
    public static final ResourceLocation ATTACK_DAMAGE_MODIFIER = SOLMaid.resourceLocation("attack_damage_boost");

    public static void updateModifier(EntityMaid maid, Holder<Attribute> type, AttributeModifier modifier) {
        AttributeInstance attribute = getAttribute(maid, type);
        attribute.removeModifier(modifier);
        attribute.addPermanentModifier(modifier);
    }

    public static @Nullable AttributeModifier getModifier(EntityMaid maid, Holder<Attribute> type, ResourceLocation id) {
        return getAttribute(maid, type).getModifier(id);
    }

    public static AttributeInstance getAttribute(EntityMaid maid, Holder<Attribute> type) {
        return Objects.requireNonNull(maid.getAttribute(type));
    }

    public static void updateAttributes(EntityMaid maid) {
        updateHP(maid);
        updateArmor(maid);
        updateAttackDamage(maid);
    }

    private static void updateHP(EntityMaid maid) {
        FoodRecord foodRecord = maid.getData(FoodRecord.TYPE);
        double total = (foodRecord.reached + 1) * InitConfig.HP();
        if (!maid.level().isClientSide) {
            AttributeModifier modifier = new AttributeModifier(HEALTH_MODIFIER, total, AttributeModifier.Operation.ADD_VALUE);
            updateModifier(maid, Attributes.MAX_HEALTH, modifier);
        }
    }

    private static void updateArmor(EntityMaid maid) {
        FoodRecord foodRecord = maid.getData(FoodRecord.TYPE);
        double total = (foodRecord.reached + 1) * InitConfig.ARMOR();
        if (!maid.level().isClientSide) {
            AttributeModifier modifier = new AttributeModifier(ARMOR_MODIFIER, total, AttributeModifier.Operation.ADD_VALUE);
            updateModifier(maid, Attributes.ARMOR, modifier);
        }
    }

    private static void updateAttackDamage(EntityMaid maid) {
        FoodRecord foodRecord = maid.getData(FoodRecord.TYPE);
        double total = (foodRecord.reached + 1) * InitConfig.ATTACK_DAMAGE();
        if (!maid.level().isClientSide) {
            AttributeModifier modifier = new AttributeModifier(ATTACK_DAMAGE_MODIFIER, total, AttributeModifier.Operation.ADD_VALUE);
            updateModifier(maid, Attributes.ATTACK_DAMAGE, modifier);
        }
    }
}
