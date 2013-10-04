package myGui;

import org.lwjgl.input.Mouse;

public class gSliderHandle extends gButton{
	
	private boolean _isVertical;
	
	private float _min;
	private float _max;
	
	public gSliderHandle(float x, float y, float width, float height, boolean vertical, float min, float max)
	{
		super (x, y, width, height);
		_isVertical = vertical;
		_min = min;
		_max = max;
	}
	
	public float getPosition()
	{
		float total = _max - _min;
		return (_x - _min) / total;
	}
	
	public float getMin()
	{
		return _min;
	}
	
	public float getMax()
	{
		return _max;
	}
	
	public void update()
	{
		super.update();
		
		if (isDown())
		{
			if (_isVertical)
			{
				if (Mouse.getY() >= _min && Mouse.getY() <= _max)
					_y = Mouse.getY();
			}
			else
			{
				if (Mouse.getX() >= _min && Mouse.getX() <= _max)
					_x = Mouse.getX() - _width/2;
			}
		}
	}

}
