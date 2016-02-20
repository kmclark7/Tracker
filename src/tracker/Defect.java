package tracker;
import java.time.LocalDateTime;

/**
 * @author Erin Harris
 *
 */
public class Defect {
	
	protected static String[] fields = {"TICKET_ID", "SUMMARY", "DESCRIPTION", 
			"ASSIGNEE_ID", "PRIORITY_LEVEL", "DATE_ENTERED", "SUBMITTER", "STATUS",
			"COMMENTS"};	
	private int ticket_id;
	private String summary;
	private String description;
	private int assignee_id;
	private int priority_level;
	private LocalDateTime date_entered;
	private int submitter;
	private String status;
	private String comments;
	
	/**
	 * @param tempTicketID
	 * @param tempSummary
	 * @param tempDescription
	 * @param tempAssignee
	 * @param tempPriority
	 * @param tempDateEntered
	 * @param tempSubmitter
	 * @param tempStatus
	 * @param tempComments
	 */

	public Defect(int t, String sm, String de, int a, int p, LocalDateTime dt, int sb, String st, String c){
		ticket_id = t;
		summary = sm;
		description = de;
		assignee_id = a;
		priority_level = p;
		date_entered = dt;
		submitter = sb;
		status = st;
		comments = c;
			
	}
	
	public int getTicketID() {
		return ticket_id;
	}

	public void setTicketID(int ticket_id) {
		this.ticket_id = ticket_id;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	

	public int getAssignee_id() {
		return assignee_id;
	}

	public void setAssignee_id(int value) {
		this.assignee_id = value;
	}

	public int getPriority_level() {
		return priority_level;
	}

	public void setPriority_level(int priority_level) {
		this.priority_level = priority_level;
	}

	public LocalDateTime getDate_entered() {
		return date_entered;
	}

	public void setDate_entered(LocalDateTime date_entered) {
		this.date_entered = date_entered;
	}

	public int getSubmitter() {
		return submitter;
	}

	public void setSubmitter(int submitter) {
		this.submitter = submitter;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
