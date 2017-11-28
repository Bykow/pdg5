/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg5.common;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Jimmy Verdasca
 */
public class WordCombinationsTest {

   private static TST dictionnary = new TST();

   public WordCombinationsTest() {
   }

   /**
    * fill the TST used as dictionnary because we need him to test this class
    *
    * @throws Exception if an error occure while filling (bad URI etc)
    */
   @BeforeClass
   public static void init() throws Exception {
      ClassLoader classLoader = TSTTest.class.getClassLoader();

      try (Stream<String> lines = Files.lines(Paths.get(classLoader.getResource("dico/fr_dico.dic").toURI()))) {
         lines.forEach(e -> dictionnary.put(e));
      }
   }

   /**
    * Test of getAllWordsFromLetters method, of class WordCombinations.
    *
    * @throws java.net.URISyntaxException
    * @throws java.io.IOException
    */
   @Test
   public void testGetAllWordsFromLetters() throws URISyntaxException, IOException {
      System.out.println("getAllWordsFromLetters");
      ClassLoader classLoader = TSTTest.class.getClassLoader();
      Path p = Paths.get(classLoader.getResource("dico/testFileWords.txt").toURI());
      Stream<String> lines = Files.lines(p);
      Stream<String> lines2 = Files.lines(p);

      WordCombinations wc = new WordCombinations(dictionnary);
      List<String> result = wc.getAllWordsFromLetters(lines.findFirst().get());
      
      List<String> wordNotFoundFromExpected = lines2.skip(1).filter((word) -> {
         return !result.contains(word) && dictionnary.contains(word);
      }).collect(Collectors.toList());

      assertTrue(
         "the algorithm missed some words"
         + ".\nThe words not found from Expected List : \n" + wordNotFoundFromExpected.toString(),
         wordNotFoundFromExpected.isEmpty()
      );

   }

}
