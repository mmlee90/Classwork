import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class GreedyPlayerTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("GreedyPlayerTests");
  }

  // Some saveString() boards
  public static final String tiny =
    "1 3 2 \n"+
    "1 1 1 \n"+
    "1 2 2 \n"+
    "";
  public static final String skinny =
    "3 3 2 \n"+
    "3 2 3 \n"+
    "1 2 1 \n"+
    "2 2 2 \n"+
    "2 1 3 \n"+
    "1 1 2 \n"+
    "2 1 1 \n"+
    "";
  public static final String cross = 
    "1 1 2 1 1\n"+
    "1 1 2 1 1\n"+
    "2 2 2 2 2\n"+
    "1 1 2 1 1\n"+
    "1 1 2 1 1\n"+
    "";
  public static final String almostCross = 
    "1 1 2 1 1\n"+
    "1 1 2 1 1\n"+
    "2 2 1 2 2\n"+
    "1 1 2 1 1\n"+
    "1 1 2 1 1\n"+
    "";
  public static final String large =
    "4 3 5 3 5 1 3 3 1 1\n"+
    "2 4 1 4 1 4 4 5 4 2\n"+
    "5 3 5 2 3 3 3 1 3 4\n"+
    "1 2 2 2 2 4 2 4 5 1\n"+
    "1 3 1 2 3 3 3 4 1 1\n"+
    "2 2 2 1 5 1 5 5 3 5\n"+
    "2 4 1 5 2 2 5 4 5 2\n"+
    "";

  // Take an array of {score,boardString} and make successive moves in
  // the given game to check that the player playing the game makes
  // the right moves.
  public static void testMoves(Game g, Object [][] expects){
    for(int i=0; i<expects.length; i++){
      Object [] expecti = expects[i];
      String msg = "";
      msg += String.format("Move #%d wrong for player %s\n",i,g.getPlayer().toString());
      msg += String.format("Before: Score=%d\n%s\n",g.getScore(),g.getBoard().toString());
      int beforeScore = g.getScore();
      String beforeBoard = g.toString();
      int expectScore = (Integer) expecti[0];
      String expectBoard = (String) expecti[1];
      msg += String.format("Expect: Score=%d\n%s\n",expectScore,expectBoard);
      g.play1();
      int actualScore = g.getScore();
      String actualBoard = g.getBoard().toString();
      msg += String.format("Actual: Score=%d\n%s\n",actualScore,actualBoard);

      assertEquals(msg, expectScore, actualScore);
      assertEquals(msg, expectBoard, actualBoard);
    }    
  }

  @Test(timeout=2000) public void greedy_adjacent_tiny () {
    testMoves(new Game(Board.fromSaveString(tiny),
                       new AdjacentRemovalPolicy(),
                       new GreedyPlayer()),
              new Object[][]{
                {16,
                 "     0  1  2\n"+
                 "   ---------\n"+
                 " 0|     2    \n"+
                 " 1|  3  1    \n"+
                 " 2|  2  2    \n"+
                 ""},
                {20,
                 "     0  1  2\n"+
                 "   ---------\n"+
                 " 0|          \n"+
                 " 1|     2    \n"+
                 " 2|  3  1    \n"+
                 ""},
                {21,
                 "     0  1  2\n"+
                 "   ---------\n"+
                 " 0|          \n"+
                 " 1|          \n"+
                 " 2|  3  1    \n"+
                 ""},
                {22,
                 "     0  1  2\n"+
                 "   ---------\n"+
                 " 0|          \n"+
                 " 1|          \n"+
                 " 2|  1       \n"+
                 ""},
                {23,
                 "     0  1  2\n"+
                 "   ---------\n"+
                 " 0|          \n"+
                 " 1|          \n"+
                 " 2|          \n"+
                 ""},
              });
  }

  @Test(timeout=2000) public void greedy_adjacent_cross () {
    testMoves(new Game(Board.fromSaveString(cross),
                       new AdjacentRemovalPolicy(),
                       new GreedyPlayer()),
              new Object[][]{
                {25,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|  1           1 \n"+
                 " 1|  1  1     1  1 \n"+
                 " 2|  2  1     1  2 \n"+
                 " 3|  1  1  2  1  1 \n"+
                 " 4|  1  1  2  1  1 \n"+
                 ""},
                {41,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|              1 \n"+
                 " 1|  1        1  1 \n"+
                 " 2|  1        1  2 \n"+
                 " 3|  2     2  1  1 \n"+
                 " 4|  1  1  2  1  1 \n"+
                 ""},
                {57,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|                \n"+
                 " 1|  1           1 \n"+
                 " 2|  1           1 \n"+
                 " 3|  2     2     2 \n"+
                 " 4|  1  1  2  1  1 \n"+
                 ""},
                {61,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|                \n"+
                 " 1|              1 \n"+
                 " 2|              1 \n"+
                 " 3|  2     2     2 \n"+
                 " 4|  1  1  2  1  1 \n"+
                 ""},
                {65,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|                \n"+
                 " 1|                \n"+
                 " 2|                \n"+
                 " 3|  2     2     2 \n"+
                 " 4|  1  1  2  1  1 \n"+
                 ""},
              });
  }

  @Test(timeout=2000) public void greedy_wholerow_tiny () {
    testMoves(new Game(Board.fromSaveString(tiny),
                       new WholeRowColRemovalPolicy(),
                       new GreedyPlayer()),
              new Object[][]{
                {25,
                 "     0  1  2\n"+
                 "   ---------\n"+
                 " 0|          \n"+
                 " 1|  3  2    \n"+
                 " 2|  2  2    \n"+
                 ""},
                {34,
                 "     0  1  2\n"+
                 "   ---------\n"+
                 " 0|          \n"+
                 " 1|          \n"+
                 " 2|  3       \n"+
                 ""},
                {35,
                 "     0  1  2\n"+
                 "   ---------\n"+
                 " 0|          \n"+
                 " 1|          \n"+
                 " 2|          \n"+
                 ""},
              });
  }
  

  @Test(timeout=2000) public void greedy_wholerow_cross () {
    testMoves(new Game(Board.fromSaveString(cross),
                       new WholeRowColRemovalPolicy(),
                       new GreedyPlayer()),
              new Object[][]{
                {81,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|                \n"+
                 " 1|  1  1  1  1    \n"+
                 " 2|  1  1  1  1    \n"+
                 " 3|  1  1  1  1    \n"+
                 " 4|  1  1  1  1    \n"+
                 ""},
                {130,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|                \n"+
                 " 1|                \n"+
                 " 2|  1  1  1       \n"+
                 " 3|  1  1  1       \n"+
                 " 4|  1  1  1       \n"+
                 ""},
                {155,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|                \n"+
                 " 1|                \n"+
                 " 2|                \n"+
                 " 3|  1  1          \n"+
                 " 4|  1  1          \n"+
                 ""},
                {164,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|                \n"+
                 " 1|                \n"+
                 " 2|                \n"+
                 " 3|                \n"+
                 " 4|  1             \n"+
                 ""},
                {165,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|                \n"+
                 " 1|                \n"+
                 " 2|                \n"+
                 " 3|                \n"+
                 " 4|                \n"+
                 ""},
              });
  }
  
  @Test(timeout=2000) public void greedy_adjacent_almostcross () {
    testMoves(new Game(Board.fromSaveString(almostCross),
                       new AdjacentRemovalPolicy(),
                       new GreedyPlayer()),
              new Object[][]{
                {9,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|        2  1  1 \n"+
                 " 1|     1  2  1  1 \n"+
                 " 2|  2  2  1  2  2 \n"+
                 " 3|  1  1  2  1  1 \n"+
                 " 4|  1  1  2  1  1 \n"+
                 ""},
                {18,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|        2       \n"+
                 " 1|     1  2     1 \n"+
                 " 2|  2  2  1  2  2 \n"+
                 " 3|  1  1  2  1  1 \n"+
                 " 4|  1  1  2  1  1 \n"+
                 ""},
                {27,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|        2       \n"+
                 " 1|        2     1 \n"+
                 " 2|     1  1  2  2 \n"+
                 " 3|     2  2  1  1 \n"+
                 " 4|  2  1  2  1  1 \n"+
                 ""},
                {36,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|                \n"+
                 " 1|              1 \n"+
                 " 2|        2  2  2 \n"+
                 " 3|     1  2  1  1 \n"+
                 " 4|  2  1  1  1  1 \n"+
                 ""},
                {52,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|                \n"+
                 " 1|                \n"+
                 " 2|              1 \n"+
                 " 3|     1  2     2 \n"+
                 " 4|  2  1  2  2  1 \n"+
                 ""},
                {61,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|                \n"+
                 " 1|                \n"+
                 " 2|        1       \n"+
                 " 3|     1  2       \n"+
                 " 4|  2  1  1       \n"+
                 ""},
              });
  }

  @Test(timeout=2000) public void greedy_wholerow_almostcross () {
    testMoves(new Game(Board.fromSaveString(almostCross),
                       new WholeRowColRemovalPolicy(),
                       new GreedyPlayer()),
              new Object[][]{
                {9,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|        2  1  1 \n"+
                 " 1|     1  2  1  1 \n"+
                 " 2|  2  2  1  2  2 \n"+
                 " 3|  1  1  2  1  1 \n"+
                 " 4|  1  1  2  1  1 \n"+
                 ""},
                {18,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|        2       \n"+
                 " 1|     1  2     1 \n"+
                 " 2|  2  2  1  2  2 \n"+
                 " 3|  1  1  2  1  1 \n"+
                 " 4|  1  1  2  1  1 \n"+
                 ""},
                {27,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|        2       \n"+
                 " 1|        2     1 \n"+
                 " 2|     1  1  2  2 \n"+
                 " 3|     2  2  1  1 \n"+
                 " 4|  2  1  2  1  1 \n"+
                 ""},
                {36,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|                \n"+
                 " 1|              1 \n"+
                 " 2|        2  2  2 \n"+
                 " 3|     1  2  1  1 \n"+
                 " 4|  2  1  1  1  1 \n"+
                 ""},
                {61,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|                \n"+
                 " 1|                \n"+
                 " 2|           1    \n"+
                 " 3|     2  2  2    \n"+
                 " 4|  2  2  1  1    \n"+
                 ""},
                {77,
                 "     0  1  2  3  4\n"+
                 "   ---------------\n"+
                 " 0|                \n"+
                 " 1|                \n"+
                 " 2|                \n"+
                 " 3|        1       \n"+
                 " 4|  2  1  1       \n"+
                 ""},
              });
  }
  

  @Test(timeout=2000) public void greedy_adjacent_large(){
    testMoves(new Game(Board.fromSaveString(large),
                       new AdjacentRemovalPolicy(),
                       new GreedyPlayer()),
              new Object[][]{
                {25,
                 "     0  1  2  3  4  5  6  7  8  9\n"+
                 "   ------------------------------\n"+
                 " 0|  4  3           1  3  3  1  1 \n"+
                 " 1|  2  4  5     5  4  4  5  4  2 \n"+
                 " 2|  5  3  1     1  3  3  1  3  4 \n"+
                 " 3|  1  2  5  3  3  4  2  4  5  1 \n"+
                 " 4|  1  3  1  4  3  3  3  4  1  1 \n"+
                 " 5|  2  2  2  1  5  1  5  5  3  5 \n"+
                 " 6|  2  4  1  5  2  2  5  4  5  2 \n"+
                 ""},
                {34,
                 "     0  1  2  3  4  5  6  7  8  9\n"+
                 "   ------------------------------\n"+
                 " 0|  4  3           1  3  3  1  1 \n"+
                 " 1|  2  4  5        4  4  5  4  2 \n"+
                 " 2|  5  3  1        3  3  1  3  4 \n"+
                 " 3|  1  2  5     5  4  2  4  5  1 \n"+
                 " 4|  1  3  1  4  1  3  3  4  1  1 \n"+
                 " 5|  2  2  2  1  5  1  5  5  3  5 \n"+
                 " 6|  2  4  1  5  2  2  5  4  5  2 \n"+
                 ""},
                {43,
                 "     0  1  2  3  4  5  6  7  8  9\n"+
                 "   ------------------------------\n"+
                 " 0|  4  3           1  3  3       \n"+
                 " 1|  2  4  5        4  4  5  1    \n"+
                 " 2|  5  3  1        3  3  1  4  1 \n"+
                 " 3|  1  2  5     5  4  2  4  3  2 \n"+
                 " 4|  1  3  1  4  1  3  3  4  5  4 \n"+
                 " 5|  2  2  2  1  5  1  5  5  3  5 \n"+
                 " 6|  2  4  1  5  2  2  5  4  5  2 \n"+
                 ""},
                {52,
                 "     0  1  2  3  4  5  6  7  8  9\n"+
                 "   ------------------------------\n"+
                 " 0|                 1  3  3       \n"+
                 " 1|     3  5        4  4  5  1    \n"+
                 " 2|  4  4  1        3  3  1  4  1 \n"+
                 " 3|  2  3  5     5  4  2  4  3  2 \n"+
                 " 4|  5  2  1  4  1  3  3  4  5  4 \n"+
                 " 5|  1  3  2  1  5  1  5  5  3  5 \n"+
                 " 6|  1  4  1  5  2  2  5  4  5  2 \n"+
                 ""},
                {61,
                 "     0  1  2  3  4  5  6  7  8  9\n"+
                 "   ------------------------------\n"+
                 " 0|                 1             \n"+
                 " 1|     3  5        4     3  1    \n"+
                 " 2|  4  4  1        3  3  5  4  1 \n"+
                 " 3|  2  3  5     5  4  4  1  3  2 \n"+
                 " 4|  5  2  1  4  1  3  3  4  5  4 \n"+
                 " 5|  1  3  2  1  5  1  2  4  3  5 \n"+
                 " 6|  1  4  1  5  2  2  3  4  5  2 \n"+
                 ""},
              });
  }
  
  @Test(timeout=2000) public void greedy_wholerow_large(){
    testMoves(new Game(Board.fromSaveString(large),
                       new WholeRowColRemovalPolicy(),
                       new GreedyPlayer()),
              new Object[][]{
                {36,
                 "     0  1  2  3  4  5  6  7  8  9\n"+
                 "   ------------------------------\n"+
                 " 0|  4              1  3  3  1  1 \n"+
                 " 1|  2  3  5     5  4  4  5  4  2 \n"+
                 " 2|  5  4  1     1  3  3  1  3  4 \n"+
                 " 3|  1  3  5  3  3  4  2  4  5  1 \n"+
                 " 4|  1  3  1  4  3  3  3  4  1  1 \n"+
                 " 5|  2  2  2  1  5  1  5  5  3  5 \n"+
                 " 6|  2  4  1  5  2  2  5  4  5  2 \n"+
                 ""},
                {52,
                 "     0  1  2  3  4  5  6  7  8  9\n"+
                 "   ------------------------------\n"+
                 " 0|  4                    3  1  1 \n"+
                 " 1|  2  3  5        1  3  5  4  2 \n"+
                 " 2|  5  4  1        4  4  1  3  4 \n"+
                 " 3|  1  3  5  3  5  3  3  4  5  1 \n"+
                 " 4|  1  3  1  4  1  4  2  4  1  1 \n"+
                 " 5|  2  2  2  1  5  1  5  5  3  5 \n"+
                 " 6|  2  4  1  5  2  2  5  4  5  2 \n"+
                 ""},
                {68,
                 "     0  1  2  3  4  5  6  7  8  9\n"+
                 "   ------------------------------\n"+
                 " 0|                       3  1  1 \n"+
                 " 1|                 1  3  5  4  2 \n"+
                 " 2|  4  3  5        4  4  1  3  4 \n"+
                 " 3|  2  4  1  3  5  3  3  4  5  1 \n"+
                 " 4|  5  3  5  4  1  4  2  4  1  1 \n"+
                 " 5|  1  3  1  1  5  1  5  5  3  5 \n"+
                 " 6|  1  4  1  5  2  2  5  4  5  2 \n"+
                 ""},
                {77,
                 "     0  1  2  3  4  5  6  7  8  9\n"+
                 "   ------------------------------\n"+
                 " 0|                       3       \n"+
                 " 1|                 1  3  5  1    \n"+
                 " 2|  4  3  5        4  4  1  4  1 \n"+
                 " 3|  2  4  1  3  5  3  3  4  3  2 \n"+
                 " 4|  5  3  5  4  1  4  2  4  5  4 \n"+
                 " 5|  1  3  1  1  5  1  5  5  3  5 \n"+
                 " 6|  1  4  1  5  2  2  5  4  5  2 \n"+
                 ""},
                {86,
                 "     0  1  2  3  4  5  6  7  8  9\n"+
                 "   ------------------------------\n"+
                 " 0|                       3       \n"+
                 " 1|                 1  3  5  1    \n"+
                 " 2|  4  3           4  4  1  4  1 \n"+
                 " 3|  2  4        5  3  3  4  3  2 \n"+
                 " 4|  5  3  5  3  1  4  2  4  5  4 \n"+
                 " 5|  1  3  1  4  5  1  5  5  3  5 \n"+
                 " 6|  1  4  5  5  2  2  5  4  5  2 \n"+
                 ""},
              });
  }

}
