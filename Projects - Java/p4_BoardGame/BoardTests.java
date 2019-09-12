import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class BoardTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("BoardTests");
  }
  
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

  // private boolean sameStates(Board b, Board c){
  //   if (b.rows() != c.rows()) return false;
  //   if (b.getCols() != c.getCols()) return false;
  //   for (int i=0; i<b.rows(); i++){
  //     for (int j = 0; j<b.getCols(); j++){
  //       assertTrue(b.gemAt(i,j).sameKind(c.gemAt(i,j)));
  //     }
  //   }
  //   return true;
  // }
  

  // Convert a 2d int array to a string like a board; don't need to
  // rely on student code to create a board then
  public static String gem2str(int [][] g2d){
    StringBuilder sb = new StringBuilder();
    int rows=g2d.length, cols=g2d[0].length;
    // Col header
    sb.append("   ");
    for(int j=0; j<cols; j++){
      sb.append(String.format("%3d",j));
    }
    sb.append("\n");
    sb.append("   ");
    for(int j=0; j<cols; j++){
      sb.append("---");
    }
    sb.append("\n");
    for(int i=0; i<rows; i++){
      sb.append(String.format("%2d|",i));
      for(int j=0; j<cols; j++){
        int g = g2d[i][j];
        String kind = g < 0 ? "" : ""+g;
        sb.append(String.format("%3s",kind));
      }
      sb.append("\n");
    }
    return sb.toString();
  }


  // Check a board has the given gem kinds
  // Dependency on gemAt(), rows(), cols()
  public static void assertBoardHasGems(int [][] expect, Board actual, String moreInfo){
    String msg = String.format("Expected:\n%s\nActual:\n%s\n",
			       gem2str(expect),actual);
    int rows = expect.length, cols = expect[0].length;

    assertEquals(msg,rows,actual.getRows());
    assertEquals(msg,cols,actual.getCols());

    for (int i=0; i<rows; i++){
      for (int j = 0; j<cols; j++){
	msg = String.format("Problem at position %d %d\nExpected:\n%s\nActual:\n%s\n%s",
			    i,j,gem2str(expect),actual,moreInfo);
        int kind = expect[i][j];
	if(kind >= 0){          // Expect filled gem with given kind
	  assertTrue(msg,actual.gemAt(i,j).sameKind(new Gem(kind)));
	}
	else{			// Expect an empty
	  assertTrue(msg, actual.gemAt(i,j).isEmpty());
	}	  
      }
    }
  }    
  
  // Omit moreInfo string when calling
  public static void assertBoardHasGems(int [][] expect, Board actual){
    assertBoardHasGems(expect,actual,"");
  }


  // Check if two boards have identical gems in identical positions
  // Dependency on gemAt(), getRows(), getCols()
  public static void assertBoardsIdentical(Board expect, Board actual){
    String msg = String.format("Expected:\n%s\nActual:\n%s\n",expect,actual);
    assertEquals(msg,expect.getRows(),actual.getRows());
    assertEquals(msg,expect.getCols(),actual.getCols());

    for (int i=0; i<expect.getRows(); i++){
      for (int j = 0; j<expect.getCols(); j++){
	msg = String.format("Problem at position %d %d\nExpected:\n%s\nActual:\n%s\n",
			    i,j,expect,actual);
	if(expect.gemAt(i,j).isFilled()){
	  assertTrue(msg,expect.gemAt(i,j).sameKind(actual.gemAt(i,j)));
	}
	else{			// Expect an empty
	  assertTrue(msg, actual.gemAt(i,j).isEmpty());
	}	  
      }
    }
  }    

  // Check that the two boards do not share any Gems or structure
  // Dependency on gemAt(), getRows(), getCols()
  public static void assertBoardsDistinct(Board expect, Board actual){
    String msg;
    for (int i=0; i<expect.getRows(); i++){
      for (int j = 0; j<expect.getCols(); j++){
	Gem eg = expect.gemAt(i,j), ag = actual.gemAt(i,j);
	msg = String.format("Problem at position %d %d\nSame gem in both boards, should be distinct; use gem.clone()\n",
			    i,j);
	assertFalse(msg, eg == ag);
      }
    }
  }

  ///////////////////////////////////////////////////////////////////////////////// 
  // Tests for constructing standard boards in Globals boardPairs
  @Test(timeout=2000) public void test_board_clearflags(){
    int [][] gems = {
      {1, 3, 2},
      {1, 1, 1},
      {1, 2, 2},
    };
    Board b = new Board(gems);
    b.gemAt(0,0).setFlag();
    b.gemAt(1,1).setFlag();
    b.gemAt(2,0).setFlag();
    b.gemAt(2,1).setFlag();
    b.gemAt(2,2).setFlag();
    
    assert(b.gemAt(0,0).flagged());
    
    b.clearFlags();
    for (int i=0; i<b.getRows(); i++){
      for (int j = 0; j<b.getCols(); j++){
        assertTrue( ! b.gemAt(i,j).flagged());
      }
    }
  }
  
  @Test(timeout=2000) public void test_board_doRemovals(){
    int [][] gems = {
      {1, 3, 2},
      {1, 1, 1},
      {1, 2, 2},
    };
    Board b = new Board(gems);
    b.gemAt(2,1).setFlag();
    b.gemAt(2,2).setFlag();
    b.doRemovals();
    
    assert(b.gemAt(0,1).isEmpty());
    assert(b.gemAt(0,2).isEmpty());
    assert(b.gemAt(1,1).sameKind(new Gem(3)));
    assert(b.gemAt(1,2).sameKind(new Gem(2)));
    assert(b.gemAt(2,1).sameKind(new Gem(1)));
    assert(b.gemAt(2,2).sameKind(new Gem(1)));
  }
  
  
  // Test gem constructor, empty gems included
  @Test(timeout=2000) public void test_board_constructor10 () {
    // duplicate structure of Globals.tiny board.
    int[][] gems = {
      {1, 3, 2},
      {1, 1, 1},
      {1, 2, 2},
    };
    Board b1 = new Board ( gems );
    
    // does the size match?
    assertEquals(b1.getRows(), 3);
    assertEquals(b1.getCols(), 3);
    
    // do (some of) the cells match?
    assertEquals(b1.gemAt(0,0).kindString(),""+gems[0][0]);
    assertEquals(b1.gemAt(1,1).kindString(),""+gems[1][1]);
    assertEquals(b1.gemAt(2,1).kindString(),""+gems[2][1]);
  }

  @Test(timeout=2000) public void test_board_constructor11 () {
    int [][] gems = {
      {1, 1, 1,-1, 2},
      {2,-1, 2, 1, 1},
    };
    Board b1 = new Board ( gems );
    assertBoardHasGems(gems, b1);
    Board b2 = new Board ( gems );
    assertBoardsIdentical(b1, b2);
    assertBoardsDistinct(b1, b2);
  }

  @Test(timeout=2000) public void test_board_constructor12 () {
    int[][] gems = {
      {  9,  4,  4, -1,  5,},
      {  2, -1,  6,  5,  5,},
      {  7,  2,  5,  8,  1,},
      {  3,  7,  8,  5,  1,},
      {  8,  7,  5,  8,  8,},
    };
    Board b1 = new Board ( gems );
    assertBoardHasGems(gems, b1);
    Board b2 = new Board ( gems );
    assertBoardsIdentical(b1, b2);
    assertBoardsDistinct(b1, b2);
  }

  @Test(timeout=2000) public void test_board_constructor13 () {
    int[][] gems = {
      { 11,  7, 11,},
      { 13,  2,  2,},
      { -1,  3,  4,},
      {  4,  2, 13,},
      {  6, 14,  6,},
      {  6, 11,  9,},
      {  5,  4, 11,},
    };
    Board b1 = new Board ( gems );
    assertBoardHasGems(gems, b1);
    Board b2 = new Board ( gems );
    assertBoardsIdentical(b1, b2);
    assertBoardsDistinct(b1, b2);
  }

  // Test gem constructor, NO empty gems included
  @Test(timeout=2000) public void test_board_constructor14 () {
    int[][] gems = {
      {1, 1, 1, 3, 2},
      {2, 3, 2, 1, 1}
    };
    Board b1 = new Board ( gems );
    assertBoardHasGems(gems, b1);
    Board b2 = new Board ( gems );
    assertBoardsIdentical(b1, b2);
    assertBoardsDistinct(b1, b2);
  }

  @Test(timeout=2000) public void test_board_constructor15 () {
    int[][] gems = {
      {  9,  4,  4,  5,  5,},
      {  2,  5,  6,  5,  5,},
      {  7,  2,  5,  8,  1,},
      {  3,  7,  8,  5,  1,},
      {  8,  7,  5,  8,  8,},
    };
    Board b1 = new Board ( gems );
    assertBoardHasGems(gems, b1);
    Board b2 = new Board ( gems );
    assertBoardsIdentical(b1, b2);
    assertBoardsDistinct(b1, b2);
  }

  @Test(timeout=2000) public void test_board_constructor16 () {
    int[][] gems = {
      { 11,  7, 11,},
      { 13,  2,  2,},
      {  2,  3,  4,},
      {  4,  2, 13,},
      {  6, 14,  6,},
      {  6, 11,  9,},
      {  5,  4, 11,},
    };
    Board b1 = new Board ( gems );
    assertBoardHasGems(gems, b1);
    Board b2 = new Board ( gems );
    assertBoardsIdentical(b1, b2);
    assertBoardsDistinct(b1, b2);
  }
  
  // Test int 2d constructor, Empty Gems should never be produced
  @Test(timeout=2000) public void test_board_constructor21 () {
    // 0's become empty
    int[][] ints = new int[][]{
      {1, 1, 1, 3, 2},
      {2, 3, 2, 1, 1}
    };
    Board b1 = new Board ( ints );
    assertBoardHasGems(ints, b1);
  }
  @Test(timeout=2000) public void test_board_constructor22 () {
    // 0's become empty
    int[][] ints = new int[][]{
        {  9,  4,  4,  5,  5,},
        {  2,  5,  6,  5,  5,},
        {  7,  2,  5,  8,  1,},
        {  3,  7,  8,  5,  1,},
        {  8,  7,  5,  8,  8,},
    };
    Board b1 = new Board ( ints );
    assertBoardHasGems(ints, b1);
  }
  @Test(timeout=2000) public void test_board_constructor23 () {
    // 0's become empty
    int[][] ints = new int[][]{
        { 11,  7, 11,},
        { 13,  2,  2,},
        {  2,  3,  4,},
        {  4,  2, 13,},
        {  6, 14,  6,},
        {  6, 11,  9,},
        {  5,  4, 11,},
    };
    Board b1 = new Board ( ints );
    assertBoardHasGems(ints, b1);
  }

  // Make sure negative numbers are interpreted as empty gems
  @Test(timeout=2000) public void test_board_constructor24 () {
    // 0's become empty
    int[][] ints = new int[][]{
      {1, -1,  0, -3, -1},
      {2,  0, -2, -1,  1}
    };
    Board b1 = new Board ( ints );
    assertBoardHasGems(ints, b1);
  }

  ////////////////////////////////////////////////////////////////////////////////
  // hasValidGem()
  @Test(timeout=2000) public void test_board_hasValidGem01 () {
    int [][] gems = {
      {1, 3, 2},
      {1, 1, 1},
      {1, 2, 2},
    };
    Board board = new Board(gems);
    boolean expect = true;
    boolean actual = board.hasValidGem();
    String msg =
      String.format("hasValidGem() wrong\nexpect: %s\nactual: %s\nboard:\n%s",
                    expect,actual,board.toString());
    assertEquals(msg,expect,actual);
  }
  @Test(timeout=2000) public void test_board_hasValidGem02 () {
    int [][] gems = {
      {-1, 3, 2},
      {-1, 1, 1},
      {-1, 2, 2},
      {-1, 3, 2},
      {-1, 1, 1},
      {-1, 2, 2},
    };
    Board board = new Board(gems);
    boolean expect = true;
    boolean actual = board.hasValidGem();
    String msg =
      String.format("hasValidGem() wrong\nexpect: %s\nactual: %s\nboard:\n%s",
                    expect,actual,board.toString());
    assertEquals(msg,expect,actual);
  }
  @Test(timeout=2000) public void test_board_hasValidGem03 () {
    int [][] gems = {
      {-1, -1, -1},
      {-1, -1, -1},
      {-1, -1, -1},
      {-1, -1, -1},
      {-1, -1, -1},
      {-1, -1, -1},
    };
    Board board = new Board(gems);
    boolean expect = false;
    boolean actual = board.hasValidGem();
    String msg =
      String.format("hasValidGem() wrong\nexpect: %s\nactual: %s\nboard:\n%s",
                    expect,actual,board.toString());
    assertEquals(msg,expect,actual);
  }
  @Test(timeout=2000) public void test_board_hasValidGem04 () {
    int [][] gems = {
      {-1, -1, -1},
      {-1, -1, -1},
      {-1,  5, -1},
      {-1, -1, -1},
      {-1, -1, -1},
      {-1, -1, -1},
    };
    Board board = new Board(gems);
    boolean expect = true;
    boolean actual = board.hasValidGem();
    String msg =
      String.format("hasValidGem() wrong\nexpect: %s\nactual: %s\nboard:\n%s",
                    expect,actual,board.toString());
    assertEquals(msg,expect,actual);
  }
  
  ////////////////////////////////////////////////////////////////////////////////
  // clearFlags()
  @Test(timeout=2000) public void test_board_clearFlags01 () {
    int [][] gems = {
      {1, 3, 2},
      {1, 1, 1},
      {1, 2, 2},
    };
    Board board = new Board(gems);
    int toFlag[][] = { {1,1}, {0,2} };
    for(int [] ij : toFlag){
      board.gemAt(ij[0],ij[1]).setFlag();
    }
    board.clearFlags();
    String expect =
      "     0  1  2\n"+
      "   ---------\n"+
      " 0|  1  3  2 \n"+
      " 1|  1  1  1 \n"+
      " 2|  1  2  2 \n"+
      "";
    String actual = board.toString();      
    String msg =
      String.format("\nFlags not cleared\nexpect: %s\nactual:\n%s\n",
                    expect,actual);
    assertEquals(msg,expect,actual);
  }
  @Test(timeout=2000) public void test_board_clearFlags02 () {
    int [][] gems = {
      {1, 3, 2},
      {1, 1, 1},
      {1, 2, 2},
    };
    Board board = new Board(gems);
    int toFlag[][] ={{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}};
    for(int [] ij : toFlag){
      board.gemAt(ij[0],ij[1]).setFlag();
    }
    board.clearFlags();
    String expect =
      "     0  1  2\n"+
      "   ---------\n"+
      " 0|  1  3  2 \n"+
      " 1|  1  1  1 \n"+
      " 2|  1  2  2 \n"+
      "";
    String actual = board.toString();      
    String msg =
      String.format("\nFlags not cleared\nexpect: %s\nactual:\n%s\n",
                    expect,actual);
    assertEquals(msg,expect,actual);
  }
  @Test(timeout=2000) public void test_board_clearFlags03 () {
    int [][] gems = {
      { 4, 3, 5, 3, 5, 1, 3, 3, 1, 1},
      { 2, 4, 1, 4, 1, 4, 4, 5, 4, 2},
      { 5, 3, 5, 2, 3, 3, 3, 1, 3, 4},
      { 1, 2, 2, 2, 2, 4, 2, 4, 5, 1},
      { 1, 3, 1, 2, 3, 3, 3, 4, 1, 1},
      { 2, 2, 2, 1, 5, 1, 5, 5, 3, 5},
      { 2, 4, 1, 5, 2, 2, 5, 4, 5, 2},
    };
    Board board = new Board(gems);
    int toFlag[][] ={{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2},{6,9},{5,4},{3,6}};
    for(int [] ij : toFlag){
      board.gemAt(ij[0],ij[1]).setFlag();
    }
    board.clearFlags();
    String expect =
      "     0  1  2  3  4  5  6  7  8  9\n"+
      "   ------------------------------\n"+
      " 0|  4  3  5  3  5  1  3  3  1  1 \n"+
      " 1|  2  4  1  4  1  4  4  5  4  2 \n"+
      " 2|  5  3  5  2  3  3  3  1  3  4 \n"+
      " 3|  1  2  2  2  2  4  2  4  5  1 \n"+
      " 4|  1  3  1  2  3  3  3  4  1  1 \n"+
      " 5|  2  2  2  1  5  1  5  5  3  5 \n"+
      " 6|  2  4  1  5  2  2  5  4  5  2 \n"+
      "";
    String actual = board.toString();      
    String msg =
      String.format("\nFlags not cleared\nexpect: %s\nactual:\n%s\n",
                    expect,actual);
    assertEquals(msg,expect,actual);
  }
  @Test(timeout=2000) public void test_board_clearFlags04 () {
    int [][] gems = {
      { 4, 3, 5, 3, 5, 1, 3, 3, 1, 1},
      { 2, 4, 1, 4, 1, 4, 4, 5, 4, 2},
      { 5, 3, 5, 2, 3, 3, 3, 1, 3, 4},
      { 1, 2, 2, 2, 2, 4, 2, 4, 5, 1},
      { 1, 3, 1, 2, 3, 3, 3, 4, 1, 1},
      { 2, 2, 2, 1, 5, 1, 5, 5, 3, 5},
      { 2, 4, 1, 5, 2, 2, 5, 4, 5, 2},
    };
    Board board = new Board(gems);
    int toFlag[][] ={{6,1}};
    for(int [] ij : toFlag){
      board.gemAt(ij[0],ij[1]).setFlag();
    }
    board.clearFlags();
    String expect =
      "     0  1  2  3  4  5  6  7  8  9\n"+
      "   ------------------------------\n"+
      " 0|  4  3  5  3  5  1  3  3  1  1 \n"+
      " 1|  2  4  1  4  1  4  4  5  4  2 \n"+
      " 2|  5  3  5  2  3  3  3  1  3  4 \n"+
      " 3|  1  2  2  2  2  4  2  4  5  1 \n"+
      " 4|  1  3  1  2  3  3  3  4  1  1 \n"+
      " 5|  2  2  2  1  5  1  5  5  3  5 \n"+
      " 6|  2  4  1  5  2  2  5  4  5  2 \n"+
      "";
    String actual = board.toString();      
    String msg =
      String.format("\nFlags not cleared\nexpect: %s\nactual:\n%s\n",
                    expect,actual);
    assertEquals(msg,expect,actual);
  }


  ////////////////////////////////////////////////////////////////////////////////
  // fromSaveString
  @Test(timeout=2000) public void test_board_fromSaveString01 () {
    int [][] gems = {
      {1, 3, 2},
      {1, 1, 1},
      {1, 2, 2},
    };
    String str = tiny;
    Board board = Board.fromSaveString(str);
    assertBoardHasGems(gems, board, "Save String:\n"+str);
  }
  @Test(timeout=2000) public void test_board_fromSaveString02 () {
    int [][] gems = {
      { 3, 3, 2},
      { 3, 2, 3},
      { 1, 2, 1},
      { 2, 2, 2},
      { 2, 1, 3},
      { 1, 1, 2},
      { 2, 1, 1},
    };
    String str = skinny;
    Board board = Board.fromSaveString(skinny);
    assertBoardHasGems(gems, board, "Save String:\n"+str);
  }
  @Test(timeout=2000) public void test_board_fromSaveString03 () {
    int [][] gems = {
      { 4, 3, 5, 3, 5, 1, 3, 3, 1, 1},
      { 2, 4, 1, 4, 1, 4, 4, 5, 4, 2},
      { 5, 3, 5, 2, 3, 3, 3, 1, 3, 4},
      { 1, 2, 2, 2, 2, 4, 2, 4, 5, 1},
      { 1, 3, 1, 2, 3, 3, 3, 4, 1, 1},
      { 2, 2, 2, 1, 5, 1, 5, 5, 3, 5},
      { 2, 4, 1, 5, 2, 2, 5, 4, 5, 2},
    };
    String str =
      "4  3  5  3  5  1  3  3  1  1 \n"+
      "2  4  1  4  1  4  4  5  4  2 \n"+
      "5  3  5  2  3  3  3  1  3  4 \n"+
      "1  2  2  2  2  4  2  4  5  1 \n"+
      "1  3  1  2  3  3  3  4  1  1 \n"+
      "2  2  2  1  5  1  5  5  3  5 \n"+
      "2  4  1  5  2  2  5  4  5  2 \n"+
      "";
    Board board = Board.fromSaveString(str);
    assertBoardHasGems(gems, board, "Save String:\n"+str);
  }
  @Test(timeout=2000) public void test_board_fromSaveString04 () {
    String str =
      "3 . 2 \n"+
      "3 . . \n"+
      "1 . 1 \n"+
      "2 2 2 \n"+
      "2 1 . \n"+
      "1 1 2 \n"+
      ". 1 1 \n"+
      "";
    int [][] gems = {
      { 3,-1, 2},
      { 3,-1,-1},
      { 1,-1, 1},
      { 2, 2, 2},
      { 2, 1,-1},
      { 1, 1, 2},
      {-1, 1, 1},
    };
    Board board = Board.fromSaveString(str);
    assertBoardHasGems(gems, board, "Save String:\n"+str);
  }
  @Test(timeout=2000) public void test_board_fromSaveString05 () {
    String str =
      "3 . 2 . \n"+
      "3 . . . \n"+
      "1 . 1 1 \n"+
      "2 . 2 3 \n"+
      "2 . . 4 \n"+
      ". . 1 1 \n"+
      "";
    int [][] gems = {
      { 3,-1, 2,-1},
      { 3,-1,-1,-1},
      { 1,-1, 1, 1},
      { 2,-1, 2, 3},
      { 2,-1,-1, 4},
      {-1,-1, 1, 1},
    };
    Board board = Board.fromSaveString(str);
    assertBoardHasGems(gems, board, "Save String:\n"+str);
  }

  ////////////////////////////////////////////////////////////////////////////////
  // saveString
  @Test(timeout=2000) public void test_board_saveString01 () {
    int [][] gems = {
      {1, 3, 2},
      {1, 1, 1},
      {1, 2, 2},
    };
    String str = tiny;
    Board board = new Board(gems);
    String msg = String.format("\nExpect saveString:\n%s\nActual saveString:\n%s\nNote each row should end with a space\nBoard:\n%s",
                               str,board.saveString(),board.toString());
    assertEquals(msg,str,board.saveString());
  }
  @Test(timeout=2000) public void test_board_saveString02 () {
    int [][] gems = {
      { 3, 3, 2},
      { 3, 2, 3},
      { 1, 2, 1},
      { 2, 2, 2},
      { 2, 1, 3},
      { 1, 1, 2},
      { 2, 1, 1},
    };
    String str = skinny;
    Board board = new Board(gems);
    String msg = String.format("\nExpect saveString:\n%s\nActual saveString:\n%s\nNote each row should end with a space\nBoard:\n%s",
                               str,board.saveString(),board.toString());
    assertEquals(msg,str,board.saveString());
  }
  @Test(timeout=2000) public void test_board_saveString03 () {
    int [][] gems = {
      { 4, 3, 5, 3, 5, 1, 3, 3, 1, 1},
      { 2, 4, 1, 4, 1, 4, 4, 5, 4, 2},
      { 5, 3, 5, 2, 3, 3, 3, 1, 3, 4},
      { 1, 2, 2, 2, 2, 4, 2, 4, 5, 1},
      { 1, 3, 1, 2, 3, 3, 3, 4, 1, 1},
      { 2, 2, 2, 1, 5, 1, 5, 5, 3, 5},
      { 2, 4, 1, 5, 2, 2, 5, 4, 5, 2},
    };
    String str =
      "4 3 5 3 5 1 3 3 1 1 \n"+
      "2 4 1 4 1 4 4 5 4 2 \n"+
      "5 3 5 2 3 3 3 1 3 4 \n"+
      "1 2 2 2 2 4 2 4 5 1 \n"+
      "1 3 1 2 3 3 3 4 1 1 \n"+
      "2 2 2 1 5 1 5 5 3 5 \n"+
      "2 4 1 5 2 2 5 4 5 2 \n"+
      "";
    Board board = new Board(gems);
    String msg = String.format("\nExpect saveString:\n%s\nActual saveString:\n%s\nNote each row should end with a space\nBoard:\n%s",
                               str,board.saveString(),board.toString());
    assertEquals(msg,str,board.saveString());
  }
  @Test(timeout=2000) public void test_board_saveString04 () {
    int [][] gems = {
      { 3,-1, 2},
      { 3,-1,-1},
      { 1,-1, 1},
      { 2, 2, 2},
      { 2, 1,-1},
      { 1, 1, 2},
      {-1, 1, 1},
    };
    String str =
      "3 . 2 \n"+
      "3 . . \n"+
      "1 . 1 \n"+
      "2 2 2 \n"+
      "2 1 . \n"+
      "1 1 2 \n"+
      ". 1 1 \n"+
      "";
    Board board = new Board(gems);
    String msg = String.format("\nExpect saveString:\n%s\nActual saveString:\n%s\nNote each row should end with a space\nBoard:\n%s",
                               str,board.saveString(),board.toString());
    assertEquals(msg,str,board.saveString());
  }
  @Test(timeout=2000) public void test_board_saveString05 () {
    int [][] gems = {
      { 3,-1, 2,-1},
      { 3,-1,-1,-1},
      { 1,-1, 1, 1},
      { 2,-1, 2, 3},
      { 2,-1,-1, 4},
      {-1,-1, 1, 1},
    };
    String str =
      "3 . 2 . \n"+
      "3 . . . \n"+
      "1 . 1 1 \n"+
      "2 . 2 3 \n"+
      "2 . . 4 \n"+
      ". . 1 1 \n"+
      "";
    Board board = new Board(gems);
    String msg = String.format("\nExpect saveString:\n%s\nActual saveString:\n%s\nNote each row should end with a space\nBoard:\n%s",
                               str,board.saveString(),board.toString());
    assertEquals(msg,str,board.saveString());
  }



  ////////////////////////////////////////////////////////////////////////////////
  // Tests for clone
  @Test(timeout=2000) public void test_board_clone01 () {
    Board board = Board.fromSaveString(tiny);
    Board clone = board.clone();
    for(int i=0; i<board.getRows(); i++){
      for(int j=0; j<board.getCols(); j++){
	Gem bg = board.gemAt(i,j);
	Gem cg = clone.gemAt(i,j);
	assert(bg.sameKind(cg));
	assert(bg != cg);
	bg.clearFlag();
	cg.setFlag();
	assert(!bg.flagged());
	assert(cg.flagged());
      }
    }
  }
  @Test(timeout=2000) public void test_board_clone02 () {
    Board board = Board.fromSaveString(skinny);
    Board clone = board.clone();
    for(int i=0; i<board.getRows(); i++){
      for(int j=0; j<board.getCols(); j++){
	Gem bg = board.gemAt(i,j);
	Gem cg = clone.gemAt(i,j);
	assert(bg.sameKind(cg));
	assert(bg != cg);
	bg.clearFlag();
	cg.setFlag();
	assert(!bg.flagged());
	assert(cg.flagged());
      }
    }
  }
  @Test(timeout=2000) public void test_board_clone1 () {
    int[][] gems = {
      {1, 1, 1, 0, 2},
      {2, 0, 2, 1, 1}
    };
    Board b1 = new Board ( gems );
    Board b2 = b1.clone();
    assertBoardsIdentical(b1, b2);
    assertBoardsDistinct(b1, b2);
  }
  @Test(timeout=2000) public void test_board_clone2 () {
    int[][] gems = new int[][]{
      {  9,  4,  4,  5,  5,},
      {  2,  5,  6,  5,  5,},
      {  7,  2,  5,  8,  1,},
      {  3,  7,  8,  5,  1,},
      {  8,  7,  5,  8,  8,},
    };
    Board b1 = new Board ( gems );
    Board b2 = b1.clone();
    assertBoardsIdentical(b1, b2);
    assertBoardsDistinct(b1, b2);
  }
  @Test(timeout=2000) public void test_board_clone3 () {
    int[][] gems = {
      { 11,  7, 11,},
      { 13,  2,  2,},
      {  2,  3,  4,},
      {  4,  2, 13,},
      {  6, 14,  6,},
      {  6, 11,  9,},
      {  5,  4, 11,},
    };
    Board b1 = new Board ( gems );
    Board b2 = b1.clone();
    assertBoardsIdentical(b1, b2);
    assertBoardsDistinct(b1, b2);
  }

  ////////////////////////////////////////////////////////////////////////////////
  // Utilities to assist in marking and removing gems from a board

  // Flag the gems marked in mask
  public static void applyMask(int [][]mask, Board b){
    b.clearFlags();
    for(int i=0; i<b.getRows(); i++){
      for(int j=0; j<b.getCols(); j++){
        if(mask[i][j] > 0){
          b.gemAt(i,j).setFlag();
        }
      }
    }
  }
  // Assert that the gems in mask are flagged in board
  public static void assertMask(int [][]mask, Board b){
    String msg;
    for(int i=0; i<b.getRows(); i++){
      for(int j=0; j<b.getCols(); j++){
        if(mask[i][j] > 0){
          msg = String.format("Problem at position %d %d\nFlag should be set\n",
        		    i,j);
          assertTrue(msg,b.gemAt(i,j).flagged());
        }
        else{
          msg = String.format("Problem at position %d %d\nFlag should be clear\n",
        		    i,j);
          assertFalse(msg,b.gemAt(i,j).flagged());
        }
      }
    }
  }
  // Convert a 2d int array mask to a string like a board; don't need
  // to rely on student code to create a board then
  public static String mask2str(int [][] g2d){
    StringBuilder sb = new StringBuilder();
    int rows=g2d.length, cols=g2d[0].length;
    // Col header
    sb.append("   ");
    for(int j=0; j<cols; j++){
      sb.append(String.format("%3d",j));
    }
    sb.append("\n");
    sb.append("   ");
    for(int j=0; j<cols; j++){
      sb.append("---");
    }
    sb.append("\n");
    for(int i=0; i<rows; i++){
      sb.append(String.format("%2d|",i));
      for(int j=0; j<cols; j++){
        String s = g2d[i][j] > 0 ? "X" : "";
        sb.append(String.format("%3s",s));
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  ////////////////////////////////////////////////////////////////////////////////
  // Tests to mark and remove gems

  // Tests for flagging boards and clearFlags
  @Test(timeout=2000) public void test_board_flag1 () {
    int [][] gems = new int[][]{
      {1, 1, 1,-1, 2},
      {2,-1, 2, 1, 1}
    };
    int mask[][] = new int[][]{
      {1, 0, 1, 0, 0},
      {0, 1, 0, 0, 1}
    };
    Board b1 = new Board ( gems );
    applyMask(mask,b1);
    assertMask(mask,b1);
    mask = new int[b1.getRows()][b1.getCols()]; // Empty mask
    b1.clearFlags();
    assertMask(mask,b1);
  }
  @Test(timeout=2000) public void test_board_flag2 () {
    int[][] gems = {
      { 11,  7, 11,},
      { 13,  2,  2,},
      {  2,  3,  4,},
      {  4,  2, 13,},
      {  6, 14,  6,},
      {  6, 11,  9,},
      {  5,  4, 11,},
    };
    int mask[][] = new int[][]{
      { 0, 0, 1,},
      { 1, 0, 0,},
      { 1, 0, 0,},
      { 1, 1, 0,},
      { 0, 0, 1,},
      { 0, 1, 0,},
      { 0, 0, 1,},
    };
    Board b1 = new Board ( gems );
    applyMask(mask,b1);
    assertMask(mask,b1);
    mask = new int[b1.getRows()][b1.getCols()]; // Empty mask
    b1.clearFlags();
    assertMask(mask,b1);
  }

  // Test that cloning preserves flags
  @Test(timeout=2000) public void test_board_flagclone1 () {
    int[][] gems = {
      {1, 1, 1,-1, 2},
      {2,-1, 2, 1, 1}
    };
    int mask[][] = new int[][]{
      {1, 0, 1, 0, 0},
      {0, 1, 0, 0, 1}
    };
    Board b1 = new Board ( gems );
    applyMask(mask,b1);
    Board b2 = b1.clone();
    assertMask(mask,b2);
  }
  @Test(timeout=2000) public void test_board_flagclone2 () {
    int[][] gems = {
      { 11,  7, 11,},
      { 13,  2,  2,},
      {  2,  3,  4,},
      {  4,  2, 13,},
      {  6, 14,  6,},
      {  6, 11,  9,},
      {  5,  4, 11,},
    };
    int mask[][] = new int[][]{
      { 0, 0, 1,},
      { 1, 0, 0,},
      { 1, 0, 0,},
      { 1, 1, 0,},
      { 0, 0, 1,},
      { 0, 1, 0,},
      { 0, 0, 1,},
    };
    Board b1 = new Board ( gems );
    applyMask(mask,b1);
    Board b2 = b1.clone();
    assertMask(mask,b2);
  }



  ////////////////////////////////////////////////////////////////////////////////
  // Check the positions in rcValid which are arrays of 3 
  // 
  // {row, col, 0/1 for valid/invalid}.  
  // 
  // Ensure that the validGemAt() method matches the expected results
  // in rcValid
  public static void assertValid(int [][]rcValid, Board b){
    String msg;
    for(int i=0; i<rcValid.length; i++){
      int row=rcValid[i][0], col=rcValid[i][1];
      boolean valid=rcValid[i][2]>0;
      String validS = valid ? "should be a valid gem" : "should NOT be a valid gem";
      msg = String.format("Problem at position %d %d: position %s\nBoard:\n%s",
        		  row,col,validS,b);
      assertEquals(msg, valid, b.validGemAt(row,col));
    }
  }
      

  ////////////////////////////////////////////////////////////////////////////////
  // Test validGemAt: nonempty and not out of bounds
  @Test(timeout=2000) public void test_board_validGemAt1 () {
    int[][] gems = {
      {1, 1, 1,-1, 2},
      {2,-1, 2, 1, 1}
    };
    // {row, col, 0/1 for valid}
    int [][] rcValid = new int[][]{
      {0,0,1}, {0,3,0}, {0,5,0}, 
      {1,1,0}, {1,2,1}, {1,-1,0}, {2,0,0},
    };
    Board b1 = new Board ( gems );
    assertValid(rcValid, b1);
  }

  @Test(timeout=2000) public void test_board_validGemAt2 () {
    int[][] gems = {
      {  9,  4,  4, -1,  5,},
      {  2, -1,  6,  5,  5,},
      {  7,  2,  5,  8,  1,},
      {  3,  7,  8,  5,  1,},
      {  8, -1, -1, -1,  8,},
    };
    // {row, col, 0/1 for valid}
    int [][] rcValid = new int[][]{
      {0,0,1}, {0,3,0}, {0,4,1}, 
      {4,1,0}, {4,2,0}, {4,4,1}, {-1,-1,0}, {-1,1,0},
    };
    Board b1 = new Board ( gems );
    assertValid(rcValid, b1);
  }

  ////////////////////////////////////////////////////////////////////////////////
  // Tests for doRemovals

  // fromSaveString combined with validGems and doRemovals 
  @Test(timeout=2000) public void board_fromSaveString1(){
    Board b = Board.fromSaveString(skinny);
    assert( b.validGemAt(0,0));
    b.gemAt(0,0).setFlag();
    b.doRemovals();
    assert( ! b.validGemAt(0,0));
  }
  
  // fromSaveString remove all
  @Test(timeout=2000) public void board_fromSaveString2(){
    Board b = Board.fromSaveString(tiny);
    assert ( b.hasValidGem());
    for (int i=0; i<b.getRows(); i++){
      for (int j = 0; j<b.getCols(); j++){
        b.gemAt(i,j).setFlag();
      }
    }
    b.doRemovals();
    assert ( ! b.hasValidGem());
  }

  ////////////////////////////////////////////////////////////////////////////////
  // Small removal tests
  @Test(timeout=2000) public void test_board_doRemovals1() {
    int [][] before = {
      { 5},
    };
    int mask[][] = {
      { 1},
    };
    int [][] expect = {
      {-1},
    };
    Board b = new Board(before);
    applyMask(mask,b);
    b.doRemovals();
    String moreInfo = String.format("Before:\n%s\nMask:\n%s\n",
        			    gem2str(before),mask2str(mask));
    assertBoardHasGems(expect,b,moreInfo);
  }
  @Test(timeout=2000) public void test_board_doRemovals2() {
    int [][] before = {
      {5,6},
      {7,8},
    };
    int mask[][] = new int[][]{
      {1,1},
      {0,0},
    };
    int [][] expect = {
      {-1,-1},
      { 7, 8},
    };
    Board b = new Board(before);
    applyMask(mask,b);
    b.doRemovals();
    String moreInfo = String.format("Before:\n%s\nMask:\n%s\n",
        			    gem2str(before),mask2str(mask));
    assertBoardHasGems(expect,b,moreInfo);
  }
  @Test(timeout=2000) public void test_board_doRemovals3() {
    int [][] before = {
      {5,6},
      {7,8},
    };
    int mask[][] = {
      {1,1},
      {1,1},
    };
    int [][] expect = {
      {-1,-1},
      {-1,-1},
    };
    Board b = new Board(before);
    applyMask(mask,b);
    b.doRemovals();
    String moreInfo = String.format("Before:\n%s\nMask:\n%s\n",
        			    gem2str(before),mask2str(mask));
    assertBoardHasGems(expect,b,moreInfo);
  }

  // Small falling
  @Test(timeout=2000) public void test_board_doRemovals4() {
    int [][] before = {
      {5,6},
      {7,8},
    };
    int mask[][] = {
      {0,0},
      {1,0},
    };
    int [][] expect = {
      {-1, 6},
      { 5, 8},
    };
    Board b = new Board(before);
    applyMask(mask,b);
    b.doRemovals();
    String moreInfo = String.format("Before:\n%s\nMask:\n%s\n",
        			    gem2str(before),mask2str(mask));
    assertBoardHasGems(expect,b,moreInfo);
  }
  @Test(timeout=2000) public void test_board_doRemovals5() {
    int [][] before = {
      {5,6},
      {7,8},
    };
    int mask[][] = {
      {0,0},
      {1,1},
    };
    int [][] expect = {
      {-1,-1},
      { 5, 6},
    };
    Board b = new Board(before);
    applyMask(mask,b);
    b.doRemovals();
    String moreInfo = String.format("Before:\n%s\nMask:\n%s\n",
        			    gem2str(before),mask2str(mask));
    assertBoardHasGems(expect,b,moreInfo);
  }

  // Small shifting
  @Test(timeout=2000) public void test_board_doRemovals6() {
    int [][] before = {
      {5,6},
      {7,8},
    };
    int mask[][] = {
      {1,0},
      {1,0},
    };
    int [][] expect = {
      {6,-1},
      {8,-1},
    };
    Board b = new Board(before);
    applyMask(mask,b);
    b.doRemovals();
    String moreInfo = String.format("Before:\n%s\nMask:\n%s\n",
        			    gem2str(before),mask2str(mask));
    assertBoardHasGems(expect,b,moreInfo);
  }

  // Larger
  // No falling or shifting
  @Test(timeout=2000) public void test_board_doRemovals7() {
    int [][] before = {
      { 1, 2, 3, 4, 5},
      { 7, 8, 9,10,11},
      {12,13,14,15,16} 
    };
    int mask[][] = {
      { 1, 1, 1, 1, 1},
      { 0, 1, 1, 0, 1},
      { 0, 0, 0, 0, 0} 
    };
    int [][] expect = {
      {-1,-1,-1,-1,-1},
      { 7,-1,-1,10,-1},
      {12,13,14,15,16} 
    };
    Board b = new Board(before);
    applyMask(mask,b);
    b.doRemovals();
    String moreInfo = String.format("Before:\n%s\nMask:\n%s\n",
        			    gem2str(before),mask2str(mask));
    assertBoardHasGems(expect,b,moreInfo);
  }
  //  Falling, no shifting
  @Test(timeout=2000) public void test_board_doRemovals8() {
    int [][] before = {
      { 1, 2, 3, 4, 5},
      { 7, 8, 9,10,11},
      {12,13,14,15,16} 
    };
    int mask[][] = {
      { 1, 0, 0, 1, 0},
      { 0, 1, 1, 0, 1},
      { 0, 1, 0, 0, 1} 
    };
    int [][] expect = {
      {-1,-1,-1,-1,-1},
      { 7,-1, 3,10,-1},
      {12, 2,14,15, 5} 
    };
    Board b = new Board(before);
    applyMask(mask,b);
    b.doRemovals();
    String moreInfo = String.format("Before:\n%s\nMask:\n%s\n",
        			    gem2str(before),mask2str(mask));
    assertBoardHasGems(expect,b,moreInfo);
  }
  // Shifting only
  @Test(timeout=2000) public void test_board_doRemovals9() {
    int [][] before = {
      { 1, 2, 3, 4, 5},
      { 7, 8, 9,10,11},
      {12,13,14,15,16} 
    };
    int mask[][] = {
      { 0, 1, 0, 0, 0},
      { 0, 1, 0, 0, 0},
      { 0, 1, 0, 0, 0} 
    };
    int [][] expect = {
      { 1, 3, 4, 5,-1},
      { 7, 9,10,11,-1},
      {12,14,15,16,-1} 
    };
    Board b = new Board(before);
    applyMask(mask,b);
    b.doRemovals();
    String moreInfo = String.format("Before:\n%s\nMask:\n%s\n",
        			    gem2str(before),mask2str(mask));
    assertBoardHasGems(expect,b,moreInfo);
  }
  // Shifting only
  @Test(timeout=2000) public void test_board_doRemovals10() {
    int [][] before = {
      { 1, 2, 3, 4, 5},
      { 7, 8, 9,10,11},
      {12,13,14,15,16} 
    };
    int mask[][] = {
      { 1, 0, 1, 1, 0},
      { 1, 0, 1, 1, 0},
      { 1, 0, 1, 1, 0} 
    };
    int [][] expect = {
      { 2, 5,-1,-1,-1},
      { 8,11,-1,-1,-1},
      {13,16,-1,-1,-1} 
    };
    Board b = new Board(before);
    applyMask(mask,b);
    b.doRemovals();
    String moreInfo = String.format("Before:\n%s\nMask:\n%s\n",
        			    gem2str(before),mask2str(mask));
    assertBoardHasGems(expect,b,moreInfo);
  }
  // Falling and shifting
  @Test(timeout=2000) public void test_board_doRemovals11() {
    int [][] before = {
      { 1, 2, 3, 4, 5},
      { 7, 8, 9,10,11},
      {12,13,14,15,16} 
    };
    int mask[][] = {
      { 1, 0, 1, 1, 0},
      { 0, 0, 1, 1, 0},
      { 1, 0, 1, 0, 1} 
    };
    int [][] expect = {
      {-1, 2,-1,-1,-1},
      {-1, 8,-1, 5,-1},
      { 7,13,15,11,-1} 
    };
    Board b = new Board(before);
    applyMask(mask,b);
    b.doRemovals();
    String moreInfo = String.format("Before:\n%s\nMask:\n%s\n",
        			    gem2str(before),mask2str(mask));
    assertBoardHasGems(expect,b,moreInfo);
  }
  // Falling and shifting on skinny
  @Test(timeout=2000) public void test_board_doRemovals12() {
    int [][] before = {
      { 1, 2, 3},
      { 4, 5, 6},
      { 7, 8, 9},
      {10,11,12},
      {13,14,15},
      {16,17,18},
      {19,20,21},
    };
    int mask[][] = {
      { 0, 1, 1},
      { 0, 1, 0},
      { 1, 1, 1},
      { 0, 1, 1},
      { 0, 1, 1},
      { 1, 1, 0},
      { 1, 1, 0},
    };
    int [][] expect = {
      {-1,-1,-1},
      {-1,-1,-1},
      {-1,-1,-1},
      { 1,-1,-1},
      { 4, 6,-1},
      {10,18,-1},
      {13,21,-1},
    };
    Board b = new Board(before);
    applyMask(mask,b);
    b.doRemovals();
    String moreInfo = String.format("Before:\n%s\nMask:\n%s\n",
        			    gem2str(before),mask2str(mask));
    assertBoardHasGems(expect,b,moreInfo);
  }
  @Test(timeout=2000) public void test_board_doRemovals13() {
    int [][] before = {
      {1, 1, 1, 1, 2},
      {2, 1, 2, 1, 1}
    };
    int mask[][] = {
      {1, 0, 1, 0, 0},
      {0, 1, 0, 0, 1}
    };
    int [][] expect = {
      {-1,-1,-1, 1,-1},
      { 2, 1, 2, 1, 2}
    };
    Board b = new Board(before);
    applyMask(mask,b);
    b.doRemovals();
    String moreInfo = String.format("Before:\n%s\nMask:\n%s\n",
        			    gem2str(before),mask2str(mask));
    assertBoardHasGems(expect,b,moreInfo);
  }


  // Multiple removals in a row
  @Test(timeout=2000) public void test_board_multiple_removals01() {
    int [][] gems = {
      { 1, 2, 3},
      { 4, 5, 6},
      { 7, 8, 9},
      {10,11,12},
      {13,14,15},
      {16,17,18},
      {19,20,21},
    };
    int mask[][] = {
      { 0, 1, 1},
      { 1, 0, 0},
      { 0, 1, 1},
      { 0, 0, 1},
      { 1, 0, 1},
      { 0, 0, 0},
      { 1, 0, 0},
    };
    Board b = new Board(gems);
    applyMask(mask,b);
    String before = b.toString();
    b.doRemovals();
    String expect =
      "     0  1  2\n"+
      "   ---------\n"+
      " 0|          \n"+
      " 1|          \n"+
      " 2|     5    \n"+
      " 3|  1 11    \n"+
      " 4|  7 14  6 \n"+
      " 5| 10 17 18 \n"+
      " 6| 16 20 21 \n"+
      "";
    String actual = b.toString();
    String msg =
      String.format("\nBefore removals:\n%s\nMask:\n%s\nExpect:\n%s\nActual:\n%s\n",
                    before,mask2str(mask),expect,actual);
    assertEquals(msg,expect,actual);

    before = actual;
    mask = new int[][]{
      { 0, 0, 0},
      { 0, 0, 0},
      { 0, 0, 0},
      { 0, 1, 0},
      { 1, 1, 0},
      { 1, 0, 0},
      { 0, 1, 0},
    };
    applyMask(mask,b);
    b.doRemovals();
    expect =
      "     0  1  2\n"+
      "   ---------\n"+
      " 0|          \n"+
      " 1|          \n"+
      " 2|          \n"+
      " 3|          \n"+
      " 4|        6 \n"+
      " 5|  1  5 18 \n"+
      " 6| 16 17 21 \n"+
      "";
    actual = b.toString();
    msg =
      String.format("\nBefore removals:\n%s\nMask:\n%s\nExpect:\n%s\nActual:\n%s\n",
                    before,mask2str(mask),expect,actual);
    assertEquals(msg,expect,actual);

    before = actual;
    mask = new int[][]{
      { 0, 0, 0},
      { 0, 0, 0},
      { 0, 0, 0},
      { 0, 0, 0},
      { 0, 0, 0},
      { 1, 1, 1},
      { 1, 1, 0},
    };
    applyMask(mask,b);
    b.doRemovals();
    expect =
      "     0  1  2\n"+
      "   ---------\n"+
      " 0|          \n"+
      " 1|          \n"+
      " 2|          \n"+
      " 3|          \n"+
      " 4|          \n"+
      " 5|  6       \n"+
      " 6| 21       \n"+
      "";
    actual = b.toString();
    msg =
      String.format("\nBefore removals:\n%s\nMask:\n%s\nExpect:\n%s\nActual:\n%s\n",
                    before,mask2str(mask),expect,actual);
    assertEquals(msg,expect,actual);
  }
}
