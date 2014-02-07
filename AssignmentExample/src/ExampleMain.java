import java.util.ArrayList;

public class ExampleMain 
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Point p = new Point(0,0);
		Circle c = new Circle("C1", Shape.Color.RED, 5.0, p);
		Rectangle r = new Rectangle("R1", Shape.Color.ORANGE, 3.0, 6.00);
		Square s = new Square("S1", Shape.Color.YELLOW, 10);
		
		ArrayList<Displayable> objects = new ArrayList<Displayable>();
		objects.add(p);
		objects.add(c);
		objects.add(r);
		objects.add(s);
		
		// polymorphism within the for loop
		for(Displayable obj : objects)
		{
			obj.Display();
		}
	}
}
