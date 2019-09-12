/**
 * @author Michelle Lee
 * CS310
 * Fall 2017
 */

/**
 * Creates a Ball object which can be used by the juggler. The ball has a stored integer and can be represented 
 * as a string.
 */
class Ball{
 int num;
 
 /**
 * Takes an inputted number and stores it in a ball.
 * 
 * @param  number  the number that will be stored in the ball
 */
 public Ball(int number){
  this.num = number;
 }
 /**
  * Returns a string representation of the number stored in the ball.
  * 
  * @return a string representation of the ball in the form "(#)"
  */
 public String toString(){
  return "(" + num + ")";
 }
 
 /**
  * Returns the number stored in the ball.
  * 
  * @return the integer stored in the ball
  */
 public int getNumber(){
  return num;
 }
 
}