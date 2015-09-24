package chapter4;
import java.util.*;
public class Solution411 {
	public static void main(String[] args) {
		Solution411 sol411 = new Solution411();
		int[] values = new int[]{5,4,5,6,9,7,3,8,10};
		TreeNode root = sol411.createTree(values);
		sol411.printOutTree(root);
		sol411.printFind(root.find(9));
		sol411.printRandom(root.getRandomNode());
		root.delete(6);
		sol411.printOutTree(root);
		root.delete(4);
		sol411.printOutTree(root);
	}
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
		public void delete (int data)
		{
			if (data == val)
			{
				if (this.left == null & this.right == null)
				{
					if (this.parent == null)
					{
						this.val = Integer.MIN_VALUE;
						this.size = 0;
					}
					else
					{
						if (this.parent.left == this)
							this.parent.left = null;
						else
							this.parent.right = null;
					}
				}
				else if (this.left == null & this.right != null)
				{
					TreeNode minRight = this.right;
					minRight.size --;
					while (minRight.left != null)
					{
						minRight = minRight.left;
						minRight.size --;
					}
					if (minRight.right != null)
						minRight.parent.left = minRight.right;
					else
						minRight.parent.right = null;
					this.val = minRight.val;
				}else
				{
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
				
			}else if (data < val)
			{
				this.left.delete(data);
			}else
			{
				this.right.delete(data);
			}
			size --;
		}
		public TreeNode getRandomNode ()
		{
			Random random = new Random();
			int index = random.nextInt(this.size);
			return getithElement(index);
		}
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
	}
	void printFind (TreeNode node)
	{
		if (node != null)
			System.out.println("Find the element in the tree with the value of " + node.val + ".");
		else
			System.out.println("The element cannot be found in the tree.");
	}
	void printRandom (TreeNode node)
	{
			System.out.println("Get a random element in the tree with the value of " + node.val + ".");
	}
}