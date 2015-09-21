package chapter2;
/*  
 * Algorithm: Copy the next node's value to current one, and then remove the next node
 * Running time complexity: O(1)
 * Space complexity: O(1)
*/
public class Solution23 {
	public static void main(String[] args) {
		Solution23 sol23 = new Solution23();
		int[] values = new int[] {1,2,3,4,5,6,7,8,9};
		ListNode list = sol23.createListNode(values);
		sol23.printOut(list);
		// Give the address of the second element, that is 2
		sol23.deleteMiddle(list.next);
		sol23.printOut(list);
		// Give the address of the third element, that is 4 (2 has already be removed)
		sol23.deleteMiddle(list.next.next);
		sol23.printOut(list);
	}
	public void deleteMiddle (ListNode node)
	{
		if (node == null || node.next == null) return;
		// Copy value from next node
		node.val = node.next.val;
		// Remove next node
		node.next = node.next.next;
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