package chapter2;
/* 
 * Assumption: Space is more important than running time. Save space as much as possible.
 * Algorithm: 
 * Use two points. One slow pointer points to the element that needs to be compare with.
 * Another faster pointers goes along the list to check whether there are duplications.
 * Running time complexity: O(n^2)
 * Space complexity: O(1)
*/
public class Solution21 {
	public static void main(String[] args) {
		Solution21 sol21 = new Solution21();
		int[] values = new int[] {3,4,1,7,4,2,7,9,3};
		ListNode list = sol21.createListNode(values);
		sol21.printOut(list);
		sol21.removeDups(list);
		sol21.printOut(list);
	}
	public ListNode removeDups (ListNode head)
	{
		// Special case
		if (head == null) return null;
		// Initiation
		ListNode fakeHead = new ListNode(0);
		fakeHead.next = head;
		ListNode slowP = fakeHead;
		ListNode fastP = fakeHead;
		// slowP points to the element that need to compare with others
		// fastP goes along the List to check duplication
		while(slowP.next != null)
		{
			// After each loop, slowP moves one step forward. 
			// fastP goes back to the same position as slowP, and start another check journey
			fastP = slowP.next;
			slowP = fastP;
			// fastP goes along List to check each Node whether it equals to slowP
			while(fastP.next != null)
			{
				// If not, move forwards
				if(fastP.next.val != slowP.val)
				{
					fastP = fastP.next;
				}
				// If yes, remove this element from list
				else
				{
					fastP.next = fastP.next.next;
				}
			}
		}
		return fakeHead.next;
	}
	// Method to create a list using array
	ListNode createListNode (int[] array)
	{
		if (array.length == 0) return null;
		ListNode head = new ListNode(0);
		ListNode p;
		p = head;
		for (int i = 0; i < array.length; i ++)
		{
			p.next = new ListNode(array[i]);
			p = p.next;
		}
		return head.next;
	}
	// Method to print out list
	void printOut (ListNode p)
	{
		while (p != null)
		{
			System.out.print(p.val + " -> ");
			p = p.next;
		}
		System.out.print("null");
		System.out.println();
	}
	// Definition of ListNode class
	class ListNode
	{
		int val;
		ListNode next;
		ListNode(int x) {val = x;}
	}
}