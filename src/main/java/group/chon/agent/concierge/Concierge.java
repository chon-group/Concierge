package group.chon.agent.concierge;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import group.chon.agent.hermes.Hermes;
import group.chon.agent.concierge.core.InputFilter;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import group.chon.agent.hermes.core.capabilities.bioinspiredProtocols.BioinspiredProcessor;
import group.chon.agent.hermes.core.capabilities.socialSkillsWithOutside.SendOutProcessor;
import jason.asSemantics.Message;

public class Concierge extends Hermes {
    private Logger logger = Logger.getLogger("Concierge");


    public Concierge(){
        super();
        logger.info("Concierge Agt Architecture");
    }

    @Override
    public void checkMail(){
        super.checkMailJasonAgArch();
        InputFilter inMessages = new InputFilter(super.getBioinspiredData(), super.getCommunicationMiddlewareHashMap());
        Map<String, List<Message>> allReceivedMessages = inMessages.getMessages();
        SendOutProcessor.processMessages(allReceivedMessages, this);
        BioinspiredProcessor.processMessages(this, inMessages);
    }


//    public void setFirewallPolicy(JsonObject p) {
//        try {
//            policyList.add(p);
//            logger.info(policyList.size()+" politica adicionada com sucesso!");
//        }catch(Exception e){
//            logger.info("Deu error para acrescentar na lista de politicas");
//        }
//    }
}