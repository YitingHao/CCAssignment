/* Assumption: all the 256 symbols in ASCII table (extended) can show up in inputs.
 * Algorithms: 
 * Scan through the first string and store info in Hashtable about how many times one specific char has showed up.
 * Check the second string whether it has the same pattern.
 * Running time complexity: O(n)
 * Space complexity: O(n)
*/
import java.util.*;
public class Solution12 {
	public static void main(String[] args) {
		Solution12 sol12 = new Solution12();
		System.out.println("Are \"adghsgry\" and \"ryadghsg\" mutual permutations? " + sol12.isPermutation("adghsgry", "ryadghsg"));
		System.out.println("Are \"adghsgry\" and \"ryadgheg\" mutual permutations? " + sol12.isPermutation("adghsgry", "ryadgheg"));
	}
	public boolean isPermutation (String s1, String s2)
	{
		// Special case: If they are both null, return true
		if (s1 == null & s2 == null) 
			return true;
		// If the length is not the same, they won't be mutual permutation
		if (s1.length() == s2.length()) 
		{
			Hashtable<Character, Integer> check = new Hashtable<Character, Integer>();
			// Go through the first string and establish Hashtable;
			for (int i = 0; i < s1.length(); i ++)
			{
				// If it appears before, update show-up times by increasing one
				if (check.containsKey(s1.charAt(i)))
					check.put(s1.charAt(i), check.get(s1.charAt(i)) + 1);
				// If the char hasn't showed before, put <char, 1> into Hashtable
				else
					check.put(s1.charAt(i), 1);
			}
			// Check the second string.
			for (int i = 0; i < s2.length(); i ++)
			{
				// char must be contained in the array, otherwise return false
				if (check.containsKey(s2.charAt(i)))
				{
					// If show-up times is not 0, update by decreasing one
					check.put(s2.charAt(i), check.get(s2.charAt(i)) - 1);
					// If If show-up times is 0, remove it from Hashtable
					if (check.get(s2.charAt(i)) == 0) 
						check.remove(s2.charAt(i));
				}else 
				{
					return false;
				}
			}
			// Only when the Hashtable is empty, meaning they match
			if (check.size() == 0) return true;
		}
		return false;
	}
}