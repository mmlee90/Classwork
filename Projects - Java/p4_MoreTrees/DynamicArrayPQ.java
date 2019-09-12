//implementing Priority Queue using a Dynamic Array that doubles the capacity everytime the array is full
public class DynamicArrayPQ<T, P extends Comparable<P>> implements PriorityQueue<T,P>
{
  Object[][] d;
  private int size = 1;
  private int fill = 0;
    //constructor
  /**
   * Constructor for a Priority Queue using a Dynamic array 
   * 
   */
  @SuppressWarnings("unchecked")
    public DynamicArrayPQ()
    {
      d = (T[][])(new Object[size][2]);
      this.size = size;
    }

    /**
     * Add the given value using the provided priority. 
     * Doubles the capacity everytime it is full.
     * 
     * @param value to be added
     * @param values priority
     */
  @SuppressWarnings("unchecked")
    public void enqueue(T value, P priority)
    {
      if(this.fill == d.length){
        size *= 2;
        Object[][] d2 = (T[][])(new Object[size][2]);
        for(int i = 0; i < d.length; i++){//copies original array to one that is doubled in size
          d2[i][0] = d[i][0];
          d2[i][1] = d[i][1];
        }
        d = d2;
      }
      int i = 0;
      while(d[i][0] != null && d[i][1] != null){
        i++;
      }
      d[i][0] = (T)value;//not adding value
      d[i][1] = (T)priority;
      fill++;
      /*if(this.fill > 1)
        bubble_sort((T[][])d);*/
    }
  
  /*
   * Sorts all elements in this list in descending order.
   * 
   * @param comparator of type T used to sort the list
   * @param the list of type T that needs to be sorted
   */
  @SuppressWarnings("unchecked")
  private void bubble_sort(T [][] data_array){
      boolean swap;
      T t;//data
      P p;//priority
      P a;
      P b;
      do{  
        swap = false;
        int j = 0;
        for(int i = 1; i < data_array.length; i++){
          a = (P)data_array[j][1];
          b = (P)data_array[i][1];
          if(a == null || a == "null" || b == "null" || b == null)
            swap = false;
          else{
            if(a.compareTo(b) < 0){//nevative a < b
              t = data_array[i][0];
              p = (P)data_array[i][1];
              data_array[i][0] = data_array[j][0];
              data_array[i][1] = data_array[j][1];
              data_array[j][0] = t;
              data_array[j][1] = (T)p;
              swap = true;
              j++;
            }
            else{
              j++; 
            }
          }
        }
      }
      while(swap == true);
  }

    /** 
     * Remove the value with the highest priority(i.e. smallest priority value)
     *
     * @return value that was dequeue'd
     */
    @SuppressWarnings("unchecked")
    public T dequeue()
    {
      int i = 0;
      while(d[i][0] != null && d[i][1] != null){
        i++;
      }
      i--;
      T t = (T)d[i][0];
      d[i][0] = null;
      d[i][1] = null;
      
      return t;
    }

    /** 
     * Return the value of the element with highest priority (i.e. smallest priority value)
     * 
     * @return value with highest priority 
     */
    @SuppressWarnings("unchecked")
    public T peek()
    {
      //int i = this.size() - 1;//int i = 0;
      /*while(d[i][0] != null && d[i][1] != null){
        i--;//i++;
      }*/
      //i--;
      T t = null;
      T a = null;
      T b = null;
      for(int i = 0; i < this.size();i++){
        if(i == 0)
          t = (T)d[i][0];
        else{
          t = (T)d[i][0];
          
        }
          
      }
      return t;
    }

    /** 
     * Return the priority of the element with highest priority (i.e. smallest priority value)
     * 
     *@return highest priority  
     */
    @SuppressWarnings("unchecked")
    public P peekPriority()
    {
      int i = this.size() - 1;//int i = 0;
      /*while(d[i][0] != null && d[i][1] != null){
        i--;//i++;
      }*/
      //i--;
      return (P)d[i][1];
    }

    /**
     * Removes everything in the priority queue
     */
    @SuppressWarnings("unchecked")
    public void clear()
    {
      Object[][] d2 = (T[][])(new Object[size][2]);
      d = d2;
    }

    /** 
     * Merge two priority queues into one and return the merged priority queue
     * 
     * @returns the merged Dynamic Array
     */
    @SuppressWarnings("unchecked")
    public DynamicArrayPQ  merge(DynamicArrayPQ other)
    {
      DynamicArrayPQ n = new DynamicArrayPQ();
      int newSize = this.size() + other.size();
      Object[][] d2 = (T[][])(new Object[newSize][2]);
      for(int i = 0; i < this.size(); i++){
          d2[i][0] = this.d[i][0];
          d2[i][1] = this.d[i][1];
      }
      int i = 0;
      for(int j = i; j < other.size(); j++){
          d2[j][0] = other.d[i][0];
          d2[j][1] = other.d[i][1];
          i++;
      }
      n.size = newSize;
      n.fill = this.fill + other.fill;
      n.d = d2;
      return n;
    }

    /** 
     * Return the size of the given priority queue
     * 
     * @return size of the given priority queue
     */
    public int size()
    {
        return this.size;
    }

    //==================================================================
    // do not modify anything below
    //==================================================================
    //merge two priority queues into one and return the merged priority queue
    public PriorityQueue  merge(PriorityQueue other)
    {
      return merge((DynamicArrayPQ)other);
    }
}
