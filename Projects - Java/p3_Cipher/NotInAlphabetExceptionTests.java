import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class NotInAlphabetExceptionTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("NotInAlphabetExceptionTests");
  }
  
  static Alphabet uppers     = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ ."); 
  static Alphabet lowers     = new Alphabet("abcdefghijklmnopqrstuvwxyz");
  static Alphabet lowerSpace = new Alphabet("abcdefghijklmnopqrstuvwxyz ");
  
  /* NotInAlphabetException Tests */
  
  @Test (timeout=2000) public void test_NotInAlphabetException_1(){
    NotInAlphabetException e = new NotInAlphabetException('c',uppers);
    assertEquals('c', e.offender);
  }
  @Test (timeout=2000) public void test_NotInAlphabetException_2(){
    NotInAlphabetException e = new NotInAlphabetException('c',uppers);
    assertEquals(uppers, e.a);
  }
  @Test (timeout=2000) public void test_NotInAlphabetException_3(){
    NotInAlphabetException e = new NotInAlphabetException('C',lowers);
    assertEquals("Not in alphabet: 'C' not found in Alphabet(abcdefghijklmnopqrstuvwxyz).", e.msg);
  }
  
  
  @Test (timeout=2000) public void test_NotInAlphabetException_4(){
    NotInAlphabetException e = new NotInAlphabetException("special message.",'c',uppers);
    assertEquals('c', e.offender);
  }
  @Test (timeout=2000) public void test_NotInAlphabetException_5(){
    NotInAlphabetException e = new NotInAlphabetException("special message.",'c',uppers);
    assertEquals(uppers, e.a);
  }
  @Test (timeout=2000) public void test_NotInAlphabetException_6(){
    NotInAlphabetException e = new NotInAlphabetException("special message.",'c',uppers);
    assertEquals("special message.", e.msg);
  }
  
  
  
  @Test (timeout=2000) public void test_NotInAlphabetException_7(){
    try {
      assertEquals(5, new Alphabet("WXYZ").get(10));
      fail("shouldn't get here...");
    } catch (NotInAlphabetException e){ /* good. */ }
  }
  @Test (timeout=2000) public void test_NotInAlphabetException_8(){ 
    try{
      assertEquals(20,new Alphabet("ABCDEFGHIJabcdefghijk").indexOf('X'));
      fail("shouldn't get here...");
    } catch (NotInAlphabetException e){ /* good. */ }
  }
  @Test (timeout=2000) public void test_NotInAlphabetException_9(){ 
    NotInAlphabetException e = new NotInAlphabetException("no good",'x',new Alphabet("ABC"));
    assertEquals(new String("no good"), e.msg);
    assertEquals('x',e.offender);
    assertEquals("ABC",e.a.getSymbols());
    assertEquals(e.toString(),e.msg);
  }
  @Test (timeout=2000) public void test_NotInAlphabetException_10(){ 
    NotInAlphabetException e = new NotInAlphabetException('x',new Alphabet("ABC"));
    String msg = new String("Not in alphabet: 'x' not found in ABC.");
    assertEquals('x',e.offender);
    assertEquals("ABC",e.a.getSymbols());
    assertEquals(e.toString(),e.msg);
  }
  
  @Test (timeout=2000) public void test_NotInAlphabetException_11(){
    NotInAlphabetException e = new NotInAlphabetException('x',new Alphabet("ABC"));
    assertEquals(e.msg,e.toString());
  }
  
}