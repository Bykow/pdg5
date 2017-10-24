package pdg5.persistent;

import java.util.Date;

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
	
	public Tournament(String title) {
		super();
		setTitle(title);
		setCreated(new Date());
	}
	
	public Tournament() {
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
	
	@Override
	public String toString() {
		return ID + ", " + title + ", " + created;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Tournament))
		{
			return false;
		}
			
		Tournament b = (Tournament) obj;
		
		return this.ID == b.getID() &&
				this.title.equals(b.getTitle()) &&
				this.created.equals(b.getCreated());
	}

}
