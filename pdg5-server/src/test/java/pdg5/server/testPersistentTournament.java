package pdg5.server;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import pdg5.server.persistent.Tournament;

public class testPersistentTournament {

	@Test
	public void testEqualsTournament() {
		Date d1 = new Date();
		Date d2 = new Date(2000,01,01);
		
		Tournament a = new Tournament("a", d1);
		Tournament b = new Tournament("a", d1);
		Tournament d = new Tournament("b", d1);
		Tournament e = new Tournament("a", d2);
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(d));
		assertFalse(a.equals(e));
	}
}
