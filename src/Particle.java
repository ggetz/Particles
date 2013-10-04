import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;


public class Particle {
	
	private float x,y;
	
	private float radius;
	private int numSides;
	
	private Color color;
	private float alpha;
	
	private int age;
	private int lifetime;
	
	private boolean blur;
	
	private boolean shouldDestroy;
	
	public Particle(float x, float y, float radius, int numSides, Color color, float alpha)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.numSides = numSides;
		this.color = color;
		this.alpha = alpha;
		age = 0;
		lifetime = 10000;
		blur = false;
		shouldDestroy = false;
	}
	
	public void update()
	{
		updateAge();
		updateColor();
		updateSize();
		updateShape();
		updatePosition();
	}
	
	public void render()
	{
		color.bind();
		
			GL11.glPushMatrix();
			GL11.glTranslatef((int)x, (int)y, 0);
			GL11.glScalef(radius, radius, 1);
		
			GL11.glBegin(GL11.GL_TRIANGLE_FAN);
				GL11.glVertex2f(0, 0);
				for(int i = 0; i <= numSides; i++){ //NUM_PIZZA_SLICES decides how round the circle looks.
					double angle = Math.PI * 2 * i / numSides;
					GL11.glVertex2f((float)Math.cos(angle), (float)Math.sin(angle));
				}
			GL11.glEnd();
			GL11.glPopMatrix();
	}
	
	public void updateAge()
	{
		age++;
		if (age >= lifetime)
			shouldDestroy = true;
	}
	
	private void updateColor() 
	{
		if (alpha <= 0)
			shouldDestroy = true;
	}
	
	private void updateSize() 
	{
		if (radius <= 0)
			shouldDestroy = true;
	}
	private void updateShape() 
	{
		if (radius <= 2)
			shouldDestroy = true;
	}
	private void updatePosition() 
	{
		if (x + radius < 0 || y + radius < 0)
			shouldDestroy = true;
	}
	
}
