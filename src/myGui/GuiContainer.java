package myGui;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

public class GuiContainer extends GuiElement{	
	protected final float STAGE_HEIGHT = 600;
	
	protected float _padding;
	
	protected Color _background;
	protected Border _border;
	
	protected ArrayList<GuiElement> _elements;
	
	public GuiContainer()
	{
		_x = 0;
		_y = 0;
		
		_height = 0;
		_width = 0;
		
		_padding = 2f;
		
		_background = Color.white;
		_border = new Border();
		
		_elements = new ArrayList<GuiElement>();
	}
	
	public GuiContainer(float x, float y, float width, float height)
	{
		_x = x;
		_y = y;
		
		_height = height;
		_width = width;
		
		_padding = 2f;
		
		_background = Color.white;
		_border = new Border();
		
		_elements = new ArrayList<GuiElement>();
	}
	
	public void setBackground(Color color)
	{
		_background = color;
	}
	
	public void setBorder(Border b)
	{
		_border = b;
	}
	
	public void setPadding(float padding)
	{
		_padding = padding;
	}
	
	public void addElement()
	{
		GuiElement newElement;
		if (_elements.size() == 0)
			newElement = new GuiElement(_x + _padding, _y + _padding, _width - 2*_padding, 0);
		else
		{
			GuiElement last = _elements.get(_elements.size()-1);
			newElement = new GuiElement(_x + _padding, last.getY() + last.getHeight() + _padding, _width - 2*_padding, 0);
		}
		_elements.add(newElement);
		if (_height < newElement.getY() - _y + newElement.getHeight() + _padding)
			_height = newElement.getY() - _y  + newElement.getHeight() + _padding;
	}
	
	public GuiContainer addContainer()
	{
		GuiContainer newElement;
		if (_elements.size() == 0)
			newElement = new GuiContainer(_x + _padding, _y +_padding, _width - 2*_padding, 0);
		else
		{
			GuiElement last = _elements.get(_elements.size()-1);
			newElement = new GuiContainer(_x + _padding, last.getY() + last.getHeight() + _padding, _width - 2*_padding, 0);
		}
		_elements.add(newElement);
		if (_height < newElement.getY() - _y + newElement.getHeight() + _padding)
			_height = newElement.getY() - _y + newElement.getHeight() + _padding;
		return newElement;
	}
	
	public gLabel addLabel(String s)
	{
		gLabel newElement;
		if (_elements.size() == 0)
			newElement = new gLabel(s, _x + _padding, _y +_padding, _width - 2*_padding);
		else
		{
			GuiElement last = _elements.get(_elements.size()-1);
			newElement = new gLabel(s, _x + _padding, last.getY() + last.getHeight() + _padding,  _width - 2*_padding);
		}
		_elements.add(newElement);
		if (_height < newElement.getY() - _y + newElement.getHeight() + 2* _padding)
			_height = newElement.getY() - _y + newElement.getHeight() + 2* _padding;
		return newElement;
	}
	
	public gLabel addLabel(String s, float offset)
	{
		gLabel newElement;
		if (_elements.size() == 0)
			newElement = new gLabel(s, _x + _padding + offset, _y +_padding, _width - 2*_padding);
		else
		{
			GuiElement last = _elements.get(_elements.size()-1);
			newElement = new gLabel(s, _x + _padding + offset, last.getY() + last.getHeight() + _padding,  _width - 2*_padding);
		}
		_elements.add(newElement);
		if (_height < newElement.getY() - _y + newElement.getHeight() + 2* _padding)
			_height = newElement.getY() - _y + newElement.getHeight() + 2* _padding;
		return newElement;
	}
	
	public gLinebreak addLinebreak()
	{
		gLinebreak newElement;
		if (_elements.size() == 0)
			newElement = new gLinebreak(_x + 8*_padding, _y +_padding, _width - 16*_padding);
		else
		{
			GuiElement last = _elements.get(_elements.size()-1);
			newElement = new gLinebreak(_x + 8*_padding, last.getY() + last.getHeight() + _padding, _width - 16*_padding);
		}
		_elements.add(newElement);
		if (_height < newElement.getY() - _y + newElement.getHeight() + 4* _padding)
			_height = newElement.getY() - _y + newElement.getHeight() + _padding;
		return newElement;
	}
	
	public gButton addButton(String s)
	{
		gButton newElement;
		if (_elements.size() == 0)
			newElement = new gButton(s, _x + _padding + _width/4, _y +_padding, _width/2 - 2*_padding);
		else
		{
			GuiElement last = _elements.get(_elements.size()-1);
			newElement = new gButton(s, _x + _padding + _width/4, last.getY() + last.getHeight() + _padding,  _width/2 - 2*_padding);
		}
		_elements.add(newElement);
		if (_height < newElement.getY() - _y + newElement.getHeight() + 2* _padding)
			_height = newElement.getY() - _y + newElement.getHeight() + 2* _padding;
		return newElement;
	}
	
	public gCheckbox addCheckbox(String label, boolean checked)
	{
		gCheckbox newElement;
		if (_elements.size() == 0)
			newElement = new gCheckbox(label, _x + _padding + _width/8, _y +_padding, (int)_width*3/8);
		else
		{
			GuiElement last = _elements.get(_elements.size()-1);
			newElement = new gCheckbox(label, _x + _padding + _width/8, last.getY() + last.getHeight() + _padding, (int)_width*3/8);
		}
		if (checked)
			newElement.check();
		_elements.add(newElement);
		if (_height < newElement.getY() - _y + newElement.getHeight() + 2* _padding)
			_height = newElement.getY() - _y + newElement.getHeight() + 2* _padding;
		return newElement;
	}
	
	public gSlider addHorizontalSlider(float min, float max)
	{
		gSliderHandle newElement;
		if (_elements.size() == 0)
			newElement = new gSliderHandle(_width/8, _y + 2*_padding, 12, 14, false, _x + _padding + _width/8,  _x + _padding + _width*7/8);
		else
		{
			GuiElement last = _elements.get(_elements.size()-1);
			newElement = new gSliderHandle(_x + _padding + _width/8, last.getY() + last.getHeight() +2*_padding, 12, 14, false, _x + _padding + _width/8,  _x + _padding + _width*7/8);
		}
		gLinebreak line = new gLinebreak(newElement.getMin(), newElement.getY()+newElement.getHeight()/2, newElement.getMax()-newElement.getMin());
		_elements.add(line);
		_elements.add(newElement);
		if (_height < newElement.getY() - _y + newElement.getHeight() + 2* _padding)
			_height = newElement.getY() - _y + newElement.getHeight() + 2* _padding;
		gSlider slider = new gSlider(min, max, newElement);
		return slider;
	}
	
	public void render()
	{
		//border
		_border.getColor().bind();
			GL11.glPushMatrix();
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2f( _x, _y );
				GL11.glVertex2f( _x + _width, _y );
				GL11.glVertex2f( _x + _width, (_y + _height) );
				GL11.glVertex2f( _x, (_y + _height));
			GL11.glEnd();
			GL11.glPopMatrix();
		//background
		_background.bind();
			GL11.glPushMatrix();
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2f( _x + _border.getWidth(), (_y + _border.getWidth()));
				GL11.glVertex2f( _x + _width - _border.getWidth(), (_y + _border.getWidth()));
				GL11.glVertex2f( _x + _width - _border.getWidth(), (_y + _height  - _border.getWidth()));
				GL11.glVertex2f( _x + _border.getWidth(), (_y + _height - _border.getWidth()));
			GL11.glEnd();
			GL11.glPopMatrix();
		//elements	
		for (GuiElement e : _elements)
			e.render();
	}
	
}
