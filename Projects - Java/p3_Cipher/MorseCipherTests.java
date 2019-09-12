import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class MorseCipherTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("MorseCipherTests");
  }
  
  
  /*  MorseCipher tests */
  
  MorseCipher mc = new MorseCipher();
  // @Test (timeout=2000) public void test_Morse_1(){ assertEquals(new Alphabet(MorseCipher.letters),new MorseCipher().getAlphabet()); }
  // @Test (timeout=2000) public void test_Morse_2(){
  //   try{ mc.encrypt1('a');}
  //   catch(UnsupportedOperationException e){return;}
  //   fail("should not allow encrypt1(char) to be called.");
  // }
  // @Test (timeout=2000) public void test_Morse_3(){
  //   try{ mc.encrypt1('a');}
  //   catch(UnsupportedOperationException e){return;}
  //   fail("should not allow decrypt1(char) to be called.");
  // }
  
  
  @Test (timeout=2000) public void test_Morse_4(){
    assertEquals(".-",mc.encrypt("A"));
  }
  
  @Test (timeout=2000) public void test_Morse_5() {
    assertEquals("....   .   .-..   .-..   ---", mc.encrypt("HELLO"));
  }
  
  @Test (timeout=2000) public void test_Morse_6(){
    assertEquals(".----   ..---   ...--", mc.encrypt("123"));
  }
  
  @Test (timeout=2000) public void test_Morse_7(){
    assertEquals(".-       -...   -...       -.-.   -.-.   -.-.", mc.encrypt("a bb ccc"));
  }
  
  @Test (timeout=2000) public void test_Morse_8(){
    assertEquals(".-.   ..-   -.       ..-.   ---   .-.       -   ....   .       .   -..-   ..   -   ...", mc.encrypt("run for the exits"));
  }
  
  @Test (timeout=2000) public void test_Morse_9(){
    assertEquals("A",mc.decrypt(".-"));
  }
  
  @Test (timeout=2000) public void test_Morse_10() {
    assertEquals("HELLO",mc.decrypt("....   .   .-..   .-..   ---"));
  }
  
  @Test (timeout=2000) public void test_Morse_11(){
    assertEquals("123", mc.decrypt(".----   ..---   ...--"));
  }
  
  @Test (timeout=2000) public void test_Morse_12(){
    assertEquals("A BB CCC",mc.decrypt(".-       -...   -...       -.-.   -.-.   -.-."));
  }
  
  @Test (timeout=2000) public void test_Morse_13(){
    assertEquals("RUN FOR THE EXITS", mc.decrypt(".-.   ..-   -.       ..-.   ---   .-.       -   ....   .       .   -..-   ..   -   ..."));
  }
  
  
  @Test (timeout=2000) public void test_Morse_14(){
    try {
      String s = new MorseCipher().encrypt("%^&*");
    } catch (NotInAlphabetException e){ return; }
    fail("shouldn't encrypt any characters not in the alphabet.");
  }
  
  
}
