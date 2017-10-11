package persistent;

import org.joda.time.DateTime;

public class Tournament {
	private int ID;
	private String title;
	private DateTime created;
	public Tournament(int iD, String title, DateTime created) {
		super();
		ID = iD;
		this.title = title;
		this.created = created;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public DateTime getCreated() {
		return created;
	}
	public void setCreated(DateTime created) {
		this.created = created;
	}

}
