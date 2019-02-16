import javax.swing.JPanel;

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
            runner.stop();
            runner = null;
        }
    }
    //Code that will be run when the new Thread is started
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
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
	
	boolean isRunning = false;

	void update() {
		
	}
	
	
}
