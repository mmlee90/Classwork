import java.util.*;
import java.io.*;

/**
 *  This class runs a simulation of the trains and
 *  the people on the trains.
 */
class Simulator {
 
 /**
  *  Finds the suspects for the murder. You may assume this method is only
  *  ever called after the entire simulation has run (so people and their
  *  trains are in the final positions).
  *  
  *  @param a String with the name of the car the murder takes place at.
  *  @returns a set of potential murderers.
  */
 public SimpleSet<Person> getMurderSuspects(String murderLocation) {
  /*
  Your Code Here...
  Remember: you have access to all the results of the simulation below.
  
  You can use the maps of car names to car objects,
  the map of train names to train objects, etc.
  
  If you get ahold of a person object, then that person's
  getCurrentCar() method will return the car they are in
  at the time of the murder...
  */
   
   
  return null;
 }
 
 
 /*****************************************************************/
 /****************** DO NOT EDIT BELOW THIS LINE ******************/
 /*****************************************************************/
 
 /**
  *  The scanner to the file containing the train actions
  */
 private Scanner trainScanner;
 
 /**
  *  The scanner to the file containing the person actions
  */
 private Scanner peopleScanner;
 
 /**
  *  A map between car names and car objects
  */
 private SimpleMap<String,Car> cars = new SimpleMap<>();
 
 /**
  *  A map between train names and train objects
  */
 private SimpleMap<String,Train> trains = new SimpleMap<>();
 
 /**
  *  A map between person names and person objects
  */
 private SimpleMap<String,Person> people = new SimpleMap<>();
 
 /**
  *  A set of cars that have been decoupled
  */
 private SimpleSet<Car> decoupledCars = new SimpleSet<>();
 
 /**
  *  Initialize the simulator to use specific files for the
  *  simulation.
  *  
  *  @param trainFile the file containing the train movements
  *  @param peopleFile the file containing the person movements
  *  @throws IOException if openning a scanner fails
  */
 public Simulator(File trainFile, File peopleFile) throws IOException {
  //open scanners to the files
  this.trainScanner = new Scanner(trainFile);
  this.peopleScanner = new Scanner(peopleFile);
 }
 
 /**
  *  Runs the entire simulation start to finish.
  *  
  *  @throws IOException if reading from the files fails
  *  @throws RuntimeException if anything used by this class throws a runtime exception
  */
 public SimpleSet<Person> simulate(String murderLocation, String murderTimeStr) throws IOException {
  int nextTrainTime = 0;
  int nextPersonTime = 0;
  
  String[] parts = murderTimeStr.split(":");
  int murderTime = (Integer.parseInt(parts[0])*60)+Integer.parseInt(parts[1]);

  //start the trains and people at their locations
  setupInitialTrainPositions(trainScanner);
  setupInitialPeoplePositions(peopleScanner);
  
  //get a list of trains and people for printing later
  Object[] trainArray = trains.valuesToArray();
  Object[] peopleArray = people.valuesToArray();
  
  //both scanners are now past the intialization sections
  //so get the next time trains (and people) need to perform
  //some action
  nextTrainTime = getNextTime(trainScanner); //time in seconds since midnight
  nextPersonTime = getNextTime(peopleScanner); //time in seconds since midnight
  
  //prints out the world for easy ASCII viewing
  printWorld(0, trainArray, peopleArray, decoupledCars.valuesToArray());
  
  //pause for person
  Scanner input = new Scanner(System.in);
  System.out.println("Hit enter to continue");
  input.nextLine();
  
  //the scanners have read the times for both files
  while(nextTrainTime != -1 || nextPersonTime != -1) {
   if(nextTrainTime > murderTime && nextPersonTime > murderTime) {
    break;
   }
   
   if(nextTrainTime != -1 && (nextTrainTime <= nextPersonTime || nextPersonTime == -1)) {
    //do action
    doNextTrainAction(nextTrainTime);
    
    //print world
    printWorld(nextTrainTime, trainArray, peopleArray, decoupledCars.valuesToArray());
    
    //parse time
    nextTrainTime = getNextTime(trainScanner);
   }
   else if(nextPersonTime != -1 && (nextPersonTime < nextTrainTime || nextTrainTime == -1)) {
    //do action
    doNextPersonAction(nextPersonTime);
    
    //print world
    printWorld(nextPersonTime, trainArray, peopleArray, decoupledCars.valuesToArray());
    
    //parse time
    nextPersonTime = getNextTime(peopleScanner);
   }
   
   System.out.println("Hit enter to continue");
   input.nextLine();
  }
  //close the streams
  trainScanner.close();
  peopleScanner.close();
  
  return getMurderSuspects(murderLocation);
 }
 
 /**
  *  Prints the world in beautiful ASCII art...
  *  
  *  @param time the time the world is at
  *  @param trainArray the list of trains being simulated
  *  @param peopleArray the list of people being simulated
  *  @param decoupledCarsArray the list of cars currently decoupled
  */
 public void printWorld(int time, Object[] trainArray, Object[] peopleArray, Object[] decoupledCarsArray) {
  int hour = time/60;
  int minute = time-(hour*60);
  
  System.out.printf("%02d:%02d\n", hour, minute);
  System.out.println();
  
  System.out.println("Trains:\n");
  for(int i = 0; i < trainArray.length; i++) {
   ((Train)trainArray[i]).printAscii();
   System.out.println();
  }
  System.out.println();
  
  System.out.println("Decoupled Cars:\n");
  for(int i = 0; i < decoupledCarsArray.length; i++) {
   ((Car)decoupledCarsArray[i]).printAscii();
   System.out.println();
  }
  System.out.println("\n");
  
  System.out.println("People:");
  for(int i = 0; i < peopleArray.length; i++) {
   System.out.println(peopleArray[i] + " on car " + ((Person)peopleArray[i]).getCurrentCar());
  }
  System.out.println();
  
 }
 
 /**
  *  Reads in the next train actions from the scanner given.
  *  
  *  @param time the current time (for use in error messages)
  */
 public void doNextTrainAction(int time) {
  while(trainScanner.hasNextLine()) {
   String line = trainScanner.nextLine();
   if(line.equals("")) {
    //we're done reading the train actions
    return;
   }
   
   String[] parts = line.split(" ");
   
   Train t = trains.getValue(parts[0]);
   //System.out.println(parts[0] + "|" + parts[1] + "|" + parts[2]);
   Car c = cars.getValue(parts[2]);
   //System.out.println("Train " + t + " car " + c);
   
   if(parts[1].equals("connect")) {
    if(!decoupledCars.contains(c)) {
     int hour = time/60;
     int minute = time-(hour*60);
     throw new RuntimeException("[" + hour + ":" + minute + "] Car " + c + " is not in the decoupledCars list");
    }
    
    t.connectCar(c);
    decoupledCars.remove(c);
   }
   else {
    t.disconnectCar(c);
    decoupledCars.add(c);
   }
  }
 }
 
 /**
  *  Reads in the next person actions from the scanner given.
  *  
  *  @param time the current time (for use in error messages)
  */
 public void doNextPersonAction(int time) {
  while(peopleScanner.hasNextLine()) {
   String line = peopleScanner.nextLine();
   if(line.equals("")) {
    //we're done reading the people actions
    return;
   }
   
   String[] parts = line.split(" ");
   
   Person p = people.getValue(parts[0]);
   Car c1 = cars.getValue(parts[1]);
   Car c2 = cars.getValue(parts[2]);
   
   if(!p.getCurrentCar().equals(c1)) {
    int hour = time/60;
    int minute = time-(hour*60);
    throw new RuntimeException("[" + hour + ":" + minute + "] Person " + p + " cannot be in car " + c1 + " according to the record");
   }
   
   if(!p.moveToCar(c2)) {
    int hour = time/60;
    int minute = time-(hour*60);
    throw new RuntimeException("[" + hour + ":" + minute + "] Person " + p + " cannot move to car " + c1 + " according to the record");
   }
  }
 }
 
 /**
  *  Skips through the file until a time is found and returns that time
  *  in seconds since midnight.
  *  
  *  @param s the scanner to read from
  *  @return the next time in seconds since midnight
  */
 public int getNextTime(Scanner s) {
  while(s.hasNextLine()) {
   String line = s.nextLine();
   if(line.matches("\\d\\d:\\d\\d")) {
    String[] parts = line.split(":");
    return (Integer.parseInt(parts[0])*60)+Integer.parseInt(parts[1]);
   }
  }
  return -1;
 }
 
 /**
  *  Reads in and sets up the initial train positions.
  *  
  *  @param trainScanner the scanner to read from
  */
 public void setupInitialTrainPositions(Scanner trainScanner) {
  //get initial train positions
  while(trainScanner.hasNextLine()) {
   //get a line and break it into parts
   String s = trainScanner.nextLine();
   if(s.length() == 0) {
    //if the line is blank, we're done reading the train's initial positions
    return;
   }
   String[] parts = s.split(" ");
   
   //create a new train
   String trainName = parts[0];
   Train t = new Train(trainName);
   trains.add(trainName, t); //add to the list of all cars
   
   //create cars and add them to the trains
   for(int i = 1; i < parts.length; i++) {
    String carName = parts[i];
    Car c = new Car(carName);
    cars.add(carName, c); //add to the list of all cars
    
    //add the car to the train
    t.connectCar(c);
    //System.out.println(c);
    //System.out.println(t);
   }
  }
 }
 
 /**
  *  Reads in and sets up the initial people positions.
  *  
  *  @param peopleScanner the scanner to read from
  */
 public void setupInitialPeoplePositions(Scanner peopleScanner) {
  //get initial people positions
  while(peopleScanner.hasNextLine()) {
   //get a line and break it into parts
   String s = peopleScanner.nextLine();
   if(s.length() == 0) {
    //if the line is blank, we're done reading the people's initial positions
    return;
   }
   String[] parts = s.split(" ");
   
   //get the name of the person
   String personName = parts[0];
   
   //get the car they start on
   String carName = parts[1];
   Car c = cars.getValue(carName);
   
   //create a new person
   Person p = new Person(personName, c);
   people.add(personName, p); //add to the list of all people
  }
 }
}