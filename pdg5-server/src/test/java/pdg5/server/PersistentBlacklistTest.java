package pdg5.server;

import org.junit.Test;
import pdg5.server.persistent.Blacklist;
import pdg5.server.persistent.User;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PersistentBlacklistTest {

    @Test
    public void testEqualBlacklist() {
        Date d1 = new Date();
        Date d2 = new Date(2000, 01, 01);
        User u1 = new User("a", "b");
        User u2 = new User("c", "d");
        User u3 = new User("e", "f");
        u1.setPass("p1");
        u2.setPass("p2");
        u3.setPass("p3");


        Blacklist a = new Blacklist(u1, u2, d1);
        Blacklist b = new Blacklist(u1, u2, d1);
        Blacklist c = new Blacklist(u3, u2, d1);
        Blacklist d = new Blacklist(u1, u3, d1);
        Blacklist e = new Blacklist(u1, u2, d2);

        assertTrue(a.equals(b));
        assertFalse(a.equals(c));
        assertFalse(a.equals(d));
        assertFalse(a.equals(e));
    }
}
