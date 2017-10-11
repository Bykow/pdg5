package persistent;

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

}
