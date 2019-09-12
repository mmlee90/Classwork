import java.util.*;
import java.io.*;
/**
 * Contains the main method that drives the simulation.
 * 
 */
class PA2 {
 public static void main(String[] args) {
  String trainFile = args[0];
  String peopleFile = args[1];
  
  try {
   Simulator s = new Simulator(new File(trainFile), new File(peopleFile));
   SimpleSet<Person> suspects = s.simulate(args[2], args[3]);
   if(suspects.size() == 0) {
    System.out.println("No suspects!");
   }
   else {
    Object[] people = suspects.valuesToArray();
    System.out.println("Suspects:");
    for(Object p : people) {
     System.out.println(p);
    }
   }
  }
  catch(IOException e) {
   System.out.println("Invalid file");
  }
  catch(RuntimeException e) {
   System.out.println(e.getMessage());
   e.printStackTrace();
  }
 }
}