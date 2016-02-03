import java.awt.Dimension;

import javax.swing.BorderFactory;
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
	
	private JPanel viewPanel = new JPanel();

	// private DefectTable table;
	private int WIDTH = 1000;
	private int HEIGHT = 1000;
	private JScrollPane scroll;
	private Integer[][] fakearray = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
	private String[] faketitles = { "numA", "numB", "numC" };

	public ViewPanel(JPanel content) {
		
		// How to allow for sorting? should be sorted originally
		// What to sort by? ticket, user, assignee, etc.
		// DefectTable table = new DefectTable();
		JTable table = new JTable(fakearray, faketitles);
		scroll = new JScrollPane(table);
		//scroll.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		scroll.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		scroll.setPreferredSize(content.getPreferredSize());
		this.add(scroll);
		
	}
}
