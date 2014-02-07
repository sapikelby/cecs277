
public class Square extends Shape implements Displayable
{
	public Square(String name, Shape.Color color, double side)
	{
		// square is implemented in terms of a rectangle
		// when we create a square, we create a rectangle within the code
		// length = width = side
		super(name, color);
		theRectangle = new Rectangle("Owned by " + name, color, side, side);
	}
	
	public Square(Square sq)
	{
		super(sq.askName(), sq.askColor());
		theRectangle = new Rectangle(sq.theRectangle);
	}
	
	@Override
	public double askArea() 
	{
		return theRectangle.askArea();
	}

	@Override
	public double askPerimeter() 
	{
		return theRectangle.askPerimeter();
	}
	
	@Override
	public String toString()
	{
		String value = "Square: " + super.toString();
		return value + ", Side = " + theRectangle.getLength();
	
	}
	
	public void Display()
	{
		System.out.println(toString());
	}
	
	private Rectangle theRectangle;

}
