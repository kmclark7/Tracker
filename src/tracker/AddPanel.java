package tracker;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * This object is a panel for creating and adding new defects to the system.
 */

/**
 * @author Erin Harris
 */

public class AddPanel extends JPanel {
	
	JLabel submitter = new JLabel("Submitter");
	JComboBox<String> subSelect;
	JLabel assignee = new JLabel("Assignee");
	JComboBox<String> assignSelect;
	JLabel summary = new JLabel("Enter a brief summary describing the defect");
	JTextField summaryEntry = new JTextField(255);
	JLabel status = new JLabel("Defect Status");
	JTextField statusEntry = new JTextField(25);
	JLabel description = new JLabel("Enter a detail description of the defect");
	JTextField descEntry = new JTextField(2500);
	JLabel priority = new JLabel("Enter a priority level");
	JTextField priorityEntry = new JTextField(2);
	JLabel comments = new JLabel("Comments");
	JTextField commentsEntry = new JTextField(2500);
	
	JPanel grid = new JPanel(); 
    JPanel labels = new JPanel(new GridLayout(0,1));
    JPanel data = new JPanel(new GridLayout(0,1));

	JButton submit = new JButton("Submit");
	JButton clear = new JButton("Clear");
	JButton back = new JButton("Back");

	DefectTableModel defectTableModel;
	UserTableModel userTableModel;
	TrackerPane tracker;
	Vector<String> userVector;
	
	int tempSubmitter;
	int tempAssignee;
	

	public AddPanel(TrackerPane tracker) {
		
		this.tracker = tracker;	
		defectTableModel = new DefectTableModel();
		userTableModel = new UserTableModel();

		//Set up combo boxes.
		userVector = userTableModel.getUserComboBoxInfo();
		subSelect = new JComboBox<String>(userVector);
		assignSelect = new JComboBox<String>(userVector);

		//Add button listener and combo box listener
		ButtonListener listener = new ButtonListener();		
		submit.addActionListener(listener);
		clear.addActionListener(listener);
		subSelect.addActionListener(listener);
		assignSelect.addActionListener(listener);

		setLayout(new BorderLayout());
	    
		Dimension dim = tracker.getPreferredSize();
		grid.setPreferredSize(new Dimension(dim.width-50, dim.height-50));

		labels.add(submitter);
		data.add(subSelect);
		labels.add(assignee);
		data.add(assignSelect);
		labels.add(summary);
		data.add(summaryEntry);
		labels.add(status);
		data.add(statusEntry);
		labels.add(description);
		data.add(descEntry);
		labels.add(priority);
		data.add(priorityEntry);
		labels.add(comments);
		data.add(commentsEntry);
		
		add(labels, BorderLayout.WEST);
		add(data, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

		buttonPanel.add(submit);
		buttonPanel.add(clear);

		add(buttonPanel, BorderLayout.SOUTH);// put buttons at the bottom of
												// panel
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {
			if (ev.getSource().equals(subSelect)){
				tempSubmitter = userTableModel.getUserAt(subSelect.getSelectedIndex()).getUserID();
			}else if (ev.getSource().equals(assignSelect)){
				tempAssignee = userTableModel.getUserAt(assignSelect.getSelectedIndex()).getUserID();
			} else if (ev.getSource() == submit) {
				int tempTicketID = -1;  //temporary till auto-assigned
				LocalDateTime tempDateTime = LocalDateTime.now();	
				String tempSummary = summaryEntry.getText();
				String tempDescription = descEntry.getText();
				int tempPriority = Integer.parseInt(priorityEntry.getText());
				String tempComments = commentsEntry.getText();
				String tempStatus = "New";
				//(int t, String sm, String de, int a, int p, LocalDateTime dt, int sb, String st, String c){
				Defect d = new Defect(tempTicketID, tempSummary, tempDescription, tempAssignee, tempPriority,
						tempDateTime, tempSubmitter, tempStatus, tempComments);
				defectTableModel.addDefect(d);

				subSelect.setSelectedIndex(0);
				assignSelect.setSelectedIndex(0);
				summaryEntry.setText("");
				status.setText("");
				descEntry.setText("");
				priorityEntry.setText("");
				commentsEntry.setText("");

				tracker.updateDefectPanel();
			}
			//Don't need a "back" -  will just change tab.
		}
	}
}
