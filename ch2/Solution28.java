package chapter2;
/* 
 * Algorithm: 
 * Use two pointers to detect the loop. The faster pointer moves twice faster than the slower one. 
 * The slower pointer moves one step each time, while the faster pointer moves two steps each time.
 * If there is a loop in the list, they will meet eventually. Where do they meet? Assume there are
 * k nodes before entering the loop, and n nodes in the loop. Therefore, at the time the slower 
 * pointer enters loop, the faster point is already k steps ahead of it. Each time the slower pointer
 * moves forward, the faster pointer catch up with it by one more step. Now there are n - k steps
 * between two pointers. Therefore, after slower pointer moves another n - k steps, faster pointer will
 * catch up with it. That is the position they meet. At the time they meet, slower points moves n - k
 * steps in the loop, there are k more steps ahead to reach the beginning point, where distance is the
 * same if start from the head to the beginning point. Move one point back to the head. Then two pointers
 * start moving forward step by step with the same pace. They will meet at the beginning point of the loop.
 * Running time complexity: O(n)
 * Space complexity: O(1)
*/
public class Solution28 {
	public static void main(String[] args) {
		Solution28 sol28 = new Solution28();
		int[] values = new int[] {1,2,3,4,5,6,7,8,9,10};
		ListNode l = sol28.createLoop(values, 5);
		System.out.println("The cicular linked list is: ");
		sol28.printLoop(l, 5, 10);
		System.out.println("The beginning of the loop is: " + sol28.loopDetection(l).val);
	}
	public ListNode loopDetection (ListNode head)
	{
		ListNode showP = head;
		ListNode fastP = head;
		while (true)
		{
			// If fastP meets null element, it means there is no loop. A circular linked list won't have null
			if (fastP == null || fastP.next == null)
				return null;
			// fastP moves twice faster than the slowP
			else
			{
				showP = showP.next;
				fastP = fastP.next.next;
			}
			// They will eventually meet if there is a loop
			if (showP == fastP) break;
		}
		// Put slowP back to the start of the list, while fastP is still at the point they meet.
		// Both move forward with the same pace, the place they meet is the beginning point of the loop
		showP = head;
		while (showP != fastP)
		{
			showP = showP.next;
			fastP = fastP.next;
		}
		return fastP;
	}
	// Method to create a list with loop
	ListNode createLoop (int[] array, int k)
	{
		if (array.length == 0) return null;
		ListNode head = new ListNode(0);
		ListNode begin = new ListNode(0);
		ListNode p;
		p = head;
		for (int i = 0; i < array.length; i ++)
		{ 
			p.next = new ListNode(array[i]);
			p = p.next;
			if (i == k - 1) begin = p;
		}
		p.next = begin;
		return head.next;
	}
	// Method to print out list
	void printLoop (ListNode p, int k, int totalL)
	{
		ListNode begin = new ListNode(0);
		for (int i = 0; i < totalL; i ++)
		{
			if (i == k - 1) begin = p;
			System.out.print(p.val + "->");
			p = p.next;
		}
		System.out.print(begin.val);
		System.out.print("  [The same " + begin.val + " as before]");
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