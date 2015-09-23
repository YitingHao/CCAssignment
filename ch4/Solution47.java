package chapter4;
/* 
 * Algorithm: This is a topological sort problem. Briefly, find one node without coming edges, add this
 * node in the queue, then remove it from the graph; try to find another node without coming edges, and
 * repeat the same thing. If cannot find a node without incoming edges, that means this graph has cycle
 * in it and there is no valid order.
 * Running time complexity: O(|E|+|V|), where |E| is the number of edges in the graph and |V| is the
 * number of nodes in the graph, since we go through all the edges and nodes
 * Space complexity: O(1)
*/
import java.util.*;
public class Solution47 {
	public static void main(String[] args) {
		Solution47 sol47 = new Solution47();
		// first test
		char[] projects = new char[]{'a','b','c','d','e','f'};
		char[][] dependencies = new char[][]{{'d','a'},{'b','f'},{'d','b'},{'a','f'},{'c','d'}};
		Graph graph = sol47.createGraph(projects, dependencies);
		sol47.printOutOrder(sol47.generateOrder(graph));
		// second test
		dependencies = new char[][]{{'b','a'},{'c','b'},{'d','c'},{'e','d'},{'f','e'}};
		graph = sol47.createGraph(projects, dependencies);
		sol47.printOutOrder(sol47.generateOrder(graph));
		// third test
		dependencies = new char[][]{{'b','a'},{'c','b'},{'d','c'},{'e','d'},{'f','e'},{'c','f'}};
		graph = sol47.createGraph(projects, dependencies);
		sol47.printOutOrder(sol47.generateOrder(graph));
	}
	// get topological order, if cannot, return null
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
				// find one without incoming edges, that is have no parents
				if (eachNode.parents.size() == 0)
				{
					result.add(eachNode);
					// remove this node, update the parents list of its children by removing this node
					for (int j = 0; j < eachNode.children.size(); j ++)
					{
						eachNode.children.get(j).parents.remove(eachNode);
					}
					nodeTrack.remove(i);
				}
			}
			// if cannot find one node without incoming edges, return null
			if (nodeTrack.size() == size) return null;
		}
		return result;
	}
	// create a graph based on projects char and their relations
	Graph createGraph (char[] projects, char[][] dependencies)
	{
		// initiate graph
		Graph graph = new Graph(projects);
		// establish the relations
		for (int index = 0; index < dependencies.length; index ++)
		{
			Node parent = graph.getNode(dependencies[index][1]);
			Node child = graph.getNode(dependencies[index][0]);
			parent.children.add(child);
			child.parents.add(parent);
		}
		return graph;
	}
	// print out topological order
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
	// Graph class, includes all the nodes, a hashmap to map char value with index,
	class Graph
	{
		Node[] nodes;
		HashMap<Character,Integer> map = new HashMap<>();
		// constructor
		Graph(char[] projects)
		{ 
			nodes = new Node[projects.length];
			for (int index = 0; index < projects.length; index ++)
			{
				nodes[index] = new Node(projects[index]);
				map.put(projects[index], index);
			}
		}
		// get node
		Node getNode (char charVal)
		{
			return nodes[map.get(charVal)];
		}
	}
	// Node class
	class Node
	{
		char val;
		List<Node> children = new ArrayList<>();
		List<Node> parents = new ArrayList<>();
		Node(char v) { val = v;}
	}
}