package pdg5.common;

/**
 * Created on 03.10.17 by Bykow
 */
public interface Protocol {

    /**
     * Socket
     */
    final String DEFAULT_SERVER = "127.0.0.1"; // modify for tests
    final int DEFAULT_PORT = 44555;

    final int NUMBER_OF_TUILES_PER_PLAYER = 7;
    final int NUMBER_OF_EXTRA_TUILES = 2;
    final int NUMBER_OF_PLAYERS = 2;
    final int NUMBER_OF_TUILES_PER_GAME = 116;

    final String LANG_FR = "fr";

    final int OK = 200;
    final int ERROR = 400;
    final int COULDNOTADDUSER = 401;
}
