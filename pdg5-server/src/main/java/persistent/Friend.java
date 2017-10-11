package persistent;

import org.joda.time.DateTime;

public class Friend {
	private int ID;
	private int fromUser;
	private int toUser;
	private DateTime lastModified;
	public Friend(int iD, int fromUser, int toUser, DateTime lastModified) {
		super();
		ID = iD;
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.lastModified = lastModified;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getFromUser() {
		return fromUser;
	}
	public void setFromUser(int fromUser) {
		this.fromUser = fromUser;
	}
	public int getToUser() {
		return toUser;
	}
	public void setToUser(int toUser) {
		this.toUser = toUser;
	}
	public DateTime getLastModified() {
		return lastModified;
	}
	public void setLastModified(DateTime lastModified) {
		this.lastModified = lastModified;
	}

}
