package Chapter2;

public class Partition {
	public static void main(String[] args) {
		ListNode l1 = new ListNode(9);
		l1.next = new ListNode(7);
		l1.next.next = new ListNode(6);
		l1.next.next.next = new ListNode(4);
		l1.next.next.next.next = new ListNode(5);
		l1.next.next.next.next.next = new ListNode(4);
		l1.next.next.next.next.next.next = new ListNode(3);
		l1.next.next.next.next.next.next.next = new ListNode(0);
		l1 = partition(l1, 5);
		System.out.println("Wait");
	}
	public static ListNode partition (ListNode h, int x)
	{
		if (h == null) return null;
		ListNode head = new ListNode(0);
		head.next = h;
		ListNode tail = h;
		ListNode p = h;
		while (p != null)
		{
			ListNode nextNode = p.next;
			if (p.val < x)
			{
				p.next = head.next;
				head.next = p;
			}else
			{
				tail.next = p;
				tail = p;
			}
			p = nextNode;
		}
		tail.next = null;
		return head.next;
	}
}
