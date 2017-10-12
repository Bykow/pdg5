package persistent;

import java.util.Date;

import org.joda.time.DateTime;

public class Tournament {
	private int ID;
	private String title;
	private Date created;
	public Tournament(int iD, String title, Date created) {
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
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}

}
