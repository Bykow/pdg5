package pdg5.common;

import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

/**
 * Created on 10.10.17 by Bykow
 */
public class TSTTest {
    private static TST tree = new TST();

    @BeforeClass
    public static void init() throws Exception {
        long start = System.nanoTime();
        ClassLoader classLoader = TSTTest.class.getClassLoader();

        Stream<String> lines = Files.lines(Paths.get(classLoader.getResource("dico/fr_dico.dic").toURI()));
        lines.forEach(e -> tree.put(e));
        lines.close();

        long stop = System.nanoTime();
        System.out.println("Created in : " + ((stop - start) / 1000000 ) + " ms");
    }

    @Test
    public void put() throws Exception {
        tree.put("derp123");
        assertTrue(tree.contains("derp123"));
    }

    @Test
    public void contains() throws Exception {
        assertTrue(tree.contains("ab"));
        assertTrue(tree.contains("aigle"));
    }
}