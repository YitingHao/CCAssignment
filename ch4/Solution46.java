package chapter4;
/* 
 * Algorithm: There are several different cases, which need to be considered careful. 1) node has right
 * subtree; 2) node has no right child and it is the left child of its parents; 3) node has no right
 * child and it is the right child of its parents.
 * Running time complexity: O(log n), at most we need to go through each layer
 * Space complexity: O(1)
*/
import java.util.*;
public class Solution46 {
	public static void main(String[] args) {
		Solution46 sol46 = new Solution46();
		// build up a binary tree
		int[] values = new int[] {5,3,7,2,4,6,8,1};
		Node root = sol46.createTree(values);
		// print out the tree
		sol46.printTree(root);
		// three tests
		sol46.printOutNextNode(root.left);
		sol46.printOutNextNode(root.left.right);
		sol46.printOutNextNode(root.right.right);
	}
	// function to get next node in binary tree
	Node nextNode (Node node)
	{
		// if node has right subtree, than find the smallest element in the right subtree.
		// that is the next node we are looking for
		if (node.right != null)
		{
			node = node.right;
			while (node.left != null)
				node = node.left;
			return node;
		} 
		// if there is no right subtree and its parent is larger than the node (node is the left
		// child of its parent), then return the parent
		else if (node.parent.val > node.val)
		{
			return node.parent;
		} 
		// if there is no right subtree and its parent is smaller than the node (node is the right
		// child of its parent), return the leftmost ancestor.
		else
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
	// function to create tree based on the values from an array
	Node createTree(int[] array)
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
	// print out the tree layer by layer
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
	// function to print out next node, if it is the largest node, show related message
	void printOutNextNode (Node node)
	{
		Node next = nextNode(node);
		if (next != null)
			System.out.println("The next node of the node " + node.val + " is: " + next.val);
		else
			System.out.println("This node, "+ node.val + ", is the node with largest value. No next node following.");
	}
	// Node class
	class Node
	{
		int val;
		Node left;
		Node right;
		Node parent;
		// constructor
		Node(int v) {val = v;}
	}
}