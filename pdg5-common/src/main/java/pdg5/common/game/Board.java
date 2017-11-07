package pdg5.common.game;

import java.util.ArrayList;

/**
 * Created on 03.10.17 by Bykow
 */
public class Board {
    private ArrayList<Integer> positionValue;
    private ArrayList<Tile> extraTile;

    public Board(ArrayList<Integer> positionValue, ArrayList<Tile> extraTile) {
        this.positionValue = positionValue;
        this.extraTile = extraTile;
    }

    public int getPositionValue(int position) {
        return positionValue.get(position);
    }

    public void setPositionValue(ArrayList<Integer> positionValue) {
        this.positionValue = positionValue;
    }

    public ArrayList<Tile> getExtraTile() {
        return extraTile;
    }

    public void setExtraTile(ArrayList<Tile> extraTile) {
        this.extraTile = extraTile;
    }
}
