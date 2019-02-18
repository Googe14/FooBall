import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    //Code that will be run when the new Thread is started
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
	private final static int    MAX_FPS = 60;
	// maximum number of frames to be skipped
	private final static int    MAX_FRAME_SKIPS = 5;
	// the frame period
	private final static int    FRAME_PERIOD = 1000 / MAX_FPS;  
	 

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
		for(int i=0; i<ball.length; i++) {
			//Set colour to that of the select ball
			g.setColor(ball[i].getColour());
			//Check if mouse is clicked down
			if(clicked) {
				//If so, draw a line connecting the mouse to the ball - before drawing ball so that the ball appears on the line
				g.drawLine((int)(mouse.getX() - this.getLocationOnScreen().getX()), (int)(mouse.getY()-this.getLocationOnScreen().getY()), (int)(ball[i].getX() + ball[i].getWidth()/2), (int)(ball[i].getY() + ball[i].getHeight()/2));
			}
			//Draw ball
			g.fillOval(ball[i].getX(), ball[i].getY(), ball[i].getWidth(), ball[i].getHeight());
		}
		//Sync graphics toolkit - smoothes repeated drawing when using some linux drivers
    	Toolkit.getDefaultToolkit().sync();
	}
	
	//Create Boolean to control if the gameloop is running
	boolean isRunning = false;
	//Store if the mouse is presently held down or not
	boolean clicked = false;
	
	//Create array of balls for the game
	FooBall_Ball[] ball;
	//Point to store position of mouse
	Point mouse;

	//Method to update the game positions and whatever
	void update() {
		//Check if mouse is held down to make box accelerate towards it
		if(clicked) {
			
		}
		for(int i=0; i<ball.length; i++) {
			mouse = MouseInfo.getPointerInfo().getLocation();
			if(clicked) {

				ball[i].genAccel(mouse, this.getLocationOnScreen());
			}
			ball[i].move();
			//ball[i].randomiseColour();
		}
		
	}
	
	//Initialise panel and create everything it needs
	public void init() {
		//Set length of ball array
		ball = new FooBall_Ball[10];
		
		//Add mouse listener to get if it's clicked or not
		this.addMouseListener(evt);
		
		//Iterate through every ball
		for(int i=0; i<ball.length; i++) {
			//Create a new ball for each position in the array
			ball[i] = new FooBall_Ball();
			//Set the position of that ball randomly
			ball[i].setPos((int)(Math.random()*(this.getWidth()-ball[i].getWidth())), (int)(Math.random()*(this.getHeight()-ball[i].getHeight())));
			//Randomise the colour of the ball 
			ball[i].randomiseColour();
			//Set bounds for that ball to bounce off
			ball[i].setBounds(this.getWidth(), this.getHeight());
			
			//ball[i].weight = (float) Math.random()*4;
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
