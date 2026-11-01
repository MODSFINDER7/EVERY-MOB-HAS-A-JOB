package com.modsfinder.everymobhasajob.job;

public final class MobJobState {
    private String activity = "idle";
    private int activityTicks;
    private long transitionGameTime = -1L;

    public void transitionTo(String next, long gameTime) {
        if (next == null || next.isBlank()) throw new IllegalArgumentException("next");
        if (!activity.equals(next)) {
            activity = next;
            activityTicks = 0;
            transitionGameTime = gameTime;
        }
    }

    public void tick() {
        if (activityTicks < Integer.MAX_VALUE) activityTicks++;
    }

    public Snapshot snapshot() {
        return new Snapshot(activity, activityTicks, transitionGameTime);
    }

    public record Snapshot(String activity, int activityTicks, long transitionGameTime) {}
}
