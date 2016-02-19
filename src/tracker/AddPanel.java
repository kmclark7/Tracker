package tracker;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;

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
	JComboBox<Integer> subSelect = new JComboBox<Integer>();
	JLabel assignee = new JLabel("Assignee");
	JComboBox<Integer> assignSelect = new JComboBox<Integer>();
	JLabel date = new JLabel("Date Detected");// Add a format, mask with / /
	//JTextField dateEnter = new JTextField(10);	//this should be automatically entered
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

	UserDAO userDAO = new UserDAO(); // need to access UserDAO to get submitter
										// and assignee lists

	public AddPanel(TrackerPane tracker) {

		ButtonListener AP = new ButtonListener();
		submit.addActionListener(AP);
		clear.addActionListener(AP);

		setLayout(new BorderLayout());
	    
		Dimension dim = tracker.getPreferredSize();
		grid.setPreferredSize(new Dimension(dim.width-50, dim.height-50));

		labels.add(submitter);
		data.add(subSelect);
		labels.add(assignee);
		data.add(assignSelect);
		//labels.add(date);			//This should be set by the program when entered into database.
		//data.add(dateEnter);		//Need this in DefectDAO
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
		//buttonPanel.add(back);

		add(buttonPanel, BorderLayout.SOUTH);// put buttons at the bottom of
												// panel
	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent AP) {

			if (AP.getSource() == submit) {

				Toolkit tempSubmitter = subSelect.getToolkit();// get from combo
																// box
				Toolkit tempAssignee = assignSelect.getToolkit();
				//Need to know how to represent date/time correctly
				String tempSummary = summaryEntry.getText();
				String tempDescription = descEntry.getText();
				int tempPriority = Integer.parseInt(priorityEntry.getText());
				String tempComments = commentsEntry.getText();
				//Date must be initialized first.
				//Defect d = new Defect(tempSubmitter, tempAssignee, tempSummary, tempDescription,
				//		tempPriority, tempComments);
				//DefectDAO.insertNewDefect(d);// Don't know if DefectDAO is class
												// name. Will change if needed.  Must create!

				subSelect.setToolTipText("");
				assignSelect.setToolTipText("");
				//dateEnter.setText("");
				summaryEntry.setText("");
				statusEntry.setText("");
				descEntry.setText("");
				priorityEntry.setText("");
				commentsEntry.setText("");

				System.out.println("Defect Submitted");
			}
			//Don't need a "back" -  will just change tab.
		}
	}
}
