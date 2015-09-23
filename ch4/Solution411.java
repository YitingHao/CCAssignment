package chapter4;
/* 
 * Assumption: The tree is already a binary search tree
 * Algorithm: 1) find() function - do binary search, which take O(log n); 2) insert operation - takes
 * O(log n), similar to find operation, need to compare with root on each layer; 3) delete operation 
 * is a little tricky, which has different cases need to be considered; 4) getRandomNode() operation
 * involves get a random number and use this number as an index to find node. Therefore, each node has
 * the same possibility to be selected
*/
import java.util.*;
public class Solution411 {
	public static void main(String[] args) {
		Solution411 sol411 = new Solution411();
		int[] values = new int[]{5,4,5,6,9,7,3,8,10};
		TreeNode root = sol411.createTree(values);
		System.out.println("Original tree");
		sol411.printOutTree(root);
		// use find() function in TreeNode class, check whether it contains 9
		sol411.printFind(root.find(9));
		System.out.println();
		// use getRandomNode() function in TreeNode class to get a random node from the tree
		sol411.printRandom(root.getRandomNode());
		System.out.println();
		// use delete() function in TreeNode class to delete node with value 6
		System.out.println("Delete a node with value 6");
		root.delete(6);
		sol411.printOutTree(root);
		// use delete() function in TreeNode class to delete node with value 4
		System.out.println("Delete a node with value 4");
		root.delete(4);
		sol411.printOutTree(root);
	}
	// TreeNode class
	class TreeNode
	{
		int val;
		int size;
		TreeNode left;
		TreeNode right;
		TreeNode parent;
		TreeNode (int data) 
		{ 
			val = data; 
			size = 1;
		}
		// insert operation
		public void insert (int data)
		{
			TreeNode newNode = new TreeNode(data);
			if (data <= val)
			{
				if (this.left == null)
				{
					this.left = newNode;
					newNode.parent = this;
				}
				else
					this.left.insert(data);
			} else
			{
				if (this.right == null)
				{
					this.right = newNode;
					newNode.parent = this;
				}
				else
					this.right.insert(data);
			}
			size ++;
		}
		// find operation
		public TreeNode find (int d)
		{
			if (d == val) 
				return this;
			else if (this.val < d) 
			{
				if (this.right != null)
					return this.right.find(d);
				else
					return null;
			}
			else 
			{
				if (this.left != null)
					return this.left.find(d);
				else
					return null;
			}
		}
		// delete operation
		public void delete (int data)
		{
			// when value equals to this node, start do deletion
			if (data == val)
			{
				// case 1: if this node has no children, delete it
				if (this.left == null & this.right == null)
				{
					// if it is the root, tree will be empty
					if (this.parent == null)
					{
						this.val = Integer.MIN_VALUE;
						this.size = 0;
					}
					// else delete it
					else
					{
						if (this.parent.left == this)
							this.parent.left = null;
						else
							this.parent.right = null;
					}
				}
				// case 2: if there is a right child, but no left child 
				else if (this.left == null & this.right != null)
				{
					TreeNode minRight = this.right;
					minRight.size --;
					// get the smallest node in its left subtree, and set this node to replace the deleted node
					while (minRight.left != null)
					{
						minRight = minRight.left;
						minRight.size --;
					}
					// if this smallest node has right child, link its child to be its parent's left child
					if (minRight.right != null)
						minRight.parent.left = minRight.right;
					else
						minRight.parent.right = null;
					this.val = minRight.val;
				}
				// case 3: if there is left child
				else
				{
					// find the largest node in the left subtree as the replacement for current node
					TreeNode maxLeft = this.left;
					maxLeft.size --;
					while (maxLeft.right != null)
					{
						maxLeft = maxLeft.right;
						maxLeft.size --;
					}
					if (maxLeft.left != null)
						maxLeft.parent.right = maxLeft.left;
					else 
						maxLeft.parent.left = null;
					this.val = maxLeft.val;
				}
				
			}
			// move to the left child is value is smaller than root, call function recursively
			else if (data < val)
			{
				this.left.delete(data);
			}
			// move to the right child is value is larger than root, call function recursively
			else
			{
				this.right.delete(data);
			}
			size --;
		}
		// get random node operation. generate a random number and based on this index to get relative node
		public TreeNode getRandomNode ()
		{
			Random random = new Random();
			int index = random.nextInt(this.size);
			return getithElement(index);
		}
		// get node with index
		public TreeNode getithElement (int index)
		{
			int leftSize = 0;
			if (this.left != null)
				leftSize = this.left.size;
			if (index < leftSize)
				return this.left.getithElement(index);
			else if (index == leftSize)
				return this;
			else
				return this.right.getithElement(index - leftSize - 1);
		}
	}
	// create tree using insertion function in Tree class
	TreeNode createTree (int[] array)
	{
		if (array.length == 0) return null;
		TreeNode root = new TreeNode(array[0]);
		for (int i = 1; i < array.length; i ++)
		{
			root.insert(array[i]);
		}
		return root;
	}
	// print out tree layer by layer
	void printOutTree (TreeNode root)
	{
		if (root == null) return;
		int height = 0;
		Queue<TreeNode> q = new LinkedList<>();
		Queue<TreeNode> qTemp = new LinkedList<>();
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
				TreeNode temp = qTemp.poll();
				if (temp.left != null)
					q.offer(temp.left);
				if (temp.right != null)
					q.offer(temp.right);
			}
			System.out.println();
		}
		System.out.println();
	}
	// print out the find function result. If it returns null, show "no such element" message; if it
	// returns a node, print out node info
	void printFind (TreeNode node)
	{
		if (node != null)
			System.out.println("Find the element in the tree with the value of " + node.val + ".");
		else
			System.out.println("The element cannot be found in the tree.");
	}
	// print out result of random node
	void printRandom (TreeNode node)
	{
			System.out.println("Get a random element in the tree with the value of " + node.val + ".");
	}
}