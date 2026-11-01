# Technical Architecture

## Current implementation status

The repository currently contains a minimal NeoForge mod entry point. The job/animation framework described below is planned and is not implemented yet.

## Target environment

- Minecraft Java 26.2
- NeoForge 26.2.0.93 (baseline from the 26.2 MDK; update only after checking the official Maven release list)
- Java 25
- ModDevGradle 2.0.148

The Gradle setup follows the official NeoForge 26.2 ModDevGradle MDK.

## Architecture goals

### Common/server side
- Mob role definitions and role state
- Activity scheduling and state transitions
- Worksite and interaction discovery
- Server-authoritative decisions
- Persistence for long-lived assignments where necessary
- Bounded pathfinding and target searches

### Client side
- Animation state presentation
- Model-part animation controllers
- Cosmetic particles and sound cues
- No authoritative job decisions

### Shared behaviour model

A mob's role should be represented separately from its current activity. For example, a taxi horse has the taxi-driver role, while waiting, accepting a passenger, travelling, and completing a ride are temporary activity states.

Activities must be interruptible. Death, unload, dimension change, target loss, and blocked paths must not leave permanent stuck states.

## Performance constraints

- Do not perform expensive world-wide entity scans every tick.
- Use scheduled or event-driven decisions with bounded search radii.
- Avoid assigning complex routines to every mob simultaneously when no player is nearby.
- Reuse navigation and interaction state where safe.
- Test with dense groups of active mobs.

## Multiplayer constraints

- Server owns job assignment, interactions, and rewards.
- Clients receive only the state needed to display the correct animation.
- Interactions must be idempotent where duplicate packets or repeated triggers are possible.
- Verify dedicated-server startup, tracking-range behaviour, and entity unload/reload.

## First implementation milestone

Implement a small shared activity-state framework and prototype one locked role before scaling to the complete mob roster.
