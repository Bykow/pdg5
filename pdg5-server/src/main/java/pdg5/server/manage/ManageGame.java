package pdg5.server.manage;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pdg5.server.persistent.Game;
import pdg5.server.persistent.Tournament;
import pdg5.server.persistent.User;

import java.util.Date;
import java.util.List;

public class ManageGame extends Manager {

   public Game addGame(String title, User player1, User player2, Tournament tournament, String remainingLetters) {
      Game game = new Game();
      game.setTitle(title);
      game.setUserByPlayer1(player1);
      game.setUserByPlayer2(player2);
      game.setTournament(tournament);
      game.setCreated(new Date());
      game.setLastActivity(new Date());
      game.setRemainingLetters(remainingLetters);

      return (Game) addToDB(game);
   }

   public Game addGame(String title, User player1, User player2, String remainingLetters) {
      Game game = new Game();
      game.setTitle(title);
      game.setUserByPlayer1(player1);
      game.setUserByPlayer2(player2);
      game.setCreated(new Date());
      game.setLastActivity(new Date());
      game.setRemainingLetters(remainingLetters);

      return (Game) addToDB(game);
   }

   public List<Game> listGame() {
      return (List<Game>) getListFromDB("FROM Game");
   }

   public List<Game> getGamesByUser(User user) {
      Session session = getSession();
      Transaction tx = null;
      List<Game> games = null;

      try {
         tx = session.beginTransaction();
         games = session.createQuery("FROM Game WHERE player1 =:p1 OR player2 =:p2")
                 .setParameter("p1", user.getId())
                 .setParameter("p2", user.getId())
                 .list();

         tx.commit();
      } catch (HibernateException e) {
         if (tx != null) {
            tx.rollback();
         }
         e.printStackTrace();
      }
      return games;
   }

   public int updateGame(Game game) {
      return updateToDB(game);
   }

   public int deleteGame(Game game) {
      return deleteToDB(game);
   }
}
