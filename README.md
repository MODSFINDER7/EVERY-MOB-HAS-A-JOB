# Every Mob Has a Job

A Minecraft Java mod that gives vanilla mobs jobs, personalities, routines, interactions and dedicated animations.

## Target

- Minecraft Java 26.2
- NeoForge 26.2.0.93
- Java 25
- Multiplayer-first; gameplay state and world changes are server-authoritative

## Locked reference-video jobs

Cow — Grass Cutter; Pig — Butcher/Shopkeeper; Iron Golem — Police Officer; Enderman — Pizza Chef and Courier; Camel — Firefighter; Villagers — Secret Business Organisation; Pillagers — Construction Workers; Creeper — Therapy Patient; Horse — Taxi Driver.

These nine assignments are fixed and must not be replaced.

## Current implementation

- Every joining Minecraft Mob receives a curated or generated species-specific occupation on the logical server.
- Job ID and activity are persisted and synchronised to tracking clients.
- Use a book on any mob to inspect its job, personality and current activity.
- Adult cows graze grass blocks over a configurable work interval, respecting mobGriefing and a cooldown.
- Pigs sell cooked porkchops for configurable emerald prices.
- Camels search a bounded nearby area for fire, approach it and extinguish it, respecting mobGriefing.
- Tamed, saddled horses offer paid boarding; the player currently steers the ride.
- Iron Golem police response records vanilla target changes and displays an alert cue without replacing combat AI.
- Endermen accept emerald pizza orders, teleport to a safe nearby delivery spot and drop a renamed pumpkin pie.
- Pillagers can place one supplied stone-brick block in a clear, supported position, with server interaction permission checks.
- Creepers accept a poppy for a visual therapy interaction only when not ignited, swelling or targeting another entity.
- Villagers accept a paper invitation for a synced private-business meeting activity.

## Build and test

Use JDK 25.

- Windows: gradlew.bat build test
- macOS/Linux: ./gradlew build test
- Development client: ./gradlew runClient
- Game tests: ./gradlew runGameTestServer

## Remaining work

The mod is not feature-complete. Custom model animation layers, actual villager offices/guards, full autonomous horse destinations, Enderman restaurant and order queue, multi-block Pillager construction, and most species-specific AI/workplaces are still in development. Live 26.2 registry audit and dedicated-server/two-client play tests are also pending.

See docs/IMPLEMENTATION_STATUS.md, docs/CONFIGURATION.md, docs/ROLE_CATALOG.md and docs/LOCKED_VIDEO_ROLES.md.
