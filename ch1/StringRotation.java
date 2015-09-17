package Chapter1;

public class StringRotation {
	public static void main(String[] args) {
		String s1 = "waterbottle";
		String s2 = "erbottlewat";
		System.out.println(checkRotation(s1, s2));
	}
	public static boolean checkRotation (String s1, String s2)
	{
		if (isSubstring(s1+s1, s2))
			return true;
		else 
			return false;
	}
	static boolean isSubstring (String s, String sub)
	{
		for (int i = 0; i < s.length() - sub.length() + 1; i ++)
		{
			if (s.substring(i, i + sub.length()).equals(sub))
				return true;
		}
		return false;
	}
}
