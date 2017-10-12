package persistent;

import java.util.Date;


public class Game {
	private int ID;
	private String title;
	private int player1;
	private int player2;
	private Date created;
	private Date lastActivity;
	private int tournament;
	public Game(int iD, String title, int player1, int player2, Date created, Date lastActivity,
			int tournament) {
		super();
		ID = iD;
		this.title = title;
		this.player1 = player1;
		this.player2 = player2;
		this.created = created;
		this.lastActivity = lastActivity;
		this.tournament = tournament;
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
	public int getPlayer1() {
		return player1;
	}
	public void setPlayer1(int player1) {
		this.player1 = player1;
	}
	public int getPlayer2() {
		return player2;
	}
	public void setPlayer2(int player2) {
		this.player2 = player2;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getLastActivity() {
		return lastActivity;
	}
	public void setLastActivity(Date lastActivity) {
		this.lastActivity = lastActivity;
	}
	public int getTournament() {
		return tournament;
	}
	public void setTournament(int tournament) {
		this.tournament = tournament;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Game))
		{
			return false;
		}
			
		Game b = (Game) obj;
		return this.ID == b.ID &&
				this.title.equals(b.title) &&
				this.player1 == b.player1 && 
				this.player2 == b.player2 && 
				this.created.equals(b.created) &&
				this.lastActivity.equals(b.lastActivity) &&
				this.tournament == b.tournament;
	}
	
	@Override
	public String toString() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

}
