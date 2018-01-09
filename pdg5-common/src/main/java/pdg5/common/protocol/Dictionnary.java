/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg5.common.protocol;

import pdg5.common.WordCombinations;

/**
 * Class sent through the connection after a log in or log up
 * it contains the wordCombination 
 * that allow the client to check by his own the validity of the word
 * 
 * @author Jimmy Verdasca
 */
public class Dictionnary extends Message {
   
   /**
    * object that allow to check by his own the validity of a word
    */
   private WordCombinations wordCombinations;
   
   /**
    * Constructor
    * 
    * @param wordCombinations object that allow to check by his own the validity of a word
    */
   public Dictionnary(WordCombinations wordCombinations) {
      this.wordCombinations = wordCombinations;
   }

   /**
    * return the object that allow to check by his own the validity of a word
    * @return the object that allow to check by his own the validity of a word
    */
   public WordCombinations getWordCombinations() {
      return wordCombinations;
   }
   
   
}
