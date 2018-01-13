package pdg5.server.model;

import pdg5.common.game.Tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;
import java.util.stream.Stream;

/**
 * represent a stack of letters (Tiles)
 */
public class TileStack {

   /**
    * stack of the Tiles
    */
    private Stack<Tile> stack = new Stack<>();
    
    /**
     * the full size of the stack
     */
    private int size;
    
    /**
     * the current size of the stack
     */
    private int tileLeft;
    
    /**
     * map who link a caracter to his value
     */
    private HashMap<Character, Integer> map = new HashMap<>();
    
    /**
     * random used to shuffle the stack or pick randomly Tiles in
     */
    private final Random r;

    /**
     * Constructor
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

    /**
     * Constructor of copy
     *
     * @param stack the TileStack we want to copy
     */
    private TileStack(TileStack stack) {
        this.stack = (Stack<Tile>) stack.stack.clone();
        this.size = stack.size;
        this.tileLeft = stack.tileLeft;
        this.map = new HashMap<>(stack.map);
        this.r = stack.r;
    }

    /**
     * Constructor
     * 
     * @param lang langage of the game
     * @param letters initial letters of the stack
     */
    public TileStack(String lang, String letters) {
        r = new Random();
        size = 0;
        ClassLoader classLoader = getClass().getClassLoader();
        Stream<String> lines = null;

        InputStream inputStream = TileStack.class.getResourceAsStream("/dico/" + lang + "_stackInit.txt");
        new BufferedReader(new InputStreamReader(inputStream)).lines()
            // Fills the map with letter and values. '0' stands for a joker
            .forEach(s -> map.put(s.charAt(0), Integer.parseInt(s.substring(5, 7))));

        letters.chars()
            .mapToObj(c -> (char) c)
            .map((Character c) -> new Tile((char) c, map.get(c)))
            .forEach((t) -> {
                size++;
                stack.push(new Tile(t));
            });

        tileLeft = size;
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
            .peek(s -> map.put(s.charAt(0), Integer.parseInt(s.substring(5, 7))))
            // For each line of the config file, adds the number of letters to stack
            .forEach(s -> {
                for (int i = Integer.parseInt(s.substring(2, 4)); i > 0; i--) {
                    size++;
                    this.stack.push(new Tile(s.charAt(0), Integer.parseInt(s.substring(5, 7))));
                }
            });

        // Shuffles the stack
        Collections.shuffle(stack, r);
    }

    /**
     * Returns a random character between 'A' included and 'Z' included
     *
     * @return char picked at random
     */
    private char getRandChar() {
        int low = (int) 'A';   //ASCII 'A'
        int high = (int) 'Z';  //ASCII 'Z'
        return (char) (r.nextInt(high + 1 - low) + low);
    }

    /**
     * get the top Tuile of the stack and update the size
     *
     * @return the top Tile of the Stack
     */
    public Tile getNextTuile() {
        tileLeft--;
        return stack.pop();
    }

    /**
     * Empties the stack
     */
    public void clear() {
        stack.clear();
        tileLeft = 0;
    }

    /**
     * return the a String reprsenting all the letters left in the TileStack.
     *
     * @return the a String reprsenting all the letters left in the TileStack
     */
    public String convertToString() {
        TileStack localStack = new TileStack(this);
        String output = new String();

        while (localStack.tileLeft > 0) {
            output = output.concat(String.valueOf(localStack.getNextTuile().getLetter()));
        }

        return output;
    }

    /**
     * return the full initial size of the stack
     * 
     * @return the full initial size of the stack
     */
    public int getSize() {
        return size;
    }

    /**
     * return the current size of the stack
     * 
     * @return the current size of the stack
     */
    public int getTileLeft() {
        return tileLeft;
    }
}
