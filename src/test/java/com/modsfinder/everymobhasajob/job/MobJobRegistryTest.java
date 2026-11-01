package com.modsfinder.everymobhasajob.job;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MobJobRegistryTest {
    @Test
    void allNineLockedVideoAssignmentsRemainPresent() {
        assertEquals("grass_cutter", MobJobRegistry.resolve("minecraft:cow").id());
        assertEquals("butcher_shopkeeper", MobJobRegistry.resolve("minecraft:pig").id());
        assertEquals("police_officer", MobJobRegistry.resolve("minecraft:iron_golem").id());
        assertEquals("pizza_chef", MobJobRegistry.resolve("minecraft:enderman").id());
        assertEquals("firefighter", MobJobRegistry.resolve("minecraft:camel").id());
        assertEquals("business_network", MobJobRegistry.resolve("minecraft:villager").id());
        assertEquals("construction_worker", MobJobRegistry.resolve("minecraft:pillager").id());
        assertEquals("therapy_patient", MobJobRegistry.resolve("minecraft:creeper").id());
        assertEquals("taxi_driver", MobJobRegistry.resolve("minecraft:horse").id());
    }

    @Test
    void unlistedMobTypesReceiveStableSpeciesSpecificJobs() {
        MobJob first = MobJobRegistry.resolve("minecraft:future_mob");
        MobJob second = MobJobRegistry.resolve("minecraft:future_mob");
        MobJob another = MobJobRegistry.resolve("minecraft:other_mob");
        assertEquals(first, second);
        assertEquals("resident_future_mob", first.id());
        assertEquals("Future Mob Specialist", first.title());
        assertNotEquals(first.id(), another.id());
    }

    @Test
    void bossEntriesAreFlagged() {
        assertTrue(MobJobRegistry.resolve("minecraft:ender_dragon").bossRole());
        assertTrue(MobJobRegistry.resolve("minecraft:wither").bossRole());
        assertTrue(MobJobRegistry.resolve("minecraft:warden").bossRole());
        assertTrue(MobJobRegistry.resolve("minecraft:elder_guardian").bossRole());
    }

    @Test
    void invalidEntityIdsAreRejected() {
        assertThrows(IllegalArgumentException.class, () -> MobJobRegistry.resolve(" "));
        assertThrows(IllegalArgumentException.class, () -> MobJobRegistry.resolve(null));
    }
}
