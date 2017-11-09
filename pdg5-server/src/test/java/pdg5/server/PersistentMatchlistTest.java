package pdg5.server;

import static org.junit.Assert.*;

import org.junit.Test;

import pdg5.server.persistent.Matchlist;
import pdg5.server.persistent.Tournament;
import pdg5.server.persistent.User;

public class PersistentMatchlistTest {

	@Test
	public void testEqualsMatchList() {
		Tournament t1 = new Tournament();
		t1.setId(1);
		Tournament t2 = new Tournament();
		t2.setId(2);
		
		User u1 = new User("a", "b");
		User u2 = new User("c","d");
		u1.setPass("p1");
		u2.setPass("p2");
		
		Matchlist a = new Matchlist(t1, u1);
		Matchlist b = new Matchlist(t1, u1);
		Matchlist c = new Matchlist(t2, u1);
		Matchlist d = new Matchlist(t1, u2);
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
	}
}
