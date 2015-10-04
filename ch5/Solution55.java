package chapter5;
/*  
 * Explanation: This statement test whether integer x is a power of 2. x can only contains one 1 bit in its
 * binary representation. Why? Let's prove this by contradiction. If x has multiple 1 bits in its binary
 * representation, for example, 10001001000100. Then after subtracting 1, the highest 1 bit won't change its
 * value. Therefore, n and n - 1 have a 1 bit in the same place, meaning n & (n - 1) is not 0.
*/
public class Solution55 {
	public static void main (String[] args)
	{
		Solution55 sol55 = new Solution55();
		// several tests
		int x = 2;
		sol55.print(x);
		x = 3;
		sol55.print(x);
		x = 256;
		sol55.print(x);
		x = 100;
		sol55.print(x);
	}
	// check whether x is a power of 2
	boolean powerOfTwo (int x)
	{
		return ((x & (x - 1)) == 0);
	}
	// print result
	void print (int x)
	{
		System.out.println("Is " + x + " a power of 2? " + powerOfTwo(x));
	}
}