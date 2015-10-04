package chapter5;
/* 
 * Algorithms: Exclusive or operation fits to solve this problem cause here we are trying to find out the
 * number of different bit. This is exactly XOR does. If two bits are the same, it will return 0; if they
 * are different, it will return 1. Then we use an operation x & (x - 1) to remove the least significant
 * 1 in x, keep counting until x becomes 0.
 * Running time complexity: O(1)
 * Space complexity: O(1)
*/
public class Solution56 {
	public static void main (String[] args)
	{
		Solution56 sol56 = new Solution56();
		// two tests
		int a = 29;
		int b = 15;
		sol56.print(a, b);
		a = 3923;
		b = 1242;
		sol56.print(a, b);
	}
	int changeNum (int a, int b)
	{
		// get XOR result
		int c = a ^ b;
		int count = 0;
		// operation c & (c - 1) removes the least significant 1 from c. Instead of checking each bit,
		// this method is more efficient
		while (c != 0)
		{
			c = c & (c - 1);
			count ++;
		}
		return count;
	}
	void print (int a, int b)
	{
		System.out.println("Input two integers: " + a + "(or "+ Integer.toBinaryString(a) +") and " + b + "(or "+ Integer.toBinaryString(b) +")");
		System.out.println("The number of bit need to flip: " + changeNum(a,b));
		System.out.println();
	}
}