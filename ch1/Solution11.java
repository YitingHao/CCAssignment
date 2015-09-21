package chapter1;
/* Assumption: all the 256 symbols in ASCII table (extended) can show up in inputs.
 * Algorithms: 
 * Store info about whether specific char appears before or not in 256 bits.
 * For example, if a char with ASCII 76 appears, 76th bit will be set as 1.
 * Then next time, if the same char appears again, I just need to check whether the bit corresponding to that char is 1 or 0.
 * Running time complexity: O(n)
 * Space complexity: O(1)
*/
public class Solution11 {
	public static void main(String[] args) {
		Solution11 sol11 = new Solution11();
		System.out.println("The string \"aberad\" is unique string? " + sol11.isUnique("aberad"));
		System.out.println("The string \"abgcheuy!_^@% *\" is unique string? " + sol11.isUnique("abgcheuy!_^@% *"));
	}
	public boolean isUnique (String s)
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