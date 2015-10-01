package chapter10;

public class Solution1011 {
	public static void main(String[] args) {
		Solution1011 sol1011 = new Solution1011();
		int[] array = new int[] {9,1,0,4,8,7};
		System.out.println("The original array: ");
		sol1011.printArray(array);
		System.out.println("Sort the array into an alternating sequeence of peaks and valleys:");
		sol1011.peaksValleys(array);
		sol1011.printArray(array);
	}
	void peaksValleys (int[] array)
	{
		for (int i = 1; i < array.length; i += 2)
		{
			if (i < array.length - 1)
			{
				if (array[i - 1] <= array[i + 1] & array[i] > array[i - 1])
				{
					int temp = array[i - 1];
					array[i - 1] = array[i];
					array[i] = temp;
				}else if (array[i - 1] > array [i + 1] & array [i] > array[i + 1])
				{
					int temp = array[i + 1];
					array[i + 1] = array[i];
					array[i] = temp;
				}
			}else
			{
				if (array[i - 1] < array[i])
				{
					int temp = array[i - 1];
					array[i - 1] = array[i];
					array[i] = temp;
				}
			}
		}
	}
	void printArray (int[] array)
	{
		if (array.length == 0)
			return;
		for (int i = 0; i < array.length; i ++)
		{
			System.out.print(array[i] + "  ");
		}
		System.out.println();
	}
}
