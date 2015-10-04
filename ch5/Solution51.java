package chapter5;
/* Assumption: The bits j through i have enough space to fit all of M. 
 * Algorithms: Insert M to N bit by bit. Two cases: bit value is 0 or 1. If bit value is 0, create a mask like
 * 1....1011 (0 is at the location that we want to change), and than let N do & operation with the mask. If bit
 * value is 1, create a mask like 0...0100 (1 is at the location that we want to change), and then let N do |
 * operation with the mask.
 * Running time complexity: O(M), proportional to the length of M
 * Space complexity: O(1)
 * Comments: There is actually a function to get binary string in Java, Integer.toBinaryString(i). Here I didn't
 * use it in order to practice
*/
public class Solution51 {
	public static void main (String[] args)
	{
		Solution51 sol51 = new Solution51();
		System.out.println("Original two numbers");
		int N = 34;
		int M = 7;
		sol51.printOut(N);
		sol51.printOut(M); 
		// Two tests
		System.out.println("After insertion, start at 4, end at 2: ");
		sol51.printOut(sol51.insert(N, M, 2, 4));
		System.out.println("After insertion, start at 7, end at 5: ");
		sol51.printOut(sol51.insert(N, M, 5, 7));
	}
	int insert (int N, int M, int i, int j)
	{
		// insert M into N bit by bit
		for (int bit = 0; bit < j - i + 1; bit ++)
		{
			// get the bit value of M. 
			// for example, the first lowest bit of M here is 1
			int bitVal = (M >> bit) & 1;
			// if bit value equals to 0, create a mask, and let N does & operation with it
			// for example, to insert the first lowest bit of M at position 2, the mask should be 11...11011
			if (bitVal == 0)
				N = N & (~(1 << (bit + i)));
			// if bit value equals to 1, create a mask, and let N does | operation with it
			// for example, to insert the first lowest bit of M at position 2, the mask should be 00...00100
			else
				N = N | (1 << (bit + i));
		}
		return N;
	}
	// display integer in a 32-bit binary format
	String intToBinary (int x)
	{
		StringBuilder binary = new StringBuilder();
		for (int i = 0; i < 32; i ++)
			binary.append((x >> i) & 1);
		return binary.reverse().toString();
	}
	// display the result in both decimal and binary forms
	void printOut (int x)
	{
		System.out.println(x + "(Decimal)   " + intToBinary(x) +"(Binary)");
	}
}