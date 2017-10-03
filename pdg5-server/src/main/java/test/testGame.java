package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import db.DBConnection;
import db.Game;

public class testGame {

	@Test
	public void testGetGames() {
		Sql2o sql2o = DBConnection.getConnection();
		 String sql =
			        "SELECT ID, title, player1, player2, created, last_activity, tournament " +
			        "FROM game";

			    try(Connection con = sql2o.open()) {
			        List<Game> games = con.createQuery(sql).executeAndFetch(Game.class);
			        for(Game g : games) {
			        	System.out.println(g.toString());
			        }
			    }
		
	}

}
