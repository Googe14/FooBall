import java.awt.Color;

public class FooBall_PhysicsObj {


	
	//Create size of ball
	protected int size = 20;
	
	//Boolean to prevent ball from colliding with more than 1 other ball and creating energy that shouldn't exist
	boolean collided = false;
	
	//Set default colour of Physics objects
	protected Color colour = Color.BLUE;
	
	protected float gravity = 1f;
	//Create rate of speed (in/de)crease
	protected float decay = 1f;
	protected float accelRate = 1f;
	
	protected float density = 1;

	protected float mass = size*density/20;
	
	//Set bounds for object to collide with
	protected int xBounds;
	protected int yBounds;
	
	//Create position
	protected float x = 0;
	protected float y = 0;
	
	//Create movement
	protected float xVel = 0f;
	protected float yVel = 0f;
	
	//Create acceleration
	protected float xAcc = 0;
	protected float yAcc = 0;
	
	public void setSize(int size) {
		this.size = size;
		mass = size*density/20;
	}
	
	public void setDensity(float density) {
		this.density = density;
		mass = size*density/20;
	}
	
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
	public void applyAcc(float xForce, float yForce) {
		xAcc += xForce;
		yAcc += yForce;
	}
	//Apply force in direction
	public void applyForce(float xForce, float yForce) {
		xAcc += xForce/mass;
		yAcc += yForce/mass;
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
		
		if(!collided) {
		//make acceleration opposite the balls direction to slow it down
		xAcc = xVel * -0.01f * decay;
		yAcc = yVel * -0.01f * decay;
		} else {
			xAcc = 0;
			yAcc = 0;
		}
		
		//Apply gravity
		//yAcc += gravity/2;
		applyAcc(0, gravity/2);

		//Move the ball
		y += yVel;
		x += xVel;
		
		//Bounce the ball
		//Bounce on Right side
		if(x >= xBounds-size/2) {
			xVel *= -0.95;
			x = xBounds-size/2;
		}
		//left side
		if(x <= size/2) {
			xVel *= -0.95;
			x = size/2;
		}
		//bottom
		if(y >= yBounds-size/2) {
			yVel *= -0.95;
			y = yBounds-size/2;
		}
		//top
		if(y <= size/2) {
			yVel *= -0.95;
			y = size/2;
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
