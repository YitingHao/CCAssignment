package Chapter1;

public class StringCompression {
	public static void main(String[] args) {
		String test = "AAabaaaaaaaccc";
		System.out.println(compressString(test));
	}
	public static String compressString (String s)
	{
		if (s == null || s.length() == 0) return null;
		StringBuilder result = new StringBuilder();
		int counter = 0;
		for( int i = 0; i < s.length();)
		{
			if (counter == 0)
			{
				result.append(s.charAt(i));
				counter ++;
				i ++;
			}else if (s.charAt(i) == s.charAt(i - 1))
			{
				counter ++;
				i ++;
			}else if (s.charAt(i) != s.charAt(i - 1))
			{
				result.append(counter);
				counter = 0;
			}
		}
		if (counter != 0) result.append(counter);
		if (result.length() < s.length()) 
			return result.toString();
		else
			return s;
	}
}