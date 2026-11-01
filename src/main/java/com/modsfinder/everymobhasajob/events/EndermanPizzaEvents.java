package com.modsfinder.everymobhasajob.events;

import com.modsfinder.everymobhasajob.EveryMobHasAJob;
import com.modsfinder.everymobhasajob.config.MobJobsConfig;
import com.modsfinder.everymobhasajob.data.MobJobAttachments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = EveryMobHasAJob.MOD_ID)
public final class EndermanPizzaEvents {
    private EndermanPizzaEvents() {}

    @SubscribeEvent
    public static void onPizzaOrder(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getTarget() instanceof EnderMan enderman)) return;
        if (!MobJobsConfig.ENABLE_JOB_ASSIGNMENTS.get()
                || !MobJobsConfig.ENABLE_ENDERMAN_PIZZA.get()) return;
        if (!event.getItemStack().is(Items.EMERALD)) return;

        // Let the client send the interaction packet. Order handling is server-authoritative.
        if (event.getLevel().isClientSide()) return;
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (!(enderman.level() instanceof ServerLevel serverLevel)) return;

        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);

        if (enderman.getTarget() != null || enderman.isPassenger()) {
            player.sendOverlayMessage(Component.translatable(
                    "message.every_mob_has_a_job.pizza.busy"));
            return;
        }

        int price = MobJobsConfig.ENDERMAN_PIZZA_EMERALD_PRICE.get();
        ItemStack payment = event.getItemStack();
        if (!player.getAbilities().instabuild && payment.getCount() < price) {
            player.sendOverlayMessage(Component.translatable(
                    "message.every_mob_has_a_job.pizza.not_enough", price));
            return;
        }

        Vec3 behindPlayer = player.getLookAngle().scale(-2.0D);
        double targetX = player.getX() + behindPlayer.x;
        double targetZ = player.getZ() + behindPlayer.z;
        double targetY = player.getY();
        BlockPos feet = BlockPos.containing(targetX, targetY, targetZ);

        if (!serverLevel.getBlockState(feet).isAir()
                || !serverLevel.getBlockState(feet.above()).isAir()
                || !serverLevel.getBlockState(feet.below())
                        .isFaceSturdy(serverLevel, feet.below(), Direction.UP)) {
            player.sendOverlayMessage(Component.translatable(
                    "message.every_mob_has_a_job.pizza.no_delivery_space"));
            return;
        }

        AABB deliveryBounds = enderman.getBoundingBox().move(
                targetX - enderman.getX(),
                targetY - enderman.getY(),
                targetZ - enderman.getZ());
        if (!serverLevel.noCollision(enderman, deliveryBounds)) {
            player.sendOverlayMessage(Component.translatable(
                    "message.every_mob_has_a_job.pizza.no_delivery_space"));
            return;
        }

        // The Enderman's vanilla teleport-to-position method handles the actual move.
        enderman.teleportTo(targetX, targetY, targetZ);
        enderman.setData(MobJobAttachments.ACTIVITY, "delivering_pizza");

        ItemStack pizza = new ItemStack(Items.PUMPKIN_PIE);
        pizza.set(DataComponents.CUSTOM_NAME, Component.literal("Nether Pizza"));
        enderman.spawnAtLocation(serverLevel, pizza);

        if (!player.getAbilities().instabuild) {
            payment.shrink(price);
        }

        player.sendOverlayMessage(Component.translatable(
                "message.every_mob_has_a_job.pizza.delivered", price));
    }
}
