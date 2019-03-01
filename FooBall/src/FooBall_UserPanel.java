import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;


//Create another JPanel to hold all the user controlled data
public class FooBall_UserPanel extends JPanel{
	
	//Serialise class
	private static final long serialVersionUID = 1L;

	//Label for settings
	JLabel lab_settings = new JLabel("<html><h3>Settings:</h3></html>");
	
	//Balls
	JLabel lab_balls = new JLabel("<html><b>Balls:</b></html>");
	SpinnerNumberModel spinm_balls = new SpinnerNumberModel(10, 1, 100, 1);
	JSpinner spin_balls = new JSpinner(spinm_balls);
	
	//Effects
	JLabel lab_effects = new JLabel("<html><b>Select balls:</b></html>");
	ButtonGroup bg_effects = new ButtonGroup();
	JRadioButton rb_effects_global	= new JRadioButton("All");
	JRadioButton rb_effects_local 	= new JRadioButton("Individual");
	
	//Mouse mode
	JLabel lab_mode = new JLabel("<html><b>Mouse mode:</b></html> ");
	ButtonGroup bg_mode = new ButtonGroup();
	JRadioButton rb_mode_string 	= new JRadioButton("String");
	JRadioButton rb_mode_repel 		= new JRadioButton("Repel");
	JRadioButton rb_mode_poolCue 	= new JRadioButton("Pool Cue");
	JRadioButton rb_mode_select		= new JRadioButton("Select");
	JRadioButton rb_mode_grab 		= new JRadioButton("Grab"); //Will only be active while effects are local
	
	//Gravity
	JLabel lab_gravity = new JLabel("<html><b>Gravity:</b></html> ");
	JSlider slide_gravity = new JSlider();
	
	//Mouse strength
	JLabel lab_mouseStrength = new JLabel("<html><b>Mouse Strength:</b></html> ");
	JSlider slide_mouseStrength = new JSlider();
	
	//Colours
	JLabel lab_colour = new JLabel("<html><b>Colour:</b></html> ");
	JCheckBox cb_backgroundColour = new JCheckBox("Background");

	JButton slider_reset = new JButton("Slider Reset");
	
	//Spinner models for colours
	JSpinner spin_colour = new JSpinner(spinm_balls);
	SpinnerNumberModel spinm_r = new SpinnerNumberModel(125, 0, 255, 10);
	SpinnerNumberModel spinm_g = new SpinnerNumberModel(125, 0, 255, 10);
	SpinnerNumberModel spinm_b = new SpinnerNumberModel(125, 0, 255, 10);
	
	//Spinners for colours
	JLabel lab_rgb = new JLabel("R:\tG:\tB:");
	JSpinner spin_r = new JSpinner(spinm_r);
	JSpinner spin_g = new JSpinner(spinm_g);
	JSpinner spin_b = new JSpinner(spinm_b);

	//Collisions
	JLabel lab_collisions = new JLabel("<html><b>Collisions:</b></html> ");
	JCheckBox cb_collisions = new JCheckBox("Collisions");
	JCheckBox cb_realisticCollisions = new JCheckBox("Realistic Collisions");

	//Size for text areas
	Dimension taSize = new Dimension(100, 30);
	
	
	float logslider(int position) {
		
		int minp = 0;
		int maxp = 100;
		
		int minv = (int)Math.log(0.01);
		int maxv = (int)Math.log(100);
		
		float scale = (float)(maxv - minv) / (float)(maxp - minp);
		
		return (float) Math.exp(minv + scale*(position-minp));
	}
	
	int logposition(int value) {
		
		int minp = 0;
		int maxp = 100;
		
		int minv = (int)Math.log(0.01);
		
		int maxv = (int)Math.log(100);
		
		float scale = (float)(maxv - minv) / (float)(maxp - minp);
		
		return (int) ((Math.log(value) - minv) / scale + minp);
		
	}
	
	public void init() {
		//Create border
		this.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(lab_settings);
		
		this.add(lab_balls);
		this.add(spin_balls);
		spin_balls.setMaximumSize(taSize);
		
		this.add(lab_effects);
		this.add(rb_effects_global);
		this.add(rb_effects_local);
		bg_effects.add(rb_effects_global);
		bg_effects.add(rb_effects_local);
		rb_effects_global.setSelected(true);
		
		this.add(lab_mode);
		this.add(rb_mode_select);
		this.add(rb_mode_grab);
		this.add(rb_mode_string);
		this.add(rb_mode_repel);
		this.add(rb_mode_poolCue);
		bg_mode.add(rb_mode_select);
		bg_mode.add(rb_mode_grab);
		bg_mode.add(rb_mode_poolCue);
		bg_mode.add(rb_mode_repel);
		bg_mode.add(rb_mode_string);
		rb_mode_string.setSelected(true);

		this.add(lab_gravity);
		this.add(slide_gravity);
		slide_gravity.setValue(logposition(1));
		slide_gravity.setMajorTickSpacing(10);
		slide_gravity.setPaintTicks(true);
		
		this.add(lab_mouseStrength);
		this.add(slide_mouseStrength);
		slide_mouseStrength.setValue(logposition(1));
		slide_mouseStrength.setPaintTicks(true);
		slide_gravity.setPaintTicks(true);

		this.add(slider_reset);
		
		this.add(lab_colour);
		this.add(lab_rgb);
		this.add(spin_r);
		this.add(spin_g);
		this.add(spin_b);
		this.add(cb_backgroundColour);
		spin_r.setMaximumSize(taSize);
		spin_g.setMaximumSize(taSize);
		spin_b.setMaximumSize(taSize);
		cb_backgroundColour.setSelected(true);
		
		this.add(lab_collisions);
		this.add(cb_collisions);
		this.add(cb_realisticCollisions);
		
		cb_collisions.setSelected(true);
		cb_realisticCollisions.setSelected(true);
		
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
