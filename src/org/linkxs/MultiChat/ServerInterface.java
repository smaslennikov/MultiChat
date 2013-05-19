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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerInterface extends JFrame implements ActionListener,
		KeyListener, FocusListener {
	private static final long	serialVersionUID	= -3738238223394683437L;
	
	private PrimaryInterface	primaryWindow;
	private ClientInterface		clientWindow;
	private ServerMachine		serverEngine;
	
	private String				IP;
	private int					PORT;
	
	private static final int	WIDTH				= 500;
	private static final int	HEIGHT				= 600;
	
	private JTextArea			outputField;
	
	private JTextField			commandField;
	
	private JButton				startClientButton;
	private JButton				startButton;
	private JButton				stopButton;
	
	private JMenuBar			topBar;
	private JMenu				fileMenu;
	private JMenu				editMenu;
	private JMenu				helpMenu;
	
	public ServerInterface(PrimaryInterface primaryWindow, int PORT) {
		super("Java multiclient chat SERVER");
		
		this.primaryWindow = primaryWindow;
		this.PORT = PORT;
		
		super.setBounds(PrimaryInterface.INITIAL_SCREEN_SIZE.width / 3,
				PrimaryInterface.INITIAL_SCREEN_SIZE.height / 3,
				ServerInterface.WIDTH, ServerInterface.HEIGHT);
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setResizable(false);
		super.setLayout(null);
		
		this.outputField = new JTextArea();
		this.outputField.setBounds(10, 30, 300, 500);
		this.outputField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		this.outputField.setEditable(false);
		
		this.commandField = new JTextField();
		this.commandField.setBounds(10, 540, 300, 20);
		this.commandField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		this.commandField.addKeyListener(this);
		this.commandField.addFocusListener(this);
		
		this.startClientButton = new JButton("Start client");
		this.startClientButton.setBounds(320, 30, 170, 30);
		this.startClientButton.addActionListener(this);
		
		this.startButton = new JButton("Start");
		this.startButton.setBounds(320, 70, 170, 30);
		this.startButton.addActionListener(this);
		
		this.stopButton = new JButton("Stop");
		this.stopButton.setBounds(320, 110, 170, 30);
		this.stopButton.addActionListener(this);
		
		this.topBar = new JMenuBar();
		this.topBar.setBounds(0, 0, ServerInterface.WIDTH, 20);
		
		this.fileMenu = new JMenu("File");
		this.editMenu = new JMenu("Edit");
		this.helpMenu = new JMenu("Help");
		
		this.topBar.add(fileMenu);
		this.topBar.add(editMenu);
		this.topBar.add(helpMenu);
		
		super.add(outputField);
		super.add(commandField);
		super.add(startClientButton);
		super.add(startButton);
		super.add(stopButton);
		super.add(topBar);
		
		this.postInit();
	}
	
	public void postInit() {
		this.serverEngine = new ServerMachine();
		
		this.outputField.append("Server initiated on port " + PORT
				+ ".\n\nFor a list of commands, enter \'~h\'.");
	}
	
	public void dispose() {
		System.exit(0);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.startClientButton)) {
			clientWindow = primaryWindow.startClient();
			clientWindow.setLocation(this.getX() + 500, this.getY());
		} else if (e.getSource().equals(this.startButton)) {
			this.serverEngine.startServer(PORT);
		} else if (e.getSource().equals(this.stopButton)) {
			this.serverEngine.stopServer();
		}
	}
	
	public void keyTyped(KeyEvent e) {
	}
	
	public void keyPressed(KeyEvent e) {
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER
				&& e.getSource().equals(this.commandField)) {
			if (this.commandField.getText().startsWith("~")) {
				serverEngine.commander(this.commandField.getText().substring(1));
			} else {
				this.outputField.append("\n" + this.commandField.getText());
			}
			this.commandField.setText("");
		}
	}
	
	public void focusGained(FocusEvent e) {
	}
	
	public void focusLost(FocusEvent e) {
	}
}
