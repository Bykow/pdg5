package pdg5.server.persistent;

public class MatchList {
	private int ID;
	private Tournament tournament;
	private User user;
	
	public MatchList(int iD, Tournament tournament, User user) {
		super();
		ID = iD;
		this.tournament = tournament;
		this.user = user;
	}
	public MatchList(Tournament tournament, User user) {
		super();
		this.tournament = tournament;
		this.user = user;
	}
	
	public MatchList() {
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Tournament getTournament() {
		return tournament;
	}
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof MatchList))
		{
			return false;
		}
			
		MatchList b = (MatchList) obj;
		
		return this.ID == b.getID() &&
				this.tournament == b.getTournament() &&
				this.user == b.getUser();
	}
	
	@Override
	public String toString() {
		return ID + ", " + tournament  + ", " + user;
	}

}
