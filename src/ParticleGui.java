import myGui.*;

import java.awt.Font;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

public class ParticleGui {
	
	private static final int SCRREEN_HEIGHT = 600;
	/* Menu Components */
	GuiContainer menu;
	GuiContainer colorMenu;
	gSlider redSlider;
	gSlider greenSlider;
	gSlider blueSlider;
	GuiContainer colorExample;
	
	/** position of quad */
	float x = 400, y = 300;
	/** angle of quad rotation */
	float rotation = 0;
	
	/** time at last frame */
	long lastFrame;
	
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;

	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(1200, 600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer
		
		setUpMenu();
			
		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			
			update(delta);
			renderGL();

			Display.update();
			Display.sync(60); // cap fps to 60fps
		}
		
		Display.destroy();
	}
	
	public void setUpMenu()
	{
		menu = new GuiContainer(900, 0, 300, 0);
			menu.setBackground(Color.darkGray);
			menu.setBorder(new Border(2, Color.lightGray));
			menu.setPadding(10f);
			menu.addLabel("OPTIONS").setColor(Color.lightGray);
	
		colorMenu = menu.addContainer();
			colorMenu.setBackground(Color.lightGray);
			colorMenu.setBorder(new Border(0, Color.lightGray));
			colorMenu.setPadding(5f);
			colorMenu.addLabel("COLOR").setColor(Color.darkGray);
			
		redSlider = colorMenu.addHorizontalSlider(0, 256);
			redSlider.getHandle().setBackground(Color.red);
			redSlider.getHandle().setHoverColor(Color.red);
			redSlider.getHandle().setPressedColor(Color.red);
			redSlider.getHandle().setBorder(new Border(1, new Color(.5f, .1f, .1f)));
		greenSlider = colorMenu.addHorizontalSlider(0, 256);
			greenSlider.getHandle().setBackground(Color.green);
			greenSlider.getHandle().setBorder(new Border(1, new Color(.1f, .5f, .1f)));
		blueSlider = colorMenu.addHorizontalSlider(0, 256);
			blueSlider.getHandle().setBackground(Color.blue);
			blueSlider.getHandle().setBorder(new Border(1, new Color(.1f, .1f, .5f)));
		colorExample = colorMenu.addContainer();
			colorExample.setPadding(8f);
			colorExample.addLabel("preview");
			
		colorMenu.addLinebreak().setBorder(new Border(0, Color.lightGray));
		
		menu.addLinebreak();
	}
	
	public void update(int delta) {
		// rotate quad
		rotation += 0.15f * delta;
		
		x = Mouse.getX();
		y = SCRREEN_HEIGHT - Mouse.getY();
		
		// keep quad on the screen
		if (x < 0) x = 0;
		if (x > 1200) x = 1200;
		if (y < 0) y = 0;
		if (y > 600) y = 600;
		
		updateFPS(); // update FPS Counter
		
		redSlider.getHandle().update();
		greenSlider.getHandle().update();
		blueSlider.getHandle().update();
		
		colorExample.setBackground(
			new Color(redSlider.getCurrentValue()/256, 
					greenSlider.getCurrentValue()/256, 
					blueSlider.getCurrentValue()/256));
	}
	
	/** 
	 * Calculate how many milliseconds have passed 
	 * since last frame.
	 * 
	 * @return milliseconds passed since last frame 
	 */
	public int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
	 
	    return delta;
	}
	
	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	
	public void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 1200, 600, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	public void renderGL() {
		// Clear The Screen And The Depth Buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		// R,G,B,A Set The Color To Blue One Time Only
		GL11.glColor3f(redSlider.getCurrentValue()/256, greenSlider.getCurrentValue()/256, blueSlider.getCurrentValue()/256);

		// draw quad
		GL11.glPushMatrix();
			GL11.glTranslatef(x, y, 0);
			GL11.glRotatef(rotation, 0f, 0f, 1f);
			GL11.glTranslatef(-x, -y, 0);
			
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2f(x - 50, y - 50);
				GL11.glVertex2f(x + 50, y - 50);
				GL11.glVertex2f(x + 50, y + 50);
				GL11.glVertex2f(x - 50, y + 50);
			GL11.glEnd();
		GL11.glPopMatrix();
		
		menu.render();
	}
	
	public static void main(String[] argv) {
		ParticleGui gui = new ParticleGui();
		gui.start();
	}
}