package chapter10;
/* 
 * Algorithms: 
 * Similar to the binary search. Difference is that there is empty string in it, when middle string is empty,
 * move it to he closest element that is not empty 
 * Running time complexity: O(lg n)
 * Space complexity: O(1)
*/
public class Solution105 {
	public static void main(String[] args) {
		Solution105 sol105 = new Solution105();
		String[] strings = new String[] {"at","","","","ball","","","car","","","dad","","", "eye", "", "eyes"};
		// several tests
		sol105.printResult("at", strings);
		sol105.printResult("dad", strings);
		sol105.printResult("eye", strings);
		sol105.printResult("eyes", strings);
	}
	// find index, similar to binary search
	int findIndex (String target, String[] strings, int start, int end)
	{
		if(start <= end)
		{
			// find the first one that is not null
			while (strings[start] == "")
				start ++;
			// find the last one that is not null
			while (strings[end] == "")
				end --;
			// get the element in the middle
			int mid = (end - start) / 2 + start;
			// if middle element is null, move it to the nearest string that is not null
			while (strings[mid] == "")
				mid ++;
			// check where target value lays
			if (target == strings[mid]) return mid;
			else if (compareLess(target, strings[mid]))
				return findIndex(target, strings, start, mid - 1);
			else
				return findIndex(target, strings, mid + 1, end);
		}else
			return -1;
	}
	// compare two strings, if the first is smaller than the second one, return true; otherwise false
	boolean compareLess (String s1, String s2)
	{
		int minL = Math.min(s1.length(), s2.length());
		// in the range that each string all has elements
		for (int index = 0; index < minL; index ++)
		{
			if (s1.charAt(index) < s2.charAt(index))
				return true;
			else if (s1.charAt(index) > s2.charAt(index))
				return false;
		}
		// if s2 is a prefix of s1, s2 is smaller than s1, return false
		if (s1.length() > s2.length())
			return false;
		return true;
	}
	// print out the result
	void printResult (String str, String[] strings)
	{
		// here revoke the findIndex function
		int index = findIndex (str, strings, 0, strings.length - 1);
		if (index == -1)
			System.out.println("There is no such string in the array");
		else
			System.out.println("The index of the string \"" + str + "\" is: " + index);
	}
}