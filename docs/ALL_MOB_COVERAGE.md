# All-mob coverage strategy for Java 26.2

The target is every vanilla mob entity type in the Overworld, Nether and End. This does not mean every entity in the entity registry: projectiles, item entities, vehicles, display entities, experience orbs and similar non-mobs are excluded.

## Registry-driven discovery

Enumerate the entity-type registry and classify entries by the mob base class. Resolve each mob through the job registry:
- Dedicated species assignment when a definition exists.
- General fallback routine otherwise.
- Boss-specific category for boss entities, preserving their identity and combat.

This makes new vanilla mobs discoverable without silently omitting them from a hand-maintained list.

## Fixed video assignments

1. Cow — grass cutter.
2. Pig — butcher/shopkeeper selling meat.
3. Iron Golem — police officer.
4. Enderman — pizza chef and teleporting delivery person.
5. Camel — firefighter.
6. Villagers — secret business organisation with restricted offices, guards and hidden facilities.
7. Pillagers — construction workers.
8. Creeper — therapy patient.
9. Horse — paid taxi driver offering selected destinations.

## Boss policy

The Ender Dragon, Wither, Elder Guardian and Warden must not become ordinary civilians. Their job concepts must preserve signature mechanics, arena behaviour and combat identity. Boss roles require separate balance tests.

## Audit output

Generate a sorted CSV with entity ID, category, job ID, dedicated/fallback flag and boss flag. This audit is the source of truth for the exact 26.2 roster; do not rely on an unverified wiki list.
