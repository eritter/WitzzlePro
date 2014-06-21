import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;


public class TestHelper {

	@Test
	public void testGetRowColDiag() {
		int[] card = {1,2,3,4,5,6,7,8,9};
		int[][] rowColDiag = Helper.getRowColDiagonal(card);
		int[][] shouldBe = {{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7}};
		
		
		Helper.sort2DArray(rowColDiag);
		Helper.sort2DArray(shouldBe);
		
		assertEquals(rowColDiag,shouldBe);
	}
	
	@Test(timeout=100)
	public void testDiceRoll()
	{
		Dice.getRoll();
		Dice.getRoll(10);
		Dice.getRoll(15);
		Dice.getRoll(7);
	}
	
	@Test
	public void testIsARowColOrDiag()
	{
		int[] card = {1,2,3,4,5,6,7,8,9};
		int[][] rowColDiags = {{1,2,3}, {4,5,6}, {7,8,9}, {1,4,7}, {2,5,8}, {3,6,9}, {1,5,9}, {3,5,7}};
		int[][] not = {{2,3,4},{4,7,8},{1,6,9},{4,5,3},{7,4,6}};
		
		for (int i = 0; i < rowColDiags.length; i++)
		{
			assertTrue(Helper.isARowColOrDiag(card, rowColDiags[i]));
		}
		
		for (int i = 0; i < not.length; i++)
		{
			assertFalse(Helper.isARowColOrDiag(card, not[i]));
		}
	}
	
	@Test
	public void testIsSolution()
	{
		int[] row543 = {5,4,3};
		int[] sols = {2, 3, 4, 5, 6, 7, 8, 11, 12, 17, 19, 23, 27, 32, 35};
		int[] notSols = {1, 9, 10, 13, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 28, 29, 30, 31, 33, 34, 36, 37};
		
		for (int i = 0; i < sols.length; i++)
		{
			System.out.println(sols[i]);
			assertTrue(Helper.isSolution(row543, sols[i]));
		}
		
		for (int i = 0; i < notSols.length; i++)
		{
			System.out.println(notSols[i]);
			assertFalse(Helper.isSolution(row543, notSols[i]));
		}
	}
	
	@Test
	public void testPermute()
	{
		int[] arr123 = {5,4,3};
		int[][] permutations = Helper.permute(arr123);
		for (int i = 0; i < permutations.length; i++)
		{
			for (int j = 0; j < permutations[i].length; j++)
			{
				//System.out.print(permutations[i][j]+" ");
			}
			//System.out.println();
		}
	}

}
