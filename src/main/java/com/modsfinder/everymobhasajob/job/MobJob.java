package com.modsfinder.everymobhasajob.job;

public record MobJob(String id, String title, String personality, String activity,
                     String animationSet, boolean bossRole) {
    public MobJob {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("title");
        if (personality == null || personality.isBlank()) throw new IllegalArgumentException("personality");
        if (activity == null || activity.isBlank()) throw new IllegalArgumentException("activity");
        if (animationSet == null || animationSet.isBlank()) throw new IllegalArgumentException("animationSet");
    }
}
