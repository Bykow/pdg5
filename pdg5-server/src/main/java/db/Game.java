package db;

import java.util.Date;

/**
 * 
 * @author Tony Clavien
 * @date 03.10.2017
 */
public class Game {

	private final int ID;
	private String title;
	private int player1;
	private int player2;
	private Date created;
	private Date last_activity;
	private int tournament;
	
	
	public Game(int id) {
		this.ID = id;
		this.title = null;
		this.created = null;
	}
	
	public Game(int id, String title) {
		this.ID = id;
		this.title = title;
		this.created = null;
	}
	
	public Game(int id, String title, int player1, int player2) {
		this.ID = id;
		this.title = title;
		this.player1 = player1;
		this.player2 = player2;
		this.created = null;
	}
	
	
	// database stuff
	public static Game[] getAllGame() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public static Game getGame(int id) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public static boolean deleteGame(int id) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public boolean deleteGame() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public boolean commitChange() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	// getter and setter
	
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

	public Date getLast_activity() {
		return last_activity;
	}

	public void setLast_activity(Date last_activity) {
		this.last_activity = last_activity;
	}

	public int getTournament() {
		return tournament;
	}

	public void setTournament(int tournament) {
		this.tournament = tournament;
	}

	public int getID() {
		return ID;
	}
	
	// Game stuff
	public static Game newGame() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	// override stuff
	@Override
	public boolean equals(Object obj) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	@Override
	public String toString() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	
	
}
