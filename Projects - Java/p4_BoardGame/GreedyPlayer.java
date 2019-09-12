import java.util.Scanner;
public class GreedyPlayer implements Player{
  // Perform a single move in the given game. May throw a
  // QuitGameException to indicate that no move is desired.
  
  // Loop through board and check what the score would be (for a given policy) if that gem was removed
  // if that score is higher than current high score store that value and its row and col
  //ONLY if score at new gem is higher than current highest score, replace it and store row and col values
  //if currently evaluated gem also equals high score ignore it and continue loop as current high score is at a lower numbered row/col
  public void executeMove(Game game){
    Board board = game.getBoard();
    int highScore = 0;
    int highestScoreRow = 0;
    int highestScoreCol = 0;
    RemovalPolicy p = game.getPolicy();
    for(int i = 0;i < board.x.length; i++){
      for(int in = 0;in < board.x[0].length;in++){   
        if(board.validGemAt(i,in)==true){
            if(p.scoreMove(i,in,board) > highScore){
              highScore = p.scoreMove(i,in,board);
              highestScoreRow = i;
              highestScoreCol = in;
            }
            else if(p.scoreMove(i,in,board) == highScore)
              continue;
          }
        else
          continue;
        }
    }
    game.removeGemAdjustScore(highestScoreRow,highestScoreCol);
  }

  // Return the name of class name of the Player
  public String toString(){
   return "GreedyPlayer"; 
  }
  
  // Return the class name of the Player
  public String saveString(){
   return "GreedyPlayer"; 
  }
}