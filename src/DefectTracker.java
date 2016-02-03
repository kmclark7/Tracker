import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * 
 */

/**
 * @author Kristin Clark
 *
 */
public class DefectTracker extends JFrame {
	
	
	/**
	 * @param args
	 */

	public static void main(String[] args) {
		

		boolean isLoggedIn;
		final int FRAME_WIDTH = 2000;
		final int FRAME_HEIGHT = 2000;
		final int TOP_BORDER = 50;

		//Create a frame
		JFrame frame = new JFrame();
		Dimension dimension = new Dimension(FRAME_WIDTH, FRAME_HEIGHT-TOP_BORDER);
		frame.setSize(dimension);
		frame.setName("The Tracker");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Create a main content pane
		JPanel content = new JPanel (new BorderLayout());
		content.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		content.setPreferredSize(dimension);
		content.setName("The Tracker");
		
		//Create tabbed panes and add to content pane
		TrackerPane trackerPane = new TrackerPane(content, false);

		//Set the panel as the content pane and make it visible.
		frame.setContentPane(content);
		frame.setVisible(true);
		
	}//end Main


}// end DefectTracker
