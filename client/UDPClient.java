package client;

import java.net.*;
import java.io.*;


public class UDPClient{
	
	private DatagramPacket request = null;
	private DatagramPacket reply = null;
	private DatagramSocket aSocket = null;
	private InetAddress aHost = null;
	private int Port;
	private byte[] buffer = new byte[1000];
	
	
	public UDPClient(String host,int serverPort){
		
		try {
			Port = serverPort;
			aSocket = new DatagramSocket();
			aHost = InetAddress.getByName(host);   
			byte[] buffer = new byte[1000];
			reply = new DatagramPacket(buffer, buffer.length);	
			
			
//			aSocket.setSoTimeout(15 * 1000); 15 segundos

			
			aSocket.receive(reply);
			System.out.println("Reply: " + new String(reply.getData()));	
		} catch (SocketException e){
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e){
			System.out.println("IO: " + e.getMessage());
		} finally {
			if(aSocket != null) {
				aSocket.close();
			}
		}
	 
	}
	
	byte[] getResponse() throws IOException{
		aSocket.receive(reply);
		return null;
	}
	
	void sendRequest(byte[] mensagem) throws IOException{
		request = new DatagramPacket(mensagem,  mensagem.length, aHost, Port);
		aSocket.send(request);
	}
	
	void close() {
		if(aSocket != null) {
			aSocket.close();
		}
	}
	
}
