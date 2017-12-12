package pdg5.server.process;

import pdg5.common.protocol.ErrorMessage;
import pdg5.common.protocol.Load;
import pdg5.common.protocol.Message;
import pdg5.common.protocol.SignIn;
import pdg5.server.manage.ManageGame;
import pdg5.server.manage.ManageUser;
import pdg5.server.model.GameController;
import pdg5.server.persistent.User;
import pdg5.server.util.ServerActiveUser;

/**
 * Created on 31.10.17 by Bykow
 */
public class ProcessSignIn implements GenericProcess {

   private SignIn signIn;
   private ManageUser manageUser;
   private ManageGame manageGame;
   private ServerActiveUser activeUser;
   private GameController gameController;

   public ProcessSignIn(SignIn signIn, ManageUser manageUser, ServerActiveUser activeUser, GameController gameController) {
      this.signIn = signIn;
      this.manageUser = manageUser;
      this.activeUser = activeUser;
      this.gameController = gameController;
   }

   @Override
   public Message execute() {
      if (manageUser.isCorrectPassword(signIn.getUsername(), signIn.getPassword())) {
         // TODO waiting for game logic to improve
         // TODO add client to activeUser

         return gameController.findGamesOf(manageUser.getUserByUsername(signIn.getUsername()).getId());
      } else {
         return new ErrorMessage("Password invalid in SignIn for user " + signIn.getUsername());
      }
   }
}
