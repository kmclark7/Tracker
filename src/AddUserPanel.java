import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddUserPanel extends JPanel{
	//Grid Layout used
	
	
	JLabel title = new JLabel("ADD USER TO DEFECT TRACKER SYSTEM");
	JLabel userLabel = new JLabel("User ID");
	JTextField user = new JTextField(10);
	JLabel lNameLabel = new JLabel("Last Name");
	JTextField lName = new JTextField(50);
	JLabel fNameLabel = new JLabel("First Name");
	JTextField fName = new JTextField(50);
	JLabel positionLabel = new JLabel("Position");
	JTextField position = new JTextField(75);
	JLabel accessLevelLabel = new JLabel("Access Level");
	JTextField accessLevel = new JTextField(2);
	JLabel teamLabel = new JLabel("Team");
	JTextField team = new JTextField(50);
	JLabel emailLabel = new JLabel("Email");
	JTextField email = new JTextField(100);
	JLabel passwordLabel = new JLabel("Password");
	JTextField password = new JTextField(15);
	JButton submit = new JButton("Add User");
	//JButton back = new JButton("Back");
	
	UserDAO userDAO = new UserDAO();
	ArrayList<User> userModel = new ArrayList<User>(userDAO.arrayList);
	UserTableModel userTableModel = new UserTableModel(userModel);
	
	
	public AddUserPanel(TrackerPane tracker){
				
		ButtonListener b = new ButtonListener();
		submit.addActionListener(b);
		//back.addActionListener(b);
		
		setLayout(new BorderLayout());
		
		title.setFont(new Font("Serif", Font.PLAIN, 16));
		add(title, BorderLayout.NORTH);
		
		JPanel buttonLabels = new JPanel(new GridLayout(8,0));
		JPanel textBoxes = new JPanel(new GridLayout(8,0));
		
		buttonLabels.add(userLabel);
		user.setEditable(false);              //this is auto-inc -- How to handle this??
		//  SELECT LAST_INSERT_ID();
		textBoxes.add(user);	
		buttonLabels.add(lNameLabel);
		textBoxes.add(lName);
		buttonLabels.add(fNameLabel);
		textBoxes.add(fName);
		buttonLabels.add(positionLabel);
		textBoxes.add(position);
		buttonLabels.add(accessLevelLabel);
		textBoxes.add(accessLevel);
		buttonLabels.add(teamLabel);
		textBoxes.add(team);
		buttonLabels.add(emailLabel);
		textBoxes.add(email);
		buttonLabels.add(passwordLabel);
		textBoxes.add(password);
				
		add(buttonLabels, BorderLayout.WEST);
		add(textBoxes, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
	
		buttonPanel.add(submit);
		//buttonPanel.add(back);
		
		add(buttonPanel, BorderLayout.SOUTH);		
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == submit) {
				//String tempUser = user.getText();
				String tempLName = lName.getText();
				String tempFName = fName.getText();
				String tempPosition = position.getText();
				String tempAccessLevel = accessLevel.getText();
				String tempTeam = team.getText();
				String tempEmail = email.getText();
				String tempPassword = password.getText();
				//int tempUserInt = Integer.parseInt(tempUser);
				//Changed to -1, but is not used.
				int tempUserInt = -1;
				int tempAccessLevelInt = Integer.parseInt(tempAccessLevel);
				//Just changed the order to match - moved Password (Kris)
				User u = new User(tempUserInt,tempPassword, tempLName, tempFName, tempPosition, tempAccessLevelInt, tempTeam, tempEmail);
				userDAO.insertNewUser(u);
				UserDAO userDAO = new UserDAO();
				System.out.println(userTableModel.getRowCount()-1+"  "+userTableModel.getValueAt(userTableModel.getRowCount()-1, 0));
				tempUserInt = (int) userTableModel.getValueAt(userTableModel.getRowCount()-1, 0);
				userTableModel.addRow(u);
				u.setUserID(tempUserInt);
				//user.setText("");
				lName.setText("");
				fName.setText("");
				position.setText("");
				accessLevel.setText("");
				team.setText("");
				email.setText("");
				password.setText("");
				
				System.out.println("Added new user to database");
			}
			
			
			
/*			if(e.getSource() == back){
				removeAll();
                JPanel newPanel=new UserAdd();
                add(newPanel);
                revalidate();
                newPanel.repaint();
			}
*/		}
		
	}

}
