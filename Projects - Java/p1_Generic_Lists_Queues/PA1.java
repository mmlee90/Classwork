/**
 * @author Michelle Lee
 * CS310
 * Fall 2017
 */
 
import java.util.*;

/**
 * 
 */
class PA1
{
 /**
  * Main code to interact with juggler. Will display introductory text and then a string representation of the juggler.
  * User can then select options via the menu to have the juggler perform an action. If user selects an option 
  * that cannot be performed, the juggler will drop all balls and the user can start over. Program does not end
  * until user chooses to quit.
  * 
  * @param args accepts user inputs to select commands for the juggler to perform.
  */
 public static void main(String[] args)
 {
  Juggler stephen = new Juggler();
  System.out.println("\nStephen, the juggler, is learning to do a shower trick...");
  System.out.println("He has " + stephen.getNumUnthrownBalls() + " balls");
  
  Scanner scan = new Scanner(System.in);
  String input = "";
  System.out.println(stephen.toString());
  while(!input.equals("4")){

    doMenu(scan);
    input = scan.next(); 
  
    if(input.equals("1")){
      try{
        stephen.throwBall();
        System.out.println(stephen.toString());
      }
      catch (Exception e){
        stephen = new Juggler();
        System.out.println("Not enough balls!");
        System.out.println("Stephen dropped everything.");
        System.out.println("Stephen wants to try again...\nHe has " + stephen.getNumUnthrownBalls() + " balls");
        System.out.println(stephen.toString());
      }
    }
    else if(input.equals("2")){
      try{
        stephen.passBall();
        System.out.println(stephen.toString());
      }
      catch(Exception e){
        stephen = new Juggler();
        System.out.println("Too many balls!");
        System.out.println("Stephen dropped everything.");
        System.out.println("Stephen wants to try again...\nHe has " + stephen.getNumUnthrownBalls() + " balls");
        System.out.println(stephen.toString());
      }
    }
    else if(input.equals("3")){
      try{
        stephen.catchBall();
        System.out.println(stephen.toString());
      }
      catch(Exception e){
        stephen = new Juggler();
        System.out.println("Too many balls!");
        System.out.println("Stephen dropped everything.");
        System.out.println("Stephen wants to try again...\nHe has " + stephen.getNumUnthrownBalls() + " balls");
        System.out.println(stephen.toString());
      }
    }
    else if(input.equals("4")){
      System.exit(0);
    }
    else{
      System.out.println("Invalid selection!");
    }
  }
 }
 
 /**
  * @param in Accepts a scanner in which to take down user input.
  * @return 0 ends function call
  */
 public static int doMenu(Scanner in)
 {
  System.out.println("\nStephen can:");
  System.out.println("1) Throw a ball into the air");
  System.out.println("2) Pass a ball between hands");
  System.out.println("3) Catch a ball from the air");  
  System.out.println("4) Quit");
  System.out.print("\nWhat should he do? ");
  
  
  return 0;
 }
}