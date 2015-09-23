package chapter4;
/* 
 * Algorithm: Again we solve this problem recursively. If the root is the same, check both the right 
 * subtree and left subtree. If not, change the main tree to be the left subtree of original main tree,
 * and do the check again. If still not, change the main tree to be the right subtree of original main
 * tree. If not, then this tree cannot be the subtree of the main tree
 * Running time complexity: O(n+km), where k is the number of occurrences that root of subtree in main
 * tree.
 * Space complexity: O(log m + log n)
*/
import java.util.*;
public class Solution410 {
	public static void main(String[] args) {
		Solution410 sol410 = new Solution410();
		int[] valsMain = new int[]{1,2,4,8,3,5,6,7};
		int[] valSub = new int[]{2,4,8,5};
		Node mainT = sol410.createTree(valsMain, 4);
		System.out.println("Main tree structure");
		sol410.printTree(mainT);
		System.out.println();
		// first test 
		Node subT1 = sol410.createTree(valSub, 3);
		System.out.println("The first subtree structure");
		sol410.printTree(subT1);
		sol410.printResult(mainT, subT1);
		// second test
		valSub = new int[]{3,6,7};
		Node subT2 = sol410.createTree(valSub, 2);
		sol410.printTree(subT2);
		sol410.printResult(mainT, subT2);
		// third test 
		valSub = new int[]{1,2,8,3};
		Node subT3 = sol410.createTree(valSub, 3);
		sol410.printTree(subT3);
		sol410.printResult(mainT, subT3);
	}
//	function to check whether the second tree is the subtree of the first tree
	boolean isSubtree (Node node, Node subT)
	{
		// base cases
		if (node == null && subT == null)
			return true;
		else if (node == null || subT == null)
			return false;
		// if current root is same check both left and right subtree
		else if(node.val == subT.val)
		{
			if (isSubtree(node.left, subT.left) && isSubtree(node.right, subT.right))
				return true;
		}
		// if it is not, check the left and right subtree of the main tree to see whether the second 
		// tree is the subtree of these tree
		return isSubtree(node.left, subT) || isSubtree(node.right, subT);
	}
	// create a binary tree
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
	// print out tree layer by layer
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
	// print out result whether the second tree is the subtree of the first tree
	void printResult (Node mainT, Node subtree)
	{
		System.out.println("Is the tree the subtree of main tree? " + isSubtree(mainT, subtree));
		System.out.println();
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