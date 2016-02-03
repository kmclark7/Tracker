import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author Kristin Clark
 *
 */
public class EditPanel extends JPanel {
	
	JPanel editPanel = new JPanel();

	public EditPanel(JPanel content) {
		JButton button = new JButton("fake edit");
		this.add(button);	
	}
}
