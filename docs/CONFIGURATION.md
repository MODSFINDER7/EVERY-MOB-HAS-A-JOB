# Server configuration

The mod registers a server-side config file named every_mob_has_a_job-server.toml.

| Option | Default | Range | Purpose |
|---|---:|---:|---|
| enableJobAssignments | true | true/false | Assign jobs to joining mobs |
| enableJobInspection | true | true/false | Inspect jobs by right-clicking a mob while holding a book |
| enableCustomAI | true | true/false | Enable occupation AI goals |
| enableCowGrassCutter | true | true/false | Enable cow grass cutting |
| enablePigButcherShop | true | true/false | Enable emerald-for-meat pig trades |
| enableCamelFirefighter | true | true/false | Enable camel fire response |
| enableHorseTaxi | true | true/false | Enable paid boarding on taxi horses |
| enableIronGolemPolice | true | true/false | Enable golem police-response state and alert particles |
| enableEndermanPizza | true | true/false | Enable Enderman pizza delivery |
| endermanPizzaEmeraldPrice | 1 | 1–64 | Fare for one Enderman pizza delivery |
| horseTaxiFareEmeralds | 1 | 1–64 | Fare for boarding a taxi horse |
| pigShopEmeraldPrice | 1 | 1–64 | Emeralds required for one pig-shop purchase |
| pigShopPorkchopsPerTrade | 2 | 1–64 | Cooked porkchops given per pig-shop purchase |
| cowGrazeWorkTicks | 40 | 20–200 | Time spent grazing before the block changes |
| cowGrazeCooldownSeconds | 18 | 1–600 | Delay between successful actions per cow |
| camelFireCooldownSeconds | 8 | 1–120 | Delay before a camel searches for another fire |

The server configuration is authoritative. The cow role respects the vanilla mob_griefing gamerule. Camel firefighters only remove ordinary fire and soul-fire blocks, not lava or other heat sources. Creative players do not pay emeralds at either shop. If the pig-shop buyer's inventory is full, purchased meat is dropped nearby.

Use a regular book on any mob to inspect its assigned job, personality and current activity. The book is not consumed.

Enderman pizza delivery is an initial gameplay implementation: hold emeralds and right-click an Enderman. It validates a safe nearby position, teleports the Enderman beside the player and drops a pumpkin pie named Nether Pizza. The fare is configurable. A dedicated pizza item, restaurant block, queue and multi-stop courier route are not implemented yet.

The horse taxi is an initial paid-boarding service: the horse must already be tamed and saddled, and the rider steers it after paying. Named destination booking and autonomous route navigation are not implemented yet. Iron Golem police activity reflects vanilla target changes and does not replace its existing combat AI.
