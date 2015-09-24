package chapter4;
import java.util.*;
public class Solution412 {
	public static void main(String[] args) {
		Solution412 sol412 = new Solution412();
		int[] values = new int[]{10,5,-3,3,1,11,3,-2,2};
		Node root = sol412.createTree(values);
		sol412.printOut(root);
		System.out.println();
		sol412.printSumResult(5, root);
	}
	int pathsWithSum (Node root, int targetSum)
	{
		HashMap<Integer,Integer> numOfRunningSum = new HashMap<>();
		addToHashmap (numOfRunningSum, 0, 1);
		return pathsWithSum (root, targetSum, 0, numOfRunningSum);
	}
	int pathsWithSum (Node node, int targetSum, int runningSum, HashMap<Integer,Integer> numOfRunningSum)
	{
		if (node == null) return 0;
		runningSum += node.val;
		addToHashmap(numOfRunningSum, runningSum, 1);
		int numOfPath = 0;
		if (numOfRunningSum.containsKey(runningSum - targetSum))
			numOfPath = numOfRunningSum.get(runningSum - targetSum);
		numOfPath += pathsWithSum(node.left, targetSum, runningSum, numOfRunningSum);
		numOfPath += pathsWithSum(node.right, targetSum, runningSum, numOfRunningSum);
		addToHashmap(numOfRunningSum, runningSum, -1);
		return numOfPath;
	}
	void addToHashmap (HashMap<Integer, Integer> map, int key, int change)
	{
		if (!map.containsKey(key))
			map.put(key, 1);
		else
			map.put(key, map.get(key) + change);
	}
	class Node
	{
		int val;
		Node left;
		Node right;
		Node(int v) {val = v;}
	}
	Node createTree (int[] array)
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
			temp.left = newNode;
			Nodes.addFirst(newNode);
			index ++;
			if (index == array.length) break;
			newNode = new Node(array[index]);
			temp.right = newNode;
			Nodes.addFirst(newNode);
			index ++;
			if (index == array.length) break;
		}
		return root;
	}
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
	void printSumResult(int targetSum, Node root)
	{
		if (pathsWithSum(root, targetSum) == 0)
			System.out.println("There is no such path that has summation " + targetSum);
		else
			System.out.println("The number of the path that has summation of " +targetSum+ " is: " + pathsWithSum(root, targetSum));
	}
}