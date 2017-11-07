package pdg5.common.game;

import pdg5.common.Protocol;

import java.util.ArrayList;

/**
 * Created on 03.10.17 by Bykow
 * A composition is an array of 0 to 7 Tuiles that a user can send to play his turn
 */
public class Composition {
    private ArrayList<Tile> comp;
    private int value;

    /**
     * Ctor
     *
     * @param comp array of Tuiles
     */
    public Composition(ArrayList<Tile> comp) {
        this.comp = comp;
    }

    /**
     * Returns the Arraylist of Tuiles
     *
     * @return
     */
    public ArrayList<Tile> getComp() {
        return comp;
    }

    /**
     * Computes the value of a Composition
     *
     * @param board
     * @return
     */
    public int getValue(Board board) {
        int sum = 0;
        for (int i = 0; i < Protocol.NUMBER_OF_TUILES_PER_PLAYER; i++) {
            if (comp.get(i) != null) {
                sum += comp.get(i).getValue() * board.getPositionValue(i);
            }
        }
        value = sum;
        return value;
    }
}
