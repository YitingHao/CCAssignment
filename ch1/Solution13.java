/* Assumption: 
 * Any symbols can show up in input. Assume string has enough space at the end to hold additional chars.
 * The true length is from the first char, which is not space, to the last char, which is not space.
 * Algorithms: 
 * Declare a char array, which has the same length as the string. 
 * Find position for the first char and the last char (the first char + trueLength - 1), which are not space.
 * Put char to the end of array if it is not space; put "%20" if char is space.
 * Running time complexity: O(n)
 * Space complexity: O(n)
*/
public class Solution13 {
	public static void main(String[] args) {
		Solution13 sol13 = new Solution13();
		System.out.println("URLify \"  Mr  John Smith       \": " + sol13.URLify("  Mr  John Smith       ", 14));
		System.out.println("URLify \"Tulip +-  \": " + sol13.URLify("Tulip +-  ", 8));
	}
	public String URLify (String s, int trueLength)
	{
		// Special cases
		if (s == null || trueLength == 0) return null;
		// Char array with same length with string
		char[] strChar = new char[s.length()];
		// Check starting point
		int start = 0;
		while (s.charAt(start) == ' ') start ++;
		// Start at the last char and move forwards to check each char
		int j = s.length() - 1;
		for (int i = trueLength + start - 1; i >= start; i --)
		{
			// Not space, save it at the end of char array
			if (s.charAt(i) != ' ')
			{
				strChar[j] = s.charAt(i);
				j --;
			}
			// If it is space, save "%20" instead
			else
			{
				strChar[j] = '0';
				strChar[j-1] = '2';
				strChar[j-2] = '%';
				j -= 3;
			}
		}
		// turn char array to string
		return String.valueOf(strChar);
	}
}