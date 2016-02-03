import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author Kristin Clark
 *
 */
public class ChangePanel extends JPanel {
	
	JPanel changePanel = new JPanel();

	public ChangePanel(JPanel content) {
		JButton button = new JButton("fake changes");
		this.add(button);
	}

}
