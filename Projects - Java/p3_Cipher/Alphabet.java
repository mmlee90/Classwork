public class Alphabet{
  public static final Alphabet DEFAULT = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz 1234567890!@#$%^&*()_+-=[]{}\\|;:'\",./?<>");
  private String symbols;
  
  public Alphabet(String symbols){
   this.symbols = symbols;
  }
  
  public int indexOf(char c) throws NotInAlphabetException{
   Alphabet s = new Alphabet(symbols); //new alphabet object to pass in NotInAlphabetException
   for(int i = 0; i <this.symbols.length(); i++){//loop through entirety of symbols, if char at specified index matches c return it
    if(symbols.charAt(i)== c)
     return i;
     }
   throw new NotInAlphabetException(c,s);//if c isn't in symbols throw exception
  }
  
  public char get(int i) throws NotInAlphabetException{
   Alphabet s = new Alphabet(symbols);
   String msg = String.format("Asked to get symbol @%d, but length of Alphabet is %d", i, this.symbols.length());
   if(i > 0){//for positive i
    if (i < this.symbols.length())//less than symbols.length() but still a positive number, meaning it is a valid index
     return symbols.charAt(i);
    else
      throw new NotInAlphabetException(msg, symbols.charAt(0),s);
   }
   else if (i < 0){//if i is negative
    if(i > -this.symbols.length())//check to see if it is still a valid negative index
     return symbols.charAt(this.symbols.length() + i);//add positive length value to i to return 
    else
     throw new NotInAlphabetException(msg,symbols.charAt(0),s);
   }
   else//if i == 0
     return symbols.charAt(i);
  }
  
  
  public int length(){
   return symbols.length(); 
  }
  
  
  public String getSymbols(){
   return this.symbols; 
  }
  
  
  public String toString(){
   return String.format("Alphabet(%s)",symbols); 
  }
  
  
  public boolean equals(Object other){
    Alphabet s = new Alphabet(symbols);
    if(other.toString().equals(s.toString()))//if they are the same return true
      return true;
    else
      return false;
  }
}

