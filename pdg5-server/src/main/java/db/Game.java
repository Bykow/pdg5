package db;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
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
	private DateTime created;
	private DateTime last_activity;
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
	
	public Game(int id, String title, int player1, int player2, DateTime created, DateTime last_activity, int tournament) {
		this.ID = id;
		this.title = title;
		this.player1 = player1;
		this.player2 = player2;
		this.created = created;
		this.last_activity = last_activity;
		this.tournament = tournament;
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
				"player1 = :p1param AND " +
				"player2 = :p2param AND " +
				"created = :createdparam AND " +
				"last_activity = :lastactparam AND " +
				"tournament = :tournamentparam " +
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
	
	public static Game newGame(String title, int player1, int player2, int tournament) {
		String sql = 
				"INSERT INTO " + tableName + " (title, player1, player2, created, last_activity, tournament) " +
			    "VALUES (:titleparam,:p1param,:p2param, :createdparam, :lastactparam, :tournamentparam)";
		try (Connection con = DBConnection.getConnection().open()) {
			DateTime now = DateTime.now();
	        int id = (int) con.createQuery(sql).addParameter("titleparam", title)
	    	        .addParameter("p1param", player1)
	    	        .addParameter("p2param", player2)
	    	        .addParameter("createdparam", now)
	    	        .addParameter("lastactparam", now)
	    	        .addParameter("tournamentparam", tournament)
	        .executeUpdate()
	        .getKey();
	        con.close();
	        return new Game(id, title, player1, player2, now,now, tournament);
	    } catch (Exception e) {
			// TODO: handle exception
	    	return null;
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

	public DateTime getCreated() {
		return created;
	}

	public void setCreated(DateTime created) {
		this.created = created;
	}

	public DateTime getLast_activity() {
		return last_activity;
	}

	public void setLast_activity(DateTime last_activity) {
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
