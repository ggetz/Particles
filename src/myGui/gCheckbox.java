package myGui;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

public class gCheckbox extends gButton{
	
	private Color _checkColor;
	
	private boolean _isChecked;
	
	public gCheckbox(String s, float x, float y, int offset)
	{
		super(s, x, y, offset);
		_isChecked = false;
		_checkColor = Color.black;
	}
	
	public boolean isChecked()
	{
		return _isChecked;
	}
	
	public void check()
	{
		_isChecked = true;
	}
	
	public void update()
	{
		super.update();
		if (wasClicked())
			_isChecked = !_isChecked;
	}
	
	public void render()
	{
		super.render();
		
		if (_isChecked)
		{
		//check
		_checkColor.bind();
			GL11.glPushMatrix();
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2f( _x + 3*_border.getWidth(), (_y + 3*_border.getWidth()));
				GL11.glVertex2f( _x + _width - 3*_border.getWidth(), (_y + 3*_border.getWidth()));
				GL11.glVertex2f( _x + _width - 3*_border.getWidth(), (_y + _height  - 3*_border.getWidth()));
				GL11.glVertex2f( _x + 3*_border.getWidth(), (_y + _height - 3*_border.getWidth()));
			GL11.glEnd();
			GL11.glPopMatrix();
		}
	}
}
