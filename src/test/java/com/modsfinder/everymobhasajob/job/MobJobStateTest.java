package com.modsfinder.everymobhasajob.job;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MobJobStateTest {
    @Test
    void transitionResetsActivityTimerAndRecordsGameTime() {
        MobJobState state = new MobJobState();
        state.tick();
        state.transitionTo("grazing", 120L);
        MobJobState.Snapshot snapshot = state.snapshot();
        assertEquals("grazing", snapshot.activity());
        assertEquals(0, snapshot.activityTicks());
        assertEquals(120L, snapshot.transitionGameTime());
    }

    @Test
    void repeatedSameActivityDoesNotResetTheTimer() {
        MobJobState state = new MobJobState();
        state.transitionTo("grazing", 120L);
        state.tick();
        state.transitionTo("grazing", 130L);
        assertEquals(1, state.snapshot().activityTicks());
        assertEquals(120L, state.snapshot().transitionGameTime());
    }
}
