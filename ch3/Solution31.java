package chapter3;
/*
 * Assumption: 
 * The length of stack is fixed. Each stack has the same length. The number pushed into each stack is
 * no longer than the fixed length, which means no overflow issue. The number of stack is from 0 to 2,
 * total three stacks.
 * Implementation Method:
 * Divide the array into three parts with even length. If the stack is full, push() function will catch an
 * exception with output "This stack is already full". If the stack is empty, both peek() and pop() function
 * will catch an exception with output "This stack is empty" and a return value Integer.MIN_VALUE.
 */
public class Solution31 {
	public static void main(String[] args) {
		Solution31 sol31 = new Solution31();
		stacks test = sol31.new stacks(4);
		// Operation on the first stack
		System.out.println("Put two elements in the 1st stack and then pop three times, the last pop will return a message:");
		test.push(11, 0);
		test.push(12, 0);
		test.pintPop(test.pop(0));
		test.pintPop(test.pop(0));
		test.pintPop(test.pop(0));
		System.out.println();
		// Operation on the second stack
		System.out.println("Put one element in the 2nd stack and then peek:");
		test.push(21, 1);
		System.out.println(test.peek(1));
		System.out.println();
		// Operation on the third stack
		System.out.println("Put five elements in the 3rd stack and then pop three times, the last push will return a message:");
		test.push(31, 2);
		test.push(32, 2);
		test.push(33, 2);
		test.push(34, 2);
		test.push(35, 2);
	}
	class stacks
	{
		private int NumOfStack = 3;
		private int stackLength;
		// The array, which contains three stacks
		private int[] array;
		// An int array to track the size of each stack
		private int[] size = new int[NumOfStack];
		// Constructor to set the length of each stack
		stacks(int FixedLength)
		{
			stackLength = FixedLength;
			array = new int[stackLength * NumOfStack];
		}
		// Check whether the specific stack is empty or not
		boolean isEmpty (int whichStack)
		{
			return size[whichStack] == 0;
		}
		// Check whether the specific stack is full or not
		boolean isFull (int whichStack)
		{
			return size[whichStack] == stackLength;
		}
		// Push item if the stack is not full, otherwise output signal
		void push (int item, int whichStack)
		{
			try {
				if (isFull(whichStack))
					throw new Exception();
				array[stackLength * whichStack + size[whichStack]] = item;
				size[whichStack] ++;
			} catch (Exception e)
			{
				System.out.println("This stack is already full");
			}
		}
		// Pop item when stack is not empty, otherwise return Integer.MIN_VALUE
		int pop (int whichStack)
		{
			try {
				if (isEmpty(whichStack))
					throw new Exception();
				int temp = array[stackLength * whichStack + size[whichStack] - 1];
				array[stackLength * whichStack + size[whichStack] - 1] = 0;
				size[whichStack] --;
				return temp;
			} catch (Exception e)
			{
				return Integer.MIN_VALUE;
			}
		}
		// Pop item when stack is not empty, otherwise return Integer.MIN_VALUE
		int peek (int whichStack)
		{
			try {
				if (isEmpty(whichStack))
					throw new Exception();
				return array[stackLength * whichStack + size[whichStack] - 1];
			} catch (Exception e)
			{
				System.out.println("This stack is empty");
				return Integer.MIN_VALUE;
			}
		}
		void pintPop (int pop)
		{
			if (pop == Integer.MIN_VALUE)
				System.out.println("This stack is empty");
			else
				System.out.println(pop);
		}
	}
}