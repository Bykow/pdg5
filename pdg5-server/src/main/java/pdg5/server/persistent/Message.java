package pdg5.server.persistent;

import java.util.Date;

public class Message {
	
	private int ID;
	private String content;
	private Date created;
	private int user;
	private int chat;
	
	public Message(int id, String content, Date created, int user, int chat) {
		super();
		ID = id;
		this.content = content;
		this.created = created;
		this.user = user;
		this.chat = chat;
	}
	
	public Message(String content, Date created, int user, int chat) {
		super();
		this.content = content;
		this.created = created;
		this.user = user;
		this.chat = chat;
	}
	
	public Message() {
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getChat() {
		return chat;
	}

	public void setChat(int chat) {
		this.chat = chat;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Message))
		{
			return false;
		}
			
		Message b = (Message) obj;
		
		return this.ID == b.getID() &&
				this.chat == b.getChat() &&
				this.user == b.getUser() &&
				this.content.equals(b.getContent()) &&
				this.created.equals(b.getCreated());
	}
	
	

}
