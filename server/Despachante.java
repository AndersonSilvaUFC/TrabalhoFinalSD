package server;

import model.Message;
import org.json.JSONException;
import org.json.JSONObject;

public class Despachante {
    private RHManagerEsqueleto esqueleto;

    public Despachante(){
        esqueleto = new RHManagerEsqueleto();
    }

    public String invoke(Message message) throws JSONException{
        String resposta = "";

        String objectRef = message.getObjectRef();
        String methodId = message.getMethodId();
        String args = message.getArgs();

        if(objectRef.equals("RHManager")){
            switch (methodId) {
                case "calculaFolhaDePagamento":
                    resposta = esqueleto.calculaFolhaDePagamento(args);
                    break;
                case "calculaBonusDeFerias":
                    resposta = esqueleto.calculaBonusDeFerias(args);
                    break;
                case "calculaFGTS":
                    resposta = esqueleto.calculaFGTS(args);
                    break;
            }
        }

        return resposta;
    }

}
