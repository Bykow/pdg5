package db;

import java.util.ArrayList;

import com.mysql.jdbc.NotImplemented;

/**
 * 
 * @author Tony Clavien
 * @date 03.10.2017
 */
public class User {

	private final int ID;
	private String email;
	private ArrayList<User> friends;
	private ArrayList<User> blacklist;

	
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
	
	public static User[] getAllUser() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public static User getUser(int id) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public static User getUser(String email) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public boolean deleteUser() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public static boolean deleteUser(int id) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public static boolean deleteUser(String email) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public boolean commitChange() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	// user stuff
	
	public void setEmail(String new_email) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
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

	
	
}
