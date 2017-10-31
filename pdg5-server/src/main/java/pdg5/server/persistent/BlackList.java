package pdg5.server.persistent;

import java.util.Date;

public class BlackList {
	private int ID;
	private User fromUser;
	private User toUser;
	private Date lastModified;
	
	@Deprecated
	public BlackList(int iD, User fromUser, User toUser, Date lastModified) {
		super();
		ID = iD;
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.lastModified = lastModified;
	}
	@Deprecated
	public BlackList(User fromUser, User toUser) {
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.lastModified = new Date();
	}
	
	public BlackList() {
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public User getFromUser() {
		return fromUser;
	}
	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}
	public User getToUser() {
		return toUser;
	}
	public void setToUser(User toUser) {
		this.toUser = toUser;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof BlackList))
		{
			return false;
		}
			
		BlackList b = (BlackList) obj;
		
		return this.ID == b.getID() &&
				this.fromUser.equals(b.getFromUser()) &&
				this.toUser.equals(b.getToUser()) &&
				this.lastModified.equals(b.getLastModified());
	}

	@Override
	public String toString() {
		return ID  + ", " + fromUser  + ", " + toUser  + ", " + lastModified;
	}
}
