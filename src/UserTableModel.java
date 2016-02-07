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
	private Class<?>[] colClass = {int.class, String.class, String.class, String.class, String.class,
		int.class, String.class, String.class};
	//private Class<User> rowClass = User.class;
	//private boolean isModelEditable = true;
	UserDAO userDAO = new UserDAO();
	ArrayList<User> userModel = new ArrayList<User>(userDAO.arrayList);

	//Change these later!!!!!
	//private boolean[] isColumnEditable = {false, true, true, true, true, true, true, true};

	private ArrayList<User> userData;

	
	public UserTableModel(ArrayList<User> userData) {
		this.userData = userData;
	}
	
    @Override
    public boolean isCellEditable(int row, int column) {
       //all cells false
       return true;
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
	public Class<?> getColumnClass(int columnIndex) {
		return colClass[columnIndex];
	}//end getColumnClass

	
	@Override
	public int getRowCount() {
		return this.userData.size();
	}//end getRowCount

	public int getUSerCount() {
		return getRowCount();
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

			case 0: {userData.get(row).setUserID(Integer.parseInt((String) value));break;}
			case 1:	{userData.get(row).setPassword((String) value);break;}
			case 2:	{userData.get(row).setLastName((String) value);break;}
			case 3:	{userData.get(row).setFirstName((String) value);break;}
			case 4:	{userData.get(row).setPosition((String) value);break;}
			case 5:	{userData.get(row).setAccessLevel(Integer.parseInt((String) value));break;}
			case 6:	{userData.get(row).setTeam((String) value);break;}
			case 7:	{userData.get(row).setEmail((String) value);break;}
			}//Need functions to set in DAO
	        fireTableCellUpdated(row, col);
	  }//end setValueAt
	        
    
    public User getUserAt(int rowIndex){
    	return this.userData.get(rowIndex);
    }
    
	public void insertUser(int row, User user)
	{
		boolean hadSuccess = userDAO.insertNewUser(user);
		if(hadSuccess) {
			userData.add(row, user);
			fireTableRowsInserted(row, row);
		}
	}

    public void addUser(User user)
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
    
    public void removeUser(int row, User user){
    	boolean hadSuccess = userDAO.deleteUser(user);
    	if (hadSuccess){
    		userData.remove(user);
    		fireTableRowsDeleted(row, row);
    	}
    }
    
    public void removeUserAt(int row){
    	removeUser(row, getUser(row));
    }
    public void replaceUser(int row, User user)
	{
    	//update this when method added.
		userData.set(row, user);
		fireTableRowsUpdated(row, row);
	}//Need DAO

}//end UserTableModel


