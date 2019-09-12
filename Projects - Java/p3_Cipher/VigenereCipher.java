public class VigenereCipher extends SymmetricCipher{
  protected String password;
  protected int passwordPos;
  
  public VigenereCipher(String password,Alphabet alphabet){
   super(alphabet);
   this.password = password;
  }
  public VigenereCipher(String password){
   super(Alphabet.DEFAULT);
   this.password = password; 
  }
   
  public String getPassword(){
   return this.password; 
  }
  
  @Override protected char encrypt1(char c){
    int index;
    String symbs = this.alphabet.getSymbols();
    if (symbs.indexOf(c) == -1)//if char is not in alphabet throw exception
      throw new NotInAlphabetException(c,alphabet);
    if (passwordPos < password.length()){//check to make sure passwordPos is still a valid index    
      index = alphabet.indexOf(c) + alphabet.indexOf(password.charAt(passwordPos));//get character in password at indicated position, then find that chars index and add with index of c
      passwordPos = passwordPos + 1;
    }
   else{
     passwordPos = 0;//resets passwordPos
     index = alphabet.indexOf(c) + alphabet.indexOf(password.charAt(passwordPos));
   }
   return alphabet.get(index);
  }
  
  @Override protected char decrypt1(char c){
    int index;
    String symbs = this.alphabet.getSymbols();
    if (symbs.indexOf(c) == -1)//if char is not in alphabet throw exception
      throw new NotInAlphabetException(c,alphabet);
    if (passwordPos < password.length()){ //check to make sure passwordPos is still a valid index   
      index = alphabet.indexOf(c) - alphabet.indexOf(password.charAt(passwordPos)); //get character in password at indicated position, then find that chars index and subtract it from index of c
      passwordPos = passwordPos + 1;
    }
   else{
     passwordPos = 0;//resets passwordPos
     index = alphabet.indexOf(c) - alphabet.indexOf(password.charAt(passwordPos));
   }
   return alphabet.get(index);
    
  }
  public String encrypt(String s){
    passwordPos = 0;
    return super.encrypt(s);
    
  }
  public String decrypt(String s){
    passwordPos = 0; 
    return super.decrypt(s);
  }
  
  public String toString(){
   return String.format("Vigenere Cipher (password=%s)", this.password); 
  }
  
  
}