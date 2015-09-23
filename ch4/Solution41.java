package chapter4;
/* 
 * Algorithm: 
 * Typical breadth first search. Start from the starting point and do breadth-first search until we find 
 * the target node.
 * Running time complexity: O(log n)
 * Space complexity: O(n)
*/
import java.util.*;
public class Solution41 {
	public static void main(String[] args) {
		Solution41 sol41 = new Solution41();
		int NodeNum = 5;
		// save routes info as an adjacent list
		// in this case, the first node points to node 2 and node 4
		int[][] routes = new int[][] {{2,4},{4},{2},{5},{2}};
		Graph graph = sol41.new Graph(NodeNum, routes);
		sol41.print(0, 4, graph);
		// need to reset the "visit" value for all nodes
		graph.reset();
		sol41.print(1, 4, graph);
		graph.reset();
		sol41.print(2, 0, graph);
		graph.reset();
	}
	class Graph
	{
		Node[] nodes;
		// constructor, generate graph based on the nodes and edges
		Graph(int NodeNum, int[][] routes)
		{
			nodes = new Node[NodeNum];
			// build up nodes
			for (int i = 0; i < NodeNum; i ++)
				nodes[i] = new Node(i + 1);
			// establish routes
			for (int i = 0; i < NodeNum; i ++)
			{
				nodes[i].children = new Node[routes[i].length];
				for (int j = 0; j < routes[i].length; j ++)
					nodes[i].children[j] = nodes[routes[i][j] - 1];
			}
		}
		public boolean checkRoute (Node start, Node end)
		{
			if (start == end) return true;
			// Put children node in the queue
			Queue<Node> q = new LinkedList<>();
			q.offer(start);
			start.visit = true;
			while (!q.isEmpty())
			{
				Node temp = q.poll();
				for (int i = 0; i < temp.children.length; i ++)
				{
					if (temp.children[i] == end) return true;
					if (temp.children[i].visit == false)
					{
						temp.children[i].visit = true;
						q.offer(temp.children[i]);
					}
				}
			}
			return false;
		}
		public void reset ()
		{
			for (int i = 0; i < nodes.length; i ++)
				nodes[i].visit = false;
		}
	}
	// node class
	class Node
	{
		public int name;
		public Node[] children;
		public boolean visit;
		// constructor
		Node(int n) {name = n;}
	}
	// print result
	void print (int start, int end, Graph graph)
	{
		System.out.println("Route beteween node " + (start + 1) + " to " + (end + 1) +"? "+ graph.checkRoute(graph.nodes[start], graph.nodes[end]));
	}
}