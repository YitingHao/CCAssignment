// Modify to build up tree using another function called createBinarySearchTree
package chapter4;
import java.util.*;
public class Solution46 {
	public static void main(String[] args) {
		Solution46 sol46 = new Solution46();
		int[] values = new int[] {5,3,7,2,4,6,8,1};
		Node root = sol46.createCompleteTree(values);
		sol46.printTree(root);
		sol46.printOutNextNode(root.left);
		sol46.printOutNextNode(root.left.right);
		sol46.printOutNextNode(root.right.right);
	}
	Node nextNode (Node node)
	{
		if (node.right != null)
		{
			node = node.right;
			while (node.left != null)
				node = node.left;
			return node;
		} else if (node.parent.val > node.val)
		{
			return node.parent;
		} else
		{
			while (node.parent != null)
			{
				if (node.parent.val <= node.val)
				{
					node = node.parent;
				} else
					break;
			}
			return node.parent;
		}
	}
	Node createCompleteTree(int[] array)
	{
		if (array.length == 0) return null;
		if (array.length == 1) return new Node(array[0]);
		LinkedList<Node> Nodes = new LinkedList<>();
		Node root = new Node(array[0]);
		root.parent = null;
		Nodes.add(root);
		int index = 1;
		while (!Nodes.isEmpty())
		{
			Node temp = Nodes.pollLast();
			Node newNode = new Node(array[index]);
			temp.left = newNode;
			newNode.parent = temp;
			Nodes.addFirst(newNode);
			index ++;
			if (index == array.length) break;
			newNode = new Node(array[index]);
			temp.right = newNode;
			newNode.parent = temp;
			Nodes.addFirst(newNode);
			index ++;
			if (index == array.length) break;
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
	void printOutNextNode (Node node)
	{
		Node next = nextNode(node);
		if (next != null)
			System.out.println("The next node of the node " + node.val + " is: " + next.val);
		else
			System.out.println("This node, "+ node.val + ", is the node with largest value. No next node following.");
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