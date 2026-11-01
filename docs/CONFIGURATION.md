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
| enablePillagerConstruction | true | true/false | Enable Pillager block placement |
| enableCreeperTherapy | true | true/false | Enable the peaceful Creeper therapy interaction |
| enableVillagerBusiness | true | true/false | Enable paper-based Villager business invitations |
| endermanPizzaEmeraldPrice | 1 | 1–64 | Fare for one Enderman pizza delivery |
| horseTaxiFareEmeralds | 1 | 1–64 | Fare for boarding a taxi horse |
| pigShopEmeraldPrice | 1 | 1–64 | Emeralds required for one pig-shop purchase |
| pigShopPorkchopsPerTrade | 2 | 1–64 | Cooked porkchops given per pig-shop purchase |
| cowGrazeWorkTicks | 40 | 20–200 | Time spent grazing before the block changes |
| cowGrazeCooldownSeconds | 18 | 1–600 | Delay between successful actions per cow |
| camelFireCooldownSeconds | 8 | 1–120 | Delay before a camel searches for another fire |

Use a regular book on any mob to inspect its job, personality and current activity. To start the Creeper's therapy-session interaction, use a poppy on a Creeper that is not targeting anything, not already swelling and not ignited. The poppy is consumed and heart particles are shown. This is a visual/activity interaction only: the Creeper's vanilla fuse, targeting and explosion mechanics are not modified.

Enderman pizza delivery currently teleports an Enderman to a safe spot near the player and drops a pumpkin pie named Nether Pizza for a configurable emerald fare. The dedicated restaurant, custom pizza item and long-distance courier routes remain planned.

Pillager construction is an early building interaction: hold stone bricks and right-click a Pillager. It places one stone-brick block directly in front of the Pillager if the space is clear, supported and permitted by the server's spawn-protection interaction check. The block is charged only after successful placement.

The horse taxi is an initial paid-boarding service: the horse must already be tamed and saddled, and the rider steers it after paying. Named destination booking and autonomous route navigation remain planned.
