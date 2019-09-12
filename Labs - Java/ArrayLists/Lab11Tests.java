// Public tests for Lab11 ALUtils

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class Lab11Tests {
  /*Main method runs tests in this file*/ 
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("Lab11Tests");
  } 

  public <T> void checkRotate(T d[], int shift, String expectS){
    ArrayList<T> a = new ArrayList<T>();
    a.addAll(Arrays.asList(d));
    String sourceBefore = a.toString();

    ArrayList<T> actualA = ALUtils.rotate(a, shift);

    String sourceAfter = a.toString();
    String actualS = actualA.toString();
    String msg =
      String.format("Rotation incorrect\n")+
      String.format("Source  : %s (before rotate)\n",sourceBefore)+
      String.format("Shift   : %d\n",shift)+
      String.format("Expect  : %s\n",expectS)+
      String.format("Actual  : %s\n",actualS)+
      String.format("Source  : %s (after rotate)\n",sourceAfter);
    assertEquals(msg,expectS,actualS);
    assertEquals(msg,sourceBefore,sourceAfter);
  }

  @Test public void test_rotate1() {
    Character [] d = { 'A', 'B', 'C', 'D', 'E', 'F'};
    int shift = 2;
    String expectS = "[E, F, A, B, C, D]";
    checkRotate(d,shift,expectS);
  }

  @Test public void test_rotate2() {
    Character [] d = { 'A', 'B', 'C', 'D', 'E', 'F'};
    int shift = 7;
    String expectS = "[F, A, B, C, D, E]";
    checkRotate(d,shift,expectS);
  }

  @Test public void test_rotate3() {
    String [] d =  { "A", "B", "C", "D", "E", "F", "G" };
    int shift = 7;
    String expectS = "[A, B, C, D, E, F, G]";
    checkRotate(d,shift,expectS);
  }

  @Test public void test_rotate4() {
    String [] d =  { "A", "B", "C", "D", "E", "F", "G" };
    int shift = 4;
    String expectS = "[D, E, F, G, A, B, C]";
    checkRotate(d,shift,expectS);
  }

  @Test public void test_rotate5() {
    Integer [] d =  { 1};
    int shift = 4;
    String expectS = "[1]";
    checkRotate(d,shift,expectS);
  }

  @Test public void test_rotate6() {
    Integer [] d =  {};
    int shift = 2;
    String expectS = "[]";
    checkRotate(d,shift,expectS);
  }

  @Test public void test_rotate7() {
    String [] d =  { "A", "B", "C", "D", "E", "F", "G" };
    int shift = 0;
    String expectS = "[A, B, C, D, E, F, G]";
    checkRotate(d,shift,expectS);
  }

  @Test public void test_rotate8() {
    String [] d =  { "A", "B", "C", "D", "E", "F", "G" };
    int shift = 28;
    String expectS = "[A, B, C, D, E, F, G]";
    checkRotate(d,shift,expectS);
  }

  @Test public void test_rotate9() {
    String [] d =  { "A", "B", "C", "D", "E", "F", "G" };
    int shift = 39;
    String expectS = "[D, E, F, G, A, B, C]";
    checkRotate(d,shift,expectS);
  }


  public <T> void checkReverse(T d[], String expectS){
    ArrayList<T> a = new ArrayList<T>();
    a.addAll(Arrays.asList(d));
    String sourceBefore = a.toString();

    ArrayList<T> actualA = ALUtils.reverse(a);

    String sourceAfter = a.toString();
    String actualS = actualA.toString();
    String msg =
      String.format("Reverse incorrect\n")+
      String.format("Source  : %s (before rotate)\n",sourceBefore)+
      String.format("Expect  : %s\n",expectS)+
      String.format("Actual  : %s\n",actualS)+
      String.format("Source  : %s (after rotate)\n",sourceAfter);
    assertEquals(msg,expectS,actualS);
    assertEquals(msg,sourceBefore,sourceAfter);
  }

  @Test public void test_reverse1() {
    String [] d = {"A","B","C","D","E"};
    String expectS = "[E, D, C, B, A]";
    checkReverse(d,expectS);
  }

  @Test public void test_reverse2() {
    String [] d = {"A","B","C","D"};
    String expectS = "[D, C, B, A]";
    checkReverse(d,expectS);
  }

  @Test public void test_reverse3() {
    String [] d = {"A",};
    String expectS = "[A]";
    checkReverse(d,expectS);
  }

  @Test public void test_reverse4() {
    Integer [] d = {};
    String expectS = "[]";
    checkReverse(d,expectS);
  }

  @Test public void test_reverse5() {
    Integer [] d = {5, 4, 3};
    String expectS = "[3, 4, 5]";
    checkReverse(d,expectS);
  }

  @Test public void test_reverse6() {
    Character [] d = {'5', '4', '3', '2', '1', '0', 'A', 'B'};
    String expectS = "[B, A, 0, 1, 2, 3, 4, 5]";
    checkReverse(d,expectS);
  }

}
