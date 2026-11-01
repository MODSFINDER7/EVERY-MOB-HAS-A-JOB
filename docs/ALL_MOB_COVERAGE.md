# All-mob coverage — Java 26.2

The implementation does not rely on a hand-maintained list for coverage. When an entity enters a level on the logical server, the event handler checks whether it is a Minecraft `Mob`. Its registered entity-type ID is resolved through `MobJobRegistry`.

- Curated mob types receive their named occupation.
- Every unlisted mob receives a stable occupation generated from its registry path.
- New mob types from future vanilla updates also receive a generated occupation.
- Non-mob entities are not assigned jobs.

This design covers modded mobs too, unless the server owner disables the feature or adds another definition. It does not claim every mob has a fully implemented custom AI: only the cow grass-cutter behaviour is currently active.

## Boss handling

The Ender Dragon, Wither, Warden and Elder Guardian have special boss/sentinel entries. They keep their existing vanilla encounter logic; the mod does not replace their combat AI with civilian routines.

## Live roster audit

A final roster export still needs to be generated from the actual 26.2 runtime registry. This avoids publishing an incomplete list copied from an external source.
