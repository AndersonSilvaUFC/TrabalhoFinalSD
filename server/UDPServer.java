package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import model.Message;
import org.json.JSONObject;

public class UDPServer {
	public static void main (String args[]) {
		try{
			int serverPort = 7000; // the server port
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
			}
		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
	}
}
class Connection extends Thread {
	private Socket clientSocket;
	private DataInputStream in;
	private DataOutputStream out;
	private Despachante despachante;

	public Message desempoacotaRequisicao(String request){
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

	public String empacotaMensagem(String resultado, int requestId){
		JSONObject obj = new JSONObject();

		obj.put("type", 1);
		obj.put("id", requestId);
		obj.put("objectRef", "");
		obj.put("methodId","");
		obj.put("args", resultado);

		return obj.toString();
	}

	public String getRequest() throws IOException {
		return in.readUTF();
	}

	public void sendResponse(String response) throws IOException {
		out.writeUTF(response);
	}

	public Connection (Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream( clientSocket.getInputStream());
			out = new DataOutputStream( clientSocket.getOutputStream());
			despachante = new Despachante();
			this.start();
		} catch(IOException e) {System.out.println("server.Connection:"+e.getMessage());}
	}
	public void run(){
		try {			                 
			String data = getRequest();

			Message request = desempoacotaRequisicao(data);
			String response = despachante.invoke(data);
			sendResponse(empacotaMensagem(response,request.getId()));
			
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());}
		

	}
}
