package chapter4;
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
	ArrayList<LinkedList<Integer>> possibleArray(Node root)
	{
		ArrayList<LinkedList<Integer>> result = new ArrayList<>();
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
		ArrayList<LinkedList<Integer>> leftSub = possibleArray(root.left);
		ArrayList<LinkedList<Integer>> rightSub = possibleArray(root.right);
		LinkedList<Integer> prefix = new LinkedList<>();
		prefix.add(root.val);
		for (LinkedList<Integer> left : leftSub)
			for (LinkedList<Integer> right : rightSub)
			{
				ArrayList<LinkedList<Integer>> combine = new ArrayList<>();
				combineTwoLists (left, right, prefix, combine);
				result.addAll(combine);
			}
		return result;
	}
	void combineTwoLists (LinkedList<Integer> l1, LinkedList<Integer> l2, LinkedList<Integer> prefix, ArrayList<LinkedList<Integer>> combine)
	{
		if (l1.size() == 0 || l2.size() == 0)
		{
			LinkedList<Integer> addTogether = new LinkedList<>();
			addTogether.addAll(prefix);
			addTogether.addAll(l1);
			addTogether.addAll(l2);
			combine.add(addTogether);
			return;
		}
		int temp = l1.removeFirst();
		prefix.addLast(temp);
		combineTwoLists(l1, l2, prefix, combine);
		prefix.removeLast();
		l1.addFirst(temp);
		
		temp = l2.removeFirst();
		prefix.addLast(temp);
		combineTwoLists(l1, l2, prefix, combine);
		prefix.removeLast();
		l2.addFirst(temp);
	}
	Node creatBST (int[] array)
	{
		return creatBST (array, 0, array.length - 1);
	}
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
	void printOutResult (ArrayList<LinkedList<Integer>> result)
	{
		for (int i = 0; i < result.size(); i ++)
			pointList(result.get(i));
	}
	void pointList (LinkedList<Integer> list)
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
	class Node
	{
		int val;
		Node left;
		Node right;
		Node (int v) {val = v;}
	}
}