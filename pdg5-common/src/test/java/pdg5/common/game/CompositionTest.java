/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg5.common.game;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.Assert;
import pdg5.common.game.Composition.Square;

/**
 *
 * @author Miguel-Portable
 */
public class CompositionTest {

   Composition comp;
   GameModel game;

   public CompositionTest() {

      Board[] boards = new Board[]{new Board("Test1", 13), new Board("Test2", 17)};

      game = new GameModel(boards, 5);
      comp = new Composition();
   }

   @Before
   public void setUp() {
      comp.setBonus(new Square[]{
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
   public void itShouldReturnACorrectValue() {
      comp.push(new Tile('A', 1));
      comp.push(new Tile('A', 2));
      comp.push(new Tile('A', 1));
      comp.push(new Tile('A', 1));
      comp.push(new Tile('A', 2));
      comp.push(new Tile('A', 1));
      comp.push(new Tile('A', 1));

      int val = comp.getValue(game.getBoard(GameModel.PlayerBoard.PLAYER1));
      assert val == 50;

      List<Tile> bonus = new ArrayList<>();
      bonus.add(new Tile('C', 2));
      game.getBoard(GameModel.PlayerBoard.PLAYER1).setBonus(bonus);

      val = comp.getValue(game.getBoard(GameModel.PlayerBoard.PLAYER1));
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
           comp.getValue(game.getBoard(GameModel.PlayerBoard.PLAYER1));
        }
      );
      
      assert(e.getMessage().contains("This isn't a valid word"));
   }

   @Test
   public void aWordTooShortShouldThrowIllegalStateException() {
      comp.push(new Tile('A', 1));

      IllegalStateException e = Assertions.assertThrows(
        IllegalStateException.class, () -> {
           comp.getValue(game.getBoard(GameModel.PlayerBoard.PLAYER1));
        }
      );
      
      assert(e.getMessage().contains("This isn't a valid word"));
   }
}
