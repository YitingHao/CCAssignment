package chapter10;
/* Assumption: A has a large enough buffer at the end to hold B
 * Algorithms: 
 * Since two arrays are already sorted, it makes thing easier. We start from the end of both arrays.
 * Compare the last element they have, if the element from A is larger, decrease the index for array A by one,
 * otherwise decrease array B by one, and then put the largest one at the end of array A to store. Repeat this
 * progress until there is no element in B array. The reason why start from end is to avoid overwrite original
 * data.
 * Running time complexity: O(n)
 * Space complexity: O(1)
*/
public class Solution101 {
	public static void main(String[] args) {
		Solution101 sol101 = new Solution101();
		// Initiate two arrays
		int[] a = new int[] {1,3,5,7,9,15,19,0,0,0,0,0,0};
		int[] b = new int[] {2,4,6,8,10,12};
		System.out.print("The frst array: ");
		sol101.printArray(a, 7);
		System.out.print("The second array: ");
		sol101.printArray(b, 6);
		// Revoke merge function
		sol101.merge (a, b, 7);
		System.out.print("The mergerd one: ");
		sol101.printArray(a, 13);
	}
	void merge (int[] a, int[] b, int lengthA)
	{
		int indexA = lengthA - 1;
		int indexB = b.length - 1;
		int index = a.length - 1;
		// Until no element in B array
		while (indexB > -1)
		{
			// The element in A is larger, move pointer back by one step
			if (a[indexA] > b[indexB])
			{
				a[index] = a[indexA];
				indexA --;
			}
			// The element in B is larger, move pointer back by one step
			else
			{
				a[index] = b[indexB];
				indexB --;
			}
			index --;
		}
	}
	// function to print out array
	void printArray (int[] array, int length)
	{
		for (int i = 0; i < length; i ++)
			System.out.print(array[i] + "  ");
		System.out.println();
	}
}