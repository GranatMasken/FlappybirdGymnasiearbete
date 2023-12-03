package main;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7268821224869093997L;
	
	GameFrame() {
	GamePanel panel = new GamePanel();
	this.add(panel);
	
	this.setTitle("FlaxFågel");
	this.setSize(WIDTH,HEIGHT);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.pack();
	this.setVisible(true);
	this.setResizable(false);
	
	}
}
