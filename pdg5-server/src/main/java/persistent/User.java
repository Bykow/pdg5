package persistent;


public class User {
	private int ID;
	private String email;
	private String pass;
	
	public User(int iD, String email, String pass) {
		super();
		ID = iD;
		this.email = email;
		this.pass = pass;
	}

	public User(String email, String pass) {
		this.email = email;
		this.pass = pass;
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
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof User))
		{
			return false;
		}
			
		User b = (User) obj;
		return this.ID == b.ID &&
				this.email.equals(b.email) && 
				this.pass.equals(b.pass);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ID + ", " + email + ", " + pass;
	}
}
