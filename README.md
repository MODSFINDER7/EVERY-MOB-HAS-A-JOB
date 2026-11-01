# Every Mob Has a Job

A Minecraft Java mod that gives vanilla mobs jobs, personalities, routines and eventually dedicated animations.

## Target

- Minecraft Java 26.2
- NeoForge 26.2.0.93
- Java 25
- Multiplayer-first, with gameplay state owned by the logical server

## Locked reference-video jobs

Cow — Grass Cutter; Pig — Butcher/Shopkeeper; Iron Golem — Police Officer; Enderman — Pizza Chef and Courier; Camel — Firefighter; Villagers — Secret Business Organisation; Pillagers — Construction Workers; Creeper — Therapy Patient; Horse — Taxi Driver.

These nine roles are project requirements and must not be replaced.

## Build

Use JDK 25. The repository includes the Gradle wrapper.

- Windows: `gradlew.bat build`
- macOS/Linux: `./gradlew build`
- Launch a development client: `./gradlew runClient`

GitHub Actions builds the project on pushes and pull requests.

## Current development status

Mob job assignment is connected to the server-side entity join event. Curated mobs receive named occupations; every other entity that is a `Mob` receives a stable species-derived fallback. The cow grass-cutter goal is the first custom job behaviour implemented.

This is not yet a finished release. Other job behaviours, trading/economy, workstations, custom model animations and client activity synchronisation remain in progress. See `docs/IMPLEMENTATION_STATUS.md`.
