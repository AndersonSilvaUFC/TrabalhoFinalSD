package client;

import model.Funcionario;
import model.Message;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class RHManagerProxy {
    private UDPClient udpClient;
    private int idAtual = 0;

    public RHManagerProxy(){
        this.udpClient = new UDPClient("localhost",6789);
    }

    public double calculaFolhaDePagamento(List<Funcionario> funcionarios) throws JSONException, IOException{
        JSONArray funcionarioJson = new JSONArray(funcionarios);

        String args = funcionarioJson.toString();

        String respostaString = doOperation("RHManager", "calculaFolhaDePagamento", args);

        JSONObject respObj = new JSONObject(respostaString);

        double resposta = respObj.getDouble("resposta");

        return resposta;
    }

    public double calculaBonusDeFerias(Funcionario funcionario) throws JSONException, IOException{
        JSONObject obj = new JSONObject(funcionario);

        String args = obj.toString();

        String respostaString = doOperation("RHManager","calculaBonusDeFerias",args);

        JSONObject respObj = new JSONObject(respostaString);

        double resposta = respObj.getDouble("resposta");

        return resposta;
    }

    private String doOperation(String objectRef, String methodId, String args) throws JSONException, IOException{

        String data = empacotaMenagem(objectRef, methodId, args);

        udpClient.sendRequest(data);

        Message resposta = desempacotaMensagem(udpClient.getResponse());

        return resposta.getArgs();
    }

    private String empacotaMenagem(String objectRef, String methodId, String args) throws JSONException{
        Message message = new Message(0,idAtual,objectRef,methodId,args);

        JSONObject obj = new JSONObject();

        obj.put("type", message.getType());
        obj.put("id", message.getId());
        obj.put("objectRef", message.getObjectRef());
        obj.put("methodId", message.getMethodId());
        obj.put("args",message.getArgs());

        return obj.toString();
    }

    private Message desempacotaMensagem(String resposta) throws JSONException{
        JSONObject obj = new JSONObject(resposta);

        Message message = new Message(
                obj.getInt("type"),
                obj.getInt("id"),
                obj.getString("objectRef"),
                obj.getString("methodId"),
                obj.getString("args")
            );

        return message;
    }


}
