package pdg5.server;

import static org.junit.Assert.*;

import org.junit.Test;

import pdg5.server.persistent.User;


public class testPersistentUser {

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
}
