package org.linkxs.MultiChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMachine implements Runnable {
	private int				PORT;
	
	private ClientHandler	clientHandler;
	private ServerSocket	serverSocket;
	private Socket			clientSocket;
	
	private Thread			thread;
	
	public ServerMachine() {
		
	}
	
	public void commander(String command) {
		System.out.println(command);
	}
	
	public void startServer(int PORT) {
		this.PORT = PORT;
		
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	public void stopServer() {
		// TODO: stops server
	}
	
	public void run() {
		try {
			serverSocket = new ServerSocket(this.PORT);
		} catch (IOException e) {
			System.out.println("Exception while starting server");
			e.printStackTrace();
		}
		
		clientHandler = new ClientHandler(serverSocket);
	}
}
