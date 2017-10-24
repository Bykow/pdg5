package pdg5.server;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import pdg5.server.persistent.BlackList;

public class testPersistentBlackList {

	@Test
	public void testEqualBlackList() {
		Date d1 = new Date();
		
		
		BlackList a = new BlackList(1, 1, 1, d1);
		BlackList b = new BlackList(1, 1, 1, d1);
		BlackList c = new BlackList(2, 1, 1, d1);
		BlackList d = new BlackList(1, 2, 1, d1);
		BlackList e = new BlackList(1, 1, 2, d1);
		Date d2 = new Date(2000,01,01);
		BlackList f = new BlackList(1, 1, 1, d2);
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
		assertFalse(a.equals(e));
		assertFalse(a.equals(f));
	}
}
