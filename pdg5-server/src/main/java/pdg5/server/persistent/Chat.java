package pdg5.server.persistent;

public class Chat {
	
	private int ID;
	private Tournament tournament;
	private Game game;
	
	public Chat(int id, Tournament tournament, Game game) {
		super();
		ID = id;
		this.tournament = tournament;
		this.game = game;
	}
	
	public Chat(Tournament tournament, Game game) {
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

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
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
				this.tournament.equals(b.getTournament()) &&
				this.game.equals(b.getGame());
	}
	
	@Override
	public String toString() {
		return this.ID + ", " + this.tournament + ", " + this.game;
	}
	
	

}
