import java.awt.Color;
import java.awt.Point;

//Simple class to hold data for the ball and do it's own movement calculations
public class FooBall_Ball extends FooBall_PhysicsObj {
	
	
	//Create size of ball
	private int radius = size;
	
	public int getRadius() {
		return radius;
	}
	
	
	
	//Generate acceleration based on mouse position
	public void genAccel(Point mouse, Point panel) {
		//Get the mouse position as useable data
		float mouseX = (float) (mouse.getX()-panel.getX()) - radius*2;
		float mouseY = (float) (mouse.getY()-panel.getY()) - radius*2;
		//Generate an amount of acceleration based on mouse position relative to ball position
		xAcc += ((mouseX - (x - width/2))*(0.0025)*accelRate);
		yAcc += ((mouseY - (y - height/2))*(0.0025)*accelRate);
	}
	
	public void init() {
		height = radius*2;
		width = radius*2;
	}
	
}
