package com.modsfinder.everymobhasajob.events;

import com.modsfinder.everymobhasajob.EveryMobHasAJob;
import com.modsfinder.everymobhasajob.config.MobJobsConfig;
import com.modsfinder.everymobhasajob.data.MobJobAttachments;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;

@EventBusSubscriber(modid = EveryMobHasAJob.MOD_ID)
public final class PoliceOfficerEvents {
    private PoliceOfficerEvents() {}

    @SubscribeEvent
    public static void onGolemTargetChange(LivingChangeTargetEvent event) {
        if (!(event.getEntity() instanceof IronGolem golem)) return;
        if (golem.level().isClientSide()) return;
        if (!MobJobsConfig.ENABLE_JOB_ASSIGNMENTS.get()
                || !MobJobsConfig.ENABLE_IRON_GOLEM_POLICE.get()) return;

        var target = event.getNewAboutToBeSetTarget();
        if (target == null) {
            golem.setData(MobJobAttachments.ACTIVITY, "patrolling");
            return;
        }

        if (target instanceof Player) {
            golem.setData(MobJobAttachments.ACTIVITY, "investigating_intruder");
            if (golem.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ParticleTypes.ANGRY_VILLAGER,
                        golem.getX(),
                        golem.getY() + 2.1D,
                        golem.getZ(),
                        4,
                        0.25D,
                        0.2D,
                        0.25D,
                        0.0D);
            }
        } else {
            golem.setData(MobJobAttachments.ACTIVITY, "responding_to_threat");
        }
    }
}
