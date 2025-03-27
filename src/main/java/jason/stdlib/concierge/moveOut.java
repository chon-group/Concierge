package jason.stdlib.concierge;

import group.chon.agent.concierge.core.Forward;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Term;

import java.util.logging.Logger;

public class moveOut extends group.chon.agent.hermes.jasonStdLib.moveOut{
    @Override
    public Object execute(final TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        Logger logger=Logger.getLogger(this.getClass().getName());
        try{
            Forward forward = new Forward();
            if (forward.isAcceptable("output",
                    "migration",
                    args[0].toString().replaceAll("\"",""),
                    args[1].toString().replaceAll("\"","").toUpperCase())) {
                logger.info("LIBERADA");
                return super.execute(ts, un, args);
            }else{
                logger.info("BLOQUEADA");
                return true;
            }
        }catch (Exception ex){
            logger.info(ex.getMessage());
            return super.execute(ts, un, args);
        }

    }
}
