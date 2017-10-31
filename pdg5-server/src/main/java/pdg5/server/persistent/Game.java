package pdg5.server.persistent;

import java.util.Date;


public class Game {
	private int ID;
	private String title;
	private User player1;
	private User player2;
	private Date created;
	private Date lastActivity;
	private int tournament;
	public Game(int iD, String title, User player1, User player2, Date created, Date lastActivity,
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
	
	public Game(String title, User player1, User player2, int tournament) {
		super();
		this.title = title;
		this.player1 = player1;
		this.player2 = player2;
		this.created = new Date();
		this.lastActivity = new Date();
		this.tournament = tournament;
	}
	
	public Game(String title, User player1, User player2) {
		super();
		this.title = title;
		this.player1 = player1;
		this.player2 = player2;
		this.created = new Date();
		this.lastActivity = new Date();
	}
	
	public Game() {
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
	public User getPlayer1() {
		return player1;
	}
	public void setPlayer1(User player1) {
		this.player1 = player1;
	}
	public User getPlayer2() {
		return player2;
	}
	public void setPlayer2(User player2) {
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
		return ID  + ", " + title  + ", " + player1  + ", " + player2  + ", " + created  + ", " + lastActivity  + ", " + tournament;
	}

}