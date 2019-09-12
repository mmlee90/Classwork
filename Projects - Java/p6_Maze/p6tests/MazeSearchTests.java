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

public class MazeSearchTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("MazeSearchTests");
  }
  
  
  private static Direction n = Direction.North, s = Direction.South, e = Direction.East, w = Direction.West;
  public static String mazeStr1 =
    "|||||||\n"+
    "|C   M|\n"+
    "||| | |\n"+
    "|   | |\n"+
    "| ||| |\n"+
    "|  C|C|\n"+
    "|||||||\n"
    ;
  public static String mazeStr2 =
    "|||C|||\n"+
    "||| |||\n"+
    "||| |||\n"+
    "C  M   \n"+
    "||| |||\n"+
    "||| |||\n"+
    "||| |||\n"
    ;
  
  // Return a dummy child of MazeSearch to ensure that
  // searchForCheese() is implemented in the parent
  private MazeSearch getMazeSearch(){
    return new MazeSearch(){
      @Override public List<Direction> searchForCheese(Maze m){
        throw new RuntimeException("unimplemented! only use this for testing isPathToCheese.");
      };
    };
  }
  
  // THESE CASES FIND CHEESE.
  /**
   * Finds the western cheese.
   */
  @Test
  public void path_test1() throws Exception{
   Maze m = new Maze(mazeStr1);
   MazeSearch ms = getMazeSearch();
   
   // note: using static vars to shorten direction names.
   List<Direction> path = Arrays.asList(w,w,w,w);
   assertTrue(ms.isPathToCheese(m,path));
  }
  
  
  /**
   * Finds the southern cheese.
   */
  @Test
  public void path_test2() throws Exception {
   Maze m = new Maze(mazeStr1);
   MazeSearch ms = getMazeSearch();
      
   List<Direction> path = Arrays.asList(s,s,s,s);
   assertTrue(ms.isPathToCheese(m,path));
  }
  
  /**
   * Finds the furthest-away cheese.
   */
  @Test
  public void path_test3() throws Exception {
   Maze m = new Maze(mazeStr1);
   MazeSearch ms = getMazeSearch();
   
   List<Direction> path = Arrays.asList(w,w,s,s,w,w,s,s,e,e);
   assertTrue(ms.isPathToCheese(m,path));
  }
  
  /**
   * This test does some backtracking unnecessarily but it does find cheese.
   */
  @Test
  public void path_test4() throws Exception {
   Maze m = new Maze(mazeStr1);
   MazeSearch ms = getMazeSearch();
   
   List<Direction> path = Arrays.asList(s,s,n,s,n,s,s,s);
   assertTrue(ms.isPathToCheese(m,path));
  }
  
  /**
   * This test goes all the way to the border but still stays on the board and succeeds.
   */
  @Test
  public void path_test5() throws Exception {
   Maze m = new Maze(mazeStr2);
   MazeSearch ms = getMazeSearch();
   
   List<Direction> path = Arrays.asList(e,e,e,w,w,w,w,w,w);
   assertTrue(ms.isPathToCheese(m,path));
  }
  
  
  /**
   * This test visits dead ends before finding cheese.
   */
  @Test
  public void path_test6() throws Exception {
   Maze m = new Maze(mazeStr2);
   MazeSearch ms = getMazeSearch();
   
   List<Direction> path = Arrays.asList(e,e,e,  w,w,w,  s,s,s,  n,n,n,  n,n,n);
   assertTrue(ms.isPathToCheese(m,path));
  }
  
  // THESE CASES DON'T FIND CHEESE.
  
  /**
   * This test doesn't find cheese, because the path is empty.
   */
  @Test
  public void path_test_f1() throws Exception {
   Maze m = new Maze(mazeStr1);
   MazeSearch ms = getMazeSearch();
   List<Direction> path = Arrays.asList();
   assertFalse(ms.isPathToCheese(m,path));
  }
  

  /**
   * This test doesn't find cheese, because it stops one spot short.
   */
  @Test
  public void path_test_f2() throws Exception {
   Maze m = new Maze(mazeStr1);
   MazeSearch ms = getMazeSearch();
   List<Direction> path = Arrays.asList(w,w,w);
   assertFalse(ms.isPathToCheese(m,path));
  }
  

  /**
   * This test doesn't find cheese, because it goes off the board.
   */
  @Test
  public void path_test_f3() throws Exception {
   Maze m = new Maze(mazeStr2);
   MazeSearch ms = getMazeSearch();
   
   List<Direction> path = Arrays.asList(e,e,e,e,w,w,w,w,w,w,w);
   try{
     boolean actual = ms.isPathToCheese(m,path);
     String msg =
       "Throw an exception when the path goes out of bounds\n"+
       String.format("Path: %s\n",path)+
       String.format("maze:\n%s\n",m.toString());
     fail(msg);
   }
   catch(Exception e){
     // Exception is correct behavior
   }
  }
  

  /**
   * This test doesn't find cheese, because it tries to go through a wall.
   */
  @Test
  public void path_test_f4() throws Exception {
   Maze m = new Maze(mazeStr2);
   MazeSearch ms = getMazeSearch();
   
   List<Direction> path = Arrays.asList(n,e,s,w,  w,w,w);
   assertFalse(ms.isPathToCheese(m,path));
  }
  
  /**
   * This test doesn't find cheese, because it goes through many walls.
   */
  @Test
  public void path_test_f5() throws Exception {
   Maze m = new Maze(mazeStr2);
   MazeSearch ms = getMazeSearch();
   
   List<Direction> path = Arrays.asList(n,e,  n,e,s,w,  s,s,  w,w,w,  n,n,w,  s);
   assertFalse(ms.isPathToCheese(m,path));

  }
  
  /**
   * Path leads beyond the cheese and should return false
   */
  @Test
  public void path_test_f6() throws Exception {
   Maze m = new Maze(mazeStr2);
   MazeSearch ms = getMazeSearch();
   
   List<Direction> path = Arrays.asList(n,n,n, s,s,s,s);
   assertFalse(ms.isPathToCheese(m,path));
  }
  
  /**
   * Path hits a wall then out of bounds, should return false
   */
  @Test
  public void path_test_f7() throws Exception {
   Maze m = new Maze(mazeStr1);
   MazeSearch ms = getMazeSearch();
   
   List<Direction> path = Arrays.asList(n,n);
   assertFalse(ms.isPathToCheese(m,path));
  }
}

