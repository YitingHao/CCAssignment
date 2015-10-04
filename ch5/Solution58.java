package chapter5;
/* Assumption: the input line's length won't exceed screen width, that is (x2 - x1) + 1 <= width.
 * Besides, the input y cannot exceed the screen height
 * Algorithms: set the start byte, end byte, and bytes between them separately. To set up start byte and end
 * byte, use masks. For bytes between if there is any, just set value to 0xFF. Be careful about the case
 * that line is in only one byte.
 * Running time complexity: O(l), proportional to the length of line
 * Space complexity: O(1)
*/
public class Solution58 {
	public static void main (String[] args)
	{
		Solution58 sol58 = new Solution58();
		// test one
		byte[] screen1 = new byte[120];
		int width = 24;
		int x1 = 3;
		int x2 = 20;
		int y = 2;
		byte[] drawline = sol58.drawLine(screen1, width, x1, x2, y);
		System.out.println("The first screen: ");
		sol58.printScreen(drawline, width); 
		// test two: line in one byte
		byte[] screen2 = new byte[120];
		x1 = 2;
		x2 = 7;
		y = 1;
		drawline = sol58.drawLine(screen2, width, x1, x2, y);
		System.out.println("The second screen: ");
		sol58.printScreen(drawline, width);
	}
	// draw a line at row y, from x1 to x2
	byte[] drawLine (byte[] screen, int width, int x1, int x2, int y)
	{
		int startByte = x1 / 8;
		int startOffset = x1 % 8;
		int endByte = x2 / 8;
		int endOffset = x2 % 8;
		// set the byte between the start byte and end byte if there is any
		for (int i = 0; i < (endByte - startByte) - 1; i ++)
		{
			screen[width / 8 * y + startByte + 1] = (byte) 0xFF;
		}
		// set the start byte and the end byte
		byte startMask = (byte) ((1 << (8 - startOffset)) - 1);
		byte endMask = (byte) ~((1 << (7 - endOffset)) - 1);
		// this is the case for line laying in one byte
		if (startByte == endByte)
		{
			// integrate the masks and set the byte
			screen[width / 8 * y + startByte] |= (startMask & endMask);
			return screen;
		}
		// set the start byte
		screen[width / 8 * y + startByte] |= startMask;
		// set the end byte
		screen[width / 8 * y + endByte] |= endMask;
		return screen;
	}
	// print out the whole screen according to the width
	void printScreen (byte[] screen, int width)
	{
		int height = screen.length / width;
		int numByte = width / 8;
		int index = 0;
		// print out row by row
		for (int row = 0; row < height; row ++)
		{
			for (int i = 0; i < numByte; i ++)
			{
				// one byte as one unit, there is space between two bytes
				System.out.print(byteToStr(screen[index]) + " ");
				index ++;
			}
			System.out.println();
		}
		System.out.println();
	}
	// convert byte to string
	String byteToStr (byte x)
	{
		StringBuilder strB = new StringBuilder();
		for (int i = 0; i < 8; i ++)
		{
			int bitVal = (x >> i) & 1;
			strB.append(bitVal);
		}
		return strB.reverse().toString();
	}
}