import java.util.*;
/**
 * Represents a single train which has a name. Trains have cars that connect to it. If disconnecting
 * a car from a train, all the cars after that care are disconnected as well. When connecting a car
 * to the train, all cars attached to that car are attached as well.
 */
class Train implements Iterable<Car> {
  private String trainName = "";
  private Car head = null, tail = null;
  
 public Train(String name) {
   this.trainName = name;
 }
 /**
  * Returns the trains name which is set in the constructor.
  * 
  * @return the trains name
  */
 public String getName() {
  //O(1)
  return trainName;
 }
 /**
  * Returns an iterator which traverses the train from the front-most car to the last car. Has
  * next() and hasNext() methods.
  * 
  * @return the iterator of type car
  */
 public Iterator<Car> iterator() {
  //both methods are required to work in O(1) time
   
   return new Iterator<Car>(){
     private Car temp = Train.this.head;
     public Car next(){  
       if(this.temp != null && this.hasNext()){
           Car current = temp;
           temp = temp.getNext();
           return current;
       }
       throw new NoSuchElementException();
     }
   
     public boolean hasNext(){
       if(this.temp != null && this.temp != Train.this.tail.getNext())
         return true;
       else
         return false;
     }
  };
 }
 /**
  * Checks to see if two trains are the same. A train is the same as another train if they have the same name.
  * If two trains have the same name, they will also have the same hash code.
  * 
  * @param o the object we are comparing to
  * @return true or false depending on if the trains are the same or not
  */
 public boolean equals(Object o) {
  //O(1)
  return this.trainName.hashCode() == o.hashCode();
 }
 /**
  * Connects the car to the end of the last car in the train. If no cars are attached, attach the car
  * directly to the tail of the train.
  * 
  * @param c the car that is being connected
  */
 public void connectCar(Car c) {
  //requied Big-O: O(n) where n=the length of the linked list
  //of cars starting at c, NOT n=the number of cars already 
  //connected to this train.
  Car tempH = c;
  Car tempT = tail;
   if(this.head == null && this.tail == null){//no cars connected to train
     c.setPrevious(head);
     tempH = c;
     head = tempH;
     while(tempH.getNext() != null)
       tempH = tempH.getNext();
     this.tail = tempH;//sets train's tail to the last car of the attached car(s)
   }
   else if(tail != null){//where there are connected cars
     tempH.setPrevious(tail);
     tail.setNext(tempH);
     while(tempH.getNext() != null)
       tempH = tempH.getNext();
     this.tail = tempH;
   }
 }
 /**
  * Disconnects the car(s) from indicated car and after. If the indicated car isn't attached to the train, 
  * throw RunTimeException.
  * 
  * @param c the car that is to be disconnected
  * @return the car(s) that were disconnected
  */
 public Car disconnectCar(Car c) {
  //required Big-O: O(n) where n=the number of cars on this train
   for(Car a : this){
       if(a.equals(c)){
         tail = a.getPrevious();
         a.setPrevious(null);
         return a;
       }
   }
   throw new RuntimeException("Cannot disconnect a car that doesn't exist");
 }
  /**
  * Hashes a train into a unique number based on the string representation of that train. If two
  * trains have the same name, they have the same hashcode.
  * 
  * @return hashcode for the train
  */
 public int hashCode() {
  //O(1)
  return trainName.hashCode();
 }
 
 //example test code... edit this as much as you want!
 public static void main(String[] args) {
  Car c1 = new Car("C1");
  Car c2 = new Car("C2");
  Car c5 = new Car("C5");

  c1.setNext(c2);
  c2.setPrevious(c1);
  
  Train t1 = new Train("T1");
  Train t1b = new Train("T1");
  
  if(t1.getName().equals("T1") && t1.equals(t1b) && t1.hashCode() == t1b.hashCode()) {
   System.out.println("Yay 1");
  }
  
  t1.printAscii();
  
  t1.connectCar(c1);
  t1.printAscii();
  
  Car c3 = new Car("C3");
  Car c4 = new Car("C4");
  
  c3.setNext(c4);
  c4.setPrevious(c3);
  
  t1.connectCar(c3);
  t1.printAscii();
 }
 
 /*****************************************************************/
 /****************** DO NOT EDIT BELOW THIS LINE ******************/
 /*****************************************************************/
 
 public String toString() {
  String s = getName();
  for(Car c : this) {
   s += " " + c;
  }
  return s;
 }
 
 public void printAscii() {
  /*
  From: http://www.ascii-art.de/ascii/t/train.txt
      o O___ _________
    _][__|o| |O O O O|
   <_______|-|_______|
    /O-O-O     o   o  
  */
  
  System.out.print(String.format("%-4s",getName())+"o O___");
  for(Car c : this) {
   System.out.print(" _________");
  }
  System.out.println();
  
  System.out.print("  _][__|o|");
  for(Car c : this) {
   System.out.print(" | "+String.format("%-5s",c.getName())+" |");
  }
  System.out.println();
  
  System.out.print(" |_______|");
  for(Car c : this) {
   System.out.print("-|_______|");
  }
  System.out.println();
  
  System.out.print("  /O-O-O  ");
  for(Car c : this) {
   System.out.print("   o   o  ");
  }
  System.out.println();
 }
}