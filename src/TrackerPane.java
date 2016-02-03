import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * 
 */

/**
 * @author Kristin Clark
 *
 */
public class TrackerPane {
	final String LOGIN = "LOGIN / LOGOUT";
	final String VIEW = "VIEW RECORD";
	final String ADD = "ENTER NEW";
	final String EDIT = "EDIT RECORD";
	final String USER = "USER DATA";
	final String CHANGE = "VIEW CHANGES";
	final int LOGIN_TAB = 0;
	final int VIEW_TAB = 1;
	final int ADD_TAB = 2;
	final int EDIT_TAB = 3;
	final int USER_TAB = 4;
	final int CHANGE_TAB = 5;

	LoginUserPanel loginUserPanel;
	ViewPanel viewPanel;
	AddPanel addPanel;
	EditPanel editPanel;
	ChangePanel changePanel;
	UserPanel userPanel;
	
	
	TrackerPane(JPanel content, boolean isLoggedIn){
		
		// Create a tabbed pane.
		JTabbedPane trackerPane = new JTabbedPane();
		trackerPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		trackerPane.setFont(new Font("Dialog", Font.BOLD, 18));
		
		//Add the tabs
		LoginUserPanel logPanel = new LoginUserPanel(content);
		trackerPane.addTab(LOGIN, logPanel);
		
		ViewPanel viewPanel = new ViewPanel(content);
		trackerPane.addTab(VIEW, viewPanel);
		
		AddPanel addPanel = new AddPanel(content);
		trackerPane.addTab(ADD, addPanel);
		
		EditPanel editPanel = new EditPanel(content);
		trackerPane.addTab(EDIT, editPanel);
		
		ChangePanel changePanel = new ChangePanel(content);
		trackerPane.addTab(CHANGE, changePanel);
		
		UserPanel userPanel = new UserPanel(content);
		trackerPane.addTab(USER, userPanel);
		
		content.add(trackerPane);
		
		//if not logged in, limit access
		if(isLoggedIn){
			trackerPane.setEnabledAt(VIEW_TAB, false);
			trackerPane.setEnabledAt(ADD_TAB, false);
			trackerPane.setEnabledAt(EDIT_TAB, false);
			trackerPane.setEnabledAt(CHANGE_TAB, false);
			trackerPane.setEnabledAt(USER_TAB, false);		
		}
	}
}
