package Chapter1;

public class ZeroMatrix {
	public static void main(String[] args) {
		int[][] matrix = new int[3][4];
		matrix[0][0] = 1;
		matrix[0][1] = 0;
		matrix[0][2] = 2;
		matrix[0][3] = 3;
		matrix[1][0] = 4;
		matrix[1][1] = 5;
		matrix[1][2] = 6;
		matrix[1][3] = 7;
		matrix[2][0] = 8;
		matrix[2][1] = 9;
		matrix[2][2] = 0;
		matrix[2][3] = 11;
		setZero(matrix);
		System.out.println("Wait");
	}
	public static void setZero (int[][] matrix)
	{
		int m = matrix.length;
		int n = matrix[0].length;
		boolean row = false;
		boolean col = false;
		for (int i = 0; i < n; i ++)
		{
			if (matrix[0][i] == 0)
			{
				row = true;
				break;
			}
		}
		for (int i = 0; i < m; i ++)
		{
			if (matrix[i][0] == 0)
			{
				col = true;
				break;
			}
		}
		for (int i = 1; i < m; i ++)
		{
			for (int j = 1; j < n; j ++)
			{
				if (matrix[i][j] == 0)
				{
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		for (int i = 1; i < m; i ++)
		{
			if (matrix[i][0] == 0)
			{
				for (int j = 1; j <n; j ++)
					matrix[i][j] = 0;
			}
		}
		for (int i = 1; i < n ; i ++)
		{
			if (matrix[0][i] == 0)
			{
				for (int j = 1; j < m; j ++)
					matrix[j][i] = 0;
			}
		}
		if (row)
		{
			for (int i = 0; i < n; i ++)
				matrix[0][i] = 0;
		}
		if (col)
		{
			for (int i = 0; i < m; i ++)
				matrix[i][0] = 0;
		}
	}
}
