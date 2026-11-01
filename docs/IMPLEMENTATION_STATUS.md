# Implementation status

## Implemented

- NeoForge 26.2 / Java 25 Gradle wrapper and GitHub Actions build/test workflow.
- Server-side config for job assignment, custom AI, cow grazing, pig shop, camel firefighting and horse taxi.
- Persistent job ID and activity entity attachments; both sync to tracking clients.
- Server-side occupation assignment for every joining entity that is a Minecraft Mob.
- Curated jobs for the nine locked video roles and many other vanilla species.
- Stable generated species-specific fallback occupations for unlisted and future mob entity types.
- Cow grass-cutter AI: adult cows graze the block beneath them, changing grass to dirt after a configurable interval. It respects mob_griefing and has a cooldown.
- Pig butcher shop: right-click a pig while holding emeralds to buy configured cooked porkchops. Payment is checked and consumed server-side; Creative players do not pay.
- Camel firefighter AI: searches a small nearby area on a bounded interval, approaches ordinary fire or soul fire, and extinguishes it. It respects mob_griefing and a configurable cooldown.
- Horse taxi paid boarding: right-click a tamed, saddled, empty horse while holding emeralds to pay the configured fare and mount it. Payment is verified before it is consumed.

## Still in development

Cow, pig, camel and horse are the first four occupations with gameplay effects. The horse's named destination selection and autonomous route navigation are unfinished. Other assigned occupations still need their AI goals, workplaces, custom interactions and role-specific animations.

Job ID and activity data sync to tracking clients, but custom animated model layers are not implemented yet. The exact Java 26.2 live entity-registry audit, dedicated-server test and two-client multiplayer play test also remain pending.

## Coverage

When an entity enters a level on the logical server, the event handler checks whether it is a Minecraft Mob. The registry resolves curated job definitions or generates a stable species-specific fallback. Non-mob entities are excluded.

This covers mob types as they join the world, including future types. It does not mean every species has its own custom work AI yet.

## Remaining acceptance gates

- ./gradlew build test succeeds on Java 25.
- Dedicated server starts with the mod.
- Two clients observe consistent gameplay and activity.
- Job data survives world reload.
- Live registry audit lists every 26.2 mob and its assignment.
- Bosses retain vanilla phase and combat logic.
- Each remaining custom occupation has its own behaviour and animation test.
