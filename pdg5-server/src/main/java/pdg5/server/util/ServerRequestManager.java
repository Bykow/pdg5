package pdg5.server.util;

import pdg5.common.protocol.*;
import pdg5.server.manage.ManageUser;
import pdg5.server.model.GameController;
import pdg5.server.process.*;

/**
 * @author Maxime Guillod
 */
public class ServerRequestManager {

    private final ManageUser manageUser;
    private final ServerActiveUser activeUser;
    private final GameController gameController;

    public ServerRequestManager(ServerActiveUser activeUser) {
        this.manageUser = new ManageUser();
        this.activeUser = activeUser;
        this.gameController = new GameController(activeUser);
    }

    /**
     * @param o
     * @param ch
     * @return Message to be send
     */
    public Message execute(Message o, ClientHandler ch) {
        System.out.println("Message received : " + o);
        
        if (o instanceof SignUp) {
            return new ProcessSignUp((SignUp) o, manageUser, activeUser, ch).execute();

        } else if (o instanceof SignIn) {
            return new ProcessSignIn((SignIn) o, manageUser, activeUser, gameController, ch).execute();

        } else if (o instanceof Noop) {
            return new ProcessNoop((Noop) o).execute();
            
        } else if (o instanceof NewGame) {
           return new ProcessNewGame((NewGame) o, gameController, activeUser).execute();
           
        } else if (o instanceof Validation) {
           return new ProcessValidation((Validation) o, gameController).execute();
           
        } else if (o instanceof Play) {
           return new ProcessPlay((Play) o, gameController, activeUser).execute(); 
           
        } else if (o instanceof Friend) {
           return new ProcessFriend((Friend) o, manageUser).execute(); 
           
        }

        return new ErrorMessage("Unhandled ErrorMessage is ServerRequestManager, default reached");
    }

}
