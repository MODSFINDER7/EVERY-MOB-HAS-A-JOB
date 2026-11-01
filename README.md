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
- Cow grass-cutter: adult cows periodically graze the block beneath them, changing grass to dirt after the configured work interval.
- Pig butcher shop: right-click a pig with emeralds to buy cooked porkchops. The price and output are configurable.
- Camel firefighter: camels scan a small nearby area for fire, walk toward it and extinguish it.
- Job ID and current activity are persisted and synchronised to tracking clients.
- Server owners can toggle job assignment, custom AI and the first job behaviours.

## Still in development

The cow, pig and camel are the first three jobs with gameplay behaviour. Other occupations, workplaces, richer economy, custom model animations and client animation layers remain in progress. The live registry audit and in-game multiplayer tests are pending.

See `docs/IMPLEMENTATION_STATUS.md`, `docs/CONFIGURATION.md` and `docs/ROLE_CATALOG.md`.
