package pdg5.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class who allow to search with a low complexity
 * all the combinations of word contained in our dictionnary
 */
public class WordCombinations {

    private final char JOKER = '?';
    private final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    private final TST dictionnary;
    private StringBuilder sb;
    private Map<Character, Integer> letterMap;
    private List<String> listOfWords;

    public WordCombinations(TST dictionnary) {
        this.dictionnary = dictionnary;
    }

    /**
     * get all possible words we can do with some letters
     *
     * @param letters in a String
     * @return a List of String representing all the possible words
     */
    public List<String> getAllWordsFromLetters(String letters) {
        listOfWords = new ArrayList<>();
        sb = new StringBuilder();
        letterMap = new HashMap<>(letters.length());

        for (char c : letters.toCharArray()) {
            Integer val = letterMap.putIfAbsent(c, 1);
            if (val != null) {
                letterMap.replace(c, val + 1);
            }
        }

        search(letters.length());
        return listOfWords;
    }

    /**
     * Search with the occurence map letterMap,
     * all the possible word of length lower than the given length.
     * if a word is found, we put him in the List listOfWord.
     *
     * @param length Max number of letters we use.
     */
    private void search(final int length) {
        if (length > 0) {
            letterMap.entrySet().forEach((entry) -> {
                Character letter = entry.getKey();
                Integer number = entry.getValue();

                if (number > 0) {
                    String lettersToTest;
                    if (letter == JOKER) {
                        lettersToTest = ALPHABET;
                    } else {
                        lettersToTest = letter.toString();
                    }

                    for (char c : lettersToTest.toCharArray()) {
                        sb.append(c);
                        if (sb.length() >= 2 && dictionnary.contains(sb.toString())) {
                            listOfWords.add(sb.toString());
                        }

                        entry.setValue(number - 1);
                        search(length - 1);
                        entry.setValue(number);

                        sb.deleteCharAt(sb.length() - 1);
                    }
                }
            });
        }
    }

}
