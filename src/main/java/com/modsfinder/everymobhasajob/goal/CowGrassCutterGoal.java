package com.modsfinder.everymobhasajob.goal;

import java.util.EnumSet;

import com.modsfinder.everymobhasajob.config.MobJobsConfig;
import com.modsfinder.everymobhasajob.data.MobJobAttachments;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.cow.Cow;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gamerules.GameRules;

public final class CowGrassCutterGoal extends Goal {
    private final Cow cow;
    private BlockPos grassPosition;
    private int workTicks;
    private int workDuration;
    private int cooldownTicks;

    public CowGrassCutterGoal(Cow cow) {
        this.cow = cow;
        setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!MobJobsConfig.ENABLE_CUSTOM_AI.get()
                || !MobJobsConfig.ENABLE_COW_GRASS_CUTTER.get()) return false;
        if (cooldownTicks > 0) {
            cooldownTicks--;
            return false;
        }
        if (!cow.isAlive() || cow.isBaby() || cow.isPassenger() || !cow.onGround()) return false;
        if (!(cow.level() instanceof ServerLevel serverLevel)
                || !serverLevel.getGameRules().get(GameRules.MOB_GRIEFING)) return false;

        BlockPos below = cow.blockPosition().below();
        if (!cow.level().getBlockState(below).is(Blocks.GRASS_BLOCK)) return false;
        grassPosition = below.immutable();
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return grassPosition != null && workTicks < workDuration
                && cow.isAlive() && cow.onGround()
                && cow.blockPosition().below().equals(grassPosition)
                && MobJobsConfig.ENABLE_CUSTOM_AI.get()
                && MobJobsConfig.ENABLE_COW_GRASS_CUTTER.get();
    }

    @Override
    public void start() {
        workTicks = 0;
        workDuration = MobJobsConfig.COW_GRAZE_WORK_TICKS.get();
        cow.getNavigation().stop();
        cow.setData(MobJobAttachments.ACTIVITY, "grazing");
    }

    @Override
    public void tick() {
        workTicks++;
        if (workTicks >= workDuration && grassPosition != null
                && cow.level().getBlockState(grassPosition).is(Blocks.GRASS_BLOCK)) {
            cow.level().setBlock(grassPosition, Blocks.DIRT.defaultBlockState(), 3);
            cooldownTicks = MobJobsConfig.COW_GRAZE_COOLDOWN_SECONDS.get() * 20;
        }
    }

    @Override
    public void stop() {
        if (cow.hasData(MobJobAttachments.ACTIVITY)
                && "grazing".equals(cow.getData(MobJobAttachments.ACTIVITY))) {
            cow.setData(MobJobAttachments.ACTIVITY, "idle");
        }
        grassPosition = null;
        workTicks = 0;
        workDuration = 0;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }
}
