package chapter4;
import java.util.*;
public class Solution47 {
	public static void main(String[] args) {
		Solution47 sol47 = new Solution47();
		char[] projects = new char[]{'a','b','c','d','e','f'};
		char[][] dependencies = new char[][]{{'d','a'},{'b','f'},{'d','b'},{'a','f'},{'c','d'}};
		Graph graph = sol47.createGraph(projects, dependencies);
		sol47.printOutOrder(sol47.generateOrder(graph));
		dependencies = new char[][]{{'b','a'},{'c','b'},{'d','c'},{'e','d'},{'f','e'}};
		graph = sol47.createGraph(projects, dependencies);
		sol47.printOutOrder(sol47.generateOrder(graph));
		dependencies = new char[][]{{'b','a'},{'c','b'},{'d','c'},{'e','d'},{'f','e'},{'c','f'}};
		graph = sol47.createGraph(projects, dependencies);
		sol47.printOutOrder(sol47.generateOrder(graph));
	}
	ArrayList<Node> generateOrder (Graph graph)
	{
		ArrayList<Node> result = new ArrayList<>();
		ArrayList<Node> nodeTrack = new ArrayList<>();
		for (int index = 0; index < graph.nodes.length; index ++)
		{
			nodeTrack.add(graph.nodes[index]);
		}
		while (nodeTrack.size() != 0)
		{
			int size = nodeTrack.size();
			for (int i = 0; i < nodeTrack.size(); i ++)
			{
				Node eachNode = nodeTrack.get(i);
				if (eachNode.parents.size() == 0)
				{
					result.add(eachNode);
					for (int j = 0; j < eachNode.children.size(); j ++)
					{
						eachNode.children.get(j).parents.remove(eachNode);
					}
					nodeTrack.remove(i);
				}
			}
			if (nodeTrack.size() == size) return null;
		}
		return result;
	}
	Graph createGraph (char[] projects, char[][] dependencies)
	{
		Graph graph = new Graph(projects);
		for (int index = 0; index < dependencies.length; index ++)
		{
			Node parent = graph.getNode(dependencies[index][1]);
			Node child = graph.getNode(dependencies[index][0]);
			parent.children.add(child);
			child.parents.add(parent);
		}
		return graph;
	}
	void printOutOrder (ArrayList<Node> order)
	{
		if (order == null)
		{
			System.out.println("There is no valid order.");
			return;
		}
		for (int index = 0; index < order.size(); index ++)
		{
			System.out.print(order.get(index).val + "  ");
		}
		System.out.println();
	}
	class Graph
	{
		Node[] nodes;
		HashMap<Character,Integer> map = new HashMap<>();
		Graph(char[] projects)
		{ 
			nodes = new Node[projects.length];
			for (int index = 0; index < projects.length; index ++)
			{
				nodes[index] = new Node(projects[index]);
				map.put(projects[index], index);
			}
		}
		Node getNode (char charVal)
		{
			return nodes[map.get(charVal)];
		}
	}
	class Node
	{
		char val;
		List<Node> children = new ArrayList<>();
		List<Node> parents = new ArrayList<>();
		Node(char v) { val = v;}
	}
}