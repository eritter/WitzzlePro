import static org.junit.Assert.assertTrue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class Helper {
	public static boolean isSolution(int[] card, int[] sol, int goal)
	{
		if (!isARowColOrDiag(card, sol))
			return false;
		else return isSolution(sol, goal);
	}
	
	public static boolean isSolution(int[] sol, int goal)
	{
		int[][] permutations = permute(sol);
		for (int p = 0; p < permutations.length; p++)
		{
			int[] s = permutations[p];
			//System.out.println(Arrays.toString(s));
			for (int firstSign = 0; firstSign < 4; firstSign++)
			{
				for (int secondSign = 0; secondSign < 4; secondSign++)
				{
					//System.out.println(Arrays.toString(s));
					int phrase1 = mathHelper(s[0], s[1], firstSign);
					int phrase2 = mathHelper(s[1], s[2], secondSign);
					if (phrase1 != -1 && mathHelper(phrase1, s[2], secondSign) == goal)
						return true;
					if (phrase2 != -1 &&mathHelper(s[0], phrase2, firstSign) == goal)
						return true;
				}
			}
		}
		return false;
	}
	public static void main(String[] args)
	{
		ArrayList<int[]> cards = getAllPossibleCards();
		int count = 0;
		for (int[] c: cards)
		{
			count++;
			System.out.println(Arrays.toString(c));
		}
		System.out.println("done w/ "+count);
	}
	
	private static int mathHelper(int a, int b, int op)
	{
		if (op == 0)
			return a + b;
		if (op == 1)
			return a - b;
		if (op == 2)
			return a * b;
		if (op == 3)
		{
			if (a % b == 0)
				return a / b;
			else return -1;
		}
		throw new IllegalArgumentException("op must be 0-3.");
	}
	
	public static ArrayList<int[]> getAllPossibleCards()
	{
		ArrayList<int[]> cards = new ArrayList<int[]>();
		int[] nine = {1,2,3,4,5,6,7,8,9};
		int[][] allPossibilities = permute(nine);
		for (int i = 0; i < allPossibilities.length; i++)
		{
			if(isAValidCard(allPossibilities[i]))
				cards.add(allPossibilities[i]);
		}
		return cards;
	}
	public static boolean isAValidCard(int[] card)
	{
		for(int i = 0; i < 36; i++)
		{
			if (!hasSolution(card, i))
				return false;
		}
		return true;
	}
	
	private static boolean hasSolution(int[] card, int goal)
	{
		int[][] rowColDiag = getRowColDiagonal(card);
		for (int i = 0; i < rowColDiag.length; i++)
		{
			if(isSolution(rowColDiag[i],goal))
				return true;
		}
		return false;
	}
	
	public static boolean isARowColOrDiag(int card[], int[] possible)
	{
		if (possible.length != 3)
			return false;
		int[] sortedPossible = Arrays.copyOf(possible, possible.length);
		Arrays.sort(sortedPossible);
		int[][] sortedRCD = sort2DArray(getRowColDiagonal(card));
		for (int i=0; i< sortedRCD.length; i++)
		{
			boolean same = true;
			for (int j = 0; j < 3; j++)
			{
				if (sortedRCD[i][j] != sortedPossible[j])
					same = false;
			}
			if (same)
				return true;
			same=true;
		}
		return false;
	}
	
	public static int[][] getRowColDiagonal(int[] card)
	{
		int[][] result = new int[8][3];
		
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				result[i][j] = card[(i * 3) + j]; //rows
				result[i + 3][j] = card[(j * 3) + i]; //columns
			}
		}
		
		for (int j = 0; j < 3; j++)
		{
			result[6][j] = card[(j * 3) + j];
			result[7][j] = card[(j * 3) - j + 2]; //diagonals
		}
		return result;
	}
	
	public static int[][] sort2DArray(int[][] arr)
	{
		for (int i = 0; i < arr.length; i++)
		{
			Arrays.sort(arr[i]);
		}
		java.util.Arrays.sort(arr, new java.util.Comparator<int[]>() {
			public int compare(int[] a, int[] b)
			{
				for (int i = 0; i < a.length && i < b.length; i++)
				{
					if (Integer.compare(a[0], b[0]) != 0)
						return Integer.compare(a[0], b[0]);
				}
				return 0;
			}
		});
		return arr;
	}
	
	/*
	 *   
	 * 
	 * OTHER PEOPLE'S CODE
	 * 
	 * 
	 */
	

	//http://www.programcreek.com/2013/02/leetcode-permutations-java/
	//changed to return arrays
	public static int[][] permute(int[] num) {
		
		int sizeOfNum = num.length;
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	 
		//start from an empty list
		result.add(new ArrayList<Integer>());
	 
		for (int i = 0; i < num.length; i++) {
			//list of list in current iteration of the array num
			ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();
	 
			for (ArrayList<Integer> l : result) {
				// # of locations to insert is largest index + 1
				for (int j = 0; j < l.size()+1; j++) {
					// + add num[i] to different locations
					l.add(j, num[i]);
	 
					ArrayList<Integer> temp = new ArrayList<Integer>(l);
					current.add(temp);
	 
					//System.out.println(temp);
	 
					// - remove num[i] add
					l.remove(j);
				}
			}
	 
			result = new ArrayList<ArrayList<Integer>>(current);
		}
		
		ArrayList<int[]> arrList = new ArrayList<int[]>();
		for (int i = 0; i < result.size(); i++)
		{
			if(result.get(i).size() == sizeOfNum)
			{
				ArrayList<Integer> temp = result.get(i);
				int[] tempArr = new int[sizeOfNum];
				for(int j = 0; j < sizeOfNum; j++)
				{
					tempArr[j] = temp.get(j);
				}
				arrList.add(tempArr);
			}
		}
		
		
		int[][] arr = new int[arrList.size()][];
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = arrList.get(i);
		}
		return arr;
	}
}
