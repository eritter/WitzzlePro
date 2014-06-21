import java.util.*;
public class Dice {
	public static int getRoll(int limit)
	{
		if (limit < 7)
		{
			throw new IllegalArgumentException("Limit must be 7 or larger.");
		}
		
		Random r = new Random();
		int die1 = r.nextInt(2); //0 or 1
		die1 *= 6; // 0 or 6 with equal probability
		
		int die2 = r.nextInt(6) + 1; //1-6
		
		int die3 = r.nextInt(6) * 6; //0, 6, 12, 18, 24, 30
		
		int total = die1 + die2 +die3;
		
		if(total > limit)
			return getRoll(limit);
		return total;
	}
	
	public static int getRoll()
	{
		return getRoll(36);
	}
}
