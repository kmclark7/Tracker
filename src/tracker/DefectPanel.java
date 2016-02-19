package tracker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

/**
 * @author Kristin Clark
 *
 */
public class DefectPanel extends JPanel{
	
	private TrackerPane tracker;
	private DefectTableModel defectTableModel;
	private JTable defectTable;
	private TableRowSorter<DefectTableModel> defectSorter;
	private JScrollPane scroll;
	private JButton addDefect = new JButton("Add Defect");
	private JButton search = new JButton("Search Defect");
	private JButton remove = new JButton("Remove Defect");
	private Font tableFont = new Font("Dialog", Font.PLAIN, 16);
	private Font headerFont = new Font("Dialog", Font.BOLD, 16);
	private String[] defectFields = { "Show All", "Ticket ID", "Summary", "Description", "Assigned To",
			"Priority Level","Date Entered", "Submitted By", "Current Status", "Comments"};
    int[] colWidths = {10, 100, 100, 10, 5, 50, 10, 10, 150};
	private JComboBox<String> defectFieldBox = new JComboBox<String>(defectFields);
	private JLabel comboLabel = new JLabel("Filter table by:");
	private JLabel filterLabel = new JLabel("Where value is:");
	private JTextField filterText = new JTextField("");


	
	// Constructor
	public DefectPanel(TrackerPane tracker) {

		this.tracker = tracker;
		defectTableModel = new DefectTableModel(tracker);
		defectTable = new JTable(defectTableModel);
		setLayout(new BorderLayout());
		
		//Create a panel with filtering tools 
		//Add listeners and add to the main panel.
		JPanel filterPanel = new JPanel(new GridLayout(2, 2));
		filterPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		comboLabel.setFont(headerFont);
		filterLabel.setFont(headerFont);
		defectFieldBox.setFont(tableFont);
		filterText.setFont(tableFont);
		filterPanel.add(comboLabel);
		filterPanel.add(filterLabel);
		filterPanel.add(defectFieldBox);
		filterPanel.add(filterText);
		//need listener
		add(filterPanel, BorderLayout.NORTH);
		
		//Set up the full main table
		fullDefectTableSetup();
    
	    //Set up scroll pane and add table to it.  Add it to the main panel.
		scroll  = new JScrollPane(defectTable);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(350, 200));
		add(scroll, BorderLayout.CENTER);
		
		//Add button listener and fonts
		ButtonListener l = new ButtonListener();
		addDefect.addActionListener(l);
		remove.addActionListener(l);

		//Create a panel of buttons and add to main panel.
		JPanel buttonPanel = new JPanel();
		addDefect.setToolTipText("Add new defect record to system");
		remove.setToolTipText("Select row and click here to remove.");
		addDefect.setFont(headerFont);
		remove.setFont(headerFont);
		buttonPanel.add(addDefect);
		buttonPanel.add(remove);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource().equals(remove)) {
				int row = defectTable.getSelectedRow();
				//int row = -1;
				if(row == -1){
					String msg = "Please select a defect record to remove.";
					JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
				System.out.println(row+"  "+defectTable.convertRowIndexToModel(row));
				defectTableModel.removeDefectAt(defectTable.convertRowIndexToModel(row));
			}

		}
	}
	private void fullDefectTableSetup(){
		defectTable.setFont(tableFont);
		defectTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		defectTable.setSelectionMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		defectTable.setDragEnabled(false);
		defectTable.setColumnSelectionAllowed(false);
		TableRowSorter<DefectTableModel> defectSorter = new TableRowSorter<DefectTableModel>(defectTableModel);
	    defectTable.setRowSorter(defectSorter);
	    defectTable.setIntercellSpacing(new Dimension(50, 5));
	    JTableHeader header = defectTable.getTableHeader();
	    header.setBackground(new Color(0.6f,0.7f,0.8f));
	    header.setFont(headerFont);
	    defectTable.setRowHeight(25);
	    for(int j = 0; j < defectTable.getColumnCount(); j++){
	    	defectTable.getColumn(defectTable.getColumnName(j)).setPreferredWidth(colWidths[j]); 
	    }
	}//end fullUserTableSetup
	
}//end UserPanel

