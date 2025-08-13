package group.chon.agent.concierge;

import group.chon.agent.concierge.core.Base;
import group.chon.agent.hermes.Hermes;
import group.chon.agent.concierge.core.InputFilter;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import group.chon.agent.hermes.core.capabilities.bioinspiredProtocols.BioinspiredProcessor;
import group.chon.agent.hermes.core.capabilities.socialSkillsWithOutside.SendOutProcessor;
import jason.asSemantics.Message;

public class Concierge extends Hermes {
    private Logger logger = Logger.getLogger(getClass().getName());


    public Concierge(){
        super();
        logger.info("Agent Architecture (version 25.08.13)");
    }

    @Override
    public void checkMail(){
        super.checkMailJasonAgArch();
        Base.setLogger(getTS().getLogger());
        InputFilter inMessages = new InputFilter(super.getBioinspiredData(), super.getCommunicationMiddlewareHashMap());
        Map<String, List<Message>> allReceivedMessages = inMessages.getMessages();
        SendOutProcessor.processMessages(allReceivedMessages, this);
        BioinspiredProcessor.processMessages(this, inMessages);
    }
}