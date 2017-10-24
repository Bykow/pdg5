package pdg5.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Random;

import org.junit.Test;

import pdg5.manage.manageGame;
import pdg5.persistent.Game;

public class testPersistentGame {

	@Test
	public void testEqualsGame() {
		Date d1 = new Date();
		Date d2 = new Date();
		
		
		Game a = new Game(0, "a", 1, 1, d1, d2, 1);
		Game b = new Game(0, "a", 1, 1, d1, d2, 1);
		Game c = new Game(1, "a", 1, 1, d1, d2, 1);
		Game d = new Game(0, "b", 1, 1, d1, d2, 1);
		Game e = new Game(0, "a", 2, 1, d1, d2, 1);
		Game f = new Game(0, "a", 1, 2, d1, d2, 1);
		Date d3 = new Date(2000,01,01);
		Game g = new Game(0, "a", 1, 1, d3, d2, 1);
		Game h = new Game(0, "a", 1, 1, d1, d3, 1);
		Game i = new Game(0, "a", 1, 1, d1, d2, 2);
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
		assertFalse(a.equals(e));
		assertFalse(a.equals(f));
		assertFalse(a.equals(g));
		assertFalse(a.equals(h));
		assertFalse(a.equals(i));
	}
}
