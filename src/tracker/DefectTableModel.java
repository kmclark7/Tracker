/**
 * 
 */
package tracker;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 * @author Kristin Clark
 *
 */
public class DefectTableModel extends AbstractTableModel{
	
	private String[] defectColumnNames = { "Ticket ID", "Summary", "Description", "Assigned To", "Priority Level",
			"Date Entered", "Submitted By", "Current Status", "Comments"};
	
	// private boolean[] isColumnEditable = {false, true, true, true, true, true, true, true};
	private DefectDAO defectDAO = new DefectDAO();
	private ArrayList<Defect> defectModel = new ArrayList<Defect>(defectDAO.arrayList);
	private TrackerPane tracker;

	
	// Constructor
	public DefectTableModel(TrackerPane tracker) {
		this.tracker = tracker;
	}

	
	public Defect getDefectAt(int row) {
		return this.defectModel.get(row);
	}

	
	public void insertDefect(int row, Defect defect) {
		boolean hadSuccess = defectDAO.insertNewDefect(defect);
		if (hadSuccess) {
			defectModel.add(row, defect);
			popUpSuccessMessage("Defect record entered into database.");
		} else {
			popUpErrorMessage("Problem saving to database.");
		}
		fireTableRowsInserted(row, row);
	}

	
	public void addDefect(Defect defect) {
		insertDefect(getRowCount(), defect);
	}

	
	public void removeDefectAt(int row) {
		removeDefect(row, getDefectAt(row));
	}
	
	
	private void removeDefect(int row, Defect defect) {
		
		boolean hadSuccess = defectDAO.deleteDefect(defect);
		if (hadSuccess) {
			defectModel.remove(defect);
			popUpSuccessMessage("Removed defect record from database.");
		} else {
			popUpErrorMessage("Error removing defect record from database.");
		}
		fireTableRowsDeleted(row, row);
	}

	
	public void replaceDefectAt(int row) {
		Defect defect = getDefectAt(row);
		replaceDefect(row, defect);
	}

	
	public void replaceDefect(int row, Defect defect) {
		
		Defect oldDefect = getDefectAt(row);
		boolean hadSuccess = defectDAO.replaceDefect(defect);
		
		if (hadSuccess) {
			defectModel.set(row, defect);
			popUpSuccessMessage("Defect record replaced in database.");
		}else {
			popUpErrorMessage("Error replacing defect record in database.");
			defectModel.set(row, oldDefect);
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
		return defectColumnNames.length;
	}// end getColumnCount

	
	@Override
	public String getColumnName(int column) {
		return this.defectColumnNames[column];
	}// end getColumnName

	
	@Override
    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
	}

	
	@Override
	public int getRowCount() {
		return defectModel.size();
	}// end getRowCount


	
	@Override
	public Object getValueAt(int row, int col) {

		switch (col) {

		case 0:
			return this.defectModel.get(row).getTicketID();
		case 1:
			return this.defectModel.get(row).getSummary();
		case 2:
			return this.defectModel.get(row).getDescription();
		case 3:
			return this.defectModel.get(row).getAssignee_id();
		case 4:
			return this.defectModel.get(row).getPriority_level();
		case 5:
			return this.defectModel.get(row).getDate_entered();
		case 6:
			return this.defectModel.get(row).getSubmitter();
		case 7:
			return this.defectModel.get(row).getStatus();
		case 8:
			return this.defectModel.get(row).getComments();
		default:
			popUpErrorMessage("Data access error.");
			return null;
		}
	}// end getValueAt
	
	public void setValueAt(Object value, int row, int col) {

		Object oldValue = getValueAt(row, col);
		boolean hadSuccess = defectDAO.replaceFieldAt(value, getDefectAt(row), col);
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
			defectModel.get(row).setTicketID(((int) value));
			break;		
		case 1: 
			defectModel.get(row).setSummary((String) value);
			break;		
		case 2: 
			defectModel.get(row).setDescription((String) value);
			break;			
		case 3: 
			defectModel.get(row).setAssignee_id((int) value);
			break;			
		case 4: 
			defectModel.get(row).setPriority_level((int) value);
			break;		
		case 5: 
			defectModel.get(row).setDate_entered((LocalDateTime) value);
			break;		
		case 6: 
			defectModel.get(row).setSubmitter((int) value);
			break;		
		case 7: 
			defectModel.get(row).setStatus((String) value);	
			break;
		case 8: 
			defectModel.get(row).setComments((String) value);
			break;
		default:
			popUpErrorMessage("Error retrieving data.");
		}//end switch
		fireTableCellUpdated(row, col);
	}
	
	public int findDefectRow(int ID) {
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
	
}// end DefectTableModel



