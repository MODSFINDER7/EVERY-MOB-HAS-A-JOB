package com.modsfinder.everymobhasajob.events;

import com.modsfinder.everymobhasajob.EveryMobHasAJob;
import com.modsfinder.everymobhasajob.config.MobJobsConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = EveryMobHasAJob.MOD_ID)
public final class PigButcherShopEvents {
    private PigButcherShopEvents() {}

    @SubscribeEvent
    public static void onPigShopInteract(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getTarget() instanceof Pig)) return;
        if (!MobJobsConfig.ENABLE_JOB_ASSIGNMENTS.get()
                || !MobJobsConfig.ENABLE_PIG_BUTCHER_SHOP.get()) return;
        if (!event.getItemStack().is(Items.EMERALD)) return;

        // Let the client send the interaction packet; the authoritative server handles the trade.
        if (event.getLevel().isClientSide()) return;
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);

        ItemStack payment = event.getItemStack();
        int price = MobJobsConfig.PIG_SHOP_EMERALD_PRICE.get();
        int amount = MobJobsConfig.PIG_SHOP_PORKCHOPS_PER_TRADE.get();

        if (!player.getAbilities().instabuild) {
            if (payment.getCount() < price) {
                player.sendOverlayMessage(
                        Component.translatable("message.every_mob_has_a_job.pig_shop.not_enough", price));
                return;
            }
            payment.shrink(price);
        }

        ItemStack product = new ItemStack(Items.COOKED_PORKCHOP, amount);
        if (!player.getInventory().add(product)) {
            player.drop(product, false);
        }

        player.sendOverlayMessage(
                Component.translatable("message.every_mob_has_a_job.pig_shop.success", amount, price));
    }
}
