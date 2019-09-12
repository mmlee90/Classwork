class MorseCipher extends Cipher {
  
  public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
  public static final String[] codes = {
    ".-",    /* A */
    "-...",  /* B */    
    "-.-.",  /* C */    
    "-..",   /* D */
    ".",     /* E */    
    "..-.",  /* F */    
    "--.",   /* G */    
    "....",  /* H */
    "..",    /* I */    
    ".---",  /* J */    
    "-.-",   /* K */    
    ".-..",  /* L */
    "--",    /* M */    
    "-.",    /* N */    
    "---",   /* O */    
    ".--.",  /* P */
    "--.-",  /* Q */    
    ".-.",   /* R */    
    "...",   /* S */    
    "-",     /* T */
    "..-",   /* U */    
    "...-",  /* V */    
    ".--",   /* W */    
    "-..-",  /* X */
    "-.--",  /* Y */    
    "--..",  /* Z */    
    ".----", /* 1 */    
    "..---", /* 2 */
    "...--", /* 3 */    
    "....-", /* 4 */    
    ".....", /* 5 */    
    "-....", /* 6 */
    "--...", /* 7 */    
    "---..", /* 8 */    
    "----.", /* 9 */    
    "-----", /* 0 */
  };
  
  public MorseCipher(){
    Alphabet alphabet = new Alphabet(letters);//created new alphabet so we can throw an exception later
   
  }
  public String encrypt(String plainText){
    String msg = "";
    String space = " ";
    for(int i = 0; i < plainText.length();i++){
      if (i==0)//for just the first character in the given string add the morse representation of that character to msg
        msg = msg + codes[letters.indexOf(plainText.charAt(i))];
      else if (space.equals(plainText.charAt(i)) == false && i != plainText.length())//every other char check if its a space or not. if its not add 3 spaces and then the next morse character
        msg = msg + "   " + codes[letters.indexOf(plainText.charAt(i))]; 
      else if(space.equals(plainText.charAt(i)) == true)//if next char is a space add 7 spaces
        msg = msg + "       "; //7 spaces    
    }
    return msg;
  }
  public String decrypt(String cryptText){
    String code = "";
    String space = " ";
    String answer = "";
    int numSpaces = 0;
    for(int i = 0; i < cryptText.length()-1;i++){
      if(i == 0)//for first character, add that dash or dot to code
        code = code + cryptText.charAt(i);        
      else if(space.equals(cryptText.charAt(i)) == false)//if next char in cryptText isn't a space add the next dot or dash to code
        code = code + cryptText.charAt(i);
      else if(space.equals(cryptText.charAt(i)) == true){//if next is a space
        numSpaces = numSpaces + 1;//add one to counter
        if(space.equals(cryptText.charAt(i+1))==true){
          if (numSpaces == 7){//if there are 7 spaces add a space to string answer
            answer = answer + " ";
            numSpaces = 0;
          }
          else if(numSpaces == 3){//if 3 this means theres no space, add the next letter directly to answer with no space
            for(int x = 0;i < codes.length;x++){
              if (code.equals(codes[x])==true)
                answer = answer + letters.charAt(x);   
            }
            code = ""; //reset code
          }
          else
            continue;  
       }
      return answer;
     }
   }
   return answer;
  }
}