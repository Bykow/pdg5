package db;

import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author Tony Clavien
 * @date 03.10.2017
 */
public class Tournament {
	
	private final int ID;
	private String title;
	private Date creationTime;
	private ArrayList<User> players;
	private ArrayList<Game> games;
	
	public Tournament(int id) {
		this.ID = id;
		title = null;
		creationTime = null;
		players = new ArrayList<>();
		games = new ArrayList<>();
	}
	
	public Tournament(int id, String title, Date creationTime) {
		this.ID = id;
		this.title = title;
		this.creationTime = creationTime;
		players = new ArrayList<>();
		games = new ArrayList<>();
	}
	
	public Tournament(int id, String title, Date creationTime, ArrayList<User> players) {
		this.ID = id;
		this.title = title;
		this.creationTime = creationTime;
		this.players = players;
		games = new ArrayList<>();
	}
	
	public Tournament(int id, String title, Date creationTime, ArrayList<User> players, ArrayList<Game> games) {
		this.ID = id;
		this.title = title;
		this.creationTime = creationTime;
		this.players = players;
		this.games = games;
	}
	
	// database stuff
	
	public static Tournament[] getAllTournament() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public static Tournament getTournament(int id) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public static boolean deleteTournament(int id) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public boolean deleteTournament() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public boolean commitChange() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public static Tournament newTournament() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	// tournament stuff
	
	public void setTitle(String title) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void setCreationTime(Date creationTime) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void addPlayer(User player) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void removePlayer(User player) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void addGame(Game game) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	public void removeGame(Game game) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	// override stuff
	@Override
	public boolean equals(Object obj) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
}
