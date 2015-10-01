package chapter10;
/* 
 * Algorithms: 
 * Modify the binary search algorithm to solve this problem. Pick two elements, one element m1 is at the one 
 * third location, and the other one m2 is at two-thirds position. There is two possibilities here. 
 * If m1 < m2, meaning the rotation point is not in this range. In the case, compare with the target value. If
 * the target value lays between m1 and m2, do binary search in this range. If it is not in this range, then
 * recursively call the findIndex function to check both ranges that [0, mid1] and [mid2,end]. Another possibility
 * is that m1 > m2, meaning the rotation point is in this range. Then compare target with the m1 and element[0],
 * if it is in this range do binary search. Otherwise, do binary search in the range [m2, end]. If no such
 * element, return -1.
 * Running time complexity: O(lg n)
 * Space complexity: O(1)
*/
public class Solution103 {
	public static void main(String[] args) {
		Solution103 sol103 = new Solution103();
		int[] values = new int[] {15,16,19,20,25,1,3,4,5,7,10,14};
		System.out.print("Input: ");
		sol103.printArray(values);
		// Several tests
		sol103.printResult(5, values, 0, values.length - 1);
		sol103.printResult(15, values, 0, values.length - 1);
		sol103.printResult(14, values, 0, values.length - 1);
		sol103.printResult(20, values, 0, values.length - 1);
		sol103.printResult(11, values, 0, values.length - 1);
	}
	// find the index of the element
	int findIndex (int target, int[] array, int start, int end)
	{
		if (start == end & array[start] != target) return -1;
		int split1 = (end - start) / 3 + start;
		int split2 = (end - start) / 3 * 2 + start;
		if (target == array[split1]) return split1;
		if (target == array[split2]) return split2;
		// The first possibility that split1 < split2
		if (array[split1] < array[split2])
		{
			// in the range, do binary search
			if (target > array[split1] & target < array[split2]) 
				return binarySearch (target, array, split1, split2);
			// otherwise, search in the rest of the array
			int index = findIndex (target, array, start, split1 - 1);
			if (index == -1)
				return findIndex (target, array, split2 + 1, end);
			else 
				return index;
		}
		// The second possibility that split1 >= split2
		else
		{
			// do binary search if not lay in the range
			if (target < array[split1] & target >= array[start])
				return binarySearch (target, array, start, split1 - 1);
			else if (target > array[split2] & target <= array[end])
				return binarySearch (target, array, split2 + 1, end);
			// else recursively call function if it is in the range
			else
				return findIndex (target, array, split1, split2);
		}	
	}
	// binarySearch the target in the rang from start to end of an array
	int binarySearch (int target, int[] array, int start, int end)
	{
		if (start < end)
		{
			int mid = (end - start) / 2 + start;
			if (array[mid] == target)
				return mid;
			else if (target < array[mid])
				return binarySearch (target, array, start, mid);
			else
				return binarySearch (target, array, mid + 1, end);
		}else
		{
			if(array[start] == target)
				return start;
			else return -1;
		}
	}
	// to print out array
	void printArray (int[] array)
	{
		for (int i = 0; i < array.length; i ++)
			System.out.print(array[i] + "  ");
		System.out.println();
	}
	// function to print out result
	void printResult (int target, int[] array, int start, int end)
	{
		// revoke the findIndex function here
		int index = findIndex(target, array, start, end);
		if (index == -1)
			System.out.println("There is no such element.");
		else
			System.out.println("The index of " + target + " is: " + index);
	}
}