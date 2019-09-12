 import java.util.Scanner;
public class Board {
  // Convenience constructor to build from a 2D array of ints. If
  // negative int appears, treat it as an empty gem. Otherwise, the
  // numbers represent the kinds of the gems to be created.
  public int rows;
  public int cols;
  public Gem x[][];//create array with gem objects
  public Gem empty = Gem.makeEmpty();
  //Loop through a given int array and populate a gem array with new gem objects of int type. if int is negative it is an empty gem
  //
  public Board(int g[][]){          
    x = new Gem[g.length][g[0].length];
    for(int i  = 0; i < g.length;i++){
      for(int in = 0; in < g[0].length;in++){
        if (g[i][in] < 0){          
          x[i][in] = Gem.makeEmpty();
        }
        else{
          x[i][in] = new Gem(g[i][in]);
        }
      }
    }
    this.cols = x[0].length;
    this.rows = x.length;
  }

   // Make a deep copy of this Board. Requires that the 2D array of
  // gems be copied entirely to prevent shallow references to the same
  // arrays.  One of the required constructors is useful for this method.
  //create new int array same size as the one made in the constructor. loop through the current board and populate
  //the int array with with what kind the gem is
  //then pass the newly created int array through the board constructor and compare each location in new board to old board
  //and reapply flagged() status
  public Board clone(){
    int y[][] = new int[this.rows][this.cols];
    for(int i = 0; i <this.x.length;i++){
      for(int in = 0; in <this.x[0].length; in++){
        if(this.validGemAt(i,in)==false)
          y[i][in] = -1;
        else
          y[i][in] = Integer.parseInt(this.x[i][in].kindString());
      }
    }
    Board b = new Board(y);
    for(int i = 0; i <b.x.length;i++){
      for(int in = 0; in < b.x[0].length; in++){
       if(this.x[i][in].flagged()==true)
         b.x[i][in].setFlag();
      }
    }
    return b;
  }
  // Access the number of rows and columns. Assumes all rows have equal length.
  public int getRows(){
    return this.rows;
    }

  public int getCols(){
    return this.cols; 
  }

  // True if the given position is in bounds and contains a gem which
  // can be removed. False if the row/col is out of bounds or the
  // board is empty at the specified position.
  public boolean validGemAt(int r, int c){
    if (r < 0 || c < 0)
      return false;
    if (r < this.rows && c < this.cols)//check if out of bounds
      if(this.x[r][c].isEmpty() == true ||this.x[r][c].kindString().equals("-1"))
        return false;
      else
        return true;
    else
      return false;
  }

  // Return true if at least one gem exists on the board. False if all
  // board positions are empty.
  public boolean hasValidGem(){
    for(int i = 0; i <this.x.length;i++){
      for(int in = 0; in <this.x[0].length; in++){        
        if (this.validGemAt(i,in) == true)//if gem found is valid return true
          return true;
      }
    }
   return false; //if gem never equals a valid gem
  }

  // Retrieve gem at given location. Do not do bounds checking; out of
  // bounds positions should automatically raise an
  // IndexOutOfBoundsException.
  public Gem gemAt(int i, int j){
    return this.x[i][j]; 
  }

  // Clear all flags of gems on the board by invoking their
  // clearFlag() method.
  public void clearFlags(){
    int in = 0;
    for(int i = 0; i < this.x.length;i++){
      in = 0;
      for(in = 0; in < this.x[0].length; in++){
        this.gemAt(i,in).clearFlag();//use gem method
      }
    }
  }

  // Any gem flagged for removal will be removed.  Blocks that should
  // "fall" will do so and columns that should shift left do so.
  // Example: The board below has 4 Gems marked with X's which are
  // flagged.
  // 
  //     0  1  2  3  4
  //   ---------------
  // 0|  4     4      
  // 1|  9  1  6     2
  // 2|  6  5  1     9
  // 3|  2  5  5  x  x
  // 4|  5  9  x  x  x
  //
  // Calling doRemovals() should first remove them from the board
  // creating gaps.
  //
  //     0  1  2  3  4
  //   ---------------
  // 0|  4     4      
  // 1|  9  1  6     2
  // 2|  6  5  1     9
  // 3|  2  5  5      
  // 4|  5  9         
  //
  // Gems should then fall downward.
  //
  //     0  1  2  3  4
  //   ---------------
  // 0|  4           
  // 1|  9  1  4     
  // 2|  6  5  6      
  // 3|  2  5  1     2
  // 4|  5  9  5     9  
  //
  // Then any empty colums should be eliminated by shifting left.
  // 
  //     0  1  2  3  4
  //   ---------------
  // 0|  4           
  // 1|  9  1  4     
  // 2|  6  5  6   
  // 3|  2  5  1  2
  // 4|  5  9  5  9  
  //
  // You may wish to write some private helper methods to help break
  // this task down into manageable chunks.
  public void doRemovals(){
    int validGemCounter = 0;
    for(int i = 0; i <this.x.length;i++){//remove flagged gems
      for(int in = 0; in <this.x[0].length; in++){//loop through each row and column to see if gem has been flagged. if flagged empty it
        if (this.x[i][in].flagged() == true)
          this.x[i][in] = empty;
      }
    }
    for(int i = 0; i < this.x.length-1;i++){//drop gems
      for(int in = 0; in < this.x[0].length; in++){//loop through each row and column.check if gems beneath are empty and if so make them "fall"
        if (this.validGemAt(i,in) == true && this.validGemAt(i+1,in) == false){//valid gem and space right beneath is empty          
          if(i == 0){//if gem is in first row just make the switch
            this.x[i+1][in] = this.x[i][in].clone();          
            this.x[i][in] = Gem.makeEmpty();  
          }
          else{//if gem is in any other row, meaning there may be gems above that need to fall down as well
            this.x[i+1][in] = this.x[i][in].clone();          
            this.x[i][in] = Gem.makeEmpty();
            for(int counter = i; counter > 0;counter--){//set counter equal to the row that we are on in the loop. work backwards to make the changes
             this.x[counter][in] = this.x[counter-1][in].clone();          
             this.x[counter-1][in] = Gem.makeEmpty(); 
            }
          }
        }
      }    
    }
    for(int i = 0; i < 1;i++){//part to shift gems to the left. Only iterate once as code below will loop through entire column
      for(int in = this.x[0].length - 1 ; in > 0 ; in--){//start from the right
        if(in == this.x[0].length -1){//if last column or first iteration
          validGemCounter = 0;
          for(int row = 0; row < this.x.length;row++){//checks all gems in column to the left
            if(this.validGemAt(row,in -1)==true)
              validGemCounter += 1;
          }
          if(validGemCounter == 0){//if the entire column to the left is empty shift the column over
            for(int row = 0; row < this.x.length;row++){
              this.x[row][in-1] = this.x[row][in].clone();
              this.x[row][in] = Gem.makeEmpty();         
            }
          }
          else
            continue;
        }
        else if (in > 0){//middle columns
          validGemCounter = 0;
          for(int row = 0; row < this.x.length;row++){
            if(this.validGemAt(row,in -1)==true)//checks all gems in one column to the left
            validGemCounter += 1;
          }          
          if(validGemCounter == 0){//if the entire column to the left is empty shift the column over
            for(int row = 0; row < this.x.length;row++){//first do the shift
              this.x[row][in-1] = this.x[row][in].clone();
              this.x[row][in] = Gem.makeEmpty();         
            }
            validGemCounter = 0;
            for(int col = in;col < this.x[0].length -1;col++){//now shift everything else that was to the right over one
              for(int row = 0; row < this.x.length;row++){//checks to make sure current column is empty
                if(this.validGemAt(row,col)==true)
                  validGemCounter += 1;
              }
              if(validGemCounter == 0){//if the column is empty shift the next column over
                for(int row = 0; row < this.x.length;row++){//first do the shift
                  this.x[row][col] = this.x[row][col+1].clone();
                  this.x[row][col+1] = Gem.makeEmpty();         
                }
              }
            }          
          }
          else
            continue;
        }
      }
    }
  }

  // Convert to a simple saveable string. This string should have each
  // gem space separated for easy reading with Scanner. Empty
  // locations on the board should be denoted by a period (.) as in
  // the following sample:
  //
  // . . 4 . . 
  // . 10 6 8 2 
  // . 5 1 5 9 
  // 2 5 5 8 4 
  // 5 9 4 9 5 
  // 
  // Each line ends with a newline (\n) character.
  public String saveString(){
    String save = "";
    for(int i = 0; i <this.x.length;i++){
      for(int in = 0; in <this.x[0].length; in++){
        if (in == this.x[0].length - 1 && this.validGemAt(i,in) == false)//last gem in row and its empty
          save = save + "." + " " + "\n";
        else if(in == this.x[0].length - 1)//if last gem is not empty
          save = save + this.x[i][in].kindString() + " " +  "\n";
        else
          if (this.validGemAt(i,in) == false)//if gem is empty
            save = save + "." + " ";
          else
            save = save + this.x[i][in].kindString() + " ";
      }
    }
    return save;    
  }

  // Create a board from the given string.  The format accepted should
  // be identical to what is produced by saveString(). Parsing this
  // string will require two passes through the string, first to count
  // the size, then to read the gems and spaces.
  public static Board fromSaveString(String s){
    int r = 0; //rows counter
    int c = 0;// cols counter
    String split[] = s.split("\n");
    r = split.length;
    String noSpaces[] = new String[r];
    String line = "";
    for(int i = 0; i < split.length;i++){//remove spaces and populate a string array with no spaces
      for(int in = 0; in < split[0].length();in++){
        if(split[i].charAt(in) == ' ')
          continue;
        else if (split[i].charAt(in) == '.')
          line = line + split[i].charAt(in);
        else//if its an integer
          line = line + split[i].charAt(in);
      }
      noSpaces[i] = line;
      line = "";
    }
    c = noSpaces[0].length();
    int y[][] = new int[r][c];
    for(int i = 0;i < r;i++){//populate int array with values from
      for(int in = 0;in <c;in++){
        if(noSpaces[i].charAt(in) == '.')
          y[i][in] = -1;
        else
          y[i][in] = Character.getNumericValue(noSpaces[i].charAt(in));
      }
    }
    Board b = new Board(y);
    return b; 
  }


  // Implementation Provided. Fancy display string version of the
  // board; assumes gem kinds fit in 2 chars. Flagged gems have an
  // asterisk put to the right of them.
  public String toString(){
    StringBuilder sb = new StringBuilder();
    // Col header
    sb.append("   ");
    for(int j = 0; j < this.getCols(); j++){
      sb.append(String.format("%3d",j));
    }
    sb.append("\n");
    sb.append("   ");
    for(int j=0; j<this.getCols(); j++){
      sb.append("---");
    }
    sb.append("\n");
    // Main body of board
    for(int i=0; i<this.getRows(); i++){
      sb.append(String.format("%2d|",i));
      for(int j=0; j<this.getCols(); j++){
        Gem g = this.gemAt(i,j);
        char flag = g.flagged() ? '*' : ' ';
        if(j==0){
          sb.append(String.format("%3s%c",g.kindString(),flag));
        }
        else{
          sb.append(String.format("%2s%c",g.kindString(),flag));
        }
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
