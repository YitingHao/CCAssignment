package chapter3;
/* 
 * Algorithm: 
 * Use a secondary stack to store the intermediate info. Every time I take out one element A from original stack,
 * I compare it with elements in the secondary stack. If it is smaller than the secondary stack, I pop out the
 * element in secondary stack to the first one, and continue comparison until I find an element that is smaller
 * than A or when secondary stack is empty. Then I retrieve the secondary stack that I poped out to the first stack
 * back the secondary stack. Repeat the same step again and again until there is no element in the original
 * stack. Then I pop all the elements in secondary stack to the original stack. The element will in increasing
 * order.
 * Running time complexity: O(n^2)
 * Space complexity: O(n)
*/
import java.util.*;
public class Solution35 {
	public static void main(String[] args) {
		Solution35 sol35 = new Solution35();
		int[] values = new int[] {21,2,34,41,5,6,73,8,94,10};
		Stack<Integer> stack = sol35.sortStack(sol35.createStack(values));
		while (!stack.isEmpty())
		{
			System.out.println(stack.pop());
		}
	}
	private Stack<Integer> sortStack (Stack<Integer> stack)
	{
		Stack<Integer> secondStack = new Stack<>();
		while (!stack.isEmpty())
		{
			// save the element you want to compare in a temp variable
			int temp = stack.pop();
			// keep tracking how many elements I pop out from secondary stack to the original one
			// so that in the future I can pop them back to secondary stack again
			int count = 0;
			while (!secondStack.isEmpty() && temp < secondStack.peek())
			{
				stack.push(secondStack.pop());
				count ++;
			}
			secondStack.push(temp);
			for (int i = 0; i < count; i ++)
			{
				secondStack.push(stack.pop());
			}
		}
		while (!secondStack.isEmpty())
		{
			stack.push(secondStack.pop());
		}
		return stack;
	}
	// Create stacks using values in an array
	private Stack<Integer> createStack (int[] array)
	{
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < array.length; i ++)
			stack.push(array[i]);
		return stack;
	}
}