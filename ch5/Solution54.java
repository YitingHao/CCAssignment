package chapter5;
/*  
 * Algorithms: To find the larger smallest number with the same number of 1 bits, we need to make sure that
 * the 0 bit flip to 1 is more significant than 1 bit flip to 0. In order to find the smallest value, the first
 * non-trailing 0 bit should be change to 1. Then rearrange the rest 1 to positions as lower as possible.
 * The method to find the smaller largest number with the same number of 1 bits is similar as above. Flip the
 * first non-trailing 1 bit to 0 to make sure it is smaller than original value. Then rearrange the rest 0 to
 * positions as lower as possible (or rearrange the rest 1 to positions as higher as possible)
 * Running time complexity: O(b)
 * Space complexity: O(1)
*/
public class Solution54 {
	public static void main (String[] args)
	{
		Solution54 sol54 = new Solution54();
		// three tests
		int x = 4320;
		sol54.print(x);
		x = 92;
		sol54.print(x);
		x = 192412;
		sol54.print(x);
	}
	// find the larger smallest number with the same number of 1 bits
	int smallestL (int x)
	{
		int bit = 0;
		int pFlipZero = 0;
		int countOne = 0;
		boolean metOne = false;
		// find the bit position of the first non-trailing 0
		while (bit < 32)
		{
			int bitVal = (x >> bit) & 1;
			// To meet with non-trailing 0, meaning need to meet with 1 first
			if (bitVal == 1)
			{
				metOne = true;
				countOne ++;
			}else if (bitVal == 0 & metOne == true)
			{
				pFlipZero = bit;
				break;
			}
			bit ++;
		}
		// flip the non-trailing 0 to 1
		x |= (1 << pFlipZero);
		// clear all the bit behind zero-flipping position to be zero
		int allOnes = ~0;
		int clearMask = allOnes << pFlipZero;
		x &= clearMask;
		// rearrange the rest ones' position and add them
		int resetMask = (1 << (countOne - 1)) - 1;
		x |= resetMask;
		return x;
	}
	// find the smaller largest number with the same number of 1 bits
	int largestS (int x)
	{
		int bit = 0;
		int pFlipOne = 0;
		int countZero = 0;
		boolean metZero = false;
		// find the bit position of the first non-trailing 1
		while (bit < 32)
		{
			int bitVal = (x >> bit) & 1;
			// To meet with non-trailing 1, meaning need to meet with 0 first
			if (bitVal == 0)
			{
				metZero = true;
				countZero ++;
			}else if (bitVal == 1 & metZero == true)
			{
				pFlipOne = bit;
				break;
			}
			bit ++;
		}
		// flip the non-trailing 1 to 0
		x &= ~(1 << pFlipOne);
		// clear all the bit behind one-flipping position to be zero
		int allOnes = ~0;
		int clearMask = allOnes << pFlipOne;
		x &= clearMask;
		// rearrange the ones' position and add them
		int right = (1 << (countZero - 1)) - 1;
		int resetMask = ~ (right | clearMask);
		x |= resetMask;
		return x;
	}
	// function to print out result
		void print (int x)
		{
			System.out.println("Input: " + x + "(Decimal)  "+ Integer.toBinaryString(x) + "(Binary)");
			int smallest = smallestL(x);
			System.out.println("The next smallest number (larger and have same number of 1 bits) is: " + smallest + "(Decimal) " + Integer.toBinaryString(smallest) + "(Binary)");
			int largest = largestS(x);
			System.out.println("The next largest number (small and have same number of 1 bits) is: " + largest + "(Decimal) " + Integer.toBinaryString(largest) + "(Binary)");
			System.out.println();
		}
}