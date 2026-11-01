# Every Mob Has a Job

A Minecraft Java mod that gives vanilla mobs personalities, jobs or special roles, custom animations, routines, and funny interactions.

> **Status:** Project setup and design phase. This repository does not yet contain a playable mod.

## Target

- Minecraft Java Edition 26.2
- NeoForge 26.2
- Java 25
- Single-player and multiplayer

Exact toolchain compatibility will be verified against the official NeoForge 26.2 development environment before implementation.

## Core scope

Cover every vanilla mob entity type in the Overworld, Nether, and End, including the Ender Dragon and other bosses. The final roster will be checked against the target version's registries so no mob is accidentally omitted or duplicated. Variants and baby forms will be represented as variants/states where appropriate.

Every mob receives a fitting personality and role. Not every creature needs an ordinary job: bosses and dangerous mobs can retain special, threatening identities.

## Locked roles from the reference video

These nine concepts are fixed and must not be replaced:

| Mob | Role | Required concept |
|---|---|---|
| Cow | Grass cutter | Cuts/trims grass as its work |
| Pig | Butcher / shopkeeper | Operates a meat shop and serves customers |
| Iron Golem | Police officer | Patrols, investigates theft, catches suspects and performs arrest interactions |
| Enderman | Pizza chef / delivery person | Prepares pizzas, works in a restaurant and teleports to deliver orders |
| Camel | Firefighter | Responds to fires and performs firefighting actions |
| Villagers | Secret business organisation | Manage businesses, restricted offices, guards and hidden facilities |
| Pillagers | Construction workers | Work at construction sites and handle building materials |
| Creeper | Therapy patient | Attends therapy and has funny reactions around personal space |
| Horse | Taxi driver | Offers paid rides to player-selected destinations |

These roles and their defining impressions are based on the supplied reference video: https://youtu.be/hqYlYNrbxNk

## Design pillars

1. **Visible behaviour:** jobs must be shown through animations and actions, not just labels or chat messages.
2. **Distinct personalities:** each mob should have recognisable idle behaviour, reactions, and a role-specific activity.
3. **World interaction:** suitable mobs can use workplaces, objects, paths, and environmental features.
4. **Comedy through animation:** timing, gestures, misunderstandings, and mob-to-mob interactions should create humour.
5. **Vanilla respect:** preserve normal spawning, combat, drops, breeding, trading, and other mechanics unless a feature explicitly requires a documented change.
6. **Multiplayer correctness:** important AI decisions and state changes are server-authoritative and synchronised to clients.
7. **Performance:** avoid expensive per-entity work every tick; use scheduled decisions, bounded searches, and safe pathfinding.

## Behaviour and animation system

The planned shared framework will provide:

- Job/personality definitions
- Activity states and transitions
- Workplace or activity-location handling
- Target selection and interaction rules
- Server-side AI decisions
- Client-side animation state synchronisation
- Safe interruption when entities die, unload, change dimensions, or lose a target
- Configurable feature toggles and tuning values

Animations should use appropriate model-part movement and state transitions. Whole-entity rotation alone is not sufficient for a dedicated animation.

## Multiplayer requirements

- Server-authoritative decisions for jobs, interactions, and rewards
- Consistent visible states for nearby clients
- No duplicate orders, rewards, or interactions caused by desynchronisation
- Dedicated-server support
- Single-player support without extra setup

## Development milestones

- [ ] Verify the complete vanilla mob roster for Java 26.2
- [ ] Create and build the NeoForge 26.2 project with Java 25
- [ ] Implement shared job, personality, activity, and synchronisation systems
- [ ] Prototype and test the nine locked reference-video roles
- [ ] Design and implement remaining Overworld mobs
- [ ] Design and implement Nether mobs
- [ ] Design and implement End mobs and boss behaviours
- [ ] Add animation support and mob-to-mob interactions
- [ ] Test dedicated-server multiplayer and persistence
- [ ] Profile performance and publish a documented release

## Repository structure (planned)

```
.
├── README.md
├── LICENSE
├── CONTRIBUTING.md
├── docs/
│   ├── MOB_ROSTER.md
│   ├── GAMEPLAY_DESIGN.md
│   └── TECHNICAL_ARCHITECTURE.md
└── src/
    ├── main/
    │   ├── java/
    │   └── resources/
    └── test/
```

## Current status

The project is at the specification stage. No feature should be described as implemented until its code exists and has been tested in-game.
