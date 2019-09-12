public class CaesarCipher extends SymmetricCipher{
  protected int shift;

  public CaesarCipher(int shift, Alphabet alphabet){
   super(alphabet);
   this.shift = shift;
  }
  
  public CaesarCipher(int shift){
    super(Alphabet.DEFAULT);
    this.shift = shift;
  }
  
  public String encrypt(String s){
   String encryptedString = "";
   if (shift == 0){//if no encryption return the inputted string
     return s;
   }
   for(int i = 0; i < s.length();i++){//otherwise loop through given string
     encryptedString = encryptedString + encrypt1(s.charAt(i)); //add each encrypted character to the answer string
   }
   return encryptedString;
   
  }
  
  
  public String decrypt(String s){
    String decryptedString = "";
    if (shift == 0)//if no encryption return the inputted string
      return s;
    for(int i = 0; i < s.length();i++){//otherwise loop through given string
      decryptedString = decryptedString + decrypt1(s.charAt(i));//add each decrypted character to the answer string
    }
    return decryptedString;
  }
  
  
  @Override public char encrypt1(char c){
    String symbs = this.alphabet.getSymbols();
    int index = symbs.indexOf(c) + shift;
    if (symbs.indexOf(c) == -1)//if char is not in alphabet throw exception
      throw new NotInAlphabetException(c,alphabet);
    else if (index > 0){ //positve index
      if (index < alphabet.length())//positive and less than length means its a valid index. return it
        return alphabet.get(index);
      else if (index > alphabet.length()){//positive but out of bounds
        shift = rotate(alphabet.indexOf(c), shift);//rotate it until it is in bounds
        return alphabet.get(index);
      }
      else if (index == alphabet.length())//if index is same as length, return first char
        return alphabet.get(0);
      }
     else if (index < 0){//negative index
      if(index > -alphabet.length())//negative but still within bounds
        return alphabet.get(index);
      else if (index < -alphabet.length()){//out of bounds
        shift = rotate(alphabet.indexOf(c), shift);//rotate it until it is in bounds
        return alphabet.get(index);
      }
      else if (index==-alphabet.length())//if index is same as length, return first char
        return alphabet.get(0);
     }
    return c; 
  }

  @Override public char decrypt1(char c){
    String symbs = this.alphabet.getSymbols();
    int index = symbs.indexOf(c) - shift;
    if (symbs.indexOf(c) == -1)//if char is not in alphabet
      throw new NotInAlphabetException(c,alphabet);
    else if(index > 0){//positive index
      if(index < alphabet.length())//positive and within bounds
        return alphabet.get(index);
      else if (index > alphabet.length()){//out of bounds
        shift = rotate(alphabet.indexOf(c), shift);
        return alphabet.get(rotate(alphabet.indexOf(c), shift));
      }
      else if (index == alphabet.length())
        return alphabet.get(0);
    }      
    else if(index < 0){//negative index
      if(index > -alphabet.length())//negative and within bounds
        return alphabet.get(alphabet.length()+index);
      else if (index < -alphabet.length()){//out of bounds
        shift = rotate(alphabet.indexOf(c),shift);
        return alphabet.get(alphabet.length() + shift + symbs.indexOf(c));
      }
      else if (index == -alphabet.length())
        return alphabet.get(0);
    }   
  return c;
  }
  
  
  public String toString(){
    return String.format("Caesar Cipher (shift=%d)", this.shift);
  }
  
  
  
  
}