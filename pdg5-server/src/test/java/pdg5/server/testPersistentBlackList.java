package pdg5.server;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import pdg5.server.persistent.BlackList;
import pdg5.server.persistent.User;

public class testPersistentBlackList {

	@Test
	public void testEqualBlackList() {
		Date d1 = new Date();
		Date d2 = new Date(2000,01,01);
		User u1 = new User("a", "b");
		User u2 = new User("c","d");
		User u3 = new User("e","f");
		
		
		BlackList a = new BlackList(u1, u2, d1);
		BlackList b = new BlackList(u1, u2, d1);
		BlackList c = new BlackList(u3, u2, d1);
		BlackList d = new BlackList(u1, u3, d1);
		BlackList e = new BlackList(u1, u2, d2);
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
		assertFalse(a.equals(e));
	}
}
