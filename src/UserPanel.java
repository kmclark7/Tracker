import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

//list of the J functions is in Heads Up book on page 413
//Border Layout

public class UserPanel extends JPanel{
	//Container c = getRootPane();
	UserDAO userDAO = new UserDAO();
	JLabel title = new JLabel("VIEW USER LIST");
	TrackerPane tracker;
	
	//this will create an array list based on the array list taskDAO so they are the same
	ArrayList<User> userModel = new ArrayList<User>(userDAO.arrayList);
	//ListTask abc = new ListTask("Sample room", "Sample Task");
	//JList user = new JList(userModel.toArray());
	UserTableModel userTableModel = new UserTableModel(userModel);
	JTable userTable = new JTable(userTableModel);
	JScrollPane scroll = new JScrollPane(userTable);
	JButton back = new JButton("Back");
	JButton addUser = new JButton("Add user");
	JButton search = new JButton("Search user");
	JButton remove = new JButton("Remove user");
		
	//Added a parameter with the name of the main TrackerPane in case needed.
	public UserPanel(TrackerPane tracker){

		this.tracker = tracker;
		
		title.setFont(new Font("Serif", Font.PLAIN, 16));
		setLayout(new BorderLayout());
		//back.setToolTipText("Go back to main");
		addUser.setToolTipText("Add new user to system");
		search.setToolTipText("Search by Last Name, First Name");
		remove.setToolTipText("Highlight task you want removed and click this button.");
		userTable.setBackground(Color.lightGray);
	    
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(350, 200));
			
		add(title, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		
		ButtonListener l = new ButtonListener();
		//back.addActionListener(l);
		search.addActionListener(l);
		remove.addActionListener(l);
		
		JPanel buttonPanel = new JPanel();
		//buttonPanel.add(back);
		buttonPanel.add(addUser);
		buttonPanel.add(search);
		buttonPanel.add(remove);
		
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
/*		if(e.getSource().equals(back)){
            removeAll();
            //JTabbedPane newPanel=new TrackerPane(getAutoscrolls());
            UserPanel newPanel = new UserPanel(tracker);
            newPanel.setBackground(Color.lightGray);
            add(newPanel);
            revalidate();
            newPanel.repaint();
		}
*/		
		if(e.getSource().equals(remove)){
			
			int index = userTable.getSelectedRow();
			//the value of list.getSelectedIndex() will be -1 if it isn't selected
			//System.out.println(list.getSelectedIndex());
			
			if (index != -1) {
				
				User tempUser = userModel.get(index);
				userDAO.deleteUser(tempUser);
				
				//userModel.remove(index);
				userTableModel.removeRow(index);
				//user.setListData(userModel.toArray());
				scroll.revalidate();
				scroll.repaint();
			}
				
			System.out.println("Remove selected object");
		}
		
		if(e.getSource().equals(search)){
			
			removeAll();
            JPanel newPanel = new UserSearch();
            add(newPanel);
            revalidate();
		}
		
/*		if(e.getSource().equals(addUser)){
			removeAll();
            JPanel newPanel =new UserAdd();
            add(newPanel);
            revalidate();
   */         
		
     
     
			
		}
		
	}
	
	
}

