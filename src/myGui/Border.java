package myGui;
import org.newdawn.slick.Color;

public class Border {
	
	private int _width;
	private Color _color;
	
	public Border()
	{
		_width = 1;
		_color = Color.black;
	}
	
	public Border(int w, Color c)
	{
		_width = w;
		_color = c;
	}
	
	public Color getColor()
	{
		return _color;
	}
	
	public int getWidth()
	{
		return _width;
	}
}
