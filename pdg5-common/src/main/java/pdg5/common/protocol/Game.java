package pdg5.common.protocol;

import pdg5.common.game.Composition;
import pdg5.common.game.Tile;

import java.util.Date;
import java.util.List;
import pdg5.common.game.Board;
import pdg5.common.game.GameModel.State;

/**
 * @author Maxime Guillod
 *
 * Class sended through the connection  by the server representing
 * a full game with two players.
 */
public class Game extends Message {

    private int ID;
    private Date created;
    private Date lastActivity;
    private int tournament;
    private Board board;
    private Board opponentBoard;
    private int nbLeftTile;
    private List<Tile> lastWordPlayed;
    private int scoreLastWordPlayed;
    private boolean yourTurn;
    private State state;
    
    public Game(int ID, Date created, Date lastActivity,
                int tournament, Board board, Board opponentBoard, int nbLeftTile, 
                List<Tile> lastWordPlayed, int scoreLastWordPlayed, boolean yourTurn, State state) {
        this.ID = ID;
        this.created = created;
        this.lastActivity = lastActivity;
        this.tournament = tournament;
        this.board = board;
        this.opponentBoard = opponentBoard;
        this.nbLeftTile = nbLeftTile;
        this.lastWordPlayed = lastWordPlayed;
        this.scoreLastWordPlayed = scoreLastWordPlayed;
        this.yourTurn = yourTurn;
        this.state = state;

    }

    /**
     * return the unique ID of a game
     * @return the unique ID of a game
     */
    public int getID() {
        return ID;
    }

    public Date getCreated() {
        return created;
    }

    public Date getLastActivity() {
        return lastActivity;
    }

    public int getTournament() {
        return tournament;
    }

   public Board getBoard() {
      return board;
   }

   public Board getOpponentBoard() {
      return opponentBoard;
   }

    public int getScore() {
        return board.getScore();
    }

    public String getNamePlayer() {
        return board.getPlayerName();
    }

    public int getOpponentScore() {
        return opponentBoard.getScore();
    }

    public String getOpponentName() {
        return opponentBoard.getPlayerName();
    }

    public int getNbLeftTile() {
        return nbLeftTile;
    }

    public List<Tile> getAddedTile() {
        return board.getLetters();
    }

    public List<Tile> getBonusLetters() {
      return board.getBonus();
    }
    
    public List<Tile> getOpponentBonusLetters() {
      return opponentBoard.getBonus();
    }

    public boolean isYourTurn() {
        return yourTurn;
    }

    public List<Tile> getLastWordPlayed() {
        return lastWordPlayed;
    }

    public int getScoreLastWordPlayed() {
        return scoreLastWordPlayed;
    }

   public State getState() {
      return state;
   }

    public String toString() {
        String output = "";
        output += "Player Name:     " + getNamePlayer() + "\n";
        output += "Player Score:    " + getScore() + "\n";
        output += "Opponent Name:   " + getOpponentName() + "\n";
        output += "Opponent Score:  " + getOpponentScore() + "\n";
        output += "isYourTurn:      " + yourTurn + "\n";
        output += "Last Word Played " + lastWordPlayed.toString() + "\n";
        output += "Bonus            " + getBonusLetters().toString() + "\n";
        output += "Opponent Bonus   " + getOpponentBonusLetters().toString() + "\n";

        return output;
    }
}
