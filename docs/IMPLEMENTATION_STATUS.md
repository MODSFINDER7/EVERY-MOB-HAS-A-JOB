# Implementation status

## Added in this batch

- Immutable job definition model.
- Central job registry for the nine locked video assignments.
- Deterministic activity-state model.
- Data file recording locked assignments and fallback policy.
- Multiplayer, animation and all-mob coverage contracts.

## Not yet playable

This is not a playable release. The current classes are a loader-independent foundation. They are not yet connected to Minecraft entity events, custom navigation goals, workstations, trade screens, persistent entity data, animation renderers or network packets. No Gradle build or in-game test has been run in this environment.

## All-mob coverage rule

The runtime must enumerate the actual Minecraft entity-type registry and classify entities by the mob base class. Every mob resolves to either a dedicated job or the fallback job. Non-mob entities such as projectiles, dropped items, vehicles, display entities and experience orbs are excluded.

A registry audit against a clean 26.2 client and dedicated server is required before claiming complete coverage. The audit must report entity ID, category, resolved job, dedicated/fallback status and boss status.

## Multiplayer rules

- Logical server owns job state, schedules, navigation, work-site selection and world changes.
- Clients receive only the state needed to display animations and UI.
- Send state changes, not full state every tick.
- Persist job state through entity save/load hooks.
- Use bounded work-site searches, staggered schedules and per-entity cooldowns.

## Acceptance gates

- Gradle build succeeds on Java 25.
- Dedicated server starts with the mod.
- Two clients observe the same activity state.
- World reload preserves job state.
- Every vanilla mob resolves to a valid job.
- Bosses retain vanilla phase and combat logic.
- Disabling the feature restores vanilla behaviour.
