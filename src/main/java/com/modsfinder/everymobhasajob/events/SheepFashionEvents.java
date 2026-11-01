package com.modsfinder.everymobhasajob.events;

import com.modsfinder.everymobhasajob.EveryMobHasAJob;
import com.modsfinder.everymobhasajob.config.MobJobsConfig;
import com.modsfinder.everymobhasajob.data.MobJobAttachments;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = EveryMobHasAJob.MOD_ID)
public final class SheepFashionEvents {
    private SheepFashionEvents() {}

    @SubscribeEvent
    public static void onWoolOrder(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getTarget() instanceof Sheep sheep)) return;
        if (!MobJobsConfig.ENABLE_JOB_ASSIGNMENTS.get()
                || !MobJobsConfig.ENABLE_SHEEP_FASHION.get()) return;
        if (!event.getItemStack().is(Items.EMERALD)) return;

        // The server validates the order, charges the player and changes the sheep's fleece state.
        if (event.getLevel().isClientSide()) return;
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);

        if (!sheep.isAlive() || sheep.isBaby() || sheep.isSheared()) {
            player.sendOverlayMessage(Component.translatable(
                    "message.every_mob_has_a_job.sheep_fashion.no_wool"));
            return;
        }

        int price = MobJobsConfig.SHEEP_FASHION_EMERALD_PRICE.get();
        ItemStack payment = event.getItemStack();
        if (!player.getAbilities().instabuild && payment.getCount() < price) {
            player.sendOverlayMessage(Component.translatable(
                    "message.every_mob_has_a_job.sheep_fashion.not_enough", price));
            return;
        }

        ResourceLocation woolId = ResourceLocation.withDefaultNamespace(
                sheep.getColor().getName() + "_wool");
        var woolItem = BuiltInRegistries.ITEM.get(woolId);
        if (woolItem == Items.AIR) {
            EveryMobHasAJob.LOGGER.warn("No wool item registered for sheep colour {}", sheep.getColor());
            return;
        }

        if (!player.getAbilities().instabuild) {
            payment.shrink(price);
        }

        sheep.setSheared(true);
        sheep.setData(MobJobAttachments.ACTIVITY, "designing_wool");
        ItemStack product = new ItemStack(woolItem);
        if (!player.getInventory().add(product)) {
            player.drop(product, false);
        }

        player.sendOverlayMessage(Component.translatable(
                "message.every_mob_has_a_job.sheep_fashion.success", price,
                Component.translatable(woolItem.getDescriptionId())));
    }
}
