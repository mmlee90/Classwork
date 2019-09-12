import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class WholeRowColRemovalPolicyTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("WholeRowColRemovalPolicyTests");
  }
  

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

  @Test(timeout=2000) public void test_description(){
    WholeRowColRemovalPolicy wrcgp = new WholeRowColRemovalPolicy();
    assertEquals("Adjacent gems in whole row/column will be removed",wrcgp.description());
  }

  @Test(timeout=2000) public void test_wrcgp_steps0() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = Board.fromSaveString(cross);
    // no-moves check.
    int [][] next = new int[][]{{1,1,2,1,1},{1,1,2,1,1},{2,2,2,2,2},{1,1,2,1,1},{1,1,2,1,1}};
    assertBoardHasGems(next,b);
  }
  
  @Test(timeout=2000) public void test_wrcgp_steps1() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = Board.fromSaveString(cross);
    
    p.flagConnectedGems(0,4,b);
    b.doRemovals();
    int [][] next = new int[][]{{1,1,2,-1,-1},{1,1,2,1,-1},{2,2,2,2,2},{1,1,2,1,1},{1,1,2,1,1}};
    assertBoardHasGems(next,b);
  }
  
  @Test(timeout=2000) public void test_wrcgp_steps2() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = Board.fromSaveString(cross);
    
    p.flagConnectedGems(2,4,b);
    b.doRemovals();
    int [][] next = new int[][]{{-1,-1,-1,-1,-1},{1,1,2,1,1},{1,1,2,1,1},{1,1,2,1,1},{1,1,2,1,1}};
    assertBoardHasGems(next,b);
  }
  
  @Test(timeout=2000) public void test_wrcgp_steps3() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = Board.fromSaveString(cross);
    
    p.flagConnectedGems(0,1,b);
    b.doRemovals();
    int [][] next = new int[][]{{-1,-1,2,1,1},{1,-1,2,1,1},{2,2,2,2,2},{1,1,2,1,1},{1,1,2,1,1}};
    assertBoardHasGems(next,b);
  }
  
  @Test(timeout=2000) public void test_wrcgp_steps4() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = Board.fromSaveString(cross);
    
    p.flagConnectedGems(4,0,b);
    b.doRemovals();
    int [][] next = new int[][]{{-1,-1,2,1,1},{-1,1,2,1,1},{1,1,2,2,2},{1,2,2,1,1},{2,1,2,1,1}};
    assertBoardHasGems(next,b);
  }
  
  @Test(timeout=2000) public void test_wrcgp_steps5() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = Board.fromSaveString(cross);
    
    p.flagConnectedGems(1,2,b);
    b.doRemovals();
    int [][] next = new int[][]{{1,1,1,1,-1},{1,1,1,1,-1},{2,2,2,2,-1},{1,1,1,1,-1},{1,1,1,1,-1}};
    assertBoardHasGems(next,b);
  }
  
  @Test(timeout=2000) public void test_wrcgp_steps6() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = Board.fromSaveString(cross);
    
    p.flagConnectedGems(2,2,b);
    b.doRemovals();
    int [][] next = new int[][]{{-1,-1,-1,-1,-1},{1,1,1,1,-1},{1,1,1,1,-1},{1,1,1,1,-1},{1,1,1,1,-1}};
    assertBoardHasGems(next,b);
  }
  
  @Test(timeout=2000) public void test_wrcgp_steps7() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = Board.fromSaveString(almostCross);
    
    p.flagConnectedGems(2,2,b);
    b.doRemovals();
    int [][] next = new int[][]{{1,1,-1,1,1},{1,1,2,1,1},{2,2,2,2,2},{1,1,2,1,1},{1,1,2,1,1}};
    assertBoardHasGems(next,b);
  }

  @Test(timeout=2000) public void test_wrcgp_steps8() {
    RemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = Board.fromSaveString(skinny);
    String before = b.toString();
    
    int row=5,col=1;
    p.flagConnectedGems(row,col,b);
    b.doRemovals();
    String expect =
      "     0  1  2\n"+
      "   ---------\n"+
      " 0|        2 \n"+
      " 1|  3     3 \n"+
      " 2|  3     1 \n"+
      " 3|  1  3  2 \n"+
      " 4|  2  2  3 \n"+
      " 5|  2  2  2 \n"+
      " 6|  2  2  1 \n"+
      "";   
    String actual = b.toString();      
    String msg =
      String.format("\nAdjacentRemoval at (%d,%d) yields wrong result\nbefore:\n%s\nexpect:\n%s\nactual:\n%s\n",
                    row,col,before,expect,actual);
    assertEquals(msg,expect,actual);

    before = actual;
    row=5;col=0;
    p.flagConnectedGems(row,col,b);
    b.doRemovals();
    expect =
      "     0  1  2\n"+
      "   ---------\n"+
      " 0|          \n"+
      " 1|        2 \n"+
      " 2|        3 \n"+
      " 3|        1 \n"+
      " 4|  3  3  2 \n"+
      " 5|  3  2  3 \n"+
      " 6|  1  2  1 \n"+
      "";   
    actual = b.toString();      
    msg =
      String.format("\nAdjacentRemoval at (%d,%d) yields wrong result\nbefore:\n%s\nexpect:\n%s\nactual:\n%s\n",
                    row,col,before,expect,actual);
    assertEquals(msg,expect,actual);

  }

  @Test(timeout=2000) public void test_WholeRowColRemovalPolicy_connected1() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = new Board(new int[][]{
	{  1,  3,  2,},
	{  1,  1,  1,},
	{  1,  2,  2,},
      });
    int r=2, c=0;
    int mask[][] = (new int[][]{
	{  1,  0,  0,},
	{  1,  0,  0,},
	{  1,  0,  0,},
      });
    b.clearFlags();
    p.flagConnectedGems(r,c,b);
    assertMask(mask,b);
  }
  @Test(timeout=2000) public void test_WholeRowColRemovalPolicy_connected2() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = new Board(new int[][]{
	{  1,  3,  2,},
	{  1,  1,  1,},
	{  1,  2,  2,},
      });
    int r=1, c=0;
    int mask[][] = (new int[][]{
	{  1,  0,  0,},
	{  1,  1,  1,},
	{  1,  0,  0,},
      });
    b.clearFlags();
    p.flagConnectedGems(r,c,b);
    assertMask(mask,b);
  }
  @Test(timeout=2000) public void test_WholeRowColRemovalPolicy_connected3() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = new Board(new int[][]{
	{  1,  1,  2,  1,  1,},
	{  1,  1,  2,  1,  1,},
	{  2,  2,  2,  2,  2,},
	{  1,  1,  2,  1,  1,},
	{  1,  1,  2,  1,  1,},
      });
    int r=0, c=1;
    int mask[][] = (new int[][]{
	{  1,  1,  0,  0,  0,},
	{  0,  1,  0,  0,  0,},
	{  0,  0,  0,  0,  0,},
	{  0,  0,  0,  0,  0,},
	{  0,  0,  0,  0,  0,},
      });
    b.clearFlags();
    p.flagConnectedGems(r,c,b);
    assertMask(mask,b);
  }
  @Test(timeout=2000) public void test_WholeRowColRemovalPolicy_connected4() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = new Board(new int[][]{
	{  1,  1,  2,  1,  1,},
	{  1,  1,  2,  1,  1,},
	{  2,  2,  2,  2,  2,},
	{  1,  1,  2,  1,  1,},
	{  1,  1,  2,  1,  1,},
      });
    int r=3, c=2;
    int mask[][] = (new int[][]{
	{  0,  0,  1,  0,  0,},
	{  0,  0,  1,  0,  0,},
	{  0,  0,  1,  0,  0,},
	{  0,  0,  1,  0,  0,},
	{  0,  0,  1,  0,  0,},
      });
    b.clearFlags();
    p.flagConnectedGems(r,c,b);
    assertMask(mask,b);
  }
  @Test(timeout=2000) public void test_WholeRowColRemovalPolicy_connected5() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = new Board(new int[][]{
	{  1,  1,  2,  1,  1,},
	{  1,  1,  2,  1,  1,},
	{  2,  2,  2,  2,  2,},
	{  1,  1,  2,  1,  1,},
	{  1,  1,  2,  1,  1,},
      });
    int r=2, c=2;
    int mask[][] = (new int[][]{
	{  0,  0,  1,  0,  0,},
	{  0,  0,  1,  0,  0,},
	{  1,  1,  1,  1,  1,},
	{  0,  0,  1,  0,  0,},
	{  0,  0,  1,  0,  0,},
      });
    b.clearFlags();
    p.flagConnectedGems(r,c,b);
    assertMask(mask,b);
  }
  @Test(timeout=2000) public void test_WholeRowColRemovalPolicy_connected6() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = new Board(new int[][]{
 //            x
 //	 0 1 2 3 4 5 6 7 8 9
	{4,3,5,3,5,1,3,3,1,1,},	// 0
	{2,4,1,4,1,4,4,5,4,2,},	// 1
	{5,3,5,2,3,3,3,1,3,4,},	// 2
	{1,2,2,2,2,4,2,4,5,1,},	// 3 x
	{1,3,1,2,3,3,3,4,1,1,},	// 4
	{2,2,2,2,5,1,5,5,3,2,},	// 5
	{2,4,1,5,2,2,5,4,5,2,},	// 6    
      });
    int r=3, c=3;
    int mask[][] = (new int[][]{
 //            x
 //	 0 1 2 3 4 5 6 7 8 9
	{0,0,0,0,0,0,0,0,0,0,},	// 0	
	{0,0,0,0,0,0,0,0,0,0,},	// 1	
	{0,0,0,1,0,0,0,0,0,0,},	// 2	
	{0,1,1,1,1,0,0,0,0,0,},	// 3 x	
	{0,0,0,1,0,0,0,0,0,0,},	// 4	
	{0,0,0,1,0,0,0,0,0,0,},	// 5	
	{0,0,0,0,0,0,0,0,0,0,},	// 6    
      });
    b.clearFlags();
    p.flagConnectedGems(r,c,b);
    assertMask(mask,b);
  }
  @Test(timeout=2000) public void test_WholeRowColRemovalPolicy_connected7() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = new Board(new int[][]{
 //                        x
 //	 0 1 2 3 4 5 6 7 8 9
	{4,3,5,3,5,1,3,3,1,1,},	// 0
	{2,4,1,4,1,4,4,5,4,1,},	// 1
	{5,3,5,2,3,3,3,1,3,1,},	// 2
	{1,2,2,2,2,4,2,4,5,1,},	// 3
	{1,3,1,2,3,3,3,4,1,1,},	// 4 x
	{2,2,2,2,5,1,5,5,3,2,},	// 5
	{2,4,1,5,2,2,5,4,5,2,},	// 6    
      });
    int r=4, c=9;
    int mask[][] = (new int[][]{
 //                        x
 //	 0 1 2 3 4 5 6 7 8 9
	{0,0,0,0,0,0,0,0,0,1,},	// 0
	{0,0,0,0,0,0,0,0,0,1,},	// 1
	{0,0,0,0,0,0,0,0,0,1,},	// 2
	{0,0,0,0,0,0,0,0,0,1,},	// 3
	{0,0,0,0,0,0,0,0,1,1,},	// 4 x
	{0,0,0,0,0,0,0,0,0,0,},	// 5
	{0,0,0,0,0,0,0,0,0,0,},	// 6    
      });
    b.clearFlags();
    p.flagConnectedGems(r,c,b);
    assertMask(mask,b);
  }

  // Scoring
  @Test(timeout=2000) public void test_WholeRowColRemovalPolicy_score1() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = new Board(new int[][]{
	{ 1, 1, 2,},
	{ 2, 1, 1,},
	{ 2, 2, 1,},
      });
    int r=0, c=2, expect=1;
    b.clearFlags();
    int score = p.scoreMove(r,c,b);
    String msg = 
      String.format("Move %d %d\nExpected %d got %d\nBoard:\n%s\n",
		    r,c,expect,score,b);
    assertEquals(msg,expect,score);
  }
  @Test(timeout=2000) public void test_WholeRowColRemovalPolicy_score2() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = new Board(new int[][]{
	{ 1, 1, 2,},
	{ 2, 1, 1,},
	{ 2, 2, 1,},
      });
    int r=0, c=1, expect=9;
    b.clearFlags();
    int score = p.scoreMove(r,c,b);
    String msg = 
      String.format("Move %d %d\nExpected %d got %d\nBoard:\n%s\n",
		    r,c,expect,score,b);
    assertEquals(msg,expect,score);
  }
  @Test(timeout=2000) public void test_WholeRowColRemovalPolicy_score3() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = new Board(new int[][]{
	{ 1, 1, 2,},
	{ 2, 1, 1,},
	{ 2, 2, 1,},
      });
    int r=2, c=2, expect=4;
    b.clearFlags();
    int score = p.scoreMove(r,c,b);
    String msg = 
      String.format("Move %d %d\nExpected %d got %d\nBoard:\n%s\n",
		    r,c,expect,score,b);
    assertEquals(msg,expect,score);
  }
  @Test(timeout=2000) public void test_WholeRowColRemovalPolicy_score4() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = new Board(new int[][]{
	{ 1, 1, 2,},
	{ 2, 1, 1,},
	{ 2, 2, 1,},
      });
    int r=2, c=0, expect=9;
    b.clearFlags();
    int score = p.scoreMove(r,c,b);
    String msg = 
      String.format("Move %d %d\nExpected %d got %d\nBoard:\n%s\n",
		    r,c,expect,score,b);
    assertEquals(msg,expect,score);
  }
  @Test(timeout=2000) public void test_WholeRowColRemovalPolicy_score5() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = new Board(new int[][]{
	{ 1, 1, 2, 1, 1,},
	{ 1, 1, 2, 1, 1,},
	{ 2, 2, 2, 2, 2,},
	{ 1, 1, 2, 1, 1,},
	{ 1, 1, 2, 1, 1,},
      });
    int r=2, c=2, expect=81;
    b.clearFlags();
    int score = p.scoreMove(r,c,b);
    String msg = 
      String.format("Move %d %d\nExpected %d got %d\nBoard:\n%s\n",
		    r,c,expect,score,b);
    assertEquals(msg,expect,score);
  }
  @Test(timeout=2000) public void test_WholeRowColRemovalPolicy_score6() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = new Board(new int[][]{
	{ 1, 1, 2, 1, 1,},
	{ 1, 1, 2, 1, 1,},
	{ 2, 2, 2, 2, 2,},
	{ 1, 1, 2, 1, 1,},
	{ 1, 1, 2, 1, 1,},
      });
    int r=0, c=0, expect=9;
    b.clearFlags();
    int score = p.scoreMove(r,c,b);
    String msg = 
      String.format("Move %d %d\nExpected %d got %d\nBoard:\n%s\n",
		    r,c,expect,score,b);
    assertEquals(msg,expect,score);
  }
  @Test(timeout=2000) public void test_WholeRowColRemovalPolicy_score7() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = new Board(new int[][]{
	{ 1, 1, 2, 1, 1,},
	{ 1, 1, 2, 1, 1,},
	{ 2, 2, 2, 2, 2,},
	{ 1, 1, 2, 1, 1,},
	{ 1, 1, 2, 1, 1,},
      });
    int r=0, c=4, expect=9;
    b.clearFlags();
    int score = p.scoreMove(r,c,b);
    String msg = 
      String.format("Move %d %d\nExpected %d got %d\nBoard:\n%s\n",
		    r,c,expect,score,b);
    assertEquals(msg,expect,score);
  }
  @Test(timeout=2000) public void test_WholeRowColRemovalPolicy_score8() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = new Board(new int[][]{
	{ 1, 1, 2, 1, 1,},
	{ 1, 1, 2, 1, 1,},
	{ 2, 2, 2, 2, 2,},
	{ 1, 1, 2, 1, 1,},
	{ 1, 1, 2, 1, 1,},
      });
    int r=4, c=0, expect=9;
    b.clearFlags();
    int score = p.scoreMove(r,c,b);
    String msg = 
      String.format("Move %d %d\nExpected %d got %d\nBoard:\n%s\n",
		    r,c,expect,score,b);
    assertEquals(msg,expect,score);
  }
  @Test(timeout=2000) public void test_WholeRowColRemovalPolicy_score9() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = new Board(new int[][]{
	{ 1, 1, 2, 1, 1,},
	{ 1, 1, 2, 1, 1,},
	{ 2, 2, 2, 2, 2,},
	{ 1, 1, 2, 1, 1,},
	{ 1, 1, 2, 1, 1,},
      });
    int r=4, c=4, expect=9;
    b.clearFlags();
    int score = p.scoreMove(r,c,b);
    String msg = 
      String.format("Move %d %d\nExpected %d got %d\nBoard:\n%s\n",
		    r,c,expect,score,b);
    assertEquals(msg,expect,score);
  }
  @Test(timeout=2000) public void test_WholeRowColRemovalPolicy_score10() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = new Board(new int[][]{
	{ 1, 1, 2, 1, 1,},
	{ 1, 1, 2, 1, 1,},
	{ 2, 2, 1, 2, 2,},
	{ 1, 1, 2, 1, 1,},
	{ 1, 1, 2, 1, 1,},
      });
    int r=2, c=2, expect=1;
    b.clearFlags();
    int score = p.scoreMove(r,c,b);
    String msg = 
      String.format("Move %d %d\nExpected %d got %d\nBoard:\n%s\n",
		    r,c,expect,score,b);
    assertEquals(msg,expect,score);
  }
  @Test(timeout=2000) public void test_WholeRowColRemovalPolicy_score11() {
    WholeRowColRemovalPolicy p = new WholeRowColRemovalPolicy();
    Board b = new Board(new int[][]{
	{ 1, 1, 2, 1, 1,},
	{ 1, 1, 2, 1, 1,},
	{ 2, 2, 2, 2, 2,},
	{ 1, 1, 1, 1, 1,},
	{ 1, 1, 2, 1, 1,},
      });
    int r=2, c=2, expect=49;
    b.clearFlags();
    int score = p.scoreMove(r,c,b);
    String msg = 
      String.format("Move %d %d\nExpected %d got %d\nBoard:\n%s\n",
		    r,c,expect,score,b);
    assertEquals(msg,expect,score);
  }

}
