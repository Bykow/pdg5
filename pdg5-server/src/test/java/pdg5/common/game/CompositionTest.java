/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg5.common.game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pdg5.common.game.Composition.Square;

import java.util.Date;

/**
 *
 * @author Miguel-Portable
 */
public class CompositionTest {

   Composition comp;
   GameModel game;

   public CompositionTest() {

      Board[] boards = new Board[]{new Board("Test1", 13), new Board("Test2", 17)};

      game = new GameModel(boards, 5, new Date(), 0);
      comp = new Composition();
   }

   @Before
   public void setUp() {
      comp.setSquare(new Square[]{
         Square.NORMAL,
         Square.DOUBLE,
         Square.NORMAL,
         Square.NORMAL,
         Square.TRIPLE,
         Square.NORMAL,
         Square.BONUS
      });
   }

   @After
   public void tearDown() {
   }
   
   @Test
   public void aBadBonusArraySizeShouldThrowIllegalArgumentException(){
      // TODO
   }
   
}
