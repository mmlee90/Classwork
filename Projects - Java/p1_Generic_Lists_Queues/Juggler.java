/**
 * @author Michelle Lee
 * CS310
 * Fall 2017
 */
import java.util.*;

/**
 * Creates a juggler which will implement the hand, air, and ball classes to juggle.
 */
class Juggler
{
 /**
  * Creates hands for the juggler to move balls. The hands act as a single item list and can only hold one ball at
  * a time.
  */
 private class Hand{
   Ball b = new Ball(0);
   int size = 0;
   
  /**
   * Keeps track of how many items are in the air and what order balls were thrown into the air.
   */
   /**
    * Works like an add() method. When a ball is caught, it gets added or "caught"  by the hand. If the hand already 
    * has a ball, a RuntimeException() is thrown.
    * 
    * @param ball what is caught by the hand
    */

   public void catchBall(Ball ball){
     if(size == 0){
       b = ball;
       ball = null;
       size++;
     }
     else{
       throw new RuntimeException();
     }
   }
   /**
    * Throws unthrown balls into the air. If the juggler's non-catching hand has a ball, throw that ball instead.
    * This works like a remove() method.
    * 
    * @return ball that was removed
    */
   public Ball throwBall(){
     Ball temp = new Ball(0);
     if(!hasBall() && numUnthrownBalls > 0){//no ball in left hand
       temp = new Ball(numUnthrownBalls);
       numUnthrownBalls--;                       
       return temp;
     }
     else if(hasBall() || (hasBall() && numUnthrownBalls == 0)){//there is a ball in left hand
       temp = b;
       Ball a = new Ball(0);
       b = a;
       size = 0;
       return temp;
     }
     else
       throw new RuntimeException();
   }
   
   /**
    * Determines whether or not the juggler's hand is holding a ball.
    * 
    * @return true if there is a ball and false if there is not
    */
   public boolean hasBall(){
     if(size == 0)
       return false;
     else
       return true; 
   }
   
   /**
    * Prints the string representation of the ball in the hand. If there is no ball, three spaces is displayed.
    * 
    * @return string representation of the ball.
    */
   public String toString(){
     if(b.toString().equals("(0)")){
       return "   ";
     } 
     else
       return b.toString();
   }
 }
 /*-------------- DO NOT CHANGE ANYTHING BELOW THIS LINE --------------*/
 
 /**
  * Creates new instance of air which will store balls.
  */
 private Air<Ball> air = new Air<>();
 
 /**
  * Creates a hand array of size 2 to store each of the juggler's hands.
  */
 private Hand[] hands = new Hand[2];
 
 /**
  * Variable to hold the number of unthrown balls. This number will be decremented by 1 each time a ball is thrown.
  */
 private int numUnthrownBalls;
 
 /**
  * Stores the number of total balls.
  */
 private int totalBalls;
 
 /**
  * Creates a new juggler with two hands. totalBalls and numUnthrownBalls are set as 5.
  */
 public Juggler()
 {
  hands[0] = new Hand();
  hands[1] = new Hand();
  this.totalBalls = this.numUnthrownBalls = 5; //((new Random()).nextInt(5))+3;
 }
 
 /**
  * Passes the ball from hands[1] to hands[0]. Hands[0] will catch the ball that is thrown by hands[1].
  */
 public void passBall()
 {
  hands[0].catchBall(hands[1].throwBall());
 }
 
 /**
  * Instructs juggler to throw the ball. If there is no ball in hands[0], and there are still balls unthrown,
  * juggler will throw one of the unthrown balls. If there is a ball in hands[0], juggler will throw that instead.
  */
 public void throwBall()
 {
  if(!hands[0].hasBall() && this.numUnthrownBalls > 0)
   air.add(new Ball(this.numUnthrownBalls--));
  else
   air.add(hands[0].throwBall());
 }
 
 /**
  * Ball that is removed from the air is added or "caught" by hands[1] of the juggler.
  */
 public void catchBall()
 {
  hands[1].catchBall(air.remove());
 }
 
 /**
  * Return the number of balls that the juggler did not yet throw into the air.
  * @return  the number of unthrown balls
  */
 public int getNumUnthrownBalls()
 {
  return this.numUnthrownBalls;
 }
 
 /**
  * Prints the balls in the air, the juggler, and the balls held by the juggler.
  * 
  * @return a string representation of the juggler and the balls
  */
 public String toString()
 {
  String spacing = "";
  if(this.totalBalls < 7) spacing += "   ";
  if(this.totalBalls < 5) spacing += "   ";
  
  return spacing + air.toString() + "\n\n      "
   + hands[0].toString() + "( )" + hands[1].toString() + "\n   "
   + "    \\__|__/\n   "
   + ((this.numUnthrownBalls > 6) ? "(7)" : "   ")
   + ((this.numUnthrownBalls > 5) ? "(6)" : "   ")
   + " |\n   "
   + ((this.numUnthrownBalls > 4) ? "(5)" : "   ")
   + ((this.numUnthrownBalls > 3) ? "(4)" : "   ")
   + " |\n   "
   + ((this.numUnthrownBalls > 2) ? "(3)" : "   ")
   + ((this.numUnthrownBalls > 1) ? "(2)" : "   ")
   + "/ \\\n   " 
   + ((this.numUnthrownBalls > 0) ? "(1)" : "   ")
   + "  /   \\\n";
 }
}