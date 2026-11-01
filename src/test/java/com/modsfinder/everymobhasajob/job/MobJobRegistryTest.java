package com.modsfinder.everymobhasajob.job;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
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
    void tenthActiveMobIsTheSheepFashionDesigner() {
        MobJob sheep = MobJobRegistry.resolve("minecraft:sheep");
        assertEquals("wool_fashion_designer", sheep.id());
        assertEquals("Wool Fashion Designer", sheep.title());
        assertFalse(sheep.bossRole());
    }

    @Test
    void everyCuratedDefinitionHasCompleteMetadata() {
        assertFalse(MobJobRegistry.entries().isEmpty());
        for (Map.Entry<String, MobJob> entry : MobJobRegistry.entries().entrySet()) {
            assertTrue(entry.getKey().startsWith("minecraft:"), entry.getKey());
            MobJob job = entry.getValue();
            assertNotNull(job.id(), entry.getKey());
            assertFalse(job.id().isBlank(), entry.getKey());
            assertFalse(job.title().isBlank(), entry.getKey());
            assertFalse(job.personality().isBlank(), entry.getKey());
            assertFalse(job.activity().isBlank(), entry.getKey());
            assertFalse(job.animationSet().isBlank(), entry.getKey());
        }
    }

    @Test
    void unlistedMobTypesReceiveStableSpeciesSpecificJobs() {
        MobJob first = MobJobRegistry.resolve("minecraft:future_mob");
        MobJob second = MobJobRegistry.resolve("minecraft:future_mob");
        MobJob another = MobJobRegistry.resolve("minecraft:other_mob");

        assertSame(first, second);
        assertEquals("resident_future_mob", first.id());
        assertEquals("Future Mob Specialist", first.title());
        assertNotEquals(first.id(), another.id());
        assertFalse(first.bossRole());
    }

    @Test
    void bossEntriesAreFlaggedWithoutChangingCivilianAssignments() {
        assertTrue(MobJobRegistry.resolve("minecraft:ender_dragon").bossRole());
        assertTrue(MobJobRegistry.resolve("minecraft:wither").bossRole());
        assertTrue(MobJobRegistry.resolve("minecraft:warden").bossRole());
        assertTrue(MobJobRegistry.resolve("minecraft:elder_guardian").bossRole());
        assertFalse(MobJobRegistry.resolve("minecraft:cow").bossRole());
        assertFalse(MobJobRegistry.resolve("minecraft:sheep").bossRole());
    }

    @Test
    void registryViewCannotBeMutatedByCallers() {
        assertThrows(UnsupportedOperationException.class,
                () -> MobJobRegistry.entries().clear());
    }

    @Test
    void invalidEntityIdsAreRejected() {
        assertThrows(IllegalArgumentException.class, () -> MobJobRegistry.resolve(" "));
        assertThrows(IllegalArgumentException.class, () -> MobJobRegistry.resolve(null));
    }
}
