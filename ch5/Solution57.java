package chapter5;
/* 
 * Algorithms: The binary number 0b10101010101010101010101010101010 equals to hexadecimal number 0xAAAAAAAA,
 * and binary number 01010101010101010101010101010101 equals to hexadecimal number 0x55555555. Therefore,
 * x does & operation with both of them can get its odd bits and even bits separately. After shifting by one
 * bit, do OR operation, then we can get result
 * Running time complexity: O(1)
 * Space complexity: O(1)
*/
public class Solution57 {
	public static void main (String[] args)
	{
		Solution57 sol57 = new Solution57();
		// two tests
		int x = 4342;
		sol57.print(x);
		x = -2345;
		sol57.print(x);
	}
	// get pair swap
	int pairSwap (int x)
	{
		return ((x & 0x55555555) << 1) | ((x & 0xAAAAAAAA) >> 1);
	}
	// function to print
	void print (int x)
	{
		System.out.println("Input: " + x + "(Decimal) " + Integer.toBinaryString(x) + "(Binary)");
		System.out.println("The result of pairwise swap: " + Integer.toBinaryString(pairSwap(x)));
		System.out.println();
	}
}
