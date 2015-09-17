package Chapter1;
/* Assumption: 
 * Any symbols can show up in input. Assume string has enough space at the end to hold additional chars.
 * The true length is from the first char, which is not space, to the last char, which is not space.
 * Algorithms: 
 * Declare a char array, which has the same length as the string. Find the first char, which is not space.
 * According the true length and the position of first char, get the position of the last char.
 * Put char to the end of array if it is not space; put "%20" if char is space.
 * Running time complexity: O(n)
 * Space complexity: O(n)
*/
public class URLify {
	public static void main(String[] args) {
		URLify test = new URLify();
		String test1 = "  Mr  John Smith       ";
		System.out.println(test.URL(test1, 14));
		String test2 = "Tulip +-  ";
		System.out.println(test.URL(test2, 8));
	}
	public String URL (String s, int trueLength)
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
			if (s.charAt(i) != ' ')
			{
				strChar[j] = s.charAt(i);
				j --;
			}
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
