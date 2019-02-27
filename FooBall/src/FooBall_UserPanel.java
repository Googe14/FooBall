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
	JLabel lab_Settings = new JLabel("Settings:");
	
	//Make label, and radio buttons for the gamemode
	JLabel lab_GameMod = new JLabel("Game Mode:");
	//Make button group to make radio buttons work together
	ButtonGroup grp_gameMod = new ButtonGroup();
	JRadioButton rBut_gameModNorm = new JRadioButton("Normal");
	JRadioButton rBut_gameModString = new JRadioButton("String");
	
	JLabel lab_SizeEdit = new JLabel("Change Size");
	
	
	/*
	 * Text input - Number of balls
	 * 
	 * 
	 * Slider - Possible values
	 * 
	 * Radio - Choose thing to change
	 * 		Gravity
	 * 		Weight
	 * 
	 * Slider for Weight/Gravity
	 * 
	 * 
	 * 3x Text Input - Background colour
	 * 		R:G:B: - background colour
	 * 
	 * 
	 * Check box - Collisions
	 * Check box - Realistic collisions
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
