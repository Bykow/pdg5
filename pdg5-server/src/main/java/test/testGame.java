package test;

import static org.junit.Assert.*;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import db.DBConnection;
import db.Game;
import db.User;

public class testGame {
	
	@Test
	public void testGetGames() {
		System.out.println("test get Games");
		 String sql =
			        "SELECT ID, title, player1, player2, created, last_activity, tournament " +
			        "FROM game";

			    try {
			        List<Game> games = testDB.con.createQuery(sql).executeAndFetch(Game.class);
			        for(Game g : games) {
			        	System.out.println(g.toString());
			        }
			    }
			    catch (Exception e) {
					// TODO: handle exception
			    	e.printStackTrace();
				}
	}
	
	@Test
	public void testEqualsGame() {
		DateTime now = DateTime.now();
		Game a = new Game(1, "a", 10, 20, now, now.plusHours(1), 2);
		Game b = new Game(1, "a", 10, 20, now, now.plusHours(1), 2);
		Game c = new Game(2, "a", 10, 20, now, now.plusHours(1), 2);
		Game d = new Game(1, "b", 10, 20, now, now.plusHours(1), 2);
		Game e = new Game(1, "a", 10, 20, DateTime.now(), now.plusHours(1), 2);
		Game f = new Game(1, "a", 10, 20, now, now.plusHours(2), 2);
		Game g = new Game(1, "a", 10, 20, now, now.plusHours(1), 3);
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
		assertFalse(a.equals(e));
		assertFalse(a.equals(f));
		assertFalse(a.equals(g));
	}
	
	@Test
	public void testBasicGame() {
		//TODO
		assertTrue(false);
	}

}
