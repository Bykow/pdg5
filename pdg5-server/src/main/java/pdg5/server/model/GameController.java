package pdg5.server.model;

import pdg5.common.Protocol;
import pdg5.common.TST;
import pdg5.common.game.Board;
import pdg5.common.game.Composition;
import pdg5.common.game.GameModel;
import pdg5.common.game.Tile;
import pdg5.common.protocol.*;
import pdg5.server.manage.ManageUser;
import pdg5.server.util.ServerActiveUser;

import java.io.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import pdg5.server.manage.ManageGame;

/**
 * this Class manage all the games created by the server
 */
public class GameController {

   /**
    * Schedular that each minutes check if the games are outdated
    */
   private final ScheduledExecutorService checkGamesOutdatedScheduler;
   
   /**
    * Max time to play (72 hours in millisecond)
    */
   private static final int OUTDATE_TIME = 72 * 60 * 60 * 1000;
   
   /**
    * number of letters left in a TileStack befor the game changes to check-end-mode
    */
   private static int TILE_LEFT_END_MODE = 10;
   
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
    * message sent if we added someone to the matchmaking list
    */
   private static final String MESSAGE_LOOKING_OPPONENT = "Nous recherchons actuellement un adversaire";

   /**
    * dictionary containing the playable words
    */
   private final TST dictionary = new TST();

   /**
    * Link item to the user's part of database
    */
   private final ManageUser manageUser;
   
   /**
    * item containing informations about all the users online
    */
   private final ServerActiveUser activeUser;

   /**
    * Constructor
    * 
     * @param activeUser The activeUsers contains informations about all the users online
    */
    public GameController(ServerActiveUser activeUser) {
      games = new HashMap<>();
      clientGames = new HashMap<>();
      tileStacks = new HashMap<>();
      playerTurnManager = new HashMap<>();
      matchMaking = new ArrayList<>();
      manageUser = new ManageUser();

      InputStream inputStream = TST.class.getResourceAsStream("/dico/fr_dico.dic");
      new BufferedReader(new InputStreamReader(inputStream)).lines()
              .forEach(dictionary::put);
      this.activeUser = activeUser;
      checkGamesOutdatedScheduler = Executors.newScheduledThreadPool(1);
      checkGamesOutdatedScheduler.scheduleAtFixedRate(areGamesOutdated(), 1, 1, TimeUnit.MINUTES);
    }

   /**
    * If a player ask for a Game with a random opponent, this method check the
    * matchmaking list to give him an opponent or put him in the waiting list.
    *
    * @param idPlayerAsking unique id of the player who asked for a game
     * @return Message response
    */
   public Message newGame(int idPlayerAsking) {
      if (matchMaking.isEmpty()) {
         matchMaking.add(idPlayerAsking);
         return new ErrorMessage(MESSAGE_LOOKING_OPPONENT);
      } else if (matchMaking.contains(idPlayerAsking)) {
         return new ErrorMessage(MESSAGE_LOOKING_OPPONENT);
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
   public Message addNewGame(int idPlayer1, int idPlayer2) {

      // add new TileStack to the Map
      TileStack ts = new TileStack(Protocol.Languages.LANG_FR.toString());
      
      // Add the game to the DB
      ManageUser userManager = new ManageUser();
      ManageGame gameManager = new ManageGame();
      pdg5.server.persistent.Game game = gameManager.addGame("title", userManager.getUserById(idPlayer1), userManager.getUserById(idPlayer2), ts.convertToString());
      
      int idGame = game.getId();
      
      tileStacks.put(idGame, ts);

      
      // add a new turnManager for this game
      TurnManager tm = new TurnManager(idPlayer1, idPlayer2, System.currentTimeMillis());
      playerTurnManager.put(idGame, tm);
      
      // add new GameModel to the Map
      Board[] boards = new Board[]{initBoard(ts, idPlayer1),
         initBoard(ts, idPlayer2)};
      GameModel model = new GameModel(
              boards, idGame, new Date(), 0
      );
      model.getBoardById(idPlayer1).getComposition().setSquare(tm.getSquares(idPlayer1));
      model.getBoardById(idPlayer2).getComposition().setSquare(tm.getSquares(idPlayer2));
      games.put(idGame, model);

      // add new idGames to the list of games of players
      addGameForClient(model, idPlayer1);
      addGameForClient(model, idPlayer2);


      // take the bonus letters from the TileStack
      List<Tile> bonus = new ArrayList<>();
      bonus.add(ts.getNextTuile());
      bonus.add(ts.getNextTuile());
      boards[0].setBonus(bonus);

      try {
         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
         ObjectOutputStream output = new ObjectOutputStream(byteArrayOutputStream);
         output.writeObject(game);
         String gameState = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
         game.setGameState(gameState);
      } catch (IOException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      // sending to second player
      activeUser.getClientHandler(idPlayer2).addToQueue(getGameFromModel(model.getGameId(), idPlayer2));
      return getGameFromModel(model.getGameId(), idPlayer1);
   }

   /**
    * return a new Board filled with Tiles of a given TileStack
    *
    * @param ts TileStack used to fill the new Board
    * @param idPlayer unique id of the player
    * @return a new Board filled with Tiles
    */
   public Board initBoard(TileStack ts, int idPlayer) {
      Board board = new Board(manageUser.getUserById(idPlayer).getUsername(), idPlayer);
      List<Tile> letters = new ArrayList<>();
      for (int i = 0; i < 7; i++) {
         letters.add(ts.getNextTuile());
      }
      board.setLetters(letters);
      return board;
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
      Board boardOfClient = gm.getBoardById(idClient);
      Board opponentBoard = gm.getOpponentBoard(idClient);
      TileStack ts = tileStacks.get(gm.getGameId());
      TurnManager tm = playerTurnManager.get(gm.getGameId());
      boolean hisTurn = tm.isCurrentPlayer(idClient);
      List<Tile> bonus;
      if(hisTurn) {
         bonus = boardOfClient.getBonus();
      } else {
         bonus = opponentBoard.getBonus();
      }

      Game game = new Game(gm.getGameId(), "title", gm.getCreation(),
        gm.getLastMove(), gm.getIdTournament(), boardOfClient.getScore(),
        boardOfClient.getPlayerName(), opponentBoard.getScore(), opponentBoard.getPlayerName(),
        ts.getTileLeft(), boardOfClient.getLetters(),
        bonus, gm.getLastWordPlayed(), gm.getScoreLastWordPlayed(), hisTurn);
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

      // Calculate the value of the word
      Board testBoard = new Board(board);
      if(!testBoard.replayWord(composition.getWord())){
          return new ErrorMessage("You don't have the letters to play this word");
      }
      int scoreToAdd = testBoard.getValue();
      board.replayWord(composition.getWord());

      // Update model with this word played (score, turn of turnManager, Tiles of player)
      // score
      board.setScore(scoreToAdd + board.getScore());
      model.setScoreLastWordPlayed(scoreToAdd);
      model.setLastWordPlayed(word);
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
      tm.turnEnded(); // turn in turnManager
      Composition comp = board.getComposition(); // Squares
      comp.setSquare(tm.getSquares(playerID));
      comp.removeAll(); // remove composition letters and bonus letters
      
      board.setBonus(new ArrayList<>());
      
      
      /*
       * get new letters from TileStack and add it to the board
       */
      TileStack ts = tileStacks.get(gameID);
      List<Tile> newLetters = board.getLetters();
      for (int i = 0; i < word.length(); i++) {
         if (ts.getTileLeft() > 0) {
            newLetters.add(ts.getNextTuile());
         }
      }
      board.setLetters(newLetters);

      int opponentId = model.getOpponentBoard(playerID).getPlayerId();
      
      // Send game result if the game finished
      if(gameEnded(model, ts)) {
         if(board.getScore() > boardOpponent.getScore()) {
            activeUser.getClientHandler(opponentId).addToQueue(new End(End.RESULT.LOSE, gameID));
            return new End(End.RESULT.WIN, gameID);
         } else if (board.getScore() < boardOpponent.getScore()) {
            activeUser.getClientHandler(opponentId).addToQueue(new End(End.RESULT.WIN, gameID));
            return new End(End.RESULT.LOSE, gameID);
         } else {
            activeUser.getClientHandler(opponentId).addToQueue(new End(End.RESULT.EQUALITY, gameID));
            return new End(End.RESULT.EQUALITY, gameID);
         }
      }
      
      activeUser.getClientHandler(opponentId).addToQueue(getGameFromModel(gameID, opponentId));
      return getGameFromModel(gameID, playerID);
   }
   
   /**
    * return true if the game has ended
    * 
    * @param model the GameModel of the game we are checking
    * @param ts the TilesStack of the game we are checking
    * @return true if the game has ended
    */
   private boolean gameEnded(GameModel model, TileStack ts) {
      int tilesLeft = ts.getTileLeft();
      
      // not end-mode yet
      if(tilesLeft > TILE_LEFT_END_MODE) {
         return false;
      // two players passed -> end of game
      } else if(model.isHasPassedLastMovePlayer1() && model.isHasPassedLastMovePlayer2()) {
         return true;
      // the tileStacke is empty and a player used all his Tiles -> end of game
      } else if (tilesLeft == 0 && 
              (model.getBoard(GameModel.PlayerBoard.PLAYER1).getLetters().isEmpty()
              || model.getBoard(GameModel.PlayerBoard.PLAYER2).getLetters().isEmpty())) {
         return true;
      } else {
         return false;
      }
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
      Map<Character, Integer> occurenceContained = getOccurenceMapFromString(contained.toUpperCase());
      Map<Character, Integer> occurenceContainer = getOccurenceMapFromString(container);
      
      // if the container don't have the character in the map
      // or has less occurence of contained then it's false
      return occurenceContained
         .keySet()
         .stream()
         .noneMatch((character) -> (
            !occurenceContainer.containsKey(character)
            || occurenceContained.get(character) > occurenceContainer.get(character)
         ));
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

   /**
    * method called by the scheduler to check in all games if there is some outdated.
    * If some are outdated, the method change the state of the game 
    * and send to players a Game to inform them of the update.
    * 
    * @return the Runnable used by the scheduler to run the method.
    */
   private Runnable areGamesOutdated() {
      return new Runnable() {
         @Override
         public void run() {
            for (Map.Entry<Integer, GameModel> entry : games.entrySet()) {
               Integer gameID = entry.getKey();
               GameModel gameModel = entry.getValue();

               // we check only games in state IN_PROGRESS
               if (gameModel.getState() == GameModel.State.IN_PROGRESS) {
                  // Check if the game is outdated
                  if (new Date().getTime() - gameModel.getLastMove().getTime() > OUTDATE_TIME) {
                     gameModel.setState(GameModel.State.OUTDATED);
                     
                     // Send to players the update
                     int idPlayer1 = gameModel.getBoard(GameModel.PlayerBoard.PLAYER1).getPlayerId();
                     int idPlayer2 = gameModel.getBoard(GameModel.PlayerBoard.PLAYER2).getPlayerId();
                     activeUser.getClientHandler(idPlayer1).addToQueue(getGameFromModel(gameID, idPlayer1));
                     activeUser.getClientHandler(idPlayer2).addToQueue(getGameFromModel(gameID, idPlayer2));
                  }
               }
            }
         }
      };
   }
}
