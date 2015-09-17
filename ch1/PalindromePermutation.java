package Chapter1;
/* Assumption: 
 * Only care about letters and ignore any other symbols that is not letters. Not case-sensitive. For example, 'A' is same as 'a'.
 * The true length is from the first char, which is not space, to the last char, which is not space.
 * Algorithms: 
 * Declare a char array, which has the same length as the string. Find the first char, which is not space.
 * According the true length and the position of first char, get the position of the last char.
 * Put char to the end of array if it is not space; put "%20" if char is space.
 * Running time complexity: O(n)
 * Space complexity: O(1)
*/
public class PalindromePermutation {
	public static void main(String[] args) {
		PalindromePermutation test = new PalindromePermutation();
		String s1 = "Tact Coa oiu";
		String s2 = " bbaa  ";
		System.out.println(test.isPermutation(s1));
		System.out.println(test.isPermutation(s2));
	}
	public boolean isPermutation (String s)
	{
		// Special cases
		if (s == null || s.length() == 0) return false;
		int bitVector = 0;
		for (int i = 0; i < s.length(); i ++)
		{
			if ((s.charAt(i) >= 65 & s.charAt(i) <= 90) || (s.charAt(i) >= 97 & s.charAt(i) <= 122))
			{
				int val = 0;
				// If it is upper case, turns it into lower case ASCII code
				if (s.charAt(i) >= 65 & s.charAt(i) <= 90)
					val = s.charAt(i) + 32 - 'a';
				else if (s.charAt(i) >= 97 & s.charAt(i) <= 122)
					val = s.charAt(i) - 'a';
				if ((bitVector & (1 << val)) == 0)
					bitVector |= (1 << val);
				else
					bitVector &= ~(1 << val);
			}
		}
		int sum = 0;
		for (int i = 0; i < 26; i ++)
			sum += (bitVector >> i) & 1;
		if (sum == 1 || sum == 0) 
			return true;
		return false;
	}
}
