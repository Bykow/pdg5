package pdg5.server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Created on 03.10.17 by Bykow
 */
public class FRLetterMap {
    private HashMap<Character, Integer> map;

    private FRLetterMap() {

        String fileName = "../resources/FR_stackInit.txt";
        try {
            readFile(fileName).forEach(s -> map.put(s.charAt(0), Integer.parseInt(s.substring(5,6))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static FRLetterMap FRLetterMap = new FRLetterMap();

    public static FRLetterMap getInstance() {
        return FRLetterMap;
    }

    protected Stream<String> readFile(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName));
    }
}
