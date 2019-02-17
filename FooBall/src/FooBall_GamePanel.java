import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//Extend JPanel to make the game panel an independent object
//Implements the Runnable object to allow certain code in this class to be run on a separate thread
//Multi-threading will be important when painting
public class FooBall_GamePanel extends JPanel implements Runnable{

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
		
		//Paint Ball
		for(int i=0; i<ball.length; i++) {
			g.setColor(Color.BLUE);
			g.fillOval((int)ball[i].x, (int)ball[i].y, ball[i].width, ball[i].height);
		}
		
    	Toolkit.getDefaultToolkit().sync();
	}
	
	//Create Boolean to control if the gameloop is running
	boolean isRunning = false;
	//
	boolean clicked = false;
	
	FooBall_Ball[] ball = new FooBall_Ball[100];

	//Method to update the game positions and whatever
	void update() {
		//Check if mouse is held down to make box accelerate towards it
		if(clicked) {
			
		}
		for(int i=0; i<ball.length; i++) {
			if(clicked) {
				ball[i].genAccel(MouseInfo.getPointerInfo().getLocation(), this.getLocationOnScreen());
			}
			ball[i].move();
		}
		
	}
	
	//Initialise panel and create everything it needs
	public void init() {
		//this.setBackground(Color.BLACK);
		this.addMouseListener(evt);
		
		for(int i=0; i<ball.length; i++) {
			ball[i] = new FooBall_Ball();
			ball[i].setPos((int)(Math.random()*(this.getWidth()-ball[i].width)), (int)(Math.random()*(this.getHeight()-ball[i].height)));
			//Set bounds for ball[i] to bounce off
			ball[i].xBounds = this.getWidth();
			ball[i].yBounds = this.getHeight();
		}
		

		
		//sync toolkit used for painting - (smoothes animation for rendering using drivers on some linux machines)
	}

	
	
	//Mouse Listener event to check for mouse click
	MouseListener evt = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			clicked = true;
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			clicked = false;
		}
		
	};
	
}
