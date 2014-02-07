
public abstract class Shape implements Nameable
{
	public enum Color { RED, ORANGE, YELLOW };
	
	public String askName()  // not abstract definition exists
	// subclasses simply call super(obj.askName())
	// no need to rewrite it in the subclasses
	{
		return _name;
	}
	
	public Color askColor()  // not abstract
	{
		return _color;
	}
	
	@Override
	public String toString() // not abstract
	{
		return "Name: " + _name + ", Color " + _color.name();
		
	}
	
	public abstract double askPerimeter();
	
	public abstract double askArea();
	
	protected Shape(String name, Color color)
	// because only intended to be called by subclasses
	{
		_name = name;
		_color = color;
	}
	
	private String _name;
	private Color _color;

}
