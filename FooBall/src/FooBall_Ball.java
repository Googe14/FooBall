import java.awt.Color;
import java.awt.Point;

//Simple class to hold data for the ball and do it's own movement calculations
public class FooBall_Ball {
	
	//Create size of ball
	int width = 20;
	int height = 20;
	
	//Set default colour of balls
	Color colour = Color.BLUE;
	
	float gravity = 1f;
	//Create rate of speed (in/de)crease
	float decay = 1f;
	float accelRate = 1f;
	
	float weight = 1;
	
	//Set bounds for ball to bounce on
	int xBounds;
	int yBounds;
	
	//Create position
	float x;
	float y;
	
	//Create movement
	float xVel = 0f;
	float yVel = 0f;
	
	//Create acceleration
	float xAcc = 0;
	float yAcc = 0;
	
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
		//Change velocity of ball
		xVel += xAcc*accelRate;
		yVel += yAcc*accelRate;
		
		//make acceleration opposite the balls direction to slow it down
		xAcc = xVel * -0.01f * decay * weight;
		yAcc = yVel * -0.01f * decay * weight + gravity/2;
		
		//Move the ball
		y += yVel;
		x += xVel;
		
		//Bounce the ball
		if(x >= xBounds-width) {
			xVel *= -0.95;
			x = xBounds-width;
		}
		if(x <= 0) {
			xVel *= -0.95;
			x = 0;
		}
		
		if(y >= yBounds-width) {
			yVel *= -0.95;
			y = yBounds-height;
		}
		if(y <= 0) {
			yVel *= -0.95;
			y = 0;
		}
	}
	
	public void randomiseColour() {
		colour = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
	}
	
}
