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
	private User[] players;
	private Date creationTime;
	
	
	public Game(int id) {
		this.ID = id;
		this.title = null;
		this.players = null;
		this.creationTime = null;
	}
	
	public Game(int id, String title) {
		this.ID = id;
		this.title = title;
		this.players = null;
		this.creationTime = null;
	}
	
	public Game(int id, String title, User[] players) {
		this.ID = id;
		this.title = title;
		if(players.length != 2) {
			throw new IllegalArgumentException("invalid number of players"); 
		}
		this.players = players;
		this.creationTime = null;
	}
	
	public Game(int id, String title, User[] players, Date creationTime) {
		this.ID = id;
		this.title = title;
		if(players.length != 2) {
			throw new IllegalArgumentException("invalid number of players"); 
		}
		this.players = players;
		this.creationTime = creationTime;
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
	
	public static Game newGame() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	// Game stuff
	public void setTitle(String title) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void setCreationTime(Date creationTime) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void setPlayer(User[] players) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	// override stuff
	@Override
	public boolean equals(Object obj) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	
	
}
