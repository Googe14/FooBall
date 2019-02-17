
public class FooBall_App {
	
	//Main Method to start program
	public static void main(String[] args) {

		
		//Set a few universal parameters - Being set in main method to easily modify at any time
		//Create dimensions array which will hold the dimensions of the Gamepanel, not the whole frame
		int[] dimensions = {900, 900};
		String name = "FooBall";
		
		
		
		//Create Frame/Window
		FooBall_Frame frame = new FooBall_Frame();
		//Initialize frame and create panels o go on it
		frame.init(dimensions, name);
		frame.genPanels();
		//Start frame and continue from there
		frame.start();
		
		
		//**End of Main Method - Continue from FooBall_Frame.java - void start()**//
	}

}
