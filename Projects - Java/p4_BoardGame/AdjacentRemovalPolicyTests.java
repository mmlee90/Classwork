import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class AdjacentRemovalPolicyTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("AdjacentRemovalPolicyTests");
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

  @Test(timeout=2000) public void test_description(){
    AdjacentRemovalPolicy arp = new AdjacentRemovalPolicy();
    assertEquals("Adjacent gems of the same kind will be removed",arp.description());
  }

  @Test(timeout=2000) public void test_arp_steps1() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
    Board b = Board.fromSaveString(tiny);
    
    int [][] next= new int[][]{{1,3,2},{1,1,1},{1,2,2}};
    assertBoardHasGems(next,b);
  }
  
  @Test(timeout=2000) public void test_arp_steps2() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
    Board b = Board.fromSaveString(tiny);
    
    p.flagConnectedGems(2,0,b);
    b.doRemovals();
    int [][] next = new int[][]{{-1,3,2},{-1,1,1},{1,2,2}};
    assertBoardHasGems(next,b);
  }
  
  @Test(timeout=2000) public void test_arp_steps3() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
    Board b = Board.fromSaveString(tiny);
    
    b = Board.fromSaveString(tiny);
    p.flagConnectedGems(2,1,b);
    b.doRemovals();
    int [][] next = new int[][]{{1,-1,-1},{1,3,2},{1,1,1}};
    assertBoardHasGems(next,b);
  }
  
  @Test(timeout=2000) public void test_arp_steps4() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
    Board b = Board.fromSaveString(tiny);
    
    b = Board.fromSaveString(tiny);
    p.flagConnectedGems(2,2,b);
    b.doRemovals();
    int [][] next = new int[][]{{1,-1,-1},{1,3,2},{1,1,1}};
    assertBoardHasGems(next,b);
  }
  
  @Test(timeout=2000) public void test_arp_steps5() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
    Board b = Board.fromSaveString(tiny);
    
    b = Board.fromSaveString(tiny);
    p.flagConnectedGems(1,0,b);
    b.doRemovals();
    int [][] next = new int[][]{{-1,2,-1},{3,1,-1},{2,2,-1}};
    assertBoardHasGems(next,b);
  }
  
  @Test(timeout=2000) public void test_arp_steps6() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
    Board b = Board.fromSaveString(tiny);
    
    b = Board.fromSaveString(tiny);
    p.flagConnectedGems(0,2,b);
    b.doRemovals();
    int [][] next = new int[][]{{1,3,-1},{1,1,1},{1,2,2}};
    assertBoardHasGems(next,b);
  }
  
  @Test(timeout=2000) public void test_arp_steps7() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
    Board b = Board.fromSaveString(cross);
    
    p.flagConnectedGems(2,2,b);
    b.doRemovals();
    int [][] next = new int[][]{
      {1,-1,-1,-1, 1},
      {1, 1,-1, 1, 1},
      {2, 1,-1, 1, 2},
      {1, 1, 2, 1, 1},
      {1, 1, 2, 1, 1}};
    assertBoardHasGems(next,b);
  }

  @Test(timeout=2000) public void test_arp_steps8() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
    Board b = Board.fromSaveString(cross);
    String before = b.toString();
    
    int row=3,col=0;
    p.flagConnectedGems(row,col,b);
    b.doRemovals();
    String expect =
      "     0  1  2  3  4\n"+
      "   ---------------\n"+
      " 0|        2  1  1 \n"+
      " 1|     1  2  1  1 \n"+
      " 2|  1  1  2  2  2 \n"+
      " 3|  1  2  2  1  1 \n"+
      " 4|  2  1  2  1  1 \n"+
      "";   
    String actual = b.toString();      
    String msg =
      String.format("\nAdjacentRemoval at (%d,%d) yields wrong result\nbefore:\n%s\nexpect:\n%s\nactual:\n%s\n",
                    row,col,before,expect,actual);
    assertEquals(msg,expect,actual);
  }

  @Test(timeout=2000) public void test_arp_steps9() {
    RemovalPolicy p = new AdjacentRemovalPolicy();
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
  }
  
  // Connecting
  @Test(timeout=2000) public void test_AdjacentRemovalPolicy_connected1() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
    Board b = new Board(new int[][]{
	{  1,  3,  2,},
	{  1,  1,  1,},
	{  1,  2,  2,},
      });
    int r=2, c=0;
    int mask[][] = (new int[][]{
	{  0,  0,  0,},
	{  1,  0,  0,},
	{  1,  0,  0,},
      });
    b.clearFlags();
    p.flagConnectedGems(r,c,b);
    assertMask(mask,b);
  }
  @Test(timeout=2000) public void test_AdjacentRemovalPolicy_connected2() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
    Board b = new Board(new int[][]{
	{  1,  3,  2,},
	{  1,  1,  1,},
	{  1,  2,  2,},
      });
    int r=1, c=0;
    int mask[][] = (new int[][]{
	{  1,  0,  0,},
	{  1,  1,  0,},
	{  1,  0,  0,},
      });
    b.clearFlags();
    p.flagConnectedGems(r,c,b);
    assertMask(mask,b);
  }
  @Test(timeout=2000) public void test_AdjacentRemovalPolicy_connected3() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
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
  @Test(timeout=2000) public void test_AdjacentRemovalPolicy_connected4() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
    Board b = new Board(new int[][]{
	{  1,  1,  2,  1,  1,},
	{  1,  1,  2,  1,  1,},
	{  2,  2,  2,  2,  2,},
	{  1,  1,  2,  1,  1,},
	{  1,  1,  2,  1,  1,},
      });
    int r=3, c=2;
    int mask[][] = (new int[][]{
	{  0,  0,  0,  0,  0,},
	{  0,  0,  0,  0,  0,},
	{  0,  0,  1,  0,  0,},
	{  0,  0,  1,  0,  0,},
	{  0,  0,  1,  0,  0,},
      });
    b.clearFlags();
    p.flagConnectedGems(r,c,b);
    assertMask(mask,b);
  }
  @Test(timeout=2000) public void test_AdjacentRemovalPolicy_connected5() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
    Board b = new Board(new int[][]{
	{  1,  1,  2,  1,  1,},
	{  1,  1,  2,  1,  1,},
	{  2,  2,  2,  2,  2,},
	{  1,  1,  2,  1,  1,},
	{  1,  1,  2,  1,  1,},
      });
    int r=2, c=2;
    int mask[][] = (new int[][]{
	{  0,  0,  0,  0,  0,},
	{  0,  0,  1,  0,  0,},
	{  0,  1,  1,  1,  0,},
	{  0,  0,  1,  0,  0,},
	{  0,  0,  0,  0,  0,},
      });
    b.clearFlags();
    p.flagConnectedGems(r,c,b);
    assertMask(mask,b);
  }
  @Test(timeout=2000) public void test_AdjacentRemovalPolicy_connected6() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
    Board b = new Board(new int[][]{
	{4,3,5,3,5,1,3,3,1,1,},
	{2,4,1,4,1,4,4,5,4,2,},
	{5,3,5,2,3,3,3,1,3,4,},
	{1,2,2,2,2,4,2,4,5,1,},
	{1,3,1,2,3,3,3,4,1,1,},
	{2,2,2,1,5,1,5,5,3,2,},
	{2,4,1,5,2,2,5,4,5,2,},
      });
    int r=4, c=6;
    int mask[][] = (new int[][]{
	{0,0,0,0,0,0,0,0,0,0,},
	{0,0,0,0,0,0,0,0,0,0,},
	{0,0,0,0,0,0,0,0,0,0,},
	{0,0,0,0,0,0,0,0,0,0,},
	{0,0,0,0,0,1,1,0,0,0,},
	{0,0,0,0,0,0,0,0,0,0,},
	{0,0,0,0,0,0,0,0,0,0,},
      });
    b.clearFlags();
    p.flagConnectedGems(r,c,b);
    assertMask(mask,b);
  }
  @Test(timeout=2000) public void test_AdjacentRemovalPolicy_connected7() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
    Board b = new Board(new int[][]{
	{4,3,5,3,5,1,3,3,1,1,},
	{2,4,1,4,1,4,4,5,4,2,},
	{5,3,5,2,3,3,3,1,3,4,},
	{1,2,2,2,2,4,2,4,5,1,},
	{1,3,1,2,3,3,3,4,1,1,},
	{2,2,2,1,5,1,5,5,3,2,},
	{2,4,1,5,2,2,5,4,5,2,},
      });
    int r=6, c=9;
    int mask[][] = (new int[][]{
	{0,0,0,0,0,0,0,0,0,0,},
	{0,0,0,0,0,0,0,0,0,0,},
	{0,0,0,0,0,0,0,0,0,0,},
	{0,0,0,0,0,0,0,0,0,0,},
	{0,0,0,0,0,0,0,0,0,0,},
	{0,0,0,0,0,0,0,0,0,1,},
	{0,0,0,0,0,0,0,0,0,1,},
      });
    b.clearFlags();
    p.flagConnectedGems(r,c,b);
    assertMask(mask,b);
  }

  // Scoring
  @Test(timeout=2000) public void test_AdjacentRemovalPolicy_score1() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
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
  @Test(timeout=2000) public void test_AdjacentRemovalPolicy_score2() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
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
  @Test(timeout=2000) public void test_AdjacentRemovalPolicy_score3() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
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
  @Test(timeout=2000) public void test_AdjacentRemovalPolicy_score4() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
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
  @Test(timeout=2000) public void test_AdjacentRemovalPolicy_score5() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
    Board b = new Board(new int[][]{
	{ 1, 1, 2, 1, 1,},
	{ 1, 1, 2, 1, 1,},
	{ 2, 2, 2, 2, 2,},
	{ 1, 1, 2, 1, 1,},
	{ 1, 1, 2, 1, 1,},
      });
    int r=2, c=2, expect=25;
    b.clearFlags();
    int score = p.scoreMove(r,c,b);
    String msg = 
      String.format("Move %d %d\nExpected %d got %d\nBoard:\n%s\n",
		    r,c,expect,score,b);
    assertEquals(msg,expect,score);
  }
  @Test(timeout=2000) public void test_AdjacentRemovalPolicy_score6() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
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
  @Test(timeout=2000) public void test_AdjacentRemovalPolicy_score7() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
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
  @Test(timeout=2000) public void test_AdjacentRemovalPolicy_score8() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
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
  @Test(timeout=2000) public void test_AdjacentRemovalPolicy_score9() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
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
  @Test(timeout=2000) public void test_AdjacentRemovalPolicy_score10() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
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
  @Test(timeout=2000) public void test_AdjacentRemovalPolicy_score11() {
    AdjacentRemovalPolicy p = new AdjacentRemovalPolicy();
    Board b = new Board(new int[][]{
	{ 1, 1, 2, 1, 1,},
	{ 1, 1, 2, 1, 1,},
	{ 2, 2, 2, 2, 2,},
	{ 1, 1, 1, 1, 1,},
	{ 1, 1, 2, 1, 1,},
      });
    int r=2, c=2, expect=16;
    b.clearFlags();
    int score = p.scoreMove(r,c,b);
    String msg = 
      String.format("Move %d %d\nExpected %d got %d\nBoard:\n%s\n",
		    r,c,expect,score,b);
    assertEquals(msg,expect,score);
  }

}
