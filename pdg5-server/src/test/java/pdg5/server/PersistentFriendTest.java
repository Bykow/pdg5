package pdg5.server;

import org.junit.Test;
import pdg5.server.persistent.Friend;
import pdg5.server.persistent.User;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PersistentFriendTest {

	@Test
	public void testEqualFriend() {
		Date d1 = new Date();
		Date d2 = new Date(2000,01,01);
		User u1 = new User("a", "b");
		User u2 = new User("c","d");
		User u3 = new User("e","f");
		u1.setPass("p1");
		u2.setPass("p2");
		u3.setPass("p3");
		
		Friend a = new Friend(u1, u2, d1);
		Friend b = new Friend(u1, u2, d1);
		Friend c = new Friend(u3, u2, d1);
		Friend d = new Friend(u1, u3, d1);
		Friend e = new Friend(u1, u2, d2);
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
		assertFalse(a.equals(e));
	}
}
