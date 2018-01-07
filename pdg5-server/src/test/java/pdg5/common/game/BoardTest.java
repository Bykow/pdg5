/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg5.common.game;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author Miguel-Portable
 */
public class BoardTest {
   
   Board board;
   Composition comp;
   GameModel game;
   
   public BoardTest() {
   }
   
   @Before
   public void setUp() {
      Board[] boards = new Board[]{new Board("Test1", 13), new Board("Test2", 17)};

      game = new GameModel(boards, 5, new Date(), 0);
      board = game.getBoard(GameModel.PlayerBoard.PLAYER1);
      comp = board.getComposition();
      
      comp.setSquare(new Composition.Square[]{
         Composition.Square.NORMAL,
         Composition.Square.DOUBLE,
         Composition.Square.NORMAL,
         Composition.Square.NORMAL,
         Composition.Square.TRIPLE,
         Composition.Square.NORMAL,
         Composition.Square.BONUS
      });
   }
   
   @Test
   public void itShouldReturnACorrectValue() {
      comp.push(new Tile('A', 1));
      comp.push(new Tile('A', 2));
      comp.push(new Tile('A', 1));
      comp.push(new Tile('A', 1));
      comp.push(new Tile('A', 2));
      comp.push(new Tile('A', 1));
      comp.push(new Tile('A', 1));

      int val = board.getValue();
      assert val == 50;

      List<Tile> bonus = new ArrayList<>();
      bonus.add(new Tile('C', 2));
      board.setBonus(bonus);

      val = board.getValue();
      assert val == 23;
   }

   @Test
   public void anIncompleteWordShouldThrowIllegalStateException() {
      comp.push(new Tile('A', 1));
      comp.push(new Tile('A', 2));
      comp.push(new Tile('A', 1));
      comp.push(new Tile('A', 1));
      comp.push(new Tile('A', 2));

      comp.remove(2);

      IllegalStateException e = Assertions.assertThrows(
        IllegalStateException.class, () -> {
           board.getValue();
        }
      );
      
      assert(e.getMessage().contains("This isn't a valid word"));
   }

   @Test
   public void aWordTooShortShouldThrowIllegalStateException() {
      comp.push(new Tile('A', 1));

      IllegalStateException e = Assertions.assertThrows(
        IllegalStateException.class, () -> {
           board.getValue();
        }
      );
      
      assert(e.getMessage().contains("This isn't a valid word"));
   }
   
   @After
   public void tearDown() {
   }

   @Test
   public void testSomeMethod() {
   }
   
}
