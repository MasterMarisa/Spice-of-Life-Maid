package com.mastermarisa.solmaid.init;

import com.google.common.collect.Lists;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class InitConfig {
    public static List<? extends Integer> MILESTONES() { return Server.MILESTONES.get(); }

    public static int HP() { return Server.HP.getAsInt(); }

    public static int ARMOR() { return Server.ARMOR.getAsInt(); }

    public static int ATTACK_DAMAGE() { return Server.ATTACK_DAMAGE.getAsInt(); }

    public static int FAVORABILITY() { return Server.FAVORABILITY.getAsInt(); }

    private static class Client {

    }

    private static class Server {
        private static final ModConfigSpec.Builder BUILDER;
        private static final ModConfigSpec SPEC;
        public static final ModConfigSpec.ConfigValue<List<? extends Integer>> MILESTONES;
        public static final ModConfigSpec.IntValue HP;
        public static final ModConfigSpec.IntValue ARMOR;
        public static final ModConfigSpec.IntValue ATTACK_DAMAGE;
        public static final ModConfigSpec.IntValue FAVORABILITY;

        public static void register(ModContainer modContainer) {
            modContainer.registerConfig(ModConfig.Type.SERVER, SPEC);
        }

        static {
            BUILDER = new ModConfigSpec.Builder();

            MILESTONES = BUILDER
                    .translation("config.solmaid.server.milestones")
                    .defineList("milestones", Lists.newArrayList(10, 20, 30, 40, 50), () -> 0, (e) -> e instanceof Integer);

            HP = BUILDER.translation("config.solmaid.server.hp_per_milestone")
                    .defineInRange("hpPerMileStone", 4, 0, 1000);

            ARMOR = BUILDER.translation("config.solmaid.server.armor_per_milestone")
                    .defineInRange("armorPerMilestone", 1, 0, 1000);

            ATTACK_DAMAGE = BUILDER
                    .translation("config.solmaid.server.attack_damage_per_milestone")
                    .defineInRange("attackDamagePerMilestone", 1, 0, 1000);

            FAVORABILITY = BUILDER
                    .translation("config.solmaid.server.favorability_per_milestone")
                    .defineInRange("favorabilityPerMilestone", 16, 0, 1000);

            SPEC = BUILDER.build();
        }
    }

    public static void register(ModContainer modContainer) {
        Server.register(modContainer);
    }
}
