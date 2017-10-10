package test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import db.User;

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
	
	@Test
	public void testEqualsUser() {
		User a = new User(1, "a", "a");
		User b = new User(1, "a", "a");
		User c = new User(1, "c", "a");
		User d = new User(1, "a", "c");
		User e = new User(2, "a", "a");
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
		assertFalse(a.equals(e));
	}
	
	@Test
	public void testBasicUser() {
		Random r = new Random();
		String randomMail = r.nextInt(1000) + "@test.com";
		String randomPass = String.valueOf(r.nextInt(1000));
		System.out.println("Random user with value " + randomMail + "," + randomPass);
		// add User
		User tstUser = User.createUser(randomMail, randomPass );
		System.out.println("created user : " + tstUser);
		assertEquals(randomMail, tstUser.getEmail());
		assertEquals(randomPass, tstUser.getPassword());
		
		// get User from database
		String sql = 
				 "SELECT ID, email, pass " +
				 "FROM user " + 
				 "WHERE email = :emailparam";
	    User user = testDB.con.createQuery(sql).addParameter("emailparam", randomMail).executeAndFetchFirst(User.class);
	    System.out.println("Sql user : " + user);
	    assertTrue(tstUser.equals(user));
		
		// modify User
	    String newRandomMail = r.nextInt(1000) + "@test.com";
	    String newRandomPass = String.valueOf(r.nextInt(1000));
	    tstUser.setEmail(newRandomMail);
	    tstUser.setPassword(newRandomPass);
	    tstUser.commitChange();
	    System.out.println("Modified user : " + tstUser );
	    assertEquals(newRandomMail, tstUser.getEmail());
		assertEquals(newRandomPass, tstUser.getPassword());
		
		// get User from database
		String sql2 = 
				 "SELECT ID, email, pass " +
				 "FROM user " + 
				 "WHERE ID = :idparam";
	    User user2 = testDB.con.createQuery(sql2).addParameter("idparam", user.getID()).executeAndFetchFirst(User.class);
	    System.out.println("Sql user : " + user2);
	    assertTrue(tstUser.equals(user2));
	    
		// get User
	    User getUser = User.getUser(user.getID());
	    assertTrue(tstUser.equals(getUser));
	    
		// delete User
	    tstUser.deleteUser();
	    System.out.println("deleted user : " + tstUser);
	    String sql3 = 
				 "SELECT ID, email, pass " +
				 "FROM user " + 
				 "WHERE ID = :idparam";
	    User user3 = testDB.con.createQuery(sql3).addParameter("idparam", user.getID()).executeAndFetchFirst(User.class);
	    System.out.println("Sql user : " + user3);
	    assertNull(user3);
	}
	
	//TODO need to test multiple user creation
	//TODO advanced user function ( friends, blacklist )

}
