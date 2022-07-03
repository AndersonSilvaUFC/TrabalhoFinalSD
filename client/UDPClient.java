package client;

import java.net.*;
import java.io.*;



public class UDPClient {
	Socket s;
	int serverPort;
	String host;
	
	public UDPClient(String ip, int port) {
	    try{
	    	this.host = ip;
			this.serverPort = port;
		   	this.s = new Socket(this.host, this.serverPort);
	    } catch (UnknownHostException e){System.out.println("Sock:"+e.getMessage()); 
	    } catch (EOFException e){ System.out.println("EOF:"+e.getMessage());
	    } catch (IOException e){ System.out.println("IO:"+e.getMessage());}
	}
	
	public void sendRequest(String request) {
		try {
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			out.writeUTF(request);
		}catch(IOException e) {
			System.out.println("IO: "+e.getMessage());
		}
	}
	
	public String getResponse() {
		try {
			DataInputStream in = new DataInputStream(s.getInputStream());
			return in.readUTF();
		}catch(IOException e) {
			System.out.println("IO: "+e.getMessage());
			return "Erro";
		}
	}
	
	public void close() {
		try {
			s.close();
		}catch(IOException e) {
			System.out.println("IO: "+e.getMessage());
		}
	}
}
