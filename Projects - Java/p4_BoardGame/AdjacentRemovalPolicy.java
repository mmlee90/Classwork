public class AdjacentRemovalPolicy implements RemovalPolicy{
  
  // Flag all gems that would be removed if location (r,c) were
  // selected by this policy.
  // First clear board of prior flags and set flag counter to 0. depending on where the gem is check to see if the gems
  // adjacent to it are equal and if so setFlag
  private int numFlags = 0;
  public void flagConnectedGems(int row, int col, Board b) {
    numFlags = 0;
    b.clearFlags();
    if(b.validGemAt(row,col)){
      numFlags += 1;
      b.x[row][col].setFlag();
      //top left corner
      if(row == 0 && col == 0){
        if(b.x[row + 1][col].sameKind(b.x[row][col]) == true){
          b.x[row + 1][col].setFlag();
          numFlags += 1;
        }
        if(b.x[row][col+1].sameKind(b.x[row][col]) == true){
          b.x[row][col+1].setFlag();
          numFlags += 1;
        }
      }
      //top right corner
      else if(row == 0 && col == b.x[0].length-1){
        if(b.x[row + 1][col].sameKind(b.x[row][col]) == true){
          b.x[row + 1][col].setFlag();
          numFlags += 1;
        }if(b.x[row][col-1].sameKind(b.x[row][col]) == true){
          b.x[row][col-1].setFlag();
          numFlags += 1;
        }
      }
      //bottom left corner
      else if(row == b.x.length-1 && col ==0){
        if(b.x[row - 1][col].sameKind(b.x[row][col]) == true){
          b.x[row - 1][col].setFlag();
          numFlags += 1;
        }
        if(b.x[row][col+1].sameKind(b.x[row][col]) == true){
          b.x[row][col+1].setFlag();
          numFlags += 1;
        }        
      }
      //bottom right corner
      else if(row == b.x.length-1 && col == b.x[0].length-1){
        if(b.x[row - 1][col].sameKind(b.x[row][col]) == true){
          b.x[row - 1][col].setFlag();
          numFlags += 1;
        }
        if(b.x[row][col-1].sameKind(b.x[row][col]) == true){
          b.x[row][col-1].setFlag();
          numFlags += 1;
        }
      }
      //left edge (not corners)
      else if(col == 0){
        if(b.x[row + 1][col].sameKind(b.x[row][col]) == true){
          b.x[row + 1][col].setFlag();
          numFlags += 1;
        }
        if(b.x[row][col+1].sameKind(b.x[row][col]) == true){
          b.x[row][col+1].setFlag();
          numFlags += 1;
        }
        if(b.x[row-1][col].sameKind(b.x[row][col]) == true){
          b.x[row-1][col].setFlag();
          numFlags += 1;
        }
      }
      //right edge (not corners)
      else if(col == b.x[0].length-1){
        if(b.x[row + 1][col].sameKind(b.x[row][col]) == true){
          b.x[row + 1][col].setFlag();
          numFlags += 1;
        }
        if(b.x[row-1][col].sameKind(b.x[row][col]) == true){
          b.x[row-1][col].setFlag();
          numFlags += 1;
        }
        if(b.x[row][col-1].sameKind(b.x[row][col]) == true){
          b.x[row][col-1].setFlag();
          numFlags += 1;
        }
      }
      //top row (not corners)
      else if(row == 0){
        if(b.x[row][col-1].sameKind(b.x[row][col]) == true){
          b.x[row][col-1].setFlag();
          numFlags += 1;
        }
        if(b.x[row+1][col].sameKind(b.x[row][col]) == true){
          b.x[row+1][col].setFlag();
          numFlags += 1;
        }
        if(b.x[row][col+1].sameKind(b.x[row][col]) == true){
          b.x[row][col+1].setFlag();
          numFlags += 1;
        }
      }
      //bottom row (not corners)
      else if(row == b.x.length-1){
        if(b.x[row][col-1].sameKind(b.x[row][col]) == true){
          b.x[row][col-1].setFlag();
          numFlags += 1;
        }
        if(b.x[row][col+1].sameKind(b.x[row][col]) == true){
          b.x[row][col+1].setFlag();
          numFlags += 1;
        }
        if(b.x[row-1][col].sameKind(b.x[row][col]) == true){
          b.x[row-1][col].setFlag();
          numFlags += 1;
        }
      }
      //everything in the middle
      else{
        if(b.x[row][col-1].sameKind(b.x[row][col]) == true){
          b.x[row][col-1].setFlag();
          numFlags += 1;
        }
        if(b.x[row][col+1].sameKind(b.x[row][col]) == true){
          b.x[row][col+1].setFlag();
          numFlags += 1;
        }
        if(b.x[row-1][col].sameKind(b.x[row][col]) == true){
          b.x[row-1][col].setFlag();
          numFlags += 1;
        }
        if(b.x[row+1][col].sameKind(b.x[row][col]) == true){
          b.x[row+1][col].setFlag();
          numFlags += 1;
        }
      }       
    }        
  }
   // Provides the score for the suggested (r,c) move. The gems
  // involved in the move may be flagged in the process or may remain
  // unflagged.
  // If theres is a valid gem at the location indicated use flagConnectedGems to evaluate how many flags/what you score
  public int scoreMove(int row, int col, Board b){
    if(b.validGemAt(row,col)){
      this.flagConnectedGems(row,col,b);  
    }
    return numFlags * numFlags;
  }
  
  
  // a human-friendly description of this policy's rules.
  public String description(){
    return "Adjacent gems of the same kind will be removed";
  }
  
  // return the name of the class
  public String toString(){
    return "AdjacentRemovalPolicy"; 
  }
  
  // return the name of the class
  public String saveString(){
    return "AdjacentRemovalPolicy"; 
  }
  
  
}