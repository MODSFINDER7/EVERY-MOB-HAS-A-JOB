# Server configuration

The mod registers a server-side config file named `every_mob_has_a_job-server.toml`.

| Option | Default | Range | Purpose |
|---|---:|---:|---|
| `enableJobAssignments` | true | true/false | Assign jobs to joining mobs |
| `enableCustomAI` | true | true/false | Enable occupation AI goals |
| `enableCowGrassCutter` | true | true/false | Enable cow grass cutting |
| `enablePigButcherShop` | true | true/false | Enable emerald-for-meat pig trades |
| `enableCamelFirefighter` | true | true/false | Enable camel fire response |
| `pigShopEmeraldPrice` | 1 | 1–64 | Emeralds required for one purchase |
| `pigShopPorkchopsPerTrade` | 2 | 1–64 | Cooked porkchops given per purchase |
| `cowGrazeWorkTicks` | 40 | 20–200 | Time spent grazing before the block changes |
| `cowGrazeCooldownSeconds` | 18 | 1–600 | Delay between successful actions per cow |
| `camelFireCooldownSeconds` | 8 | 1–120 | Delay before a camel searches for another fire |

The server configuration is authoritative. The cow role respects the vanilla `mob_griefing` gamerule. Camel firefighters only remove ordinary fire and soul-fire blocks, not lava or other heat sources. Creative players do not pay emeralds at the pig shop. If the player's inventory is full, purchased meat is dropped nearby.
