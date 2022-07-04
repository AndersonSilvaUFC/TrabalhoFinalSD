package server;

import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import model.Message;
import org.json.JSONObject;

import java.net.*;
import java.io.*;

public class UDPServer{
	DatagramSocket aSocket = null;
	byte[] buffer = null;
	DatagramPacket request = null;
	DatagramPacket reply = null;

	
	public UDPServer(){
	    try{
	    	aSocket = new DatagramSocket(6789);
	    	buffer = new byte[1000];
	    	
	 		while(true){
 				request = new DatagramPacket(buffer, buffer.length);
 				reply = new DatagramPacket(request.getData(), 
			   	request.getLength(), request.getAddress(), request.getPort());
			}
	    } catch (SocketException e) {
	    	System.out.println("Socket: " + e.getMessage());
	    } 
	}
	
		public String getRequest() throws IOException {
			aSocket.receive(request);
			return request.toString();
		}
		
		public void sendResponse() throws IOException {
			aSocket.send(reply);
		
		}
		
		public void close() {
			if(aSocket != null) {
				aSocket.close();
			}
		}
		
	
}
