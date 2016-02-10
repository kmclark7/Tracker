package tracker;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 * Creates an table from an abstract table model for the user database.
 */

/**
 * @author Kristin Clark
 */

public class UserTableModel extends AbstractTableModel{
	
	private String[] userColumnNames = { "User ID", "User Password", "Last Name", "First Name", "Position",
			"Access Level", "Team Name", "Email Address" };

	// private boolean[] isColumnEditable = {false, true, true, true, true, true, true, true};
	private UserDAO userDAO = new UserDAO();
	private ArrayList<User> userModel = new ArrayList<User>(userDAO.arrayList);
	private TrackerPane tracker;

	
	// Constructor
	public UserTableModel(TrackerPane tracker) {
		this.tracker = tracker;	
	}

	
	public User getUserAt(int row) {
		return this.userModel.get(row);
	}

	
	public void insertUser(int row, User user) {
		boolean hadSuccess = userDAO.insertNewUser(user);
		if (hadSuccess) {
			userModel.add(row, user);
			popUpSuccessMessage("User entered into database.");
		} else {
			popUpErrorMessage("Problem saving to database.");
		}
		fireTableRowsInserted(row, row);
	}

	
	public void addUser(User user) {
		insertUser(getRowCount(), user);
	}

	
	public void removeUserAt(int row) {
		removeUser(row, getUserAt(row));
	}
	
	
	private void removeUser(int row, User user) {
		
		boolean hadSuccess = userDAO.deleteUser(user);
		if (hadSuccess) {
			userModel.remove(user);
			popUpSuccessMessage("Removed user from database.");
		} else {
			popUpErrorMessage("Error removing from database.");
		}
		fireTableRowsDeleted(row, row);
	}

	
	public void replaceUserAt(int row) {
		User user = getUserAt(row);
		replaceUser(row, user);
	}

	
	public void replaceUser(int row, User user) {
		
		User oldUser = getUserAt(row);
		boolean hadSuccess = userDAO.replaceUser(user);
		
		if (hadSuccess) {
			userModel.set(row, user);
			popUpSuccessMessage("User replaced in database.");
		}else {
			popUpErrorMessage("Error replacing user in database.");
			userModel.set(row, oldUser);
		}
		fireTableRowsUpdated(row, row);
	}

	
	@Override
	public boolean isCellEditable(int row, int col) {
		if (col == 0){return false;}
		else {return true;}
	}

	
	@Override
	public int getColumnCount() {
		return userColumnNames.length;
	}// end getColumnCount

	
	@Override
	public String getColumnName(int column) {
		return this.userColumnNames[column];
	}// end getColumnName

	
	@Override
    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
	}

	
	@Override
	public int getRowCount() {
		return userModel.size();
	}// end getRowCount


	
	@Override
	public Object getValueAt(int row, int col) {

		switch (col) {

		case 0:
			return this.userModel.get(row).getUserID();
		case 1:
			return this.userModel.get(row).getPassword();
		case 2:
			return this.userModel.get(row).getLastName();
		case 3:
			return this.userModel.get(row).getFirstName();
		case 4:
			return this.userModel.get(row).getPosition();
		case 5:
			return this.userModel.get(row).getAccessLevel();
		case 6:
			return this.userModel.get(row).getTeam();
		case 7:
			return this.userModel.get(row).getEmail();
		default:
			return null;
		}
	}// end getValueAt
	
	
	public void setValueAt(Object value, int row, int col) {

		Object oldValue = getValueAt(row, col);
		boolean hadSuccess = userDAO.replaceFieldAt(value, getUserAt(row), col);
		//boolean hadSuccess = false;
		
		if (hadSuccess){
			popUpSuccessMessage("Change saved to database.");
			setTableValueAt(value, row, col);
		}else{
			popUpErrorMessage("Error changing in database.");
			setTableValueAt(oldValue, row, col);
		} 
		   			
	}// end setValueAt

	
	public void setTableValueAt(Object value, int row, int col) {

		switch (col) {

		case 0:
			userModel.get(row).setUserID(((int) value));
			break;		
		case 1: 
			userModel.get(row).setPassword((String) value);
			break;		
		case 2: 
			userModel.get(row).setLastName((String) value);
			break;			
		case 3: 
			userModel.get(row).setFirstName((String) value);
			break;			
		case 4: 
			userModel.get(row).setPosition((String) value);
			break;		
		case 5: 
			userModel.get(row).setAccessLevel((int) value);
			break;		
		case 6: 
			userModel.get(row).setTeam((String) value);
			break;		
		case 7: 
			userModel.get(row).setEmail((String) value);		
		}//end switch
		fireTableCellUpdated(row, col);
	}
	
	public int findUserRow(int ID) {
		int row = -1;
		for (int i = 0; i < getRowCount(); i++) {
			if (((int) getValueAt(i, 0)) == ID) {
				row = i;
				break;
			}
		}
		return row;
	}

	
	private void popUpErrorMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	
	private void popUpSuccessMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "SUCCESS", JOptionPane.INFORMATION_MESSAGE);	
	} 
	
}// end UserTableModel

