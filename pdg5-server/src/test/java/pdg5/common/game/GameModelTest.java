package pdg5.common.game;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel-Portable
 */
public class GameModelTest {
   
   Board[] boards;
   GameModel model;
   
   char charCount = 'a';
   int integerCount = 1;
   
   private List<Tile> getUniqueLettersList(int number){
      List<Tile> list;
      list = new ArrayList<>();
      for (int j = 0; j < number; j++) {
         list.add(new Tile(charCount++, integerCount++));
      }
      
      return list;
   }
   
   public GameModelTest() {
      
   }
   
   @Before
   public void setUp() {
      Board board1 = new Board("Bob", 4);
      Board board2 = new Board("Alice", 7);
      
      board1.setLetters(getUniqueLettersList(7));      
      board2.setLetters(getUniqueLettersList(7));
      
      board1.setScore(45);
      board2.setScore(67);
      
      board1.setBonus(getUniqueLettersList(2));
      board2.setBonus(getUniqueLettersList(2));
      
      boards = new Board[]{board1, board2};
      model = new GameModel(boards, 42, new Date(), 0);
   }
   
   @After
   public void tearDown() {
   }
   
   @Test
   public void theGameModelShouldBeCorrectlySerializable(){
      try {
         new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(model);
         
         Serializable original = model;
         Serializable copy = SerializationUtils.clone(original);
         
         assert(original.equals(copy));
      } catch (NotSerializableException ex) {
         fail(ex.toString());
      } catch(IOException ex){
         fail(ex.toString());
      }
   }
   
}
