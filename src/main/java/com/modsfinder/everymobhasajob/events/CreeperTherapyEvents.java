package com.modsfinder.everymobhasajob.events;

import com.modsfinder.everymobhasajob.EveryMobHasAJob;
import com.modsfinder.everymobhasajob.config.MobJobsConfig;
import com.modsfinder.everymobhasajob.data.MobJobAttachments;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = EveryMobHasAJob.MOD_ID)
public final class CreeperTherapyEvents {
    private CreeperTherapyEvents() {}

    @SubscribeEvent
    public static void onTherapySession(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getTarget() instanceof Creeper creeper)) return;
        if (!MobJobsConfig.ENABLE_JOB_ASSIGNMENTS.get()
                || !MobJobsConfig.ENABLE_CREEPER_THERAPY.get()) return;
        if (!event.getItemStack().is(Items.POPPY)) return;

        // The client sends the interaction; the session marker is applied by the server.
        if (event.getLevel().isClientSide()) return;
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (!(creeper.level() instanceof ServerLevel serverLevel)) return;

        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);

        // Never interfere with a live fuse, ignition, or an active target.
        if (!creeper.isAlive() || creeper.getTarget() != null
                || creeper.getSwellDir() > 0 || creeper.isIgnited()) {
            player.sendOverlayMessage(Component.translatable(
                    "message.every_mob_has_a_job.therapy.too_busy"));
            return;
        }

        event.getItemStack().shrink(1);
        creeper.setData(MobJobAttachments.ACTIVITY, "therapy_session");

        serverLevel.sendParticles(
                ParticleTypes.HEART,
                creeper.getX(),
                creeper.getY() + 1.1D,
                creeper.getZ(),
                6,
                0.35D,
                0.35D,
                0.35D,
                0.02D);

        player.sendOverlayMessage(Component.translatable(
                "message.every_mob_has_a_job.therapy.session_started"));
    }
}
