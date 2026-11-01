package com.modsfinder.everymobhasajob.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class MobJobsConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ENABLE_JOB_ASSIGNMENTS = BUILDER
            .comment("Assign occupation data to mobs when they join a server level.")
            .define("enableJobAssignments", true);

    public static final ModConfigSpec.BooleanValue ENABLE_CUSTOM_AI = BUILDER
            .comment("Allow custom occupation AI goals to run.")
            .define("enableCustomAI", true);

    public static final ModConfigSpec.BooleanValue ENABLE_COW_GRASS_CUTTER = BUILDER
            .comment("Allow adult cows to perform the grass-cutter occupation.")
            .define("enableCowGrassCutter", true);

    public static final ModConfigSpec.IntValue COW_GRAZE_WORK_TICKS = BUILDER
            .comment("Number of ticks a cow spends grazing before the grass block is cut.")
            .defineInRange("cowGrazeWorkTicks", 40, 20, 200);

    public static final ModConfigSpec.IntValue COW_GRAZE_COOLDOWN_SECONDS = BUILDER
            .comment("Seconds between successful grass-cutter actions for the same cow.")
            .defineInRange("cowGrazeCooldownSeconds", 18, 1, 600);

    public static final ModConfigSpec SPEC = BUILDER.build();

    private MobJobsConfig() {}
}
