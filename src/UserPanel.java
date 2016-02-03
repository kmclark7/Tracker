import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author Kristin Clark
 *
 */
public class UserPanel extends JPanel {
	
	JPanel userPanel = new JPanel();

	public UserPanel(JPanel content) {
		JButton button = new JButton("fake user");
		this.add(button);
	}

}
