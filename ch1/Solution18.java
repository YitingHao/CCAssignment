/*  
 * Algorithm: 
 * Use the space in the first row and column to store info that whether this row or column has zero or not.
 * Therefore, no extra space is needed. The little tricky thing here is to deal with the first row and the 
 * first column later. Otherwise, it will erase the info store in these space.
 * Running time complexity: O(colLength^2)
 * Space complexity: O(1)
*/
public class Solution18 {
	public static void main(String[] args) {
		Solution18 sol18 = new Solution18();
		// Test one
		int[][] matrix = new int[][] {{1,2,0,4,5},{6,9,4,0,8},{9,4,7,1,9}};
		System.out.println("The original matrix is:");
		sol18.printOut(matrix);
		System.out.println("Zero Matrix:");
		sol18.zeroMatrix(matrix);
		sol18.printOut(matrix);
		// Test two
		matrix = new int[][] {{1,3},{6,3},{9,1},{9,0}};
		System.out.println("The original matrix is:");
		sol18.printOut(matrix);
		System.out.println("Zero Matrix:");
		sol18.zeroMatrix(matrix);
		sol18.printOut(matrix);
	}
	public void zeroMatrix (int[][] matrix)
	{
		// Get row and column length
		int rowLength = matrix.length;
		int colLength = matrix[0].length;
		boolean row = false;
		boolean col = false;
		// Check whether the first row has zero or not
		for (int i = 0; i < colLength; i ++)
		{
			if (matrix[0][i] == 0)
			{
				row = true;
				break;
			}
		}
		// Check whether the first column has zero or not
		for (int i = 0; i < rowLength; i ++)
		{
			if (matrix[i][0] == 0)
			{
				col = true;
				break;
			}
		}
		// Check the rest part of matrix and store info in the first row and the first column
		for (int i = 1; i < rowLength; i ++)
		{
			for (int j = 1; j < colLength; j ++)
			{
				if (matrix[i][j] == 0)
				{
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		// Go through the first column. If the element is zero, set all that row to be zero
		for (int i = 1; i < rowLength; i ++)
		{
			if (matrix[i][0] == 0)
			{
				for (int j = 1; j <colLength; j ++)
					matrix[i][j] = 0;
			}
		}
		// Go through the first row. If the element is zero, set all that column to be zero
		for (int i = 1; i < colLength ; i ++)
		{
			if (matrix[0][i] == 0)
			{
				for (int j = 1; j < rowLength; j ++)
					matrix[j][i] = 0;
			}
		}
		// Set all elements in first row to zero if there is a zero
		if (row)
		{
			for (int i = 0; i < colLength; i ++)
				matrix[0][i] = 0;
		}
		// Set all elements in first column to zero if there is a zero
		if (col)
		{
			for (int i = 0; i < rowLength; i ++)
				matrix[i][0] = 0;
		}
	}
	// Print out matrix row by row
		void printOut (int[][] matrix)
		{
			if (matrix == null) return;
			int row = matrix.length;
			int col = matrix[0].length;
			for (int i = 0; i < row; i ++)
			{
				for (int j = 0; j < col; j ++)
				{
					System.out.print(matrix[i][j] + "  ");
				}
				System.out.println();
			}
		}
}