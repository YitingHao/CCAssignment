package chapter4;
/* 
 * Algorithm: 
 * Check the height of each node first and then let the node with larger height goes several steps above.
 * The steps it takes equals to the difference between heights of two nodes. Then both nodes goes above
 * and they will meet at their first common ancestor
 * Running time complexity: O(log n)
 * Space complexity: O(1)
*/
import java.util.*;
public class Solution48 {
	public static void main(String[] args) {
		Solution48 sol48 = new Solution48();
		int[] values = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
		Node root = sol48.createTree(values, 6);
		System.out.println("Print out the binary tree");
		sol48.printTree(root);
		Node firstCommon = sol48.firstCommon(root, root.left.left.left.left.left, root.left.right.right);
		System.out.print("The common ancestor of node 6 and node 12 is: ");
		sol48.printOutResult(firstCommon);
	}
	Node firstCommon (Node root, Node n1, Node n2)
	{
		int height1 = 0;
		int height2 = 0;
		Node p = n1;
		while (p.parent != null)
		{
			p = p.parent;
			height1 ++;
		}
		if (p != root) return null;
		p = n2;
		while (p.parent != null)
		{
			p = p.parent;
			height2 ++;
		}
		if (p != root) return null;
		int diff = Math.abs(height1 - height2);
		if (height1 > height2)
		{
			for (int i = 0; i < diff; i ++)
				n1 = n1.parent;
		}else if (height1 < height2)
		{
			for (int i = 0; i < diff; i ++)
				n2 = n2.parent;
		}
		while (n1 != n2)
		{
			n1 = n1.parent;
			n2 = n2.parent;
		}
		return n1;
	}
	Node createTree (int[] array, int tHeight)
	{
		if (array.length == 0) return null;
		if (array.length == 1) return new Node(array[0]);
		Node root = new Node(array[0]);
		Node temp = root;
		LinkedList<Node> nodes = new LinkedList<>();
		nodes.add(root);
		int index = 1;
		while (index < array.length && index < tHeight)
		{
			Node newNode = new Node(array[index]);
			temp.left = newNode;
			newNode.parent = temp;
			temp = temp.left;
			index ++;
		}
		if (index < array.length)
		{
			while (!nodes.isEmpty())
			{
				temp = nodes.pollLast();
				if (temp.left != null)
					nodes.addFirst(temp.left);
				if (temp.left == null)
				{
					Node newNode = new Node(array[index]);
					temp.left = newNode;
					newNode.parent = temp;
					nodes.addFirst(temp.left);
					index ++;
					if (index >= array.length) break;
				}
				if (temp.right == null)
				{
					Node newNode = new Node(array[index]);
					temp.right = newNode;
					newNode.parent = temp;
					nodes.addFirst(temp.right);
					index ++;
					if (index >= array.length) break;
				}
			}
		}
		return root;
	}
	void printTree(Node root)
	{
		if (root == null) return;
		int height = 0;
		Queue<Node> current = new LinkedList<>();
		Queue<Node> next = new LinkedList<>();
		current.offer(root);
		while (!current.isEmpty())
		{
			System.out.print("layer " + height + ": ");
			height ++;
			while(!current.isEmpty())
			{
				Node temp = current.poll();
				System.out.print(temp.val + "  ");
				if (temp.left != null)
					next.offer(temp.left);
				if (temp.right != null)
					next.offer(temp.right);
			}
			current = next;
			next = new LinkedList<>();
			System.out.println();
		}
	}
	void printOutResult(Node firstCommon)
	{
		if (firstCommon == null)
			System.out.println("Not all these two nodes are in the tree. There is no common ancestor.");
		else
			System.out.println(firstCommon.val);
	}
	class Node
	{
		int val;
		Node left;
		Node right;
		Node parent;
		Node(int v) {val = v;}
	}
}