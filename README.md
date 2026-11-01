# Every Mob Has a Job

A Minecraft Java mod that gives vanilla mobs jobs, personalities, routines and dedicated animations.

## Target

- Minecraft Java 26.2
- NeoForge 26.2.0.93
- Java 25
- Multiplayer-first; gameplay decisions are server-authoritative

## Locked reference-video jobs

Cow — Grass Cutter; Pig — Butcher/Shopkeeper; Iron Golem — Police Officer; Enderman — Pizza Chef and Courier; Camel — Firefighter; Villagers — Secret Business Organisation; Pillagers — Construction Workers; Creeper — Therapy Patient; Horse — Taxi Driver.

These nine roles are fixed requirements.

## Build

Use JDK 25.

- Windows: `gradlew.bat build`
- macOS/Linux: `./gradlew build`
- Development client: `./gradlew runClient`
- Game tests: `./gradlew runGameTestServer`

## Current implementation

- Every joining entity that is a Minecraft `Mob` receives a curated or generated species-specific job assignment on the logical server.
- Cow grass-cutter: adult cows periodically graze the block beneath them, changing grass to dirt after the configured work interval. The role respects `mob_griefing` and has a configurable per-cow cooldown.
- Pig butcher shop: right-click a pig while holding emeralds to buy cooked porkchops. Price and output are configurable; Creative players do not pay.
- Server owners can toggle job assignment, custom AI, cow grazing and the pig shop.
- Unit tests cover locked role IDs, generated fallback IDs and activity state.

## Still in development

The cow and pig are the first two jobs with actual gameplay behaviour. The remaining occupations, workplace systems, economy beyond the pig trade, dedicated model animations and client activity synchronisation remain in progress. A live Java 26.2 mob-registry audit and in-game multiplayer tests are still pending.

See `docs/IMPLEMENTATION_STATUS.md`, `docs/CONFIGURATION.md` and `docs/ROLE_CATALOG.md`.
