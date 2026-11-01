# Implementation status

## Added in this source batch

- NeoForge 26.2 / Java 25 MDK-aligned Gradle setup.
- Gradle wrapper scripts, wrapper JAR and wrapper properties.
- GitHub Actions build workflow.
- Persistent entity attachments for job ID and current activity.
- Server-side entity-join assignment for every entity that is a Minecraft `Mob`.
- Curated occupations for the nine locked video roles and a broad set of other vanilla mobs.
- Generated, species-named fallback occupation for any vanilla or future mob type not in the curated catalogue.
- First real job AI prototype: adult cows periodically graze the grass block beneath them, respecting the `mobGriefing` gamerule.
- Unit tests for locked roles, fallback uniqueness, boss policy and activity state.

## Not yet a finished playable release

Only the cow grass-cutter role currently changes in-game behaviour. Other mob occupations are assigned and catalogued, but their workstations, navigation goals, economy/trade interactions, job-specific AI and dedicated model animations are not finished.

Job IDs and activity state are persisted on the server. Client-side animation replication has not yet been implemented, so current attachment state is not presented as a finished multiplayer animation system.

## Coverage guarantee

Runtime assignment checks every joining entity with `instanceof Mob` and resolves its registered entity-type ID. Every unlisted mob receives a stable species-specific fallback. Non-mob entities such as projectiles, dropped items, vehicles, display entities and experience orbs are excluded.

The exact live 26.2 roster still requires an in-game registry audit on a clean client and dedicated server. That audit has not yet been run.

## Server authority and performance

- Job assignment and the cow goal are installed only on the logical server.
- The cow goal checks only its current support block; it does not perform a world-wide or chunk-wide scan.
- The cow goal honours `mobGriefing` and uses a per-cow cooldown.
- No custom AI decisions run from render callbacks.

## Acceptance gates

- `./gradlew build` succeeds on Java 25.
- Dedicated server starts with the mod.
- Two clients observe consistent job/activity state.
- Job data survives world reload.
- All vanilla mob entity types resolve to valid jobs.
- Bosses retain vanilla phase and combat logic.
- Disabling the feature restores vanilla behaviour.
- Every custom occupation has an in-game behaviour test.
