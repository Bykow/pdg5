/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg5.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
      String letters = "";
      List<String> expResult = new ArrayList<String>();
      File file = new File(".\\src\\test\\resources\\testFileWords.txt");
      try (BufferedReader br = new BufferedReader(new FileReader(file))) {
         String line = br.readLine();
         letters = line;
         while ((line = br.readLine()) != null) {
            expResult.add(line);
      }
}     catch (IOException ex) {
         fail("impossible to read the result file");
      }
      WordCombinations wc = new WordCombinations(tree);
      List<String> result = wc.getAllWordsFromLetters(letters);
      for (Iterator<String> iterator = result.iterator(); iterator.hasNext();) {
         String next = iterator.next();
         
      }
      boolean pass = true;
      if (expResult.size() != result.size()) {
         pass = false;
      }
      List<String> wordNotFoundFromResult = new ArrayList<>();
      for (String word : result) {
         if(!expResult.contains(word)) {
            pass = false;
            wordNotFoundFromResult.add(word);
         }
      }
      
      List<String> wordNotFoundFromExpected = new ArrayList<>();
      for (String word : expResult) {
         if(!result.contains(word)) {
            pass = false;
            wordNotFoundFromExpected.add(word);
         }
      }
      if(!pass) {
         System.out.println("the numbers of word found (" + result.size() + ") isn't correct should be : " + expResult.size() + 
                 ".\nThe words not found from Expected List : \n" + wordNotFoundFromExpected.toString() +
                 ".\nThe words not found from result List : \n" + wordNotFoundFromResult.toString());
         fail();
      }
   }
   
}
