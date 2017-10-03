package pdg5.server;

import pdg5.common.Protocol;
import pdg5.common.game.Tuile;

import java.io.IOException;
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
public class TuileStack {
    private Stack<Tuile> stack = new Stack<>();
    private HashMap<Character, Integer> map = new HashMap<>();
    private Random r;

    /**
     * Ctor
     *
     * @param lang langage of the game
     */
    TuileStack(String lang) {
        r = new Random();
        try {
            initStack(lang);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize both HashMap of letter with values and stack of Tuiles for a game.
     *
     * @param lang langage of the game
     * @throws IOException
     */
    private void initStack(String lang) throws IOException {
        String fileName = "../resources/" + lang + "_stackInit.txt";
        readFile(fileName)
                .forEach(s -> {
                    // Fills the map with letter and values. '0' stands for a joker
                    map.put(s.charAt(0), Integer.parseInt(s.substring(5,6)));

                    // For each line of the config file, adds the number of letters to stack
                    for (int i = Integer.parseInt(s.substring(2,3)); i < 0; i--) {
                        stack.push(new Tuile(s.charAt(0), Integer.parseInt(s.substring(5,6))));
                    }
                });

        // Adds the missing 12 Tuiles to get the 114 Tuiles of a game. Picks at random the remaining
        for (int i = Protocol.NUMBER_OF_TUILES_PER_GAME - stack.size(); i > 0; i--) {
            char c = getRandChar();
            stack.push(new Tuile(c, map.get(c)));
        }

        // Shuffles the stack
        Collections.shuffle(stack, r);
    }

    /**
     * Open and return a stream of the file
     *
     * @param fileName config file for a given langage
     * @return stream of Strings
     * @throws IOException
     */
    private Stream<String> readFile(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName));
    }

    /**
     * Returns a random character between 'A' included and 'Z' included
     * @return char picked at random
     */
    private char getRandChar() {
        int low = 65;   //ASCII 'A'
        int high = 91;  //ASCII 'Z' + 1
        return (char)(r.nextInt(high-low) + low);
    }
}
