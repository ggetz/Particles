package myGui;

import org.lwjgl.opengl.GL11;

public class gLinebreak extends GuiContainer{
	
	public gLinebreak(float x, float y, float width)
	{
		_x = x;
		_y = y;
		_width = width;
		_border = new Border();
		_height = _border.getWidth();
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
	}
}
