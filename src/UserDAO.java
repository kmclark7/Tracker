import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {

	ArrayList<User> arrayList = new ArrayList<User>();
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;

	public UserDAO() {

		makeConnection();

		try {
			String q = "SELECT * from user";

			st = con.createStatement();
			rs = st.executeQuery(q);

			while (rs.next()) {
				
				//I added the entry of all fields.
				//You need to read them all so that we can create a JPanel to edit them, too.
				//Also, you need them to form the correct type of ArrayList.
				//Also parsed strings to get integers
				int tempUserID = Integer.parseInt(rs.getString(1));
				String tempPassword = rs.getString(2);
				String tempLastName = rs.getString(3);
				String tempFirstName = rs.getString(4);
				String tempPosition = rs.getString(5);
				int tempAccessLevel = Integer.parseInt(rs.getString(6));
				String tempTeam = rs.getString(7);
				String tempEmail = rs.getString(8);
				
				User i = new User(tempUserID, tempPassword, tempLastName, tempFirstName,
						tempPosition, tempAccessLevel, tempTeam, tempEmail);
				arrayList.add(i);
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
		} catch (NumberFormatException ex) {
			System.out.println("Error converting String to integer.");
		} catch (SQLException ex) {
			System.out.println("Error with table or data");
		}
	}
	
		public String getCurrentList() {

			String str = "";

			return str;
		}

/*	//We probably won't need this and there were errors, so I commented out.
	public String getCurrentListFromUserID (String s) {
			String str = "";
			for (int i = 0; i < arrayList.size(); i++) {
				if (arrayList.get(i).getUserID().equals(s)){   
					str += arrayList.get(i).getPassword();
					str += "\n";

				}
			}
			return str;
		}
*/
		
		//***We will need a method here to check that the password entered matches.
		
		//Should also add getters and setters just in case.  
		
		
		
		
		//Don't need this now, but will need if we add the option to change password.
		public void insertNewPassword(User a) {
			makeConnection();

			try {
				System.out.println("Here");
				
			System.out.println(a.getUserID());
				String q = "insert into user (userID, password) values ('" + a.getUserID() + "', '" + a.getPassword() + "');";
				
				System.out.println(q);
				st = con.createStatement();
				st.executeUpdate(q);

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
		public void makeConnection() {
			String url = "jdbc:mysql://localhost:3306/tracker";
			String user = "root";
			String password = "Mommyof5SQL!";

			try {

				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(url, user, password);
				System.out.println("Connection made");

			} catch (Exception ex) {
				Logger lgr = Logger.getLogger(UserDAO.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
				System.out.println("Sql Exception");

			}

		}
	}
