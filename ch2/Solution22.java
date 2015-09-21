/* 
 * Assumption: All inputs are valid. For example, in the list with only two elements, k won't be larger than two.
 * Algorithm: 
 * Use two pointers. The faster pointer is k steps ahead of the slower one.
 * When fast pointer to the end (null), the slower pointer points at the element we need.
 * Running time complexity: O(n)
 * Space complexity: O(1)
*/
public class Solution22 {
	public static void main(String[] args) {
		Solution22 sol22 = new Solution22();
		int[] values = new int[] {1,2,3,4,5,6,7,8,9};
		ListNode list = sol22.createListNode(values);
		sol22.printOut(list);
		System.out.println("The 3rd element to end is: " + sol22.returnKth(list, 3).val);
		System.out.println("The 1st element to end is: " + sol22.returnKth(list, 1).val);
		System.out.println("The 5th element to end is: " + sol22.returnKth(list, 5).val);
	}
	public ListNode returnKth (ListNode head, int k)
	{
		// Special case
		if (head == null) return null;
		// Initiation
		ListNode slowP = head;
		ListNode fastP = head;
		// Move faster pointer k steps ahead
		for (int i = 0; i < k; i ++)
			fastP = fastP.next;
		// When faster point gets the end, slower pointer points at the element we want
		while (fastP != null)
		{
			fastP = fastP.next;
			slowP = slowP.next;
		}
		return slowP;
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