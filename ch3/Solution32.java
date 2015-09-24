package chapter3;
/*
 * Implementation Method:
 * Use linked list to implement stack. Besides general pop(), push(), empty() function, I use another stack
 * to store info about minimum. If the new item added is smaller or equal to the element that enter stack
 * last, which is mins.peek(), push this value into minimum stack. If the pop-out item is equal to the
 * mins.peek(), pop out the last-in element in minimum stack. Besides, if stack is empty, return
 * Integer.MAX_VALUE for function pop(), peek(), and minimum().
 */
import java.util.*;
public class Solution32 {
	public static void main(String[] args) {
		// test
		Solution32 sol32 = new Solution32();
		stackMin test = sol32.new stackMin();
		test.push(6);
		System.out.println("Push one elment 6 into stack. Now the minimun is " + test.minimum());
		test.push(5);
		System.out.println("Push another elment 5 into stack. Now the minimun is " + test.minimum());
		test.push(10);
		test.push(3);
		System.out.println("Push another two elments 10 and 3 into stack. Now the minimun is " + test.minimum());
		test.pop();
		System.out.println("Pop out one elment out of stack. Now the minimun is " + test.minimum());
		test.pop();
		test.pop();
		System.out.println("Pop out two elments out of stack. Now the minimun is " + test.minimum());
	}
	class stackMin
	{
		private ListNode head = new ListNode(0);
		private ListNode p = head.next;
		// Stack to store minimum info
		private Stack<Integer> mins = new Stack<>(); 
		// Check whether the specific stack is empty or not
		boolean isEmpty()
		{
			return (p == null);
		}
		// Push item into stack. If item is smaller or equal to the last-in element in mins, push it into mins
		void push (int x)
		{
			if (mins.isEmpty() || x <= mins.peek())
			{
				mins.push(x);
			}
			head.next = new ListNode (x);
			head.next.next = p;
			p = head.next;
		}
		// Pop item out of stack. If item equals to the last-in element in mins, pop the last-in element out of mins
		int pop ()
		{
			if (isEmpty())
				return Integer.MAX_VALUE;
			else
			{
				if (p.val == mins.peek())
					mins.pop();
				head.next = p.next;
				ListNode temp = p;
				p = p.next;
				return temp.val;
			}
		}
		// Peek last-in element
		int peek ()
		{
			if (isEmpty())
				return Integer.MAX_VALUE;
			else
				return p.val;
		}
		// Get current minimum value
		int minimum ()
		{
			if (isEmpty())
				return Integer.MAX_VALUE;
			else
				return mins.peek();
		}
		// Define of node in linked list
		class ListNode
		{
			int val;
			ListNode next;
			ListNode(int x) {val = x;}
		}
	}
}