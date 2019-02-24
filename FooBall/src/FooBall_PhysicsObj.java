import java.awt.Color;
import java.awt.Point;

public class FooBall_PhysicsObj {


	
	//Create size of ball
	protected int size = 20;

	protected int width;
	protected int height;
	
	//Boolean to prevent ball from colliding with more than 1 other ball and creating energy that shouldn't exist
	boolean collided = false;
	
	//Set default colour of Physics objects
	protected Color colour = Color.BLUE;
	
	protected float gravity = 1f;
	//Create rate of speed (in/de)crease
	protected float decay = 0.2f;
	protected float accelRate = 1f;
	
	protected float mass = size/20;
	
	//Set bounds for object to collide with
	protected int xBounds;
	protected int yBounds;
	
	//Create position
	protected float x;
	protected float y;
	
	//Create movement
	protected float xVel = 0f;
	protected float yVel = 0f;
	
	//Create acceleration
	protected float xAcc = 0;
	protected float yAcc = 0;
	
	//Setters for details of object
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
	
	public float getMass() {
		return mass;
	}
	
	//Move the fooball
	public void move() {		
		//Change velocity of ball
		xVel += xAcc*accelRate;
		yVel += yAcc*accelRate;
		
		//make acceleration opposite the balls direction to slow it down
		if(!collided) {
			xAcc = xVel * -0.01f * decay * mass;
			yAcc = yVel * -0.01f * decay * mass;
			//Apply gravity
			yAcc += gravity/2;
		} else {
			xAcc = 0;
			yAcc = 0;
		}
		
		//Move the ball
		y += yVel;
		x += xVel;
		
		//Bounce the ball
		//Bounce on Right side
		if(x >= xBounds-width) {
			xVel *= -1;
			x = xBounds-width;
		}
		//left side
		if(x <= 0) {
			xVel *= -1;
			x = 0;
		}
		//bottom
		if(y >= yBounds-width) {
			yVel *= -1;
			y = yBounds-height;
		}
		//top
		if(y <= 0) {
			yVel *= -1;
			y = 0;
		}
		collided = false;

		//Cap minimum velocity 
		if(xVel <= 0.01 && xVel >= -0.01) {
			xVel = 0;
		}
		if(yVel <= 0.01 && yVel >= -0.01) {
			yVel = 0;
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
	
	public void init() {
		
		
		
	}
	
}
