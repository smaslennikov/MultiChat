package org.linkxs.MultiChat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler implements Runnable {
	Socket clientSocket;
	
	public ClientHandler(ServerSocket serverSocket) {
		while (true) {
			try {
				clientSocket = serverSocket.accept();
				Thread thread = new Thread(this);
				thread.start();
				System.out.println("Started thread");
			} catch (IOException e) {
				System.out.println("Exception while creating new thread");
				e.printStackTrace();
			}
		}
	}
	
	public void run() {
		Socket clientSocket;
		BufferedReader clientIn;
		DataOutputStream clientOut;
		
		clientSocket = this.clientSocket;
		
		try {
			clientIn = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			clientOut = new DataOutputStream(clientSocket.getOutputStream());
			System.out.println("Created IO streams");
		} catch (IOException e) {
			System.out.println("Exception while creating IO streams");
			e.printStackTrace();
		}
	}
}
