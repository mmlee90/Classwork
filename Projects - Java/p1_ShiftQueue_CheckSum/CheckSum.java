public class CheckSum{

  // Compute Fletcher's checksum for the integers given.  Refer to the
  // project specification for pseudocode of how to compute this.
  // Return the 64-bit checksum as an array of 2 integers.
  /*loop through each element of array data
    * add element i of data onto sum1 putting the result in sum1
    * add sum1 onto sum2 putting the result in sum2
   */
  public static int [] computeCheckSum(int [] data){
   int sum1 = 0;
   int sum2 = 0;
   
   for (int i=0; i<= data.length-1; i++){
    sum1 = sum1 + data[i];
    sum2 = sum2 + sum1;
   }
   int [] result = new int[2];
   result[0]=sum1;
   result[1]=sum2;  
   return result;                                                      
   }
  
  // Convenience method. Convert the standard string message to an
  // array of integers then call the other version of computeCheckSum
  // on it.  The provided class RunCheckSum class contains a useful
  // method for converting strings to arrays of integers.
  /*convert given string message int and then run computeCheckSum
   * to see if what is returned is equivalent to the expected
   */
  public static int [] computeCheckSum(String message){
   int [] converted_message = RunCheckSum.stringToInts(message);
   return computeCheckSum(converted_message);
  }

  // Verify that the checksum computed from the array data matches the
  // given expected checksum.  Argument expected is an array of two
  // integers which is the 64-bit checksum which is expected.  If the
  // computed checksum matches the expected, return true.  Otherwise
  // return false.
  /* given an array data, computeCheckSum and compare it to the expected array
   * check each element against each other to make sure they are equal
   */
  public static boolean verifyCheckSum(int [] data, int [] expected){
    int [] checksum_data = computeCheckSum(data);
    for (int i =0; i <= checksum_data.length - 1; i++){
     if (checksum_data[i]==expected[i])
       continue;
     else
       return false;
    }
    return true;
  }
  // Convenience method.  Convert the standard string message to an
  // array of ints.  Convert the string expectedHex to an array of
  // integers as well but note that the format of this string is
  // different: it represents hexadecimal numbers and must be
  // converted with a different function from the RunCheckSum class.
  // Return true if the checksum computed from data matches the
  // expected; use the other version of verifyCheckSum for this.
  /* convert given string into an array of ints and then computeCheckSum
   * compare the result of that to the array version of what is expected
   */
  public static boolean verifyCheckSum(String message, String expectedHex){
   
   int [] converted_message = RunCheckSum.stringToInts(message);
   int [] converted_expectedHex = RunCheckSum.hexStringToInts(expectedHex);
   int [] checksum_message = computeCheckSum(converted_message);
   //check each element against each other to make sure they are equal
   for (int i = 0; i <= checksum_message.length - 1; i++){
    if (checksum_message[i]==converted_expectedHex[i])
      continue;
    else
      return false;
   }
   return true;  
  }  
}