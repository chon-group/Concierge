package group.chon.agent.concierge.core;

import group.chon.agent.concierge.core.Forward;
import group.chon.agent.hermes.core.capabilities.bioinspiredProtocols.BioinspiredData;
import group.chon.agent.hermes.core.capabilities.bioinspiredProtocols.dto.AgentTransferRequestMessageDto;
import group.chon.agent.hermes.core.capabilities.manageConnections.middlewares.CommunicationMiddleware;
import group.chon.agent.hermes.core.utils.MessageUtils;
import jason.asSemantics.Message;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class InputFilter extends group.chon.agent.hermes.core.InComingMessages {


    public InputFilter(BioinspiredData bioinspiredData, HashMap<String, CommunicationMiddleware> communicationMiddlewares) {
        super(bioinspiredData, communicationMiddlewares);
    }

    @Override
    public void classifyMessages(List<String> receivedEncryptedMessages,
                                 CommunicationMiddleware communicationMiddleware,
                                 String connectionIdentifier) {

        Forward forward = new Forward();
        Iterator<String> iterator = receivedEncryptedMessages.iterator();
        while (iterator.hasNext()) {
            String receivedEncryptedMessage = iterator.next();
            /* Obtain the messages */
            Object decryptedObjectMessage = communicationMiddleware.getCommunicationSecurity()
                    .decrypt(receivedEncryptedMessage);

            /* Is a KQML message? */
            Message decryptedMessage = MessageUtils.getJasonMessage(decryptedObjectMessage);
            if (decryptedMessage != null) {
                if (!forward.isAcceptable("input",             /* way          */
                        "communication",                            /* scope        */
                        decryptedMessage.getSender(),               /* from         */
                        decryptedMessage.getIlForce())) {           /* kqml force   */
                    iterator.remove();                          /* message REJECTED */
                    continue;
                }
            }

            /* Is a migration request? */
            AgentTransferRequestMessageDto agentTransferRequestMessageDto = MessageUtils
                    .getAgentTransferRequestMessage(decryptedObjectMessage);
            if (agentTransferRequestMessageDto != null) {
                if (!forward.isAcceptable("input",                                          /* way               */
                        "migration",                                                             /* scope             */
                        agentTransferRequestMessageDto.getSenderIdentification(),                /* from              */
                        agentTransferRequestMessageDto.getBioinspiredProtocol().toString())) {   /* migration protocol*/
                    iterator.remove();                                                          /* migration REJECTED */
                }
            }
        }

        /* to HermesArch deal with accepted messages */
        super.classifyMessages(receivedEncryptedMessages, communicationMiddleware, connectionIdentifier);
    }


}
