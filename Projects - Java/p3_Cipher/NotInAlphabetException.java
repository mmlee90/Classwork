public class NotInAlphabetException extends RuntimeException{
 public final String msg;
 public final char offender;
 public final Alphabet a;
 
 public NotInAlphabetException(String msg, char offender, Alphabet a){
  this.msg = msg;
  this.offender = offender;
  this.a=a;
  System.out.println(this.msg);
  
 }
 public NotInAlphabetException(char offender, Alphabet a){
   this.offender = offender;
   this.a = a;
   this.msg = String.format("Not in alphabet: '%s' not found in %s.",offender, a);
   System.out.println(this.msg);
 }
 
 public String toString(){
  return msg;
 }
}