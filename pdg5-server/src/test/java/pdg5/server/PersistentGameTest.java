package pdg5.server;

import org.junit.Test;
import pdg5.server.persistent.Game;
import pdg5.server.persistent.User;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PersistentGameTest {

	@Test
	public void testEqualsGame() {
		Date d1 = new Date();
		Date d2 = new Date();
		Date d3 = new Date(2000,01,01);
		User u1 = new User("a", "b");
		User u2 = new User("c","d");
		User u3 = new User("e","f");
		u1.setPass("p1");
		u2.setPass("p2");
		u3.setPass("p3");
		
		
		Game a = new Game(u1, u2, "a", d1, d2);
		Game b = new Game(u1, u2, "a", d1, d2);
		Game c = new Game(u3, u2, "a", d1, d2);
		Game d = new Game(u1, u3, "a", d1, d2);
		Game e = new Game(u1, u2, "b", d1, d2);
		Game f = new Game(u1, u2, "a", d3, d2);
		Game g = new Game(u1, u2, "a", d1, d3);
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
		assertFalse(a.equals(e));
		assertFalse(a.equals(f));
		assertFalse(a.equals(g));
	}
}
