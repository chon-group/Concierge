package group.chon.agent.concierge.core;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.logging.Logger;

public class Base {

    private static Logger logger = Logger.getLogger("Concierge");
    private static JsonArray policies = new JsonArray();
    private static JsonArray guidances = new JsonArray();

    public static void addPolicy(JsonObject policy) {
        policies.add(policy);
        logger.info(policies.size()+" politica adicionada com sucesso!");
    }
    
    public static void addGuidance(JsonObject guidance) {
        guidances.add(guidance);
        logger.info(guidances.size()+" guidance adicionada com sucesso!");
    }

    public static JsonArray getPolicies() {
        return policies;
    }

    public static JsonArray getGuidances() {
        return guidances;
    }
}
