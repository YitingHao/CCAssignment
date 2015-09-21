package chapter1;
/*  
 * Algorithm: The length difference between two strings is 0 or 1. Get the minimum length of both strings.
 * Compare char in strings until we find mismatch. Since it only allows one edit, the rest part should be same.
 * Running time complexity: O(n)
 * Space complexity: O(1)
*/
public class Solution15 {
	public static void main(String[] args) {
		Solution15 sol15 = new Solution15();
		System.out.println("Are \"pale\" and \"ple\" one edit away? " + sol15.oneWay("pale", "ple"));
		System.out.println("Are \"pales\" and \"pale\" one edit away? " + sol15.oneWay("pales", "pale"));
		System.out.println("Are \"pale\" and \"bale\" one edit away? " + sol15.oneWay("pale", "bale"));
		System.out.println("Are \"pale\" and \"pal\" one edit away? " + sol15.oneWay("pale","pal"));
		System.out.println("Are \"pale\" and \"bake\" one edit away? " + sol15.oneWay("pale", "bake"));
	}
	public boolean oneWay(String s1, String s2)
	{
		// Special case, no edit, they are same
		if (s1 == s2) return true;
		// If their length different is larger than one, then there is more than one edit.
		if (Math.abs(s1.length() - s2.length()) > 1) return false;
		// Get the minLength
		int minLength = Math.min(s1.length(), s2.length());
		int index = 0;
loop:	while (index < minLength)
		{
			// If at position index, char from s1 equals to char from s2, move on and increase index by one
			if (s1.charAt(index) == s2.charAt(index))
			{
				index ++;
			}
			// If at position index, chars do not equal: There are three cases
			else
			{
				switch (s1.length() - s2.length()) 
				{
					// Two strings have the same length, which is minLength.
					// If one char is already different, the rest part must be the same
					case 0: 
						if (s1.substring(index + 1, minLength).equals(s2.substring(index + 1, minLength))) 
							break loop;
						else
							return false;
					// String s1 is shorter, which has length minLength.
					// Skip the different char in String s2 and the rest in String s2 must be the same as s1
					case -1:
						if (s1.substring(index, minLength).equals(s2.substring(index + 1, minLength + 1))) 
							break loop;
						else
							return false;
					// String s1 is longer, which has length minLength + 1.
					// Skip the different char in String s1 and the rest in String s1 must be the same as s2
					case 1:
						if (s1.substring(index + 1, minLength + 1).equals(s2.substring(index, minLength)))
							break loop;
						else
							return false;
				}
			}
		}
		return true;
	}
}