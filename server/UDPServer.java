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

import model.Message;

import org.json.JSONException;
import org.json.JSONObject;

public class UDPServer {
	
	
	public static void main (String args[]) throws IOException, JSONException {
		DatagramSocket aSocket = null;
		byte[] buffer = null;
		DatagramPacket request = null;
		DatagramPacket reply = null;
		Despachante despachante;
		 try{
			    despachante = new Despachante();
		    	aSocket = new DatagramSocket(6789);
		    	buffer = new byte[1000];
		 		while(true){
	 				request = new DatagramPacket(buffer, buffer.length);
	 				//reply = new DatagramPacket(request.getData(), 
				   //request.getLength(), request.getAddress(), request.getPort());
	 				
	 				aSocket.receive(request);
	 				
	 				String requestStr = new String(request.getData());
	 				
	 				Message msgRequest = desempoacotaRequisicao(requestStr);
	 				
	 				String response = despachante.invoke(requestStr);
	 				
	 				byte[] resAux = empacotaMensagem(response,msgRequest.getId());
	 				
	 				reply = new DatagramPacket(resAux, resAux.length,request.getAddress(), request.getPort() );
	 				
	 				aSocket.send(reply);
	 				
				}
		    } catch (SocketException e) {
		    	System.out.println("Socket: " + e.getMessage());
		    } 
	}
	
	public  static Message desempoacotaRequisicao(String request) throws JSONException{
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

	public static byte[] empacotaMensagem(String resultado, int requestId) throws JSONException{
		JSONObject obj = new JSONObject();

		obj.put("type", 1);
		obj.put("id", requestId);
		obj.put("objectRef", "");
		obj.put("methodId","");
		obj.put("args", resultado);

		return obj.toString().getBytes();
	}
}

	
		
