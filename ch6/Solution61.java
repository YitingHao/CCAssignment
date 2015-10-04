package chapter6;
/* 
 * Solution: The way to distinguish each bottle is that we take different numbers of pills from different
 * bottles. For example, from the 1st bottle take out 1 pill, from the 2nd take out 2 pills, from the third
 * bottle take out 3 pills, ...., and so on. Then how to know which bottle has heavy pills? If there is no
 * heavy bottle, every pill should be the same, then the total weight should be 1 + 2 + 3 + ... + 20 = 210.
 * Now we have heavy pills, then (realWeight - 210) / (1.1 - 1) should be the index of the bottle which has
 * heavy pills
*/
import java.util.*;
public class Solution61 {
	public static void main(String[] args) {
		Solution61 sol61 = new Solution61();
		// tests will generate random numbers between 1 to 20, represents different bottles
		// no input is needed
		sol61.print();
		sol61.print();
		sol61.print();
	}
	// get the scale according to the randomly generated bottle index
	double scale (int heavyBottle)
	{
		double sum = 0;
		for (int i = 1; i <= 20; i ++)
		{
			if (i != heavyBottle)
			{
				sum += i * 1;
			}else
			{
				sum += i * 1.1;
			}
		}
		return sum;
	}
	// get the index of bottle based on measurement of the scaler
	int numHeavy (double scale)
	{
		double pseudoSum = 0;
		for (int i = 1; i <= 20; i ++)
		{
			pseudoSum += 1 * i;
		}
		double num = (scale - pseudoSum) / (1.1 - 1);
		// be careful whether casting double to integer type
		if ((num - Math.floor(num)) > (Math.ceil(num) - num))
			return (int) Math.ceil(num);
		else
			return (int) Math.floor(num);
	}
	// generate a random number, which represents the index of the bottle
	// get the measurement on scaler by revoking function scale()
	// based on measurement, we get the index of the bottle using function numHeavy().
	// then compare our result with the generated bottle index, it should be the same
	void print ()
	{
		Random rn = new Random();
		int heavyBottle = 1 + rn.nextInt(20);
		double scal = scale(heavyBottle);
		int num = numHeavy(scal);
		System.out.println("The measurement on scale is: " + scal + " grams");
		System.out.println("The bottle " + num + " has heavy pills. Is it the same as random bottle generated? "  + (heavyBottle == num));
		System.out.println();
	}
}