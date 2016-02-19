package tracker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.sql.Date;

/**
 * @author Erin Harris
 *
 */
public class DefectDAO {
	
	private String[] fields = {"TICKET_ID", "SUMMARY", "DESCRIPTION", 
			"ASSIGNEE_ID", "PRIORITY_LEVEL", "DATE_ENTERED", "SUBMITTER", "STATUS",
			"COMMENTS"};
	public ArrayList<Defect> arrayList = new ArrayList<Defect>();
	private Connection con = null;
	private Statement st = null;
	private ResultSet rs = null;

	public DefectDAO() {

		makeConnection();

		try {

			String q = "SELECT * from items";

			st = con.createStatement();
			rs = st.executeQuery(q);

			while (rs.next()) {
				int tempTicketID = rs.getInt(1);
				String tempSummary = rs.getString(2);
				String tempDescription = rs.getString(3);
				int tempAssignee = rs.getInt(4);
				int tempPriority = rs.getInt(5);
				Date tempDateEntered = rs.getDate(6); // get date
				int tempSubmitter = rs.getInt(7);
				String tempStatus = rs.getString(8);
				String tempComments = rs.getString(9);
				Defect d = new Defect(tempTicketID, tempSummary, tempDescription, tempAssignee, tempPriority,
						tempDateEntered, tempSubmitter, tempStatus, tempComments);
				arrayList.add(d);
			}

			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (SQLException ex) {
			System.out.println("Error with table");
		}

	}


	public boolean insertNewDefect(Defect i) {

			String q = "insert into defect (summary, description, assignee_id, priority_level, date_entered, submitter, status, comments) "
					+ "values ('" + i.getSummary() + "', '" + i.getDescription() + "', '"
					+ i.getAssignee_id() + "', '" + i.getPriority_level() + "', '" + i.getDate_entered() + "', '"
					+ i.getSubmitter() + "', '" + i.getStatus() + "', '" + i.getComments() + "');";

			return executeDAO(q);
	}

	public boolean replaceDefect(Defect i){
			
		String q = "UPDATE defect SET ((summary, description, assignee_id, priority_level, date_entered, submitter, status, comments) "
					+ "values ('" + i.getSummary() + "', '" + i.getDescription() + "', '"
					+ i.getAssignee_id() + "', '" + i.getPriority_level() + "', '" + i.getDate_entered() + "', '"
					+ i.getSubmitter() + "', '" + i.getStatus() + "', '" + i.getComments() + "') WHERE ticketID = '"+i.getTicketID()+"';";
		
		return executeDAO(q);		
	}
	
	
	public boolean deleteDefect(Defect defect){
		
		String q = "delete from defect where TICKET_ID='" + defect.getTicketID() + "';";
		
		return executeDAO(q);
	}
	
	
	public boolean replaceFieldAt(Object value, Defect i, int col){
		
		String q = "";
		if(fields[col].equals("ASSIGNEE_ID")|| fields[col].equals("PRIORITY") || fields[col].equals("SUBMITTER")){
			int intValue = (int) value;
			q = "UPDATE defect SET "+fields[col]+" = "+ intValue + " WHERE TICKET_ID = '"+ i.getTicketID()+"';";
		}else{
			String stringValue = (String) value;
			q = "UPDATE defect SET "+fields[col]+" = '"+ stringValue + "' WHERE TICKET_ID = '"+ i.getTicketID()+"';";			
		}
		return executeDAO(q);
	}
	
	
	public Object getFieldAt(Defect i, int col){
		
		String q = "SELECT "+fields[col]+" from DEFECT WHERE TICKET_ID = '"+ i.getTicketID()+"';";
		return executeDAO(q);
	}
	
	public void searchDefect(Defect d) {

		makeConnection();

		try {

			String q = "";
			String criteria = "";
			int cnt = 0;

			Integer t = d.getTicketID();
			if (t != null) {
				criteria += "ticket_id=" + d.getTicketID();
			}

			Integer sb = d.getSubmitter();
			if (sb != null) {
				if (cnt > 0) {
					criteria += ", and ";
					criteria += "submitter=" + d.getSubmitter();
				}
			}

			Integer a = d.getAssignee_id();
			if (a != null) {
				if (cnt > 0) {
					criteria += ", and ";
					criteria += "assignee_id=" + d.getAssignee_id();
				}
			}

			Date dt = d.getDate_entered();
			if (dt != null) {
				if (cnt > 0) {
					criteria += ", and ";
					criteria += "date_entered=" + d.getDate_entered();
				}
			}

			String stat = d.getStatus();
			if (stat != null) {
				if (cnt > 0) {
					criteria += ", and ";
					criteria += "status=" + d.getStatus();
				}
			}

			Integer p = d.getPriority_level();
			if (p != null) {
				if (cnt > 0) {
					criteria += ", and ";
					criteria += "priority_level=" + d.getPriority_level();
				}
			}

			if (cnt > 0) {
				q = "select * from Defect where " + criteria + ";";
			} else {
				q = "select * from Defect;";
			}
			st = con.createStatement(); // throwing error
			st.executeUpdate(q); // throwing error
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException ex) {
			System.out.println("Error with table or data");

		}
	}

	
	private boolean executeDAO(String q){
		
		makeConnection();
		boolean hadSuccess = false;
		
		try {
			st = con.createStatement();
			st.executeUpdate(q);

			// close the statement and connection once we are done
			if (con != null) {
				con.close();
			}
			
			if (st != null) {
				st.close();
			}
			
			hadSuccess = true;
		} catch (SQLException ex) {
			System.out.println("Error with table or data");
			System.out.println(ex);
		}
		return hadSuccess;
	}
	
	
	private void makeConnection() {

		String url = "jdbc:mysql://localhost:3306/tracker";
		String user = "root";
		String password = "Mommyof5SQL!";

		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Connection made");

		} catch (Exception ex) {
			Logger lgr = Logger.getLogger(DefectDAO.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
			System.out.println("Sql Exception");

		}

	}
	
	
	
}
