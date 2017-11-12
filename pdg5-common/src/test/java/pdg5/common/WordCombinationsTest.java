/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg5.common;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Jimmy Verdasca
 */
public class WordCombinationsTest {
   
   private static TST tree = new TST();
   
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

     Stream<String> lines = Files.lines(Paths.get(classLoader.getResource("dico/fr_dico.dic").toURI()));
     lines.forEach(e -> tree.put(e));
     lines.close();
   }

   /**
    * Test of getAllWordsFromLetters method, of class WordCombinations.
    */
   @Test
   public void testGetAllWordsFromLetters() {
      System.out.println("getAllWordsFromLetters");
      String letters = "aime";
      WordCombinations wc = new WordCombinations(tree);
      List<String> expResult = null;
      List<String> result = wc.getAllWordsFromLetters(letters);
      for (Iterator<String> iterator = result.iterator(); iterator.hasNext();) {
         String next = iterator.next();
         System.out.println(next);
         
      }
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }
   
}
