package server;

import org.json.JSONException;
import org.json.JSONObject;

public class Despachante {
    private RHManagerEsqueleto esqueleto;

    public Despachante(){
        esqueleto = new RHManagerEsqueleto();
    }

    public String invoke(String message) throws JSONException{
        String resposta = "";
        JSONObject obj = new JSONObject(message);

        String objectRef = obj.getString("objectRef");
        String methodId = obj.getString("methodId");
        String args = obj.getString("args");

        if(objectRef.equals("RHManager")){
            switch (methodId) {
                case "calculaFolhaDePagamento":
                    resposta = esqueleto.calculaFolhaDePagamento(args);
                    break;
                case "calculaBonusDeFerias":
                    resposta = esqueleto.calculaBonusDeFerias(args);
                    break;
            }
        }

        return resposta;
    }

}
