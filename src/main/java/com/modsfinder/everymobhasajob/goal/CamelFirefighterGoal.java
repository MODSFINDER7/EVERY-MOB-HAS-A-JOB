package com.modsfinder.everymobhasajob.goal;

import java.util.EnumSet;

import com.modsfinder.everymobhasajob.config.MobJobsConfig;
import com.modsfinder.everymobhasajob.data.MobJobAttachments;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gamerules.GameRules;

public final class CamelFirefighterGoal extends Goal {
    private static final int SEARCH_INTERVAL_TICKS = 40;
    private static final double EXTINGUISH_DISTANCE_SQR = 6.25D;

    private final Camel camel;
    private BlockPos firePosition;
    private int searchCooldown;
    private int workTicks;

    public CamelFirefighterGoal(Camel camel) {
        this.camel = camel;
        setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!MobJobsConfig.ENABLE_CUSTOM_AI.get()
                || !MobJobsConfig.ENABLE_CAMEL_FIREFIGHTER.get()) return false;
        if (searchCooldown > 0) {
            searchCooldown--;
            return false;
        }
        if (!camel.isAlive() || camel.isBaby() || camel.isPassenger()) return false;
        if (!(camel.level() instanceof ServerLevel serverLevel)
                || !serverLevel.getGameRules().get(GameRules.MOB_GRIEFING)) return false;

        firePosition = findNearestFire();
        if (firePosition == null) {
            searchCooldown = SEARCH_INTERVAL_TICKS;
            return false;
        }
        return true;
    }

    private BlockPos findNearestFire() {
        BlockPos origin = camel.blockPosition();
        BlockPos nearest = null;
        double nearestDistance = Double.MAX_VALUE;

        // A small local cube is checked only once per search interval.
        for (int y = -1; y <= 2; y++) {
            for (int x = -4; x <= 4; x++) {
                for (int z = -4; z <= 4; z++) {
                    BlockPos candidate = origin.offset(x, y, z);
                    if (!isFire(candidate)) continue;

                    double distance = camel.distanceToSqr(
                            candidate.getX() + 0.5D,
                            candidate.getY() + 0.5D,
                            candidate.getZ() + 0.5D);
                    if (distance < nearestDistance) {
                        nearest = candidate.immutable();
                        nearestDistance = distance;
                    }
                }
            }
        }
        return nearest;
    }

    private boolean isFire(BlockPos position) {
        var state = camel.level().getBlockState(position);
        return state.is(Blocks.FIRE) || state.is(Blocks.SOUL_FIRE);
    }

    @Override
    public boolean canContinueToUse() {
        return firePosition != null
                && camel.isAlive()
                && isFire(firePosition)
                && workTicks < 20 * 12
                && MobJobsConfig.ENABLE_CUSTOM_AI.get()
                && MobJobsConfig.ENABLE_CAMEL_FIREFIGHTER.get();
    }

    @Override
    public void start() {
        workTicks = 0;
        camel.setData(MobJobAttachments.ACTIVITY, "responding_to_fire");
        camel.getNavigation().moveTo(
                firePosition.getX() + 0.5D,
                firePosition.getY(),
                firePosition.getZ() + 0.5D,
                1.1D);
    }

    @Override
    public void tick() {
        if (firePosition == null) return;
        workTicks++;

        camel.getLookControl().setLookAt(
                firePosition.getX() + 0.5D,
                firePosition.getY() + 0.5D,
                firePosition.getZ() + 0.5D);

        double targetX = firePosition.getX() + 0.5D;
        double targetY = firePosition.getY() + 0.5D;
        double targetZ = firePosition.getZ() + 0.5D;
        if (camel.distanceToSqr(targetX, targetY, targetZ) <= EXTINGUISH_DISTANCE_SQR
                && isFire(firePosition)
                && camel.level() instanceof ServerLevel serverLevel
                && serverLevel.getGameRules().get(GameRules.MOB_GRIEFING)) {
            camel.level().setBlock(firePosition, Blocks.AIR.defaultBlockState(), 3);
            searchCooldown = MobJobsConfig.CAMEL_FIRE_COOLDOWN_SECONDS.get() * 20;
            firePosition = null;
        } else if (camel.getNavigation().isDone()) {
            camel.getNavigation().moveTo(targetX, firePosition.getY(), targetZ, 1.1D);
        }
    }

    @Override
    public void stop() {
        if (camel.hasData(MobJobAttachments.ACTIVITY)
                && "responding_to_fire".equals(camel.getData(MobJobAttachments.ACTIVITY))) {
            camel.setData(MobJobAttachments.ACTIVITY, "idle");
        }
        camel.getNavigation().stop();
        firePosition = null;
        workTicks = 0;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }
}
