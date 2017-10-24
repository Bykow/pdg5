package pdg5.server;

import static org.junit.Assert.*;

import org.junit.Test;

import pdg5.server.persistent.User;


public class testPersistentUser {

	@Test
	public void testEqualsUser() {
		User a = new User(1, "a", "a", "a");
		User b = new User(1, "a", "a", "a");
		User c = new User(1, "c", "a", "a");
		User d = new User(1, "a", "a", "c");
		User e = new User(2, "a", "a", "a");
		User f = new User(2, "a", "c", "a");
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
		assertFalse(a.equals(e));
		assertFalse(a.equals(f));
	}
}
