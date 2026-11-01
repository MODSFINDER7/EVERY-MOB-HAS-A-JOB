package com.modsfinder.everymobhasajob.item;

import java.util.function.Supplier;

import com.modsfinder.everymobhasajob.EveryMobHasAJob;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    private static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, EveryMobHasAJob.MOD_ID);

    public static final Supplier<Item> NETHER_PIZZA = ITEMS.register(
            "nether_pizza",
            () -> new Item(new Item.Properties().food(
                    new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationModifier(0.8F)
                            .build())));

    private ModItems() {}

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
