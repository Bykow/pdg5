package pdg5.server;

import static org.junit.Assert.*;

import org.junit.Test;

import pdg5.server.persistent.User;


public class testPersistentUser {

	@Test
	public void testEqualsUser() {
		
		
		User a = new User("a", "b");
		User b = new User("a", "b");
		User c = new User("c", "b");
		User d = new User("a", "c");
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
	}
}
