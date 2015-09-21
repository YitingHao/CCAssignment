/*  
 * Algorithm: Use three pointers. Pointer p goes through the list to check. Pointer head always points
 * to the head of the list, while pointer tail will always points to the end of the list. If the node
 * has greater or same value as given partition x, then add it at the tail. Otherwise, add it at the head.
 * Running time complexity: O(n)
 * Space complexity: O(1)
*/
public class Solution24 {
	public static void main(String[] args) {
		Solution24 sol24 = new Solution24();
		// 1st test
		int[] values1 = new int[] {9,8,7,6,5,4,3,2,1,0};
		ListNode list1 = sol24.createListNode(values1);
		System.out.println("The original list:");
		sol24.printOut(list1);
		System.out.println("The list after partition by 5:");
		ListNode partitionResult1 = sol24.partition(list1, 5);
		sol24.printOut(partitionResult1);
		// 2nd test
		int[] values2 = new int[] {20,36,91,2,5,23,961,10,35,9,7,1};
		ListNode list2 = sol24.createListNode(values2);
		System.out.println("The original list:");
		sol24.printOut(list2);
		System.out.println("The list after partition by 10:");
		ListNode partitionResult2 = sol24.partition(list2, 10);
		sol24.printOut(partitionResult2);
	}
	public ListNode partition (ListNode head, int x)
	{
		// Special case
		if (head == null) return null;
		// Initiation
		ListNode fakeHead = new ListNode(0);
		fakeHead.next = head;
		ListNode tail = head;
		ListNode p = head;
		// Pointer p goes through the list to check each node whether has larger value than x or not
		while (p != null)
		{
			// Save the next node address first
			ListNode nextNode = p.next;
			// If it is smaller than given x, add it to the head
			if (p.val < x)
			{
				p.next = fakeHead.next;
				fakeHead.next = p;
			}
			// If it is greater or equal to given x, add it to the tail
			else
			{
				tail.next = p;
				tail = p;
			}
			p = nextNode;
		}
		// Do not forget to add a null to the tail in the end, otherwise, it will form a loop
		tail.next = null;
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
