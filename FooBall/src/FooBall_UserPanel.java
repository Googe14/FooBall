import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


//Create another JPanel to hold all the user controlled data
public class FooBall_UserPanel extends JPanel{
	
	//Serialise class
	private static final long serialVersionUID = 1L;

	//Label 
	JLabel settings = new JLabel("Settings:");
	
	//Make label, and radio buttons for the gamemode
	JLabel gameModLab = new JLabel("Game Mode:");
	//Make button group to make radio buttons work together
	ButtonGroup gameModGrp = new ButtonGroup();
	JRadioButton gameModNorm = new JRadioButton("Normal");
	JRadioButton gameModString = new JRadioButton("String");
	
	JLabel massEditLab = new JLabel("Change ");
	/*
	 * Radio - Gamemode
	 * 		Normal
	 * 		String
	 * Text input - Number of balls
	 * 
	 * Radio - Choose thing to change
	 * 		Gravity
	 * 		Weight
	 * Radio - Choose other thing to change
	 * 		Accel Rate
	 * 		Decell Rate
	 * 
	 * 3x Text Input - Background colour
	 * 		R:G:B: - background colour
	 */
	
	
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
