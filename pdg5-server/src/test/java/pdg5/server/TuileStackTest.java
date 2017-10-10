package pdg5.server;

import org.junit.Test;
import pdg5.common.Protocol;
import pdg5.server.model.TuileStack;

/**
 * Created on 10.10.17 by Bykow
 */
public class TuileStackTest {
    private TuileStack tuileStack = new TuileStack(Protocol.LANG_FR);

    @Test
    public void getNextTuile() throws Exception {
        for (int i = 0; i < tuileStack.getSize(); i++) {
            System.out.println(i + " " + tuileStack.getNextTuile());
        }
    }
}