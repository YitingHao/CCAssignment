package chapter4;
import java.util.*;
public class Solution410 {
	public static void main(String[] args) {
		Solution410 sol410 = new Solution410();
		int[] valsMain = new int[]{1,2,4,8,3,5,6,7};
		int[] valSub = new int[]{2,4,8,5};
		Node mainT = sol410.createTree(valsMain, 4);
		Node subT1 = sol410.createTree(valSub, 3);
		sol410.printTree(mainT);
		sol410.printTree(subT1);
		System.out.println(sol410.isSubtree(mainT, subT1));
		valSub = new int[]{3,6,7};
		Node subT2 = sol410.createTree(valSub, 2);
		sol410.printTree(subT2);
		System.out.println(sol410.isSubtree(mainT, subT2));
		valSub = new int[]{1,2,8,3};
		Node subT3 = sol410.createTree(valSub, 3);
		sol410.printTree(subT3);
		System.out.println(sol410.isSubtree(mainT, subT3));
	}
	public boolean isSubtree (Node mainT, Node subT)
	{
		if (subT == null) return true;
		if (checkMatch(mainT, subT))
			return true;
		return false;
	}
	boolean checkMatch (Node node, Node subT)
	{
		if (node == null && subT == null)
			return true;
		else if (node == null || subT == null)
			return false;
		else if(node.val == subT.val)
		{
			if (checkMatch(node.left, subT.left) && checkMatch(node.right, subT.right))
				return true;
		}
		return checkMatch(node.left, subT) || checkMatch(node.right, subT);
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
	class Node
	{
		int val;
		Node left;
		Node right;
		Node parent;
		Node(int v) {val = v;}
	}
}