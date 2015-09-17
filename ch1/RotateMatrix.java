package Chapter1;

public class RotateMatrix {
	public static void main(String[] args) {
		int[][] matrix = new int[5][5];
		matrix[0][0] = 1;
		matrix[0][1] = 2;
		matrix[0][2] = 3;
		matrix[0][3] = 4;
		matrix[0][4] = 17;
		matrix[1][0] = 5;
		matrix[1][1] = 6;
		matrix[1][2] = 7;
		matrix[1][3] = 8;
		matrix[1][4] = 18;
		matrix[2][0] = 9;
		matrix[2][1] = 10;
		matrix[2][2] = 11;
		matrix[2][3] = 12;
		matrix[2][4] = 19;
		matrix[3][0] = 13;
		matrix[3][1] = 14;
		matrix[3][2] = 15;
		matrix[3][3] = 16;
		matrix[3][4] = 20;
		matrix[4][0] = 21;
		matrix[4][1] = 22;
		matrix[4][2] = 23;
		matrix[4][3] = 24;
		matrix[4][4] = 25;
		rotate(matrix, 5);
		System.out.println("Wait");
	}
	public static void rotate (int[][] matrix, int n)
	{
		for (int layer = 0; layer < n / 2; layer ++)
		{
			int start = layer;
			int end = n - 1 - layer;
			int j = start;
			for (int i = layer; i < end - start + layer; i ++)
			{
				int temp = matrix[start][i];
				matrix[start][i] = matrix[j][end];
				matrix[j][end] = temp;
				j ++;
			}
			j = end;
			for (int i = layer; i < end - start + layer; i ++)
			{
				int temp = matrix[start][i];
				matrix[start][i] = matrix[end][j];
				matrix[end][j] = temp;
				j --;
			}
			j = end;
			for (int i = layer; i < end - start + layer; i ++)
			{
				int temp = matrix[start][i];
				matrix[start][i] = matrix[j][start];
				matrix[j][start] = temp;
				j --;
			}
		}
	}
}