/** Example of using unit tests for programming assignment 3.  This is
  * partially how your code will be graded.  Later in the class we will
  * write our own unit tests.  To run them on the command line, make
  * sure that the junit-cs211.jar is in the project directory.
  * 
  *  demo$ javac -cp .:junit-cs211.jar *.java     # compile everything
  *  demo$ java  -cp .:junit-cs211.jar P3Tests    # run tests
  * 
  * On windows replace : with ; (colon with semicolon)
  *  demo$ javac -cp .;junit-cs211.jar *.java     # compile everything
  *  demo$ java  -cp .;junit-cs211.jar P3Tests    # run tests
  */

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.util.Arrays.*;

public class P3Tests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main(
                                    "AlphabetTests",
                                    "NotInAlphabetExceptionTests",
                                    /* Cipher - no direct tests.  */
                                    "SymmetricCipherTests",
                                    "CaesarCipherTests",
                                    "VigenereCipherTests",
                                    "MorseCipherTests"
                                   );
  }
}
