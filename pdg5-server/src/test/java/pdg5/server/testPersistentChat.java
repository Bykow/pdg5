package pdg5.server;

import static org.junit.Assert.*;

import org.junit.Test;

import pdg5.server.persistent.Chat;

public class testPersistentChat {

	@Test
	public void testEqualsChat() {
		Chat a = new Chat(1, 1, 1);
		Chat b = new Chat(1, 1, 1);
		Chat c = new Chat(2, 1, 1);
		Chat d = new Chat(1, 2, 1);
		Chat e = new Chat(1, 1, 2);
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
		assertFalse(a.equals(e));
	}
}
