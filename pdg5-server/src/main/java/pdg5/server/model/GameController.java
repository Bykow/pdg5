package pdg5.server.model;

import pdg5.common.Protocol;
import pdg5.common.TST;
import pdg5.common.game.Board;
import pdg5.common.game.Composition;
import pdg5.common.game.GameModel;
import pdg5.common.game.GameModel.State;
import pdg5.common.game.Tile;
import pdg5.common.protocol.*;
import pdg5.server.util.ServerActiveUser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import pdg5.server.manage.ManageGame;
import pdg5.server.manage.ManageUser;
import pdg5.server.persistent.User;

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
    private static final int TILE_LEFT_END_MODE_HARD = 2;
    private static final int TILE_LEFT_END_MODE_LAZY = 10;

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
     * stock the list of chat message for a given unique id of game
     */
    private final Map<Integer, List<ChatServerSide>> chats;

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
     * item containing informations about all the users online
     */
    private final ServerActiveUser activeUser;

    /**
     * Constructor
     *
     * @param activeUser The activeUsers contains informations about all the users
     * online
     */
    public GameController(ServerActiveUser activeUser) {
        games = new HashMap<>();
        clientGames = new HashMap<>();
        tileStacks = new HashMap<>();
        playerTurnManager = new HashMap<>();
        chats = new HashMap<>();
        matchMaking = new ArrayList<>();

        InputStream inputStream = TST.class.getResourceAsStream("/dico/fr_dico.dic");
        new BufferedReader(new InputStreamReader(inputStream)).lines()
                .forEach(dictionary::put);
        this.activeUser = activeUser;

        checkGamesOutdatedScheduler = Executors.newScheduledThreadPool(1);
        checkGamesOutdatedScheduler.scheduleAtFixedRate(areGamesOutdated(), 1, 1, TimeUnit.MINUTES);
    }

    /**
     * Load the data from database for a user
     *
     * @param idPlayer
     */
    public void dataLoad(int idPlayer) {

        ManageGame manageGame = activeUser.getDatabaseManagers(idPlayer).getManageGame();
        ManageUser manageUser = activeUser.getDatabaseManagers(idPlayer).getManageUser();

        User user = manageUser.getUserById(idPlayer);

        clientGames.put(idPlayer, manageGame.getGamesByUser(user)
                .stream()
                .peek((g) -> tileStacks.put(g.getId(), new TileStack(Protocol.Languages.LANG_FR.toString(), g.getRemainingLetters())))
                .peek((g) -> playerTurnManager.put(g.getId(), (TurnManager) g.getTurnManagerAsSerializable()))
                .map((g) -> (GameModel) g.getGameStateAsSerializable())
                .peek((g) -> games.put(g.getGameId(), g))
                .map(GameModel::getGameId).collect(Collectors.toList()));

    }

    public void addChat(ChatServerSide chatServer) {
        addChat(chatServer, true);
    }

    /**
     * add to the map of chat messages a new message
     *
     * @param chatServer the message we want to add
     * @param sendToBoth if it must be sent to both
     */
    public void addChat(ChatServerSide chatServer, boolean sendToBoth) {
        int idGame = chatServer.getIdGame();
        // we create a list if it's the first message of the game
        List<ChatServerSide> list;
        if (!chats.containsKey(idGame)) {
            chats.put(idGame, new ArrayList<>());
        }
        list = chats.get(idGame);
        list.add(chatServer);

        // TODO update the DB
        //pdg5.server.persistent.Chat chatDB = new ManageChat().;
        // tell to other player
        int idSecondPlayer = games.get(idGame).getOpponentBoardById(chatServer.getIdSender()).getPlayerId();
        activeUser.giveToClientHandler(idSecondPlayer, chatServerToChat(idSecondPlayer, chatServer));
        if (sendToBoth) {
            int idFirstPlayer = games.get(idGame).getBoardById(chatServer.getIdSender()).getPlayerId();
            activeUser.giveToClientHandler(idFirstPlayer, chatServerToChat(idFirstPlayer, chatServer));
        }
    }

    /**
     * create a new Chat from a ChatServerSide
     *
     * @param chatServer the ChatServerSide used to create the Chat
     * @return a new Chat from a ChatServerSide
     */
    private Chat chatServerToChat(int idReceiver, ChatServerSide chatServer) {
        if (chatServer.getSenderType() != Chat.SENDER.SERVER) {
            if (idReceiver == chatServer.getIdSender()) {
                return new Chat(chatServer.getMessage(), chatServer.getIdGame(), chatServer.getTimeStamp(), Chat.SENDER.USER);
            } else {
                return new Chat(chatServer.getMessage(), chatServer.getIdGame(), chatServer.getTimeStamp(), Chat.SENDER.OPPONENT);
            }
        } else {
            return new Chat(chatServer.getMessage(), chatServer.getIdGame(), chatServer.getTimeStamp(), Chat.SENDER.SERVER);
        }

    }

    /**
     * return a map where keys are the unique id of games and values the historic of
     * the chat of the associated game
     *
     * @param playerID the unique player id we wish the historics
     * @return a map where keys are the unique id of games and values the historic of
     * the chat of the associated game
     */
    public Map<Integer, List<Chat>> getAllChatsOfPlayer(int playerID) {
        Map<Integer, List<Chat>> map = new HashMap<>();
        List<Integer> userGames = clientGames.get(playerID);
        userGames.forEach((idGame) -> {
            List<Chat> chatList = new ArrayList<>();
            List<ChatServerSide> messagesChat = chats.get(idGame);
            if (messagesChat != null) {
                messagesChat.forEach((chatServerSide) -> {
                    chatList.add(chatServerToChat(playerID, chatServerSide));
                });
            }
            map.put(idGame, chatList);
        });

        return map;
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
        ManageGame manageGame = activeUser.getDatabaseManagers(idPlayer1).getManageGame();
        ManageUser manageUser = activeUser.getDatabaseManagers(idPlayer1).getManageUser();
        pdg5.server.persistent.Game game = manageGame.addGame("title", manageUser.getUserById(idPlayer1), manageUser.getUserById(idPlayer2), ts.convertToString());
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

        Tile next = ts.getNextTuile();
        next.setBonus(true);
        bonus.add(next);

        next = ts.getNextTuile();
        next.setBonus(true);
        bonus.add(next);

        if (tm.isCurrentPlayer(idPlayer1)) {
            model.getBoardById(idPlayer1).setBonus(bonus);
        } else {
            model.getOpponentBoard(idPlayer1).setBonus(bonus);
        }

        // update the game to database
        game.setGameState(model);
        game.setTurnManager(tm);
        manageGame.updateGame(game);

        // sending to second player
        activeUser.giveToClientHandler(idPlayer2, getGameFromModel(model.getGameId(), idPlayer2));
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
        ManageUser manageUser = activeUser.getDatabaseManagers(idPlayer).getManageUser();
        Board board = new Board(manageUser.getUserById(idPlayer).getUsername(), idPlayer);
        List<Tile> letters = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            letters.add(ts.getNextTuile());
        }
        board.setLetters(letters);
        return board;
    }

    /**
     * add a GameModel to the list of games of a client !!!carefull this don't add
     * the game to the Map of games!!!
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
     * Find all games where the specific client given by id participate and return
     * the list through the Load class
     *
     * @param idClient unique id we wants the list of games
     * @return the Load created and filled with all games of the client
     */
    public List<Game> findGamesOf(int idClient) {
        List<Game> listGames = new ArrayList<>();
        List<Integer> gameIds = clientGames.get(idClient);
        if (gameIds != null) {
            gameIds.forEach((idGame) -> {
                listGames.add(getGameFromModel(idGame, idClient));
            });
        }
        return listGames;
    }

    /**
     * Create a protocol.Game from a Game Model and return it the GameModel used is
     * found with the id of a game the Game created is the point of view of the
     * client given by idClient
     *
     * @param idGame unique id of the game we use to build the protocol.Game
     * @param idClient unique id that will be the point of view of the Game created
     * @return the protocol.Game created
     */
    private Game getGameFromModel(int idGame, int idClient) {
        GameModel gm = games.get(idGame);
        Board boardOfClient = new Board(gm.getBoardById(idClient));
        Board opponentBoard = gm.getOpponentBoard(idClient);

        Board cleanedOpponentBoard = new Board(opponentBoard);
        cleanedOpponentBoard.getLetters().clear();

        TileStack ts = tileStacks.get(gm.getGameId());
        TurnManager tm = playerTurnManager.get(gm.getGameId());
        boolean hisTurn = tm.isCurrentPlayer(idClient);

        List<Tile> lastWordPlayed;
        lastWordPlayed = gm.getLastWordPlayed();

        Game game = new Game(gm.getGameId(), gm.getCreation(),
                gm.getLastMove(), gm.getIdTournament(), boardOfClient,
                cleanedOpponentBoard, ts.getTileLeft(),
                lastWordPlayed, gm.getScoreLastWordPlayed(), hisTurn, gm.getState());
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
     * A player try to pass his turn, depending on the context (endGameMode) we will
     * pass or throw tiles
     *
     * @param gameID unique id of the game
     * @param playerID unique id of the player trying to pass
     * @return an ErrorMessage if he can't pass, or a Game with the updates
     */
    public Message pass(int gameID, int playerID) {
        //check if the game exist
        GameModel model = games.get(gameID);
        if (model == null || model.getState() != State.IN_PROGRESS) {
            return new ErrorMessage("Cette partie n'existe plus ou a été terminée");
        }

        //check if it's player's turn
        TurnManager tm = playerTurnManager.get(gameID);
        if (!tm.isCurrentPlayer(playerID)) {
            return new ErrorMessage("Ce n'est pas votre tour !");
        }

        // empty the last word played as there is none
        model.setLastWordPlayed(new ArrayList<>());

        // throw Tiles or pass depending on the game context
        TileStack ts = tileStacks.get(gameID);
        if (isEndMode(model, ts)) {
            pass(model, playerID, ts);
        } else {
            throwAction(model, playerID, ts);
        }

        // send game result if the game is finish
        if (gameEnded(model, ts)) {
            sendScoreResults(model);
            return new Noop(Noop.Sender.SERVER);
        } else { // send next turn
            tm.turnEnded();

            ManageGame manageGame = activeUser.getDatabaseManagers(playerID).getManageGame();
            ManageUser manageUser = activeUser.getDatabaseManagers(playerID).getManageUser();

            // update the DB game
            pdg5.server.persistent.Game game
                    = manageGame.getGamesByUser(manageUser.getUserById(playerID))
                            .stream()
                            .filter((f) -> f.id == gameID)
                            .findAny()
                            .get();
            game.setGameState(model);
            game.setTurnManager(tm);
            manageGame.updateGame(game);

            int opponentId = model.getOpponentBoardById(playerID).getPlayerId();
            Message m = getGameFromModel(gameID, opponentId);
            activeUser.giveToClientHandler(opponentId, m);
            return getGameFromModel(gameID, playerID);
        }
    }

    private void pass(GameModel model, int playerID, TileStack ts) {
        Board board = model.getBoardById(playerID);

        // the player lose the total value of bonus letters in his score
        List<Tile> bonus = board.getBonus();
        int lostScore = 0;
        lostScore = bonus.stream().map((bonusTile) -> bonusTile.getValue()).reduce(lostScore, (sum, tile) -> sum + tile);
        board.setScore(board.getScore() - lostScore);
        board.setBonus(new ArrayList<>());
        board.setLastAction(Board.LAST_ACTION.PASS);

        // Save Last Action for Chat
        addChat(new ChatServerSide(new Date().getTime(), playerID, Chat.SENDER.USER,
                board.getPlayerName() + " a passé et perdu " + lostScore + " points", model.getGameId()));
    }

    private void throwAction(GameModel model, int playerID, TileStack ts) {
        Board board = model.getBoardById(playerID);
        Board opponentBoard = model.getOpponentBoard(playerID);
        List<Tile> letters = board.getLetters();
        List<Tile> bonusOpponent = new ArrayList<>();

        //two random of player letter are sent as bonus to the opponent
        for (int i = 0; i < 2; i++) {
            bonusOpponent.add(letters.remove(rand.nextInt(letters.size())));
            Tile t = ts.getNextTuile();
            t.setBonus(true);
            letters.add(ts.getNextTuile());
        }
        opponentBoard.setBonus(bonusOpponent);

        // the player lose the total value of bonus letters in his score
        List<Tile> bonus = board.getBonus();
        int lostScore = 0;
        lostScore = bonus.stream().map((bonusTile) -> bonusTile.getValue()).reduce(lostScore, (sum, tile) -> sum + tile);
        board.setScore(board.getScore() - lostScore);
        board.setBonus(new ArrayList<>());
        board.setLastAction(Board.LAST_ACTION.THROW);

        // Save Last Action for Chat
        StringBuilder wordAsString = new StringBuilder();
        bonusOpponent.forEach((tile) -> {
            wordAsString.append(tile.getLetter());
        });

        addChat(new ChatServerSide(new Date().getTime(), playerID, Chat.SENDER.USER,
                board.getPlayerName() + " a jeté "
                + wordAsString.toString() + " et perdu " + lostScore + " points", model.getGameId()));
    }

    /**
     * a player try to play a word in a given (id) game. The server first check if
     * the move is valid : 1) check if the game exist 2) check if it's player's turn
     * 3) Check if the composition is a valide word Structure 4) Check if the word is
     * in the dictionary 5) Check if the player possess the word letters
     *
     * @param gameID unique id of the game
     * @param playerID unique id of the player trying the move
     * @param composition containing the word
     * @return an ErrorMessage if a check fails or a Game with the new State because
     * the word has been played
     */
    public Message play(int gameID, int playerID, Composition composition) {

        //check if the game exist
        GameModel model = games.get(gameID);
        if (model == null || model.getState() != State.IN_PROGRESS) {
            return new ErrorMessage("Cette partie n'existe plus ou a été terminée");
        }

        //check if it's player's turn
        TurnManager tm = playerTurnManager.get(gameID);
        if (!tm.isCurrentPlayer(playerID)) {
            return new ErrorMessage("Ce n'est pas votre tour !");
        }

        // Check if the composition is a valide word Structure
        if (!composition.isValid()) {
            return new ErrorMessage("La composition n'est pas une structure valide");
        }

        // Check if the word is in the dictionary
        // We can use trim because we checked the structure of the composition before
        if (!dictionary.contains(composition.getStringForm().trim())) {
            return new ErrorMessage("Ce mot n'est pas dans notre dictionnaire");
        }

        Board board = model.getBoardById(playerID);

        // Check if the player possess the word letters
        Board testBoard = new Board(board);
        List<Tile> word = composition.getWord();
        if (!testBoard.replayWord(word)) {
            return new ErrorMessage("Vous n'avez pas les lettres pour jouer ce mot");
        }

        // Calculate the value of the word
        int scoreToAdd = testBoard.getValue();
        board.replayWord(word);

        // Update model with this word played (score, turn of turnManager, Tiles of player)
        // score
        board.setScore(scoreToAdd + board.getScore());
        model.setScoreLastWordPlayed(scoreToAdd);
        model.setLastWordPlayed(word);

        // Send Square.W to opponent
        Board boardOpponent = model.getOpponentBoard(playerID);
        List<Tile> newBonusTile = new ArrayList<>();
        Composition.Square[] squares = tm.getSquares(playerID);
        for (int i = 0; i < word.size(); i++) {
            if (squares[i] == Composition.Square.W) {
                Tile t = new Tile(composition.remove(i));
                t.setBonus(true);
                newBonusTile.add(t);
            }
        }
        boardOpponent.setBonus(newBonusTile);
        tm.turnEnded(); // turn in turnManager
        Composition comp = board.getComposition(); // Squares
        comp.setSquare(tm.getSquares(playerID));
        comp.removeAll(); // remove composition letters and bonus letters

        board.getBonus().clear();

        //get new letters from TileStack and add it to the board
        TileStack ts = tileStacks.get(gameID);
        List<Tile> newLetters = board.getLetters();
        for (int i = 0; i < word.size(); i++) {
            if (ts.getTileLeft() > 0) {
                newLetters.add(ts.getNextTuile());
            }
        }
        board.setLetters(newLetters);

        int opponentId = model.getOpponentBoard(playerID).getPlayerId();
        board.setLastAction(Board.LAST_ACTION.PLAY);

        // Send game result if the game finished
        if (gameEnded(model, ts)) {
            sendScoreResults(model);
            return new Noop(Noop.Sender.SERVER);
        }

        ManageGame manageGame = activeUser.getDatabaseManagers(playerID).getManageGame();
        ManageUser manageUser = activeUser.getDatabaseManagers(playerID).getManageUser();

        // update the DB game
        pdg5.server.persistent.Game game
                = manageGame.getGamesByUser(manageUser.getUserById(playerID))
                        .stream()
                        .filter((f) -> f.id == gameID)
                        .findAny()
                        .get();
        game.setGameState(model);
        game.setTurnManager(tm);
        manageGame.updateGame(game);

        // Save Last Action for Chat
        StringBuilder wordAsString = new StringBuilder();
        word.forEach((tile) -> {
            wordAsString.append(tile.getLetter());
        });
        

        addChat(new ChatServerSide(new Date().getTime(), playerID, Chat.SENDER.USER,
                board.getPlayerName() + " a joué "
                + wordAsString.toString() + " pour " + scoreToAdd + " points", gameID));

        activeUser.giveToClientHandler(opponentId, getGameFromModel(gameID, opponentId));
        return getGameFromModel(gameID, playerID);
    }

    private void sendScoreResults(GameModel model) {
        int gameID = model.getGameId();
        Board board = model.getBoard(GameModel.PlayerBoard.PLAYER1);
        Board boardOpponent = model.getBoard(GameModel.PlayerBoard.PLAYER2);
        int player1Id = board.getPlayerId();
        int player2Id = boardOpponent.getPlayerId();

        if (board.getScore() > boardOpponent.getScore()) {
            activeUser.giveToClientHandler(player2Id, new End(End.RESULT.LOSE, gameID));
            activeUser.giveToClientHandler(player1Id, new End(End.RESULT.WIN, gameID));
        } else if (board.getScore() < boardOpponent.getScore()) {
            activeUser.giveToClientHandler(player2Id, new End(End.RESULT.WIN, gameID));
            activeUser.giveToClientHandler(player1Id, new End(End.RESULT.LOSE, gameID));
        } else {
            activeUser.giveToClientHandler(player2Id, new End(End.RESULT.EQUALITY, gameID));
            activeUser.giveToClientHandler(player1Id, new End(End.RESULT.EQUALITY, gameID));
        }
    }

    private boolean isEndMode(GameModel model, TileStack ts) {
        int tilesLeft = ts.getTileLeft();
        // not end-mode yet
        if (tilesLeft <= TILE_LEFT_END_MODE_HARD
                || tilesLeft <= TILE_LEFT_END_MODE_LAZY
                && model.getBoard(GameModel.PlayerBoard.PLAYER1).getLastAction() == Board.LAST_ACTION.THROW
                && model.getBoard(GameModel.PlayerBoard.PLAYER2).getLastAction() == Board.LAST_ACTION.THROW) {
            model.setState(State.END_MODE);
        }

        return model.getState() == GameModel.State.END_MODE;
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
        if (!isEndMode(model, ts)) {
            return false;
            // two players passed -> end of game
        } else if (model.getBoard(GameModel.PlayerBoard.PLAYER1).getLastAction() == Board.LAST_ACTION.PASS
                && model.getBoard(GameModel.PlayerBoard.PLAYER2).getLastAction() == Board.LAST_ACTION.PASS) {
            return true;
            // the tileStacke is empty and a player used all his Tiles -> end of game
        } else {
            return tilesLeft == 0
                    && (model.getBoard(GameModel.PlayerBoard.PLAYER1).getLetters().isEmpty()
                    || model.getBoard(GameModel.PlayerBoard.PLAYER2).getLetters().isEmpty());
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
                .noneMatch((character) -> (!occurenceContainer.containsKey(character)
                || occurenceContained.get(character) > occurenceContainer.get(character)));
    }

    /**
     * return a map where keys are the letters of a String and the value the
     * occurence of this letter
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
     * return the dictionnary of the game
     * @return a Ternary Search Tree
     */
    public TST getDictionnary() {
        return dictionary;
    }

    /**
     * method called by the scheduler to check in all games if there is some
     * outdated. If some are outdated, the method change the state of the game and
     * send to players a Game to inform them of the update.
     *
     * @return the Runnable used by the scheduler to run the method.
     */
    private Runnable areGamesOutdated() {
        return () -> {
            games.entrySet().forEach((entry) -> {
                Integer gameID = entry.getKey();
                // Check if the game is outdated
                GameModel gameModel = entry.getValue();
                // we check only games in state IN_PROGRESS
                if (gameModel.getState() == GameModel.State.IN_PROGRESS) {
                    if (new Date().getTime() - gameModel.getLastMove().getTime() > OUTDATE_TIME) {
                        gameModel.setState(GameModel.State.OUTDATED);

                        ManageGame manageGame = new ManageGame();
                        ManageUser manageUser = new ManageUser();

                        // Send to players the update
                        int idPlayer1 = gameModel.getBoard(GameModel.PlayerBoard.PLAYER1).getPlayerId();
                        int idPlayer2 = gameModel.getBoard(GameModel.PlayerBoard.PLAYER2).getPlayerId();
                        // update the DB game
                        pdg5.server.persistent.Game game
                                = manageGame.getGamesByUser(manageUser.getUserById(idPlayer1))
                                        .stream()
                                        .filter((f) -> Objects.equals(f.id, gameID))
                                        .findAny()
                                        .get();
                        game.setGameState(gameModel);
                        manageGame.updateGame(game);
                        activeUser.giveToClientHandler(idPlayer1, getGameFromModel(gameID, idPlayer1));
                        activeUser.giveToClientHandler(idPlayer2, getGameFromModel(gameID, idPlayer2));
                    }
                }
            });
        };
    }
}
