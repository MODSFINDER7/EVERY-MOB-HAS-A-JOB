# Implementation status

## Active feature scope: 10 mobs

The first gameplay slice is limited to the nine locked reference-video roles plus Sheep as the tenth mob. Other species keep their descriptive occupation entries and generated fallbacks, but do not receive custom behaviour in this milestone.

| Mob | Implemented gameplay slice | Remaining core work |
|---|---|---|
| Cow | Adult cow grass-cutter goal | Better patch-to-patch work routine and animation |
| Pig | Emerald-for-cooked-porkchop shop | Stall/work hours, stock display and shop animation |
| Iron Golem | Synced police activity on vanilla target changes; alert particles | Actual patrol route and theft/intrusion report system |
| Enderman | Emerald pizza order, safe nearby teleport and custom Nether Pizza item | Restaurant workstation, order queue, preparation and courier route |
| Camel | Bounded nearby fire search, navigation and extinguishing | Fire station/dispatch routine and animation |
| Villager | Paper invitation sets synced private-meeting activity | Restricted offices, guards, hidden facilities and meeting group behaviour |
| Pillager | Places one supplied stone-brick block after server-side safety/permission checks | Construction site, material carrying, multi-block plans and work animations |
| Creeper | Poppy-triggered visual therapy interaction while calm | Therapy room and non-invasive calming routine; vanilla fuse remains intact |
| Horse | Paid boarding for tamed, saddled, empty horses | Player-selected destinations and autonomous route navigation |
| Sheep | Emerald wool order; returns one wool block matching its fleece colour and becomes sheared | Fashion stall/display routine and dedicated pose/animation |

## Universal systems

- NeoForge 26.2 / Java 25 Gradle wrapper and GitHub Actions build/test workflow.
- Server-side config for job assignment, job inspection and the ten selected mob features.
- Persistent job ID and activity entity attachments; both sync to tracking clients.
- Server-side occupation assignment for every joining entity that is a Minecraft Mob.
- Curated jobs for the nine locked video roles and many other vanilla species.
- Stable generated species-specific fallback occupations for unlisted and future mob entity types.
- Universal job inspection: use a book on a mob to display its occupation, personality and current activity; the book is not consumed.

## Safety and multiplayer notes

All custom interactions in this slice validate and mutate gameplay state on the logical server. Sheep wool payment is consumed only after the server validates that the sheep is alive, adult and not already sheared. Creative players do not pay. The sheep is sheared only after a valid purchase, allowing vanilla grazing to regrow its fleece.

Bosses retain their vanilla encounter mechanics; they are not part of the ten civilian-job feature slice.

## Still in development

The ten mobs do not yet have complete autonomous work schedules or dedicated animated model layers. Several current features are deliberately small gameplay prototypes rather than the final video-scale behaviour. The Villager organisation, Horse destination routing, Enderman restaurant and Pillager multi-block construction are the largest remaining role-specific systems.

The exact Java 26.2 live entity-registry audit, dedicated-server test and two-client multiplayer play test also remain pending.

## Coverage

When an entity enters a level on the logical server, the event handler checks whether it is a Minecraft Mob. The registry resolves curated job definitions or generates a stable species-specific fallback. Non-mob entities are excluded.

This gives mobs an occupation identity. It does not mean every species has custom work AI; custom behaviour is currently limited to the ten listed above.
