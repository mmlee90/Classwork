import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class AlphabetTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("AlphabetTests");
  }
  
  /* Alphabet Tests */
  
  static Alphabet uppers     = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ ."); 
  static Alphabet lowers     = new Alphabet("abcdefghijklmnopqrstuvwxyz");
  static Alphabet lowerSpace = new Alphabet("abcdefghijklmnopqrstuvwxyz ");
  
  @Test (timeout=2000) public void test_Alpha_1(){ assertEquals(3,new Alphabet("abcde").indexOf('d')); }
  @Test (timeout=2000) public void test_Alpha_2(){ assertEquals(20,new Alphabet("ABCDEFGHIJabcdefghijk").indexOf('k')); }
  @Test (timeout=2000) public void test_Alpha_3(){ assertEquals('c',new Alphabet("abcde").get(2)); }
  @Test (timeout=2000) public void test_Alpha_4(){ assertEquals(93, Alphabet.DEFAULT.length()); }
  @Test (timeout=2000) public void test_Alpha_5(){ assertEquals(5, new Alphabet("ABCDE").length()); }
  @Test (timeout=2000) public void test_Alpha_6(){ assertEquals(18,new Alphabet("abcdefghijklmnopqr").length()); }
  @Test (timeout=2000) public void test_Alpha_7(){ assertEquals("QUICK BROWN FoX",new Alphabet("QUICK BROWN FoX").getSymbols()); }
  @Test (timeout=2000) public void test_Alpha_8(){ assertEquals("Alphabet(123XYzabC)",new Alphabet("123XYzabC").toString()); }
  
  @Test (timeout=2000) public void test_Alpha_9(){ assertEquals( 5, new Alphabet("abcdefg").indexOf('f')); }
  @Test (timeout=2000) public void test_Alpha_10(){ assertEquals( 0, new Alphabet("abcdefg").indexOf('a')); }
  @Test (timeout=2000) public void test_Alpha_11(){ assertEquals(26, Alphabet.DEFAULT   .indexOf('a')); }
  
  @Test (timeout=2000) public void test_Alpha_12(){ assertEquals('A', Alphabet.DEFAULT.get( 0)); }
  @Test (timeout=2000) public void test_Alpha_13(){ assertEquals('c', Alphabet.DEFAULT.get(28)); }
  @Test (timeout=2000) public void test_Alpha_14(){ assertEquals(' ', Alphabet.DEFAULT.get(52)); }
  @Test (timeout=2000) public void test_Alpha_15(){ assertEquals('>', Alphabet.DEFAULT.get(92)); }
  
  @Test (timeout=2000) public void test_Alpha_16() {
    Object a1 = new Alphabet("ABCabc");
    Object a2 = new Alphabet("ABCabc");
    Object a3 = new Alphabet("123");
    
    assertEquals   ( a1,a2); // true
    assertFalse( a1.equals(a3)); // false
  }
  
  @Test (timeout=2000) public void test_Alpha_17(){
    try{
      uppers.get(400);
    } catch (NotInAlphabetException e){ return; }
    fail ("should have realized 400 wasn't a valid index in the uppers alphabet.");
  }
  @Test (timeout=2000) public void test_Alpha_18(){
    try{
      uppers.indexOf('a');
    } catch (NotInAlphabetException e){ return; }
    fail ("should have realized 'a' wasn't in the uppers alphabet.");
  }
  
  
}