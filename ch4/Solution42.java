package chapter4;
/* 
 * Algorithm: 
 * Do this recursively. Find the mid element to be the root.
 * Running time complexity: O(log n)
 * Space complexity: O(n)
*/
import java.util.*;
public class Solution42 {
	public static void main(String[] args) {
		Solution42 sol42 = new Solution42();
		int[] values = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
		Node binarySearchTree = sol42.createMinimum(values);
		sol42.printOut(binarySearchTree);
	}
	Node createMinimum (int[] array)
	{
		return createMinimun (array, 0, array.length - 1);
	}
	Node createMinimun (int[] array, int start, int end)
	{
		if (start <= end)
		{
			int mid = (end - start + 1) / 2 + start;
			Node root = new Node (array[mid]);
			root.leftChild = createMinimun (array, start, mid - 1);
			root.rightChild = createMinimun (array, mid + 1, end);
			return root;
		}
		return null;
	}
	void printOut (Node root)
	{
		if (root == null) return;
		int height = 0;
		Queue<Node> q = new LinkedList<>();
		Queue<Node> qTemp = new LinkedList<>();
		q.offer(root);
		while (!q.isEmpty())
		{
			System.out.print("layer " + height + ":  ");
			height ++;
			while (!q.isEmpty())
			{
				System.out.print(q.peek().val + "   ");
				qTemp.offer(q.poll());
			}
			while (!qTemp.isEmpty())
			{
				Node temp = qTemp.poll();
				if (temp.leftChild != null)
					q.offer(temp.leftChild);
				if (temp.rightChild != null)
					q.offer(temp.rightChild);
			}
			System.out.println();
		}
	}
	class Node
	{
		int val;
		Node leftChild;
		Node rightChild;
		Node (int v) {val = v;}
	}
}