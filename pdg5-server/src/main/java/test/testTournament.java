package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import db.DBConnection;
import db.Tournament;

public class testTournament {

	@Test
	public void testGetTournament() {
		System.out.println("test get tournament");
		/*Sql2o sql2o = DBConnection.getConnection();
		 String sql =
			        "SELECT ID, title, created " +
			        "FROM tournament";

			    try(Connection con = sql2o.open()) {
			        List<Tournament> tournaments = con.createQuery(sql).executeAndFetch(Tournament.class);
			        for(Tournament t : tournaments) {
			        	System.out.println(t.toString());
			        }
			    }*/
		
	}

}
