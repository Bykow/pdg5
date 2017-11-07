package pdg5.client;

import pdg5.common.protocole.Chat;
import pdg5.common.protocole.Game;
import pdg5.common.protocole.Load;
import pdg5.common.protocole.ValidationWord;

/**
 *
 */
public class ClientRequestManager {

   public void execute(Object o) {
      if(o instanceof Load){
         loadAllGames((Load)o);
      } else if(o instanceof Game) {
         loadGame((Game)o);
      } else if(o instanceof ValidationWord) {
         validateWord((ValidationWord)o);
      } else if(o instanceof Chat) {
         getChat((Chat)o);
      }
   }

   public void loadAllGames(Load load) {
      // getting all the games

   }

   public void loadGame(Game game) {
      // getting one game information

   }

   public void validateWord(ValidationWord validation) {
      // validation of a word

      System.out.println("Valid : " + validation.isIsValid());
   }

   public void getChat(Chat chat) {
      // chat entry

   }
}
