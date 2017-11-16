package pdg5.server.model;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import pdg5.common.Protocol;
import pdg5.common.TST;
import pdg5.common.game.Board;
import pdg5.common.game.Composition;
import pdg5.common.game.GameModel;
import pdg5.common.game.Tile;
import pdg5.common.protocol.ErrorMessage;
import pdg5.common.protocol.Game;
import pdg5.common.protocol.Load;
import pdg5.common.protocol.Message;
import pdg5.common.protocol.ValidationWord;

/**
 * @author Jimmy Verdasca
 * 
 * this Class manage all the games created by the server
 */
public class GameController {
   
   /**
    * random used for Square position random
    */
   private final Random rand = new Random();
   
   /**
    * stock the model of the game, the hash use the id of the game
    */
   private final Map<Integer, GameModel> games;
   
   /**
    * stock for an id of client all his id games
    */
   private final Map<Integer, List<Integer> > clientGames;
   
   /**
    * stock the tileStack of a game, the hash use the id of the game
    */
   private final Map<Integer, TileStack> tileStacks;
   
   /**
    * stock the turn manager for a given unique id of game
    */
   private final Map<Integer, TurnManager> playerTurnManager;
   
   /**
    * current player waiting for an other random player
    */
   private final List<Integer> matchMaking;
   
   /**
    * first free id of Game possible
    */
   private static int currentAvailableId = -1;
   
   /**
    * message sent if we added someone to the matchmaking list
    */
   private static final String lookingForOpponent = "Nous recherchons actuellement un adversaire";
   
   /**
    * dictionnary containing the playable words
    */
   private static TST dictionnary = new TST();
   
   /**
    * Constructor
    */
   public GameController() {
      games = new HashMap<>();
      clientGames = new HashMap<>();
      tileStacks = new HashMap<>();
      playerTurnManager = new HashMap<>();
      matchMaking = new ArrayList<>();
      
      // Fill the dictionnary
      ClassLoader classLoader = GameController.class.getClassLoader();
      Stream<String> lines;
      try {
         lines = Files.lines(Paths.get(classLoader.getResource("dico/fr_dico.dic").toURI()));
         lines.forEach(e -> dictionnary.put(e));
         lines.close();
      } catch (URISyntaxException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
   
   /**
    * If a player ask for a Game with a random opponent, this method check 
    * the matchmaking list to give him an opponent or 
    * put him in the waiting list.
    * 
    * @param idPlayerAsking unique id of the player who asked for a game
    */
   public Message askNewGame(int idPlayerAsking) {
      if(matchMaking.isEmpty()) {
         matchMaking.add(idPlayerAsking);
         return new ErrorMessage(lookingForOpponent);
      } else {
         return addNewGame(idPlayerAsking, matchMaking.remove(0));
      }
   }
   
   /**
    * Start a new game by creating a GameModel and adding it to the list games
    * 
    * @param idPlayer1 unique id of first player
    * @param idPlayer2 unique id of second player
    * @return the game to the clients who will play
    */
   private Message addNewGame(int idPlayer1, int idPlayer2) {
      currentAvailableId++;
      
      // add new TileStack to the Map
      TileStack ts = new TileStack(Protocol.Languages.LANG_FR.toString());
      tileStacks.put(currentAvailableId, ts);
      
      // add new GameModel to the Map
      Board[] boards = new Board[]{initBoard(ts, idPlayer1), 
                                   initBoard(ts, idPlayer2)};
      GameModel model = new GameModel(
              boards, currentAvailableId, new Date(), 0
      );
      games.put(currentAvailableId, model);
      
      // add new idGames to the list of games of players
      addGameForClient(model, idPlayer1);
      addGameForClient(model, idPlayer2);
      
      // add a new turnManager for this game
      TurnManager tm = new TurnManager(idPlayer1, idPlayer2, System.currentTimeMillis());
      playerTurnManager.put(currentAvailableId, tm);
      
      // take the bonus letters from the TileStack
      List<Tile> bonus = new ArrayList<>();
      bonus.add(ts.getNextTuile());
      bonus.add(ts.getNextTuile());
      
      return getGameFromModel(model.getGameId(), idPlayer1);
   }
   
   /**
    * return a new Board filled with Tiles of a given TileStack
    * 
    * @Param ts TileStack used to fill the new Board
    * @return a new Board filled with Tiles
    */
   public Board initBoard(TileStack ts, int idPlayer) {
      Board board = new Board(userManager.getUserNameById(idPlayer), idPlayer);
      List<Tile> letters = new ArrayList<>();
      for (int i = 0; i < 7; i++) {
         letters.add(ts.getNextTuile());
      }
      board.setLetters(letters);
      return board;
   }
   
   public Message askOpponentNewGame(int idPlayerAskingForGame, int idWishedOpponent) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
   
   private void addGameForClient(GameModel gm, int idPlayer) {
      if(clientGames.containsKey(idPlayer)) {
         clientGames.get(idPlayer).add(gm.getGameId());
      } else {
         List<Integer> newList = new ArrayList<>();
         newList.add(gm.getGameId());
         clientGames.put(idPlayer, newList);
      }
   }

   public Message findGamesOf(int idClient) {
      List<Game> games = new ArrayList<>();
      for (Integer idGame : clientGames.get(idClient)) {
         games.add(getGameFromModel(idGame, idClient));
      }
      return new Load(games, idClient);
   }
   
   private Game getGameFromModel(int idGame, int idClient) {
      GameModel gm = games.get(idGame);
      Board board1 = gm.getBoard(GameModel.PlayerBoard.PLAYER1);
      Board board2 = gm.getBoard(GameModel.PlayerBoard.PLAYER2);
      Board boardOfClient = gm.getBoardById(idClient);
      TileStack ts = tileStacks.get(gm.getGameId());
      TurnManager tm = playerTurnManager.get(gm.getGameId());
      
      Game game = new Game(gm.getGameId(), "title", gm.getCreation(), 
              gm.getLastMove(), gm.getIdTournament(), board1.getScore(), 
              board1.getPlayerName(), board2.getScore(), board2.getPlayerName(),
              ts.getTileLeft(), board1.getLetters(), tm.getSquares(idClient), 
              boardOfClient.getBonus(), tm.isCurrentPlayer(idClient));
      return game;
   }

   public Message validateComposition(Composition composition) {
      return new ValidationWord(dictionnary.contains(composition.toString()));
   }
}