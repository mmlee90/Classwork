/* create a class that will censor out a specified string pattern with %!^*#@
 * create censor and the replacement string outside of the constructors
 */
import java.io.*;
public class CensoredWriter extends PrintWriter{
 //create variables to hold both the string to be censored and replacement string
 public String replacement = "%!^*#@"; 
 public String censor;
 
 public CensoredWriter(OutputStream o, String c) {
  super(o); //call parent constructor
  this.censor = c;

 }
 public CensoredWriter(File f, String c) throws Exception{
  super(f); //call parent constructor
  this.censor = c;
  
 }
 public CensoredWriter(String s, String c)throws Exception{
  super(s); //call parent constructor
  this.censor = c;
  
 }
 
//return the output whith the specified phrase replaced with the replacement text
 public String transform(String s){ 
  return s.replaceAll(this.censor,replacement);  
 }
 
 //print and println methods to override PrintWriter's existing print and println methods
 //transformed output is passed through parent class's print method
 public void print(String s){ 
  super.print(transform(s));
 }
 
 public void println(String s){
  super.println(transform(s));
 }
}