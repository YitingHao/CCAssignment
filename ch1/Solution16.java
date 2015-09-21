/* 
 * Assumption: The string has only uppercase and lowercase letters.
 * Algorithm: Use a counter to indicator how many times the same letter shows up. 
 * Reset it to 0 when current char is not the same as last one. 
 * Append letters and count to a StringBuilder as going along the string.
 * Running time complexity: O(n)
 * Space complexity: O(n)
*/
public class Solution16 {
	public static void main(String[] args) {
		Solution16 sol16 = new Solution16();
		System.out.println("String \"aabcccccaaa\" can be compress as: " + sol16.stringCompress("aabcccccaaa"));
		System.out.println("String \"AAabaaaaaaaccc\" can be compress as: " + sol16.stringCompress("AAabaaaaaaaccc"));
		System.out.println("String \"abc\" has no need to compress: " + sol16.stringCompress("abc"));
	}
	public String stringCompress (String s)
	{
		// Special case
		if (s == null || s.length() == 0) return null;
		// StringBUilder creates a resizable array of all the strings. 
		// There is no need to copy string back every time, which improve efficiency.
		StringBuilder result = new StringBuilder();
		int counter = 0;
		// Go through the string to check
		for( int index = 0; index < s.length();)
		{
			// If counter equals 0, meaning previous letter is different from current letter 
			// or it is the first letter in the string, then append this char
			if (counter == 0)
			{
				result.append(s.charAt(index));
				counter ++;
				index ++;
			}
			// If current char is the same as the last one, move on by increasing index
			else if (s.charAt(index) == s.charAt(index - 1))
			{
				counter ++;
				index ++;
			}
			// If current char is not the same as previous one, append counter. Then rest counter to be 0
			else if (s.charAt(index) != s.charAt(index - 1))
			{
				result.append(counter);
				counter = 0;
			}
		}
		// Do not forge to add the last counter for the last char
		if (counter != 0) result.append(counter);
		// Calculate compressed string length with original string. 
		// If compressed string is larger, return original one; Otherwise, return compressed string
		if (result.length() < s.length()) 
			return result.toString();
		else
			return s;
	}
}