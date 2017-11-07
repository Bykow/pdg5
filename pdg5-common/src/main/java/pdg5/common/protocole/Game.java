package pdg5.common.protocole;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import pdg5.common.game.Composition;
import pdg5.common.game.Tile;

/**
 * @author Maxime Guillod
 */
public class Game implements Serializable {

    private int ID;
    private String title;
    private Date created;
    private Date lastActivity;
    private int tournament;
    private int score;
    private int opponentScore;
    private String opponentName;
    private int nbLeftTile;
    private List<Tile> addedTile;
    private Composition.Square square;
    private List<Tile> bonusLetters;
    private boolean bonusIsMine;
    
    public Game(int ID, String title, Date created, Date lastActivity, 
                int tournament, int score, int opponentScore, 
                String opponentName, int nbLeftTile, List<Tile> addedTile,
                Composition.Square square, List<Tile> bonusLetters,
                boolean bonusIsMine) {
        this.ID = ID;
        this.title = title;
        this.created = created;
        this.lastActivity = lastActivity;
        this.tournament = tournament;
        this.score = score;
        this.opponentScore = opponentScore;
        this.opponentName = opponentName;
        this.nbLeftTile = nbLeftTile;
        this.addedTile = addedTile;
        this.square = square;
        this.bonusLetters = bonusLetters;
        this.bonusIsMine = bonusIsMine;
        
    }

    public int getID() {
       return ID;
    } 

    public String getTitle() {
       return title;
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

    public int getScore() {
       return score;
    }

   public int getOpponentScore() {
      return opponentScore;
   }

   public String getOpponentName() {
      return opponentName;
   }

   public int getNbLeftTile() {
      return nbLeftTile;
   }

   public List<Tile> getAddedTile() {
      return addedTile;
   }

   public Composition.Square getSquare() {
      return square;
   }

   public List<Tile> getBonusLetters() {
      return bonusLetters;
   }

   public boolean isBonusIsMine() {
      return bonusIsMine;
   }
   
   
    
    
    
    
}
