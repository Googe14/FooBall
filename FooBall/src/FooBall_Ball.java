import java.awt.Color;
import java.awt.Point;

//Simple class to hold data for the ball and do it's own movement calculations
public class FooBall_Ball {
	
	//Create size of ball
	private int width = 50;
	private int height = 50;
	
	//Boolean to prevent ball from colliding with more than 1 other ball and creating energy that shouldn't exist
	boolean collided = false;
	
	//Set default colour of balls
	private Color colour = Color.BLUE;
	
	private float gravity = 1f;
	//Create rate of speed (in/de)crease
	private float decay = 0f;
	private float accelRate = 1f;
	
	private float weight = 1;
	
	//Set bounds for ball to bounce on
	private int xBounds;
	private int yBounds;
	
	//Create position
	private float x;
	private float y;
	
	//Create movement
	private float xVel = 0f;
	private float yVel = 0f;
	
	//Create acceleration
	private float xAcc = 0;
	private float yAcc = 0;
	
	//Setters for details of ball
	public void setPos(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public void setVel(float xVel, float yVel) {
		this.xVel = xVel;
		this.yVel = yVel;
	}
	public void setAcc(float xAcc, float yAcc) {
		this.xAcc = xAcc;
		this.yAcc = yAcc;
	}
	
	public void setRate(float decay, float accelRate) {
		this.decay = decay;
		this.accelRate = accelRate;
	}
	
	public void setBounds(int xBounds, int yBounds) {
		this.xBounds = xBounds;
		this.yBounds = yBounds;
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public int getX() {
		return (int)x;
	}
	public int getY() {
		return (int)y;
	}
	
	public boolean isCollided() {
		return collided;
	}
	public void setCollided() {
		collided = true;
	}

	//Apply force in direction
	public void applyForce(float xForce, float yForce) {
		xAcc += xForce;
		yAcc += yForce;
	}
	public float getVelX() {
		return xVel;
	}
	public float getVelY() {
		return yVel;
	}
	
	//Generate acceleration based on mouse position
	public void genAccel(Point mouse, Point panel) {
		//Get the mouse position as useable data
		float mouseX = (float) (mouse.getX()-panel.getX());
		float mouseY = (float) (mouse.getY()-panel.getY());
		//Generate an amount of acceleration based on mouse position relative to ball position
		xAcc += ((mouseX - (x - width/2))*(0.0025)*accelRate);
		yAcc += ((mouseY - (y - height/2))*(0.0025)*accelRate);
	}
	
	//Move the fooball
	public void move() {
		collided = false;
		
		//Change velocity of ball
		xVel += xAcc*accelRate;
		yVel += yAcc*accelRate;
		
		//make acceleration opposite the balls direction to slow it down
		xAcc = xVel * -0.01f * decay * weight;
		yAcc = yVel * -0.01f * decay * weight;
		
		//Move the ball
		y += yVel;
		x += xVel;
		
		//Apply gravity
		yAcc += gravity/2;
		
		//Bounce the ball
		//Bounce on Right side
		if(x >= xBounds-width) {
			xVel *= -0.95;
			x = xBounds-width;
		}
		//left side
		if(x <= 0) {
			xVel *= -0.95;
			x = 0;
		}
		//bottom
		if(y >= yBounds-width) {
			yVel *= -0.95;
			y = yBounds-height;
		}
		//top
		if(y <= 0) {
			yVel *= -0.95;
			y = 0;
		}
	}
	
	//Randomise colour of the ball
	public void randomiseColour() {
		setColour(new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
	}
	//set/getter for colour
	public Color getColour() {
		return colour;
	}
	public void setColour(Color colour) {
		this.colour = colour;
	}
	
}
