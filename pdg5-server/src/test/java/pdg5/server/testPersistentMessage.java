package pdg5.server;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import pdg5.server.persistent.Message;

public class testPersistentMessage {

	@Test
	public void testEqualsMessage() {
		Date d1 = new Date();
		Date d2 = new Date(2000,01,01);
		
		
		Message a = new Message(1, "a", d1, 1, 1);
		Message b = new Message(1, "a", d1, 1, 1);
		Message c = new Message(2, "a", d1, 1, 1);
		Message d = new Message(1, "b", d1, 1, 1);
		Message e = new Message(1, "a", d2, 1, 1);
		Message f = new Message(1, "a", d1, 2, 1);
		Message g = new Message(1, "a", d1, 2, 2);
		
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
		assertFalse(a.equals(e));
		assertFalse(a.equals(f));
		assertFalse(a.equals(g));
	}
}
