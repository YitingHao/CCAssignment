package chapter2;
/*  
 * Algorithm: 
 * Reverse the list first and then compare the reverse one and original one.
 * If they have the same value for each node, than the original list is palindrome.
 * Running time complexity: O(n)
 * Space complexity: O(n)
*/
public class Solution26 {
	public static void main(String[] args) {
		Solution26 sol26 = new Solution26();
		int[] values1 = new int[] {1,2,0,2,1};
		ListNode l = sol26.createListNode(values1);
		sol26.printOut(l);
		System.out.println("Is the above list palindrome? " + sol26.checkPalin(l));
		int[] values2 = new int[] {1,2,0,1};
		l = sol26.createListNode(values2);
		sol26.printOut(l);
		System.out.println("Is the above list palindrome? " + sol26.checkPalin(l));
	}
	public boolean checkPalin (ListNode h)
	{
		// Reverse first
		ListNode r = reverse(h);
		// Compare whether is identical
		while (h != null)
		{
			if (r.val != h.val) 
				return false;
			h = h.next;
			r = r.next;
		}
		return true;
	}
	// Reverse the list
	ListNode reverse (ListNode head)
	{
		// Special case
		if (head == null || head.next == null) return head;
		// Initiation
		ListNode r = new ListNode(head.val);
		ListNode p = head.next;
		// Add node to the head of new linked list r
		while (p != null)
		{
			ListNode newNode = new ListNode(p.val);
			newNode.next = r;
			r = newNode;
			p = p.next;
		}
		return r;
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
			System.out.print(p.val + "->");
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