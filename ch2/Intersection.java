package Chapter2;

public class Intersection {
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(3);
		l1.next.next.next = new ListNode(4);
		l1.next.next.next.next = new ListNode(5);
		l1.next.next.next.next.next = new ListNode(6);
		l1.next.next.next.next.next.next = new ListNode(7);
		ListNode l2 = new ListNode(8);
		l2.next = new ListNode(9);
		l2.next.next = l1.next.next.next.next;
		ListNode test = checkIntersection(l1, l2);
		System.out.println(test);
	}
	public static ListNode checkIntersection (ListNode l1, ListNode l2)
	{
		if (l1 == null | l2 == null) return null;
		int length1 = 1;
		ListNode h1 = l1;
		ListNode h2 = l2;
		int length2 = 1;
		while (l1.next != null)
		{
			length1 ++;
			l1 = l1.next;
		}
		while (l2.next != null)
		{
			length2 ++;
			l2 = l2.next;
		}
		if (l1 == l2)
		{
			ListNode longer;
			ListNode shorter;
			if (length1 < length2)
			{
				longer = h2;
				shorter = h1;
			}else
			{
				longer = h1;
				shorter = h2;
			}
			for (int i = 0; i< Math.abs(length1 - length2); i ++)
				longer = longer.next;
			while (longer != shorter)
			{
				longer = longer.next;
				shorter = shorter.next;
			}
			return longer;
		}else
			return null;
	}
}