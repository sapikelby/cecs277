
public class Rectangle extends Shape implements Displayable
{
	public Rectangle(String name, Shape.Color color, double length, double width)
	{
		super(name, color);
		_length = length;
		_width = width;
	}
	
	public Rectangle(Rectangle r)
	{
		super(r.askName(), r.askColor());
		_length = r._length;
		_width = r._width;
	}
	
	public double getLength()
	{
		return _length;
	}
	
	public void setLength(double length)
	{
		_length = length;
	}
	
	public double getWidth()
	{
		return _width;
	}
	
	public void setWidth(double width)
	{
		_width = width;
	}
	@Override
	public double askArea() 
	{
		return _length * _width;
	}

	@Override
	public double askPerimeter() 
	{
		return 2 * _length + 2 * _width;
	}
	
	@Override
	public String toString()
	{
		String value = "Rectangle: " + super.toString();
		return value + ", Length = " + _length + ", Width = " + _width;
		
	}
	
	public void Display()
	{
		System.out.println(toString());
	}
	
	double _length;
	double _width;

}
