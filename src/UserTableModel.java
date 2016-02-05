import java.awt.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
/**
 * 
 */

/**
 * @author Kristin Clark
 *
 */
class UserTableModel extends AbstractTableModel {

	private String[] userColumnNames = {"User ID", "User Password", "Last Name", "First Name", "Position",
			"Access Level", "Team Name", "Email Address" };
	private Class[] colClass = {int.class, String.class, String.class, String.class, String.class,
		int.class, String.class, String.class};
	private Class rowClass = User.class;
	private boolean isModelEditable = true;

	//Change these later!!!!!
	//private boolean[] isColumnEditable = {false, true, true, true, true, true, true, true};

	private ArrayList<User> userData;

	
	public UserTableModel(ArrayList<User> userData) {
		this.userData = userData;
	}
	
	@Override
	public int getColumnCount() {
		return userColumnNames.length;
	}//end getColumnCount

	
	@Override
	public String getColumnName(int column) {
		return this.userColumnNames[column];
	}//end getColumnName

	
	@Override
	public Class getColumnClass(int columnIndex) {
		return colClass[columnIndex];
	}//end getColumnClass

	
	@Override
	public int getRowCount() {
		return this.userData.size();
	}//end getRowCount

	
	@Override
	public Object getValueAt(int row, int col) {

		switch (col) {

		case 0: return this.userData.get(row).getUserID();
		case 1:	return this.userData.get(row).getPassword();
		case 2:	return this.userData.get(row).getLastName();
		case 3:	return this.userData.get(row).getFirstName();
		case 4:	return this.userData.get(row).getPosition();
		case 5:	return this.userData.get(row).getAccessLevel();
		case 6:	return this.userData.get(row).getTeam();
		case 7:	return this.userData.get(row).getEmail();
		default: return null;
		}
	}//end getValueAt
	
	  public void setValueAt(Object value, int row, int col) {
		  
		  switch (col) {

			case 0: userData.get(row).setUserID((int) value);
			case 1:	userData.get(row).setPassword((String) value);
			case 2:	userData.get(row).setLastName((String) value);
			case 3:	userData.get(row).setFirstName((String) value);
			case 4:	userData.get(row).setPosition((String) value);
			case 5:	userData.get(row).setAccessLevel((int) value);
			case 6:	userData.get(row).setTeam((String) value);
			case 7:	userData.get(row).setEmail((String) value);
			}
	        fireTableCellUpdated(row, col);
	  }//end setValueAt
	        
    
    public User getUserAt(int rowIndex){
    	return this.userData.get(rowIndex);
    }
    
	public void insertUser(int row, User user)
	{
		userData.add(row, user);
		fireTableRowsInserted(row, row);
	}

    public void addRow(User user)
	{
		insertUser(getRowCount(), user);
	}

    public User getUser(int row)
	{
		return userData.get(row);
	}

    public int findUserRow(int ID){
    	int row = -1;
    	for(int i = 0; i< getRowCount(); i++){
    		if (((int) getValueAt(i, 0)) == ID){row = i;break;}
    	}
    	return row;
    }
    
    public void removeRow(int row){
    	userData.remove(row);
		fireTableRowsDeleted(row, row);
    }
    
    
    public void replaceRow(int row, User user)
	{
		userData.set(row, user);
		fireTableRowsUpdated(row, row);
	}

}//end UserTableModel


