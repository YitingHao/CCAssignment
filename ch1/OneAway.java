package Chapter1;

public class OneAway {
	public static void main(String[] args) {
		String s1 = "test1";
		String s2 = "test";
		System.out.println(checkEdit(s1,s2));
	}
	public static boolean checkEdit(String s1, String s2)
	{
		if (s1 == s2) return true;
		if (Math.abs(s1.length() - s2.length()) > 1) return false;
		int minLength = Math.min(s1.length(), s2.length());
		int i = 0;
		while (i < minLength)
		{
			if (s1.charAt(i) == s2.charAt(i))
			{
				i ++;
				if ( i == minLength) return true;
			}else
			{
				if ((s1.length() == s2.length()) & (s1.substring(i + 1, minLength) == s2.substring(i + 1, minLength)) ||
					(s1.length() == minLength) & (s1.substring(i, minLength) == s2.substring(i + 1, minLength + 1))	  ||
					(s2.length() == minLength) & (s1.substring(i + 1, minLength + 1) == s2.substring(i, minLength)))
					return true;
				else break;
			}
		}
		return false;
	}
}