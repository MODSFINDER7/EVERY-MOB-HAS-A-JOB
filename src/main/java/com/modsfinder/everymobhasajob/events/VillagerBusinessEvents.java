package com.modsfinder.everymobhasajob.events;

import com.modsfinder.everymobhasajob.EveryMobHasAJob;
import com.modsfinder.everymobhasajob.config.MobJobsConfig;
import com.modsfinder.everymobhasajob.data.MobJobAttachments;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = EveryMobHasAJob.MOD_ID)
public final class VillagerBusinessEvents {
    private VillagerBusinessEvents() {}

    @SubscribeEvent
    public static void onBusinessInvitation(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getTarget() instanceof Villager villager)) return;
        if (!MobJobsConfig.ENABLE_JOB_ASSIGNMENTS.get()
                || !MobJobsConfig.ENABLE_VILLAGER_BUSINESS.get()) return;
        if (!event.getItemStack().is(Items.PAPER)) return;

        // The client only initiates the interaction; meeting state is server-authoritative.
        if (event.getLevel().isClientSide()) return;
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (!(villager.level() instanceof ServerLevel serverLevel)) return;

        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);

        if (!villager.isAlive() || villager.isBaby() || villager.isTrading()) {
            player.sendOverlayMessage(Component.translatable(
                    "message.every_mob_has_a_job.business.busy"));
            return;
        }

        event.getItemStack().shrink(1);
        villager.setData(MobJobAttachments.ACTIVITY, "secret_meeting");

        serverLevel.sendParticles(
                ParticleTypes.SMOKE,
                villager.getX(),
                villager.getY() + 1.0D,
                villager.getZ(),
                4,
                0.25D,
                0.2D,
                0.25D,
                0.01D);

        player.sendOverlayMessage(Component.translatable(
                "message.every_mob_has_a_job.business.invited"));
    }
}
