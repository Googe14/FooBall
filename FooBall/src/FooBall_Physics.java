import java.util.ArrayList;

public class FooBall_Physics {

	public void transferEnergy(FooBall_PhysicsObj obj1, FooBall_PhysicsObj obj2) {
		//Original force swap of 2 balls
		obj1.applyForce(obj2.getVelX()*obj2.getMass(), obj2.getVelY()*obj2.getMass());
		obj2.setVel(0, 0);
		               
		obj2.applyForce(obj1.getVelX()*obj1.getMass(), obj1.getVelY()*obj1.getMass());
		obj1.setVel(0, 0);
	}
	
	//Collide balls
		void collideBalls(FooBall_Ball ball1, FooBall_Ball target) {

			//First attempt at 2D elastic collisions
			/*
			//Get ball positions
			int x1 = ball1.getX();
			int y1 = ball1.getX();
			
			int x2 = target.getX();
			int y2 = target.getY();
			//Ball masses
			float m1 = ball1.getMass();
			float m2 = target.getMass();
			//Velocity directions
			float[] v1 = new float[2];
			float[] v2 = new float[2];

			//Find normal between balls
			float normal = (float) Math.atan2((double)(y1-y2), (double)(x1-x2));
			//Find direction of balls
			float v1a = (float) Math.atan(((m2 * Math.sin(((double)normal)) / (m1 + (m2 * Math.cos((double)normal))))));
			float v2a = (float) (((Math.PI - ((double)normal))/2));
			
			//Find velocity magnitudes
			float v1m = (float) (Math.sqrt((m1*m1) + (m2*m2) + (m1*2*m2*Math.cos(normal)))/(m1*m2));
			float v2m = (float) (v1a * ((2*m1)/(m1+m2)) * Math.sin(normal/2));
			
			//Convert velocity angles into usable vectors of the correct distance
			v1[0] = (float) Math.cos(v1a)*v1m;
			v1[1] = (float) Math.sin(v1a)*v1m;
			
			v2[0] = (float) Math.cos(v2a)*v2m;
			v2[1] = (float) Math.sin(v2a)*v2m;
			
			//Assign these vectors to the ball objects
			ball1.applyForce(v1[0], v1[1]);
			target.applyForce(v2[0], v2[1]);
			
			ball1.setVel(0, 0);
			target.setVel(0, 0);
			*/
			//Made Redundant due to non-consideration of original velocities and confusion between positive and negative normal
			

			/*
			//Second attempt at 2D elastic collisions, almost full functional but gets confused about positive and negative normal, cannot find fix to that and so moved onto another strategy
			
			//Get ball positions
			int x1 = ball1.getX();
			int y1 = ball1.getY();
			
			int x2 = target.getX();
			int y2 = target.getY();
			//Get mass of balls
			float m1 = ball1.getMass();
			float m2 = target.getMass();
			//Get ball velocities
			float v1x = ball1.getVelX();
			float v1y = ball1.getVelY();
			float v1 = (float) Math.sqrt(v1x*v1x + v1y*v1y);
			
			float v2x = target.getVelX();
			float v2y = target.getVelY();
			float v2 = (float) Math.sqrt(v2x*v2x + v2y*v2y);
			
			//Get angles of balls
			float a1 = (float) Math.atan2(v1y, v1x);
			float a2 = (float) Math.atan2(v2y, v2x);
			
			//System.out.println(Math.toDegrees(a1) + ", " + Math.toDegrees(a2));
			
			//Convert velocities into ones usable by equation
			v1x = (float) (v1 * Math.cos(a1));
			v1y = (float) (v1 * Math.sin(a1));
			
			v2x = (float) (v2 * Math.cos(a2));
			v2y = (float) (v2 * Math.sin(a2));
			
			float normal = (float) (Math.atan2(Math.cos(y2-y1), Math.sin(x2-x1)));
			
			System.out.println(Math.toDegrees(normal));
			
			//System.out.println(x2-x1);
			
			try {
				if((normal < 0)) {
					normal = (float) (Math.PI - normal);
				
					System.out.println("Improper bounce");
				}
			} catch(Exception e){System.out.println("Error");}
			
			
			//Get final velocities
			float fv1x = (float) (((((v1	*	Math.cos(a1-normal)	*	(m1-m2))	+	(2*m2*v2*Math.cos(a2-normal)))	/	(m1+m2))	*	Math.cos(normal))	+	(v1*Math.sin(a1-normal)	*	Math.sin(normal)));
			float fv1y = (float) (((((v1	*	Math.cos(a1-normal)	*	(m1-m2))	+	(2*m2*v2*Math.cos(a2-normal)))	/	(m1+m2))	*	Math.sin(normal))	+	(v1*Math.sin(a1-normal)	*	Math.cos(normal)));

			float fv2x = (float) (((((v2	*	Math.cos(a2-normal)	*	(m2-m1))	+	(2*m1*v1*Math.cos(a1-normal)))	/	(m2+m1))	*	Math.cos(normal))	+	(v2*Math.sin(a2-normal)	*	Math.sin(normal)));
			float fv2y = (float) (((((v2	*	Math.cos(a2-normal)	*	(m2-m1))	+	(2*m1*v1*Math.cos(a1-normal)))	/	(m2+m1))	*	Math.sin(normal))	+	(v2*Math.sin(a2-normal)	*	Math.cos(normal)));
			
			ball1.applyForce(fv1y, fv1x);
			target.applyForce(fv2y, fv2x);
			
			ball1.setVel(0, 0);
			target.setVel(0, 0);
			*/
			
			
			
			//Attempt to get balls to bounce of each other completely by myself, was about as (in)effective as other attempts from wikipedia
			/*
			//Get ball positions
			int x1 = target.getX();
			int y1 = target.getY();
			
			int x2 = ball1.getX();
			int y2 = ball1.getY();

			//Get velocity of balls
			float v1x = target.getVelX();
			float v1y = target.getVelY();
			//Get magnitude
			float v1m = (float) Math.sqrt((v1x*v1x) + (v1y*v1y));
			//Get tangent
			float tangent = -1/((float)(y2-y1)/(x2-x1));
			
			float tangentx = tangent;
			float tangenty = 1;
			
			//Avoid problems with infinity
			if(y2-y1==0) {
				tangentx = 0;
				tangenty = 1;
			} else if(x2-x1 == 0) {
				tangentx = 1;
				tangenty = 0;
			} else {
				
				float tangentm = (float) Math.sqrt(tangentx*tangentx + 1);

				tangentx = tangentx/tangentm;
				tangenty = 1/tangentm;
			}
			
			
			float normal = (float)(y2-y1)/(x2-x1);
			
			
			
			
			target.applyForce(tangentx*v1m, tangenty*v1m);
			target.setVel(0, 0);
			*/
			
			
			//Working ball bounces, unfortunately just copied from a tutorial :(
			
			//Get ball positions
			int x1 = ball1.getX();
			int y1 = ball1.getY();
			
			int x2 = target.getX();
			int y2 = target.getY();
			//Get ball masses
			float m1 = ball1.getMass();
			float m2 = target.getMass();
			//Get ball velocities
			float v1x = ball1.getVelX();
			float v1y = ball1.getVelY();
			
			float v2x = target.getVelX();
			float v2y = target.getVelY();
			//Get distance between balls
			float balld = (float) Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
			//Create normal with ball positions and distance
			float nx = (x2-x1) / balld;
			float ny = (y2-y1) / balld;
			//Create tangent from normal
			float tx = -ny;
			float ty = nx;
			//Dot products of tangent
			float dpTan1 = v1x * tx + v1y * ty;
			float dpTan2 = v2x * tx + v2y * ty;
			
			//Dot products of normal
			float dpNorm1 = v1x * nx + v1y * ny;
			float dpNorm2 = v2x * nx + v2y * ny;
			//Get conserved eneragy scalar
			float fm1 = (dpNorm1 * (m1-m2) + 2.0f * m2	* dpNorm2) / (m1+m2);
			float fm2 = (dpNorm2 * (m2-m1) + 2.0f * m1	* dpNorm1) / (m1+m2);
			
			//Bounce balls
			//Transfer force to other balls
			ball1.applyAcc(tx*dpTan1 + nx * fm1, ty*dpTan1 + nx * fm1);
			target.applyAcc(tx*dpTan2 + nx * fm2, ty*dpTan2 + nx * fm2);
			
			//Reset velocity as it has been transferred to another ball (and avoid multiple collisions from creating energy)
			ball1.setVel(0, 0);
			target.setVel(0, 0);
			
		}
		
		void separateBalls(FooBall_Ball ball1, FooBall_Ball target) {
			//Get ball positions
			int x1 = ball1.getX();
			int y1 = ball1.getY();
			
			int x2 = target.getX();
			int y2 = target.getY();
			//Get ball sizes
			int r1 = ball1.getRadius();
			int r2 = target.getRadius();
			//Create vector for ball point differences
			float vx = x2-x1;
			float vy = y2-y1;
			//Get original distance apart of balls
			int px = (int)vx;
			int py = (int)vy;
			//Get target distance apart of balls and set it as difference
			int td = r1+r2;
			td -= (int)Math.sqrt(px*px + py*py) - 2;
			if(vx != 0 || vy != 0) {
				//Get length of vector
				float vl = (float)Math.sqrt((vx*vx + vy*vy));
				//Turn into unit vector
				vx = vx/vl;
				vy = vy/vl;
			}
			
			//Move balls out of each other
			target.setPos(target.getX()+(vx*td/2), target.getY()+(vy*td/2));
			ball1.setPos(ball1.getX()-(vx*td/2), ball1.getY()-(vy*td/2));

			
		}
		
		//Check if the given two balls are colliding
		void compareBalls(FooBall_Ball ball1, FooBall_Ball ball2, boolean realistic) {
			//Get coordinates of balls
			int x1 = ball1.getX();
			int x2 = ball2.getX();
			
			int y1 = ball1.getY();
			int y2 = ball2.getY();
			//Get the distance between those coordinates
			int distance = (int)Math.sqrt((x2-x1)*(x2-x1)+ (y2-y1)*(y2-y1));
			//Check if they are touching
			if(distance <= ball1.getRadius() + ball2.getRadius()) {
				//Move balls out of each other as not to get stuck inside each other
				separateBalls(ball1, ball2);
				//Make colliding balls bounce off each other
				if(realistic) {
					collideBalls(ball1, ball2);
				} else {
					transferEnergy(ball1, ball2);
				}
			}
		}
		
		
		//Check if all the balls have collided
		void checkCollisions(ArrayList<FooBall_Ball> ball, boolean realistic) {
			int n = ball.size();
			//Got to each element of the array
			for(int i=0; i<n; i++) {
				//Go to each element on the array again
				for(int j=0; j<n; j++) {
					//Skip comparing to self or if ball has already bounced
					if(i == j) {
						break;
					}
					compareBalls(ball.get(i), ball.get(j), realistic);
				}
			}
		}
		
		//Check if ball in x,y position is overlapping with any other ball.
		boolean checkForSpace(ArrayList<FooBall_Ball> ball, FooBall_Ball target) {
			//Get position of target ball
			int x1 = target.getX();
			int y1 = target.getY();
			
			for(int i=0; i<ball.size(); i++) {
				if(ball.get(i).getId() != target.getId()) {
				
					//Get position of other balls
					int x2 = ball.get(i).getX();
					int y2 = ball.get(i).getY();
					
					//Get the distance between those coordinates
					int distance = (int)Math.sqrt((x2-x1)*(x2-x1)+ (y2-y1)*(y2-y1));
					//Check if they are touching
					if(distance <= ball.get(i).getRadius() + target.getRadius()) {
						return false;
					}
				}
			}
			//Balls are not touching any others
			return true;
			
		}
	
	
}
