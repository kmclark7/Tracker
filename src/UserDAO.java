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

				// I added the entry of all fields.
				// You need to read them all so that we can create a JPanel to
				// edit them, too.
				// Also, you need them to form the correct type of ArrayList.
				// Also parsed strings to get integers
				int tempUserID = Integer.parseInt(rs.getString(1));
				String tempPassword = rs.getString(2);
				String tempLastName = rs.getString(3);
				String tempFirstName = rs.getString(4);
				String tempPosition = rs.getString(5);
				int tempAccessLevel = Integer.parseInt(rs.getString(6));
				String tempTeam = rs.getString(7);
				String tempEmail = rs.getString(8);

				User i = new User(tempUserID, tempPassword, tempLastName, tempFirstName, tempPosition, tempAccessLevel,
						tempTeam, tempEmail);
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

	// public String getCurrentList() {

	// String str = "";

	// return str;
	// }

	// Changed to use to print data to screen if needed
	public void getCurrentList() {
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println(arrayList.get(i).getUserID());
			System.out.println(arrayList.get(i).getPassword());

		}
	}

	// ***We will need a method here to check that the password entered matches.

	// Should also add getters and setters just in case.

	// Don't need this now, but will need if we add the option to change
	// password.
	public boolean insertNewPassword(User a) {
		makeConnection();
		boolean hadSuccess = true;

		try {

			System.out.println(a.getUserID());
			String q = "insert into user (userID, password) values ('" + a.getUserID() + "', '" + a.getPassword()
					+ "');";

			st = con.createStatement();
			st.executeUpdate(q);

			if (st != null) {
				st.close();
				hadSuccess = true;
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException ex) {
			System.out.println("Error with table or data");
		}
		return hadSuccess;
	}

	public boolean insertNewUser(User u) {
		makeConnection();
		boolean hadSuccess = false;

		try {
			String q = "insert into user (LAST_NAME, FIRST_NAME, POSITION, ACCESS_LEVEL, TEAM, EMAIL, PASSWORD) values "
					+ "('" + u.getLastName() + "', '" + u.getFirstName() + "', '" + u.getPosition() + "', '"
					+ u.getAccessLevel() + "', '" + u.getTeam() + "', '" + u.getEmail() + "', '" + u.getPassword()
					+ "');";
			st = con.createStatement();
			System.out.println(q);
			st.executeUpdate(q);
			if (st != null) {
				st.close();
				hadSuccess = true;
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException ex) {
			System.out.println("Error with table or data");
		}
		return hadSuccess;
	}

	// These added by Milissa
	public boolean deleteUser(User tempUser) {
		// TODO Auto-generated method stub

		makeConnection();
		boolean hadSuccess = false;
		
		try {
			String q = "delete from user where userID='" + tempUser.getUserID() + "';";
			st = con.createStatement();
			st.executeUpdate(q);

			// close the statement and connection once we are done
			if (con != null) {
				con.close();
				hadSuccess = true;
			}
			
			if (st != null) {
				st.close();
			}
			
		} catch (SQLException ex) {
			System.out.println("Error with table or data");
			System.out.println(ex);
	
		}

		return hadSuccess;
	}

	public boolean searchUser(User u) {
		makeConnection();
		boolean hadSuccess = false;

		try {
			String q = "";
			String criteria = "";
			int cnt = 0;

			Integer uid = u.getUserID();
			if (uid != null) {
				criteria += "userid=" + u.getUserID();
			}
			if (u.getLastName() != null) {
				if (cnt > 0) {
					criteria += ", and ";
				}
				criteria += "last_name=" + u.getLastName();
			}
			if (u.getFirstName() != null) {
				if (cnt > 0) {
					criteria += ", and ";
				}
				criteria += "first_name=" + u.getFirstName();
			}
			if (u.getPosition() != null) {
				if (cnt > 0) {
					criteria += ", and ";
				}
				criteria += "position=" + u.getPosition();
			}

			Integer al = u.getAccessLevel();
			if (al != null) {
				if (cnt > 0) {
					criteria += ", and ";
				}
				criteria += "access_level=" + u.getAccessLevel();
			}
			if (u.getTeam() != null) {
				if (cnt > 0) {
					criteria += ", and ";
				}
				criteria += "team=" + u.getTeam();
			}
			if (u.getEmail() != null) {
				if (cnt > 0) {
					criteria += ", and ";
				}
				criteria += "email=" + u.getEmail();
			}
			if (u.getPassword() != null) {
				if (cnt > 0) {
					criteria += ", and ";
				}
				criteria += "password=" + u.getPassword();
			}
			if (cnt > 0) {
				q = "select * from user where " + criteria + ";";
			} else {
				q = "select * from user;";
			}
			st = con.createStatement();
			st.executeUpdate(q);
			if (st != null) {
				st.close();
				hadSuccess = true;
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException ex) {
			System.out.println("Error with table or data");
		}
		return hadSuccess;
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
