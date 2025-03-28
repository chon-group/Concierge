package group.chon.agent.concierge.jasonStdLib;

import com.google.gson.JsonObject;
import group.chon.agent.concierge.Concierge;
import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Term;

import java.util.logging.Logger;


/**
 * <p>
 * Internal action: <b><code>.policy</code></b>.
 *
 * <p>
 **/
public class policy extends DefaultInternalAction{
    private Logger logger = Logger.getLogger("Concierge");

    @Override
    public Object execute(final TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        int error = 0;
        if(args.length != 4){
            logger.info("Numero incorreto de argumentos para criar uma nova politica");
            error++;
        }else{
            if(!args[0].toString().equals("input") && !args[0].toString().equals("output") && !args[0].toString().equals("all")){
                logger.info("O valor inserido na politica para tipo está incorreto. all/input/output");
                error++;
            }else{
                if(!args[1].toString().equals("all") && !args[1].toString().equals("communication") && !args[1].toString().equals("migration")){
                    logger.info("O valor inserido na politica para abrangencia está incorreto. all/communication/migration");
                    error++;
                }else{
                    boolean v;
                    v = validarProtocolo(args[2].toString(), args[1].toString());
                    switch (args[1].toString()) {
                        case "all":
                            if (!v) {
                                logger.info("O valor inserido na policy para protocolo esta incorreto. all/kqml/bioinsp/tell/untell/askOne/askAll/achieve/unachieve/mutualism/inquilinism/predation");
                                return false;
                            }
                        case "communication":
                            if (!v) {
                                logger.info("O valor inserido na policy para protocolo de comunicação esta incorreto. kqml/tell/untell/askOne/askAll/achieve/unachieve");
                                return false;
                            }
                        case "migration":
                            if (!v) {
                                logger.info("O valor inserido na policy para protocolo de migração esta incorreto. bioinsp/mutualism/inquilinism/predation");
                                return false;
                            }
                    }
                    if( !args[3].toString().equals("accept") && !args[3].toString().equals("drop")){
                            logger.info("O valor inserido na politica para determinação está incorreto. accept/drop");
                            error++;
                    }else{
                            JsonObject politica = new JsonObject();
                            politica.addProperty("tipo", args[0].toString());
                            politica.addProperty("abrangencia", args[1].toString());
                            politica.addProperty("protocolo", args[2].toString().toLowerCase());
                            politica.addProperty("determinacao", args[3].toString());
                            //ts.getUserAgArch().setFirewallPolicy(politica);
                            group.chon.agent.concierge.core.Base.addPolicy(politica,ts.getLogger());
                        }
                    }
                }
            }

        if(error > 0)
            return false;
    return true;
    }

    private boolean validarProtocolo(String protocolo, String abrangencia){
        if (abrangencia.equals("all")) {
            switch (protocolo.toLowerCase()) {
                case "all":
                case "kqml":
                case "bioinsp":
                case "tell":
                case "untell":
                case "tellhow":
                case "untellhow":
                case "askone":
                case "askall":
                case "askhow":
                case "achieve":
                case "unachieve":
                case "mutualism":
                case "inquilinism":
                case "predation":
                    return true;
            }
        }else if (abrangencia.equals("communication")) {
            switch (protocolo.toLowerCase()) {
                case "all":
                case "kqml":
                case "tell":
                case "untell":
                case "tellhow":
                case "untellhow":
                case "askone":
                case "askall":
                case "askhow":
                case "achieve":
                case "unachieve":
                    return true;
            }
        } else if (abrangencia.equals("migration")) {
            switch (protocolo.toLowerCase()) {
                case "all":
                case "bioinsp":
                case "mutualism":
                case "inquilinism":
                case "predation":
                    return true;
            }
        }
        return false;
    }
}
