package Dictionnaire;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class representing a dictionary of all the french word.
 * We can just search a word on it.
 */
public class Dictionary {

    private TreeSet<String> dico;
    //path to the dictionary file
    private String path = "../Dico/french.dic";

    /**
     * Constructor
     * Build a dictionary (TreeSet of String) containing all the french words.
     * All the word doesn't contain accent.
     */
    public Dictionary() {

        BufferedReader br = null;
        FileReader fr = null;
        dico = new TreeSet<>();

        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                dico.add(sCurrentLine);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * 
     * @param wordTested
     * @return true if wordTested is contained in the dictionnary
     */
    public boolean  contain(String wordTested) {
        return dico.contains(wordTested);
    }

}
