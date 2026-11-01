# Every Mob Has a Job

A Minecraft Java mod that gives vanilla mobs jobs, personalities, routines, interactions and dedicated animations.

## Target

- Minecraft Java 26.2
- NeoForge 26.2.0.93
- Java 25
- Multiplayer-first; gameplay state and world changes are server-authoritative

## Active development scope: 10 mobs

The first playable feature slice is deliberately limited to these ten mobs. The nine reference-video roles are fixed; Sheep is the tenth addition.

| Mob | Occupation | Current gameplay slice |
|---|---|---|
| Cow | Grass Cutter | Adult cows graze grass blocks over a configurable work interval; respects mobGriefing and cooldown |
| Pig | Butcher / Shopkeeper | Sells cooked porkchops for emeralds |
| Iron Golem | Police Officer | Tracks vanilla target changes and displays a police alert cue |
| Enderman | Pizza Chef / Courier | Accepts emerald orders, teleports to a safe nearby delivery spot and drops Nether Pizza |
| Camel | Firefighter | Searches a bounded area, approaches nearby fire and extinguishes it |
| Villager | Secret Business Organisation | Accepts paper invitations and syncs a private-meeting activity marker |
| Pillager | Construction Worker | Places one supplied stone-brick block in a clear, supported, permitted position |
| Creeper | Therapy Patient | Accepts a poppy for a visual therapy interaction when calm; vanilla fuse remains unchanged |
| Horse | Taxi Driver | Offers paid boarding when tamed, saddled and unoccupied; player currently steers |
| Sheep | Wool Fashion Designer | Sells one block of its current wool colour for emeralds, then becomes sheared until vanilla grazing regrows its fleece |

The other vanilla mob occupations remain in the catalogue as descriptive assignments/fallbacks. Their custom gameplay behaviours are outside this first ten-mob feature slice.

## Universal systems

- Every joining Minecraft Mob receives a curated or generated species-specific occupation on the logical server.
- Job ID and activity are persisted and synchronised to tracking clients.
- Use a book on any mob to inspect its job, personality and current activity.
- Server-side config controls job assignment, inspection and the individual occupation interactions.

## Build and test

Use JDK 25.

- Windows: gradlew.bat build test
- macOS/Linux: ./gradlew build test
- Development client: ./gradlew runClient
- Game tests: ./gradlew runGameTestServer

## Remaining work

This is an active development build, not a finished release. The ten selected jobs still need deeper routines and dedicated custom animations. In particular, the Villager offices/guards/hidden facilities, autonomous Horse destinations, Enderman restaurant/order queue and multi-block Pillager construction are not implemented yet. A live 26.2 mob-registry audit, dedicated-server test and two-client multiplayer play test are also pending.

See docs/IMPLEMENTATION_STATUS.md, docs/CONFIGURATION.md, docs/ROLE_CATALOG.md and docs/LOCKED_VIDEO_ROLES.md.
