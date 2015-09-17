package Chapter2;

public class Palindrome {
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(0);
		l1.next.next.next = new ListNode(2);
		l1.next.next.next.next = new ListNode(1);
		System.out.println(checkPalin(l1));
	}
	public static boolean checkPalin (ListNode h)
	{
		ListNode r = reverse(h);
		while (h != null)
		{
			if (r.val != h.val) 
				return false;
			h = h.next;
			r = r.next;
		}
		return true;
	}
	static ListNode reverse (ListNode head)
	{
		ListNode p1 = head;
		ListNode p2 = head.next;
		p1.next = null;
		while (p2 != null)
		{
			ListNode temp = p2.next;
			p2.next = p1;
			p1 = p2;
			p2 = temp;
		}
		return p1;
	}
}