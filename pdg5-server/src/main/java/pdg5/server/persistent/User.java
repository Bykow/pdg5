package pdg5.server.persistent;


public class User {
	private int ID;
	private String email;
	private String username;
	private String pass;
	
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
