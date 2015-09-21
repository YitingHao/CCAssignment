package chapter1;
/* 
 * Assumption: The matrix is N*N matrix.
 * Algorithm: To rotate matrix layer by layer. 
 * To rotate one layer, swap four corresponding elements each time until this layer rotate 90 degree.
 * Running time complexity: O(n^2)
 * Space complexity: O(1)
*/
public class Solution17 {
	public static void main(String[] args) {
		Solution17 sol17 = new Solution17();
		int[][] matrix1 = new int[][] {{16,15,14,13},{12,11,10,9},{8,7,6,5,},{4,3,2,1}};
		System.out.println("The original matrix is:");
		sol17.printOut(matrix1);
		System.out.println("Rotate 90 degree clockwise:");
		sol17.rotateMatrix(matrix1, 4);
		sol17.printOut(matrix1);
		int[][] matrix2 = new int[][] {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
		System.out.println("The original matrix is:");
		sol17.printOut(matrix2);
		System.out.println("Rotate 90 degree clockwise:");
		sol17.rotateMatrix(matrix2, 5);
		sol17.printOut(matrix2);
	}
	public void rotateMatrix (int[][] matrix, int n)
	{
		// Rotate matrix layer by layer
		for (int layer = 0; layer < n / 2; layer ++)
		{
			// Get how many sets of four elements that I need to swap
			int exchangeTimes = n - 2 * layer - 1;
			for (int offset = 0; offset < exchangeTimes; offset ++)
				// Swap one set of four elements, which set is determined by offset
				exchangeFourElements(matrix, layer, offset);
		}
	}
	// Swap four elements, left -> top, top -> right, right -> bottom, bottom -> left
	void exchangeFourElements (int[][] matrix, int layer, int offset)
	{
		int matrixSize = matrix.length;
		int top = matrix[layer][layer + offset];
		int right = matrix[layer + offset][matrixSize - 1 - layer];
		int bottom = matrix[matrixSize - 1 - layer][matrixSize - 1 - layer - offset];
		int left = matrix[matrixSize - 1 - layer - offset][layer];
		int temp = top;
		// left -> top
		matrix[layer][layer + offset] = left;
		// bottom -> left
		matrix[matrixSize - 1 - layer - offset][layer] = bottom;
		// right -> bottom
		matrix[matrixSize - 1 - layer][matrixSize - 1 - layer - offset] = right;
		// top -> right
		matrix[layer + offset][matrixSize - 1 - layer] = temp;
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