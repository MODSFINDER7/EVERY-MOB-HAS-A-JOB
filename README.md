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
- The cow grass-cutter role has an actual AI goal. It changes a grass block to dirt after a grazing interval, respects the vanilla `mob_griefing` rule, and has a per-cow cooldown.
- Server owners can configure job assignment, custom AI and cow grazing in the server config.
- Unit tests cover the locked role IDs, generated fallback IDs and activity state.

## Still in development

Only the cow grass-cutter role currently changes in-game behaviour. Other job AI, shop/economy interactions, workplaces, animations and client activity synchronisation remain to be completed. The exact live registry audit and in-game multiplayer test are still pending.
See `docs/IMPLEMENTATION_STATUS.md` and `docs/CONFIGURATION.md`.
