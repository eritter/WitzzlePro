import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;


public class Controller {
	JFrame frame;
	JPanel mainPanel, cardPanel;
	JLabel goalLabel;
	NumbersPanel numbersPanel;
	JButton newGame;
	ActionListener numberListener, otherListener;
	NumberButton[] numberButtons;
	Game game;
	Font goalFont, buttonFont;
	
	private void createAndShowGame()
	{
		goalFont = new Font("Arial", Font.PLAIN, 24);
		buttonFont = new Font("Arial", Font.PLAIN, 18);
		
		game = new Game();
		
		frame = new JFrame("WittzlePro JFram Arguement");
		//frame.setSize(300,4000);
		frame.setPreferredSize(new Dimension(225, 350));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel(new BorderLayout(0,2));
		frame.setContentPane(mainPanel);		
		
		//Middle- card
		//numberButtons = setUpCard();
		setUpCard(game.getCard());
		
		
		//Bottom- new game
		goalLabel = new JLabel("Your goal is "+game.getGoal()+".", SwingConstants.CENTER);
		goalLabel.setFont(goalFont);
		mainPanel.add(goalLabel,BorderLayout.NORTH);
		addEtcButtons();
		
		frame.pack();
		frame.setVisible(true);
	}
	private void setUpCard(int[] card)
	{
		//numberPanel = new NumberPanel(listener);
		numberListener = new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (e.getSource() instanceof NumberButton) {
	                ((NumberButton) e.getSource()).buttonClicked();
	                checkWin();
	            }
	        }
	    };
	    numbersPanel = new NumbersPanel(numberListener, card);
	    mainPanel.add(numbersPanel);
	    
	}
	
	private void addEtcButtons()
	{
		otherListener = new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (e.getSource() instanceof JButton) {
	                newGame();
	            }
	        }
	    };
	    newGame = new JButton("New Game");
	    newGame.setFont(buttonFont);
		newGame.addActionListener(otherListener);
		mainPanel.add(newGame,BorderLayout.SOUTH);
	}
//	private NumberButton[] setUpCard()
//	{
//		numberListener = new ActionListener() {
//	        @Override
//	        public void actionPerformed(ActionEvent e) {
//	            if (e.getSource() instanceof NumberButton) {
//	                ((NumberButton) e.getSource()).buttonClicked();
//	                checkWin();
//	            }
//	        }
//	    };
//	    
//	    NumberButton[] buttons = new NumberButton[9];
//		cardPanel = new JPanel(new GridLayout(3,3));
//		for (int i = 0; i < 9; i++)
//		{
//			NumberButton b = new NumberButton(i);
//			b.addActionListener(numberListener);
//			cardPanel.add(b);
//			buttons[i] = b;
//			
//		}
//		mainPanel.add(cardPanel);
//		return buttons;
//	}
//	
	private void checkWin()
	{
		int[] chosenNums = numbersPanel.getChosenNumbers();
		if (game.isSolution(chosenNums))
		{
			JOptionPane.showMessageDialog(frame, "Correct!");
			newGame();
		}
		
	}
	
	private void newGame()
	{
		int[] nums = game.newCard(frame);
		numbersPanel.setNumbers(nums);
		
		goalLabel.setText("Your goal is "+game.rollAgain()+".");
	}

//	private int[] getChosenNumbers(NumberButton[] nb)
//	{
//		ArrayList<NumberButton> arrList = new ArrayList<NumberButton>();
//		for (int i = 0; i < nb.length; i++)
//		{
//			if (nb[i].buttonCurrentlySelected())
//				arrList.add(nb[i]);
//		}
//		
//		int[] ret = new int[arrList.size()];
//		
//		for(int i = 0; i < ret.length; i++)
//			ret[i] = arrList.get(i).getNumber();
//		
//		return ret;
//	}
	
	
	public static void main(String[] args)
	{
		Controller controll = new Controller();
		controll.createAndShowGame();
	}
	
}
