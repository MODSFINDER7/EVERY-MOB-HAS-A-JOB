package com.modsfinder.everymobhasajob.job;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public final class MobJobRegistry {
    private static final Map<String, MobJob> JOBS = new LinkedHashMap<>();
    private static final MobJob FALLBACK = new MobJob("town_resident", "Town Resident",
            "Quiet, curious and individual", "Follows a simple local routine",
            "idle, look, walk, interact", false);

    static {
        add("minecraft:cow", "grass_cutter", "Grass Cutter", "Calm and hardworking",
                "Grazes in patches, moves between patches and takes breaks", "graze, chew, walk, rest");
        add("minecraft:pig", "butcher_shopkeeper", "Butcher Shopkeeper", "Cheerful and business-minded",
                "Runs a meat stall, displays stock and sells meat through a safe trade interface", "serve, point, inspect_stock, close_shop");
        add("minecraft:iron_golem", "police_officer", "Police Officer", "Strict but protective",
                "Patrols its village and investigates configured theft or intrusion events", "patrol, alert, investigate, escort");
        add("minecraft:enderman", "pizza_chef", "Pizza Chef and Courier", "Quiet and punctual",
                "Prepares pizza at a workstation and teleports to deliver it", "prepare, plate, teleport, deliver");
        add("minecraft:camel", "firefighter", "Firefighter", "Brave and patient",
                "Responds to configured fire incidents and performs a rescue routine", "dispatch, trot, spray, celebrate");
        add("minecraft:villager", "business_network", "Secret Business Network", "Secretive and organised",
                "Uses restricted offices, scheduled meetings, guards and hidden facilities", "meeting, paperwork, guard, whisper");
        add("minecraft:pillager", "construction_worker", "Construction Worker", "Competitive and energetic",
                "Works at marked construction sites and performs staged building tasks", "carry, measure, hammer, inspect");
        add("minecraft:creeper", "therapy_patient", "Therapy Patient", "Anxious but learning to stay calm",
                "Visits a calm therapy area and performs breathing routines; vanilla fuse behaviour is preserved", "sit, breathe, fidget, calm");
        add("minecraft:horse", "taxi_driver", "Taxi Driver", "Professional and proud",
                "Offers player-confirmed paid rides to selected destinations", "wait, offer_ride, trot, arrive");
    }

    private MobJobRegistry() {}

    private static void add(String entity, String id, String title, String personality, String activity, String animations) {
        JOBS.put(entity, new MobJob(id, title, personality, activity, animations, false));
    }

    public static Optional<MobJob> find(String entityId) {
        return Optional.ofNullable(JOBS.get(entityId));
    }

    public static MobJob resolve(String entityId) {
        return JOBS.getOrDefault(entityId, FALLBACK);
    }

    public static Map<String, MobJob> entries() {
        return Collections.unmodifiableMap(JOBS);
    }

    public static MobJob fallback() {
        return FALLBACK;
    }
}
