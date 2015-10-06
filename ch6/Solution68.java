package chapter6;
/* 
 * Method: In order to minimize the number of drops for the worst case, we need to make a balance
 * between different cases. In other words, our goal is to keep the total number of drops as
 * consistent as possible no matter egg 1 breaks on the first drop or the last drop. A perfect
 * balanced system should be the drops of egg 1 + the drops of egg 2 is a constant. We know that
 * if egg 1 move one more step, then egg 2 is allowed one less step. For example, if egg 1 is
 * dropped on floor 10, and then floor 20. The potential move for egg 2 is 9 steps. Then next time
 * when egg 1 drop again, we must reduce potential move for egg 2 to 8 steps. Therefore, egg 1 need
 * to start at floor x, then go up by x-1 floors, then go up by x-2 floors, until gets to the total
 * floor. That is, x+(x-1)+(x-2)+...+1 = # of floors. This is how I get x in the firstInterval function.
 * Besides, the result may be not an integer. Need to ceil the double value, otherwise we cann't
 * cover all the floors in maximum x drops.
 * 
*/
import java.util.*;
public class Solution68 {
	public static void main (String[] args)
	{
		Solution68 sol68 = new Solution68();
		// two tests with different floor number
		sol68.print(100);
		sol68.print(50);
	}
	// return an array includes 1) the floor index where eggs will be break if they are dropped above;
	// 2) the drops we tried in this case
	int[] findBreakPoint (int floors)
	{
		int[] result = new int[2];
		int count = 0;
		int interval = firstInterval(floors);
		int egg1 = interval;
		int egg2 = 0;
		// randomly generate a number between 1 to floors, which represents the breakingPoint
		Random rm = new Random();
		int breakFloor = 1 + rm.nextInt(floors);
		// drop egg1 to get range of the breaking point
		while (egg1 < breakFloor)
		{
			count ++;
			interval --;
			egg1 += interval;
		}
		count ++;
		// drop egg2 in the range, increasing one floor each time
		egg2 = egg1 - interval + 1;
		while (egg2< breakFloor)
		{
			count ++;
			egg2 ++;
		}
		result[0] = egg2;
		result[1] = ++ count;
		return result;
	}
	// based on given floors, calculate the first interval. 
	// That is to solve equation, x(x+1)/2= floors. Return the supremum integer
	int firstInterval (int floors)
	{
		double delta = Math.sqrt((double)(1 + 8 * floors));
		double result = (delta - 1) / 2;
		return (int) Math.ceil(result);
	}
	// print result, revoke both findBreakPoint() and firstInterval() functions
	void print (int floors)
	{
		int[] result = findBreakPoint(floors);
		System.out.println("Eggs will be break if throw above " + result[0] + "th floor out of " + floors + " floors.");
		System.out.println("The first interval we tried is: " + firstInterval(floors));
		System.out.println("The number of drops we tried in this case is: " + result[1]);
		System.out.println();
	}
}