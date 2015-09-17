package Chapter2;

public class DeleteMiddleNode {
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(3);
		l1.next.next.next = new ListNode(4);
		l1.next.next.next.next = new ListNode(5);
		l1.next.next.next.next.next = new ListNode(6);
		l1.next.next.next.next.next.next = new ListNode(7);
		l1.next.next.next.next.next.next.next = new ListNode(8);
		System.out.println(deleteNode(l1.next.next.next.next));
		System.out.println("Wait");
	}
	public static boolean deleteNode (ListNode n)
	{
		if (n == null || n.next == null) return false;
		n.val = n.next.val;
		n.next = n.next.next;
		return true;
	}
}
