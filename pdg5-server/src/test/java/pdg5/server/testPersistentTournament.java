package pdg5.server;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import pdg5.server.persistent.Tournament;

public class testPersistentTournament {

	@Test
	public void testEqualsTournament() {
		Date d1 = new Date();
		
		
		Tournament a = new Tournament(1, "a", d1);
		Tournament b = new Tournament(1, "a", d1);
		Tournament c = new Tournament(2, "a", d1);
		Tournament d = new Tournament(1, "b", d1);
		Date d2 = new Date(2000,01,01);
		Tournament e = new Tournament(1, "a", d2);
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
		assertFalse(a.equals(e));
	}
}
