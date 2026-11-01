package com.modsfinder.everymobhasajob.job;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class MobJobRegistry {
    private static final Map<String, MobJob> JOBS = new ConcurrentHashMap<>();

    static {
        // The nine assignments from the reference video are locked.
        add("minecraft:cow", "grass_cutter", "Grass Cutter", "Calm, patient and hardworking",
                "Grazes grass in patches, pauses to work, then wanders to the next patch",
                "graze, chew, inspect_grass, walk", false);
        add("minecraft:pig", "butcher_shopkeeper", "Butcher Shopkeeper", "Cheerful and business-minded",
                "Runs a meat stall, displays stock and sells meat through a player-facing trade interaction",
                "serve, point, inspect_stock, close_shop", false);
        add("minecraft:iron_golem", "police_officer", "Police Officer", "Strict but protective",
                "Patrols its village and investigates configured theft or intrusion reports",
                "patrol, alert, investigate, escort", false);
        add("minecraft:enderman", "pizza_chef", "Pizza Chef and Courier", "Quiet, punctual and easily annoyed by staring",
                "Prepares pizza at a restaurant workstation and teleports to deliver confirmed orders",
                "prepare, plate, carry_box, teleport, deliver", false);
        add("minecraft:camel", "firefighter", "Firefighter", "Brave, patient and a little dramatic",
                "Responds to nearby fire incidents and performs a firefighting routine",
                "dispatch, trot, spray, celebrate", false);
        add("minecraft:villager", "business_network", "Secret Business Organisation", "Secretive, organised and status-conscious",
                "Runs a hidden business network with restricted offices, meetings, guards and private facilities",
                "paperwork, meeting, guard_patrol, whisper", false);
        add("minecraft:pillager", "construction_worker", "Construction Worker", "Competitive, noisy and accident-prone",
                "Works at marked construction sites, carries materials and performs staged building tasks",
                "carry, measure, hammer, inspect", false);
        add("minecraft:creeper", "therapy_patient", "Therapy Patient", "Anxious but trying to stay calm",
                "Visits a therapy area and performs calming routines; its vanilla fuse and explosion rules remain unchanged",
                "sit, breathe, fidget, calm", false);
        add("minecraft:horse", "taxi_driver", "Taxi Driver", "Professional and proud of its ride rating",
                "Offers player-confirmed paid rides to selected destinations",
                "wait, offer_ride, trot, arrive", false);

        // Bosses keep their original encounter role and combat identity.
        add("minecraft:ender_dragon", "end_realm_custodian", "End Realm Custodian",
                "Ancient, territorial and alert", "Maintains its role as the End's central guardian; vanilla phases remain untouched",
                "fly, circle, perch, roar", true);
        add("minecraft:wither", "catastrophe_class_entity", "Catastrophe-Class Entity",
                "Unstable and destructive by nature", "Retains its vanilla boss encounter; no civilian routine is applied",
                "hover, charge, phase_transition", true);
        add("minecraft:warden", "deep_dark_sentinel", "Deep Dark Sentinel",
                "Silent, sensory and territorial", "Guards the Deep Dark through its existing vibration and scent systems",
                "listen, investigate, emerge, retreat", true);
        add("minecraft:elder_guardian", "monument_commander", "Ocean Monument Commander",
                "Ancient and watchful", "Oversees its monument while preserving vanilla guardian behaviour",
                "hover, observe, pulse", true);

        // Individual occupations for other familiar vanilla mobs.
        add("minecraft:allay", "lost_property_clerk", "Lost Property Clerk", "Helpful and easily distracted",
                "Collects matching dropped items and returns them to its assigned storage point", "hover, collect, deliver");
        add("minecraft:armadillo", "shell_inspector", "Shell Inspector", "Cautious and meticulous",
                "Inspects dry and sheltered spots before returning to its normal routine", "inspect, curl, uncurl");
        add("minecraft:axolotl", "pond_rescue_medic", "Pond Rescue Medic", "Curious and helpful",
                "Patrols aquatic work areas and checks on nearby aquatic creatures", "swim, inspect, rest");
        add("minecraft:bat", "cave_sound_engineer", "Cave Sound Engineer", "Nocturnal and easily startled",
                "Visits cave sound points and performs short listening routines", "hang, listen, flutter");
        add("minecraft:bee", "pollination_manager", "Pollination Manager", "Busy and schedule-driven",
                "Visits flowers and returns to its hive as part of a pollination route", "buzz, pollinate, return");
        add("minecraft:blaze", "furnace_operator", "Furnace Operator", "Hot-headed and efficient",
                "Oversees a Nether foundry area without changing its vanilla combat logic", "hover, inspect, idle");
        add("minecraft:bogged", "swamp_greenhouse_keeper", "Swamp Greenhouse Keeper", "Quiet and patient",
                "Maintains marked swamp planters and checks nearby crops", "tend, inspect, rest");
        add("minecraft:breeze", "wind_tunnel_technician", "Wind Tunnel Technician", "Restless and playful",
                "Tests airflow around trial structures while retaining its normal trial behaviour", "bounce, gust, observe");
        add("minecraft:cat", "village_pest_controller", "Village Pest Controller", "Independent and observant",
                "Patrols village edges and rests at assigned safe spots", "stalk, patrol, sit, nap");
        add("minecraft:cave_spider", "cavern_textile_weaver", "Cavern Textile Weaver", "Patient and industrious",
                "Maintains decorative web-work zones in marked caves", "climb, weave, inspect");
        add("minecraft:chicken", "egg_courier", "Egg Courier", "Energetic and easily distracted",
                "Moves eggs from its roost area to a nearby collection point", "peck, cluck, deliver");
        add("minecraft:cod", "market_quality_inspector", "Fish Market Quality Inspector", "Busy and curious",
                "Schools around marked aquatic market displays", "swim, inspect, circle");
        add("minecraft:creaking", "forest_night_watch", "Forest Night Watch", "Still, eerie and observant",
                "Keeps to its own creaking-heart rules and acts as a forest sentinel", "freeze, turn, observe");
        add("minecraft:dolphin", "ocean_lifeguard", "Ocean Lifeguard", "Playful and attentive",
                "Circles safe swimming areas and reacts to nearby swimmers", "swim, leap, escort");
        add("minecraft:donkey", "cargo_dispatcher", "Cargo Dispatcher", "Steady and dependable",
                "Waits at caravan points and carries assigned cargo", "wait, load, travel");
        add("minecraft:drowned", "harbour_salvage_clerk", "Harbour Salvage Clerk", "Slow and forgetful",
                "Searches marked underwater salvage zones without changing vanilla hostility", "drift, search, inspect");
        add("minecraft:endermite", "portal_tunnel_surveyor", "Portal Tunnel Surveyor", "Tiny and impatient",
                "Scurries around marked portal-adjacent survey points", "scurry, inspect, hide");
        add("minecraft:evoker", "illusion_stage_director", "Illusion Stage Director", "Formal and theatrical",
                "Keeps its existing encounter behaviour and performs a dramatic idle routine when not engaged", "gesture, bow, observe");
        add("minecraft:fox", "night_parcel_courier", "Night Parcel Courier", "Clever and opportunistic",
                "Carries small deliveries between marked forest drop points", "sneak, carry, deliver, curl_up");
        add("minecraft:frog", "insect_population_guide", "Insect Population Guide", "Laid-back and focused",
                "Patrols wetland edges and keeps to its natural habitat", "hop, sit, catch");
        add("minecraft:ghast", "nether_chorus_singer", "Nether Chorus Singer", "Loud, sensitive and dramatic",
                "Performs distant chorus calls in open Nether spaces", "float, sing, drift");
        add("minecraft:glow_squid", "underwater_sign_artist", "Underwater Sign Artist", "Calm and luminous",
                "Visits marked underwater display points", "swim, glow, drift");
        add("minecraft:goat", "mountain_bell_inspector", "Mountain Bell Inspector", "Independent and energetic",
                "Checks mountain paths and bell posts", "climb, bleat, inspect");
        add("minecraft:guardian", "monument_patrol", "Monument Patrol Officer", "Alert and territorial",
                "Circles its monument patrol area", "swim, scan, circle");
        add("minecraft:happy_ghast", "sky_ferry_pilot", "Sky Ferry Pilot", "Gentle and dependable",
                "Carries willing passengers along a player-directed sky route", "float, wait, ferry, land");
        add("minecraft:hoglin", "crimson_forest_ranger", "Crimson Forest Ranger", "Stubborn and territorial",
                "Roams its home territory and keeps the forest route clear", "sniff, roam, rest");
        add("minecraft:husk", "desert_waystation_host", "Desert Waystation Host", "Dry, patient and persistent",
                "Stays around marked desert waystations", "wander, wait, inspect");
        add("minecraft:llama", "caravan_logistics_manager", "Caravan Logistics Manager", "Proud and organised",
                "Keeps caravan groups together and waits at rest stops", "follow, wait, spit_idle");
        add("minecraft:magma_cube", "lava_temperature_tester", "Lava Temperature Tester", "Bouncy and enthusiastic",
                "Bounces between marked Nether work zones", "hop, bounce, rest");
        add("minecraft:mooshroom", "mushroom_farm_manager", "Mushroom Farm Manager", "Gentle and methodical",
                "Tends mushroom-friendly areas and moves between marked patches", "graze, inspect, wander");
        add("minecraft:mule", "pack_route_coordinator", "Pack Route Coordinator", "Reliable and quiet",
                "Carries supplies between caravan stops", "wait, load, travel");
        add("minecraft:ocelot", "jungle_trail_guide", "Jungle Trail Guide", "Cautious and alert",
                "Moves between jungle trail markers", "stalk, look, walk");
        add("minecraft:panda", "bamboo_food_critic", "Bamboo Food Critic", "Relaxed and highly opinionated",
                "Samples bamboo patches and takes long breaks", "sit, munch, roll, nap");
        add("minecraft:parrot", "jungle_radio_host", "Jungle Radio Host", "Talkative and observant",
                "Repeats nearby ambient sounds and visits jungle perches", "perch, mimic, flutter");
        add("minecraft:phantom", "sleep_schedule_auditor", "Sleep Schedule Auditor", "Dramatic and restless",
                "Circles the night sky and retreats during daytime", "circle, swoop, retreat");
        add("minecraft:piglin", "gold_appraiser", "Gold Appraiser", "Business-minded and suspicious",
                "Inspects gold-related trades and returns to its patrol route", "inspect, barter, patrol");
        add("minecraft:piglin_brute", "vault_gatekeeper", "Vault Gatekeeper", "Serious and watchful",
                "Guards its bastion area while retaining vanilla combat behaviour", "stand_guard, inspect, patrol");
        add("minecraft:polar_bear", "arctic_coast_ranger", "Arctic Coast Ranger", "Protective and quiet",
                "Roams the shoreline and checks nearby ice paths", "walk, sniff, rest");
        add("minecraft:pufferfish", "aquarium_warning_officer", "Aquarium Warning Officer", "Nervous but professional",
                "Signals when other creatures enter its personal space", "swim, puff, deflate");
        add("minecraft:rabbit", "carrot_market_assistant", "Carrot Market Assistant", "Fast and easily distracted",
                "Moves between marked crop patches", "hop, nibble, look");
        add("minecraft:ravager", "festival_float_carrier", "Festival Float Carrier", "Huge, grumpy and surprisingly patient",
                "Moves heavy decorative festival props when calm; vanilla combat remains intact", "stand, stomp, idle");
        add("minecraft:salmon", "river_tour_guide", "River Tour Guide", "Persistent and social",
                "Leads short schools along river routes", "swim, circle, school");
        add("minecraft:sheep", "wool_fashion_designer", "Wool Fashion Designer", "Proud and expressive",
                "Visits marked grooming and display spots", "graze, pose, inspect");
        add("minecraft:shulker", "storage_facility_manager", "Storage Facility Manager", "Organised and stationary",
                "Keeps to assigned storage entrances and acts as a living storage sentinel", "open, close, observe");
        add("minecraft:silverfish", "underground_cable_inspector", "Underground Cable Inspector", "Small and diligent",
                "Patrols marked underground maintenance tunnels", "scurry, inspect, hide");
        add("minecraft:skeleton", "museum_exhibit_curator", "Museum Exhibit Curator", "Quiet and precise",
                "Maintains a museum display area while retaining vanilla combat behaviour", "stand, point, inspect");
        add("minecraft:skeleton_horse", "ghost_carriage_driver", "Ghost Carriage Driver", "Polite and mysterious",
                "Waits at marked carriage stands and offers atmospheric rides", "wait, trot, arrive");
        add("minecraft:slime", "bounce_castle_tester", "Bounce Castle Tester", "Excited and springy",
                "Tests marked soft-play zones", "compress, bounce, wobble");
        add("minecraft:sniffer", "ancient_seed_curator", "Ancient Seed Curator", "Patient and curious",
                "Sniffs out ancient seeds and visits marked garden plots", "sniff, dig, rest");
        add("minecraft:snow_golem", "winter_festival_decorator", "Winter Festival Decorator", "Cheerful and seasonal",
                "Visits marked festival decoration points", "walk, wave, decorate");
        add("minecraft:spider", "cave_rope_weaver", "Cave Rope Weaver", "Patient and quietly creative",
                "Builds decorative web patterns in marked locations", "climb, weave, rest");
        add("minecraft:stray", "frozen_delivery_dispatcher", "Frozen Delivery Dispatcher", "Reserved and punctual",
                "Keeps to cold-biome delivery routes", "walk, wait, inspect");
        add("minecraft:strider", "lava_ferry_operator", "Lava Ferry Operator", "Unbothered and dependable",
                "Waits at safe lava-crossing docks and ferries willing passengers", "wait, step, ferry");
        add("minecraft:sulfur_cube", "bouncy_packaging_tester", "Bouncy Packaging Tester", "Springy and curious",
                "Tests bounce-friendly work areas", "compress, bounce, wobble");
        add("minecraft:tadpole", "swimming_school_trainee", "Swimming School Trainee", "Small and eager",
                "Practises short swimming loops near its school", "swim, turn, rest");
        add("minecraft:trader_llama", "caravan_route_supervisor", "Caravan Route Supervisor", "Alert and dependable",
                "Keeps travelling market caravans together", "follow, wait, inspect");
        add("minecraft:tropical_fish", "reef_colour_consultant", "Reef Colour Consultant", "Bright and social",
                "Schools around marked coral display areas", "swim, school, turn");
        add("minecraft:turtle", "beach_conservation_ranger", "Beach Conservation Ranger", "Patient and protective",
                "Returns to its home beach and checks nesting areas", "crawl, swim, nest, rest");
        add("minecraft:vex", "tiny_dispatch_assistant", "Tiny Dispatch Assistant", "Hyperactive and loyal",
                "Performs short messenger loops while keeping its summoned-entity identity", "hover, dart, return");
        add("minecraft:vindicator", "warehouse_inventory_manager", "Warehouse Inventory Manager", "Serious and organised",
                "Checks marked storage rooms while retaining vanilla combat behaviour", "inspect, patrol, report");
        add("minecraft:wandering_trader", "travelling_market_representative", "Travelling Market Representative",
                "Friendly, mysterious and itinerant", "Sets up a travelling market and offers its existing trades",
                "arrive, display_goods, trade, depart");
        add("minecraft:witch", "weather_station_forecaster", "Weather Station Forecaster", "Independent and eccentric",
                "Checks local weather conditions at marked stations", "observe, stir, report");
        add("minecraft:wither_skeleton", "nether_hall_curator", "Nether Hall Curator", "Formal and stern",
                "Patrols its fortress halls while retaining vanilla combat behaviour", "patrol, inspect, stand");
        add("minecraft:wolf", "forest_search_guide", "Forest Search Guide", "Loyal and attentive",
                "Follows its trusted player and searches marked forest paths", "track, follow, sit");
        add("minecraft:zoglin", "nether_wilderness_ranger", "Nether Wilderness Ranger", "Wild and territorial",
                "Roams its home area; vanilla hostility remains unchanged", "sniff, roam, rest");
        add("minecraft:zombie", "night_shift_postal_worker", "Night Shift Postal Worker", "Persistent and slow-moving",
                "Follows a night route between marked mail points while retaining vanilla hostility", "stagger, carry, wander");
        add("minecraft:zombie_horse", "undead_carriage_driver", "Undead Carriage Driver", "Quiet and dependable",
                "Waits at carriage stands and offers atmospheric rides", "wait, walk, arrive");
        add("minecraft:zombie_villager", "town_renovation_planner", "Town Renovation Planner", "Absent-minded and hopeful",
                "Visits marked construction plans while preserving its vanilla conversion mechanic", "inspect, point, wander");
        add("minecraft:zombified_piglin", "nether_museum_guard", "Nether Museum Guard", "Protective and easily disturbed",
                "Patrols a marked gold-history exhibit while retaining vanilla anger rules", "patrol, inspect, rest");
    }

    private MobJobRegistry() {}

    private static void add(String entityId, String id, String title, String personality,
                            String activity, String animations) {
        add(entityId, id, title, personality, activity, animations, false);
    }

    private static void add(String entityId, String id, String title, String personality,
                            String activity, String animations, boolean bossRole) {
        JOBS.put(entityId, new MobJob(id, title, personality, activity, animations, bossRole));
    }

    public static Optional<MobJob> find(String entityId) {
        return Optional.ofNullable(JOBS.get(entityId));
    }

    public static MobJob resolve(String entityId) {
        if (entityId == null || entityId.isBlank()) {
            throw new IllegalArgumentException("entityId must not be blank");
        }
        return JOBS.computeIfAbsent(entityId, MobJobRegistry::createGeneratedJob);
    }

    private static MobJob createGeneratedJob(String entityId) {
        int separator = entityId.indexOf(':');
        String path = separator >= 0 ? entityId.substring(separator + 1) : entityId;
        String displayName = titleCase(path.replace('_', ' '));
        String idPath = path.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9_]", "_");
        return new MobJob(
                "resident_" + idPath,
                displayName + " Specialist",
                "Has a species-specific personality while keeping its vanilla temperament",
                "Follows a simple local occupation routine appropriate for " + displayName,
                "idle, look, walk, interact",
                false
        );
    }

    private static String titleCase(String value) {
        StringBuilder result = new StringBuilder();
        boolean capitalize = true;
        for (char character : value.toCharArray()) {
            if (Character.isWhitespace(character) || character == '-') {
                result.append(character);
                capitalize = true;
            } else {
                result.append(capitalize ? Character.toUpperCase(character) : character);
                capitalize = false;
            }
        }
        return result.toString();
    }

    public static Map<String, MobJob> entries() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(JOBS));
    }
}
