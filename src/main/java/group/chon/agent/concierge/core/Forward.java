package group.chon.agent.concierge.core;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.logging.Logger;

public class Forward {
    private Logger logger;
    private JsonArray ruleList = group.chon.agent.concierge.core.Base.getGuidances();
    private JsonArray policyList = group.chon.agent.concierge.core.Base.getPolicies();

    public Forward(Logger logger) {
        this.logger = logger;
    }

    public boolean isAcceptable(String way, String scope, String address, String type) {

       if(applicableGuidance(way,scope,address,type)){
           logger.fine("ACCEPTED! "+way+" "+scope+" "+address+" "+type);
           return true;
       }else{
           logger.info("REJECTED! "+way+" "+scope+" "+address+" "+type);
           return false;
       }
    }

    private boolean applicableGuidance(String way, String scope, String address, String type) {
        JsonObject mensagemJsonObject = new JsonObject();
        mensagemJsonObject.addProperty("tipo", way);
        mensagemJsonObject.addProperty("protocolo", type);
        mensagemJsonObject.addProperty("abrangencia", scope);
        mensagemJsonObject.addProperty("endereco", address);
        return checkGuidance(mensagemJsonObject);
    }

    public boolean checkGuidance(JsonObject mensagem) {
        boolean resultado = false;
        int contador = 0;
        if (this.ruleList.size() == 0) {
            resultado = this.checkPolicy(mensagem);
        } else {
            for (int i = 0; i < this.ruleList.size(); i++) {
                if (this.ruleList.get(i).getAsJsonObject().get("tipo").getAsString().equals("all")
                        || this.ruleList.get(i).getAsJsonObject().get("tipo").getAsString().equals(mensagem.get("tipo").getAsString())) {
                    if (this.ruleList.get(i).getAsJsonObject().get("endereco").getAsString().equals(mensagem.get("endereco").toString())
                        || matchAddress(this.ruleList.get(i).getAsJsonObject().get("endereco").getAsString(), mensagem.get("endereco").toString())) {
                        if (this.ruleList.get(i).getAsJsonObject().get("protocolo").getAsString().equals(mensagem.get("protocolo").getAsString().toLowerCase())
                                || this.ruleList.get(i).getAsJsonObject().get("protocolo").getAsString().equals("all")
                                || isValidIlForceOrProtocol(this.ruleList.get(i).getAsJsonObject().get("protocolo").getAsString(), mensagem.get("protocolo").getAsString().toLowerCase())){
                            contador += 1;
                            if (this.ruleList.get(i).getAsJsonObject().get("abrangencia").getAsString().equals("all")
                                    && this.ruleList.get(i).getAsJsonObject().get("determinacao").getAsString().equals("accept")) {
                                resultado = true;
                            } else if (this.ruleList.get(i).getAsJsonObject().get("abrangencia").getAsString().equals("all")
                                    && this.ruleList.get(i).getAsJsonObject().get("determinacao").getAsString().equals("drop")) {
                                resultado = false;
                            }
                            if (this.ruleList.get(i).getAsJsonObject().get("abrangencia").getAsString().equals("communication")
                                    && mensagem.get("abrangencia").getAsString().equals("communication")
                                    && this.ruleList.get(i).getAsJsonObject().get("determinacao").getAsString().equals("accept")) {
                                resultado = true;
                            } else if (this.ruleList.get(i).getAsJsonObject().get("abrangencia").getAsString().equals("communication")
                                    && mensagem.get("abrangencia").getAsString().equals("communication")
                                    && this.ruleList.get(i).getAsJsonObject().get("determinacao").getAsString().equals("drop")) {
                                resultado = false;
                            }
                            if (this.ruleList.get(i).getAsJsonObject().get("abrangencia").getAsString().equals("migration")
                                    && mensagem.get("abrangencia").getAsString().equals("migration")
                                    && this.ruleList.get(i).getAsJsonObject().get("determinacao").getAsString().equals("accept")) {
                                resultado = true;
                            } else if (this.ruleList.get(i).getAsJsonObject().get("abrangencia").getAsString().equals("migration")
                                    && mensagem.get("abrangencia").getAsString().equals("migration")
                                    && this.ruleList.get(i).getAsJsonObject().get("determinacao").getAsString().equals("drop")) {
                                resultado = false;
                            }
                        }
                    }
                }
            }
        }
        if (contador == 0) {
            resultado = this.checkPolicy(mensagem);
        }
        return resultado;
    }

    private boolean isValidIlForceOrProtocol(String protocolo, String protocoloMensagem){
        if (protocolo.equals("all")) {
            switch (protocoloMensagem.toLowerCase()) {
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
        }else if (protocolo.equals("kqml")) {
            switch (protocoloMensagem.toLowerCase()) {
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
        } else if (protocolo.equals("bioinsp")) {
            switch (protocoloMensagem.toLowerCase()) {
                case "mutualism":
                case "inquilinism":
                case "predation":
                    return true;
            }
        }
        return false;
    }


    public boolean checkPolicy(JsonObject mensagem) {
        boolean resultado = false;
        int contador = 0;
        if (this.policyList.size() == 0) {
            resultado = true;
        } else {
            for (int i = 0; i < this.policyList.size(); i++) {
                if (this.policyList.get(i).getAsJsonObject().get("tipo").getAsString().equals("all")
                        || this.policyList.get(i).getAsJsonObject().get("tipo").getAsString().equals(mensagem.get("tipo").getAsString())) {
                    if (this.policyList.get(i).getAsJsonObject().get("protocolo").getAsString().equals(mensagem.get("protocolo").getAsString().toLowerCase())
                            || this.policyList.get(i).getAsJsonObject().get("protocolo").getAsString().equals("all")
                            || isValidIlForceOrProtocol(this.policyList.get(i).getAsJsonObject().get("protocolo").getAsString(), mensagem.get("protocolo").getAsString().toLowerCase())) {
                        if (this.policyList.get(i).getAsJsonObject().get("abrangencia").getAsString().equals("all")
                                && this.policyList.get(i).getAsJsonObject().get("determinacao").getAsString().equals("accept")
                                && this.policyList.get(i).getAsJsonObject().get("abrangencia").getAsString().equals("all")) {
                            contador += 1;
                            resultado = true;
                        } else if (this.policyList.get(i).getAsJsonObject().get("abrangencia").getAsString().equals("all")
                                && this.policyList.get(i).getAsJsonObject().get("determinacao").getAsString().equals("drop")) {
                            contador += 1;
                            resultado = false;
                        }
                        if (this.policyList.get(i).getAsJsonObject().get("abrangencia").getAsString().equals("communication")
                                && mensagem.get("abrangencia").getAsString().equals("communication")
                                && this.policyList.get(i).getAsJsonObject().get("determinacao").getAsString().equals("accept")) {
                            contador += 1;
                            resultado = true;
                        } else if (this.policyList.get(i).getAsJsonObject().get("abrangencia").getAsString().equals("communication")
                                && mensagem.get("abrangencia").getAsString().equals("communication")
                                && this.policyList.get(i).getAsJsonObject().get("determinacao").getAsString().equals("drop")) {
                            contador += 1;
                            resultado = false;
                        }
                        if (this.policyList.get(i).getAsJsonObject().get("abrangencia").getAsString().equals("migration")
                                && mensagem.get("abrangencia").getAsString().equals("migration")
                                && this.policyList.get(i).getAsJsonObject().get("determinacao").getAsString().equals("accept")) {
                            contador += 1;
                            resultado = true;
                        } else if (this.policyList.get(i).getAsJsonObject().get("abrangencia").getAsString().equals("migration")
                                && mensagem.get("abrangencia").getAsString().equals("migration")
                                && this.policyList.get(i).getAsJsonObject().get("determinacao").getAsString().equals("drop")) {
                            contador += 1;
                            resultado = false;
                        }
                    }
                }
            }
        }
        if(contador == 0){
            resultado = true;
        }
        return resultado;
    }

    private boolean matchAddress(String padrao, String endereco) {
        String regex = padrao.replace(".", "\\.")
                .replace("-", "\\-")
                .replace("*", ".*");
        return endereco.matches(regex);
    }

}
