package chapter4;
/* 
 * Algorithm: The valid sequence must have root come first. This rule must apply to any subtree. So
 * we can do this recursively. The most tricky thing is how to combine the subtree results. When
 * combine, the rule that node must come first sholdn't change.
 * Running time complexity: O(n^n)
 * Space complexity: O(n^n)
 * Note: Refer to the book solution
*/
import java.util.*;
public class Solution49 {
	public static void main(String[] args) {
		Solution49 sol49 = new Solution49();
		int[] values = new int[] {1,2,3,4,5};
		Node BST = sol49.creatBST(values);
		sol49.printOutTree(BST);
		ArrayList<LinkedList<Integer>> result = sol49.possibleArray(BST);
		sol49.printOutResult(result);
	}
	// get all possible array
	ArrayList<LinkedList<Integer>> possibleArray(Node root)
	{
		ArrayList<LinkedList<Integer>> result = new ArrayList<>();
		// base cases
		if (root == null)
		{
			result.add(new LinkedList<Integer>());
			return result;
		}
		if (root.left == null & root.right == null)
		{
			LinkedList<Integer> newList = new LinkedList<>();
			newList.add(root.val);
			result.add(newList);
			return result;
		}
		// recursively call to analyze the left subtree and the right subtree
		ArrayList<LinkedList<Integer>> leftSub = possibleArray(root.left);
		ArrayList<LinkedList<Integer>> rightSub = possibleArray(root.right);
		// root need to show up first
		LinkedList<Integer> prefix = new LinkedList<>();
		prefix.add(root.val);
		// generate all the possibilities
		for (LinkedList<Integer> left : leftSub)
			for (LinkedList<Integer> right : rightSub)
			{
				ArrayList<LinkedList<Integer>> combine = new ArrayList<>();
				combineTwoLists (left, right, prefix, combine);
				result.addAll(combine);
			}
		return result;
	}
	// return all the possible combinations to combine list 1 and list with a prefix, which need to go first
	void combineTwoLists (LinkedList<Integer> l1, LinkedList<Integer> l2, LinkedList<Integer> prefix, ArrayList<LinkedList<Integer>> combine)
	{
		// base case: if one list is empty
		if (l1.size() == 0 || l2.size() == 0)
		{
			LinkedList<Integer> addTogether = new LinkedList<>();
			addTogether.addAll(prefix);
			addTogether.addAll(l1);
			addTogether.addAll(l2);
			combine.add(addTogether);
			return;
		}
		// remove on element from the first list and add it into prefix
		int temp = l1.removeFirst();
		prefix.addLast(temp);
		// recursively call the function
		combineTwoLists(l1, l2, prefix, combine);
		// restore
		prefix.removeLast();
		l1.addFirst(temp);
		// remove on element from the second list and add it into prefix
		temp = l2.removeFirst();
		prefix.addLast(temp);
		// recursively call the function
		combineTwoLists(l1, l2, prefix, combine);
		// restore
		prefix.removeLast();
		l2.addFirst(temp);
	}
	// create a binary tree
	Node creatBST (int[] array)
	{
		return creatBST (array, 0, array.length - 1);
	}
	// overload
	Node creatBST (int[] array, int start, int end)
	{
		if (start <= end)
		{
			int mid = (end - start + 1) / 2 + start;
			Node root = new Node (array[mid]);
			root.left = creatBST (array, start, mid - 1);
			root.right = creatBST (array, mid + 1, end);
			return root;
		}
		return null;
	}
	// print out multiple list 
	void printOutResult (ArrayList<LinkedList<Integer>> result)
	{
		for (int i = 0; i < result.size(); i ++)
			printList(result.get(i));
	}
	// print out one list
	void printList (LinkedList<Integer> list)
	{
		System.out.print("{");
		for (int i = 0; i < list.size(); i ++)
		{
			System.out.print(list.get(i));
			if (i != list.size() - 1)
				System.out.print(", ");
		}
		System.out.print("}  ");
	}
	// print out the tree
	void printOutTree (Node root)
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
				System.out.print(q.peek().val + "   ");
				qTemp.offer(q.poll());
			}
			while (!qTemp.isEmpty())
			{
				Node temp = qTemp.poll();
				if (temp.left != null)
					q.offer(temp.left);
				if (temp.right != null)
					q.offer(temp.right);
			}
			System.out.println();
		}
	}
	// Node class
	class Node
	{
		int val;
		Node left;
		Node right;
		Node (int v) {val = v;}
	}
}