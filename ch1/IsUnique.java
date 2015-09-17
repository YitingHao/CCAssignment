package Chapter1;
// Assumption: all the 256 symbols in ASCII table (extended) can show up in inputs.
/* Algorithms: 
 * Store info whether specific char appears before or not in 256 bits.
 * For example, if a char with ASCII 76 appears, 76th bit will be set as 1.
 * Then next time, if the same char appears again, I just need to check whether the bit corresponding to that char is 1 or 0.
 * Running time complexity: O(n)
 * Space complexity: O(1)
*/
public class IsUnique {
	public static void main(String[] args) {
		// Two tests: the first has repeated char 'a'; while the second one does not.
		String test1 = "aberad";
		String test2 = "abgcheuy!_^@% *";
		System.out.println(checkUnique(test1));
		System.out.println(checkUnique(test2));
	}
	public static boolean checkUnique (String s)
	{
		// If the length is larger than 256, there will be definitely repeated char.
		if (s.length() > 256)
			return false;
		// Set four long variables to store info, each long has 8 bytes, that is 64 bits.
		// In order to save all info, I need 256 / 64 = 4 long variables.
		long bitVector1 = 0L;
		long bitVector2 = 0L;
		long bitVector3 = 0L;
		long bitVector4 = 0L;
		// Go through string to check each char
		for (int i = 0; i < s.length(); i ++)
		{
			int val = s.charAt(i);
			// char with ASCII from 0 to 63
			if (val < 64)
			{
				if ((bitVector1 & (1 << val)) > 0) return false;
				bitVector1 |= (1 << val);
			}
			// char with ASCII from 64 to 127
			else if (val < 128)
			{
				if ((bitVector2 & (1 << val)) > 0) return false;
				bitVector2 |= (1 << val);
			}
			// char with ASCII from 128 to 191
			else if (val < 192)
			{
				if ((bitVector3 & (1 << val)) > 0) return false;
				bitVector3 |= (1 << val);
			}
			// char with ASCII from 192 to 255
			else
			{
				if ((bitVector4 & (1 << val)) > 0) return false;
				bitVector4 |= (1 << val);
			}
		}
		return true;
	}
}