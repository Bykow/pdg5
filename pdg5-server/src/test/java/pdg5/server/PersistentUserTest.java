package pdg5.server;

import org.junit.Test;
import pdg5.server.persistent.User;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class PersistentUserTest {

	@Test
	public void testEqualsUser() {
		
		
		User a = new User("a", "b");
		User b = new User("a", "b");
		User c = new User("c", "b");
		User d = new User("a", "c");
		a.setPass("p1");
		b.setPass("p1");
		c.setPass("p2");
		d.setPass("p3");
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
	}
}
