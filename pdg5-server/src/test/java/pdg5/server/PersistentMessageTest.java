package pdg5.server;

import org.junit.Test;
import pdg5.server.persistent.Chat;
import pdg5.server.persistent.Message;
import pdg5.server.persistent.User;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PersistentMessageTest {

	@Test
	public void testEqualsMessage() {
		Date d1 = new Date();
		Date d2 = new Date(2000,01,01);
		
		User u1 = new User("a", "b");
		User u2 = new User("c","d");
		u1.setPass("p1");
		u2.setPass("p2");
		
		Chat c1 = new Chat();
		c1.setId(1);
		Chat c2 = new Chat();
		c2.setId(2);
		
		
		Message a = new Message(c1, u1, "a", d1);
		Message b = new Message(c1, u1, "a", d1);
		Message c = new Message(c2, u1, "a", d1);
		Message d = new Message(c1, u2, "a", d1);
		Message e = new Message(c1, u1, "b", d1);
		Message f = new Message(c1, u1, "a", d2);
		
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
		assertFalse(a.equals(e));
		assertFalse(a.equals(f));
	}
}
