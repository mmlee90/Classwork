import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class VigenereCipherTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("VigenereCipherTests");
  }
  
  static Alphabet uppers     = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ ."); 
  static Alphabet lowers     = new Alphabet("abcdefghijklmnopqrstuvwxyz");
  static Alphabet lowerSpace = new Alphabet("abcdefghijklmnopqrstuvwxyz ");
  
  /*  VigenereCipher */
  
  
  @Test (timeout=2000) public void test_VigenereCipher_1(){ assertEquals("ABCDEFG",new VigenereCipher("ABCDEFG").getPassword()); }
  @Test (timeout=2000) public void test_VigenereCipher_2(){ assertEquals("abcdefg",new VigenereCipher("abcdefg",lowers).getPassword()); }
  
  @Test (timeout=2000) public void test_VigenereCipher_3(){ assertEquals(Alphabet.DEFAULT,new VigenereCipher("ABCDEFG").getAlphabet()); }
  @Test (timeout=2000) public void test_VigenereCipher_4(){ assertEquals(lowers,new VigenereCipher("abcdefg",lowers).getAlphabet()); }
  
  @Test (timeout=2000) public void test_VigenereCipher_5(){ assertEquals("ZERO",        new VigenereCipher("A")    .encrypt("ZERO"));         }
  @Test (timeout=2000) public void test_VigenereCipher_6(){ assertEquals("YcMQR",       new VigenereCipher("SLIME").encrypt("GREEN"));        }
  @Test (timeout=2000) public void test_VigenereCipher_7(){ assertEquals("SLIMESLI",    new VigenereCipher("SLIME").encrypt("AAAAAAAA"));     }
  @Test (timeout=2000) public void test_VigenereCipher_8(){ assertEquals("exiuLEFUsooo",new VigenereCipher("AAAB") .encrypt("exitLEFTsoon")); }
  
  @Test (timeout=2000) public void test_VigenereCipher_9() { assertEquals("ZERO",        new VigenereCipher("A")    .decrypt("ZERO"));         }
  @Test (timeout=2000) public void test_VigenereCipher_10(){ assertEquals("GREEN",       new VigenereCipher("SLIME").decrypt("YcMQR"));        }
  @Test (timeout=2000) public void test_VigenereCipher_11(){ assertEquals("AAAAAAAA",    new VigenereCipher("SLIME").decrypt("SLIMESLI"));     }
  @Test (timeout=2000) public void test_VigenereCipher_12(){ assertEquals("exitLEFTsoon",new VigenereCipher("AAAB") .decrypt("exiuLEFUsooo")); }
  
  @Test (timeout=2000) public void test_VigenereCipher_13(){ assertEquals('X',new VigenereCipher("A") .encrypt1('X')); }
  @Test (timeout=2000) public void test_VigenereCipher_14(){ assertEquals('E',new VigenereCipher("E") .encrypt1('A')); }
  @Test (timeout=2000) public void test_VigenereCipher_15(){ assertEquals('Y',new VigenereCipher("B") .encrypt1('X')); }
  @Test (timeout=2000) public void test_VigenereCipher_16(){ assertEquals('A',new VigenereCipher("B") .encrypt1('>')); }
  @Test (timeout=2000) public void test_VigenereCipher_17(){ assertEquals('a',new VigenereCipher("B") .encrypt1('Z')); }
  @Test (timeout=2000) public void test_VigenereCipher_18(){ assertEquals('x',new VigenereCipher("a") .encrypt1('X')); }
  @Test (timeout=2000) public void test_VigenereCipher_19(){ assertEquals('r',new VigenereCipher("b") .encrypt1('Q')); }
  
  @Test (timeout=2000) public void test_VigenereCipher_20(){ assertEquals('X',new VigenereCipher("A") .decrypt1('X')); }
  @Test (timeout=2000) public void test_VigenereCipher_21(){ assertEquals('.',new VigenereCipher("F") .decrypt1('A')); }
  @Test (timeout=2000) public void test_VigenereCipher_22(){ assertEquals('W',new VigenereCipher("B") .decrypt1('X')); }
  @Test (timeout=2000) public void test_VigenereCipher_23(){ assertEquals('<',new VigenereCipher("B") .decrypt1('>')); }
  @Test (timeout=2000) public void test_VigenereCipher_24(){ assertEquals('Y',new VigenereCipher("B") .decrypt1('Z')); }
  @Test (timeout=2000) public void test_VigenereCipher_25(){ assertEquals('m',new VigenereCipher("N") .decrypt1('z')); }
  
  
  @Test (timeout=2000) public void test_Vigenere_26(){
    try {
      char c = new VigenereCipher("password",lowers).encrypt1('A');
    } catch (NotInAlphabetException e){ return; }
    fail("shouldn't encrypt any characters not in the alphabet.");
  }
  
  @Test (timeout=2000) public void test_Vigenere_27(){
    try {
      char c = new VigenereCipher("password",lowers).decrypt1('A');
    } catch (NotInAlphabetException e){ return; }
    fail("shouldn't encrypt any characters not in the alphabet.");
  }
  
  @Test (timeout=2000) public void test_Vigenere_28(){
    try {
      String s = new VigenereCipher("password",lowers).encrypt("HELLO");
    } catch (NotInAlphabetException e){ return; }
    fail("shouldn't encrypt any characters not in the alphabet.");
  }
  @Test (timeout=2000) public void test_Vigenere_29(){
    try {
      String s = new VigenereCipher("password",lowers).decrypt("HELLO");
    } catch (NotInAlphabetException e){ return; }
    fail("shouldn't encrypt any characters not in the alphabet.");
  }
  
  @Test (timeout=2000) public void test_Vigenere_30(){
    VigenereCipher v = new VigenereCipher("swordfish");
    assertEquals("Vigenere Cipher (password='swordfish')",v.toString());
  }
  
}