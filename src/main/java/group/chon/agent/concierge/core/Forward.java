package group.chon.agent.concierge.core;

import java.util.logging.Logger;

public class Forward {

    public boolean isAcceptable(String way, String scope, String address, String type) {
        Logger logger = Logger.getLogger("Concierge");
        logger.info("Way: " + way);
        logger.info("Scope: " + scope);
        logger.info("Origem/Destino: " + address);
        logger.info("Força/Protocol: " + type);
        logger.info("---------------------------");

        if(applicableGuidance(way,scope,address,type)){
            return checkGuidance(way,scope,address,type);
        }else{
            return checkPolicy(way,scope,type);
        }
    }

    private boolean applicableGuidance(String way, String scope, String address, String type) {
        /* TODO applicable guidance verification*/
        return true;
    }

    private boolean checkGuidance(String way, String scope, String address, String type) {
        /* TODO guidance verification*/
        return true;
    }

    private boolean checkPolicy(String way, String scope, String type) {
        /* TODO policy verification*/
        return true;
    }
}
