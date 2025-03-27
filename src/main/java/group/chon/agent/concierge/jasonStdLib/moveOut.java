package group.chon.agent.concierge.jasonStdLib;

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
            Forward forward = new Forward(ts.getLogger());
            if (forward.isAcceptable("output",
                    "migration",
                    args[0].toString().replaceAll("\"",""),
                    args[1].toString().replaceAll("\"","").toUpperCase())) {
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
