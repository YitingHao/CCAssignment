package chapter10;

public class Solution1010 {
	public static void main(String[] args) {
		Solution1010 sol1010 = new Solution1010();
		int[] values = new int[] {5,1,4,4,5,9,7,13,3};
		System.out.print("Stream (in order of appearance): ");
		sol1010.print(values);
		for (int i = 0; i < values.length; i ++)
		{
			sol1010.track(values[i]);
		}
		System.out.println("getRankofNumber(" + 1 + ") = " + sol1010.getRankOfNumber(1));
		System.out.println("getRankofNumber(" + 3 + ") = " + sol1010.getRankOfNumber(3));
		System.out.println("getRankofNumber(" + 4 + ") = " + sol1010.getRankOfNumber(4));
	}
	Node root = null;
	void track (int x)
	{
		if (root == null)
		{
			root = new Node(x);
		}else
		{
			root.insert(x);
		}
	}
	int getRankOfNumber (int x)
	{
		int smallerP = 0;
		Node p = root;
		while (p.val != x)
		{
			if (x < p.val)
			{
				p = p.left;
			}else
			{
				p = p.right;
				smallerP ++;
			}
		}
		if (p.right == null)
			return (p.size - 1 + smallerP);
		else
			return (p.size - p.right.size -1 + smallerP);
	}
	class Node
	{
		int val;
		int size;
		Node left;
		Node right;
		Node(int value) 
		{ 
			val = value; 
			size = 1;
		}
		void insert (int x)
		{
			if (x <= val)
			{
				if (this.left == null)
					this.left = new Node(x);
				else
					this.left.insert(x);
			}else
			{
				if (this.right == null)
					this.right = new Node(x);
				else
					this.right.insert(x);
			}
			size ++;
		}
	}
	void print (int[] array)
	{
		for (int i = 0; i < array.length; i ++)
			System.out.print(array[i] + "  ");
		System.out.println();
	}
}