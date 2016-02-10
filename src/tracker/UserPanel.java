package tracker;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
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
	TrackerPane tracker;
	UserTableModel userTableModel;
	JTable userTable;
	TableRowSorter<UserTableModel> userSorter;
	JScrollPane scroll;
	JButton addUser = new JButton("Add user");
	JButton search = new JButton("Search user");
	JButton remove = new JButton("Remove user");
	Font tableFont = new Font("Dialog", Font.PLAIN, 16);
	Font headerFont = new Font("Dialog", Font.BOLD, 16);

	// Constructor
	public UserPanel(TrackerPane tracker) {

		this.tracker = tracker;
		userTableModel = new UserTableModel(tracker);
		userTable = new JTable(userTableModel);

		//title.setFont(new Font("Serif", Font.PLAIN, 16));
		setLayout(new BorderLayout());
		
		//Set text for tool tip.
		addUser.setToolTipText("Add new user to system");
		
		//search.setToolTipText("Search by Last Name, First Name");
		remove.setToolTipText("Highlight task you want removed and click this button.");
		
		//Set up table
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
    
	    //Set up scroll pane and add table to it.
		scroll  = new JScrollPane(userTable);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(350, 200));

		add(scroll, BorderLayout.CENTER);

		ButtonListener l = new ButtonListener();
		search.addActionListener(l);
		remove.addActionListener(l);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(addUser);
		buttonPanel.add(search);
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

			//if (e.getSource().equals(search)) {

				//removeAll();
				//JPanel newPanel = new UserSearch();
				//add(newPanel);
				//revalidate();
			//}
		}
	}
	
	
}//end UserPanel



