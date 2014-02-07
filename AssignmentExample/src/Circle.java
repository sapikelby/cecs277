
public class Circle extends Shape implements Displayable
{
	public Circle(String name, Shape.Color color, double radius, Point center)
	// standard constructor
	{
		super(name, color);
		_radius = radius;
		_center = center;
	}
	
	public Circle(Circle c) // copy constructor with diff. params
	// this provides a copy of the circle
	{
		super(c.askName(), c.askColor());
		_radius = c._radius;
		_center = new Point(c._center);  // new point in memory with same attributes
	}
	
	@Override
	public double askArea() 
	{
		return Math.PI * _radius * _radius;
	}

	@Override
	public double askPerimeter() 
	{
		return 2.0 * Math.PI * _radius;
	}
	
	public double getRadius()
	{
		return _radius;
	}
	
	public void setRadius(double r)
	{
		_radius = r;
	}
	
	public Point getCenter()
	{
		return _center;
	}
	
	public void setCenter(Point center)
	{
		_center = center;
	}
	
	@Override
	public String toString()
	{
		String value = "Circle: " + super.toString();
		return value + ", Radius = " + _radius + ", Center = " + _center;
		
	}
	
	public void Display()
	{
		System.out.println(toString());
	}
	
	private double _radius;
	private Point _center;

}
