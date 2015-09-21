/* 
 * Algorithm:If s2 is a rotation of s1, meaning s2 must be a substring of s1 + s1, where s1 + s1 represents
 * the string that writes s1 twice.
 * Running time complexity: O(n)
 * Space complexity: O(n)
*/
public class Solution19 {
	public static void main(String[] args) {
		Solution19 sol19 = new Solution19();
		System.out.println("Is \"erbottlewat\" a rotation of \"watterbottle\"? " + sol19.stringRotation("watterbottle", "erbottlewat"));
		System.out.println("Is \"ingspe\" a rotation of \"spring\"? " + sol19.stringRotation("spring", "ingspe"));
	}
	public boolean stringRotation (String s1, String s2)
	{
		// If s2 is the substring of s1 + s1, then s2 is a rotation of s1.
		if (isSubstring(s1+s1, s2))
			return true;
		else 
			return false;
	}
	// Function to check whether one string is another string's substring or not
	boolean isSubstring (String s, String sub)
	{
		for (int i = 0; i < s.length() - sub.length() + 1; i ++)
		{
			if (s.substring(i, i + sub.length()).equals(sub))
				return true;
		}
		return false;
	}
}