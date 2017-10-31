package pdg5.server.persistent;

import java.util.Set;

public class User {
	private int ID;
	private String email;
	private String username;
	private String pass;
	private Set<BlackList> blacklists;
	private Set<Friend> friends;
	private Set<MatchList> matchlists;
	
	
	public User(int iD, String email,String username, String pass) {
		super();
		ID = iD;
		this.email = email;
		this.setUsername(username);
		this.pass = pass;
	}

	public User(String email, String username, String pass) {
		this.email = email;
		this.setUsername(username);
		this.pass = pass;
	}
	
	public User() {
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<BlackList> getBlacklists() {
		return blacklists;
	}

	public void setBlacklists(Set<BlackList> blacklists) {
		this.blacklists = blacklists;
	}

	public Set<Friend> getFriends() {
		return friends;
	}

	public void setFriends(Set<Friend> friends) {
		this.friends = friends;
	}

	public Set<MatchList> getMatchlists() {
		return matchlists;
	}

	public void setMatchlists(Set<MatchList> matchlists) {
		this.matchlists = matchlists;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof User))
		{
			return false;
		}
			
		User b = (User) obj;
		return this.ID == b.ID &&
				this.email.equals(b.email) && 
				this.username.equals(b.username) &&
				this.pass.equals(b.pass);
	}
	
	@Override
	public String toString() {
		return ID + ", " + email + ", "+ username + ", " + pass;
	}
}
