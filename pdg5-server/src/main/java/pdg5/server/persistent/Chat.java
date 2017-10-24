package pdg5.server.persistent;

public class Chat {
	
	private int ID;
	private int tournament;
	private int game;
	
	public Chat(int id, int tournament, int game) {
		super();
		ID = id;
		this.tournament = tournament;
		this.game = game;
	}
	
	public Chat(int tournament, int game) {
		super();
		this.tournament = tournament;
		this.game = game;
	}
	
	public Chat() {
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

	public int getGame() {
		return game;
	}

	public void setGame(int game) {
		this.game = game;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Chat))
		{
			return false;
		}
			
		Chat b = (Chat) obj;
		
		return this.ID == b.getID() &&
				this.tournament == b.getTournament() &&
				this.game == b.getGame();
	}
	
	@Override
	public String toString() {
		return this.ID + ", " + this.tournament + ", " + this.game;
	}
	
	

}
