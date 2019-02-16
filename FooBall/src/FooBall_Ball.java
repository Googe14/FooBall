
//Simple class to hold data for the ball and do it's own movement calculations
public class FooBall_Ball {
	
	//Create size of ball
	int width;
	int height;
	
	//Create rate of speed (in/de)crease
	float decay;
	float accelRate;
	
	//Create position
	int x;
	int y;
	
	//Create movement
	int xVel;
	int yVel;
	
	//Create acceleration
	int xAcc;
	int yAcc;
	
	//Setters for details of ball
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void setVel(int xVel, int yVel) {
		this.xVel = xVel;
		this.yVel = yVel;
	}
	public void setAcc(int xAcc, int yAcc) {
		this.xAcc = xAcc;
		this.yAcc = yAcc;
	}
	
	public void setRate(float decay, float accelRate) {
		this.decay = decay;
		this.accelRate = accelRate;
	}
	
}
