// Implements operations for an int queue.  Queues are stored in an
// array with empty cells of the array containing -1.  Queues add
// elements the end and remove elements from the front. Each operation
// must deal with the fact that the queue does not carry the number
// elements it currently contains.  The suggested way to do this is to
// keep all elements on the "left" of the array near index 0. 
public class ShiftQueue{

  // Create an empty integer queue with the given maximum capacity.
  // Allocate an array of the given size and fill it with the empty
  // element which is the integer -1. If capacity is negative, return
  // null.
  /* if capacity is zero, return empty array, if negative capacity is given return null
   * if capacity is neither zero or null, create array with capacity elements
   * then loop through the array and change each element to -1
   */
  public static int [] makeQueue(int capacity){
    if (capacity ==0){
      int[]queue = {};
      return queue;
    }
    else if (capacity < 0){
     return null;
    }
    else{
      int[]queue = new int[capacity];
      for(int i = 0; i <= capacity-1; i++){ 
       queue[i] = -1;
      }
    return queue;
    }
  }
  // Add newElement to the end of queue.  Use a loop to search for the
  // first occurrence of an empty element (-1) put newItem there.
  // After a succesul add return true.  If there are no empty slots or
  // newItem is -1, return false. 
  /*array must have at least one element and newItem cannot be -1
   * if there is no elements in queue, cannot add element to nothing
   * as long as the queue has at least one empty element perform loop to check for first empty slot
   * replace that empty slot with newItem and return true
   * if last iteration is reached and it still is not an empty element, return false 
   */
  public static boolean addToEndOfQueue(int [] queue, int newItem){
   if (queue.length == 0 || newItem == -1)
     return false;
   if (queue.length > 0){
    for (int i = 0; i <= queue.length -1; i++){
     if (queue[i] == -1){
      queue[i] = newItem;
      return true;
     }
     else if (i==queue.length-1 && queue[i] != -1)
       return false;
     else if (queue[i] != -1)
       continue;
     }
    }
   return false;
   }
  

  // Remove an element from the front of the queue which is index
  // 0. Shift all existing elements to the left by one index.  The new
  // front of the queue should be at index 0 after this shift. Ensure
  // that all empty slots have the value -1 in them. On successfully
  // removing an element, return true. If queue is empty (length 0 or
  // all elements are -1), return false.
  /*
   * if queue has length of more than 0 and isn't filled with -1 
   * loop through the queue for all values up until the last index and replace item at index with number at index i+ 1
   * for element at the last slot of the queue, replace it with -1 
   */
  
  
  public static boolean removeFromFrontOfQueue(int [] queue){
   if (queue.length == 0)
     return false;
   else if (queue.length >0){
       if (queue [0]== -1)
         return false;
       else if (queue[0] !=-1)
         for(int i =0; i<=queue.length -1; i++){
          if (i < queue.length -1)
            queue[i] = queue [i+1];
          else if (i == queue.length-1)
            queue[i] = -1;
         }
     }  
     return true;
  }



  // Produce a string representing the queue.  This string should not
  // contain any -1 elements: only the integers that have been
  // added. Each element should be separated by a space with an
  // additional space at the end after the last element.  Sample
  // formats are as follows.
  // 
  /* check to make sure that queue is not empty and that first element is not -1
   * if queue passes those checks create an empty string
   * loop through queue and add each element to the string if it is not equal to -1
   * any time loop comes across a -1, add a single space to the string and immediately exit/return the string
   */
  
  public static String queueString(int [] queue){
    if (queue.length ==0)
      return "";
    else if (queue[0]==-1)
      return "";
    String q = "";
    
    for (int i = 0; i <= queue.length -1; i++){
       if (queue[i]!=-1)
        q += queue[i] + " ";    
       else if (queue[i]==-1)
        return q;      
      }
    return q;
  }
}

