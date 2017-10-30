package pdg5.server;

import pdg5.common.protocole.Chat;
import pdg5.common.protocole.Game;
import pdg5.common.protocole.Load;
import pdg5.common.protocole.ValidationWord;

/**
 * @author Maxime Guillod
 */
public class ServerRequestManager {

    public void execute(Object o) {
        if (o instanceof Load) {
            loadAllGames((Load) o);
        } else if (o instanceof Game) {
            loadGame((Game) o);
        } else if (o instanceof ValidationWord) {
            validateWord((ValidationWord) o);
        } else if (o instanceof Chat) {
            getChat((Chat) o);
        }
    }

    public void loadAllGames(Load load) {
        // TODO
        // getting all the games

    }

    public void loadGame(Game game) {
        System.out.println("ServerRequestManager.loadGame");
        // TODO
        // getting one game information

    }

    public void validateWord(ValidationWord validation) {
        // TODO
        // validation of a word

    }

    public void getChat(Chat chat) {
        // TODO
        // chat entry

    }
}
