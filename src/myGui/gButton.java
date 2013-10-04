package myGui;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

public class gButton extends GuiContainer{
	
	private boolean _isHovering;
	private boolean _isPressing;
	private boolean _wasPressing;
	private boolean _wasClicked;
	
	private gLabel _label;
	
	private Color _hoverBackground;
	private Color _pressBackground;
	
	public gButton(String label, float x, float y, int offset)
	{
		super(x, y, 0, 0);
		_isHovering = false;
		_isPressing = false;
		_wasPressing = false;
		_hoverBackground = Color.lightGray;
		_pressBackground = Color.darkGray;
		
		_label = this.addLabel(label, offset);
		_height = _width = _label.getHeight();
		
	}
	
	public gButton(String label, float x, float y, float width)
	{
		super(x, y, width, 0);
		_isHovering = false;
		_isPressing = false;
		_wasPressing = false;
		_hoverBackground = Color.lightGray;
		_pressBackground = Color.darkGray;
		
		_label = this.addLabel(label);
		_height = _label.getHeight() + 3* _padding;
	}
	
	public gButton(float x, float y, float width, float height)
	{
		super(x, y, width, height);
		_isHovering = false;
		_isPressing = false;
		_wasPressing = false;
		_hoverBackground = Color.lightGray;
		_pressBackground = Color.darkGray;
		
		_label = this.addLabel("");
	}
	
	public gLabel getLabel()
	{
		return _label;
	}
	
	public boolean isDown()
	{
		return _isPressing;
	}
	
	public boolean wasClicked()
	{
		return _wasClicked;
	}
	
	public void setHoverColor(Color color)
	{
		_hoverBackground = color;
	}
	
	public void setPressedColor(Color color)
	{
		_pressBackground = color;
	}
	
	public void update()
	{
		if (Mouse.getX() >= _x && Mouse.getX() <= _x + _width
				&& Mouse.getY() <= 600 - _y && Mouse.getY() >= 600 - _y - _height)
			_isHovering = true;
		else _isHovering = false;
		
		if (_isHovering && Mouse.isButtonDown(0))
			_isPressing = true;
		
		if(!Mouse.isButtonDown(0))
			_isPressing = false;

		
		if (_isPressing && !_wasPressing)
			_wasClicked = true;
		else _wasClicked = false;
		
		if (_isPressing)
			_wasPressing = true;
		else _wasPressing = false;
	}
	
	public void render()
	{
		if (_isPressing)
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
			_pressBackground.bind();
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
		else if (_isHovering)
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
			_hoverBackground.bind();
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
		else super.render();
	}
}
