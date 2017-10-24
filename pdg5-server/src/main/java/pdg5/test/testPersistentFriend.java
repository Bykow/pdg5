package pdg5.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Random;

import org.junit.Test;

import pdg5.manage.manageFriend;
import pdg5.persistent.Friend;

public class testPersistentFriend {

	@Test
	public void testEqualFriend() {
		Date d1 = new Date();
		
		
		Friend a = new Friend(1, 1, 1, d1);
		Friend b = new Friend(1, 1, 1, d1);
		Friend c = new Friend(2, 1, 1, d1);
		Friend d = new Friend(1, 2, 1, d1);
		Friend e = new Friend(1, 1, 2, d1);
		Date d2 = new Date(2000,01,01);
		Friend f = new Friend(1, 1, 1, d2);
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
		assertFalse(a.equals(e));
		assertFalse(a.equals(f));
	}
}
