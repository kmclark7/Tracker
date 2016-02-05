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

public class UserSearch extends JPanel{
	//Grid Layout used
	
	
	JLabel title = new JLabel("SEARCH FOR USER IN DEFECT TRACKER SYSTEM");
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
	JButton submit = new JButton("Search");
	JButton back = new JButton("Back");
	TrackerPane tracker;
	UserDAO userDAO = new UserDAO();
	
	
	public void UserAdd(){
		
		
		ButtonListener b = new ButtonListener();
		submit.addActionListener(b);
		back.addActionListener(b);
		
		setLayout(new BorderLayout());
		
		title.setFont(new Font("Serif", Font.PLAIN, 16));
		add(title, BorderLayout.NORTH);
		
		JPanel buttonLabels = new JPanel(new GridLayout(8,0));
		JPanel textBoxes = new JPanel(new GridLayout(8,0));
		
		buttonLabels.add(userLabel);
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
		buttonPanel.add(back);
		
		add(buttonPanel, BorderLayout.SOUTH);		
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == submit) {
				String tempUser = user.getText();
				String tempLName = lName.getText();
				String tempFName = fName.getText();
				String tempPosition = position.getText();
				String tempAccessLevel = accessLevel.getText();
				String tempTeam = team.getText();
				String tempEmail = email.getText();
				String tempPassword = password.getText();
				int tempUserInt = Integer.parseInt(tempUser);
				int tempAccessLevelInt = Integer.parseInt(tempAccessLevel);
				//Changed to match order - password moved up (Kris)
				User u = new User(tempUserInt, tempPassword, tempLName, tempFName, tempPosition, tempAccessLevelInt, tempTeam, tempEmail);
				userDAO.searchUser(u);
				
				user.setText("");
				lName.setText("");
				fName.setText("");
				position.setText("");
				accessLevel.setText("");
				team.setText("");
				email.setText("");
				password.setText("");
				
				System.out.println("Added new user to database");
			}
			
			
			
			if(e.getSource() == back){
				removeAll();
                AddUserPanel newPanel=new AddUserPanel(tracker);
                add(newPanel);
                revalidate();
                newPanel.repaint();
			}
		}
		
	}

}

