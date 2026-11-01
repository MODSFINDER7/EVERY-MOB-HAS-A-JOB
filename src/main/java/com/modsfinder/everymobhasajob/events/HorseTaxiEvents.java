package com.modsfinder.everymobhasajob.events;

import com.modsfinder.everymobhasajob.EveryMobHasAJob;
import com.modsfinder.everymobhasajob.config.MobJobsConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.equine.Horse;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = EveryMobHasAJob.MOD_ID)
public final class HorseTaxiEvents {
    private HorseTaxiEvents() {}

    @SubscribeEvent
    public static void onTaxiBooking(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getTarget() instanceof Horse horse)) return;
        if (!MobJobsConfig.ENABLE_JOB_ASSIGNMENTS.get()
                || !MobJobsConfig.ENABLE_HORSE_TAXI.get()) return;
        if (!event.getItemStack().is(Items.EMERALD)) return;

        // The client sends the interaction; the server handles payment and boarding.
        if (event.getLevel().isClientSide()) return;
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);

        if (!horse.isTamed() || !horse.isSaddled() || !horse.getPassengers().isEmpty()) {
            player.sendOverlayMessage(Component.translatable(
                    "message.every_mob_has_a_job.taxi.not_ready"));
            return;
        }

        ItemStack payment = event.getItemStack();
        int fare = MobJobsConfig.HORSE_TAXI_FARE_EMERALDS.get();
        if (!player.getAbilities().instabuild && payment.getCount() < fare) {
            player.sendOverlayMessage(Component.translatable(
                    "message.every_mob_has_a_job.taxi.not_enough", fare));
            return;
        }

        if (!player.startRiding(horse)) {
            player.sendOverlayMessage(Component.translatable(
                    "message.every_mob_has_a_job.taxi.boarding_failed"));
            return;
        }

        if (!player.getAbilities().instabuild) payment.shrink(fare);
        player.sendOverlayMessage(Component.translatable(
                "message.every_mob_has_a_job.taxi.boarded", fare));
    }
}
