package Chapter2;

public class ReturnKthtoLast {
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(3);
		l1.next.next.next = new ListNode(4);
		l1.next.next.next.next = new ListNode(5);
		l1.next.next.next.next.next = new ListNode(6);
		l1.next.next.next.next.next.next = new ListNode(7);
		l1.next.next.next.next.next.next.next = new ListNode(8);
		System.out.println(findKth(l1, 8));
	}
	public static int findKth (ListNode head, int k)
	{
		ListNode p1 = head;
		ListNode p2 = head;
		for (int i = 0; i < k - 1; i ++)
			p2 = p2.next;
		while (p2.next != null)
		{
			p1 = p1.next;
			p2 = p2.next;
		}
		return p1.val;
	}
}
