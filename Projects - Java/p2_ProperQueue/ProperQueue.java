public class ProperQueue{
  private int size;
  private Integer[] elements;
  
  /* initialize elements. if maxCapacity isn't a positive integer create queue with zero capacity
   * if maxCapacity is a positive integer, create queue with that many elements and the populate with -1's
   */
  public ProperQueue(int maxCapacity){
    if (maxCapacity <= 0)
      elements = new Integer[0];
    else if (maxCapacity > 0)
      elements = new Integer[maxCapacity];
    
    for (int i=0; i < elements.length; i++){
      elements[i] = -1;
    }
  }
  
  /* loop through elements to count how many elements are stored (everything except a -1)
   */
  public int getSize(){
    int count = 0;
    for (int i = 0; i < elements.length; i++){
     if (elements[i] != -1)
       count++;
    }
    return count;
  }
  
  // return the capacity (which is also the length)
  public int getCapacity(){
    return elements.length;
  }
  
  /* loop through elements checking to see if there are any empty elements
   * if one is found return false, otherwise return true
   */
  public boolean isFull(){
    for (int i = 0; i < elements.length; i++){
      if (elements[i] == -1)
        return false;
      else
        continue;
    }
    return true;
  }
  
  /* loop through elements checking to see if it is filled with empty elements
   * if an element is found return false, otherwise return true
   */
  public boolean isEmpty(){
    for (int i = 0; i < elements.length; i++){
      if (elements[i] != -1)
        return false;
      else
        continue;
    }
    return true;
  }
  
  /* create and return string representation of queue showing each number seperated by a space
   * create string variable and add each element of queue into it making sure the string does not
   * end in a space
   * if loop doesn't initiate (example: elements is empty queue) empty string is still returned
   */
  public String toString(){
    String queue = "";
    for (int i = 0; i < elements.length; i++){
      if (elements[i] != -1 && i == 0)
        queue = queue + elements[i];
      else if (elements[i] != -1)
        queue = queue + " " + elements[i];
    }
    return queue;
  }
  
  /* attempts to add element e to end of queue
   * if e is null, throw RuntimeException with message "Cannot add null"
   * otherwise loop through elements for the first empty element and replace that with e and return true
   * if no empty element is found throw RuntimeException with message "Queue full"
   */
  public boolean add (Integer e){
    if (e == null){
      RuntimeException n = new RuntimeException("Cannot add null");
      throw n;
    }
    else{
      for (int i = 0; i < elements.length; i++){
        if (elements[i] == -1){
         elements[i] = e;
         return true;
        }
        else
          continue;
      }
    }
    RuntimeException full = new RuntimeException("Queue full");
    throw full;
  } 
  
   /* attempts to add element e to end of queue
   * if e is null, return false
   * otherwise loop through elements for the first empty element and replace that with e and return true
   * if no empty element is found, return false
   */
  public boolean offer(Integer e){
    if (e == null){
      return false;
    }
    else{
      for (int i = 0; i < elements.length; i++){
        if (elements[i] == -1){
         elements[i] = e;
         return true;
        }
      }
    }
    return false;
  }
  
  /* attempts to remove front item in queue and return it
   * check the first element to see if it is empty or not
   * if it is not empty, store that first element and loop through the queue and push up all elements
   * one index up, remembering to make the very last element an empty element. Then return the stored element
   * if first element is empty throw RuntimeException with message "Queue empty"
   */
  public Integer remove(){
    if (elements[0] != -1){
      int removedElement = elements[0];
      for (int i =0; i < elements.length;i++){
        if (i != elements.length -1)
          elements[i]=elements[i+1];
        else
          elements[i] = -1;
      }
        return removedElement;
    }
    RuntimeException empty = new RuntimeException("Queue empty");
    throw empty;
  }
  
  /* attempts to remove front item in queue and return it
   * check the first element to see if it is empty or not
   * if it is not empty, store that first element and loop through the queue and push up all elements
   * one index up, remembering to make the very last element an empty element. Then return the stored element
   * if first element is empty, return null
   */
  public Integer poll(){
    if (elements[0] != -1){
      int removedElement = elements[0];
      for (int i =0; i < elements.length;i++){
        if (i != elements.length -1)
          elements[i]=elements[i+1];
        else
          elements[i] = -1;
      }
      return removedElement;
    }
    else
      return null;
  }
  
  /* check to see if first element of queue is empty
   * if its not empty, return that element
   * if queue is empty throw RuntimeException with message "Queue empty"
   */
  public Integer element(){
    if (elements[0]!=-1)
      return elements[0];
    RuntimeException empty = new RuntimeException("Queue empty");
    throw empty;
  }
  
   /* check to see if first element of queue is empty
   * if its not empty, return that element
   * if queue is empty, return null
   */
  public Integer peek(){
     if (elements[0]!=-1)
      return elements[0];
    return null;
  }
  
}
                      