package chapter5;
/* 
 * Algorithms: Use an array to save lengths of three adjacent groups. For example, 100111100001101. From lower
 * bit to higher bit, the first group has value 1 and length 1, the second group has value 0 with length 1, the
 * third group has value 1 with length 2. Therefore, the value saved in array should be the length of each group,
 * that is [1,1,2]. Previous value keeps tracking the value of the last group that saved in the array, therefore,
 * we know the pattern of the adjacent three groups. In this case, it is 1, 0, 1. Based on the pattern and length
 * of each group, we can get maximum length of three adjacent groups. Then compare it with overall maximum value.
 * If needed, update. This method can run by one pass with constant volume of space.
 * Running time complexity: O(b), proportional to the bit length of the integer
 * Space complexity: O(1)
*/
public class Solution53 {
	public static void main (String[] args)
	{
		Solution53 sol53 = new Solution53();
		// four tests
		int x = 1775;
		sol53.print(x);
		x = 51349;
		sol53.print(x);
		x = 682015;
		sol53.print(x);
		x = 4192;
		sol53.print(x);
	}
	// get maximum length by flip 0 to 1 only once
	int flipBitWin (int x)
	{
		// special cases: only includes 0 or 1
		if (x == 0) return 1;
		if (x == Integer.MIN_VALUE) return 32;
		// max: maximum length need to return
		// bitI: index of the bit, since input is an integer, this value cannot exceed 32
		// groupI: index of the group, group is defined as bits with the same value, which is either 1 or 0
		// count: record length of each group
		// preVal: the value of previous bit
		// curVal: the value of current bit
		// countArr: an array holds the number of adjacent three groups
		int max = 0;
		int bitI = 1;
		int groupI = 0;
		int count = 1;
		int preVal = x & 1;
		int curVal = 0;
		int[] countArr = new int[3];
		// Initiation
		// First, try to fill the first three groups count number in the array
		while (bitI < 32 & groupI < 3)
		{
			curVal = (x >> bitI) & 1;
			if (curVal == preVal)
			{
				count ++;
			}else
			{
				// save count to countArr and compare with other two values in the array to get current length
				countArr[groupI % 3] = count;
				preVal = curVal;
				groupI ++;
				count = 1;
			}
			bitI ++;
		}
		// if there is only two groups in the array, meaning one group with all 1 and the other one with all 0
		// there is no change that groupI is 1, cause we handle this special case that contains only 1 or 0 already at the very beginning 
		if (groupI == 2)
		{
			// if preVal is 0, it means the last group contains all 1; and the first group contains all 0
			// Be aware that preVal is not the value for the last group that saved in the array, cause
			// I change the preVal when the curVal is not the same as preVal
			if (preVal == 0) 
				return countArr[(groupI - 1) % 3] + 1;
			else 
				return countArr[(groupI - 2) % 3] + 1;
		}
		// if countArr is filled with all three elements, initialize the max value
		else
		{
			int length = 0;
			// if preVal is 1, this means the saved three groups has value 0, 1, 0.
			// so the maximum length in these three adjacent groups should be the length of the 1 group plus 1
			if (preVal == 1)
			{
				length = countArr[(groupI - 2) % 3] + 1;
			}
			// if preVal is 0, meaning the saved three groups has value 1, 0, 1.
			else
			{
				// if the middle 0 group has only one element, we can flip this 0 and connect two 1 groups
				if (countArr[(groupI - 2) % 3] == 1)
				{
					length = countArr[(groupI - 1) % 3] + countArr[(groupI - 3) % 3] + 1;
				}
				// if there are multiple 0 between two 1 groups, pick up the 1 group which has longer length, and plus one
				else
					length = Math.max(countArr[(groupI - 1) % 3], countArr[(groupI - 3) % 3] + 1);
			}
			// update max
			if (length > max)
				max = length;
		}
		// continue to check the bit value, save the length of three adjacent group. if needed, calculate the length and updata max
		while (bitI < 32)
		{
			int length = 0;
			curVal = (x >> bitI) & 1;
			if (curVal == preVal)
			{
				count ++;
			}else
			{
				countArr[groupI % 3] = count;
				// only need to re-calculate max when saved three groups have pattern like 1, 0, 1
				if (preVal == 1)
				{
					if (countArr[(groupI - 1) % 3] == 1)
						length = countArr[groupI % 3] + countArr[(groupI - 2) % 3] + 1;
					else
						length = Math.max(countArr[groupI % 3], countArr[(groupI - 2) % 3]) + 1;
					if (length > max)
						max = length;
				}
				preVal = curVal;
				groupI ++;
				count = 1;
			}
			bitI ++;
		}
		return max;
	}
	// function to print out result
	void print (int x)
	{
		System.out.println("Input: " + x + "(Decimal)  "+ Integer.toBinaryString(x) + "(Binary)");
		System.out.println("The longest length of sequence of 1s by flipping: " + flipBitWin(x));
	}
}