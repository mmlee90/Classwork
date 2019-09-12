import java.util.*;
/**
 * Creates a person. A person has a name and can keep track of what car they are in. A person can move from one
 * car to another but only if the car is neighboring. 
 */
class Person {
 private String name;
 private Car current;
 
 public Person(String name, Car currentCar) {
   this.name = name;
   current = currentCar;
 }
 /**
  * Returns the person's name which is set in the constructor.
  * 
  * @return the person's name
  */
 public String getName() {
  //O(1)
  return name;
 }
 /**
  * Returns the car that the person is currently in.
  * 
  * @return the current car
  */
 public Car getCurrentCar() {
  //O(1)
  return current;
 }
 /**
  * Tries to move the person from their current car to the car passed in as a parameter. The person can only move if
  * the passed in car is an adjacent car. Returns false if person does not move.
  * 
  * @param c car that the person is trying to move to
  * @return true or false depending on if the person can move
  */
 public boolean moveToCar(Car c) {
  //O(1)
   Car temp = c;
   if(this.current.getNext() != null && this.current.getNext().equals(c)){
     this.current = temp;
     return true;
   }
   else if(this.current.getPrevious() != null && this.current.getPrevious().equals(c)) {
     this.current = temp;
     return true;
   }
   else
     return false;
 }
 /**
  * Checks to see if two people are the same. A person is the same as another person if they have the same name.
  * 
  * @param the object we are comparing to
  * @return true or false depending on if the people are the same or not
  */
 public boolean equals(Object o) {
  //O(1)
  return this.name.equals(o.toString());
 }
  /**
  * Hashes a person into a unique number based on the string representation of that person. Hashcode is the
  * same if two people have the same name.
  * 
  * @return hashcode for the person
  */
 public int hashCode() {
  //O(1)
  return name.hashCode();
 }
 /**
  * Return's the string representation of a person
  * 
  * @return person's name
  */
 public String toString() {
  //O(1)
  return name;
 }
 
 //example test code... edit this as much as you want!
 public static void main(String[] args) {
  Car c1 = new Car("C1");
  Car c2 = new Car("C2");
  Car c3 = new Car("C3");
  
  c1.setNext(c2);
  c2.setPrevious(c1);
  
  Person p1 = new Person("P1", c1);
  
  if(p1.getCurrentCar().equals(c1) && p1.getName().equals("P1")) {
   System.out.println("Yay 1");
  }

  if(p1.moveToCar(c2) && p1.getCurrentCar().equals(c2) && p1.moveToCar(c1) && p1.getCurrentCar().equals(c1)) {
   System.out.println("Yay 2");
  }
  
  Person p1b = new Person("P1", c1);
  if(p1.equals(p1b) && p1.hashCode() == p1b.hashCode()) {
   System.out.println("Yay 3");
  }
 }
}