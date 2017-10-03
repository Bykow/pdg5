package pdg5.common.game;

import java.util.ArrayList;
import pdg5.common.Protocol;

/**
 * Created on 03.10.17 by Bykow
 */
public class Composition {
    private ArrayList<Tuile> comp;
    private int value;

    public Composition(ArrayList<Tuile> comp) {
        this.comp = comp;
    }

    public ArrayList<Tuile> getComp() {
        return comp;
    }

    public int getValue(Board board) {
        int sum = 0;
        for (int i = 0; i < Protocol.NUMBEROFTUILES; i++) {
            if (comp.get(i) != null) {
                sum += comp.get(i).getValue() * board.getPositionValue(i);
            }
        }
        return value;
    }
}
