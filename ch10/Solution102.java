package chapter10;
/* Assumption: A has a large enough buffer at the end to hold B
 * Algorithms: 
 * Use two pointers to point to two different elements in the array. The slower point points at the element
 * we want to compare with other elements. The fast point points at the element that we want to check whether
 * it is the anagrams of the element that slower pointer points at. If they are anagrams, exchange the element
 * that fast pointer points at with the element next to the slower pointer and move both pointer move forwards.
 * When fast pointer hits the end of array, meaning no other anagram, move slower pointer one more step forwards
 * and reset fast pointer to point at the next element of slower pointer.
 * Running time complexity in worst case: O(n^2)
 * Space complexity: O(1)
*/
import java.util.*;
public class Solution102 {
	public static void main(String[] args) {
		Solution102 sol102 = new Solution102();
		String[] strings = new String[] {"abc","abcd","bac","arh","badc"};
		System.out.print("The orginal strings: ");
		sol102.print(strings);
		// Revoke rearrange function()
		sol102.rearrange(strings);
		System.out.println();
		System.out.print("After rearrangement: ");
		sol102.print(strings);
	}
	void rearrange (String[] strings)
	{
		int sIndex = 0;
		while (sIndex < strings.length)
		{
			for (int fIndex = sIndex + 1; fIndex < strings.length; fIndex ++)
			{
				// exchange fast pointer element with the next element of slower element when they are anagrams
				if (anagrams(strings[sIndex], strings[fIndex]) & (fIndex !=  sIndex + 1))
				{
					String temp = strings[fIndex];
					strings[fIndex] = strings[sIndex + 1];
					strings[sIndex + 1] = temp;
					sIndex ++;
					fIndex ++;
				}
			}
			sIndex ++;
		}
	}
	// function to check whether two strings are anagrams or not
	boolean anagrams (String s1, String s2)
	{
		// with different length, definitely not anagrams
		if (s1.length() != s2.length()) return false;
		// Sort chars in two strings
		char[] temp1 = s1.toCharArray();
		Arrays.sort(temp1);
		char[] temp2 = s2.toCharArray();
		Arrays.sort(temp2);
		// if sorted char array is exactly same, then they are anagrams
		for (int index = 0; index < s1.length(); index ++)
		{
			if (temp1[index] != temp2[index]) return false;
		}
		return true;
	}
	// print out string[] array
	void print (String[] strings)
	{
		for (int i = 0; i < strings.length; i ++)
			System.out.print(strings[i] + "  ");
	}
}