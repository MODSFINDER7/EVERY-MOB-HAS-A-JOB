# Implementation status

## Implemented

- NeoForge 26.2 / Java 25 Gradle wrapper and build workflow.
- Server-side configuration for job assignment, custom AI, cow grazing, pig shop and camel firefighting.
- Persistent job ID and activity entity attachments; both are synchronised to tracking clients.
- Server-side occupation assignment for each joining entity that is a Minecraft `Mob`.
- Curated jobs for the nine locked video roles and many other vanilla species.
- Stable generated species-specific fallback occupations for unlisted and future mob entity types.
- Cow grass-cutter AI: adult cows graze the block beneath them, changing grass to dirt after the configured interval. It respects `mob_griefing` and has a cooldown.
- Pig butcher shop: right-click a pig while holding emeralds to buy configured cooked porkchops. Payment is checked and consumed server-side; Creative players do not pay.
- Camel firefighter AI: searches a small nearby area on a bounded interval, approaches ordinary fire or soul fire, and extinguishes it. It respects `mob_griefing` and a configurable cooldown.
- Unit tests for locked assignments, fallback generation and activity state.

## Still in development

Cow, pig and camel are the first three occupations with gameplay effects. Other assigned occupations still need their AI goals, workplaces, custom interactions, and role-specific animations.

The activity state is synchronised to tracking clients, but custom animated model layers are not implemented yet. The exact Java 26.2 live entity-registry audit, dedicated-server test and two-client multiplayer play test also remain pending.

## Coverage

When an entity enters a level on the logical server, the event handler checks whether it is a Minecraft `Mob`. The registry resolves curated job definitions or generates a stable species-specific fallback. Non-mob entities are excluded.

This covers mob types as they join the world, including future types. It does not mean every species has its own custom work AI yet.

## Remaining acceptance gates

- `./gradlew build test` succeeds on Java 25.
- Dedicated server starts with the mod.
- Two clients observe consistent gameplay and activity.
- Job data survives world reload.
- Live registry audit lists every 26.2 mob and its assignment.
- Bosses retain vanilla phase and combat logic.
- Each remaining custom occupation has its own behaviour and animation test.
