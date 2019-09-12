import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class SymmetricCipherTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("SymmetricCipherTests");
  }
  
  static Alphabet uppers     = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ ."); 
  static Alphabet lowers     = new Alphabet("abcdefghijklmnopqrstuvwxyz");
  static Alphabet lowerSpace = new Alphabet("abcdefghijklmnopqrstuvwxyz ");
  
  
  /* SymmetricCipher */
  
  /*
   * This inner class extends SymmetricCipher in its own way, correctly; this allows us to test your implementation
   * details of SymmetricCipher without relying upon your CaesarCipher or VigenereCipher to be absolutely correct.
   */
  class SwapRWCipher extends SymmetricCipher{
    public SwapRWCipher(Alphabet a){
      super(a);
    }
    public SwapRWCipher(){
      super(Alphabet.DEFAULT);
    }
    public char encrypt1(char c){
      switch(c){
        case 'R': return 'W';
        case 'r': return 'w';
        case 'W': return 'R';
        case 'w': return 'r';
        default:  return c;
      }
    }
    public char decrypt1(char c){
      switch(c){
        case 'R': return 'W';
        case 'r': return 'w';
        case 'W': return 'R';
        case 'w': return 'r';
        default:  return c;
      }
    }
  }
  
  SwapRWCipher swap = new SwapRWCipher(Alphabet.DEFAULT);
  @Test (timeout=2000) public void test_Symmetric_1(){ assertEquals( 5, swap.rotate( 4,  1)); }
  @Test (timeout=2000) public void test_Symmetric_2(){ assertEquals( 5, swap.rotate( 4,  1)); }
  @Test (timeout=2000) public void test_Symmetric_3(){ assertEquals( 3, swap.rotate( 4, -1)); }
  @Test (timeout=2000) public void test_Symmetric_4(){ assertEquals(10, swap.rotate(10, 93)); } // DEFAULT has 93 characters.
  @Test (timeout=2000) public void test_Symmetric_5(){ assertEquals(91, swap.rotate( 3, -5)); }
  @Test (timeout=2000) public void test_Symmetric_6(){ assertEquals( 9, swap.rotate( 2,100)); }
  
  @Test (timeout=2000) public void test_Symmetric_7(){ assertEquals( Alphabet.DEFAULT, swap.getAlphabet()); }
  @Test (timeout=2000) public void test_Symmetric_8(){ assertEquals( uppers, new SwapRWCipher(uppers).getAlphabet()); }
  
  @Test (timeout=2000) public void test_Symmetric_9() { assertEquals( 1, swap.wrapInt(   1)); }
  @Test (timeout=2000) public void test_Symmetric_10(){ assertEquals( 0, swap.wrapInt(  93)); }
  @Test (timeout=2000) public void test_Symmetric_11(){ assertEquals(92, swap.wrapInt(  -1)); }
  @Test (timeout=2000) public void test_Symmetric_12(){ assertEquals(88, swap.wrapInt(  -5)); }
  @Test (timeout=2000) public void test_Symmetric_13(){ assertEquals(70, swap.wrapInt(1000)); }
  
  @Test (timeout=2000) public void test_Symmetric_14(){ assertEquals("no fancy alphas", swap.encrypt("no fancy alphas"));}
  @Test (timeout=2000) public void test_Symmetric_15(){ assertEquals("no fancy alphas", swap.decrypt("no fancy alphas"));}
  @Test (timeout=2000) public void test_Symmetric_16(){ assertEquals("hello rowld", swap.encrypt("hello world"));}
  @Test (timeout=2000) public void test_Symmetric_17(){ assertEquals("rwRW", swap.encrypt("wrWR"));}
  @Test (timeout=2000) public void test_Symmetric_18(){ assertEquals("hello rowld", swap.decrypt("hello world"));}
  @Test (timeout=2000) public void test_Symmetric_19(){ assertEquals("rwRW", swap.decrypt("wrWR"));}
  
  /* here's another sneaky way to make a SymmetricCipher value in-place; this is called an "anonymous class".
   * In this case, the anonymous class is extending SymmetricCipher. Anonymous classes are truly expressions, 
   * and this is why it shows up on the righthand side of an assignment statement here.
   * 
   * It'll complain if we try to use encrypt1 or decrypt1, but we can test the other methods.
   *
   //  static SymmetricCipher sc = new SymmetricCipher(Alphabet.DEFAULT){
   //    public char encrypt1(char c){ throw new RuntimeException("shouldn't try encrypt1 with this anonymous class!"); }
   //    public char decrypt1(char c){ throw new RuntimeException("shouldn't try encrypt1 with this anonymous class!"); }
   //  };
   // we've already got enough SymmetricCipher tests above though, so we won't actually use this.
   */
  
}