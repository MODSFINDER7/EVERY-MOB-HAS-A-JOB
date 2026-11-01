package com.modsfinder.everymobhasajob.events;

import com.modsfinder.everymobhasajob.EveryMobHasAJob;
import com.modsfinder.everymobhasajob.config.MobJobsConfig;
import com.modsfinder.everymobhasajob.data.MobJobAttachments;
import com.modsfinder.everymobhasajob.goal.CowGrassCutterGoal;
import com.modsfinder.everymobhasajob.job.MobJob;
import com.modsfinder.everymobhasajob.job.MobJobRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.cow.Cow;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@EventBusSubscriber(modid = EveryMobHasAJob.MOD_ID)
public final class MobJobEvents {
    private MobJobEvents() {}

    @SubscribeEvent
    public static void onMobJoinLevel(EntityJoinLevelEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (!MobJobsConfig.ENABLE_JOB_ASSIGNMENTS.get()) return;
        if (!(event.getEntity() instanceof Mob mob)) return;

        var entityKey = BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType());
        if (entityKey == null) return;

        MobJob job = MobJobRegistry.resolve(entityKey.toString());
        if (!mob.hasData(MobJobAttachments.JOB_ID)) {
            mob.setData(MobJobAttachments.JOB_ID, job.id());
        }
        if (!mob.hasData(MobJobAttachments.ACTIVITY)) {
            mob.setData(MobJobAttachments.ACTIVITY, "idle");
        }

        if (mob instanceof Cow cow
                && MobJobsConfig.ENABLE_CUSTOM_AI.get()
                && MobJobsConfig.ENABLE_COW_GRASS_CUTTER.get()
                && !cow.getData(MobJobAttachments.COW_GOAL_INSTALLED)) {
            cow.getGoalSelector().addGoal(6, new CowGrassCutterGoal(cow));
            cow.setData(MobJobAttachments.COW_GOAL_INSTALLED, true);
        }
    }
}
