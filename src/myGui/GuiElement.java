package myGui;

public class GuiElement {

	protected float _x; 
	protected float _y;
	
	protected float _height;
	protected float _width;
	
	public GuiElement()
	{
		float _x = 0;
		float _y = 0;
		
		float _height = 0;
		float _width = 0;
	}
	
	public GuiElement(float x, float y, float w, float h)
	{
		float _x = x;
		float _y = y;
		
		float _height = h;
		float _width = w;
	}
	
	public void render()
	{
		
	}
	
	public float getX()
	{
		return _x;
	}
	public float getY()
	{
		return _y;
	}
	public float getHeight()
	{
		return _height;
	}
	public float getWidth()
	{
		return _width;
	}

}
