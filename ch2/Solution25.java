package chapter2;
/* 
 * Algorithm: 
 * For reverse summation: Start from the heads of two lists, add two digits up, change carry if needed,
 * 						  and then save the summation digit in one of the list, which save space. Be aware
 * 						  the lists may do not have the same length. To deal with this issue, go through
 * 						  the part that both lists are not null, then link the remaining part to the list
 * 						  where we store the result.
 * For forward summation: Reverse the linked lists before, and then add them add just as reverse summation.
 * Both Running time complexity: O(n)
 * Both Space complexity: O(1)
*/
public class Solution25 {
	public static void main(String[] args) {
		Solution25 sol25 = new Solution25();
		// Test for adding two reverse linked lists
		int[] reverse1 = new int[] {9,8,7,6,9};
		int[] reverse2 = new int[] {1,8,7,9};
		ListNode r1 = sol25.createListNode(reverse1);
		ListNode r2 = sol25.createListNode(reverse2);
		System.out.println("The original two reverse linked list: ");
		sol25.printOut(r1);
		sol25.printOut(r2);
		ListNode rSum = sol25.sumReverse(r1, r2);
		System.out.println("The revers sum linked list: ");
		sol25.printOut(rSum);
		System.out.println();
		// Test for adding two reverse linked lists
		int[] forward1 = new int[] {9,8,7,6,9};
		int[] forward2 = new int[] {1,8,7,9};
		ListNode f1 = sol25.createListNode(forward1);
		ListNode f2 = sol25.createListNode(forward2);
		System.out.println("The original two forward linked list: ");
		sol25.printOut(f1);
		sol25.printOut(f2);
		ListNode fSum = sol25.sumForward(f1, f2);
		System.out.println("The forward sum linked list: ");
		sol25.printOut(fSum);
	}
	public ListNode sumReverse (ListNode l1, ListNode l2)
	{
		// Initiation: p1 points to the head l1, p2 points to the head l2
		ListNode p1 = new ListNode(0);
		p1.next = l1;
		ListNode p2 = new ListNode(0);
		p2.next = l2;
		int carry = 0;
		// The result is stored in the list1, therefore no extra space is needed.
		while (p1.next != null && p2.next != null)
		{
			int bitSum = (p1.next.val + p2.next.val + carry) % 10;
			carry = (p1.next.val + p2.next.val + carry) / 10;
			p1.next.val = bitSum;
			p1 = p1.next;
			p2 = p2.next;			
		}
		// If list2 is longer than list1, remove the elements to list1
		// If list1 is longer than list2, no action, cause the final result will be stored in list1
		if (p1.next == null & p2.next != null)
			p1.next = p2.next;
		while (p1.next != null)
		{
			int bitSum = (p1.next.val + carry) % 10;
			carry = (p1.next.val + carry) / 10;
			p1.next.val = bitSum;
			p1 = p1.next;
		}
		// Do not forget the carry, there is possibility the result list is longer than both list1 and list2
		if (carry != 0)
			p1.next = new ListNode(carry);
		return l1;
	}
	public ListNode sumForward (ListNode l1, ListNode l2)
	{
		// Reverse both lists first
		l1 = reverse(l1);
		l2 = reverse(l2);
		// Then add them together reversely
		ListNode rSum = sumReverse(l1, l2);
		return reverse(rSum);
	}
	// Reverse the list
	ListNode reverse (ListNode head)
	{
		// Special case
		if (head == null || head.next == null) return head;
		// Initiation
		ListNode p1 = head;
		ListNode p2 = head.next;
		p1.next = null;
		// Remove each next node to the head
		while (p2 != null)
		{
			ListNode nextNode = p2.next;
			p2.next = p1;
			p1 = p2;
			p2 = nextNode;
		}
		return p1;
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