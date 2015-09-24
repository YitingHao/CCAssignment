package chapter4;
/* 
 * Algorithm: 
 * Typical breadth first search.
 * Running time complexity: O(log n)
 * Space complexity: O(n)
*/
import java.util.*;
public class Solution41 {
	public static void main(String[] args) {
		Solution41 sol41 = new Solution41();
		int NodeNum = 5;
		int[][] routes = new int[][] {{2,4},{4},{2},{5},{2}};
		Graph graph = sol41.new Graph(NodeNum, routes);
		System.out.println("Route beteween node 1 to 5? " + graph.checkRoute(graph.nodes[0], graph.nodes[4]));
		graph.reset();
		System.out.println("Route beteween node 2 to 5? " + graph.checkRoute(graph.nodes[1], graph.nodes[4]));
		graph.reset();
		System.out.println("Route beteween node 3 to 1? " + graph.checkRoute(graph.nodes[2], graph.nodes[0]));
	}
	class Graph
	{
		Node[] nodes;
		Graph(int NodeNum, int[][] routes)
		{
			nodes = new Node[NodeNum];
			for (int i = 0; i < NodeNum; i ++)
				nodes[i] = new Node(i + 1);
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
	class Node
	{
		public int name;
		public Node[] children;
		public boolean visit;
		Node(int n) {name = n;}
	}
}