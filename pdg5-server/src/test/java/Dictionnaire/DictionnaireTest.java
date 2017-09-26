package Dictionnaire;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Maxime Guillod
 */
public class DictionnaireTest {
    
    Dictionnaire dico;
    
    public DictionnaireTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dico = new Dictionnaire();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void containTrue() {
        assertTrue(dico.contrain("maison"));
        assertTrue(dico.contrain("chatte"));
        assertTrue(dico.contrain("salut"));
        assertTrue(dico.contrain("zzzz"));
    }

    @Test
    public void containFalse() {
        assertFalse(dico.contrain("zzz"));
        assertFalse(dico.contrain("tony"));
    }
    
}
