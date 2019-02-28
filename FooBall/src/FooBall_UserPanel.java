import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;


//Create another JPanel to hold all the user controlled data
public class FooBall_UserPanel extends JPanel{
	
	//Serialise class
	private static final long serialVersionUID = 1L;

	//Label 
	JLabel lab_Settings = new JLabel("Settings:");
	
	JLabel lab_balls = new JLabel("Balls: ");
	JTextArea ta_balls = new JTextArea("10");
	
	JLabel lab_effects = new JLabel("Select balls:");
	ButtonGroup bg_effects = new ButtonGroup();
	JCheckBox cb_effects_global = new JCheckBox("All");
	JCheckBox cb_effects_single = new JCheckBox("Individual");
	
	JLabel lab_mode = new JLabel("Mouse mode");
	ButtonGroup bg_mode = new ButtonGroup();
	JCheckBox cb_mode_string = new JCheckBox("String");
	JCheckBox cb_mode_repel = new JCheckBox("Repel");
	JCheckBox cb_mode_poolCue = new JCheckBox("Pool Cue");
	JCheckBox cb_mode_grab = new JCheckBox("Grab"); //Will only be active while effects are local
	
	
	JLabel lab_gravity = new JLabel("Gravity");
	JSlider slide_gravity = new JSlider();
	//TODO Format sliders
	
	JLabel lab_mouseStrength = new JLabel("Mouse Strength");
	JSlider slide_mouseStrength = new JSlider();
	
	JLabel lab_colour = new JLabel("Colour: ");
	JCheckBox cb_backgroundColour = new JCheckBox("Background");
	
	JLabel lab_rgb = new JLabel("R:\tG:\tB:");
	
	JCheckBox cb_collisions = new JCheckBox("Collisions");
	JCheckBox cb_realisticCollisions = new JCheckBox("Realistic Collisions");

	
	public void init() {
		//Create border
		this.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		
	}
	
	/*TODO
	 * Add:
	 *  rates of decay and acceleration for balls
	 *  change colour of balls
	 *  change background colour
	 *  walls deflect ball/stop ball
	 *  Gravity
	 *  String mode - highlight connection
	 *  	when clicked - always
	 *  Change number of balls
	 */
	
	
}
