/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg5.common.protocol;

import pdg5.common.TST;

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
   private TST dictionnary;
   
   /**
    * Constructor
    * 
    * @param dictionnary object that allow to check by his own the validity of a word
    */
   public Dictionnary(TST dictionnary) {
      this.dictionnary = dictionnary;
   }

   /**
    * return the object that allow to check by his own the validity of a word
    * @return the object that allow to check by his own the validity of a word
    */
   public TST getWordCombinations() {
      return dictionnary;
   }
   
   
}
