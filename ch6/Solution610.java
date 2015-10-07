package chapter6;
/* 
 * Algorithms: This process can be done in seven days, just one round test is enough. The solution
 * actually has some similarity with binary search. First, I label on the bottles from 1 to 1000.
 * For test 1, I put half of bottles, that is from bottle 1 to bottle 500, on the strip. So through
 * this test, it can help me eliminate half of total bottles, narrowing poison bottle to half of bottles.
 * For test two, I choose bottles from 1 to 250, and from 500 to 750, that is half part of previous 
 * two parts. Based on the second test, I can eliminate 3/4 bottles, narrowing poison bottle into 1/4
 * bottles. How? If the first test is positive, meaning the poison bottle is from 1 to 500. Then if the
 * second test is negative, meaning the poison bottle is from 251 to 500. So with this logic, we can
 * narrow down half of its previous range based on each test result. In other words, with test 1, we 
 * narrow down to 1/2 of total bottles (that is 500); with test 2, we narrow down to 1/4 (that is 250); 
 * with test 3, we narrow down to 1/8 (that is 125); ...; with test 10, we narrow down to 1/2^10 (that is 1).
 * To sum up, if the total number of bottles is less than 1024 (2^10), the poison bottle can be found in
 * seven days. If the total number of bottles is larger than 1024, we may need extra time to narrow down
 * to one.
*/
import java.util.*;
public class Solution610 {
	public static void main (String[] args)
	{
		Solution610 sol610 = new Solution610();
		// total 1000 bottles
		sol610.print(1000);
	}
	// find poison bottle based on 10 tests' result. We can narrow down into half for each test
	int[] findPoison (int[] rangeTrack)
	{
		Random rm = new Random();
		int[] result = new int[11];
		// get random 10 test results
		for (int i = 0; i < 10; i ++)
		{
			if (rm.nextBoolean())
				result[i] = 1;
			else 
				result[i] = 0;
		}
		// change range 10 times based on each result. Why 10? Because we have 10 strips
		for (int i = 0; i < 10; i ++)
		{
			range (result[i], rangeTrack);
		}
		result[10] = rangeTrack[0];
		// result includes 10 test results and the index of poison bottle
		return result;
	}
	// change the range based on each test result
	void range (int test, int[] rangeTrack)
	{
		int mid = (rangeTrack[0] + rangeTrack[1]) / 2;
		// if test is positive, meaning the poison bottle is in the first half
		if (test == 1)
			rangeTrack[1] = mid;
		// if test is negative, meaning the poison bottle is in the last half
		else
			rangeTrack[0] = mid + 1;
	}
	// print out result
	void print (int range)
	{
		int[] rangeTrack = new int[2];
		rangeTrack[0] = 1;
		rangeTrack[1] = range;
		int[] result = findPoison(rangeTrack);
		System.out.print("Test results sequence from 1 to 10: ");
		for (int i = 0; i < 10; i ++)
		{
			if (result[i] == 0)
				System.out.print("- ");
			else
				System.out.print("+ ");
		}
		System.out.println();
		System.out.println("Based on results of 10 tests, the index of poison bottle is: " + result[10]);
	}
}