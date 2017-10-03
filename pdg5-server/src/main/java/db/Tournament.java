package db;

import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author Tony Clavien
 * @date 03.10.2017
 */
public class Tournament {
	
	private int ID;
	private String title;
	private Date created;
	private ArrayList<User> players;
	private ArrayList<Game> games;
	
	public Tournament(int id) {
		this.ID = id;
		title = null;
		created = null;
		players = new ArrayList<>();
		games = new ArrayList<>();
	}
	
	public Tournament(int id, String title, Date creationTime) {
		this.ID = id;
		this.title = title;
		this.created = creationTime;
		players = new ArrayList<>();
		games = new ArrayList<>();
	}
	
	public Tournament(int id, String title, Date creationTime, ArrayList<User> players) {
		this.ID = id;
		this.title = title;
		this.created = creationTime;
		this.players = players;
		games = new ArrayList<>();
	}
	
	public Tournament(int id, String title, Date creationTime, ArrayList<User> players, ArrayList<Game> games) {
		this.ID = id;
		this.title = title;
		this.created = creationTime;
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
	
	// getter and setter
	
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public ArrayList<User> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<User> players) {
		this.players = players;
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}

	public static Tournament newTournament() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	
	
	// tournament stuff
	
	
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
	
	@Override
	public String toString() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
}
