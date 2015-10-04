package chapter5;
/* Assumption: The input value should be a double between 0 to 1 exclusively. 
 * Algorithms: Compare the x with fractions 1/2, 1/4, 1/16, ..., 1/2^32. If x is larger, subtract fraction,
 * append 1 to String and continue to compare with next fraction. If x is smaller, append 0. If x equals
 * fraction, after subtraction x will be 0, therefore loop will end.
 * Running time complexity: O(1)
 * Space complexity: O(1)
*/
public class Solution52 {
	public static void main (String[] args)
	{
		Solution52 sol52 = new Solution52();
		// four tests
		double x = 0.15625;
		sol52.print(x);
		x = 0.515625;
		sol52.print(x);
		x = 0.5015625;
		sol52.print(x);
		x = 0.00000000023283064365386963;
		sol52.print(x);
	}
	// function to get binary format for real number between 0 and 1
	String doubleToBinary (double x)
	{
		// x must be in the range of 0 to 1
		if (x <= 0 || x >= 1) return "Error";
		double frac = 0.5;
		StringBuilder strB = new StringBuilder();
		// break the loop either when x is 0 or length is longer than 32
		while (x > 0)
		{
			if (strB.length() >= 32)
				return "Error";
			// when x is larger or same as fraction, subtract fraction and append 1 to stringbuilder
			if (x >= frac)
			{
				strB.append(1);
				x -= frac;
			}
			// when x is smaller than fraction, and append 0 to stringbuilder
			else
				strB.append(0);
			// each time fraction needs to decrease 2 times
			frac /= 2;
		}
		return "0." + strB.toString();
	}
	// way to print out result
	void print (double x)
	{
		System.out.println("The binary format of " + x + " is: " + doubleToBinary(x));
	}
}