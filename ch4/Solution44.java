package chapter4;
/* 
 * Algorithm: Recursively check the height. To improve the efficiency, we immediately return when we find
 * any subtree is unbalanced.
 * Running time complexity: O(n), this recursive progress actually need us to go through each node
 * Space complexity: O(H), need a stack space to store the height of each layer
*/
import java.util.*;
public class Solution44 {
	public static void main(String[] args) {
		Solution44 sol44 = new Solution44();
		int[] values = new int[] {9,23,3,91,39,4,67,24,90,35,28,1,75,11};
		// create a complete tree, which must be balanced
		Node tree1 = sol44.createCompleteTree(values);
		System.out.println(sol44.checkBalance(tree1));
		// create an unbalanced tree with height 6
		Node tree2 = sol44.creatUnbalTree(values, 6);
		System.out.println(sol44.checkBalance(tree2));
		// try to create an unbalanced tree, however, since height is not large enough for 13 nodes, tree is balanced
		Node tree3 = sol44.creatUnbalTree(values, 5);
		System.out.println(sol44.checkBalance(tree3));
	}
	// return boolean value whether tree is balanced or not
	boolean checkBalance (Node root)
	{
		if (checkHeight(root.leftChild) == Integer.MIN_VALUE) 
			return false;
		if (checkHeight(root.rightChild) == Integer.MIN_VALUE) 
			return false;
		return true;
	}
	// find height for the tree, do this recursively
	int checkHeight (Node root)
	{
		// base cases
		if (root == null) 
			return 0;
		else if (root.leftChild == null & root.rightChild == null)
			return 1;
		// check left tree
		int lHeight = checkHeight(root.leftChild);
		if (lHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
		// check right tree
		int rHeight = checkHeight(root.rightChild);
		if (rHeight == Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		// if difference is greater than 1, return MIN_VALUE
		if (Math.abs(lHeight - rHeight) > 1) 
			return Integer.MIN_VALUE;
		return Math.max(lHeight, rHeight) + 1;
	}
	// create a complete tree
	Node createCompleteTree (int[] array)
	{
		// special cases
		if (array.length == 0) return null;
		if (array.length == 1) return new Node(array[0]);
		LinkedList<Node> Nodes = new LinkedList<>();
		Node root = new Node(array[0]);
		Nodes.add(root);
		int index = 1;
		while (!Nodes.isEmpty())
		{
			Node temp = Nodes.pollLast();
			Node newNode = new Node(array[index]);
			// add left child
			temp.leftChild = newNode;
			Nodes.addFirst(newNode);
			index ++;
			// check whether out of range
			if (index == array.length) break;
			newNode = new Node(array[index]);
			// add right child
			temp.rightChild = newNode;
			Nodes.addFirst(newNode);
			index ++;
			// check whether out of range
			if (index == array.length) break;
		}
		return root;
	}
	// create unbalanced with height tHeight
	Node creatUnbalTree (int[] array, int tHeight)
	{
		// special cases
		if (array.length == 0) return null;
		if (array.length == 1) return new Node(array[0]);
		Node root = new Node(array[0]);
		Node temp = root;
		LinkedList<Node> nodes = new LinkedList<>();
		nodes.add(root);
		int index = 1;
		// first go deep until we have tHeight
		while (index < tHeight && index < array.length)
		{
			temp.leftChild = new Node(array[index]);
			temp = temp.leftChild;
			index ++;
		}
		// then add elements layer by layer
		if (index < array.length)
		{
			while (!nodes.isEmpty())
			{
				temp = nodes.pollLast();
				if (temp.leftChild != null)
					nodes.addFirst(temp.leftChild);
				if (temp.leftChild == null)
				{
					temp.leftChild = new Node(array[index]);
					nodes.addFirst(temp.leftChild);
					index ++;
					if (index >= array.length) break;
				}
				if (temp.rightChild == null)
				{
					temp.rightChild = new Node(array[index]);
					nodes.addFirst(temp.rightChild);
					index ++;
					if (index >= array.length) break;
				}
			}
		}
		return root;
	}
	// Node class
	class Node
	{
		int value;
		Node leftChild;
		Node rightChild;
		Node (int v) {value = v;}
	}
}