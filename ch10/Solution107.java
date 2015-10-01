package chapter10;
/* 
 * Algorithms: 
 * The available memory is 1 GB, that is about 8 billion (8*10^3) bits. The input includes 4 billions,
 * roughly 2^(32) integers. Since 8*10^3 > 2^(32), we can use bit vector to contain info whether specific
 * integer has showed up. The left memory, that is 8*10^3 - 2^(32) bits, can be used to load integer data.
 * Therefore, the left memory can hold (8*10^3 - 2^32) / 32 = 115782272 integers.
 * We go through all the integers and then find one zero bit in the bitVector.
 * The corresponding integer of this zero bit has never showed up before, that is the integer we are looking for.
 * Running time complexity: O(n)
*/
public class Solution107 {
	public static void main (String[] args)
	{
		Solution107 sol107 = new Solution107();
		// Load an array that can fit into left memory (discussed above)
		// Here I generate an array, just a mock. Due to the large amount, need about ten seconds to run
		int[] data = sol107.generator(Integer.MAX_VALUE, 115782272);
		int missingInt = sol107.missingInt(data);
		System.out.println(missingInt);
	}
	// find one integer that doesn't appear before
	int missingInt (int[] array)
	{
		bitVector vector = new bitVector(134217728);
		// Set the corresponding bit to 1 for each element
		for (int i = 0; i < array.length; i ++)
			vector.setNum(array[i]);
		// The corresponding integer that bit with zero value in bitVector is the one we are looking for.
		for (int i = 0; i < vector.bitNum.length; i ++)
		{
			for (int j = 0; j < 32; j ++)
			{
				if ((vector.bitNum[i] & (1 << j)) == 0)
					return 32 * i + j;
			}
		}
		return -1;
	}
	// bitVector class: To store bit vector info, including two methods. getnum() and setnum() function
	class bitVector
	{
		int[] bitNum;
		// Constructor
		bitVector(int totalNum)
		{
			bitNum = new int[totalNum];
		}
		// Get the corresponding bit
		int getNum (int num)
		{
			int intIndex = num / 32;
			int offset = num % 32;
			return (bitNum[intIndex] & (1 << offset));
		}
		// Set the corresponding bit to 1
		void setNum (int num)
		{
			int intIndex = num / 32;
			int offset = num % 32;
			bitNum[intIndex] |= (1 << offset);
		}
	}
	// Randomly generate an huge array, with parameters maximum value and the length
	int[] generator (int maximum, int length)
	{
		int[] array = new int[length];
		for (int i = 0; i < length; i ++)
			array[i] = (int) (Math.round(Math.random() * maximum));
		return array;
	}
}