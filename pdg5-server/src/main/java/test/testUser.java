package test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import javax.rmi.ssl.SslRMIClientSocketFactory;

import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import db.DBConnection;
import db.User;
import util.utils;

public class testUser {
		
	@Test
	public void testGetUsers() {
		System.out.println("test get Users");
		 String sql =
			        "SELECT ID, email, pass " +
			        "FROM user";

			    try {
			        List<User> users = testDB.con.createQuery(sql).executeAndFetch(User.class);
			        for(User u : users) {
			        	System.out.println(u.toString());
			        }
			    }
			    catch (Exception e) {
					// TODO: handle exception
			    	e.printStackTrace();
				}
		
	}

}
