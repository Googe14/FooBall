import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;


//Create another JPanel to hold all the user controlled data
public class FooBall_UserPanel extends JPanel{
	
	//Serialise class
	private static final long serialVersionUID = 1L;

	//Label for settings
	JLabel lab_settings = new JLabel("<html><h3>Settings:</h3></html>");
	
	//Balls
	JLabel lab_balls = new JLabel("<html><b>Balls:</b></html>");
	SpinnerNumberModel spinm_balls = new SpinnerNumberModel(10, 0, 1000, 1);
	JSpinner spin_balls = new JSpinner(spinm_balls);
	
	//Effects
	JLabel lab_effects = new JLabel("<html><b>Affect balls:</b></html>");
	ButtonGroup bg_effects = new ButtonGroup();
	JRadioButton rb_effects_global	= new JRadioButton("All");
	JRadioButton rb_effects_local 	= new JRadioButton("Individual");
	
	//Mouse mode
	JLabel lab_mode = new JLabel("<html><b>Mouse mode:</b></html> ");
	ButtonGroup bg_mode = new ButtonGroup();
	JRadioButton rb_mode_string 	= new JRadioButton("String");
	JRadioButton rb_mode_repel 		= new JRadioButton("Repel");
	JRadioButton rb_mode_poolCue 	= new JRadioButton("Pool Cue");
	JRadioButton rb_mode_grab 		= new JRadioButton("Grab"); //Will only be active while effects are local
	
	//Gravity
	JLabel lab_gravity = new JLabel("<html><b>Gravity:</b></html> ");
	JSlider slide_gravity = new JSlider();
	
	//Gravity
	JLabel lab_drag = new JLabel("<html><b>Air Resistance:</b></html> ");
	JSlider slide_drag = new JSlider();
	
	//Mouse strength
	JLabel lab_mouseStrength = new JLabel("<html><b>Mouse Strength:</b></html> ");
	JSlider slide_mouseStrength = new JSlider();

	//Mouse strength
	JLabel lab_speed = new JLabel("<html><b>Speed:</b></html> ");
	JSlider slide_speed = new JSlider();
	
	JButton slider_reset = new JButton("Slider Reset");

	//Collisions
	JLabel lab_collisions = new JLabel("<html><b>Collisions:</b></html> ");
	JCheckBox cb_collisions = new JCheckBox("Collisions");
	JCheckBox cb_realisticCollisions = new JCheckBox("Realistic Collisions");

	JLabel lab_drawing = new JLabel("<html><b>Drawing:</b></html> ");
	JCheckBox cb_temp = new JCheckBox("Temperature");
	JCheckBox cb_teth = new JCheckBox("Tethers");
	
	//Size for text areas
	Dimension taSize = new Dimension(100, 30);
	
	JButton reset = new JButton("Reset");
	
	//Get value from position on slider (slider scale logarithmically)
	float logslider(int position, boolean frame) {
		
		if(position == 0) {
			return 0;
		}
		
		int minp = 1;
		int maxp = 100;

		int minv = (int)Math.log(0.01);
		int maxv = (int)Math.log(100);
		
		if(frame) {
			minv = (int)Math.log(500);
			maxv = (int)Math.log(50000);
		}
		
		float scale = (float)(maxv - minv) / (float)(maxp - minp);
		
		return (float) Math.exp(minv + scale*(position-minp));
	}
	//Get position on slider from value 
	int logposition(int value, boolean frame) {
		int minp = 1;
		int maxp = 100;
		
		int minv = (int)Math.log(0.01);
		int maxv = (int)Math.log(100);
		
		if(frame) {
			minv = (int)Math.log(500);
			maxv = (int)Math.log(50000);
		}
		
		float scale = (float)(maxv - minv) / (float)(maxp - minp);
		
		return (int) ((Math.log(value) - minv) / scale + minp);
	}
	
	//Set positions, groups, changelisteners etc for panel components
	public void init(ActionListener evt, ChangeListener chng) {
		//Create border
		this.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(lab_settings);
		
		this.add(lab_balls);
		this.add(spin_balls);
		spin_balls.setMaximumSize(taSize);
		spin_balls.addChangeListener(chng);
		
		this.add(lab_effects);
		this.add(rb_effects_global);
		this.add(rb_effects_local);
		bg_effects.add(rb_effects_global);
		bg_effects.add(rb_effects_local);
		rb_effects_global.setSelected(true);
		rb_effects_global.addActionListener(evt);
		rb_effects_local.addActionListener(evt);
		
		this.add(lab_mode);
		this.add(rb_mode_grab);
		this.add(rb_mode_string);
		this.add(rb_mode_repel);
		this.add(rb_mode_poolCue);
		bg_mode.add(rb_mode_grab);
		bg_mode.add(rb_mode_poolCue);
		bg_mode.add(rb_mode_repel);
		bg_mode.add(rb_mode_string);
		rb_mode_string.setSelected(true);
		rb_mode_grab.addActionListener(evt);
		rb_mode_string.addActionListener(evt);
		rb_mode_repel.addActionListener(evt);
		rb_mode_poolCue.addActionListener(evt);

		this.add(lab_gravity);
		this.add(slide_gravity);
		slide_gravity.setMajorTickSpacing(10);
		slide_gravity.setPaintTicks(true);
		slide_gravity.addChangeListener(chng);
		
		this.add(lab_mouseStrength);
		this.add(slide_mouseStrength);
		slide_mouseStrength.setMajorTickSpacing(10);
		slide_mouseStrength.setPaintTicks(true);
		slide_mouseStrength.addChangeListener(chng);
		
		this.add(lab_speed);
		this.add(slide_speed);
		slide_speed.setMajorTickSpacing(10);
		slide_speed.setPaintTicks(true);
		slide_speed.addChangeListener(chng);
		
		this.add(lab_drag);
		this.add(slide_drag);
		slide_drag.setMajorTickSpacing(10);
		slide_drag.setPaintTicks(true);
		slide_drag.addChangeListener(chng);

		setSliders();
		
		this.add(slider_reset);
		slider_reset.addActionListener(evt);
		
		this.add(lab_collisions);
		this.add(cb_collisions);
		this.add(cb_realisticCollisions);
		cb_collisions.addActionListener(evt);
		cb_realisticCollisions.addActionListener(evt);
		cb_collisions.setSelected(true);
		cb_realisticCollisions.setSelected(true);
		
		this.add(lab_drawing);
		this.add(cb_temp);
		this.add(cb_teth);
		cb_temp.addActionListener(evt);
		cb_teth.addActionListener(evt);
		cb_teth.setSelected(true);
		
		this.add(reset);
		reset.addActionListener(evt);
		
	}
	
	//Set position of sliders to game default
	public void setSliders() {
		slide_gravity.setValue(logposition(1, false));
		slide_mouseStrength.setValue(logposition(1, false));
		slide_drag.setValue(logposition(1, false));
		slide_speed.setValue(logposition(2000, true));
	}
	
	public void setBalls(int numBalls) {
		spin_balls.setValue(numBalls);
	}
	
	/*TODO
	 * Add:
	 *  Change size of balls
	 *  
	 *  randomize colours and sizes
	 */
	
	
}
