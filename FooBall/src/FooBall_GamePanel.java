import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

//Extend JPanel to make the game panel an independent object
//Implements the Runnable object to allow certain code in this class to be run on a separate thread
//Multi-threading will be important when painting
public class FooBall_GamePanel extends JPanel implements Runnable{

	//Serialise class
	private static final long serialVersionUID = 1L;

	/*
	 		************************************************
			*********Multi-Threading implementation*********
			************************************************
	*/

	//Create new Thread object that will be used to run the game loop
	Thread runner;
	//Used to safely and properly start the Thread
    public void start() {
        if (runner == null) {
            runner = new Thread(this);
            runner.start();
        }
    }
	//Used to safely and properly stop the Thread
    public void stop() {
        if (runner != null) {
            //runner.stop();
            runner = null;
        }
    }
    //Will be run when the new Thread is started
	@Override
	public void run() {
		// TODO Auto-generated method stub
		isRunning = true;
		loop();
	}

	/*
	 		************************************************
	 		******End of Multi-Threading implementation*****
	 		************************************************
	 		***************Start of Game Loop***************
	 		************************************************
	*/
	
	
	// desired fps
	private final static int    MAX_FPS = 120;
	// maximum number of frames to be skipped
	private final static int    MAX_FRAME_SKIPS = 5;
	// the frame period
	private final static int    FRAME_PERIOD = 2000 / MAX_FPS;  
	 

	public void loop() {
	    long beginTime;     // the time when the cycle begun
	    long timeDiff;      // the time it took for the cycle to execute
	    int sleepTime;      // ms to sleep (<0 if we're behind)
	    int framesSkipped;  // number of frames being skipped 
	 
	    sleepTime = 0;
	 
	    while (isRunning) {
	    	this.requestFocus();
	        // in the surface
	        try {
	                beginTime = System.currentTimeMillis();
	                framesSkipped = 0;  // resetting the frames skipped
	                // update game state
	                update();
	                // render state to the screen
	                // draws the canvas on the panel
	                repaint();                
	                // calculate how long did the cycle take
	                timeDiff = System.currentTimeMillis() - beginTime;
	                // calculate sleep time
	                sleepTime = (int)(FRAME_PERIOD - timeDiff);
	 
	                if (sleepTime > 0) {
	                    // if sleepTime > 0 we're OK
	                    try {
	                        // send the thread to sleep for a short period
	                        // very useful for battery saving
	                        Thread.sleep(sleepTime);
	                    } catch (InterruptedException e) {}
	                }
	 
	                while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
	                    // we need to catch up
	                    // update without rendering
	                		update();
	                    // add frame period to check if in next frame
	                    sleepTime += FRAME_PERIOD;
	                    framesSkipped++;
	                }
	            }
	         finally {
	            // in case of an exception the surface is not left in
	            // an inconsistent state
	            }
	        }   // end finally
	    }
	
	/*
	 		************************************************
	 		*************End of Game Loop stuff*************
	 		************************************************
	*/
	
	//Painting area
	public void paintComponent(Graphics g) {
		setDoubleBuffered(true);
		//Refresh screen with a black background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//Paint Ball - iterate through each ball to paint it
		for(int i=0; i<ball.size(); i++) {
			//Set colour to that of the select ball
			g.setColor(ball.get(i).getColour());
			//Check if mouse is clicked down
			if(clicked) {
				//If so, draw a line connecting the mouse to the ball - before drawing ball so that the ball appears on the line
				g.drawLine((int)(mouse.getX() - this.getLocationOnScreen().getX()), (int)(mouse.getY()-this.getLocationOnScreen().getY()), (int)(ball.get(i).getX()), (int)(ball.get(i).getY()));
			}
			//Draw ball
			g.fillOval(ball.get(i).getX()-ball.get(i).getRadius(), ball.get(i).getY()-ball.get(i).getRadius(), ball.get(i).getRadius()*2, ball.get(i).getRadius()*2);
		}
		//Sync graphics toolkit - smoothes looped drawing when using some graphical drivers on linux machines
    	Toolkit.getDefaultToolkit().sync();
	}
	
	
	//Create Boolean to control if the gameloop is running
	boolean isRunning = false;
	//Store if the mouse is presently held down or not
	boolean clicked = false;
	//Boolean to check if game has initialised yet
	boolean inInit = true;
	//Stor if bounces are realistic or direct energy transfer
	boolean realistic;
	//Number of balls to spawn
	int numBalls = 10;
	
	int width = this.getWidth();
	int height = this.getHeight();
	
	FooBall_Physics physics = new FooBall_Physics();
	
	//Create array of balls for the game
	ArrayList<FooBall_Ball> ball = new ArrayList<>();
	//Point to store position of mouse
	Point mouse;

	//Method to update the game positions and whatever
	void update() {
		//Loop through all balls
		for(int i=0; i<ball.size(); i++) {
			mouse = MouseInfo.getPointerInfo().getLocation();
			//Check if mouse is held down to make box accelerate towards it
			if(clicked) {

				ball.get(i).genAccel(mouse, this.getLocationOnScreen());
			}
			
			//Move balls
			ball.get(i).move();
			
			//ball.get(i).randomiseColour();
		}
		physics.checkCollisions(ball, realistic);
	}
	
	public void setBalls(int numBalls) {
		this.numBalls = numBalls;
	}
	
	int rWidth;
	int rHeight;
	
	public void setDims(Dimension dims) {
		this.setSize(dims);
	}
	
	//Initialise panel and create everything it needs
	public void init() {
		//Set realistic bounces
		realistic = true;
		//Set length of ball array
		
		//Add mouse listener to get if it's clicked or not
		this.addMouseListener(evt);
		
		//Iterate through every ball
		for(int i=0; i<numBalls; i++) {
			addBall();
		}
		
		//Was used to debug physics
		/*
		ball[0].setRadius(12);
		ball[1].setRadius(30);
		*/
	}
	
	
	void addBall() {
		ball.add(new FooBall_Ball());
		ball.get(ball.size()-1).init(ball.size()-1);
		boolean spaceAvailable = false;
		while(!spaceAvailable) {
			//Set the position of that ball randomly
			ball.get(ball.size()-1).setPos((int)(Math.random()*(this.getWidth()-(ball.get(ball.size()-1).getRadius()*2))+ball.get(ball.size()-1).getRadius()), (int)(Math.random()*(this.getHeight()-(ball.get(ball.size()-1).getRadius()*2))+ball.get(ball.size()-1).getRadius()));
			//Check if there is space in that spot to hold the ball
			spaceAvailable = physics.checkForSpace(ball, ball.get(ball.size()-1));
		}
		ball.get(ball.size()-1).randomiseColour();
		//Set bounds for that ball to bounce off
		ball.get(ball.size()-1).setBounds(this.getWidth(), this.getHeight());
		ball.get(ball.size()-1).setRadius((int)(Math.random()*20+10));
	}
	
	public void setNumBalls(int number) {
		//Check if we are increasing or reducing number of balls
		if(number > ball.size()) {
			//Loop for as long as is required to reach correct number of balls
			for(int i = number; i>ball.size(); i--) {
				addBall();
			}
			
		} else if (number < ball.size()) {
			
			for(int i = number; i<ball.size();i++) {
				ball.remove(ball.size()-1);
			}
			
		}
		
	}
	
	//Mouse Listener event to check for mouse click
	MouseListener evt = new MouseListener() {

		//Not needed
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		//Not needed
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		//Not needed
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		//Check if mouse has been pressed
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			clicked = true;
		}

		//Check if mouse has been un-pressed
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			clicked = false;
		}
		
	};
	
}
