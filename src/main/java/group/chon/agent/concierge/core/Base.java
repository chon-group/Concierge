package group.chon.agent.concierge.core;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.logging.Logger;

public class Base {

    private static Logger logger = null;
    private static JsonArray policies = new JsonArray();
    private static JsonArray guidances = new JsonArray();

    public static void addPolicy(JsonObject policy, Logger logger) {
        policies.add(policy);
        logger.info("Policy ("+policies.size()+") successfully included!");
    }
    
    public static void addGuidance(JsonObject guidance, Logger logger) {
        guidances.add(guidance);
        logger.info("Guidance ("+guidances.size()+") successfully included!");
    }

    public static JsonArray getPolicies() {
        return policies;
    }

    public static JsonArray getGuidances() {
        return guidances;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        Base.logger = logger;
    }
}
