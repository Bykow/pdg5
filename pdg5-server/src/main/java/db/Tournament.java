package db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.sql2o.Connection;

import util.utils;

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
	private static final String tableName = "tournament";
	private static final String[] columns = {"ID", "title", "created"};
	
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
	//TODO miss players and games
	
	public static List<Tournament> getAllTournament() {
		String sql =
		        "SELECT " + utils.toSqlSelect(columns) +  " " +
		        "FROM " + tableName;

		    try(Connection con = DBConnection.getConnection().open()) {
		        List<Tournament> tournaments = con.createQuery(sql).executeAndFetch(Tournament.class);
		        con.close();
		        return tournaments;
		    }
	}
	
	public static Tournament getTournament(int id) {
		String sql =
		        "SELECT " + utils.toSqlSelect(columns) +  " " +
		        "FROM " + tableName + " " +
		        "WHERE ID = :idparam";

		    try(Connection con = DBConnection.getConnection().open()) {
		        Tournament tournament = con.createQuery(sql).addParameter("idparam", id).executeAndFetchFirst(Tournament.class);
		        con.close();
		        return tournament;
		    }
	}
	
	//TODO put the delete in another method
	public static boolean deleteTournament(int id) {
		String sql = 
				"DELETE FROM " + tableName+ "  WHERE ID = :idparam";
		try (Connection con = DBConnection.getConnection().open()) {
	        con.createQuery(sql).addParameter("idparam", id).executeUpdate();
	        con.close();
	        return true;
	    } catch (Exception e) {
			// TODO: handle exception
	    	return false;
		}
	}
	
	public boolean deleteTournament() {
		return deleteTournament(ID);
	}
	
	public boolean commitChange() {
		String sql = 
				"UPDATE " + tableName + " " +
				"SET title = :titleparam AND " +
				"created = :createdparam " +
				"WHERE ID = :idparam";
		try (Connection con = DBConnection.getConnection().open()) {
	        con.createQuery(sql).addParameter("titleparam", this.title)
	        .addParameter("createdparam", this.created)
	        .addParameter("idparam", this.ID)
	        .executeUpdate();
	        con.close();
	        return true;
	    } catch (Exception e) {
			// TODO: handle exception
	    	return false;
		}
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
