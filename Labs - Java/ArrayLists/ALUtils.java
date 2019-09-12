import java.util.ArrayList;
public class ALUtils{
  // Creates a copy of the parameter a. Reverses the order of elements
  // in the copy and returns the reversed copy. Assumes a is non-null.
  
  // Create new ArrayList and loop to populate it with items from the given array starting at the end 
  public static <T> ArrayList<T> reverse(ArrayList<T> a){
    ArrayList<T> reversed = new ArrayList<T>();
    for(int i = a.size()-1; i >=0;i--){
     reversed.add(a.get(i));
    }
    return reversed;
  }

  // Creates a copy of the given ArrayList a and rotates the copy to
  // the right by the given shift.  Elements at high indicies wrap
  // around to lower indices.  Assumes parameter a is non-null and
  // that shift is a non-negative number. Returns the rotated copy.
  
  // Loop through given arraylist using the shift and starting from shift distance from the end
  // then go to the beginning and add until you get back to that shift distance from the end
  // If shift is greater then size keep subtracting the size from shift until shift is less than size (to wrap the index around)
  // special cases for an empty ArrayList a or if the shift is 0
  public static <T> ArrayList<T> rotate(ArrayList<T> a, int shift){
    ArrayList<T> rotated = new ArrayList<T>();
    int i = shift;
    if (a.size() == 0)
      return rotated;
    else if(shift == 0)
      return a;
    while (i > a.size()){
        i = i - a.size();
    }
    while(i > 0){      
      rotated.add(a.get(a.size()-i));
      i--;
    }
    i = 0;
    while(a.get(i)!=rotated.get(0)){
     rotated.add(a.get(i));
     i++;
    }
    
    
    
    return rotated;

  }
}
