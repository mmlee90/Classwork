public class WholeRowColRemovalPolicy implements RemovalPolicy{
  
  // Flag all gems that would be removed if location (r,c) were
  // selected by this policy.
  private int numFlags = 0;
  
  //first check to see if indicated gem is valid
  //check whole column/row that the gem is in to see if the gems adjacent to it are the same 
  //if next gem isn't valid stop checking. policy only removes contiguous matching gems
  public void flagConnectedGems(int row, int col, Board b){
    numFlags = 0;
    b.clearFlags();
    if(b.validGemAt(row,col)==true){
     numFlags +=1;
     b.x[row][col].setFlag();
     //top left corner
     if(row == 0 && col == 0){
       for(int i = 0;i < b.x.length -1;i++){
         if(b.x[i+1][col].sameKind(b.x[row][col])==true){
           b.x[i+1][col].setFlag();
           numFlags +=1;
         }
         else
           break;
       }
       for(int in = 0;in < b.x[0].length - 1;in++){
         if(b.x[row][in+1].sameKind(b.x[row][col])==true){
           b.x[row][in+1].setFlag();
           numFlags +=1;
         }
         else
           break;
       }
     }
     //top right corner
     else if(row == 0 && col == b.x[0].length-1){
       for(int i = row;i < b.x.length -1;i++){
        if(b.x[i + 1][col].sameKind(b.x[row][col]) == true){
          b.x[i + 1][col].setFlag();
          numFlags += 1;
        }
        else
           break;
       }
        for(int in = b.x[0].length -1;in > 0 ;in--){
          if(b.x[row][in-1].sameKind(b.x[row][col]) == true){
            b.x[row][in-1].setFlag();
            numFlags += 1;
          }
          else
           break;
        }
       }
     //bottom left corner
      else if(row == b.x.length-1 && col ==0){
        for(int i = b.x.length -1;i > 0 ;i--){
          if(b.x[i - 1][col].sameKind(b.x[row][col]) == true){
            b.x[i - 1][col].setFlag();
            numFlags += 1;
          }
          else
           break;
        }
        for(int in = 0;in < b.x[0].length-1 ;in++){
          if(b.x[row][in+1].sameKind(b.x[row][col]) == true){
            b.x[row][in+1].setFlag();
            numFlags += 1;
          }
          else
           break;
        }
        
      }
      //bottom right corner
      else if(row == b.x.length-1 && col == b.x[0].length-1){
        for(int i = b.x.length -1;i > 0 ;i--){
          if(b.x[i - 1][col].sameKind(b.x[row][col]) == true){
            b.x[i - 1][col].setFlag();
            numFlags += 1;
          }
          else
           break;
        }
        for(int in = b.x[0].length -1;in > 0 ;in--){
          if(b.x[row][in-1].sameKind(b.x[row][col]) == true){
            b.x[row][in-1].setFlag();
            numFlags += 1;
          }
          else
           break;
        }
      }
      //left edge
      else if(col == 0){
        for(int i = row; i < b.x.length-1;i++){
          if(b.x[i + 1][col].sameKind(b.x[row][col]) == true){
            b.x[i + 1][col].setFlag();
            numFlags += 1;
          }
          else
             break;
        }
        for(int in = col; in < b.x[0].length - 1;in++){
          if(b.x[row][in+1].sameKind(b.x[row][col]) == true){
            b.x[row][in+1].setFlag();
            numFlags += 1;
          }
          else
            break;
        }
        for(int i = row;i > 0;i--){
          if(b.x[i-1][col].sameKind(b.x[row][col]) == true){
            b.x[i-1][col].setFlag();
            numFlags += 1;
          }
          else
             break;
        }
      }
      //right edge
      else if(col == b.x[0].length-1){
        for(int i = row; i < b.x.length -1;i++){
          if(b.x[i + 1][col].sameKind(b.x[row][col]) == true){
            b.x[i + 1][col].setFlag();
            numFlags += 1;
          }
          else
             break;
        }
        for(int i = row;i > 0;i--){
          if(b.x[i-1][col].sameKind(b.x[row][col]) == true){
            b.x[i-1][col].setFlag();
            numFlags += 1;
          }
          else
             break;
        }
        for(int in = col;in > 0;in--){
          if(b.x[row][in-1].sameKind(b.x[row][col]) == true){
            b.x[row][in-1].setFlag();
            numFlags += 1;
          }
          else
            break;
        }
      }
      //top row
      else if(row == 0){
        for(int in = col; in > 0;in--){
          if(b.x[row][in-1].sameKind(b.x[row][col]) == true){
            b.x[row][in-1].setFlag();
            numFlags += 1;
          }
          else
             break;
        }
        for(int i = row;i < b.x.length-1;i++){
          if(b.x[i+1][col].sameKind(b.x[row][col]) == true){
            b.x[i+1][col].setFlag();
            numFlags += 1;
          }
          else
             break;
        }
        for(int in = col; in < b.x[0].length - 1;in++){
          if(b.x[row][in+1].sameKind(b.x[row][col]) == true){
            b.x[row][in+1].setFlag();
            numFlags += 1;
          }
          else
             break;
        }
      }
      //bottom row
      else if(row == b.x.length-1){
        for(int in = col; in > 0;in--){
          if(b.x[row][in-1].sameKind(b.x[row][col]) == true){
            b.x[row][in-1].setFlag();
            numFlags += 1;
          }
          else
             break;
        }
        for(int in = col; in < b.x[0].length-1;in++){
          if(b.x[row][in+1].sameKind(b.x[row][col]) == true){
            b.x[row][in+1].setFlag();
            numFlags += 1;
          }
          else
             break;
        }
        for(int i = row; i > 0;i--){
          if(b.x[i-1][col].sameKind(b.x[row][col]) == true){
            b.x[i-1][col].setFlag();
            numFlags += 1;
          }
          else
             break;
        }
      }
      //middle gems
      else{
        for(int in = col; in > 0;in--){
          if(b.x[row][in-1].sameKind(b.x[row][col]) == true){
            b.x[row][in-1].setFlag();
            numFlags += 1;
          }
          else
             break;
        }
        for(int in = col; in < b.x[0].length -1;in++){
          if(b.x[row][in+1].sameKind(b.x[row][col]) == true){
            b.x[row][in+1].setFlag();
            numFlags += 1;
          }
          else
             break;
        }
        for(int i = row; i > 0; i--){
          if(b.x[i-1][col].sameKind(b.x[row][col]) == true){
            b.x[i-1][col].setFlag();
            numFlags += 1;
          }
          else
             break;
        }
        for(int i = row; i < b.x.length -1;i++){
          if(b.x[i+1][col].sameKind(b.x[row][col]) == true){
            b.x[i+1][col].setFlag();
            numFlags += 1;
          }
          else
             break;
        }
      } 
    }  
  }
  
  // Provides the score for the suggested (r,c) move. The gems
  // involved in the move may be flagged in the process or may remain
  // unflagged.
  public int scoreMove(int row, int col, Board b){
    if(b.validGemAt(row,col)){
      this.flagConnectedGems(row,col,b);  
    }
    return numFlags * numFlags;
  }
  
  // a human-friendly description of this policy's rules.
  public String description(){
    return "Adjacent gems in whole row/column will be removed"; 
  }

  // return the name of the class
  public String toString(){
   return "WholeRowColRemovalPolicy"; 
  }

  // return the name of the class
  public String saveString(){
   return "WholeRowColRemovalPolicy"; 
  }
}