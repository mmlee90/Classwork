public abstract class SymmetricCipher{
  protected Alphabet alphabet;
  
  public SymmetricCipher(Alphabet alphabet){
    super();
    this.alphabet = alphabet; 
  }
  
  
  public int wrapInt(int i){
   int len = this.alphabet.length(); 
   int index = i;
   while(index < 0)//if given negative i
     index = index + len;//add positive length to negative i to see if it will become a valid index
     if (index > 0 && index <=len -1)//if it falls between 0 and length of alphabet return the newly calculated index
       return index;
   while (index > 0){//positive i
     if (index > len - 1){//if index is out of bounds
      index = index - len;//subtract len until it is within bounds
      if (index <= len-1)
        return index;
     }  
   }
   if (i == 0 || i == len)
     return 0;
   return 0;
  }

  
  public int rotate(int index, int shift){
    int len = this.alphabet.length();
    int shiftsRemaining = shift;//use number of spaces to shift as a counter
    
    if (shift > 0){//positive shift
     while ((index + shiftsRemaining) > len){//if given index + remaining spaces to shift is out of bounds
      shiftsRemaining = wrapInt(shift);//wrap shift remaining
     }
     return index + shiftsRemaining;
    }
    else if (shift < 0){//negative shift
      if(index + shiftsRemaining <-len){//if it is more than length
        while ((index + shiftsRemaining) < -len){//keep wrapping until it is in bounds
         shiftsRemaining = wrapInt(shift);
        }
        return index + shiftsRemaining;
        }
      else if (index + shiftsRemaining < len && index + shiftsRemaining > 0)//if less than length but more than 0 return it
        return index + shiftsRemaining;
      else if (index + shiftsRemaining > -len)
       return len + (index + shiftsRemaining);
      return index;
    }
    return index;
  }
  
  
  public Alphabet getAlphabet(){
   return alphabet; 
  }
  
  
  public String encrypt(String s){
    String msg = "";
    char[]ans = s.toCharArray(); //make array to loop through
    for(char x: ans){//for each character encrypt it and add it to msg
     msg = msg + encrypt1(x); 
    }
    return msg;
  }
  
  
  public String decrypt(String s){
    String msg = "";
    char[]ans = s.toCharArray();//make array to loop through
    for(char x: ans){//for each character decrypt it and add it to msg
     msg = msg + decrypt1(x); 
    }
    return msg;
  }
  
  
  protected abstract char encrypt1(char c);
  protected abstract char decrypt1(char c);
  
  
}