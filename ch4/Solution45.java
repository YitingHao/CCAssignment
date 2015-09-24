package chapter4;

import java.util.LinkedList;
public class Solution45 {
	public static void main(String[] args) {
		Solution45 sol45 = new Solution45();
		int[] values1 = new int[] {5,3,7,2,4,6,8,1};
		Node tree1 = sol45.createCompleteTree(values1);
		int[] values2 = new int[] {6,5,7,1,8};
		Node tree2 = sol45.createCompleteTree(values2);
		System.out.println(sol45.checkBinary(tree1));
		System.out.println(sol45.checkBinary(tree2));
	}
	boolean checkBinary(Node root)
	{
		if (root == null || (root.leftChild == null & root.rightChild == null)) 
			return true;
		if (checkBinary(root.leftChild) & checkBinary(root.rightChild))
		{
			Node rightMost = new Node(Integer.MIN_VALUE);
			Node leftMost = new Node(Integer.MAX_VALUE);
			if (root.leftChild != null)
			{
				rightMost = root.leftChild;
				while (rightMost.rightChild != null)
					rightMost = rightMost.rightChild;
			}
			if (root.rightChild != null)
			{
				leftMost = root.rightChild;
				while (leftMost.leftChild != null)
					leftMost = leftMost.leftChild;
			}
			if ((rightMost.value <= root.value) & (leftMost.value > root.value))
				return true;
		}
		return false;
	}
	Node createCompleteTree(int[] array)
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
	class Node
	{
		int value;
		Node leftChild;
		Node rightChild;
		Node (int v) {value = v;}
	}
}