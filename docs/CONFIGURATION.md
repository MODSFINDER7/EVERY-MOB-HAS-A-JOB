# Server configuration

The mod registers a server-side config file named `every_mob_has_a_job-server.toml`.

| Option | Default | Range | Purpose |
|---|---:|---:|---|
| `enableJobAssignments` | true | true/false | Assign jobs to joining mobs |
| `enableCustomAI` | true | true/false | Enable occupation AI goals |
| `enableCowGrassCutter` | true | true/false | Enable cow grass cutting |
| `cowGrazeWorkTicks` | 40 | 20–200 | Time spent grazing before the block changes |
| `cowGrazeCooldownSeconds` | 18 | 1–600 | Delay between successful actions per cow |

The server configuration is authoritative. The cow role also respects the vanilla `mob_griefing` gamerule. These switches do not alter vanilla AI when custom AI is disabled.
