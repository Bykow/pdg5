package pdg5.common.game;

import java.util.ArrayList;

/**
 * Created on 03.10.17 by Bykow
 */
public class Board {
    private ArrayList<Integer> positionValue;
    private ArrayList<Tuile> extraTuile;

    public Board(ArrayList<Integer> positionValue, ArrayList<Tuile> extraTuile) {
        this.positionValue = positionValue;
        this.extraTuile = extraTuile;
    }

    public int getPositionValue(int position) {
        return positionValue.get(position);
    }

    public void setPositionValue(ArrayList<Integer> positionValue) {
        this.positionValue = positionValue;
    }

    public ArrayList<Tuile> getExtraTuile() {
        return extraTuile;
    }

    public void setExtraTuile(ArrayList<Tuile> extraTuile) {
        this.extraTuile = extraTuile;
    }
}
