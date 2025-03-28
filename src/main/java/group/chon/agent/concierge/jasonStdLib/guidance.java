package group.chon.agent.concierge.jasonStdLib;

import com.google.gson.JsonObject;
import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Term;

import java.util.logging.Logger;

/**
 * <p>
 * Internal action: <b><code>.guidance</code></b>.
 *
 * <p>
 **/
public class guidance extends DefaultInternalAction{

    private Logger logger = Logger.getLogger("Concierge");

    @Override
    public Object execute(final TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        int error = 0;
        if (args.length != 5) {
            logger.info("Numero incorreto de argumentos para criar uma nova rule");
            error++;
        } else {
            if (!args[0].toString().equals("input") && !args[0].toString().equals("output") && !args[0].toString().equals("all")) {
                logger.info("O valor inserido na rule para tipo está incorreto. all/input/output");
                error++;
            } else {
                if (!args[1].toString().equals("all") && !args[1].toString().equals("communication") && !args[1].toString().equals("migration")) {
                    logger.info("O valor inserido na rule para abrangencia está incorreto. all/communication/migration");
                    error++;
                } else {
                    if (args[2].toString().isEmpty()) {
                        logger.info("O valor inserido na rule para endereco está incorreto.");
                        error++;
                    } else {
                        boolean v;
                        v = validarProtocolo(args[3].toString(), args[1].toString());
                        switch (args[1].toString()) {
                            case "all":
                                if (!v) {
                                    logger.info("O valor inserido na rule para protocolo esta incorreto. all/kqml/bioinsp/tell/untell/askOne/askAll/achieve/unachieve/mutualism/inquilinism/predation");
                                    return false;
                                }
                            case "communication":
                                if (!v) {
                                    logger.info("O valor inserido na rule para protocolo de comunicação esta incorreto. kqml/tell/untell/askOne/askAll/achieve/unachieve");
                                    return false;
                                }
                            case "migration":
                                if (!v) {
                                    logger.info("O valor inserido na rule para protocolo de migração esta incorreto. bioinsp/mutualism/inquilinism/predation");
                                    return false;
                                }
                        }
                        if (!args[4].toString().equals("accept") && !args[4].toString().equals("drop")) {
                            logger.info("O valor inserido na rule para determinação está incorreto. accept/drop");
                            error++;
                        } else {
                            JsonObject rule = new JsonObject();
                            rule.addProperty("tipo", args[0].toString());
                            rule.addProperty("abrangencia", args[1].toString());
                            rule.addProperty("endereco", args[2].toString());
                            rule.addProperty("protocolo", args[3].toString().toLowerCase());
                            rule.addProperty("determinacao", args[4].toString());
                            //ts.getUserAgArch().setFirewallRule(rule);
                            group.chon.agent.concierge.core.Base.addGuidance(rule,ts.getLogger());
                        }
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
                case "all":
                    return true;
            }
        }else if (abrangencia.equals("communication")) {
            switch (protocolo.toLowerCase()) {
                case "kqml":
                case "tell":
                case "untell":
                case "tellhow":
                case "untellhow":
                case "askone":
                case "askall":
                case "achieve":
                case "unachieve":
                case "all":
                    return true;
            }
        } else if (abrangencia.equals("migration")) {
            switch (protocolo.toLowerCase()) {
                case "bioinsp":
                case "mutualism":
                case "inquilinism":
                case "predation":
                case "all":
                    return true;
            }
        }
        return false;
    }
}
