package chapter3;
/*
 * Implementation Method:
 * Use an ArrayList<Stack> to store each stack. At the time to initiate stack, users need to point out the 
 * capacity of each stack. If push large enough elements that exceed the capacity, start a new stack and save 
 * it into the ArrayList. Besides, there is also a List<Integer> variable to save the info about how many
 * elements in each stack. So the users can pop elements out of arbitrarily chosen stack without damaging
 * the performance of the whole stack.
 */
import java.util.*;
public class Solution33 {
	public static void main(String[] args) {
		Solution33 sol33 = new Solution33();
		SetOfStacks test = sol33.new SetOfStacks(3);
		int[] values = new int[] {1,2,3,4,5,6,7,8};
		test.create(test, values);
		System.out.println("Create the set of stacks using values in an array and pop two elements out of overall stack: ");
		for (int i = 0; i < 2; i ++)
			System.out.print(test.pop() + "  ");
		System.out.println();
		System.out.println("Each stack has capacity 3 and pop out element in each stack: ");
		for (int i = 1; i > -1; i --)
		{
			System.out.print(test.popAt(i) + "  ");
		}
		System.out.println();
		System.out.println("Peek the current element in overall stack: " + test.peek());
	}
	class SetOfStacks
	{
		private int capacity;
		private List<Stack> stacks= new ArrayList<>();
		private List<Integer> numTrack = new ArrayList<>();
		private SetOfStacks (int cap)
		{
			capacity = cap;
		}
		// Check whether the whole stack is empty or not
		private boolean isEmpty ()
		{
			if (stacks.size() == 1 && stacks.get(0).isEmpty())
			{
				stacks.remove(0);
				numTrack.remove(0);
			}
			return stacks.isEmpty();
		}
		// push operation
		private void push (int x)
		{
			// If the whole stack is empty or the current stack is full, create a new stack.
			// Add the new stack into the list of stacks and update the list for tracking number of each stack.
			if (isEmpty () || numTrack.get(stacks.size() - 1) == capacity)
			{
				Stack<Integer> newStack = new Stack<Integer>();
				newStack.push(x);
				stacks.add(newStack);
				numTrack.add(1);
			}
			// Else push the element into current stack and update the size of current stack
			else
			{
				numTrack.set(stacks.size() - 1, numTrack.get(stacks.size() - 1) + 1);
				stacks.get(stacks.size() - 1).push(x);
			}
		}
		
		private int pop ()
		{
			// if the whole stacks are empty, return minimum value
			if (isEmpty())
				return Integer.MIN_VALUE;
			// if the current stack is empty, remove the stack and get element from previous stack
			if (stacks.get(stacks.size() - 1).isEmpty())
			{
				numTrack.remove(stacks.size() - 1);
				stacks.remove(stacks.size() - 1);		
			}
			numTrack.set(stacks.size() - 1, numTrack.get(stacks.size() - 1) - 1);
			return (int) stacks.get(stacks.size() - 1).pop();
		}
		// peek function
		private int peek ()
		{
			if (isEmpty ())
				return Integer.MIN_VALUE;
			if (stacks.get(stacks.size() - 1).isEmpty())
			{
				numTrack.remove(stacks.size() - 1);
				stacks.remove(stacks.size() - 1);
			}
			return (int) stacks.get(stacks.size() - 1).peek();
		}
		// pop out elements in chosen stack (The follow up problem)
		private int popAt (int index)
		{
			// if the chosen stack is empty, return MIN value
			if (stacks.get(index).isEmpty())
				return Integer.MIN_VALUE;
			// if not, pop out element and update corresponding size
			numTrack.set(index, numTrack.get(index) - 1);
			return (int) stacks.get(index).pop();
		}
		// create piles of stacks using array
		private void create (SetOfStacks stacks, int[] array)
		{
			for (int i = 0; i < array.length; i ++)
			{
				stacks.push(array[i]);
			}
		}
	}
}