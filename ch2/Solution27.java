/* 
 * Algorithm: 
 * If two lists intersect, it means the end node of both lists are the same node. This is method to judge
 * whether they intersect. The way to get the intersection node is: first, let longer list move several
 * steps ahead, where the amount of steps equal to the length difference between two lists. After that,
 * both list move forward until they meet at the intersection.
 * Running time complexity: O(n)
 * Space complexity: O(1)
*/
public class Solution27 {
	public static void main(String[] args) {
		Solution27 sol27 = new Solution27();
		int[] values1 = new int[] {1,2,3,4,5};
		int[] values2 = new int[] {6,7,8};
		ListNode l1 = sol27.createListNode(values1);
		ListNode l2 = sol27.createListNode(values2);
		l2.next.next.next = l1.next.next;
		sol27.printOut(l1);
		sol27.printOut(l2);
		System.out.println("The intersection of these two lists is at node: " + sol27.intersection(l1, l2).val);
	}
	public ListNode intersection (ListNode l1, ListNode l2)
	{
		// Special case
		if (l1 == null || l2 == null) return null;
		// Initiation: length1 tracks the length of the list1; length2 tracks the length of the list2
		int length1 = 1;
		int length2 = 1;
		ListNode head1 = l1;
		ListNode head2 = l2;
		// Go through the list1
		while (l1.next != null)
		{
			length1 ++;
			l1 = l1.next;
		}
		// Go through the list2
		while (l2.next != null)
		{
			length2 ++;
			l2 = l2.next;
		}
		// If the end node of both lists are the same node (reference same, not value), these two lists intersect.
		if (l1 == l2)
		{
			// Check which one is the longer list
			ListNode longer;
			ListNode shorter;
			if (length1 < length2)
			{
				longer = head2;
				shorter = head1;
			}else
			{
				longer = head1;
				shorter = head2;
			}
			// longer list move several steps ahead, where the number of steps is the difference between lengths of two lists
			for (int i = 0; i< Math.abs(length1 - length2); i ++)
				longer = longer.next;
			// Both pointers move forward until they meet at the same node
			while (longer != shorter)
			{
				longer = longer.next;
				shorter = shorter.next;
			}
			return longer;
		}else
			return null;
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