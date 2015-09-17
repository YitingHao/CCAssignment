package Chapter2;

public class SumLists {
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(3);
		ListNode l2 = new ListNode(9);
		l2.next = new ListNode(7);
		l1 = sumForward(l1, l2);
		System.out.println("Wait");
	}
	public static ListNode sumReverse (ListNode l1, ListNode l2)
	{
		ListNode p1 = new ListNode(0);
		p1.next = l1;
		ListNode p2 = new ListNode(0);
		p2.next = l2;
		int carry = 0;
		while (p1.next != null && p2.next != null)
		{
			int bitSum = (p1.next.val + p2.next.val + carry) % 10;
			carry = (p1.next.val + p2.next.val + carry) / 10;
			p1.next.val = bitSum;
			p1 = p1.next;
			p2 = p2.next;			
		}
		if (p1.next == null & p2.next != null)
			p1.next = p2.next;
		while (p1.next != null)
		{
			int bitSum = (p1.next.val + carry) % 10;
			carry = (p1.next.val + carry) / 10;
			p1.next.val = bitSum;
			p1 = p1.next;
		}
		if (carry != 0)
			p1.next = new ListNode(carry);
		return l1;
	}
	public static ListNode sumForward (ListNode l1, ListNode l2)
	{
		ListNode r1 = reverse(l1);
		ListNode r2 = reverse(l2);
		ListNode rSum = sumReverse(r1, r2);
		return reverse(rSum);
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
