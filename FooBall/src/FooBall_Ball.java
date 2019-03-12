import java.awt.Point;

//Simple class to hold data for the ball and do it's own movement calculations
public class FooBall_Ball extends FooBall_PhysicsObj {
	
	
	int id = 0;
	
	//Create size of ball
	private int radius = size;
	
	protected float accelRate = 1f;
	
	public int getRadius() {
		return radius;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public void setSize(int size) {
		radius = size/2;
		this.size = size;
		mass = size*density/20;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
		setSize(radius*2);
	}
	
	//Generate acceleration based on mouse position
	public void genAccel(Point mouse, Point panel) {
		//Get the mouse position as useable data
		float mouseX = (float) (mouse.getX()-panel.getX()) - radius*2;
		float mouseY = (float) (mouse.getY()-panel.getY()) - radius*2;
		//Generate an amount of acceleration based on mouse position relative to ball position
		xAcc += ((mouseX - (x))*(0.0025)*accelRate/mass);
		yAcc += ((mouseY - (y))*(0.0025)*accelRate/mass);
	}
	
	public void setAccel(float accelRate) {
		this.accelRate = accelRate;
	}
	
	//Redundant
	public void init(int id) {
		this.id = id;
	}
	
}
