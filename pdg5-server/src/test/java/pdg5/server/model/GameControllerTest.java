/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg5.server.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pdg5.common.game.Board;
import pdg5.common.game.Composition;
import pdg5.common.protocol.ErrorMessage;
import pdg5.common.protocol.Game;
import pdg5.common.protocol.Message;
import pdg5.server.util.ServerActiveUser;

/**
 *
 * @author Jimmy Verdasca
 */
public class GameControllerTest {
   
   private GameController gameController;
   
   public GameControllerTest() {
   }
   
   @Before
   public void setUp() {
      gameController = new GameController(new ServerActiveUser());
   }

   /**
    * Test of newGame method, of class GameController.
    */
   @Test
   public void testAskNewGame() {
      System.out.println("askNewGame");
      
      int idPlayerAsking = 32;
      int idPlayerAsking2 = 16;
      
      Message result = gameController.newGame(idPlayerAsking);
      assertEquals(new ErrorMessage("Nous recherchons actuellement un adversaire"), result);
      
      Message result2 = gameController.newGame(idPlayerAsking2);
      assertTrue(result2 instanceof Game);
      
      assertEquals(((Game)result2).getScore(), 0); // score of player begin at 0
      assertEquals(((Game)result2).getAddedTile().size(), 7); // reveice 7 random Tiles
      assertEquals(((Game)result2).getBonusLetters().size(), 2); // reveice 2 random bonus Tiles
      assertNotNull(((Game)result2).getCreated()); // the Creation Date exist
      assertEquals(((Game)result2).getID(), 0); // the first ID game should be 0
      assertNotNull(((Game)result2).getLastActivity()); // the lastMove Date exist
      assertNotNull(((Game)result2).getNamePlayer()); // the player has a name
      assertEquals(((Game)result2).getNbLeftTile(), 101); // after distribution there is 101 Tile left
      assertNotNull(((Game)result2).getOpponentName()); // opponent has a name
      assertEquals(((Game)result2).getOpponentScore(), 0); // opponent start with a 0 score
   }

   /**
    * Test of initBoard method, of class GameController.
    */
   @Test
   public void testInitBoard() {
      System.out.println("initBoard");
      TileStack ts = null;
      int idPlayer = 0;
      Board expResult = null;
      Board result = gameController.initBoard(ts, idPlayer);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of findGamesOf method, of class GameController.
    */
   @Test
   public void testFindGamesOf() {
      System.out.println("findGamesOf");
      int idClient = 0;
      Message expResult = null;
      Message result = gameController.findGamesOf(idClient);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of validateComposition method, of class GameController.
    */
   @Test
   public void testValidateComposition() {
      System.out.println("validateComposition");
      Composition composition = null;
      Message expResult = null;
      Message result = gameController.validateComposition(composition);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of play method, of class GameController.
    */
   @Test
   public void testPlay() {
      System.out.println("play");
      int gameID = 0;
      int playerID = 0;
      Composition composition = null;
      Message expResult = null;
      Message result = gameController.play(gameID, playerID, composition);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }
   
}
