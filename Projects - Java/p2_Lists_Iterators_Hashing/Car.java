import java.util.*;
/**
 * Creates a single car which can be attached to other cars and the train. The train has a name and
 * cars can connect to other cars from the front or back.
 */
class Car {
  private Car next, previous;
  private String carName;

 public Car(String name) {
   carName = name;
   next = null;
   previous = null;
 }
 /**
  * Returns the next car.
  * 
  * @return the next car
  */
 public Car getNext() {
  //O(1)

  return next;
 }
 /**
  * Returns the car in front of this car.
  * 
  * @return the previous car
  */
 public Car getPrevious() {
  //O(1)
  return previous;
 }
 /**
  * Connects a car to the back of the passed car. The new car's head must be linked to the end of the car.
  * Do nothing if there is no car to set a next car to (current car is null)
  * 
  * @param the car to be added to the back
  */
 public void setNext(Car next) {
  //O(1)
   if(this.next == null){
     this.next = next;
     
   }
 }
 /**
  * Connects a car to the front of the passed car. The new car's tail must be linked to the head of the 
  * passed car.
  * 
  * @param the car to be added to the front
  */
 public void setPrevious(Car previous) {
  //O(1)
   if(this.previous == null){
     this.previous = previous;
     
   }
 }
 /**
  * Returns the name of the car.
  * 
  * @return the name of the car
  */
 public String getName() {
  //O(1)
  return carName;
 }
 /**
  * Checks to see if two cars are the same. A car is the same as another car if they have the same name.
  * 
  * @param the object that we are comparing to
  * @return true or false depending on if the cars are the same
  */
 public boolean equals(Object o) {
  //O(1)
   return carName.equals(o.toString());
 }
 /**
  * Hashes a car into a unique number based on the string representation of a car. If two objects are 
  * equal they return the same hashcode.
  * 
  * @return hashcode for the car
  */
 public int hashCode() {
  //O(1)
   
   return carName.hashCode();
 }
 /**
  * Return's the string representation of a car
  * 
  * @return car's name
  */
 public String toString() {
  //O(1)
  return carName;
 }
 
 //example test code... edit this as much as you want!
 public static void main(String[] args) {
  Car c1 = new Car("C1");
  Car c2 = new Car("C2");

  
  c1.setNext(c2);//c1 c2
  c2.setPrevious(c1);


  if(c1.getName().equals("C1")) {
   System.out.println("Yay 1");
  }

  if(c1.getNext().equals(c2) && c2.getPrevious().equals(c1)) {
   System.out.println("Yay 2");
  }
  
  Car c1b = new Car("C1");
  if(c1.equals(c1b) && c1.hashCode() == c1b.hashCode()) {
   System.out.println("Yay 3");
  }

  c1.printAscii();
 }
 
 /*****************************************************************/
 /****************** DO NOT EDIT BELOW THIS LINE ******************/
 /*****************************************************************/
 
 public void printAscii() {
  /*
  From: http://www.ascii-art.de/ascii/t/train.txt
   _________
   |O O O O|
  -|_______|
     o   o  
  */
  
  Car current = this;
  while(current != null) {
   System.out.print(" _________");
   current = current.getNext();
  }
  System.out.println();
  
  current = this;
  while(current != null) {
   System.out.print(" | "+String.format("%-5s",current.getName())+" |");
   current = current.getNext();
  }
  System.out.println();
  
  current = this;
  while(current != null) {
   System.out.print("-|_______|");
   current = current.getNext();
  }
  System.out.println();
  
  current = this;
  while(current != null) {
   System.out.print("   o   o  ");
   current = current.getNext();
  }
  System.out.println();
 }
}