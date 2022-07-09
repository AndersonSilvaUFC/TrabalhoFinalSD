package client;

import model.Funcionario;
import model.Message;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

public class RHManagerProxy {
    private UDPClient udpClient;
    private int idAtual = 0;
    private int limiteRequisicoes = 10;

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
        JSONObject funionarioJson = new JSONObject(funcionario);

        String args = funionarioJson.toString();

        String respostaString = doOperation("RHManager","calculaBonusDeFerias",args);

        JSONObject respObj = new JSONObject(respostaString);

        double resposta = respObj.getDouble("resposta");

        return resposta;
    }

    public double calculaFGTS(Funcionario funcionario) throws JSONException, IOException{
        JSONObject funcionarioJson = new JSONObject(funcionario);

        String args = funcionarioJson.toString();

        String respostaString = doOperation("RHManager","calculaFGTS",args);

        JSONObject respObj = new JSONObject(respostaString);

        double resposta = respObj.getDouble("resposta");

        return resposta;
    }

    private String doOperation(String objectRef, String methodId, String args) throws JSONException, IOException {
        Message resposta;
        String data = empacotaMenagem(objectRef, methodId, args);

        while (limiteRequisicoes > 0){

            try {
                resposta = desempacotaMensagem(udpClient.getResponse());
                //System.out.println(resposta.getId());

                limiteRequisicoes = 10;
                return resposta.getArgs();
            } catch (SocketTimeoutException e) {
                //System.out.println("Limite: "+limiteRequisicoes);
                udpClient.sendRequest(data);
                limiteRequisicoes--;
            }
        }
        //udpClient.getResponse();
        limiteRequisicoes = 10;
        JSONObject respostaJson = new JSONObject();
        respostaJson.put("resposta",-1.0);
        return respostaJson.toString();
    }

    private String empacotaMenagem(String objectRef, String methodId, String args) throws JSONException{
        Message message = new Message(0,idAtual++,objectRef,methodId,args);

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

    public void close(){
        this.udpClient.close();
    }
}
