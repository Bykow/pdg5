package persistent;

import org.joda.time.DateTime;

public class Game {
	private int ID;
	private String title;
	private int player1;
	private int player2;
	private DateTime created;
	private DateTime lastActivity;
	private int tournament;
	public Game(int iD, String title, int player1, int player2, DateTime created, DateTime lastActivity,
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
	public DateTime getCreated() {
		return created;
	}
	public void setCreated(DateTime created) {
		this.created = created;
	}
	public DateTime getLastActivity() {
		return lastActivity;
	}
	public void setLastActivity(DateTime lastActivity) {
		this.lastActivity = lastActivity;
	}
	public int getTournament() {
		return tournament;
	}
	public void setTournament(int tournament) {
		this.tournament = tournament;
	}

}
