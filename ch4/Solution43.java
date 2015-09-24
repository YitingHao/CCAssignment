package chapter4;
import java.util.*;
public class Solution43 {
	public static void main(String[] args) {
		Solution43 sol43 = new Solution43();
		int[] values = new int[] {21,34,3,16,86,7,98,2,4,6,82,68,10};
		Node root = sol43.createCompleteTree(values);
		ArrayList<LinkedList<Node>> result = sol43.createLevelLists(root);
		sol43.printOut(result);
	}
	ArrayList<LinkedList<Node>> createLevelLists (Node root)
	{
		ArrayList<LinkedList<Node>> result = new ArrayList<>();
		LinkedList<Node> previous = new LinkedList<>();
		LinkedList<Node> current = new LinkedList<>();
		if (root != null)
			current.add(root);
		while (current.size() > 0)
		{
			result.add(current);
			previous = current;
			current = new LinkedList<>();
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
	class Node
	{
		int value;
		Node leftChild;
		Node rightChild;
		Node (int v) {value = v;}
	}
}