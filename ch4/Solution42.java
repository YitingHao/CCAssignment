package chapter4;
/* 
 * Assumption: the given array is sorted with unique integer elements
 * Algorithm: 
 * Find the mid element to be the root and do this recursively.
 * Running time complexity: O(log n)
 * Space complexity: O(n)
*/
import java.util.*;
public class Solution42 {
	public static void main(String[] args) {
		Solution42 sol42 = new Solution42();
		int[] values = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
		// create minimal tree
		Node binarySearchTree = sol42.createMinimum(values, 0, values.length - 1);
		sol42.printOut(binarySearchTree);
	}
	// create the minimal tree, take values from an array, define the start and end point (inclusive)
	Node createMinimum (int[] array, int start, int end)
	{
		if (start <= end)
		{
			int mid = (end - start + 1) / 2 + start;
			// set middle element to be the root
			Node root = new Node (array[mid]);
			// recursively build up the tree
			root.leftChild = createMinimum (array, start, mid - 1);
			root.rightChild = createMinimum (array, mid + 1, end);
			return root;
		}
		return null;
	}
	// function to print out tree layer by layer
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
				// print out elements in q, which are all in one layer
				// save these elements in another queue, in order to generate next layer elements
				System.out.print(q.peek().val + "   ");
				qTemp.offer(q.poll());
			}
			// get elements of next layer and put it back to queue q
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
	// node class
	class Node
	{
		int val;
		Node leftChild;
		Node rightChild;
		Node (int v) {val = v;}
	}
}