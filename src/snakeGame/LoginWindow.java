
package snakeGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class LoginWindow implements KeyListener {
	
	private JFrame frame;
	private JPanel panelTop;
	private JPanel panelCenter;
	private JPanel panelBot;
	
	// private int playerOne, playerTwo, playerThree, playerFour, AIPlayers = 0;
	private int AIPlayers;
	
	private JPanel panelMid1 = new JPanel(new GridLayout(0, 4));
	private JPanel panelMid2 = new JPanel(new GridLayout(0, 4));
	private JPanel panelMid3 = new JPanel(new GridLayout(0, 4));
	private JPanel panelMid4 = new JPanel(new GridLayout(0, 4));
	
	private JLabel P1Label = new JLabel("Press 'UP'");
	private JLabel P11Label = new JLabel("Player One: ");
	private JLabel P2Label = new JLabel("Press 'W'");
	private JLabel P21Label = new JLabel("Player Two: ");
	private JLabel P3Label = new JLabel("Press 'Y'");
	private JLabel P31Label = new JLabel("Player Three: ");
	private JLabel P4Label = new JLabel("Press 'P'");
	private JLabel P41Label = new JLabel("Player Four: ");
	
	private Server server;
	
	public LoginWindow(Server server) {
		this.server = server;
		frame = new JFrame("Log In Window");
		frame.setBounds(450, 150, 475, 300);
		frame.setLayout(new GridLayout(3, 0));
		
		topFrameSettings();
		centerFrameSettings();
		bottomFrameSettings();
		
		// Advises the window when 'x' is pressed then the window closes
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// The Frame cannot be resized
		frame.setResizable(false);
		// Make the keyListener active to this window
		frame.addKeyListener(this);
		frame.setFocusable(true);
		// Tells the frame that it is visible
		frame.setVisible(true);
	}
	
	public void topFrameSettings() {
		// TOP //
		panelTop = new JPanel();
		// ***** PLACE HEADING HERE *****
		ImageIcon snake = new ImageIcon("snake.jpg");
		JLabel snakeLabel = new JLabel();
		snakeLabel.setIcon(snake);
		panelTop.add(snakeLabel);
		frame.add(panelTop, BorderLayout.NORTH);
	}
	
	public void centerFrameSettings() {
		// CENTER //
		panelCenter = new JPanel(new GridLayout(4, 0));
		
		JLabel labelOut1 = new JLabel("Player OUT");
		JLabel labelOut2 = new JLabel("Player OUT");
		JLabel labelOut3 = new JLabel("Player OUT");
		JLabel labelOut4 = new JLabel("Player OUT");
		JLabel labelNotReadyImage1 = new JLabel();
		JLabel labelNotReadyImage2 = new JLabel();
		JLabel labelNotReadyImage3 = new JLabel();
		JLabel labelNotReadyImage4 = new JLabel();
		ImageIcon redCross = new ImageIcon("redCross.jpg");
		labelNotReadyImage1.setIcon(redCross);
		labelNotReadyImage2.setIcon(redCross);
		labelNotReadyImage3.setIcon(redCross);
		labelNotReadyImage4.setIcon(redCross);
		
		// Panel - One
		panelMid1.add(P1Label);
		panelMid1.add(P11Label);
		P11Label.setBackground(Color.yellow);
		P11Label.setOpaque(true);
		panelMid1.add(labelOut1);
		panelMid1.add(labelNotReadyImage1);
		
		// Panel - Two
		panelMid2.add(P2Label);
		panelMid2.add(P21Label);
		P21Label.setBackground(Color.blue);
		P21Label.setForeground(Color.white);
		P21Label.setOpaque(true);
		panelMid2.add(labelOut2);
		panelMid2.add(labelNotReadyImage2);
		
		// Panel - Three
		panelMid3.add(P3Label);
		panelMid3.add(P31Label);
		P31Label.setBackground(Color.red);
		P31Label.setOpaque(true);
		panelMid3.add(labelOut3);
		panelMid3.add(labelNotReadyImage3);
		
		// Panel - Four
		panelMid4.add(P4Label);
		panelMid4.add(P41Label);
		P41Label.setBackground(Color.green);
		P41Label.setOpaque(true);
		panelMid4.add(labelOut4);
		panelMid4.add(labelNotReadyImage4);
		
		// Add the secondary panels to the main 'Center' panel
		panelCenter.add(panelMid1);
		panelCenter.add(panelMid2);
		panelCenter.add(panelMid3);
		panelCenter.add(panelMid4);
		
		frame.add(panelCenter, BorderLayout.CENTER);
		
	}
	
	public void bottomFrameSettings() {
		
		// BOTTOM //
		panelBot = new JPanel(new GridLayout(3, 0));
		
		// Create new panels
		JPanel panelBot1 = new JPanel(new GridLayout(0, 4));
		JPanel panelBot2 = new JPanel();
		JPanel panelBot3 = new JPanel();
		
		JLabel panelBotLabel1 = new JLabel("");
		JLabel panelBotLabel2 = new JLabel("No. of AI PLayers: ");
		JTextField panelBotTextField = new JTextField();
		JLabel panelBotLabel4 = new JLabel("< or = to 100.");
		
		panelBot1.add(panelBotLabel1);
		panelBot1.add(panelBotLabel2);
		panelBot1.add(panelBotTextField);
		panelBot1.add(panelBotLabel4);
		
		// Make the button to start game
		JButton startButton = new JButton("Start the Game");
		
		// Add actionListener, so when button is pressed it opens new window
		startButton.addActionListener(new ActionListener() {
			
			@Override // Close current window, make new instance of new one and
						// open it
			public void actionPerformed(ActionEvent e) {
				// IF the textbox has data, AND if the data is numeric, AND 1 or
				// more player Snakes have been created.
				if (panelBotTextField.getText().toString().length() > 0 && isNumeric(panelBotTextField)
						&& server.realPlayerList.size() > 0) {
					
					AIPlayers = Integer.parseInt(panelBotTextField.getText().toString());
					
					// if the number of AI are not valid, reject input. ELSE,
					// start the game.
					if (AIPlayers > 100) {
						JOptionPane.showMessageDialog(panelBot, "There can only be 100 AI Players.");
						panelBotTextField.setText("");
					} else if (AIPlayers <= 0) {
						JOptionPane.showMessageDialog(panelBot, "There have to be more than 0 AI Players.");
						panelBotTextField.setText("");
					} else {
						server.startGame(AIPlayers);
						frame.dispose();
						
					}
				}
			}
		});
		
		panelBot2.add(startButton);
		
		// once the user clicks on the textbox, the KeyListener will not pick up
		// the "Login" values
		// so the user must press tab twice to return focus to the background
		// before adding more snakes.
		JLabel panelBot3Label1 = new JLabel("*Press Tab 2x to be able to enter players again*");
		panelBot3.add(panelBot3Label1);
		panelBot.add(panelBot1);
		panelBot.add(panelBot2);
		panelBot.add(panelBot3);
		frame.add(panelBot, BorderLayout.SOUTH);
	}
	
	/*
	 * checks if the data in the PanelBoxTextField is numeric.
	 */
	public static boolean isNumeric(JTextField panelBotTextField) {
		try {
			int i = Integer.parseInt(panelBotTextField.getText().toString());
		}
		catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		int c = e.getKeyCode();
		
		JLabel labelIn = new JLabel("Player IN.");
		JLabel labelReadyImage = new JLabel();
		ImageIcon greenTick = new ImageIcon("greenTick.jpg");
		labelReadyImage.setIcon(greenTick);
		labelReadyImage.setIcon(greenTick);
		
		// depending on which button was pressed, create a snake with the
		// corresponding control scheme.
		switch (c) {
			case KeyEvent.VK_UP:
				server.addSnakePlayer(1);
				
				panelMid1.removeAll();
				panelMid1.add(P1Label);
				panelMid1.add(P11Label);
				panelMid1.add(labelIn);
				panelMid1.add(labelReadyImage);
				panelMid1.repaint();
				panelMid1.revalidate();
				break;
			
			case KeyEvent.VK_W:
				server.addSnakePlayer(2);
				
				panelMid2.removeAll();
				panelMid2.add(P2Label);
				panelMid2.add(P21Label);
				panelMid2.add(labelIn);
				panelMid2.add(labelReadyImage);
				panelMid2.repaint();
				panelMid2.revalidate();
				break;
			
			case KeyEvent.VK_Y:
				server.addSnakePlayer(3);
				
				panelMid3.removeAll();
				panelMid3.add(P3Label);
				panelMid3.add(P31Label);
				panelMid3.add(labelIn);
				panelMid3.add(labelReadyImage);
				panelMid3.repaint();
				panelMid3.revalidate();
				break;
			
			case KeyEvent.VK_P:
				server.addSnakePlayer(4);
				
				panelMid4.removeAll();
				panelMid4.add(P4Label);
				panelMid4.add(P41Label);
				panelMid4.add(labelIn);
				panelMid4.add(labelReadyImage);
				panelMid4.repaint();
				panelMid4.revalidate();
				break;
			
			default:
				break;
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	public int getAIPlayers() {
		return AIPlayers;
	}
	
	public void setAIPlayers(int aIPlayers) {
		AIPlayers = aIPlayers;
	}
}
