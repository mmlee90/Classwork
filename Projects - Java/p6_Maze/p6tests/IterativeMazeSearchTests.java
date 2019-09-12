import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;


/**
 * Note: All of these tests are testing your isPathToCheese method.
 * There's no great way to do so when we can't enforce the particular
 * path you choose, without tracing your path and checking it obeys
 * the rules. This will look quite like the method's implementation
 * itself! So we've provided a .class file for MazeSearchTestsSecretHelper,
 * and call its (supposedly correct) implementation. Your implementation
 * needs to match this implementation's outputs to be correct.
 */

public class IterativeMazeSearchTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("IterativeMazeSearchTests");
  }
  
  public static final String abundance1str =
    "|||||||||||||||\n"+
    "|C         C|||\n"+
    "|||| ||| ||||||\n"+
    "|||| |||     ||\n"+
    "|   M  C C|| ||\n"+
    "|C|| ||||||| ||\n"+
    "||||  C  |||C||\n"+
    "|||||||||||||||\n"
    ;
  
  public static final String fork1str =
    "        ||||||||||||||\n"+
    "        |           C|\n"+
    "||||||||| ||||||||||||\n"+
    "|M                   |\n"+
    "||||||||||||||||||||||\n"
    ;
  public static final String fork2str =
    "        ||||||||||||||\n"+
    "        |            |\n"+
    "||||||||| ||||||||||||\n"+
    "|M                  C|\n"+
    "||||||||||||||||||||||\n"
    ;
  public static final String fork3str =
    "        ||||||||||||||\n"+
    "        |           C|\n"+
    "||||||||| ||||||||||||\n"+
    "|M                  C|\n"+
    "||||||||||||||||||||||\n"
    ;
  public static final String junction1str =
    "            |||       \n"+
    "            | |       \n"+
    "            | |       \n"+
    "            | |       \n"+
    "||||||||||||| ||||||||\n"+
    "|M                   |\n"+
    "||||||||||||| ||||||||\n"+
    "            | |       \n"+
    "            | |       \n"+
    "            | |       \n"+
    "            |C|       \n"+
    "            |||       \n"
    ;
  public static final String junction2str =
    "||||||||||||||||||||||\n"+
    "||||||||||||| ||||||||\n"+
    "||||||||||||| ||||||||\n"+
    "||||||||||||| ||||||||\n"+
    "||||||||||||| ||||||||\n"+
    "|M                   |\n"+
    "||||||||||||| ||||||||\n"+
    "||||||||||||| ||||||||\n"+
    "||||||||||||| ||||||||\n"+
    "||||||||||||| ||||||||\n"+
    "|||||||||||||C||||||||\n"+
    "||||||||||||||||||||||\n"
    ;
  public static final String labyrinthstr =
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
    "||||||||||||||||||||||||||||||||||||\n"
    ;
  public static final String loop1str =
    "|||||||||||||||||||||||\n"+
    "|M                   ||\n"+
    "| |||||||||||||||||| ||\n"+
    "| |||||||||C|||||||| ||\n"+
    "| ||||||||| |||||||| ||\n"+
    "|                    ||\n"+
    "|||||||||||||||||||||||\n"
    ;
  public static final String loop2str =
    "|||||||||||||||||||||||\n"+
    "|M                   ||\n"+
    "| ||||||||| |||||||| ||\n"+
    "| ||||||||| |||||||| ||\n"+
    "| ||||||||| |||||||| ||\n"+
    "|                   C||\n"+
    "|||||||||||||||||||||||\n"
    ;
  public static final String loop3str =
    "|||||||||||||||||||||||\n"+
    "|M                  C||\n"+
    "| ||||||||| |||||||| ||\n"+
    "| ||||||||| |||||||| ||\n"+
    "| ||||||||| |||||||| ||\n"+
    "|                    ||\n"+
    "|||||||||||||||||||||||\n"
    ;
  public static final String loop4str =
    "|||||||||||||||||||||||\n"+
    "|M     C             ||\n"+
    "| ||||||||| |||||||| ||\n"+
    "| ||||||||| |||||||| ||\n"+
    "| ||||||||| |||||||| ||\n"+
    "|                    ||\n"+
    "|||||||||||||||||||||||\n"
    ;
  public static final String loop5str =
    "|||||||||||||||||||||||\n"+
    "|M                   ||\n"+
    "| ||| || || || || || ||\n"+
    "| ||| || || || ||C|| ||\n"+
    "| ||| || || || || || ||\n"+
    "|                    ||\n"+
    "|||||||||||||||||||||||\n"
    ;
  public static final String maze1str =
    "||||||||\n"+
    "|     M|\n"+
    "| | ||||\n"+
    "| |    |\n"+
    "| ||||||\n"+
    "|      |\n"+
    "|||||| |\n"+
    "| ||   |\n"+
    "|C|  |C|\n"+
    "||||||||\n"
    ;
  public static final String maze2str =
    "||||||||\n"+
    "|     M|\n"+
    "| | ||||\n"+
    "| |    |\n"+
    "| ||||||\n"+
    "|      |\n"+
    "|||||| |\n"+
    "| ||   |\n"+
    "|C|C | |\n"+
    "||||||||\n"
    ;
  public static final String open1str =
    "|||||||||||\n"+
    "|M        |\n"+
    "|      C  |\n"+
    "|         |\n"+
    "|         |\n"+
    "|         |\n"+
    "|||||||||||\n"
    ;
  public static final String open2str =
    "|||||||||||\n"+
    "|M        |\n"+
    "|         |\n"+
    "|         |\n"+
    "|         |\n"+
    "|       C |\n"+
    "|||||||||||\n"
    ;
  public static final String open3str =
    "|||||||||||\n"+
    "|M       C|\n"+
    "|         |\n"+
    "|         |\n"+
    "|         |\n"+
    "|         |\n"+
    "|||||||||||\n"
    ;
  public static final String open4str =
    "|||||||||||\n"+
    "|M        |\n"+
    "|         |\n"+
    "|         |\n"+
    "|         |\n"+
    "|C        |\n"+
    "|||||||||||\n"
    ;
  public static final String rooms1str =
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
    "||||||||||||||||||||||||\n"
    ;
  public static final String simple1str =
    "M   C\n"
    ;
  public static final String simple2str =
    "|||||||\n"+
    "|M   C|\n"+
    "|||||||\n"
    ;
  public static final String simple3str =
    "|||\n"+
    "|M|\n"+
    "| |\n"+
    "| |\n"+
    "|C|\n"+
    "| |\n"+
    "| |\n"+
    "|||\n"
    ;
  public static final String spiral1str =
    "||||||||||||||||||||\n"+
    "|                  |\n"+
    "| |||||||||||||||| |\n"+
    "| |              | |\n"+
    "| | |||||||||||| | |\n"+
    "|M| |         C| | |\n"+
    "||| | |||||||||| | |\n"+
    "  | |            | |\n"+
    "  | |||||||||||||| |\n"+
    "  |                |\n"+
    "  ||||||||||||||||||\n"
    ;
  public static final String spiral2str =
    "||||||||||||||||||||\n"+
    "|                  |\n"+
    "| |||||||||||||||| |\n"+
    "| |              | |\n"+
    "| | |||||||||||| | |\n"+
    "|C| |M         | | |\n"+
    "||| | |||||||||| | |\n"+
    "  | |            | |\n"+
    "  | |||||||||||||| |\n"+
    "  |                |\n"+
    "  ||||||||||||||||||\n"
    ;
  
  public static final String nopath1str = 
    "||||||\n"+
    "| M  |\n"+
    "||||||\n"+
    "|  C |\n"+
    "||||||\n"
    ;
  
  public static final String nopath2str = 
    "||||||||||||||||||||\n"+
    "|                  |\n"+
    "| |||||||||||||||| |\n"+
    "|||              | |\n"+
    "| | |||||||||||| | |\n"+
    "|C| |         M| | |\n"+
    "||| | |||||||||| | |\n"+
    "  | |            | |\n"+
    "  | |||||||||||||| |\n"+
    "  |                |\n"+
    "  ||||||||||||||||||\n"
    ;
  public static final String nopath3str = 
    "M|C\n";
  
  public static final String nopath4str = 
    "M\n"+
    " \n"+
    " \n"+
    "|\n"+
    " \n"+
    " \n"+
    "C\n"
    ;    
  
  // dispatches our secret isPathToCheese implementation in order to verify your cheesy path is valid.
  public void checkMaze(Maze maze, MazeSearch ms){
    assertTrue(MazeSearchTestsSecretHelper.isPathToCheese(maze, ms.searchForCheese(maze)));
  }
  
  // checks that a cheese-search yields null, rather than some supposed list of directions to cheese.
  // only useful for tests that ought to not find cheese.
  public void checkMazeFails(Maze maze, MazeSearch ms){
    assertEquals(null,ms.searchForCheese(maze));
  }
  
  // We check each of the given sample mazes. Your answer must give a valid path that our secret isPathToCheese implementation accepts.
  @Test(timeout=2000) public void iterative_maze1 () throws Exception{ checkMaze(new Maze(abundance1str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze2 () throws Exception{ checkMaze(new Maze(fork1str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze3 () throws Exception{ checkMaze(new Maze(fork2str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze4 () throws Exception{ checkMaze(new Maze(fork3str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze5 () throws Exception{ checkMaze(new Maze(junction1str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze6 () throws Exception{ checkMaze(new Maze(junction2str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze7 () throws Exception{ checkMaze(new Maze(labyrinthstr), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze8 () throws Exception{ checkMaze(new Maze(loop1str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze9 () throws Exception{ checkMaze(new Maze(loop2str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze10() throws Exception{ checkMaze(new Maze(loop3str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze11() throws Exception{ checkMaze(new Maze(loop4str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze12() throws Exception{ checkMaze(new Maze(loop5str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze13() throws Exception{ checkMaze(new Maze(maze1str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze14() throws Exception{ checkMaze(new Maze(maze2str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze15() throws Exception{ checkMaze(new Maze(open1str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze16() throws Exception{ checkMaze(new Maze(open2str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze17() throws Exception{ checkMaze(new Maze(open3str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze18() throws Exception{ checkMaze(new Maze(open4str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze19() throws Exception{ checkMaze(new Maze(rooms1str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze20() throws Exception{ checkMaze(new Maze(simple1str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze21() throws Exception{ checkMaze(new Maze(simple2str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze22() throws Exception{ checkMaze(new Maze(simple3str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze23() throws Exception{ checkMaze(new Maze(spiral1str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_maze24() throws Exception{ checkMaze(new Maze(spiral2str), new IterativeMazeSearch()); }
  
  // these mazes have cheese, but have no solutions. Yours should return null.
  @Test(timeout=2000) public void iterative_nopath1() throws Exception{ checkMazeFails(new Maze(nopath1str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_nopath2() throws Exception{ checkMazeFails(new Maze(nopath2str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_nopath3() throws Exception{ checkMazeFails(new Maze(nopath3str), new IterativeMazeSearch()); }
  @Test(timeout=2000) public void iterative_nopath4() throws Exception{ checkMazeFails(new Maze(nopath4str), new IterativeMazeSearch()); }
}
  

