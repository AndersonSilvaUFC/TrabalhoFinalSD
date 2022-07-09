package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import model.Message;

import org.json.JSONException;
import org.json.JSONObject;

public class UDPServer {

	public static void main (String args[]) throws IOException, JSONException {
		Map<Integer, String> historico = new HashMap<>();
		DatagramSocket aSocket = null;
		byte[] buffer = null;
		DatagramPacket request = null;
		DatagramPacket reply = null;
		Despachante despachante;
		long limite = 200;
		long total = 0;
		try{
			    despachante = new Despachante();
		    	aSocket = new DatagramSocket(6789);
		    	buffer = new byte[1000];
		 		while(true) {

					request = new DatagramPacket(buffer, buffer.length);

					aSocket.receive(request);

					String requestStr = new String(request.getData());

					Message msgRequest = desempoacotaRequisicao(requestStr);
					String response;
					//System.out.println("id: "+msgRequest.getId());
					int idAtual = msgRequest.getId();
					byte[] resAux;

					// Tratamento de duplicadas
					if (!historico.containsKey(idAtual) ) {
						long start = System.currentTimeMillis();
						response = despachante.invoke(msgRequest);
						long end = System.currentTimeMillis();

						total = (end - start);

						historico.put(idAtual, response);
					} else {
						//System.out.println("Duplicada");
						response = historico.get(idAtual);
					}

					if(total < limite) {
						resAux = empacotaMensagem(response,idAtual);
						reply = new DatagramPacket(resAux, resAux.length,request.getAddress(), request.getPort() );
						aSocket.send(reply);
					}
				}
		    } catch (SocketException e) {
		    	System.out.println("Socket: " + e.getMessage());
		    }
	}

	private static Message desempoacotaRequisicao(String request) throws JSONException{
		JSONObject obj = new JSONObject(request);

		Message message = new Message(
			obj.getInt("type"),
			obj.getInt("id"),
			obj.getString("objectRef"),
			obj.getString("methodId"),
			obj.getString("args")
		);

		return message;
	}

	private static byte[] empacotaMensagem(String resultado, int requestId) throws JSONException{
		JSONObject obj = new JSONObject();
		obj.put("type", 1);
		obj.put("id", requestId);
		obj.put("objectRef", "");
		obj.put("methodId", "");

		if(resultado != null) {
			obj.put("args", resultado);
		} else {
			System.out.println(resultado);
			obj.put("args", "");
		}
		return obj.toString().getBytes();
	}
}

	
		
