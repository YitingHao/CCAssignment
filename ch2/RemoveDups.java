package Chapter2;

public class RemoveDups {
	public static void main(String[] args) {
		ListNode l1 = new ListNode(3);
		l1.next = new ListNode(1);
		l1.next.next = new ListNode(3);
		l1.next.next.next = new ListNode(1);
		l1.next.next.next.next = new ListNode(2);
		l1.next.next.next.next.next = new ListNode(1);
		l1.next.next.next.next.next.next = new ListNode(0);
		l1.next.next.next.next.next.next.next = new ListNode(0);
		System.out.println("Wait");
	}
	public static ListNode removeDup (ListNode head)
	{
		if (head == null) return null;
		ListNode h = new ListNode(0);
		h.next = head;
		ListNode p1 = h;
		ListNode p2 = h;
		while(p1.next != null)
		{
			p2 = p1.next;
			p1 = p2;
			while(p2.next != null)
			{
				if(p2.next.val != p1.val)
				{
					p2 = p2.next;
				}else
				{
					p2.next = p2.next.next;
				}
			}
		}
		return h.next;
	}
}
