package Chapter2;

public class LoopDetection {
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(3);
		l1.next.next.next = new ListNode(4);
		l1.next.next.next.next = new ListNode(5);
		l1.next.next.next.next.next = new ListNode(6);
		l1.next.next.next.next.next.next = new ListNode(7);
		l1.next.next.next.next.next.next.next = l1.next.next.next;
		ListNode result = findStart(l1);
		System.out.println(result);
	}
	public static ListNode findStart (ListNode head)
	{
		ListNode h = head;
		ListNode p1 = head;
		ListNode p2 = head;
		while (true)
		{
			if (p2 == null || p2.next == null)
				return null;
			else
			{
				p1 = p1.next;
				p2 = p2.next.next;
			}
			if (p1 == p2) break;
		}
		p1 = h;
		while (p1 != p2)
		{
			p1 = p1.next;
			p2 = p2.next;
		}
		return p2;
	}
}
