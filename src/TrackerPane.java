import java.awt.Dimension;
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
public class TrackerPane extends JTabbedPane{

	private final String LOG = "LOGIN / LOGOUT";
	private final String VIEW = "VIEW RECORD";
	private final String ADD = "ENTER NEW";
	private final String EDIT = "EDIT RECORD";
	private final String CHANGE = "VIEW CHANGES";
	private final String USER = "USER DATA";
	
	private final String LOG_TEXT = "Log in or out.";
	private final String VIEW_TEXT = "View Defect Records.";
	private final String ADD_TEXT = "Add New Defect Record.";
	private final String EDIT_TEXT = "Edit Existing Defect.";
	private final String CHANGE_TEXT = "Show Details of Changes Made.";
	private final String USER_TEXT = "Add or Edit User Information.";
			
	private boolean isLoggedIn;
	private JPanel content;
	private TrackerPane tracker;
	private LoginUserPanel logPanel;
	private ViewPanel viewPanel;
	private AddPanel addPanel;
	private EditPanel editPanel;
	private ChangePanel changePanel;
	private UserPanel userPanel;


	// Create a tabbed pane and fonts for tabs	
	private JTabbedPane tabPane = new JTabbedPane();
	Font unselectedTabFont = new Font("Dialog", Font.PLAIN, 18);
	Font selectedTabFont = new Font("Dialog", Font.BOLD, 18);
			
	
	public TrackerPane(JPanel content){
		this(content, false);
	}
	
	private TrackerPane(JPanel content, boolean isLoggedIn){
		
		this.isLoggedIn = isLoggedIn;
		this.content = content;
		
		//Set size, fonts, etc. for tabbed pane
		tabPane.setPreferredSize(new Dimension(1600, 800));
		tabPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabPane.setFont(unselectedTabFont);
		tabPane.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

				
		//Create the panels for each tab
		//*********Re-make for Role/AccessLevel*********
		
		int index = 0;
		
		//This sets up Login Tab only if not logged in
		if(!isLoggedIn){
			
			logPanel = new LoginUserPanel(this, false);
			tabPane.insertTab(LOG, null, logPanel,LOG_TEXT , index++);
			System.out.println(LOG+" "+LOG_TEXT+" "+index);
			content.add(tabPane);
			
		//This sets up other tabs if logged in.
		}else{
			
			logPanel = new LoginUserPanel(this, isLoggedIn);
			tabPane.insertTab(LOG, null, logPanel, LOG_TEXT, index++);

			viewPanel = new ViewPanel(this);	
			tabPane.insertTab(VIEW, null, viewPanel,VIEW_TEXT , index++);

			addPanel = new AddPanel(this);	
			tabPane.insertTab(ADD, null, addPanel, ADD_TEXT, index++);

			editPanel = new EditPanel(this);	
			tabPane.insertTab(EDIT, null, editPanel, EDIT_TEXT, index++);

			changePanel = new ChangePanel(this);
			tabPane.insertTab(CHANGE, null, changePanel, CHANGE_TEXT, index++);

			userPanel = new UserPanel(this);
			tabPane.insertTab(USER, null, userPanel, USER_TEXT, index++);
			System.out.println(USER+"  "+USER_TEXT+" "+ index);
			
			content.add(tabPane);
			tabPane.setSelectedComponent(viewPanel);	
		}
		
		
	}//end Constructor

		
	
	public void LogIn(){
      
		content.remove(tabPane);
		new TrackerPane(content, true);
		content.revalidate();
	}

	public void LogOut(){
		content.removeAll();
		new TrackerPane(content, false);
		content.revalidate();
	}

}//end TrackerPane


