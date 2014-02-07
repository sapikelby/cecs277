
public class Point implements Displayable
{
	public Point(double x, double y) 
	{
		_x = x;
		_y = y;
	}
	
	public Point(Point p)
	{
		_x = p._x;
		_y = p._y;
	}
	
	@Override
	public String toString()
	{
		return "Point: (" + _x + "," + _y + ")";
		
	}
	
	public void Display()
	{
		System.out.println(toString());
	}
	
	private double _x;
	private double _y;
}
