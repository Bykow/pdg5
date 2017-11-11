package pdg5.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class WordCombinations {

   TST dictionnary;
   StringBuilder sb;
   Map<Character, Integer> letterMap;
   List<String> listOfWords;

   public WordCombinations(TST dictionnary) {
      this.dictionnary = dictionnary;
   }

   public List<String> getAllWordsFromLetters(String letters) {
      listOfWords = new ArrayList<>();
      sb = new StringBuilder();
      letterMap = new HashMap<>(letters.length());
      
      for (char c : letters.toCharArray()) {
         Integer val = letterMap.putIfAbsent(c, 1);
         if(val != null){
            letterMap.replace(c, val + 1);
         }
      }
      
      search(letters.length());
      return listOfWords;
   }

   private void search(final int length) {
      if (length > 0) {
         letterMap.entrySet().forEach((entry) -> {
            Character letter = entry.getKey();
            Integer number = entry.getValue();

            if (number > 0) {
               sb.append(letter);
               if (sb.length() >= 2 && dictionnary.contains(sb.toString())) {
                  listOfWords.add(sb.toString());
               }
               
               entry.setValue(number - 1);
               search(length - 1);
               entry.setValue(number);
            }
         });
      }
   }

}
