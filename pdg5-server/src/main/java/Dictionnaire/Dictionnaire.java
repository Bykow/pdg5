package Dictionnaire;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxime Guillod
 */
public class Dictionnaire {

    private TreeSet<String> dico;

    public Dictionnaire() {

        BufferedReader br = null;
        FileReader fr = null;
        dico = new TreeSet<>();

        try {
            fr = new FileReader("../Dico/french.dic");
            br = new BufferedReader(fr);

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                dico.add(sCurrentLine);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dictionnaire.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Dictionnaire.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Dictionnaire.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean  contrain(String wordTested) {
        return dico.contains(wordTested);
    }

}
