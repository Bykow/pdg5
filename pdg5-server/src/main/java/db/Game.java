package db;

import java.util.Date;
import java.util.List;

import org.sql2o.Connection;

import util.utils;

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
	private static final String tableName = "game";
	private static final String[] columns = {"ID", "title", "player1", "player2", "created", "last_activity", "tournament"};
	
	
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
	public static List<Game> getAllGame() {
		String sql =
		        "SELECT " + utils.toSqlSelect(columns) +  " " +
		        "FROM " + tableName;

		    try(Connection con = DBConnection.getConnection().open()) {
		        List<Game> games = con.createQuery(sql).executeAndFetch(Game.class);
		        con.close();
		        return games;
		    }
	}
	
	public static Game getGame(int id) {
		String sql =
		        "SELECT " + utils.toSqlSelect(columns) +  " " +
		        "FROM " + tableName + " " +
		        "WHERE ID = :idparam";

		    try(Connection con = DBConnection.getConnection().open()) {
		        Game game = con.createQuery(sql).addParameter("idparam", id).executeAndFetchFirst(Game.class);
		        con.close();
		        return game;
		    }
	}
	
	public static boolean deleteGame(int id) {
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
	
	public boolean deleteGame() {
		return deleteGame(ID);
	}
	
	public boolean commitChange() {
		// ID, title, player1, player2, created, last_activity, tournament
		String sql = 
				"UPDATE " + tableName + " " +
				"SET title = :titleparam AND " +
				"title = :p1param AND " +
				"title = :p2param AND " +
				"title = :createdparam AND " +
				"title = :lastactparam AND " +
				"created = :tournamentparam " +
				"WHERE ID = :idparam";
		try (Connection con = DBConnection.getConnection().open()) {
	        con.createQuery(sql).addParameter("titleparam", this.title)
	        .addParameter("p1param", this.player1)
	        .addParameter("p2param", this.player2)
	        .addParameter("createdparam", this.created)
	        .addParameter("lastactparam", this.last_activity)
	        .addParameter("tournamentparam", this.tournament)
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
