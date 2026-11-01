# Animation and multiplayer contract

## Animation layers

1. Base locomotion remains vanilla.
2. Job overlay adds short actions such as grazing, serving, inspecting, hammering or breathing.
3. Transitions blend in and out rather than snapping between poses.
4. Missing animation controllers fall back to vanilla rendering.

## Replicated activity snapshot

- Stable activity key.
- Activity start game time or elapsed ticks.
- Optional small integer animation variant.
- Optional work-site reference.

The server sends changes only. Clients interpolate animation progress locally. Packets validate entity existence, dimension and tracking range before applying.

## Performance constraints

- No per-frame AI decisions.
- No global entity scans.
- Event-driven activity transitions.
- Staggered schedules and cached work sites with expiry.
- Animation-only cosmetics stay client-side.
- Config switches for job AI, animations, economy and job groups.
