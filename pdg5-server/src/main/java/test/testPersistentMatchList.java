package test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import manage.manageMatchList;
import persistent.MatchList;

public class testPersistentMatchList {

	@Test
	public void testEqualsMatchList() {
		MatchList a = new MatchList(1, 2, 3);
		MatchList b = new MatchList(1, 2, 3);
		MatchList c = new MatchList(4, 2, 3);
		MatchList d = new MatchList(1, 5, 3);
		MatchList e = new MatchList(1, 2, 6);
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
		assertFalse(a.equals(e));
	}
}