package chapter4;
/* 
 * Assumption: One node also consider as a path
 * Algorithm: 
 * Save the running summation in a hashMap. Running summation means the summation of the path from one node
 * to another node. Each path I just need to find whether there is running summation that appears before, is
 * equals to the current running summation minus the target summation. Overall, we do this recursively.
 * Running time complexity: O(n)
 * Space complexity: O(n)
 * Note: refer to book solution
*/
import java.util.*;
public class Solution412 {
	public static void main(String[] args) {
		Solution412 sol412 = new Solution412();
		int[] values = new int[]{10,5,-3,3,1,11,3,-2,2};
		Node root = sol412.createTree(values);
		System.out.println("Print out the binary tree: ");
		sol412.printOut(root);
		// three tests, each node is considered a path
		sol412.printSumResult(5, root);
		sol412.printSumResult(10, root);
		sol412.printSumResult(1, root);
	}
	// find the number of paths that have summation equals to target summation
	int pathsWithSum (Node root, int targetSum)
	{
		// the hashmap contains the info about running summation, which is the summation from root to current node
		HashMap<Integer,Integer> numOfRunningSum = new HashMap<>();
		// initiation the hashmap
		addToHashmap (numOfRunningSum, 0, 1);
		return pathsWithSum (root, targetSum, 0, numOfRunningSum);
	}
	// overload
	int pathsWithSum (Node node, int targetSum, int runningSum, HashMap<Integer,Integer> numOfRunningSum)
	{
		if (node == null) return 0;
		// get runningSum for current node and update info in hashmap
		runningSum += node.val;
		addToHashmap(numOfRunningSum, runningSum, 1);
		int numOfPath = 0;
		if (numOfRunningSum.containsKey(runningSum - targetSum))
			numOfPath = numOfRunningSum.get(runningSum - targetSum);
		// recursively revoke function on left subtree
		numOfPath += pathsWithSum(node.left, targetSum, runningSum, numOfRunningSum);
		// recursively revoke function on right subtree
		numOfPath += pathsWithSum(node.right, targetSum, runningSum, numOfRunningSum);
		// reverse the changes to the hash table so that other nodes don't use it
		addToHashmap(numOfRunningSum, runningSum, -1);
		return numOfPath;
	}
	// add 
	void addToHashmap (HashMap<Integer, Integer> map, int key, int change)
	{
		if (!map.containsKey(key))
			map.put(key, 1);
		else
			map.put(key, map.get(key) + change);
	}
	// Node class
	class Node
	{
		int val;
		Node left;
		Node right;
		Node(int v) {val = v;}
	}
	// create tree function
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
	// print out tree layer by layer
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
	// print out number of paths that has a summation equals to target summation
	void printSumResult(int targetSum, Node root)
	{
		if (pathsWithSum(root, targetSum) == 0)
			System.out.println("There is no such path that has summation " + targetSum);
		else
			System.out.println("The number of the path that has summation of " +targetSum+ " is: " + pathsWithSum(root, targetSum));
	}
}