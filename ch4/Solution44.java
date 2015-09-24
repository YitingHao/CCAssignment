package chapter4;

import java.util.LinkedList;
public class Solution44 {
	public static void main(String[] args) {
		Solution44 sol44 = new Solution44();
		int[] values = new int[] {9,23,3,91,39,4,67,24,90,35,28,1,75,11};
		Node tree1 = sol44.createCompleteTree(values);
		System.out.println(sol44.checkBalance(tree1));
		Node tree2 = sol44.creatUnbalTree(values, 6);
		System.out.println(sol44.checkBalance(tree2));
		Node tree3 = sol44.creatUnbalTree(values, 5);
		System.out.println(sol44.checkBalance(tree3));
	}
	boolean checkBalance (Node root)
	{
		if (checkHeight(root.leftChild) == Integer.MIN_VALUE) 
			return false;
		if (checkHeight(root.rightChild) == Integer.MIN_VALUE) 
			return false;
		return true;
	}
	int checkHeight (Node root)
	{
		if (root == null) 
			return 0;
		else if (root.leftChild == null & root.rightChild == null)
			return 1;
		int lHeight = checkHeight(root.leftChild);
		if (lHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
		int rHeight = checkHeight(root.rightChild);
		if (rHeight == Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		if (Math.abs(lHeight - rHeight) > 1) 
			return Integer.MIN_VALUE;
		return Math.max(lHeight, rHeight) + 1;
	}
	Node createCompleteTree (int[] array)
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
	Node creatUnbalTree (int[] array, int tHeight)
	{
		if (array.length == 0) return null;
		if (array.length == 1) return new Node(array[0]);
		Node root = new Node(array[0]);
		Node temp = root;
		LinkedList<Node> nodes = new LinkedList<>();
		nodes.add(root);
		int index = 1;
		while (index < tHeight && index < array.length)
		{
			temp.leftChild = new Node(array[index]);
			temp = temp.leftChild;
			index ++;
		}
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
	class Node
	{
		int value;
		Node leftChild;
		Node rightChild;
		Node (int v) {value = v;}
	}
}