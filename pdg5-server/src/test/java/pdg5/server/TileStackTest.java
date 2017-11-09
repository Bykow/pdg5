package pdg5.server;

import org.junit.Test;
import pdg5.common.Protocol;
import pdg5.server.model.TileStack;

/**
 * Created on 10.10.17 by Bykow
 */
public class TileStackTest {
    private TileStack tileStack = new TileStack(Protocol.LANG_FR);

    @Test
    public void getNextTuile() throws Exception {
        for (int i = 0; i < tileStack.getSize(); i++) {
            System.out.println(i + " " + tileStack.getNextTuile());
        }
    }
}