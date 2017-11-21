package pdg5.server.model;

import pdg5.common.Protocol;
import pdg5.common.game.Tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;
import java.util.stream.Stream;

/**
 * Created on 03.10.17 by Bykow
 */
public class TileStack {
    private Stack<Tile> stack = new Stack<>();
    private int size;
    private int tileLeft;
    private HashMap<Character, Integer> map = new HashMap<>();
    private Random r;

    /**
     * Ctor
     *
     * @param lang langage of the game
     */
    public TileStack(String lang) {
        size = 0;
        r = new Random();
        try {
            initStack(lang);
        } catch (Exception e) {
            e.printStackTrace();
        }

        tileLeft = size;
    }

    private TileStack(TileStack stack) {
        this.stack = stack.stack;
        this.size = stack.size;
        this.tileLeft = stack.tileLeft;
        this.map = stack.map;
        this.r = stack.r;
    }

    /**
     * Initialize both HashMap of letter with values and stack of Tuiles for a game.
     *
     * @param lang langage of the game
     * @throws IOException
     */
    private void initStack(String lang) {
        ClassLoader classLoader = getClass().getClassLoader();
        Stream<String> lines = null;

        InputStream inputStream = TileStack.class.getResourceAsStream("/dico/" + lang + "_stackInit.txt");
        new BufferedReader(new InputStreamReader(inputStream)).lines()
                // Fills the map with letter and values. '0' stands for a joker
                .peek(s -> map.put(s.charAt(0), Integer.parseInt(s.substring(5,7))))
                // For each line of the config file, adds the number of letters to stack
                .forEach(s -> {
                    for (int i = Integer.parseInt(s.substring(2,4)); i > 0; i--) {
                        size++;
                        this.stack.push(new Tile(s.charAt(0), Integer.parseInt(s.substring(5,7))));
                    }
                });


        /*
        // Adds the missing 12 Tuiles to get the 114 Tuiles of a game. Picks at random the remaining
        for (int i = Protocol.NUMBER_OF_TUILES_PER_GAME-size; i > 0; i--) {
            char c = getRandChar();
            size++;
            stack.push(new Tile(c, map.get(c)));
        }
        */

        // Shuffles the stack
        Collections.shuffle(stack, r);
    }

    /**
     * Returns a random character between 'A' included and 'Z' included
     * @return char picked at random
     */
    private char getRandChar() {
        int low = (int)'A';   //ASCII 'A'
        int high = (int)'Z';  //ASCII 'Z'
        return (char)(r.nextInt(high + 1 - low) + low);
    }

    public Tile getNextTuile() {
        tileLeft--;
        return stack.pop();
    }

    public String convertToString() {
        TileStack stack = new TileStack(this);
        String output = new String();

        while(stack.tileLeft > 0) {
            output.concat(String.valueOf(stack.getNextTuile().getLetter()));
        }

        return output;
    }

    public int getSize() {
        return size;
    }

    public int getTileLeft() {
        return tileLeft;
    }
}
