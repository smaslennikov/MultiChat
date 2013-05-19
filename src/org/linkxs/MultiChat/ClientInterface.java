package org.linkxs.MultiChat;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientInterface extends JFrame implements ActionListener,
		KeyListener, FocusListener {
	private static final long	serialVersionUID	= -3738238223394683437L;
	
	private PrimaryInterface	primaryWindow;
	private ClientEngine		clientEngine;
	
	private String				IP;
	private int					PORT;
	
	private static final int	WIDTH				= 500;
	private static final int	HEIGHT				= 600;
	
	private static int			ID;
	private static String		nickname;
	
	private JTextArea			outputField;
	
	private JTextField			commandField;
	
	private JButton				connectButton;
	private JButton				disconnectButton;
	
	private JMenuBar			topBar;
	private JMenu				fileMenu;
	private JMenu				editMenu;
	private JMenu				helpMenu;
	
	public ClientInterface(PrimaryInterface primaryWindow, String IP, int PORT) {
		super("Java multiclient chat CLIENT");
		super.setBounds(PrimaryInterface.INITIAL_SCREEN_SIZE.width / 3,
				PrimaryInterface.INITIAL_SCREEN_SIZE.height / 3,
				ClientInterface.WIDTH, ClientInterface.HEIGHT);
		this.primaryWindow = primaryWindow;
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setResizable(false);
		super.setLayout(null);
		
		if (IP.equals("")) {
			this.IP = "localhost";
		} else {
			this.IP = IP;
		}
		
		if (PORT == 0) {
			this.PORT = 1840;
		} else {
			this.PORT = PORT;
		}
		
		this.outputField = new JTextArea();
		this.outputField.setBounds(10, 30, 300, 500);
		this.outputField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		this.outputField.setEditable(false);
		
		this.commandField = new JTextField();
		this.commandField.setBounds(10, 540, 300, 20);
		this.commandField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		this.commandField.addKeyListener(this);
		this.commandField.addFocusListener(this);
		
		this.connectButton = new JButton("Connect");
		this.connectButton.setBounds(320, 30, 170, 30);
		this.connectButton.addActionListener(this);
		
		this.disconnectButton = new JButton("Disconnect");
		this.disconnectButton.setBounds(320, 70, 170, 30);
		this.disconnectButton.addActionListener(this);
		
		this.topBar = new JMenuBar();
		this.topBar.setBounds(0, 0, ClientInterface.WIDTH, 20);
		
		this.fileMenu = new JMenu("File");
		this.editMenu = new JMenu("Edit");
		this.helpMenu = new JMenu("Help");
		
		this.topBar.add(fileMenu);
		this.topBar.add(editMenu);
		this.topBar.add(helpMenu);
		
		super.add(outputField);
		super.add(commandField);
		super.add(connectButton);
		super.add(disconnectButton);
		super.add(topBar);
		
		this.postInit();
	}
	
	public void postInit() {
		this.clientEngine = new ClientEngine();
		
		this.outputField
				.append("Client initiated.\n\nFor a list of commands, enter \'~h\'.");
	}
	
	public void dispose() {
		System.exit(0);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.connectButton)) {
			this.IP = JOptionPane.showInputDialog(this, "Please enter IP",
					this.IP);
			if (this.clientEngine.connect(IP, PORT)) {
				this.setNickname();
			} else {
				JOptionPane.showMessageDialog(this, "Connection failed");
			}
		} else if (e.getSource().equals(this.disconnectButton)) {
			this.clientEngine.disconnect();
		}
	}
	
	public void setNickname() {
		JOptionPane.showInputDialog(this, "Please enter nickname",
				("Guest" + ID));
	}
	
	public void focusGained(FocusEvent e) {
	}
	
	public void focusLost(FocusEvent e) {
	}
	
	public void keyTyped(KeyEvent e) {		
	}
	
	public void keyPressed(KeyEvent e) {		
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER
				&& e.getSource().equals(this.commandField)) {
			if (this.commandField.getText().startsWith("~")) {
				clientEngine
						.commander(this.commandField.getText().substring(1));
			} else {
				this.outputField.append("\n" + this.commandField.getText());
			}
			this.commandField.setText("");
		}
	}
}
