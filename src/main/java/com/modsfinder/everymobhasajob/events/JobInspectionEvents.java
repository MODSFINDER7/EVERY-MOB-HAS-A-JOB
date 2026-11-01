package com.modsfinder.everymobhasajob.events;

import com.modsfinder.everymobhasajob.EveryMobHasAJob;
import com.modsfinder.everymobhasajob.config.MobJobsConfig;
import com.modsfinder.everymobhasajob.data.MobJobAttachments;
import com.modsfinder.everymobhasajob.job.MobJob;
import com.modsfinder.everymobhasajob.job.MobJobRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = EveryMobHasAJob.MOD_ID)
public final class JobInspectionEvents {
    private JobInspectionEvents() {}

    @SubscribeEvent
    public static void onInspectMob(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getTarget() instanceof Mob mob)) return;
        if (!MobJobsConfig.ENABLE_JOB_ASSIGNMENTS.get()
                || !MobJobsConfig.ENABLE_JOB_INSPECTION.get()) return;
        if (!event.getItemStack().is(Items.BOOK)) return;

        // Keep the client prediction untouched; the server sends the authoritative job details.
        if (event.getLevel().isClientSide()) return;
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);

        var entityKey = BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType());
        if (entityKey == null) return;

        MobJob job = MobJobRegistry.resolve(entityKey.toString());
        String activity = mob.getData(MobJobAttachments.ACTIVITY);
        player.sendSystemMessage(Component.translatable(
                "message.every_mob_has_a_job.inspect",
                mob.getDisplayName(),
                job.title(),
                job.personality(),
                activity));
    }
}
