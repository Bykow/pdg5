package pdg5.common;

/**
 * Created on 03.10.17 by Bykow
 */
public interface Protocol {

    /**
     * Socket
     */
    final String DEFAULT_SERVER = "localhost"; // modify for tests
    final int DEFAULT_PORT = 44555;

    final int NUMBER_OF_TUILES_PER_PLAYER = 7;
    final int NUMBER_OF_EXTRA_TUILES = 2;
    final int NUMBER_OF_PLAYERS = 2;
    final int NUMBER_OF_TUILES_PER_GAME = 116;

    final int DISPLAY_CHAT_MESSAGES = 50;

    final int OK = 200;
    final int ERROR = 400;
    final int COULDNOTADDUSER = 401;

    public enum Languages {LANG_FR ("fr");
      private final String lang;

      /**
       * constructor
       * @param lang string for a langage
       */
      private Languages(final String lang) {
          this.lang = lang;
      }

      /**
       * return the string associated to the langage
       * @return the string associated to the langage
       */
      @Override
      public String toString() {
          return lang;
      }
    };
    
    final int TOAST_BOTTOM_DIST = 50;
    final int TOAST_DEFAULT_DURATION = 5000;
}
