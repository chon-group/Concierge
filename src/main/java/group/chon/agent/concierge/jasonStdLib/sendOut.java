package group.chon.agent.concierge.jasonStdLib;

import group.chon.agent.concierge.core.Forward;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Term;
import java.util.logging.Logger;

public class sendOut extends group.chon.agent.hermes.jasonStdLib.sendOut{
    Logger logger=Logger.getLogger(sendOut.class.getName());
    @Override
    public Object execute(final TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        try{
            Forward forward = new Forward(ts.getLogger());
            if (forward.isAcceptable("output",
                    "communication",
                    args[0].toString().replaceAll("\"",""),
                    args[1].toString().replaceAll("\"","").toLowerCase())) {
                return super.execute(ts, un, args);
            }else{
                return true;
            }
        }catch (Exception ex){
            logger.info(ex.getMessage());
            return super.execute(ts, un, args);
        }
    }
}
