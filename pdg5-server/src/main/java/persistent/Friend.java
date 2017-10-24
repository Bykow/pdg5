package persistent;

import java.util.Date;

public class Friend {
	private int ID;
	private int fromUser;
	private int toUser;
	private Date lastModified;
	public Friend(int iD, int fromUser, int toUser, Date lastModified) {
		super();
		ID = iD;
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.lastModified = lastModified;
	}
	
	public Friend(int fromUser, int toUser) {
		super();
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.lastModified = new Date();
	}
	
	public Friend() {
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
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Friend))
		{
			return false;
		}
			
		Friend b = (Friend) obj;
		
		return this.ID == b.getID() &&
				this.fromUser == b.getFromUser() &&
				this.toUser == b.getToUser() &&
				this.lastModified.equals(b.getLastModified());
	}
	
	@Override
	public String toString() {
		return ID  + ", " + fromUser  + ", " + toUser  + ", " + lastModified;
	}

}