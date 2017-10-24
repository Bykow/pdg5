package pdg5.persistent;

public class MatchList {
	private int ID;
	private int tournament;
	private int user;
	public MatchList(int iD, int tournament, int user) {
		super();
		ID = iD;
		this.tournament = tournament;
		this.user = user;
	}
	public MatchList(int tournament, int user) {
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
	public int getTournament() {
		return tournament;
	}
	public void setTournament(int tournament) {
		this.tournament = tournament;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
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
