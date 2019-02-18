import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//Extend JFrame to make the window it's own object
public class FooBall_Frame extends JFrame{

	//Serialise class
	private static final long serialVersionUID = 1L;

	//Create variables for the frame
	int[] dimensions;
	
	//Create panel objects for the Frame to use
	FooBall_GamePanel pane;
	FooBall_UserPanel userPane;
	
	//Initialise frame
	public void init(int[] dimensions, String name) {
		//Set dimensions for the frame as those passed from main method
		this.dimensions = dimensions;
		//Set program to exit when we click the x button
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setName(name);
	}
	
	
	
	//Generate the Panels and add to frame
	public void genPanels() {
		//Make new panel objects
		pane = new FooBall_GamePanel();
		userPane = new FooBall_UserPanel();
		
		//Initialize panels - setting size and location on Frame
		pane.setPreferredSize(new Dimension(dimensions[0], dimensions[1]));
		pane.setLocation(0,  0);
		userPane.setPreferredSize(new Dimension(200, dimensions[1]));
		userPane.setLocation(dimensions[0], 0);
		//Initialise panels
		pane.init();
		userPane.init();
		
		//Add panels to Frame
		this.add(pane);
		this.add(userPane);
		
		//Set how the layout of the panels will be
		this.setLayout(new FlowLayout());
		//Organize frame to fit panels in the right order
		this.pack();
		//Stop users from dragging to resize
		this.setResizable(false);
	}
	
	
	//Open frame and start program (basically new Main method)
	public void start() {
		this.setVisible(true);
		
		pane.init();
		pane.start();
		
	}
	
	
	//Create change listener object to check if any changes have been made to the UserPanel (buttons pressed etc)
	ChangeListener change = new ChangeListener() {
		//When something changes, code in stateChanged will run
		@Override
		public void stateChanged(ChangeEvent arg0) {
			// TODO Auto-generated method stub
			
			
			
		}};
	
	
	
}
