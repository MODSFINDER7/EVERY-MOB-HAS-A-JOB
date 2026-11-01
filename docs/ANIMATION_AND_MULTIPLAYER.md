# Animation and multiplayer contract

## Current state

The server stores each mob's job ID and current activity in persistent NeoForge entity attachments. The cow prototype writes the activity key `grazing` while it performs its grass-cutting routine and returns to `idle` afterwards.

## Animation layers

1. Base locomotion remains vanilla.
2. A future job overlay adds short actions such as grazing, serving, inspecting, hammering or breathing.
3. Transitions blend in/out rather than snapping between poses.
4. Missing animation controllers fall back to vanilla rendering.

Animation names in the job catalogue are state labels, not a claim that custom model animations are already available.

## Multiplayer

The logical server owns job assignment, decisions, work-site selection and world changes. Client-side activity replication and animated model layers are not implemented yet. They must be added with a bounded, validated sync path; send state changes rather than a full state packet every tick.

## Performance

- No per-frame AI decisions.
- No global entity scans.
- Event-driven assignment.
- Bounded searches only.
- Per-entity cooldowns for repeated work.
- Cosmetic animation should remain client-side.
