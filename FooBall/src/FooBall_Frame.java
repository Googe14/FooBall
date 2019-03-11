import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

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
		pane.setDims(new Dimension(dimensions[0], dimensions[1]));
		pane.setLocation(0,  0);
		userPane.setPreferredSize(new Dimension(200, dimensions[1]));
		userPane.setLocation(dimensions[0], 0);
		//Initialise panels
		pane.init();
		userPane.init(evt, change);
		
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
		pane.start();
		
	}
	
	
	//Create change listener object to check if any changes have been made to the UserPanel (buttons pressed etc)
	ChangeListener change = new ChangeListener() {
		//When something changes, code in stateChanged will run
		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getSource() == userPane.spin_balls) {
				if((int)userPane.spin_balls.getValue() > 200 && userPane.cb_collisions.isSelected()) {
					userPane.spin_balls.setValue(200);
				}
				pane.setNumBalls((int) userPane.spin_balls.getValue());
			} else if (e.getSource() == userPane.slide_gravity) {
			} else if (e.getSource() == userPane.slide_mouseStrength) {
			} 
			
		}};
	
	ActionListener evt = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == userPane.rb_effects_global) {
			} else if (e.getSource() == userPane.rb_effects_local) {
			} else if (e.getSource() == userPane.rb_mode_string) {
			} else if (e.getSource() == userPane.rb_mode_repel) {
			} else if (e.getSource() == userPane.rb_mode_poolCue) {
			} else if (e.getSource() == userPane.rb_mode_grab) {
			} else if (e.getSource() == userPane.cb_collisions) {
				pane.setCollisions(userPane.cb_collisions.isSelected());
			} else if (e.getSource() == userPane.cb_realisticCollisions) {
				pane.setRealistic(userPane.cb_realisticCollisions.isSelected());
			} else if (e.getSource() == userPane.reset) {
				pane.reset();
				userPane.setBalls(pane.getBalls());
			} else if (e.getSource() == userPane.slider_reset) {
				userPane.setSliders();
			}
		}
		
	};
	
}
