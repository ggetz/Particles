package myGui;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.FontUtils;

import java.awt.Font;

public class gLabel extends GuiElement{
	private TrueTypeFont _font;
	private Color _color;
	private String _label;
	private int _style;
	
	protected float _x;
	protected float _y;
	
	public gLabel(String s, TrueTypeFont f)
	{
		super();
		_font = f;
		_color = Color.black;
		_label = s;
		_style = Font.PLAIN;
	}
	
	public gLabel(String s, float x, float y, float w)
	{
		_x = x;
		_y = y;
		_label = s;
		_style = Font.PLAIN;
		Font awtFont = new Font("Arial", _style, 14);
		_font = new TrueTypeFont(awtFont, true);
		_color = Color.black;
		_width = w;
		_height = 14;
	}
	
	public void setColor(Color c)
	{
		_color = c;
	}
	
	public float getY()
	{
		return _y;
	}
	
	public void setStyle(int style)
	{
		_style = style;
		Font awtFont = new Font("Arial", _style, 14);
		_font = new TrueTypeFont(awtFont, true);
	}
	
	public void updateLabel(String label)
	{
		_label = label;
	}
	
	public void render()
	{		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		_color.bind();
		//_font.drawString(_x, _y, _label, _color);
		FontUtils.drawCenter(_font, _label, (int)_x, (int)_y, (int)_width, _color);
		
		GL11.glDisable(GL11.GL_BLEND);
	}
}
