package chapter3;
/*
 * Implementation Method:
 * Use two stacks to implement queue. When you do pop out, one stack give you the most recent element added;
 * while another one pop out the most previous element added.
 */
import java.util.*;
public class Solution34 {
	public static void main(String[] args) {
		Solution34 sol34 = new Solution34();
		int[] values = new int[] {11,34,2,6,23,5,10};
		System.out.print("Create the queue: ");
		MyQueue queue = sol34.new MyQueue(values);
		sol34.printOutQueue(values);
		System.out.print("Remove three elements: ");
		for (int i = 0; i < 3; i ++)
		{
			System.out.print(queue.remove() + "  ");
		}
		System.out.println();
		System.out.print("Peek the next element: ");
		System.out.print(queue.peek());
		System.out.println();
		System.out.print("Add one element 0 and print out all current queue: ");
		queue.add(0);
		while (!queue.isEmpty())
		{
			System.out.print(queue.remove() + "  ");
		}
	}
	class MyQueue
	{
		private Stack<Integer> stack1 = new Stack<>();
		private Stack<Integer> stack2 = new Stack<>();
		// Constructor
		MyQueue() {}
		MyQueue (int[] array)
		{
			for (int i = 0; i < array.length; i ++)
			{
				stack1.add(array[i]);
			}
		}
		private boolean isEmpty()
		{
			return (stack1.isEmpty() & stack2.isEmpty());
		}
		// add function
		private void add (int item)
		{
			stack1.push(item);
		}
		// remove function
		private int remove ()
		{
			if(isEmpty())
				return Integer.MIN_VALUE;
			if (stack2.isEmpty())
			{
				while (!stack1.isEmpty())
				{
					stack2.push(stack1.pop());
				}
			}
			return stack2.pop();
		}
		// peek function
		private int peek()
		{
			if(isEmpty())
				return Integer.MIN_VALUE;
			if (stack2.isEmpty())
			{
				while (!stack1.isEmpty())
				{
					stack2.push(stack1.pop());
				}
			}
			return stack2.peek();
		}
	}
	// print out queue based on the array
	void printOutQueue (int[] array)
	{
		for (int i = 0; i < array.length; i ++)
			System.out.print(array[i] + "  ");
		System.out.println();
	}
}