package chapter10;
/* 
 * Algorithms: 
 * Since we don't know the length, first thing is to find length in O(lg n). How? We guess the range first
 * by increasing index twice each time. Then do binary search in that range to find out the length. Once
 * we know the length, it will take use O(lgn) time to find elements using binary search.
 * Running time complexity: O(lg n)
 * Space complexity: O(1)
*/
public class Solution104 {
	public static void main(String[] args) {
		Solution104 sol104 = new Solution104();
		int[] values = new int[] {1,3,7,8,11,24,26,28,30};
		// initiation
		Listy noSizeArr = sol104.new Listy(values);
		sol104.printListy(noSizeArr);
		// several tests
		sol104.printResult(1, noSizeArr);
		sol104.printResult(8, noSizeArr);
		sol104.printResult(26, noSizeArr);
		sol104.printResult(30, noSizeArr);
		sol104.printResult(31, noSizeArr);
	}
	int indexInListy (int target, Listy noSizeArr)
	{
		int length = findLength (noSizeArr);
		// Once know the length, do binary search
		return binarySearch (target, noSizeArr, 0, length);
	}
	// function to find the length
	int findLength (Listy noSizeArr)
	{
		if (noSizeArr == null) return 0;
		// pseudoL is the length we guess, and we try to get close to real length as quick as possible
		// by increasing pseudo length twice larger than its previous value
		int pseudoL = 1;
		// find the proper range, then revoke function turnningP to get the real length
		while (noSizeArr.element(pseudoL) != -1)
			pseudoL *= 2;
		return turnningP (noSizeArr, pseudoL / 2, pseudoL);
	}
	// find real length in the range from start to end. The way to do this is similar as binary search
	int turnningP (Listy noSizeArr, int start, int end)
	{
		int mid = (end - start) / 2 + start;
		if (noSizeArr.element(mid) != -1 & noSizeArr.element(mid + 1) == -1)
			return mid;
		else if (noSizeArr.element(mid) == -1 & noSizeArr.element(mid + 1) == -1)
			return turnningP (noSizeArr, start, mid);
		else
			return turnningP (noSizeArr, mid + 1,end);
	}
	// binary search function
	int binarySearch (int target, Listy noSizeArr, int start, int end)
	{
		if (start <= end)
		{
			int mid = (end - start) / 2 + start;
			if (noSizeArr.element(mid) == target) return mid;
			else if (target < noSizeArr.element(mid))
				return binarySearch (target, noSizeArr, start, mid - 1);
			else
				return binarySearch (target, noSizeArr, mid + 1, end);
		}else
			return -1;
	}
	// Listy class, although there is an array in it, it is private. So we don't know the length of the array
	class Listy
	{
		private int[] val;
		// constructor
		Listy(int[] array)
		{
			val = new int[array.length];
			for (int i = 0; i < array.length; i ++)
			{
				val[i] = array[i];
			}
		}
		// return index, if out of range, return -1.
		int element(int i)
		{
			if (i >= val.length)
				return -1;
			else
				return val[i];
		}
	}
	// print out a Listy, here doesn't know the length of the Listy
	void printListy (Listy noSizeArr)
	{
		if (noSizeArr == null)
			System.out.println("No element in this Listy");
		else
		{
			System.out.print("Print out all Listy's elements: ");
			int i = 0;
			// check whether it is out of range, if the element(i) == -1, meaning it is out of range
			while (noSizeArr.element(i) != -1)
			{
				System.out.print(noSizeArr.element(i) + "  ");
				i ++;
			}
			System.out.println();
		}
	}
	// print out result
	void printResult (int value, Listy noSizeArr)
	{
		// revoke function indexInListy here
		int index = indexInListy(value,noSizeArr);
		if (index == -1) 
			System.out.println("There is no such element in the Listy.");
		else
			System.out.println("The index for the value " + value + " is: " + index);
	}
}