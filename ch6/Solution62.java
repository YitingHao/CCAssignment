package chapter6;
/*  
 * Method: Calculate the success chance in both game and choose the one with higher possibilities.
 * When calculate the chance to win in the second game, it includes two cases, that win two times
 * or win three times.
*/
public class Solution62 {
	public static void main(String[] args) {
		Solution62 sol62 = new Solution62();
		// several tests
		double p = 0.5;
		sol62.print(p);
		p = 0.25;
		sol62.print(p);
		p = 0.75;
		sol62.print(p);
	}
	boolean chooseFirst (double p)
	{
		// calculate the possibility to win the second game, including the cases that win two times and win three times
		double winSecond = 3 * (1 - p) * p * p + p * p * p;
		// compare with the chance to win the first game
		if (winSecond > p)
			return false;
		else 
			return true;
	}
	// print out result
	void print (double p)
	{
		System.out.println("The possibility to make one shot is: " + p);
		if (chooseFirst(p))
			System.out.println("Should choose the first game.");
		else
			System.out.println("Should choose the second game.");
		System.out.println();
	}
}
