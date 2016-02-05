import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 * 
 */

/**
 * @author Kristin Clark
 *
 */
public class ViewPanel extends JPanel {
	
	// private DefectTable table;

	private final int BORDER_LENGTH = 20;
	private JScrollPane scroll;
	private Integer[][] fakearray = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
	private String[] faketitles = { "numA", "numB", "numC" };

	public ViewPanel(TrackerPane tracker) {
		
		//int w = (int) (dimension.getWidth() - 2 * BORDER_LENGTH);
		//int l = (int) (dimension.getHeight() - 2 * BORDER_LENGTH);
		//Dimension scrollDimension = new Dimension(w, l);
		// How to allow for sorting? should be sorted originally
		// What to sort by? ticket, user, assignee, etc.
		// DefectTable table = new DefectTable();
		JTable table = new JTable(fakearray, faketitles);
		scroll = new JScrollPane(table);
		//scroll.setPreferredSize(scrollDimension);
		scroll.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		//scroll.setPreferredSize(tracker.getSize());
		this.add(scroll);
		
	}
}
