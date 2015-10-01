package chapter10;

public class Solution109 {
	public static void main (String[] args)
	{
		Solution109 sol109 = new Solution109();
		int[][] matrix = new int[][] {{1,2,3,4,5},{7,9,11,20,70},{8,26,28,37,88},{10,35,46,52,90}};
		sol109.printMatrix(matrix);
		// six tests
		sol109.printResult(1, matrix);
		sol109.printResult(90, matrix);
		sol109.printResult(10, matrix);
		sol109.printResult(28, matrix);
		sol109.printResult(88, matrix);
		sol109.printResult(73, matrix);
	}
	// find coordinates x, y for target value x in a matrix
	// rowS - starting row; rowE - ending row
	// colS - starting column; colE - ending column
	int[] find(int x, int[][] matrix, int rowS, int rowE, int colS, int colE)
	{
		// special case
		if (matrix == null) 
			return null;
		// if x is smaller than the left-top element or is larger than the right-bottom element,
		// it cannot be in this sub-matirx
		if (x < matrix[rowS][colS] || x > matrix[rowE][colE])
			return null;
		int[] coordinates = new int[2];
		int diagonal = Math.min(rowE - rowS + 1, colE - colS + 1);
		int turnning = turningP(x, matrix, rowS, colS, diagonal);
		if (turnning == -1)
		{
			if ((rowE - rowS) < (colE - colS))
				return find(x, matrix, rowS, rowE, colS + diagonal, colE);
			else
				return find(x, matrix, rowS + diagonal, rowE, colS, colE);	
		}
		if (matrix[rowS + turnning][colS + turnning] == x)
		{
			coordinates[0] = rowS + turnning;
			coordinates[1] = colS + turnning;
			return coordinates;
		}
		coordinates = find(x, matrix, rowS + turnning, rowE, colS, colS + turnning - 1);
		if (coordinates == null)
			coordinates = find(x, matrix, rowS, rowS + turnning - 1, colS + turnning, colE);
		return coordinates;
	}
	int turningP(int x, int[][] matrix, int rowS, int colS, int diagonal)
	{
		for (int i = 0; i < diagonal; i ++)
		{
			if (x <= matrix[rowS + i][colS + i] )
				return i;
		}
		return -1;
	}
	// print out matrix
	void printMatrix (int[][] matrix)
	{
		if (matrix == null) return;
		int row = matrix.length;
		int col = matrix[0].length;
		for (int i = 0; i < row; i ++)
		{
			 for (int j = 0; j < col; j++)
			 {
				 if (matrix[i][j] >= 10)
					 System.out.print(matrix[i][j] + "  ");
				 else
					 System.out.print(matrix[i][j] + "   ");
			 }
			 System.out.println();
		}
	}
	// print out result
	void printResult (int x, int[][] matrix)
	{
		// here revoke find() function
		int[] coordinates = find(x, matrix, 0, matrix.length - 1, 0, matrix[0].length - 1);
		if (coordinates == null)
			System.out.println("There is no element with value of " + x + " in the matrix");
		else
		{
			System.out.println("The element " + x + " is in the matrix, in the row " + coordinates[0] + " and column " + coordinates[1]);
		}
	}
}