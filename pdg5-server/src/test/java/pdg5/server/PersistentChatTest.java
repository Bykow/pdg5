package pdg5.server;

import org.junit.Test;
import pdg5.server.persistent.Chat;
import pdg5.server.persistent.Game;
import pdg5.server.persistent.Message;
import pdg5.server.persistent.Tournament;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PersistentChatTest {

	@Test
	public void testEqualsChat() {
		
		Game g1 = new Game();
		g1.setId(1);
		Game g2 = new Game();
		g2.setId(2);
		
		Tournament t1 = new Tournament();
		t1.setId(1);
		Tournament t2 = new Tournament();
		t2.setId(2);
		
		Message m1 = new Message();
		Message m2 = new Message();
		m1.setId(1);
		m2.setId(2);
	
		Set ms1 = new HashSet(0);
		ms1.add(m1);
		Set ms2 = new HashSet(0);
		ms2.add(m2);
		
		Chat a = new Chat(g1, t1, ms1);
		Chat b = new Chat(g1, t1, ms1);
		Chat c = new Chat(g2, t1, ms1);
		Chat d = new Chat(g1, t2, ms1);
		Chat e = new Chat(g1, t1, ms2);
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
		assertFalse(a.equals(e));
	}
}
