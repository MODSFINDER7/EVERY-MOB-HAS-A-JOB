package com.modsfinder.everymobhasajob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.modsfinder.everymobhasajob.data.MobJobAttachments;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(EveryMobHasAJob.MOD_ID)
public final class EveryMobHasAJob {
    public static final String MOD_ID = "every_mob_has_a_job";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public EveryMobHasAJob(IEventBus modEventBus) {
        MobJobAttachments.register(modEventBus);
        LOGGER.info("Every Mob Has a Job 0.2.0 initialised.");
    }
}
