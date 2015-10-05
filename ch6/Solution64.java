package chapter6;
/*  
 * Method: If all ants choose the same direction, the collision won't occur. Otherwise, it will. So we
 * calculate the chance that collision won't happen first. If all ants choose clockwise direction, the 
 * chance of this is 1/(2^n). n is the number of ants and is also the number of vertex. If all ants choose
 * anti-clockwise direction, then the chance is also 1/(2^n). Therefore, the chance that collision won't
 * happen is 2^-(n-1). The possibility that collision happens is 1 - 2^-(n-1).
*/
public class Solution64 {
	public static void main (String[] args)
	{
		Solution64 sol64 = new Solution64();
		// several tests
		sol64.print(3);
		sol64.print(4);
		sol64.print(5);
	}
	// calculate the possibility that collision occurs
	double collisionP (int vertex)
	{
		// calculate the possibility that collision doesn't occur, which is all ants choose the same direction
		double p = 1;
		for (int i = 0; i < vertex - 1; i ++)
			p *= 0.5;
		// 1 minus the possibility that collision doesn't happen equals to the possibility that collision occurs
		return 1 - p;
	}
	// print result function
	void print (int vertex)
	{
		System.out.println("The possibility of collision in a " + vertex + "-vertex polygon is: " + collisionP(vertex));
	}
}