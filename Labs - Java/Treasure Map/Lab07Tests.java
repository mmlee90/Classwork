import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class Lab07Tests {
  /*Main method runs tests in this file*/ 
  public static void main(String args[])
  {
    org.junit.runner.JUnitCore.main("Lab07Tests");
  } 

  @Test
  public void tmInteractive1 () {
    setInput("4 4 3   0 0 1 1 0 2");
    TreasureMap tm = new TreasureMap();
    assertTrue(tm.treasureAt(0,0));
    assertTrue(tm.treasureAt(1,1));
    assertTrue(tm.treasureAt(0,2));

    assertFalse(tm.treasureAt(0,1));
    assertFalse(tm.treasureAt(0,3));
  }

  @Test
  public void tmInteractive2 () {
    setInput("10 5 5   0 0  5 4  1 1  0 2  9 2");
    TreasureMap tm = new TreasureMap();
    assertTrue(tm.treasureAt(0,0));
    assertTrue(tm.treasureAt(5,4));
    assertTrue(tm.treasureAt(1,1));
    assertTrue(tm.treasureAt(0,2));
    assertTrue(tm.treasureAt(9,2));

    assertFalse(tm.treasureAt(0,1));
    assertFalse(tm.treasureAt(0,3));
    assertFalse(tm.treasureAt(9,4));
    assertFalse(tm.treasureAt(2,2));
  }

  @Test
  public void tmInteractive3 () {
    setInput("1 8 2   0 3  0 6");
    TreasureMap tm = new TreasureMap();
    assertTrue(tm.treasureAt(0,3));
    assertTrue(tm.treasureAt(0,6));

    assertFalse(tm.treasureAt(0,1));
    assertFalse(tm.treasureAt(0,7));
    assertFalse(tm.treasureAt(0,0));
  }

  @Test
  public void tmInteractive4 () {
    setInput("4 7 7   0 3  0 6  0 0  3 5  1 1  0 2  3 6");
    TreasureMap tm = new TreasureMap();
    assertTrue(tm.treasureAt(0,3));
    assertTrue(tm.treasureAt(0,6));
    assertTrue(tm.treasureAt(0,0));
    assertTrue(tm.treasureAt(3,5));
    assertTrue(tm.treasureAt(1,1));
    assertTrue(tm.treasureAt(0,2));
    assertTrue(tm.treasureAt(3,6));

    assertFalse(tm.treasureAt(0,1));
    assertFalse(tm.treasureAt(2,0));
    assertFalse(tm.treasureAt(2,5));
    assertFalse(tm.treasureAt(1,2));
    assertFalse(tm.treasureAt(2,1));
  }

  @Test
  public void tmFile1() throws Exception{
    String filename = "testmap1.txt";
    String testMap = 
     //012345678
      ".........\n"+// 0
      "........X\n"+// 1
      "..X......\n"+// 2
      ".....XX..\n"+// 3
      ".........\n"+// 4
      ".........\n";// 5
    PrintWriter fout = new PrintWriter(new File(filename));
    fout.print(testMap);
    fout.close();
    TreasureMap tm = new TreasureMap(filename);
    assertTrue(tm.treasureAt(2,2));
    assertTrue(tm.treasureAt(3,5));
    assertTrue(tm.treasureAt(3,6));
    assertTrue(tm.treasureAt(1,8));

    assertFalse(tm.treasureAt(0,1));
    assertFalse(tm.treasureAt(2,0));
    assertFalse(tm.treasureAt(3,4));
    assertFalse(tm.treasureAt(3,7));
    assertFalse(tm.treasureAt(4,7));
    assertFalse(tm.treasureAt(4,5));
    assertFalse(tm.treasureAt(4,6));
  }    

  @Test
  public void tmFile2() throws Exception{
    String filename = "testmap2.txt";
    String testMap = 
     //01
      "X.\n"+// 0
      ".X\n"+// 1
      "..\n"+// 2
      "..\n"+// 3
      "X.\n"+// 4
      "..\n"+// 5
      "..\n"+// 6
      ".X\n"+// 7
      "..\n"+// 8
      "X.\n";// 9
    PrintWriter fout = new PrintWriter(new File(filename));
    fout.print(testMap);
    fout.close();
    TreasureMap tm = new TreasureMap(filename);
    assertTrue(tm.treasureAt(0,0));
    assertTrue(tm.treasureAt(1,1));
    assertTrue(tm.treasureAt(4,0));
    assertTrue(tm.treasureAt(7,1));
    assertTrue(tm.treasureAt(9,0));

    assertFalse(tm.treasureAt(0,1));
    assertFalse(tm.treasureAt(1,0));
    assertFalse(tm.treasureAt(3,0));
    assertFalse(tm.treasureAt(3,1));
    assertFalse(tm.treasureAt(4,1));
    assertFalse(tm.treasureAt(5,0));
    assertFalse(tm.treasureAt(5,1));
  }    


  @Test
  public void tmFile3() throws Exception{
    String filename = "testmap3.txt";
    String testMap = 
     //0123456
      "X.....X\n"+// 0
      ".X...X.\n"+// 1 
      "..X.X..\n"+// 2 
      "...X...\n"+// 3 
      "..X.X..\n"+// 4 
      ".X...X.\n"+// 5 
      "X.....X\n"+// 6 
     //0123456
      "";
    PrintWriter fout = new PrintWriter(new File(filename));
    fout.print(testMap);
    fout.close();
    TreasureMap tm = new TreasureMap(filename);
    assertTrue(tm.treasureAt(0,0));
    assertTrue(tm.treasureAt(1,1));
    assertTrue(tm.treasureAt(3,3));
    assertTrue(tm.treasureAt(0,6));
    assertTrue(tm.treasureAt(6,6));
    assertTrue(tm.treasureAt(6,0));
    assertTrue(tm.treasureAt(4,2));

    assertFalse(tm.treasureAt(0,1));
    assertFalse(tm.treasureAt(1,0));
    assertFalse(tm.treasureAt(3,0));
    assertFalse(tm.treasureAt(2,3));
    assertFalse(tm.treasureAt(4,1));
    assertFalse(tm.treasureAt(4,3));
    assertFalse(tm.treasureAt(6,5));
  }    

  @Test
  public void tmFile4() throws Exception{
    String filename = "testmap4.txt";
    String testMap = 
      //         10        20        30        40        50        60        
     //0123456789012345678901234567890123456789012345678901234567890123456789
      "...............................XXXXXXXXXXXX...........................\n"+ //  0
      "........................XXXXXXXXXXXXXXXXXXXXXXXXX.....................\n"+ //  1
      ".....................XXXXXX...................XXXXXX..................\n"+ //  2
      "..................XXXXX...........................XXXX................\n"+ //  3
      "................XXXX.................................XXXX.............\n"+ //  4
      "..............XXX......................................XXX............\n"+ //  5
      ".............XXX.........................................XXX..........\n"+ //  6
      "...........XXX............................................XXX.........\n"+ //  7
      "..........XXX...............................................XX........\n"+ //  8
      ".........XX..................................................XX.......\n"+ //  9
      "........XX....................................................XX......\n"+ // 10
      "......XXX..............................................X......XXX.....\n"+ // 11
      ".....XXX...............................................X.......XXX....\n"+ // 12
      "....XXX...............................................XX........XX....\n"+ // 13
      "....XXXX..............................................XX.........XX...\n"+ // 14
      "...XXXXXX.............................................XX.........XX...\n"+ // 15
      "...X....XX............................................XX..........XX..\n"+ // 16
      "..XX.....XX............................................XXX........XX..\n"+ // 17
      "..X......XXX............................................XXXX.......XX.\n"+ // 18
      ".XX......XXX.............................................XXX.......XX.\n"+ // 19
      ".XX.....XXXXX.............................................XX........X.\n"+ // 20
      ".X.......XXXX.............................................XXX.......X.\n"+ // 21
      "XX.......XXXX.............................................XXXXXXX...XX\n"+ // 22
      "XX.......XXXX.............................................XXXXXXX...XX\n"+ // 23
      "XX.......XXX...........XXXXXXX............XXXXXXX.........XXXXXX....XX\n"+ // 24
      "XX..XX...XXX......XXXXXXXXXXXXX..........XXXXXXXXXXXXXX....XXXXX....XX\n"+ // 25
      "XX..XX....XX...XXXXXXXXXXXXXXXX..........XXXXXXXXXXXXXXXX...XXX.....XX\n"+ // 26
      "XX...XX..XX..XXXXXXXXXXXXXXXXX...........XXXXXXXXXXXXXXXXX...XX.....XX\n"+ // 27
      "XX....XX.XX..XXXXXXXXXXXXXXXXX..........XXXXXXXXXXXXXXXXXX...XX.....XX\n"+ // 28
      ".X....XXXXX..XXXXXXXXXXXXXXXXXXX.........XXXXXXXXXXXXXXXXX..XXXXXXXXXX\n"+ // 29
      ".XX...XXXX...XXXXXXXXXXXXXXXXXX...........XXXXXXXXXXXXXXXX..XXXXXXXXXX\n"+ // 30
      "..X...XXX....XXXXXXXXXXXXXXXXX............XXXXXXXXXXXXXXXX...XXXXXXXXX\n"+ // 31
      "..XX..XXX....XXXXXXXXXXXXXXXX......X......XXXXXXXXXXXXXXXX...XXXXXXXXX\n"+ // 32
      "..XX..XX.....XXXXXXXXXXXXXX.......XXXX....XXXXXXXXXXXXXXXX....XXXXXXX.\n"+ // 33
      "...XXXXX....XXXXXXXXXXXXXXX......XXXXX.......XXXXXXXXXXXXX.....XXXXXX.\n"+ // 34
      "....XXXX.....XXXXXXXXX..XX.......XXXXXX.........XXXXXXXXX..........X..\n"+ // 35
      "....XXX.......XXXXX...XXX........XXXXXX..........XX...................\n"+ // 36
      ".....XX..............XX..........XXXXXXX......XX.XXX............X.....\n"+ // 37
      ".....XXXX...............XX......XXXXXXXXX....XXXX...X...XX.....X.X....\n"+ // 38
      ".....XX.XX.....................XXXXX.XXXX....XX........XXX...XX.XX....\n"+ // 39
      ".....XX..XX.....X..............XXX.X.XXXX.....XX.......XXXX.XX..X.....\n"+ // 40
      "......XX..XX..XXX..............XX....XX.X.............XXXXX.XXXXX.....\n"+ // 41
      ".......X..XXX.XXXX.............XX....XX.............XXXXXX..XXXX......\n"+ // 42
      ".......XX..XX..XXXXX...........XXXXX..X............XXXXXX..XX.........\n"+ // 43
      "........XXXXX..XXXXXX...........XX..XX............XXXXXX...XX.........\n"+ // 44
      "...........XXX..XXXXX...............X............XXXXXX...XX..........\n"+ // 45
      "............XX..XXXXX...............X.............XXXX....XX..........\n"+ // 46
      "............XX...XXXX..XX.........................XXXX....XX..........\n"+ // 47
      "............XX....XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.....XX..........\n"+ // 48
      "............XX.....XXXXX.X.XXX.X..X...XX.XX.X.XXXX.XX.....X...........\n"+ // 49
      "...........XXX.....XXXXX..X....X..X......XX........X......X...........\n"+ // 50
      "...........XXX......XXXXXXX....X..........X...XXXXXX.....XX...........\n"+ // 51
      "...........XXX......XX.X.XXXXXXXXXXXX...XXXXX.X..X.X.....XX...........\n"+ // 52
      "...........XXX......XX.X......XXXX.XX....XX.X.X..X.......XX...........\n"+ // 53
      "...........XXX.......XXXXXXXX.X.X..XX.XX.XX.X.XXXX.......XX...........\n"+ // 54
      "...........XXX.........XXXXXXXXXXXXXXXXXXXXXXX.........XXX............\n"+ // 55
      "............XXXXXXX...............XX..................XX..............\n"+ // 56
      ".............XXXXXXXX...............................XXX...............\n"+ // 57
      ".................XXXXX......................XX....XXXX................\n"+ // 58
      "....................XXX.....................XX..XXXX..................\n"+ // 59
      ".....................XXX.....XX....X...........XXX....................\n"+ // 60
      "......................XXXX........XX..........XXX.....................\n"+ // 61
      "........................XXXX.....XXX........XXXX......................\n"+ // 62
      "..........................XXXXXXXXXXXXXXXXXXXXX.......................\n"+ // 63
      "............................XXXXXXXXXXXXXXXX..........................\n"+ // 64
     //0123456789012345678901234567890123456789012345678901234567890123456789
      //         10        20        30        40        50        60        

      "";

    PrintWriter fout = new PrintWriter(new File(filename));
    fout.print(testMap);
    fout.close();
    TreasureMap tm = new TreasureMap(filename);

    assertTrue(tm.treasureAt(56,13));
    assertTrue(tm.treasureAt(44,33));
    assertTrue(tm.treasureAt(37,64));
    assertTrue(tm.treasureAt(16,9));
    assertTrue(tm.treasureAt(22,0));
    assertTrue(tm.treasureAt(32,69));
    assertTrue(tm.treasureAt(64,28));

    assertFalse(tm.treasureAt(43,37));
    assertFalse(tm.treasureAt(64,0));
    assertFalse(tm.treasureAt(64,69));
    assertFalse(tm.treasureAt(0,0));
    assertFalse(tm.treasureAt(0,69));
    assertFalse(tm.treasureAt(47,16));
    assertFalse(tm.treasureAt(27,65));
  }    


  static ByteArrayOutputStream localOut, localErr;
  static PrintStream sysOut, sysErr;
  static ByteArrayInputStream localIn;
  static InputStream sysIn;
  static String [] emptyArgs = {};
  static String theInput;

  @BeforeClass
  public static void setUp() throws Exception {
    sysIn  = System.in;
    sysOut = System.out;
    sysErr = System.err;
  }

  // Before every test is run, reset the streams to capture
  // stdout/stderr
  @Before
  public void setUpStreams() {
    localIn  = null;
    localOut = new ByteArrayOutputStream();
    localErr = new ByteArrayOutputStream();
    System.setOut(new PrintStream(localOut));
    System.setErr(new PrintStream(localErr));
  }

  // After every test, restore stdout/stderr
  @After
  public void cleanUpStreams() {
    // System.setOut(null);
    // System.setErr(null);
    System.setIn(sysIn);
    System.setOut(sysOut);
    System.setErr(sysErr);
  }

  public void setInput(String s){
    localIn = new ByteArrayInputStream(s.getBytes());
    System.setIn(localIn);
  }

}
