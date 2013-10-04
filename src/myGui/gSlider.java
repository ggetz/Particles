package myGui;

public class gSlider extends GuiElement{
	
	private gSliderHandle _handle;
	private gLabel _label;
	
	private float _minValue;
	private float _maxValue;
	
	public gSlider(float min, float max, gSliderHandle handle)
	{
		_minValue = min;
		_maxValue = max;
		_handle = handle;
	}
	
	public float getCurrentValue()
	{
		return _minValue + _handle.getPosition()* (_maxValue - _minValue);
	}
	
	public float getMinValue()
	{
		return _minValue;
	}
	
	public float getMaxValue()
	{
		return _maxValue;
	}
	
	public gSliderHandle getHandle()
	{
		return _handle;
	}
	
	public gLabel getLabel()
	{
		return _label;
	}
}
