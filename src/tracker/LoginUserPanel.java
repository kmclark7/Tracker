package tracker;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This is a panel object that displays a login screen and handles logging in and out.
 */
/**
 * @author Kerty Levy
 */

//Renamed class and Constructor to start with capital.
public class LoginUserPanel extends JPanel {

	//String[] User = {"userID", "password"};   //don't need this?
	
	private JLabel loginInstructions = new JLabel("Please enter your user ID and password to access the tracker system");
	private JLabel userIDLabel = new JLabel ("UserID");
	private JTextField userIDText = new JTextField(50);
	private JLabel passwordLabel = new JLabel("Password");
	private JTextField passwordText = new JTextField(50);
	private JButton login = new JButton("login");
	private JButton logout = new JButton("logout");
	private UserDAO userDAO = new UserDAO();
	//Added name of main panel to call methods from
	private TrackerPane tracker;
	
	//Added a JPanel in the constructor so that we can 
	//pass it the name of the main panel when needed.
	public LoginUserPanel(TrackerPane tracker, boolean isLoggedIn){
		
		this.tracker = tracker;
	
		ButtonListener b = new ButtonListener();
		login.addActionListener(b);		
		logout.addActionListener(b);	

		setLayout(new BorderLayout());
		
		loginInstructions.setFont(new Font("Serif", Font.PLAIN, 16));
		add(loginInstructions, BorderLayout.NORTH);
		
		// to line these up properly, instead of 2x0 grid,
		// make one JPanel with a 2x2 grid.  
		//Also, try putting in NORTH or WEST instead of CENTER
		//so that they do not expand.
		JPanel buttonLabels = new JPanel(new GridLayout(2,0));
		JPanel textBoxes = new JPanel(new GridLayout(2,0));	
		
		buttonLabels.add(userIDLabel);
		textBoxes.add(userIDText);
		buttonLabels.add(passwordLabel);
		textBoxes.add(passwordText);
	
		add(buttonLabels, BorderLayout.WEST);
		add(textBoxes, BorderLayout.CENTER);
		
		add(buttonLabels, BorderLayout.WEST);
		add(textBoxes, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(login);
		buttonPanel.add(logout);
		
		add(buttonPanel, BorderLayout.SOUTH);		
		
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource().equals(login)) {
				
				String tempUserID = userIDText.getText();
				String tempPassword = passwordText.getText();
				//Need to search for this userID and see if password matches.
				//	will need a method in userDAO to do this.
				//User i = new User(tempUserID, tempPassword);
				//UserDAO.insertNewUserID(i);		//You only need to do this if changing ID
				//UserDAO.insertNewPassword(p);		//you only need to do this if changing Password
				userIDText.setText("");
				passwordText.setText("");
				System.out.println("UID and PW entered");
				
				//need to add a call that will update the logged-in status to true
				//For now, set isLoggedIn to true.
				boolean isLoggedIn = true;
				if(isLoggedIn){tracker.LogIn();}
			
				//Added procedure for logout.
			}else if (e.getSource().equals(logout)){
					tracker.LogOut();
			}
	
		}
	}
}
