package chapter4;
/* 
 * Algorithm: Check whether the tree is balanced or not recursively. If both left and right subtree
 * are balanced. We need to find out the largest value in the left subtree S(l), and the smallest value
 * S(s) in the right subtree. The root should be larger or equal to the S(l) and smaller than S(s).
 * Running time complexity: O(N), in this recursive progress, we actually need to touch each node
 * Space complexity: O(log n), due to the use of recursion, we need a stack space to recurse up to
 * depth of the tree.
*/
import java.util.*;
public class Solution45 {
	public static void main(String[] args) {
		Solution45 sol45 = new Solution45();
		// two tests
		// build tree 1
		int[] values1 = new int[] {5,3,7,2,4,6,8,1};
		Node tree1 = sol45.createTree(values1);
		// build tree 2
		int[] values2 = new int[] {6,5,7,1,8};
		Node tree2 = sol45.createTree(values2);
		sol45.printOut(tree1);
		System.out.println("Is this a binary tree? " + sol45.checkBinary(tree1));
		System.out.println();
		sol45.printOut(tree2);
		System.out.println("Is this a binary tree? " + sol45.checkBinary(tree2));
	}
	// function to check whether the tree is a binary tree or not
	boolean checkBinary(Node root)
	{
		// base cases
		if (root == null || (root.leftChild == null & root.rightChild == null)) 
			return true;
		if (checkBinary(root.leftChild) & checkBinary(root.rightChild))
		{
			Node rightMost = new Node(Integer.MIN_VALUE);
			Node leftMost = new Node(Integer.MAX_VALUE);
			// for the left subtree, get the rightmost leaf, which is the largest one in the subtree
			if (root.leftChild != null)
			{
				rightMost = root.leftChild;
				while (rightMost.rightChild != null)
					rightMost = rightMost.rightChild;
			}
			// for the right subtree, get the leftmost leaf, which is the smallest one in the subtree
			if (root.rightChild != null)
			{
				leftMost = root.rightChild;
				while (leftMost.leftChild != null)
					leftMost = leftMost.leftChild;
			}
			// compare the rightmost and leftmost value with root
			if ((rightMost.value <= root.value) & (leftMost.value > root.value))
				return true;
		}
		return false;
	}
	// create a tree based on the values in an array
	Node createTree(int[] array)
	{
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
			temp.leftChild = newNode;
			Nodes.addFirst(newNode);
			index ++;
			if (index == array.length) break;
			newNode = new Node(array[index]);
			temp.rightChild = newNode;
			Nodes.addFirst(newNode);
			index ++;
			if (index == array.length) break;
		}
		return root;
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
				System.out.print(q.peek().value + "   ");
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
	// Node class
	class Node
	{
		int value;
		Node leftChild;
		Node rightChild;
		Node (int v) {value = v;}
	}
}