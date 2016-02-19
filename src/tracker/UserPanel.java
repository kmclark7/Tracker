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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;


/**
 * This is a panel object that displays a table of users.
 */

/**
 * @author Milissa Parmentier
 */

//list of the J functions is in Heads Up book on page 413
//Border Layout

public class UserPanel extends JPanel {
	
	private TrackerPane tracker;
	private UserTableModel userTableModel;
	private JTable userTable;
	private TableRowSorter<UserTableModel> userSorter;
	private JScrollPane scroll;
	private JButton addUser = new JButton("Add user");
	private JButton search = new JButton("Search user");
	private JButton remove = new JButton("Remove user");
	private Font tableFont = new Font("Dialog", Font.PLAIN, 16);
	private Font headerFont = new Font("Dialog", Font.BOLD, 16);
	private String[] userFields = { "Show All", "User ID", "User Password", "Last Name", 
			"First Name", "Position","Access Level", "Team Name", "Email Address" };
	private JComboBox<String> userFieldBox = new JComboBox<String>(userFields);
	private JLabel comboLabel = new JLabel("Filter table by:");
	private JLabel filterLabel = new JLabel("Where value is:");
	private JTextField filterText = new JTextField("");


	
	// Constructor
	public UserPanel(TrackerPane tracker) {

		this.tracker = tracker;
		userTableModel = new UserTableModel(tracker);
		userTable = new JTable(userTableModel);
		setLayout(new BorderLayout());
		
		//Create a panel with filtering tools 
		//Add listeners and add to the main panel.
		JPanel filterPanel = new JPanel(new GridLayout(2, 2));
		filterPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		comboLabel.setFont(headerFont);
		filterLabel.setFont(headerFont);
		userFieldBox.setFont(tableFont);
		filterText.setFont(tableFont);
		filterPanel.add(comboLabel);
		filterPanel.add(filterLabel);
		filterPanel.add(userFieldBox);
		filterPanel.add(filterText);
		//need listener
		add(filterPanel, BorderLayout.NORTH);
		
		//Set up the full main table
		fullUserTableSetup();
    
	    //Set up scroll pane and add table to it.  Add it to the main panel.
		scroll  = new JScrollPane(userTable);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(350, 200));
		add(scroll, BorderLayout.CENTER);
		
		//Add button listener and fonts
		ButtonListener l = new ButtonListener();
		addUser.addActionListener(l);
		remove.addActionListener(l);

		//Create a panel of buttons and add to main panel.
		JPanel buttonPanel = new JPanel();
		addUser.setToolTipText("Add new user to system");
		remove.setToolTipText("Select row and click here to remove.");
		addUser.setFont(headerFont);
		remove.setFont(headerFont);
		buttonPanel.add(addUser);
		buttonPanel.add(remove);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource().equals(remove)) {
				int row = userTable.getSelectedRow();
				//int row = -1;
				if(row == -1){
					String msg = "Please select a user to remove.";
					JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
				System.out.println(row+"  "+userTable.convertRowIndexToModel(row));
				userTableModel.removeUserAt(userTable.convertRowIndexToModel(row));
			}

		}
	}
	private void fullUserTableSetup(){
		userTable.setFont(tableFont);
		userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userTable.setSelectionMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		userTable.setDragEnabled(false);
		userTable.setColumnSelectionAllowed(false);
		TableRowSorter<UserTableModel> userSorter = new TableRowSorter<UserTableModel>(userTableModel);
	    userTable.setRowSorter(userSorter);
	    userTable.setIntercellSpacing(new Dimension(50, 5));
	    JTableHeader header = userTable.getTableHeader();
	    header.setBackground(new Color(0.6f,0.7f,0.8f));
	    header.setFont(headerFont);
	    userTable.setRowHeight(25);
	    int[] colWidths = {10, 100, 100, 100, 100, 10, 100, 150};
	    for(int j = 0; j < userTable.getColumnCount(); j++){
	    	userTable.getColumn(userTable.getColumnName(j)).setPreferredWidth(colWidths[j]); 
	    }
	}//end fullUserTableSetup
	
}//end UserPanel



