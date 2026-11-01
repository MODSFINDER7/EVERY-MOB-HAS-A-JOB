package com.modsfinder.everymobhasajob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.modsfinder.everymobhasajob.config.MobJobsConfig;
import com.modsfinder.everymobhasajob.data.MobJobAttachments;
import com.modsfinder.everymobhasajob.item.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(EveryMobHasAJob.MOD_ID)
public final class EveryMobHasAJob {
    public static final String MOD_ID = "every_mob_has_a_job";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public EveryMobHasAJob(IEventBus modEventBus, ModContainer modContainer) {
        MobJobAttachments.register(modEventBus);
        ModItems.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.SERVER, MobJobsConfig.SPEC);
        LOGGER.info("Every Mob Has a Job 0.2.0 initialised.");
    }
}
