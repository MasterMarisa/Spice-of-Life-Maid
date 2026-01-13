package com.mastermarisa.solmaid.config;

import com.google.common.collect.Lists;
import com.mastermarisa.solmaid.utils.ModUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ModConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.ConfigValue<List<? extends Integer>> MILESTONES = BUILDER
            .translation("config.solmaid.server.milestones")
            .defineList("milestones", Lists.newArrayList(new Integer[]{5, 10, 15, 20, 25, 30, 35, 40, 45, 50}), () -> 0, (e) -> e instanceof Integer);

    public static final ModConfigSpec.IntValue HEARTS_PER_MILESTONE = BUILDER
            .translation("config.solmaid.server.hearts_per_milestone")
            .defineInRange("heartsPerMilestone", 1, 0, 1000);

    public static final ModConfigSpec.IntValue ARMOR_PER_MILESTONE = BUILDER
            .translation("config.solmaid.server.armor_per_milestone")
            .defineInRange("armorPerMilestone", 0, 0, 1000);

    public static final ModConfigSpec.IntValue ATTACK_DAMAGE_PER_MILESTONE = BUILDER
            .translation("config.solmaid.server.attack_damage_per_milestone")
            .defineInRange("armorPerMilestone", 0, 0, 1000);

    public static final ModConfigSpec.IntValue FAVORABILITY_PER_MILESTONE = BUILDER
            .translation("config.solmaid.server.favorability_per_milestone")
            .defineInRange("FavorabilityPerMilestone", 2, 0, 1000);

    public static final ModConfigSpec.BooleanValue COUNT_INVALID_MOD_FOOD = BUILDER
            .translation("config.solmaid.server.count_invalid_mod_food")
            .define("countInvalidModFood",true);

    public static final ModConfigSpec SPEC = BUILDER.build();

    private static final ModConfigSpec.Builder CLIENT_BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue ITEMS_PER_LINE = CLIENT_BUILDER
            .translation("config.solmaid.client.item_per_line")
            .defineInRange("itemsPerLine", 10, 1, 1000);

    public static final ModConfigSpec CLIENT_SPEC = CLIENT_BUILDER.build();

    public static List<Integer> getMileStones() {
        return new ArrayList<>(MILESTONES.get());
    }

    public static int getHeartsPerStone(){ return HEARTS_PER_MILESTONE.getAsInt(); }

    public static int getArmorPerStone(){ return ARMOR_PER_MILESTONE.getAsInt(); }

    public static int getAttackDamagePerStone(){ return ATTACK_DAMAGE_PER_MILESTONE.getAsInt(); }

    public static int getFavorbilityPerMilestone() { return FAVORABILITY_PER_MILESTONE.getAsInt(); }

    public static int getItemsPerLine(){ return ITEMS_PER_LINE.getAsInt(); }

    public static boolean doCountInvalidModFood() { return COUNT_INVALID_MOD_FOOD.getAsBoolean(); }
}
