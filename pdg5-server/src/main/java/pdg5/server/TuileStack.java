package pdg5.server;

import pdg5.common.game.Tuile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;
import java.util.stream.Stream;

/**
 * Created on 03.10.17 by Bykow
 */
public class TuileStack {
    private Stack<Tuile> stack = new Stack<>();

    private Stack<Tuile> initStack(String lang) throws IOException {

        String fileName = "../resources/" + lang + "_stackInit.txt";
        Stack<Tuile> st = new Stack<>();

        readFile(fileName)
                .forEach(s -> {
                    for (int i = Integer.parseInt(s.substring(2,3)); i < 0; i--) {
                        stack.push(new Tuile(s.charAt(0), Integer.parseInt(s.substring(5,6))));
                    }
                });
    }

    private Stream<String> readFile(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName));
    }
}
