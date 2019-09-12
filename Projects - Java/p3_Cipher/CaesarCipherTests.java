import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class CaesarCipherTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("CaesarCipherTests");
  }
  
  static Alphabet uppers     = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ ."); 
  static Alphabet lowers     = new Alphabet("abcdefghijklmnopqrstuvwxyz");
  static Alphabet lowerSpace = new Alphabet("abcdefghijklmnopqrstuvwxyz ");
  
  
  /* CaesarCipher Tests */
  
  @Test (timeout=2000) public void test_Caesar_1(){ assertEquals(5,new CaesarCipher(5).shift); }
  @Test (timeout=2000) public void test_Caesar_2(){ assertEquals(50,new CaesarCipher(50).shift); }
  @Test (timeout=2000) public void test_Caesar_3(){ assertEquals(-8,new CaesarCipher(-8).shift); }
  @Test (timeout=2000) public void test_Caesar_4(){ assertEquals(Alphabet.DEFAULT,new CaesarCipher(20).getAlphabet()); }
  
  @Test (timeout=2000) public void test_Caesar_5(){
    Alphabet a =  new Alphabet("ABCDEFGHIJKLMNOP");
    CaesarCipher c = new CaesarCipher(10,a);
    assertEquals(a, c.alphabet);
  }
  
  @Test (timeout=2000) public void test_Caesar_6(){ assertEquals('X',new CaesarCipher(  0) .encrypt1('X')); }
  @Test (timeout=2000) public void test_Caesar_7(){ assertEquals('Y',new CaesarCipher(  1) .encrypt1('X')); }
  @Test (timeout=2000) public void test_Caesar_8(){ assertEquals('1',new CaesarCipher(  2) .encrypt1('z')); }
  @Test (timeout=2000) public void test_Caesar_9(){ assertEquals('e',new CaesarCipher(100) .encrypt1('X')); }
  @Test (timeout=2000) public void test_Caesar_10(){ assertEquals('S',new CaesarCipher( -5) .encrypt1('X')); }
  @Test (timeout=2000) public void test_Caesar_11(){ assertEquals(',',new CaesarCipher(-10) .encrypt1('E')); }
  
  @Test (timeout=2000) public void test_Caesar_12(){ assertEquals('X',new CaesarCipher(  0) .decrypt1('X')); }
  @Test (timeout=2000) public void test_Caesar_13(){ assertEquals('W',new CaesarCipher(  1) .decrypt1('X')); }
  @Test (timeout=2000) public void test_Caesar_14(){ assertEquals('x',new CaesarCipher(  2) .decrypt1('z')); }
  @Test (timeout=2000) public void test_Caesar_15(){ assertEquals('Q',new CaesarCipher(100) .decrypt1('X')); }
  @Test (timeout=2000) public void test_Caesar_16(){ assertEquals('c',new CaesarCipher( -5) .decrypt1('X')); }
  @Test (timeout=2000) public void test_Caesar_17(){ assertEquals('P',new CaesarCipher(-11) .decrypt1('E')); }
  
  
  @Test (timeout=2000) public void test_Caesar_18(){ assertEquals("catfood for cats",new CaesarCipher(0, lowerSpace).encrypt("catfood for cats")); }
  @Test (timeout=2000) public void test_Caesar_19(){ assertEquals("catfood for cats",new CaesarCipher(0, lowerSpace).decrypt("catfood for cats")); }
  @Test (timeout=2000) public void test_Caesar_20(){ assertEquals("trjweeu",new CaesarCipher(17, lowerSpace).encrypt("catfood")); }
  @Test (timeout=2000) public void test_Caesar_21(){ assertEquals("catfood",new CaesarCipher(17, lowerSpace).decrypt("trjweeu")); }
  @Test (timeout=2000) public void test_Caesar_22(){ assertEquals("bjvxwyfrwmzn",new CaesarCipher(-5, lowerSpace).encrypt("go backwards")); }
  @Test (timeout=2000) public void test_Caesar_23(){ assertEquals("go backwards",new CaesarCipher(-5, lowerSpace).decrypt("bjvxwyfrwmzn")); }
  @Test (timeout=2000) public void test_Caesar_24(){
    CaesarCipher c30 = new CaesarCipher(30, lowerSpace);
    CaesarCipher c3  = new CaesarCipher( 3, lowerSpace);
    String msg = "roundabout equal effects";
    assertEquals(c3.encrypt(msg), c30.encrypt(msg));
  }
  @Test (timeout=2000) public void test_Caesar_25(){
    CaesarCipher c = new CaesarCipher(300, lowerSpace);
    String clear   = "long messages are no extra hassle";
    String crypted = "orqjcphvvdjhvcduhcqrch wudckdvvoh";
    assertEquals(crypted,c.encrypt(clear));
  }
  @Test (timeout=2000) public void test_Caesar_26(){
    CaesarCipher c = new CaesarCipher(300, lowerSpace);
    String clear   = "long messages are no extra hassle";
    String crypted = "orqjcphvvdjhvcduhcqrch wudckdvvoh";
    assertEquals(clear,c.decrypt(crypted));
  }
  @Test (timeout=2000) public void test_Caesar_27(){
    CaesarCipher c = new CaesarCipher(300, new Alphabet(Alphabet.DEFAULT.getSymbols()+"\n"));
    String clear = "Four score and seven years ago our fathers brought forth on this continent, a new nation, conceived in Liberty, and dedicated to the proposition that all men are created equal.\n\nNow we are engaged in a great civil war, testing whether that nation, or any nation so conceived and so dedicated, can long endure. We are met on a great battle-field of that war. We have come to dedicate a portion of that field, as a final resting place for those who here gave their lives that that nation might live. It is altogether fitting and proper that we should do this.\n\nBut, in a larger sense, we can not dedicate -- we can not consecrate -- we can not hallow -- this ground. The brave men, living and dead, who struggled here, have consecrated it, far above our poor power to add or detract. The world will little note, nor long remember what we say here, but it can never forget what they did here. It is for us the living, rather, to be dedicated here to the unfinished work which they who fought here have thus far so nobly advanced. It is rather for us to be here dedicated to the great task remaining before us -- that from these honored dead we take increased devotion to that cause for which they gave the last full measure of devotion -- that we here highly resolve that these dead shall not have died in vain -- that this nation, under God, shall have a new birth of freedom -- and that government of the people, by the people, for the people, shall not perish from the earth.";
    String crypted = "X6@9*0u69w*s5v*0w#w5*^ws90*sy6*6@9*xs!zw90*t96@yz!*x69!z*65*!z 0*u65! 5w5!L*s*5w$*5s! 65L*u65uw #wv* 5*d tw9!^L*s5v*vwv us!wv*!6*!zw*796760 ! 65*!zs!*s33*4w5*s9w*u9ws!wv*w8@s3MRRf6$*$w*s9w*w5ysywv* 5*s*y9ws!*u # 3*$s9L*!w0! 5y*$zw!zw9*!zs!*5s! 65L*69*s5^*5s! 65*06*u65uw #wv*s5v*06*vwv us!wvL*us5*365y*w5v@9wM*ow*s9w*4w!*65*s*y9ws!*ts!!3w\nx w3v*6x*!zs!*$s9M*ow*zs#w*u64w*!6*vwv us!w*s*769! 65*6x*!zs!*x w3vL*s0*s*x 5s3*9w0! 5y*73suw*x69*!z60w*$z6*zw9w*ys#w*!zw 9*3 #w0*!zs!*!zs!*5s! 65*4 yz!*3 #wM*a!* 0*s3!6yw!zw9*x !! 5y*s5v*7967w9*!zs!*$w*0z6@3v*v6*!z 0MRRT@!L* 5*s*3s9yw9*0w50wL*$w*us5*56!*vwv us!w*\n\n*$w*us5*56!*u650wu9s!w*\n\n*$w*us5*56!*zs336$*\n\n*!z 0*y96@5vM*lzw*t9s#w*4w5L*3 # 5y*s5v*vwsvL*$z6*0!9@yy3wv*zw9wL*zs#w*u650wu9s!wv* !L*xs9*st6#w*6@9*7669*76$w9*!6*svv*69*vw!9su!M*lzw*$693v*$ 33*3 !!3w*56!wL*569*365y*9w4w4tw9*$zs!*$w*0s^*zw9wL*t@!* !*us5*5w#w9*x69yw!*$zs!*!zw^*v v*zw9wM*a!* 0*x69*@0*!zw*3 # 5yL*9s!zw9L*!6*tw*vwv us!wv*zw9w*!6*!zw*@5x 5 0zwv*$692*$z uz*!zw^*$z6*x6@yz!*zw9w*zs#w*!z@0*xs9*06*56t3^*sv#s5uwvM*a!* 0*9s!zw9*x69*@0*!6*tw*zw9w*vwv us!wv*!6*!zw*y9ws!*!s02*9w4s 5 5y*twx69w*@0*\n\n*!zs!*x964*!zw0w*z6569wv*vwsv*$w*!s2w* 5u9ws0wv*vw#6! 65*!6*!zs!*us@0w*x69*$z uz*!zw^*ys#w*!zw*3s0!*x@33*4ws0@9w*6x*vw#6! 65*\n\n*!zs!*$w*zw9w*z yz3^*9w063#w*!zs!*!zw0w*vwsv*0zs33*56!*zs#w*v wv* 5*#s 5*\n\n*!zs!*!z 0*5s! 65L*@5vw9*Y6vL*0zs33*zs#w*s*5w$*t 9!z*6x*x9wwv64*\n\n*s5v*!zs!*y6#w954w5!*6x*!zw*7w673wL*t^*!zw*7w673wL*x69*!zw*7w673wL*0zs33*56!*7w9 0z*x964*!zw*ws9!zM";
    
    // does it encrypt, decrypt, and cancel out?
    assertEquals(crypted, c.encrypt(clear));
    assertEquals(clear,c.decrypt(crypted));
    assertEquals(clear, c.decrypt(c.encrypt(clear)));
  }    
  
  @Test (timeout=2000) public void test_Caesar_28(){ assertEquals("NYLLUFZSPTLFPZFKLHKSDG",new CaesarCipher(7,uppers).encrypt("GREEN SLIME IS DEADLY.")); }
  @Test (timeout=2000) public void test_Caesar_29(){ assertEquals("SBQQZKCXUYQKUCKO YUZS",new CaesarCipher(40,uppers).encrypt("GREEN SLIME IS COMING")); }
  @Test (timeout=2000) public void test_Caesar_30(){ assertEquals("GMXXCKUZKDTQKGMI",new CaesarCipher(40,uppers).encrypt("WALLS IN THE WAY")); }
  @Test (timeout=2000) public void test_Caesar_31(){ assertEquals("WALLS IN THE WAY",new CaesarCipher(40,uppers).decrypt("GMXXCKUZKDTQKGMI")); }
  
  
  @Test (timeout=2000) public void test_Caesar_32(){
    try {
      char c = new CaesarCipher(12,lowers).encrypt1('A');
    } catch (NotInAlphabetException e){ return; }
    fail("shouldn't encrypt any characters not in the alphabet.");
  }
  
  @Test (timeout=2000) public void test_Caesar_33(){
    try {
      char c = new CaesarCipher(12,lowers).decrypt1('A');
    } catch (NotInAlphabetException e){ return; }
    fail("shouldn't encrypt any characters not in the alphabet.");
  }
  
  @Test (timeout=2000) public void test_Caesar_34(){
    try {
      String s = new CaesarCipher(12,lowers).encrypt("HELLO");
    } catch (NotInAlphabetException e){ return; }
    fail("shouldn't encrypt any characters not in the alphabet.");
  }
  @Test (timeout=2000) public void test_Caesar_35(){
    try {
      String s = new CaesarCipher(12,lowers).decrypt("HELLO");
    } catch (NotInAlphabetException e){ return; }
    fail("shouldn't encrypt any characters not in the alphabet.");
  }
  
  @Test (timeout=2000) public void test_Caesar_36(){
    CaesarCipher c = new CaesarCipher(12,lowers);
    assertEquals("Caesar Cipher (shift=12)",c.toString());
  }
  
  
  
}