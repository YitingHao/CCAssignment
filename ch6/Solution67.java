package chapter6;
/* 
 * Mathematical proof: 
 * The ratio of boys and girls should be 1:1. The chance of one family to have only child is 1/2, that is 
 * the family has a girl as their first child. The chance for one family to have two children is (1/2)^2,
 * that is the first child is boy and the second on is girl. To sum up, the chance for one family to have
 * n kids is (1/2)^n. Let calculate how many kids that a family can have on average: E=1*(1/2)+2*(1/2)^2+...
 * +n*(1/2)^n; (1/2)E=1*(1/2)^2+2*(1/2)^3+...+n*(1/2)^(n+1). Then E-(1/2)E=(1/2)+(1/2)^2+...+(1/2)^n.
 * That is E/2=1-1/2^n, E=2-1/2^(n-1). When n is close to infinity, E=2. This says on average each family
 * has two children, meaning one boy and one girl. The ratio should be 1:1.
 * 
*/
import java.util.*;
public class Solution67 {
	public static void main (String[] args)
	{
		// several test, when the number of your samples is large enough, the result is close to 0.5
		Solution67 sol67 = new Solution67();
		sol67.print(10);
		sol67.print(50);
		sol67.print(100);
		sol67.print(500);
	}
	// get ratio from data collected from several families
	double ratio (int numOfFamily)
	{
		int girls = 0;
		int boys = 0;
		for (int i = 0; i < numOfFamily; i ++)
		{
			int total = numOfChild();
			girls ++;
			boys += total - 1;
		}
		return (double)girls / (double)(girls + boys);
	}
	// randomly generate a result, return the total number children that this family has
	int numOfChild ()
	{
		int count = 0;
		boolean haveGirl = false;
		Random rm = new Random();
		while (!haveGirl)
		{
			boolean random = rm.nextBoolean();
			if (random)
			{
				haveGirl = true;
				count ++;
			}else
				count ++;
		}
		return count;
	}
	// print result
	void print (int family)
	{
		System.out.println("Collect data from " + family + " families and the ratio is " + ratio(family));
	}
}