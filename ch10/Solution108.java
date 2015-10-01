package chapter10;
/* 
 * Algorithms: 
 * The available memory is 4 kilobytes, that is 4*2^10 bytes = 8*4*2^10 bits = 32768 bits > 32000.
 * Therefore, I can use bit vector to store the info about whether this integer has appeared before or not.
 * Since we only have 4 kilobytes, with 32000 bits are used to store bit vector. There are still
 * 32768 - 32000 = 768 bits left. These memory can used to read part of data from file. Each time memory
 * can only read 768 / 32 = 24 integers from disk. After go through all the data, if the corresponding bit
 * is 1, meaning it appears before, then print out the duplicate.
 * Running time complexity: O(n)
*/
public class Solution108 {
	public static void main (String[] args)
	{
		Solution108 sol108 = new Solution108();
		// Generate a large array with 10000 elements and the maximum value is 32000
		int[] array = sol108.generator(32000, 10000);
//		sol108.printArray(array);
		System.out.println("The duplicates: ");
		// Print out duplicates
		sol108.printDup(array);
	}
	// Function to print duplicates
	void printDup (int[] array)
	{
		bitVector vector = new bitVector(32000);
		for (int i = 0; i < array.length; i ++)
		{
			// Check corresponding bit, if equals to 1, meaning appear before
			if (vector.getNum(array[i]) == 1)
			{
				System.out.print(array[i] + " ");
			}
			// If not equals to 1, set it to 1, meaning it shows up
			else
				vector.setNum(array[i]);
		}
	}
	// bitVector class: To store bit vector info, including two method. getnum() and setnum() function
	class bitVector
	{
		int[] bitNum;
		// Constructor
		bitVector(int totalNum)
		{
			bitNum = new int[totalNum / 32 + 1];
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
	// Print out array, didn't use here. Too many elements
	void printArray (int[] array)
	{
		for (int i = 0; i < array.length; i ++)
		{
			System.out.print(array[i] + " ");
			if (i != 0 & i % 30 == 0)
				System.out.println();
		}
		System.out.println();
	}
}