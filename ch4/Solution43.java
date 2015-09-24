package chapter4;
/* 
 * Algorithm: 
 * Since it is required to establish linked list layer by layer, then we can apply breadth-first search.
 * Put all elements at each depth in one list.
 * Running time complexity: O(n), need to go through all node
 * Space complexity: O(n), need to generate linked list for each layer
*/
import java.util.*;
public class Solution43 {
	public static void main(String[] args) {
		Solution43 sol43 = new Solution43();
		int[] values = new int[] {21,34,3,16,86,7,98,2,4,6,82,68,10};
		// create a complete binary tree based on the array
		Node root = sol43.createCompleteTree(values);
		// generate linked list of all the nodes at each depth
		ArrayList<LinkedList<Node>> result = sol43.createLevelLists(root);
		sol43.printOut(result);
	}
	// function to generate lists layer by layer
	ArrayList<LinkedList<Node>> createLevelLists (Node root)
	{
		ArrayList<LinkedList<Node>> result = new ArrayList<>();
		// previous list contains elements in previous layer
		LinkedList<Node> previous = new LinkedList<>();
		// current list contains elements in current layer
		LinkedList<Node> current = new LinkedList<>();
		// initiation
		if (root != null)
			current.add(root);
		while (current.size() > 0)
		{
			result.add(current);
			previous = current;
			// need to create a new list
			current = new LinkedList<>();
			// add next layer elements into current list
			for (Node eachNode : previous)
			{
				if (eachNode.leftChild != null)
					current.add(eachNode.leftChild);
				if (eachNode.rightChild != null)
					current.add(eachNode.rightChild);
			}
		}
		return result;
	}
	// print out all the lists
	void printOut (ArrayList<LinkedList<Node>> levelLists)
	{
		for (int i = 0; i < levelLists.size(); i ++)
		{
			System.out.print("layter " + i + ":  ");
			for (Node eachNode : levelLists.get(i))
				System.out.print(eachNode.value + "  ");
			System.out.println();
		}
	}
	// create a complete binary tree based on the array
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
			// add left child
			Node newNode = new Node(array[index]);
			temp.leftChild = newNode;
			Nodes.addFirst(newNode);
			index ++;
			// check whether out of range
			if (index == array.length) break;
			// add right child
			newNode = new Node(array[index]);
			temp.rightChild = newNode;
			Nodes.addFirst(newNode);
			index ++;
			// check whether out of range
			if (index == array.length) break;
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