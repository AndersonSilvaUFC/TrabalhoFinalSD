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
	
	public UDPServer(){
		DatagramSocket aSocket = null;
	    try{
	    	aSocket = new DatagramSocket(6789);
	    	byte[] buffer = new byte[1000];
	 		while(true){
 				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
 				aSocket.receive(request);
 				DatagramPacket reply = new DatagramPacket(request.getData(), 
			   	request.getLength(), request.getAddress(), request.getPort());
 				aSocket.send(reply);
			}
	    } catch (SocketException e) {
	    	System.out.println("Socket: " + e.getMessage());
	    } catch (IOException e) {
	    	System.out.println("IO: " + e.getMessage());
		} finally {
			if(aSocket != null) {
				aSocket.close();
			}
		}
	}
}
