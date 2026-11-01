package com.modsfinder.everymobhasajob.events;

import com.modsfinder.everymobhasajob.EveryMobHasAJob;
import com.modsfinder.everymobhasajob.config.MobJobsConfig;
import com.modsfinder.everymobhasajob.data.MobJobAttachments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = EveryMobHasAJob.MOD_ID)
public final class PillagerConstructionEvents {
    private PillagerConstructionEvents() {}

    @SubscribeEvent
    public static void onConstructionSupply(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getTarget() instanceof Pillager pillager)) return;
        if (!MobJobsConfig.ENABLE_JOB_ASSIGNMENTS.get()
                || !MobJobsConfig.ENABLE_PILLAGER_CONSTRUCTION.get()) return;
        if (!event.getItemStack().is(Items.STONE_BRICKS)) return;

        // The client sends the interaction; block placement and payment are server-authoritative.
        if (event.getLevel().isClientSide()) return;
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (!(pillager.level() instanceof ServerLevel serverLevel)) return;

        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);

        if (pillager.getTarget() != null || pillager.isAggressive()) {
            player.sendOverlayMessage(Component.translatable(
                    "message.every_mob_has_a_job.construction.busy"));
            return;
        }

        BlockPos placePosition = pillager.blockPosition().relative(pillager.getDirection());
        if (!serverLevel.getBlockState(placePosition).canBeReplaced()
                || !serverLevel.getBlockState(placePosition.above()).isAir()
                || !serverLevel.getBlockState(placePosition.below())
                        .isFaceSturdy(serverLevel, placePosition.below(), Direction.UP)
                || !serverLevel.mayInteract(player, placePosition)) {
            player.sendOverlayMessage(Component.translatable(
                    "message.every_mob_has_a_job.construction.no_space"));
            return;
        }

        ItemStack bricks = event.getItemStack();
        if (!player.getAbilities().instabuild && bricks.isEmpty()) return;

        if (!serverLevel.setBlock(placePosition, Blocks.STONE_BRICKS.defaultBlockState(), 3)) {
            player.sendOverlayMessage(Component.translatable(
                    "message.every_mob_has_a_job.construction.failed"));
            return;
        }

        if (!player.getAbilities().instabuild) {
            bricks.shrink(1);
        }

        pillager.setData(MobJobAttachments.ACTIVITY, "constructing");
        player.sendOverlayMessage(Component.translatable(
                "message.every_mob_has_a_job.construction.success"));
    }
}
