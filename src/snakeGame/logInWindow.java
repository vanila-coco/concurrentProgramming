package snakeGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class logInWindow extends JFrame implements KeyListener{

	//private JFrame frame;
	private JPanel panelTop;
	private JPanel panelCenter;
	private JPanel panelBot;

	private int playerOne, playerTwo, playerThree, playerFour = 0;
	public int getPlayerOne() {
		return playerOne;
	}

	public void setPlayerOne(int playerOne) {
		this.playerOne = playerOne;
	}

	public int getPlayerTwo() {
		return playerTwo;
	}

	public void setPlayerTwo(int playerTwo) {
		this.playerTwo = playerTwo;
	}

	public int getPlayerThree() {
		return playerThree;
	}

	public void setPlayerThree(int playerThree) {
		this.playerThree = playerThree;
	}

	public int getPlayerFour() {
		return playerFour;
	}

	public void setPlayerFour(int playerFour) {
		this.playerFour = playerFour;
	}

	public int getAIPlayers() {
		return AIPlayers;
	}

	public void setAIPlayers(int aIPlayers) {
		AIPlayers = aIPlayers;
	}

	private int AIPlayers = 0;

	private JPanel panelMid1 = new JPanel(new GridLayout(0, 4));
	private JPanel panelMid2 = new JPanel(new GridLayout(0, 4));
	private JPanel panelMid3 = new JPanel(new GridLayout(0, 4));
	private JPanel panelMid4 = new JPanel(new GridLayout(0, 4));

	private JLabel P1Label = new JLabel("Press 'UP'");
	private JLabel P12Label = new JLabel("Player One: ");
	private JLabel P2Label = new JLabel("Press 'W'");
	private JLabel P21Label = new JLabel("Player Two: ");
	private JLabel P3Label = new JLabel("Press 'T'");
	private JLabel P31Label = new JLabel("Player Three: ");
	private JLabel P4Label = new JLabel("Press 'I'");
	private JLabel P41Label = new JLabel("Player Four: ");

	public logInWindow(){

		this.setName("Log In Window");
		setBounds(450, 150, 475, 300);
		setLayout(new GridLayout(3, 0));

		topFrameSettings();
		centerFrameSettings();
		bottomFrameSettings();

		// Advises the window when 'x' is pressed then the window closes
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// The Frame cannot be resized
		setResizable(false);
		// Make the keyListener active to this window
		addKeyListener(this);
		setFocusable(true);
		// Tells the frame that it is visible 
		setVisible(true);
	}

	public void topFrameSettings(){
		// TOP //
		panelTop = new JPanel();
		// ***** PLACE HEADING HERE *****
		ImageIcon snake = new ImageIcon("/Users/Miljan/Desktop/Uni/JavaProgramming/SnakeWindow/snake.jpg");
		JLabel snakeLabel = new JLabel();
		snakeLabel.setIcon(snake);
		panelTop.add(snakeLabel);
		add(panelTop, BorderLayout.NORTH);
	}

	public void centerFrameSettings(){
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
		ImageIcon redCross = new ImageIcon("/Users/Miljan/Desktop/Uni/JavaProgramming/SnakeWindow/redCross.jpg");
		labelNotReadyImage1.setIcon(redCross);
		labelNotReadyImage2.setIcon(redCross);
		labelNotReadyImage3.setIcon(redCross);
		labelNotReadyImage4.setIcon(redCross);

		// Panel - One 
		panelMid1.add(P1Label);
		panelMid1.add(P12Label);
		panelMid1.add(labelOut1);
		panelMid1.add(labelNotReadyImage1);

		// Panel - Two		
		panelMid2.add(P2Label);
		panelMid2.add(P21Label);
		panelMid2.add(labelOut2);
		panelMid2.add(labelNotReadyImage2);

		// Panel - Three		
		panelMid3.add(P3Label);
		panelMid3.add(P31Label);
		panelMid3.add(labelOut3);
		panelMid3.add(labelNotReadyImage3);

		// Panel - Four
		panelMid4.add(P4Label);
		panelMid4.add(P41Label);
		panelMid4.add(labelOut4);
		panelMid4.add(labelNotReadyImage4);

		// Add the secondary panels to the main 'Center' panel 
		panelCenter.add(panelMid1);
		panelCenter.add(panelMid2);
		panelCenter.add(panelMid3);
		panelCenter.add(panelMid4);

		add(panelCenter, BorderLayout.CENTER);

	}

	public void bottomFrameSettings(){

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

			@Override // Close current window, make new instance of new one and open it
			public void actionPerformed(ActionEvent e) {

				AIPlayers = Integer.parseInt(panelBotTextField.getText().toString());

				if(AIPlayers > 100){
					JOptionPane.showMessageDialog(panelBot, "There can only be 100 AI Players.");
					panelBotTextField.setText("");
				}else{
					System.out.println("AI Players: " + AIPlayers);
					System.out.println("---- If '1' then player is in, otherwise is not ----");
					System.out.println("Player One: " + playerOne);
					System.out.println("Player Two: " + playerTwo);
					System.out.println("Player Three: " + playerThree);
					System.out.println("Player Four: " + playerFour);
					dispose();
					gameWindow second = new gameWindow();
					second.setVisible(true);
				}
			}
		});

		panelBot2.add(startButton);
		panelBot.add(panelBot1);
		panelBot.add(panelBot2);
		panelBot.add(panelBot3);
		add(panelBot, BorderLayout.SOUTH);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int c = e.getKeyCode();

		JLabel labelIn = new JLabel("Player IN.");
		JLabel labelReadyImage = new JLabel();
		ImageIcon greenTick = new ImageIcon("/Users/Miljan/Desktop/Uni/JavaProgramming/SnakeWindow/greenTick.jpg");
		labelReadyImage.setIcon(greenTick);
		labelReadyImage.setIcon(greenTick);

		switch(c){
		case KeyEvent.VK_UP:
			System.out.println("UP Event");

			playerOne = 1;

			panelMid1.removeAll();
			panelMid1.add(P1Label);
			panelMid1.add(P12Label);
			panelMid1.add(labelIn);
			panelMid1.add(labelReadyImage);
			panelMid1.repaint();
			panelMid1.revalidate();

			break;

		case KeyEvent.VK_W:
			System.out.println("W EVENT");

			playerTwo = 1;

			panelMid2.removeAll();
			panelMid2.add(P2Label);
			panelMid2.add(P21Label);
			panelMid2.add(labelIn);
			panelMid2.add(labelReadyImage);
			panelMid2.repaint();
			panelMid2.revalidate();
			break;

		case KeyEvent.VK_Y:
			System.out.println("Y EVENT");

			playerThree = 1;

			panelMid3.removeAll();
			panelMid3.add(P3Label);
			panelMid3.add(P31Label);
			panelMid3.add(labelIn);
			panelMid3.add(labelReadyImage);
			panelMid3.repaint();
			panelMid3.revalidate();
			break;

		case KeyEvent.VK_P:
			System.out.println("P EVENT");

			playerFour = 1;

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

	public static void main(String[] args) {
		logInWindow rnew = new logInWindow();

	}

}
