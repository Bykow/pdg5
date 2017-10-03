package test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import db.DBConnection;
import db.User;

public class testUser {
	
	@Test
	public void testGetUsers() {
		Sql2o sql2o = DBConnection.getConnection();
		 String sql =
			        "SELECT ID, email, pass " +
			        "FROM user";

			    try(Connection con = sql2o.open()) {
			        List<User> users = con.createQuery(sql).executeAndFetch(User.class);
			        for(User u : users) {
			        	System.out.println(u.toString());
			        }
			    }
		
	}

}
