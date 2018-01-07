package pdg5.common.protocol;

import pdg5.common.game.Composition;
import pdg5.common.game.Tile;

import java.util.Date;
import java.util.List;

/**
 * @author Maxime Guillod
 *
 * Class sended through the connection  by the server representing
 * a full game with two players.
 */
public class Game extends Message {

    private int ID;
    private String title;
    private Date created;
    private Date lastActivity;
    private int tournament;
    private int score;
    private int idPlayer;
    private String namePlayer;
    private int opponentScore;
    private int idPlayer2;
    private String opponentName;
    private int nbLeftTile;
    private List<Tile> addedTile;
    private List<Tile> bonusLetters;
    private List<Tile> opponentBonusLetters;

    private String lastWordPlayed;
    private int scoreLastWordPlayed;
    private boolean yourTurn;

    public Game(int ID, String title, Date created, Date lastActivity,
                int tournament, int score, String namePlayer, int opponentScore,
                String opponentName, int nbLeftTile, List<Tile> addedTile, List<Tile> bonusLetters,
                String lastWordPlayed, int scoreLastWordPlayed, boolean yourTurn) {
        this.ID = ID;
        this.title = title;
        this.created = created;
        this.lastActivity = lastActivity;
        this.tournament = tournament;
        this.score = score;
        this.namePlayer = namePlayer;
        this.opponentScore = opponentScore;
        this.opponentName = opponentName;
        this.nbLeftTile = nbLeftTile;
        this.addedTile = addedTile;
        this.bonusLetters = bonusLetters;
        this.opponentBonusLetters = opponentBonusLetters;
        this.lastWordPlayed = lastWordPlayed;
        this.scoreLastWordPlayed = scoreLastWordPlayed;
        this.yourTurn = yourTurn;

    }

    /**
     * return the unique ID of a game
     * @return the unique ID of a game
     */
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

    public String getNamePlayer() {
        return namePlayer;
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

    public List<Tile> getBonusLetters() {
        return bonusLetters;
    }

    public List<Tile> getOpponentBonusLetters() {
        return opponentBonusLetters;
    }

    public boolean isYourTurn() {
        return yourTurn;
    }

    public String getLastWordPlayed() {
        return lastWordPlayed;
    }

    public int getScoreLastWordPlayed() {
        return scoreLastWordPlayed;
    }

    public String toString() {
        String output = "";
        output += "Player Name:     " + namePlayer + "\n";
        output += "Player Score:     " + score + "\n";
        output += "Opponent Name:   " + opponentName + "\n";
        output += "Opponent Score:   " + opponentScore + "\n";
        output += "isYourTurn:      " + yourTurn + "\n";
        output += "Last Word Played " + lastWordPlayed + "\n";

        return output;
    }
}
