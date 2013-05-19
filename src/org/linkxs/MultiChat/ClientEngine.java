package org.linkxs.MultiChat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientEngine {
	private Socket				socket;
	private BufferedReader		serverIn;
	private DataOutputStream	serverOut;
	
	public ClientEngine() {
		
	}
	
	public void commander(String command) {
		
	}
	
	public boolean connect(String IP, int PORT) {
		try {
			socket = new Socket(IP, PORT);
			serverIn = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			serverOut = new DataOutputStream(socket.getOutputStream());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void disconnect() {
		try {
			socket.close();
			serverIn.close();
			serverOut.close();
		} catch (IOException e) {
			System.out.println("Exception closing socket and IOs for client");
			e.printStackTrace();
		}
	}
	
	public void newbiePacket() {
		
	}
}

