import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class MazeTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("MazeTests");
  }
  

  // Enable sorting of cheese list results
  static Comparator<Coord> rowcol = new Comparator<Coord>(){
      public int compare(Coord x, Coord y){
        int diff = x.row - y.row;
        if(diff != 0){ return diff; }
        return x.col - y.col;
      }
    };

  // Check that a board has the basic properties given
  public static void
    checkMaze(Maze maze, 
              int expectRows,
              int expectCols,
              String toString,
              Coord expectMouse,
              List<Coord> expectCheese,
              List<Coord> expectWalls,
              List<Coord> expectSpaces
              ) 
  {
    String msg =
      String.format("Expect Rows: %d\n",expectRows) +
      String.format("Expect Cols: %d\n",expectCols) +
      String.format("Expect Mouse:  %s\n",expectMouse) +
      String.format("Expect Cheese: %s\n",expectCheese) +
      String.format("Maze:\n%s\n",maze.toString());
    assertEquals("Rows Wrong\n"+msg, expectRows, maze.getRows());
    assertEquals("Cols Wrong\n"+msg, expectCols, maze.getCols());
    assertEquals("Mouse Wrong\n"+msg, expectMouse, maze.getMouseLocation());
    List<Coord> actualCheese = maze.getCheeseLocations();
    Collections.sort(actualCheese, rowcol);
    assertEquals("Cheese Wrong\n"+msg, expectCheese, actualCheese);
    assertEquals("String Wrong\n"+msg, toString, maze.toString());
  }

  // Convenience check which constructs the board from a string
  public static void
    checkMazeString(String input, 
              int expectRows,
              int expectCols,
              String toString,
              Coord expectMouse,
              List<Coord> expectCheese,
              List<Coord> expectWalls,
              List<Coord> expectSpaces
              ) throws Exception
  {
    checkMaze(new Maze(input),
              expectRows,
              expectCols,
              toString,
              expectMouse,
              expectCheese,
              expectWalls,
              expectSpaces
              );
  }  

  // Convenience check which constructs the board from a file
  public static void
    checkMazeFile(String input, String fname,
                  int expectRows,
                  int expectCols,
                  String toString,
                  Coord expectMouse,
                  List<Coord> expectCheese,
                  List<Coord> expectWalls,
                  List<Coord> expectSpaces
                  ) throws Exception
  {
    PrintWriter fout = new PrintWriter(fname);
    fout.print(input);
    fout.close();

    checkMaze(new Maze(new File(fname)),
              expectRows,
              expectCols,
              toString,
              expectMouse,
              expectCheese,
              expectWalls,
              expectSpaces
              );
  }  

  ///////////////////////////////////////////////////////////////////////////////// 
  // Tests for constructing mazes, ensure they have the proper properties
  @Test(timeout=2000) public void string_constructor1() throws Exception{
    String input =
      "M   C\n"+
      "";
    checkMazeString(input,
              1,                              // expectRows
              5,                              // expectCols
              input,                          // toString
              new Coord(0,0),                 // expectMouse 
              Arrays.asList(new Coord(0,4)),  // expectCheese
              Arrays.asList(),                // expectWalls
              Arrays.asList(new Coord(0,1),   // expectSpaces
                            new Coord(0,2))
              );
  }
  @Test(timeout=2000) public void string_constructor2() throws Exception{
    String input =
      "|||||||\n"+
      "|M   C|\n"+
      "|||||||\n"+
      "";
    checkMazeString(input,
              3,                              // expectRows
              7,                              // expectCols
              input,                          // toString
              new Coord(1,1),                 // expectMouse 
              Arrays.asList(new Coord(1,5)),  // expectCheese
              Arrays.asList(new Coord(0,0),   // expectWalls
                            new Coord(0,1),
                            new Coord(2,1),
                            new Coord(2,4),
                            new Coord(1,0),
                            new Coord(1,6)),
              Arrays.asList(new Coord(1,2),   // expectSpaces
                            new Coord(1,3))
              );
  }
  @Test(timeout=2000) public void string_constructor3() throws Exception{
    String input =
      "|||\n"+
      "|M|\n"+
      "| |\n"+
      "| |\n"+
      "|C|\n"+
      "| |\n"+
      "| |\n"+
      "|||\n"+
      "";
    checkMazeString(input,
              8,                              // expectRows
              3,                              // expectCols
              input,                          // toString
              new Coord(1,1),                 // expectMouse 
              Arrays.asList(new Coord(4,1)),  // expectCheese
              Arrays.asList(new Coord(0,0),   // expectWalls
                            new Coord(0,1),
                            new Coord(2,0),
                            new Coord(4,2),
                            new Coord(7,0),
                            new Coord(7,2)),
              Arrays.asList(new Coord(2,1),   // expectSpaces
                            new Coord(3,1),
                            new Coord(5,1),
                            new Coord(6,1))
              );
  }
  @Test(timeout=2000) public void string_constructor4() throws Exception{
    String input =
      "        ||||||||||||||\n"+
      "        |           C|\n"+
      "||||||||| ||||||||||||\n"+
      "|M                   |\n"+
      "||||||||||||||||||||||\n"+
      "";
    checkMazeString(input,
              5,                              // expectRows
              22,                             // expectCols
              input,                          // toString
              new Coord(3,1),                 // expectMouse 
              Arrays.asList(new Coord(1,20)), // expectCheese
              Arrays.asList(new Coord(0,8),   // expectWalls
                            new Coord(0,10),
                            new Coord(0,21),
                            new Coord(1,21),
                            new Coord(1,8),
                            new Coord(2,5),
                            new Coord(2,10)),
              Arrays.asList(new Coord(0,0),   // expectSpaces
                            new Coord(1,1),
                            new Coord(1,9),
                            new Coord(2,9),
                            new Coord(3,20))
              );
  }
  @Test(timeout=2000) public void string_constructor5() throws Exception{
    String input =
      "|||||||||||||||||||||||\n"+
      "|M                   ||\n"+
      "| ||| || || || || || ||\n"+
      "| ||| || || || ||C|| ||\n"+
      "| ||| || || || || || ||\n"+
      "|                    ||\n"+
      "|||||||||||||||||||||||\n"+
      "";
    checkMazeString(input,
              7,                              // expectRows
              23,                             // expectCols
              input,                          // toString
              new Coord(1,1),                 // expectMouse 
              Arrays.asList(new Coord(3,17)), // expectCheese
              Arrays.asList(new Coord(3,3),   // expectWalls
                            new Coord(3,4),
                            new Coord(3,16),
                            new Coord(2,21),
                            new Coord(2,22),
                            new Coord(6,15),
                            new Coord(7,22)),
              Arrays.asList(new Coord(1,2),   // expectSpaces
                            new Coord(3,5),
                            new Coord(4,11),
                            new Coord(6,13),
                            new Coord(6,20))
              );
  }
  @Test(timeout=2000) public void string_constructor6() throws Exception{
    String input =
      "||||||||||||||||||||||||||||||||||||\n"+
      "||        ||||||||||||      ||||||||\n"+
      "|| |||||| ||||||||M||| |||||||||||||\n"+
      "|| |||||| |||||||| ||| |||        ||\n"+
      "|| |||||| |||||||| ||| ||| |||||| ||\n"+
      "|| |               ||| ||| |||||| ||\n"+
      "|| ||||||||||||||| ||| ||| |||||| ||\n"+
      "|| |||||| ||| ||||          ||||| ||\n"+
      "|| |||||| ||| |||| |||||||||||||| ||\n"+
      "|| |||             |||||          ||\n"+
      "|||||||||||||||||||||||| |||||||| ||\n"+
      "|||C||                   |||      ||\n"+
      "||| ||||||||| |||||| |||||||||||||||\n"+
      "|||           ||||||               |\n"+
      "||||||||||||||||||||||||||||||||||||\n"+
      "";
    checkMazeString(input,
              15,                             // expectRows
              36,                             // expectCols
              input,                          // toString
              new Coord(2,18),                // expectMouse 
              Arrays.asList(new Coord(11,3)), // expectCheese
              Arrays.asList(new Coord(8,6),   // expectWalls
                            new Coord(7,19),
                            new Coord(3,16),
                            new Coord(13,19),
                            new Coord(1,28),
                            new Coord(13,35),
                            new Coord(11,2)),
              Arrays.asList(new Coord(5,2),   // expectSpaces
                            new Coord(3,5),
                            new Coord(3,18),
                            new Coord(12,20),
                            new Coord(13,34))
              );
  }
  @Test(timeout=2000) public void string_constructor7() throws Exception{
    String input =
      "        ||||||||||||||\n"+
      "        |           C|\n"+
      "||||||||| ||||||||||||\n"+
      "|M                  C|\n"+
      "||||||||||||||||||||||\n"+
      "";
    checkMazeString(input,
              5,                              // expectRows
              22,                             // expectCols
              input,                          // toString
              new Coord(3,1),                 // expectMouse 
              Arrays.asList(new Coord(1,20),  // expectCheese
                            new Coord(3,20)),
              Arrays.asList(new Coord(0,8),   // expectWalls
                            new Coord(0,10),
                            new Coord(0,21),
                            new Coord(1,21),
                            new Coord(1,8),
                            new Coord(2,5),
                            new Coord(2,10)),
              Arrays.asList(new Coord(0,0),   // expectSpaces
                            new Coord(1,1),
                            new Coord(1,9),
                            new Coord(2,9),
                            new Coord(3,20))
              );
  }
  @Test(timeout=2000) public void string_constructor8() throws Exception{
    String input =
      "|||||||||||||||\n"+
      "|C         C|||\n"+
      "|||| ||| ||||||\n"+
      "|||| |||     ||\n"+
      "|   M  C C|| ||\n"+
      "|C|| ||||||| ||\n"+
      "||||  C  |||C||\n"+
      "|||||||||||||||\n"+
      "";
    checkMazeString(input,
              8,                              // expectRows
              15,                             // expectCols
              input,                          // toString
              new Coord(4,4),                 // expectMouse 
              Arrays.asList(new Coord(1,1),  // expectCheese
                            new Coord(1,11),
                            new Coord(4,7),
                            new Coord(4,9),
                            new Coord(5,1),
                            new Coord(6,6),
                            new Coord(6,12)),
              Arrays.asList(new Coord(0,0),   // expectWalls
                            new Coord(1,0),
                            new Coord(2,6),
                            new Coord(5,8),
                            new Coord(5,2),
                            new Coord(5,14),
                            new Coord(7,14),
                            new Coord(0,14)),
              Arrays.asList(new Coord(1,2),   // expectSpaces
                            new Coord(2,8),
                            new Coord(2,4),
                            new Coord(4,6),
                            new Coord(6,8))
              );
  }
  @Test(timeout=2000) public void string_constructor9() throws Exception{
    String input =
      "||||||||||||||||||||||||\n"+
      "||           |||||||||||\n"+
      "||        C  |||||||||||\n"+
      "||           |||||||||||\n"+
      "||           |||||||||||\n"+
      "||||||| ||||||||||||||||\n"+
      "||||||| ||||||||||||||||\n"+
      "||||||| |||         ||||\n"+
      "||||||| |||   M     ||||\n"+
      "||||||| |||         ||||\n"+
      "|||||||                |\n"+
      "||||||||||||||||||||||||\n"+
      "";
    checkMazeString(input,
              12,                           // expectRows
              24,                           // expectCols
              input,                        // toString
              new Coord(8,14),              // expectMouse 
              Arrays.asList(new Coord(2,10)), // expectCheese
              Arrays.asList(new Coord(0,0),   // expectWalls
                            new Coord(3,13),
                            new Coord(2,6),
                            new Coord(5,8),
                            new Coord(5,6),
                            new Coord(9,10),
                            new Coord(9,20),
                            new Coord(11,23)),
              Arrays.asList(new Coord(10,22),   // expectSpaces
                            new Coord(2,8),
                            new Coord(10,13),
                            new Coord(9,7),
                            new Coord(4,2),
                            new Coord(1,2),
                            new Coord(2,9))
              );
  }

  ////////////////////////////////////////////////////////////////////////////////
  @Test(timeout=2000) public void file_constructor1() throws Exception{
    String input =
      "M   C\n"+
      "";
    checkMazeFile(input,"simple1-test.txt",
              1,                              // expectRows
              5,                              // expectCols
              input,                          // toString
              new Coord(0,0),                 // expectMouse 
              Arrays.asList(new Coord(0,4)),  // expectCheese
              Arrays.asList(),                // expectWalls
              Arrays.asList(new Coord(0,1),   // expectSpaces
                            new Coord(0,2))
              );
  }
  @Test(timeout=2000) public void file_constructor2() throws Exception{
    String input =
      "|||||||\n"+
      "|M   C|\n"+
      "|||||||\n"+
      "";
    checkMazeFile(input,"simple2-test.txt",
              3,                              // expectRows
              7,                              // expectCols
              input,                          // toString
              new Coord(1,1),                 // expectMouse 
              Arrays.asList(new Coord(1,5)),  // expectCheese
              Arrays.asList(new Coord(0,0),   // expectWalls
                            new Coord(0,1),
                            new Coord(2,1),
                            new Coord(2,4),
                            new Coord(1,0),
                            new Coord(1,6)),
              Arrays.asList(new Coord(1,2),   // expectSpaces
                            new Coord(1,3))
              );
  }
  @Test(timeout=2000) public void file_constructor3() throws Exception{
    String input =
      "|||\n"+
      "|M|\n"+
      "| |\n"+
      "| |\n"+
      "|C|\n"+
      "| |\n"+
      "| |\n"+
      "|||\n"+
      "";
    checkMazeFile(input,"simple3-test.txt",
              8,                              // expectRows
              3,                              // expectCols
              input,                          // toString
              new Coord(1,1),                 // expectMouse 
              Arrays.asList(new Coord(4,1)),  // expectCheese
              Arrays.asList(new Coord(0,0),   // expectWalls
                            new Coord(0,1),
                            new Coord(2,0),
                            new Coord(4,2),
                            new Coord(7,0),
                            new Coord(7,2)),
              Arrays.asList(new Coord(2,1),   // expectSpaces
                            new Coord(3,1),
                            new Coord(5,1),
                            new Coord(6,1))
              );
  }
  @Test(timeout=2000) public void file_constructor4() throws Exception{
    String input =
      "        ||||||||||||||\n"+
      "        |           C|\n"+
      "||||||||| ||||||||||||\n"+
      "|M                   |\n"+
      "||||||||||||||||||||||\n"+
      "";
    checkMazeFile(input,"fork1-test.txt",
              5,                              // expectRows
              22,                             // expectCols
              input,                          // toString
              new Coord(3,1),                 // expectMouse 
              Arrays.asList(new Coord(1,20)), // expectCheese
              Arrays.asList(new Coord(0,8),   // expectWalls
                            new Coord(0,10),
                            new Coord(0,21),
                            new Coord(1,21),
                            new Coord(1,8),
                            new Coord(2,5),
                            new Coord(2,10)),
              Arrays.asList(new Coord(0,0),   // expectSpaces
                            new Coord(1,1),
                            new Coord(1,9),
                            new Coord(2,9),
                            new Coord(3,20))
              );
  }
  @Test(timeout=2000) public void file_constructor5() throws Exception{
    String input =
      "|||||||||||||||||||||||\n"+
      "|M                   ||\n"+
      "| ||| || || || || || ||\n"+
      "| ||| || || || ||C|| ||\n"+
      "| ||| || || || || || ||\n"+
      "|                    ||\n"+
      "|||||||||||||||||||||||\n"+
      "";
    checkMazeFile(input,"loop5-test.txt",
              7,                              // expectRows
              23,                             // expectCols
              input,                          // toString
              new Coord(1,1),                 // expectMouse 
              Arrays.asList(new Coord(3,17)), // expectCheese
              Arrays.asList(new Coord(3,3),   // expectWalls
                            new Coord(3,4),
                            new Coord(3,16),
                            new Coord(2,21),
                            new Coord(2,22),
                            new Coord(6,15),
                            new Coord(7,22)),
              Arrays.asList(new Coord(1,2),   // expectSpaces
                            new Coord(3,5),
                            new Coord(4,11),
                            new Coord(6,13),
                            new Coord(6,20))
              );
  }
  @Test(timeout=2000) public void file_constructor6() throws Exception{
    String input =
      "||||||||||||||||||||||||||||||||||||\n"+
      "||        ||||||||||||      ||||||||\n"+
      "|| |||||| ||||||||M||| |||||||||||||\n"+
      "|| |||||| |||||||| ||| |||        ||\n"+
      "|| |||||| |||||||| ||| ||| |||||| ||\n"+
      "|| |               ||| ||| |||||| ||\n"+
      "|| ||||||||||||||| ||| ||| |||||| ||\n"+
      "|| |||||| ||| ||||          ||||| ||\n"+
      "|| |||||| ||| |||| |||||||||||||| ||\n"+
      "|| |||             |||||          ||\n"+
      "|||||||||||||||||||||||| |||||||| ||\n"+
      "|||C||                   |||      ||\n"+
      "||| ||||||||| |||||| |||||||||||||||\n"+
      "|||           ||||||               |\n"+
      "||||||||||||||||||||||||||||||||||||\n"+
      "";
    checkMazeFile(input,"labrynth1-test.txt",
              15,                             // expectRows
              36,                             // expectCols
              input,                          // toString
              new Coord(2,18),                // expectMouse 
              Arrays.asList(new Coord(11,3)), // expectCheese
              Arrays.asList(new Coord(8,6),   // expectWalls
                            new Coord(7,19),
                            new Coord(3,16),
                            new Coord(13,19),
                            new Coord(1,28),
                            new Coord(13,35),
                            new Coord(11,2)),
              Arrays.asList(new Coord(5,2),   // expectSpaces
                            new Coord(3,5),
                            new Coord(3,18),
                            new Coord(12,20),
                            new Coord(13,34))
              );
  }
  @Test(timeout=2000) public void file_constructor7() throws Exception{
    String input =
      "        ||||||||||||||\n"+
      "        |           C|\n"+
      "||||||||| ||||||||||||\n"+
      "|M                  C|\n"+
      "||||||||||||||||||||||\n"+
      "";
    checkMazeFile(input,"fork3-test.txt",
              5,                              // expectRows
              22,                             // expectCols
              input,                          // toString
              new Coord(3,1),                 // expectMouse 
              Arrays.asList(new Coord(1,20),  // expectCheese
                            new Coord(3,20)),
              Arrays.asList(new Coord(0,8),   // expectWalls
                            new Coord(0,10),
                            new Coord(0,21),
                            new Coord(1,21),
                            new Coord(1,8),
                            new Coord(2,5),
                            new Coord(2,10)),
              Arrays.asList(new Coord(0,0),   // expectSpaces
                            new Coord(1,1),
                            new Coord(1,9),
                            new Coord(2,9),
                            new Coord(3,20))
              );
  }
  @Test(timeout=2000) public void file_constructor8() throws Exception{
    String input =
      "|||||||||||||||\n"+
      "|C         C|||\n"+
      "|||| ||| ||||||\n"+
      "|||| |||     ||\n"+
      "|   M  C C|| ||\n"+
      "|C|| ||||||| ||\n"+
      "||||  C  |||C||\n"+
      "|||||||||||||||\n"+
      "";
    checkMazeFile(input,"abundance-test.txt",
              8,                              // expectRows
              15,                             // expectCols
              input,                          // toString
              new Coord(4,4),                 // expectMouse 
              Arrays.asList(new Coord(1,1),  // expectCheese
                            new Coord(1,11),
                            new Coord(4,7),
                            new Coord(4,9),
                            new Coord(5,1),
                            new Coord(6,6),
                            new Coord(6,12)),
              Arrays.asList(new Coord(0,0),   // expectWalls
                            new Coord(1,0),
                            new Coord(2,6),
                            new Coord(5,8),
                            new Coord(5,2),
                            new Coord(5,14),
                            new Coord(7,14),
                            new Coord(0,14)),
              Arrays.asList(new Coord(1,2),   // expectSpaces
                            new Coord(2,8),
                            new Coord(2,4),
                            new Coord(4,6),
                            new Coord(6,8))
              );
  }
  @Test(timeout=2000) public void file_constructor9() throws Exception{
    String input =
      "||||||||||||||||||||||||\n"+
      "||           |||||||||||\n"+
      "||        C  |||||||||||\n"+
      "||           |||||||||||\n"+
      "||           |||||||||||\n"+
      "||||||| ||||||||||||||||\n"+
      "||||||| ||||||||||||||||\n"+
      "||||||| |||         ||||\n"+
      "||||||| |||   M     ||||\n"+
      "||||||| |||         ||||\n"+
      "|||||||                |\n"+
      "||||||||||||||||||||||||\n"+
      "";
    checkMazeFile(input,"rooms-test.txt",
              12,                           // expectRows
              24,                           // expectCols
              input,                        // toString
              new Coord(8,14),              // expectMouse 
              Arrays.asList(new Coord(2,10)), // expectCheese
              Arrays.asList(new Coord(0,0),   // expectWalls
                            new Coord(3,13),
                            new Coord(2,6),
                            new Coord(5,8),
                            new Coord(5,6),
                            new Coord(9,10),
                            new Coord(9,20),
                            new Coord(11,23)),
              Arrays.asList(new Coord(10,22),   // expectSpaces
                            new Coord(2,8),
                            new Coord(10,13),
                            new Coord(9,7),
                            new Coord(4,2),
                            new Coord(1,2),
                            new Coord(2,9))
              );
  }

  ////////////////////////////////////////////////////////////////////////////////
  // Tests for flags
  @Test(timeout=2000) public void flag_tests1() throws Exception{
    String input =
      "        ||||||||||||||\n"+
      "        |           C|\n"+
      "||||||||| ||||||||||||\n"+
      "|M                   |\n"+
      "||||||||||||||||||||||\n"+
      "";
    Maze maze = new Maze(input);
    String msg;
    for(int i=0; i<maze.getRows(); i++){
      for(int j=0; j<maze.getCols(); j++){
        msg =
          String.format("Position (%d,%d) should not be flagged\n",i,j)+
          String.format("maze:\n%s",maze.toString());
        assertFalse(msg,maze.isFlagged(new Coord(i,j)));
      }
    }
  }
    
  @Test(timeout=2000) public void flag_tests2() throws Exception{
    String input =
      "        ||||||||||||||\n"+
      "        |           C|\n"+
      "||||||||| ||||||||||||\n"+
      "|M                   |\n"+
      "||||||||||||||||||||||\n"+
      "";
    String expect =
      ".       ||||||||||||||\n"+
      "        |           C|\n"+
      "|||||||||.||||||||||||\n"+
      "|M..                 |\n"+
      "||||||||||||||||||||||\n"+
      "";
    List<Coord> setFlags =
      Arrays.asList(new Coord(0,0),
                    new Coord(2,9),
                    new Coord(3,2),
                    new Coord(3,3));
    Maze maze = new Maze(input);
    for(Coord c : setFlags){
      maze.setFlag(c);
    }
    String msg;
    for(Coord c : setFlags){
      msg =
        String.format("Flag at position %s should be set\n",c) +
        String.format("maze:\n%s",maze.toString());
      assertTrue(msg,maze.isFlagged(c));
    }
    msg =
      String.format("Flagged positions %s, maze.toString() wrong\n",setFlags.toString())+
      String.format("Expect:\n%s",expect)+
      String.format("Actual:\n%s",maze.toString())+
      "\n";
    assertEquals(msg,expect,maze.toString());
  }
  @Test(timeout=2000) public void flag_tests3() throws Exception{
    String input =
      "||||||||\n"+
      "|     M|\n"+
      "| | ||||\n"+
      "| |    |\n"+
      "| ||||||\n"+
      "|      |\n"+
      "|||||| |\n"+
      "| ||   |\n"+
      "|C|C | |\n"+
      "||||||||\n"+
      "";
    String expect =
      "||||||||\n"+
      "|.....M|\n"+
      "|.| ||||\n"+
      "|.|    |\n"+
      "|.||||||\n"+
      "|......|\n"+
      "||||||.|\n"+
      "| ||  .|\n"+
      "|C|C |.|\n"+
      "||||||||\n"+
      "";
    List<Coord> setFlags =
      Arrays.asList(new Coord(1,5),
                    new Coord(1,4),
                    new Coord(1,3),
                    new Coord(1,2),
                    new Coord(1,1),
                    new Coord(2,1),
                    new Coord(3,1),
                    new Coord(4,1),
                    new Coord(5,1),
                    new Coord(5,2),
                    new Coord(5,3),
                    new Coord(5,4),
                    new Coord(5,5),
                    new Coord(5,6),
                    new Coord(6,6),
                    new Coord(7,6),
                    new Coord(8,6));
    Maze maze = new Maze(input);
    for(Coord c : setFlags){
      maze.setFlag(c);
    }
    String msg;
    for(Coord c : setFlags){
      msg =
        String.format("Flag at position %s should be set\n",c) +
        String.format("maze:\n%s",maze.toString());
      assertTrue(msg,maze.isFlagged(c));
    }
    msg =
      String.format("Flagged positions %s, maze.toString() wrong\n",setFlags.toString())+
      String.format("Expect:\n%s",expect)+
      String.format("Actual:\n%s",maze.toString())+
      "\n";
    assertEquals(msg,expect,maze.toString());

    maze.clearFlags();
    msg =
      String.format("Flagged positions should be cleard, maze.toString() wrong\n",setFlags.toString())+
      String.format("Expect:\n%s",input)+
      String.format("Actual:\n%s",maze.toString())+
      "\n";
    assertEquals(msg,input,maze.toString());
  }
  @Test(timeout=2000) public void flag_tests4() throws Exception{
    String input =
      "||||||||\n"+
      "|     M|\n"+
      "| | ||||\n"+
      "| |    |\n"+
      "| ||||||\n"+
      "|      |\n"+
      "|||||| |\n"+
      "| ||   |\n"+
      "|C|C | |\n"+
      "||||||||\n"+
      "";
    String expect =
      "||||||||\n"+
      "|.....M|\n"+
      "|.| ||||\n"+
      "|.|    |\n"+
      "|.||||||\n"+
      "|......|\n"+
      "||||||.|\n"+
      "| ||  .|\n"+
      "|C|C |.|\n"+
      "||||||||\n"+
      "";
    List<Coord> setFlags =
      Arrays.asList(new Coord(1,5),
                    new Coord(1,4),
                    new Coord(1,3),
                    new Coord(1,2),
                    new Coord(1,1),
                    new Coord(2,1),
                    new Coord(3,1),
                    new Coord(4,1),
                    new Coord(5,1),
                    new Coord(5,2),
                    new Coord(5,3),
                    new Coord(5,4),
                    new Coord(5,5),
                    new Coord(5,6),
                    new Coord(6,6),
                    new Coord(7,6),
                    new Coord(8,6));
    Maze maze = new Maze(input);
    for(Coord c : setFlags){
      maze.setFlag(c);
    }
    String msg;
    for(Coord c : setFlags){
      msg =
        String.format("Flag at position %s should be set\n",c) +
        String.format("maze:\n%s",maze.toString());
      assertTrue(msg,maze.isFlagged(c));
    }
    msg =
      String.format("Flagged positions %s, maze.toString() wrong\n",setFlags.toString())+
      String.format("Expect:\n%s",expect)+
      String.format("Actual:\n%s",maze.toString())+
      "\n";
    assertEquals(msg,expect,maze.toString());

    // Try setting again
    for(Coord c : setFlags){
      msg =
        String.format("Flag at position %s should be set\n",c) +
        String.format("maze:\n%s",maze.toString());
      assertTrue(msg,maze.isFlagged(c));
    }
    msg =
      String.format("Flagged positions %s, maze.toString() wrong\n",setFlags.toString())+
      String.format("Expect:\n%s",expect)+
      String.format("Actual:\n%s",maze.toString())+
      "\n";
    assertEquals(msg,expect,maze.toString());

  }
  @Test(timeout=2000) public void flag_tests5() throws Exception{
    String input =
      "||||||||\n"+
      "|     M|\n"+
      "| | ||||\n"+
      "| |    |\n"+
      "| ||||||\n"+
      "|      |\n"+
      "|||||| |\n"+
      "| ||   |\n"+
      "|C|C | |\n"+
      "||||||||\n"+
      "";
    String expect =
      "||||||||\n"+
      "|     M|\n"+
      "| | ||||\n"+
      "| |    |\n"+
      "| ||||||\n"+
      "|      |\n"+
      "|||||| |\n"+
      "|.||   |\n"+
      "|C|C | |\n"+
      "||||||||\n"+
      "";
    List<Coord> setFlags =
      Arrays.asList(new Coord(0,0),
                    new Coord(0,1),
                    new Coord(1,6),
                    new Coord(7,1),
                    new Coord(8,1),
                    new Coord(8,3),
                    new Coord(6,3),
                    new Coord(0,7),
                    new Coord(9,6));
    Maze maze = new Maze(input);
    for(Coord c : setFlags){
      maze.setFlag(c);
    }
    String msg;
    for(Coord c : setFlags){
      msg =
        String.format("Flag at position %s should be set\n",c) +
        String.format("maze:\n%s",maze.toString());
      assertTrue(msg,maze.isFlagged(c));
    }
    msg =
      String.format("Flagged positions %s, maze.toString() wrong\n",setFlags.toString())+
      String.format("Expect:\n%s",expect)+
      String.format("Actual:\n%s",maze.toString())+
      "\n";
    assertEquals(msg,expect,maze.toString());
  }

  /////////////////////////////////////////////////////////////////////////////////
  // Path tests
  @Test(timeout=2000) public void pat_tests1() throws Exception{
    String input =
      "|||||||||||\n"+
      "|M        |\n"+
      "|      C  |\n"+
      "|         |\n"+
      "|         |\n"+
      "|         |\n"+
      "|||||||||||\n"+
      "";
    String expect =
      "|||||||||||\n"+
      "|M..      |\n"+
      "|  .   C  |\n"+
      "|  .  .   |\n"+
      "| ..  .   |\n"+
      "| .....   |\n"+
      "|||||||||||\n"+
      "";
    Coord start = new Coord(1,1);
    List<Direction> dirs =
      Arrays.asList(Direction.East,
                    Direction.East,
                    Direction.South,
                    Direction.South,
                    Direction.South,
                    Direction.West,
                    Direction.South,
                    Direction.East,
                    Direction.East,
                    Direction.East,
                    Direction.East,
                    Direction.North,
                    Direction.North);
    Maze maze = new Maze(input);
    maze.flagPath(dirs,start);
    String msg;
    msg =
      String.format("Path displayed wrong\n")+
      String.format("Expect:\n%s",expect)+
      String.format("Actual:\n%s",maze.toString())+
      "\n";
    assertEquals(msg,expect,maze.toString());
  }
  @Test(timeout=2000) public void pat_tests2() throws Exception{
    String input =
      "||||||||||||||||||||||||||||||||||||\n"+
      "||        ||||||||||||      ||||||||\n"+
      "|| |||||| ||||||||M||| |||||||||||||\n"+
      "|| |||||| |||||||| ||| |||        ||\n"+
      "|| |||||| |||||||| ||| ||| |||||| ||\n"+
      "|| |               ||| ||| |||||| ||\n"+
      "|| ||||||||||||||| ||| ||| |||||| ||\n"+
      "|| |||||| ||| ||||          ||||| ||\n"+
      "|| |||||| ||| |||| |||||||||||||| ||\n"+
      "|| |||             |||||          ||\n"+
      "|||||||||||||||||||||||| |||||||| ||\n"+
      "|||C||                   |||      ||\n"+
      "||| ||||||||| |||||| |||||||||||||||\n"+
      "|||           ||||||               |\n"+
      "||||||||||||||||||||||||||||||||||||\n"+
      "";
    String expect =
      "||||||||||||||||||||||||||||||||||||\n"+
      "||        ||||||||||||      ||||||||\n"+
      "|| |||||| ||||||||M||| |||||||||||||\n"+
      "|| |||||| ||||||||.||| |||        ||\n"+
      "|| |||||| ||||||||.||| ||| |||||| ||\n"+
      "|| |         ......||| ||| |||||| ||\n"+
      "|| ||||||||||||||| ||| ||| |||||| ||\n"+
      "|| |||||| ||| ||||....      ||||| ||\n"+
      "|| |||||| ||| |||| |||||||||||||| ||\n"+
      "|| |||             |||||          ||\n"+
      "|||||||||||||||||||||||| |||||||| ||\n"+
      "|||C||                   |||      ||\n"+
      "||| ||||||||| |||||| |||||||||||||||\n"+
      "|||           ||||||               |\n"+
      "||||||||||||||||||||||||||||||||||||\n"+
      "";
    Coord start = new Coord(2,18);
    List<Direction> dirs =
      Arrays.asList(Direction.South,
                    Direction.South,
                    Direction.South,
                    Direction.West,
                    Direction.West,
                    Direction.West,
                    Direction.West,
                    Direction.West,
                    Direction.East,
                    Direction.East,
                    Direction.East,
                    Direction.East,
                    Direction.South,
                    Direction.South,
                    Direction.East,
                    Direction.East,
                    Direction.East,
                    Direction.East,
                    Direction.North,
                    Direction.North,
                    Direction.North,
                    Direction.North);
    Maze maze = new Maze(input);
    maze.flagPath(dirs,start);
    String msg;
    msg =
      String.format("Path displayed wrong\n")+
      String.format("Expect:\n%s",expect)+
      String.format("Actual:\n%s",maze.toString())+
      "\n";
    assertEquals(msg,expect,maze.toString());
  }
}
