package pdg5.server.model;

import pdg5.common.Protocol;
import pdg5.common.TST;
import pdg5.common.game.Board;
import pdg5.common.game.Composition;
import pdg5.common.game.GameModel;
import pdg5.common.game.Tile;
import pdg5.common.protocol.*;
import pdg5.server.manage.ManageUser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
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
   private final Map<Integer, List<Integer>> clientGames;

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
    * dictionary containing the playable words
    */
   private static TST dictionary = new TST();

   private ManageUser manageUser;

   /**
    * Constructor
    */
   public GameController() {
      games = new HashMap<>();
      clientGames = new HashMap<>();
      tileStacks = new HashMap<>();
      playerTurnManager = new HashMap<>();
      matchMaking = new ArrayList<>();
      manageUser = new ManageUser();

      InputStream inputStream = TST.class.getResourceAsStream("/dico/fr_dico.dic");
      new BufferedReader(new InputStreamReader(inputStream)).lines()
              .forEach(dictionary::put);

   }

   /**
    * If a player ask for a Game with a random opponent, this method check the
    * matchmaking list to give him an opponent or put him in the waiting list.
    *
    * @param idPlayerAsking unique id of the player who asked for a game
    */
   public Message askNewGame(int idPlayerAsking) {
      if (matchMaking.isEmpty()) {
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

      // TODO send to second player
      return getGameFromModel(model.getGameId(), idPlayer1);
   }

   /**
    * return a new Board filled with Tiles of a given TileStack
    *
    * @Param ts TileStack used to fill the new Board
    * @return a new Board filled with Tiles
    */
   public Board initBoard(TileStack ts, int idPlayer) {
      Board board = new Board(manageUser.getUserNameById(idPlayer), idPlayer);
      List<Tile> letters = new ArrayList<>();
      for (int i = 0; i < 7; i++) {
         letters.add(ts.getNextTuile());
      }
      board.setLetters(letters);
      return board;
   }

   /**
    * Send a request to the wished opponent to play a new Game
    *
    * @param idPlayerAskingForGame unique id of the player requesting a game to
    * opponent
    * @param idWishedOpponent unique id of the wished opponent
    * @return the protocol.TODO class to ask the opponent if he accept the game
    */
   public Message askOpponentNewGame(int idPlayerAskingForGame, int idWishedOpponent) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   /**
    * add a GameModel to the list of games of a client !!!carefull this don't add the
    * game to the Map of games!!!
    *
    * @param gm the GameModel we want to add
    * @param idPlayer the player participating at this game
    */
   private void addGameForClient(GameModel gm, int idPlayer) {
      if (clientGames.containsKey(idPlayer)) {
         clientGames.get(idPlayer).add(gm.getGameId());
      } else {
         List<Integer> newList = new ArrayList<>();
         newList.add(gm.getGameId());
         clientGames.put(idPlayer, newList);
      }
   }

   /**
    * Find all games where the specific client given by id participate and return the
    * list through the Load class
    *
    * @param idClient unique id we wants the list of games
    * @return the Load created and filled with all games of the client
    */
   public Message findGamesOf(int idClient) {
      // TODO clientGame null pointer exception when signIn
      List<Game> listGames = new ArrayList<>();
      List<Integer> gameIds = clientGames.get(idClient);
      if (gameIds != null) {
         gameIds.forEach((idGame) -> {
            listGames.add(getGameFromModel(idGame, idClient));
         });
      }
      return new Load(listGames);
   }

   /**
    * Create a protocol.Game from a GameModel and return it the GameModel used is
    * found with the id of a game the Game created is the point of view of the client
    * given by idClient
    *
    * @param idGame unique id of the game we use to build the protocol.Game
    * @param idClient unique id that will be the point of view of the Game created
    * @return the protocol.Game created
    */
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

   /**
    * Check if the Composition word is in the dictionary
    *
    * @param composition containing a word
    * @return true if the word is in the dictionary, else false
    */
   public Message validateComposition(Composition composition) {
      if (composition.isValid()) {
         return new ValidationWord(dictionary.contains(composition.toString()));
      } else {
         return new ValidationWord(false);
      }
   }

   /**
    * a player try to play a word in a given (id) game. The server first check if the
    * move is valid : 1) Check if the composition is a valide word Structure 2) Check
    * if the word is in the dictionary 3) Check if the player possess the word
    * letters
    *
    * @param gameID unique id of the game
    * @param playerID unique id of the player trying the move
    * @param composition containing the word
    * @return an ErrorMessage if a check fails or a Game with the new State because
    * the word has been played
    */
   public Message play(int gameID, int playerID, Composition composition) {
      // Check if the composition is a valide word Structure
      if (!composition.isValid()) {
         return new ErrorMessage("The given Composition isn't a valide word structure");
      }

      // Check if the word is in the dictionary
      // We can use trim because we checked the structure of the composition before
      String word = composition.getStringForm().trim();
      if (!dictionary.contains(word)) {
         return new ErrorMessage("The given word isn't in our dictionary");
      }

      // Check if the player possess the word letters
      GameModel model = games.get(gameID);
      Board board = model.getBoardById(playerID);
      List<Tile> letters = board.getLetters();
      StringBuilder sb = new StringBuilder();
      for (Tile letter : letters) {
         sb.append(letter.getLetter());
      }

      for (Tile bonus : board.getBonus()) {
         sb.append(bonus.getLetter());
      }

      if (!isContained(word, sb.toString())) {
         return new ErrorMessage("You don't have the letters to play this word. Tried : " + word + " have : " + sb.toString());
      }

      // Calculate the value of the word
      int scoreToAdd = composition.getValue(board);

      // Update model with this word played (score, turn of turnManager, Tiles of player)
      // score
      board.setScore(scoreToAdd + board.getScore());
      // Send Square.W to opponent
      TurnManager tm = playerTurnManager.get(gameID);
      Board boardOpponent = model.getOpponentBoard(playerID);
      List<Tile> newBonusTile = new ArrayList<>();
      Composition.Square[] squares = tm.getSquares(playerID);
      for (int i = 0; i < squares.length; i++) {
         if (squares[i] == Composition.Square.W) {
            newBonusTile.add(composition.remove(i));
         }
      }
      boardOpponent.setBonus(newBonusTile);
      // turn in turnManager
      tm.turnEnded();
      // Squares
      Composition comp = model.getComposition();
      comp.setBonus(tm.getSquares(playerID));
      // remove composition letters and bonus letters
      comp.removeAll();
      board.setBonus(new ArrayList<Tile>());
      // get new letters from TileStack and add it to the board
      TileStack ts = tileStacks.get(gameID);
      List<Tile> newLetters = board.getLetters();
      for (int i = 0; i < word.length(); i++) {
         if (ts.getTileLeft() > 0) {
            newLetters.add(ts.getNextTuile());
         }
      }
      board.setLetters(newLetters);

      // TODO check if game ended
      // Send to player the new state of the game
      // TODO send to second player
      return getGameFromModel(gameID, playerID);
   }

   /**
    * Check if the letters of a string (contained), are contained in an other string
    * (container)
    *
    * @param contained smaller string
    * @param container bigger or equal string
    * @return true if letters of contained are contained in container
    */
   private boolean isContained(String contained, String container) {
      Map<Character, Integer> occurenceContained = getOccurenceMapFromString(contained);
      Map<Character, Integer> occurenceContainer = getOccurenceMapFromString(container);
      for (Character character : occurenceContained.keySet()) {
         // if the container don't have the character in the map
         // or has less occurence of contained then it's false
         if (!occurenceContainer.containsKey(character)
                 || occurenceContained.get(character) > occurenceContainer.get(character)) {
            return false;
         }
      }
      return true;
   }

   /**
    * return a map where keys are the letters of a String and the value the occurence
    * of this letter
    *
    * @param string we want to be mapped
    * @return a map where keys are the letters of a String and the value the
    * occurence of this letter
    */
   private Map<Character, Integer> getOccurenceMapFromString(String string) {
      Map<Character, Integer> map = new HashMap<>();
      for (char c : string.toCharArray()) {
         if (map.containsKey(c)) {
            int nbOccurence = map.get(c);
            map.put(c, ++nbOccurence);
         } else {
            map.put(c, 1);
         }
      }
      return map;
   }
}
