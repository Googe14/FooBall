import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class FooBall_App {
	
	//Main Method to start program
	public static void main(String[] args) {

		
		//Set a few universal parameters - Being set in main method to easily modify at any time
		//Create dimensions array which will hold the dimensions of the Gamepanel, not the whole frame
		int[] dimensions = {650, 650};
		String name = "FooBall";
		
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
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
