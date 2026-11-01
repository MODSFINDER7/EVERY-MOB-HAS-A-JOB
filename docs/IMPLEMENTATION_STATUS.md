# Implementation status

## Implemented

- NeoForge 26.2 / Java 25 MDK-aligned Gradle setup and official wrapper.
- GitHub Actions build-and-test workflow.
- Server-side config for job assignment, custom AI, cow grazing and pig shop pricing.
- Persistent entity attachments for job ID and activity.
- Server-side assignment for every joining entity that is a Minecraft `Mob`.
- Curated jobs for the nine locked video roles and additional vanilla species.
- Stable generated fallback occupations for unlisted and future mob entity types.
- Cow grass-cutter AI: adult cows graze the block beneath them, changing grass to dirt after the configured interval. It respects `mob_griefing` and has a cooldown.
- Pig butcher shop: right-click a pig while holding emeralds to buy the configured number of cooked porkchops. The server checks and consumes payment; Creative players do not pay; full inventories cause the product to drop nearby.
- Unit tests for locked assignments, fallback generation and activity state.

## Still in development

The cow and pig are the first two job behaviours with actual gameplay effects. Other assigned occupations still need their AI goals, workplaces, custom interactions and role-specific animations.

Client-side activity animation synchronisation is not implemented yet. The exact Java 26.2 live entity-registry audit, dedicated-server test and two-client multiplayer test also remain pending.

## Coverage

When an entity enters a level on the logical server, the event handler checks whether it is a Minecraft `Mob`. The registry resolves curated job definitions or generates a stable species-specific fallback. Non-mob entities are excluded.

This covers mob types as they join the world, including future types. It does not mean that every species has its own custom work AI yet.

## Multiplayer and performance

- Job assignment and gameplay interactions execute on the logical server.
- The cow goal checks only the block directly beneath the cow.
- No global entity scans or renderer-driven AI.
- Cow work duration and cooldown are configurable.
- Pig purchases verify payment and mutate inventory on the server.

## Remaining acceptance gates

- `./gradlew build test` succeeds on Java 25.
- Dedicated server starts with the mod.
- Two clients observe consistent gameplay and activity.
- Job data survives world reload.
- Live registry audit lists every 26.2 mob and its assignment.
- Bosses retain vanilla phase and combat logic.
- Each remaining custom occupation has its own behaviour and animation test.
