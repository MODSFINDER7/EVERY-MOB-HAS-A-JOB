package com.modsfinder.everymobhasajob.data;

import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.modsfinder.everymobhasajob.EveryMobHasAJob;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class MobJobAttachments {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENTS =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, EveryMobHasAJob.MOD_ID);

    public static final Supplier<AttachmentType<String>> JOB_ID = ATTACHMENTS.register(
            "job_id", () -> AttachmentType.builder(() -> "town_resident")
                    .serialize(Codec.STRING.fieldOf("value"))
                    .sync(ByteBufCodecs.STRING_UTF8)
                    .build());

    public static final Supplier<AttachmentType<String>> ACTIVITY = ATTACHMENTS.register(
            "activity", () -> AttachmentType.builder(() -> "idle")
                    .serialize(Codec.STRING.fieldOf("value"))
                    .sync(ByteBufCodecs.STRING_UTF8)
                    .build());

    // Runtime-only markers: goals are installed when an entity joins a server level.
    public static final Supplier<AttachmentType<Boolean>> COW_GOAL_INSTALLED = ATTACHMENTS.register(
            "cow_goal_installed", () -> AttachmentType.builder(() -> false).build());

    public static final Supplier<AttachmentType<Boolean>> CAMEL_GOAL_INSTALLED = ATTACHMENTS.register(
            "camel_goal_installed", () -> AttachmentType.builder(() -> false).build());

    private MobJobAttachments() {}

    public static void register(IEventBus modEventBus) {
        ATTACHMENTS.register(modEventBus);
    }
}
