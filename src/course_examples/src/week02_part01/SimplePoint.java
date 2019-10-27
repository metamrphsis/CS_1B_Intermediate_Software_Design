package week02_part01;

public class SimplePoint
{
	private int x;	  // 	QUESTION: Do we need our attributes to be publically accessible by other classes?
	private int y;
	private int [] rgb;

	public SimplePoint()
	{
		x = 0;
		y = 0;

		rgb = new int[3];
		rgb[0] = 0;	// red
		rgb[1] = 0; // green
		rgb[2] = 0; // blue
	}

	public SimplePoint(int x, int y, int [] color)
	{
		this.x = x;
		this.y = y;

		rgb = new int[3];
		rgb[0] = 0;	// red
		rgb[1] = 0; // green
		rgb[2] = 0; // blue
	}

	public boolean equals(Object other)
	{
		if (other instanceof SimplePoint)
		{
			SimplePoint myVar = (SimplePoint) other;



			boolean isSame = (this.x == myVar.x) && (this.y == myVar.y);
			return isSame;
		}
		else
		{
			return false;
		}
	}

	public String toString()
	{
		String result;
		result = "" + x;
		result += ",";
		result += y;
		return result;
	}
}






