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
    
    Dictionary dico;
    
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
        dico = new Dictionary();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void containTrue() {
        assertTrue(dico.contain("maison"));
        assertTrue(dico.contain("chatte"));
        assertTrue(dico.contain("salut"));
        assertTrue(dico.contain("zzzz"));
    }

    @Test
    public void containFalse() {
        assertFalse(dico.contain("zzz"));
        assertFalse(dico.contain("tony"));
    }
    
}
