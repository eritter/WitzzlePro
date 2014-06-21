import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Game {
	int[] cardNumbers; //in row-major format
	ArrayList<int[]> possibleCards = new ArrayList<int[]>();
	Random r = new Random();
	int goal;
	JFrame frame;
	
	public Game()
	{
		int[] card = {5,6,1,7,4,2,8,9,3};
		cardNumbers = card;
		//cardNumbers = newCard();
		goal = Dice.getRoll();
	}
	
	public int[] getCard()
	{
		return cardNumbers;
	}
	
//	public int[] newCard()
//	{
//		int[] newNums = {0,0,0,0,0,0,0,0,0};
//		cardNumbers = newNums;
//		return cardNumbers;
//	}
	
	public int[] newCard(JFrame f)
	{
		frame = f;
		if (possibleCards.size() < 1)
		{
			setPossibleCards();
			if (possibleCards.size() < 1)
				return null;
			return newCard(f);
		}
		else
		{
			int rand = r.nextInt(possibleCards.size());
			cardNumbers = possibleCards.get(rand);
			return cardNumbers;
		}
	}
	
	private void setPossibleCards()
	{
		AllPossibleCards c = new AllPossibleCards();
		int[][] set1 = c.getCards();
		for (int i = 0; i < set1.length; i++)
			possibleCards.add(set1[i]);
		
		int[][] set2 = c.getCards2();
		for (int i = 0; i < set2.length; i++)
			possibleCards.add(set2[i]);
		
		
//		BufferedReader br;
//		try {
//			br = new BufferedReader(new FileReader("src/Cards.txt"));
//			String line = br.readLine();
//			
//			while (line != null)
//			{
//				possibleCards.add(parseCardLine(line));
//				line = br.readLine();
//			}
//			br.close();
//		}
//		catch(Exception e)
//		{
//			JOptionPane.showMessageDialog(frame, e.getMessage());
//			System.out.println("badness on setPossibleCards()");
//			System.out.println(e.getMessage());
//		}
	}
	
	public static int[] parseCardLine(String line)
	{
		int[] arr = new int[9];
		for (int i = 0; i < arr.length; i++)
			arr[i] = line.charAt(1 + (3 * i)) - '0';
		//System.out.println(line);
		return arr;
	}
	
	public int getGoal()
	{
		return goal;
	}
	
	public int rollAgain()
	{
		int newGoal = Dice.getRoll();
		if (goal == newGoal)
			return rollAgain();
		goal = newGoal;
		return goal;
	}
	
	public boolean isSolution(int[] possibleSol)
	{
		return Helper.isSolution(cardNumbers, possibleSol, goal);
	}
	
	public int[][] getSolutions()
	{
		//TO DO
		return null;
	}
	public static void main(String[] args)
	{
		Game g = new Game();
		//System.out.println(Arrays.toString(g.newCard()));
		//System.out.println(Arrays.toString(parseCardLine("[6, 4, 5, 8, 3, 7, 2, 9, 1]")));
	}
}
