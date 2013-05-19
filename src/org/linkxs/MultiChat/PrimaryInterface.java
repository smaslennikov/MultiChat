package org.linkxs.MultiChat;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class PrimaryInterface extends JFrame implements ActionListener,
		FocusListener, KeyListener {
	private static final long		serialVersionUID	= -4178122210815568529L;
	
	public static final Dimension	INITIAL_SCREEN_SIZE	= Toolkit
																.getDefaultToolkit()
																.getScreenSize();
	private static PrimaryInterface	primaryWindow;
	private static ClientInterface	clientWindow;
	private static ServerInterface	serverWindow;
	
	private static final int		WIDTH				= 245;
	private static final int		HEIGHT				= 105;
	
	private static int				PORT				= 1840;
	private static String			IP					= "localhost";
	
	private JLabel					ipLabel;
	private JLabel					portLabel;
	
	private JTextField				ipField;
	private JTextField				portField;
	
	private JButton					serverButton;
	private JButton					clientButton;
	
	public PrimaryInterface() {
		super("Java multiclient chat");
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setBounds(PrimaryInterface.INITIAL_SCREEN_SIZE.width / 2,
				PrimaryInterface.INITIAL_SCREEN_SIZE.height / 2,
				PrimaryInterface.WIDTH, PrimaryInterface.HEIGHT);
		super.setResizable(false);
		super.setLayout(null);
		
		this.ipLabel = new JLabel("IP: ");
		this.portLabel = new JLabel("Port: ");
		
		this.ipField = new JTextField();
		this.portField = new JTextField();
		
		this.serverButton = new JButton("Server");
		this.clientButton = new JButton("Client");
		
		this.ipLabel.setBounds(10, 10, 25, 20);
		this.ipField.setBounds(40, 10, 100, 20);
		
		this.portLabel.setBounds(150, 10, 40, 20);
		this.portField.setBounds(195, 10, 40, 20);
		
		this.serverButton.setBounds(10, 40, 105, 30);
		this.clientButton.setBounds(130, 40, 105, 30);
		
		this.ipField.addFocusListener(this);
		this.ipField.addKeyListener(this);
		
		this.portField.addFocusListener(this);
		this.portField.addKeyListener(this);
		
		this.serverButton.addActionListener(this);
		this.clientButton.addActionListener(this);
		
		super.add(ipLabel);
		super.add(portLabel);
		super.add(ipField);
		super.add(portField);
		super.add(serverButton);
		super.add(clientButton);
	}
	
	public void dispose() {
		System.exit(0);
	}
	
	public static void main(String[] args) {
		primaryWindow = new PrimaryInterface();
		primaryWindow.setVisible(true);
	}
	
	public ServerInterface startServer() {
		primaryWindow.setVisible(false);
		serverWindow = new ServerInterface(primaryWindow, PORT);
		serverWindow.setVisible(true);
		
		return serverWindow;
	}
	
	public ClientInterface startClient() {
		primaryWindow.setVisible(false);
		clientWindow = new ClientInterface(primaryWindow, IP, PORT);
		clientWindow.setVisible(true);
		
		return clientWindow;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.clientButton)) {
			this.startClient();
		} else if (e.getSource().equals(this.serverButton)) {
			this.startServer();
		}
	}
	
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
