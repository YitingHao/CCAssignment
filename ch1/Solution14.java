/* Assumption: 
 * Only care about letters and ignore any other symbols. Not case-sensitive. For example, 'A' is same as 'a'.
 * Algorithm:
 * If a string is a palindrome permutation, it holds such properties: 
 * All letters appears even times or all letters appears even times except one appears odd times.
 * Scan through the string and save info for each char ('a' to 'z', lowrcase the string) in a bitVector.
 * There are 26 different letters. One int has 32 bit. Therefore, one int variable is enough.
 * For example, if 'b' appears, I check the 1st bit (ASCII code calculation: 'b'-'a') of the bitVector.
 * If this bit is 1, set it to be 0. If it is 0, set it to 1. 
 * Therefore, if 'b' appears even times in the string, the 1st bit will be 0; if 'b' appears odd times, the corresponding bit is 1.
 * For a palindrome permutation, it only allow zero or one letter to appear odd times.
 * Therefore, to sum up every bit in the bitVector, if sum is 1 or 0. It is palindrome permutaion. Otherwise, it is not.
 * Running time complexity: O(n)
 * Space complexity: O(1)
*/
public class Solution14 {
	public static void main(String[] args) {
		Solution14 sol14 = new Solution14();
		System.out.println("Is \"Tact Coa\" a palindrome permutation? " + sol14.palindromePer("Tact Coa"));
		System.out.println("Is \" Tact Coa oiu \" a palindrome permutation? " + sol14.palindromePer(" Tact Coa oiu "));
	}
	public boolean palindromePer (String s)
	{
		// Special cases
		if (s == null || s.length() == 0) return false;
		// Turn all upperCase to lowerCase
		s = s.toLowerCase();
		int bitVector = 0;
		// Go through string
		for (int i = 0; i < s.length(); i ++)
		{
			// Check whether the char is letter or not
			if (s.charAt(i) >= 97 & s.charAt(i) <= 122)
			{
				int	val = s.charAt(i) - 'a';
				// Check corresponding bit in bitVector
				if ((bitVector & (1 << val)) == 0)
					// If corresponding bit is 0, set it to be 1
					bitVector |= (1 << val);
				else
					// If corresponding bit is 1, set it to be 0
					bitVector &= ~(1 << val);
			}
		}
		// Sum up every bit in the bitVector
		int sum = 0;
		for (int i = 0; i < 26; i ++)
			sum += (bitVector >> i) & 1;
		// If sum less than 2, it is true
		if (sum == 1 || sum == 0) 
			return true;
		return false;
	}
}