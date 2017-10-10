package db;

import java.util.ArrayList;
import java.util.List;

import org.sql2o.Connection;

import com.mysql.jdbc.NotImplemented;

import test.testDB;
import util.utils;

/**
 * 
 * @author Tony Clavien
 * @date 03.10.2017
 */
public class User {

	private int ID;
	private String email;
	private String pass;
	private ArrayList<User> friends;
	private ArrayList<User> blacklist;
	private static final String tableName = "user";
	private static final String[] columns = {"ID", "email", "pass"};

	
	public User(int id) {
		ID = id;
		email = null;
		friends = new ArrayList<>();
		blacklist = new ArrayList<>();
	}
	
	public User(int id, String email) {
		ID = id;
		this.email = email;
		friends = new ArrayList<>();
		blacklist = new ArrayList<>();
	}
	
	public User(int id, String email, ArrayList<User> friends, ArrayList<User> blacklist) {
		ID = id;
		this.email = email;
		/* careful the content is not copied */
		this.friends = friends;
		this.blacklist = blacklist;
	}
	
	// database stuff
	// TODO miss friends, blacklist, and chat
	
	public static List<User> getAllUser() {
		String sql =
		        "SELECT " + utils.toSqlSelect(columns) + " " +
		        "FROM " + tableName;

		    try (Connection con = DBConnection.getConnection().open()) {
		        List<User> users = con.createQuery(sql).executeAndFetch(User.class);
		        con.close();
		        return users;
		    }
		    
	}

	public static User getUser(int id) {
		String sql = 
				"SELECT " + utils.toSqlSelect(columns) + " " +
				"FROM " + tableName + " " +
				"WHERE ID = :idparam";
		try (Connection con = DBConnection.getConnection().open()) {
	        User user = con.createQuery(sql).addParameter("idparam", id).executeAndFetchFirst(User.class);
	        con.close();
	        return user;
	    }
	}
	
	public static User getUser(String email) {
		String sql = 
				"SELECT " + utils.toSqlSelect(columns) + " " +
				"FROM " + tableName+ " " +
				"WHERE email = :emailparam";
		try (Connection con = DBConnection.getConnection().open()) {
	        User user = con.createQuery(sql).addParameter("emailparam", email).executeAndFetchFirst(User.class);
	        con.close();
	        return user;
	    }
	}
	
	public boolean deleteUser() {
		// .executeUpdate();
		return deleteUser(ID);
		
	}
	
	public static boolean deleteUser(int id) {
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
	
	public static boolean deleteUser(String email) {
		String sql = 
				"DELETE FROM " + tableName+ "  WHERE email = :emailparam";
		try (Connection con = DBConnection.getConnection().open()) {
	        con.createQuery(sql).addParameter("emailparam", email).executeUpdate();
	        con.close();
	        return true;
	    } catch (Exception e) {
			// TODO: handle exception
	    	return false;
		}
	}
	
	public boolean commitChange() {
		String sql = 
				"UPDATE " + tableName + " " +
				"SET email = :emailparam AND " +
				"pass = :passparam " +
				"WHERE ID = :idparam";
		try (Connection con = DBConnection.getConnection().open()) {
	        con.createQuery(sql).addParameter("emailparam", this.email)
	        .addParameter("passparam", this.pass)
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
	
	public String getPassword() {
		return pass;
	}

	public void setPassword(String password) {
		this.pass = password;
	}

	public ArrayList<User> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}

	public ArrayList<User> getBlacklist() {
		return blacklist;
	}

	public void setBlacklist(ArrayList<User> blacklist) {
		this.blacklist = blacklist;
	}

	public int getID() {
		return ID;
	}
	
	public void setID(int id) {
		this.ID = id;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String new_email) {
		this.email = new_email;
	}

	public static User newUser() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	// user stuff
	
	public void addFriend(User friend) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void removeFriend(User friend) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void addBlackList(User bl) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void removeBlackList(User bl) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	// override stuff
	@Override
	public boolean equals(Object obj) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ID + ", " + email;
	}

	
	
}
