// Basic stack class backed by standard java arrays. Provides push(),
// pop(), top() methods along with list versions of the elements.
// When a push() would exceed the internal capacity of the internal
// array, the array is doubled in size.
// 
// CONSTRAINT: Unless otherwise specified in individual methods, this
// class must implemented without relying on any other classes aside
// from standard arrays and Object.
import java.util.*;
public class AStack<T>{

  // Create a stack with the default capacity 10. The stack expands
  // its internal array when the number of elements pushed() into it
  // would exceed the internal capacity.
  
  private int capacity;
  private int size;
  private Object[] arr;
  private int popped = 0;
  private int pushed = 0;
 
  @SuppressWarnings("unchecked")
  public AStack(){
    capacity = 10;     
    Object[] arr = new Object[capacity];    
    this.capacity = capacity;
    this.arr = arr;
    this.popped = 0;
    this.pushed = 0;
  }

  // Create a stack with the indicated initial capacity.
  public AStack(int n){
   capacity = n;
   Object[] arr = new Object[capacity];   
   this.capacity = capacity;
   this.popped = 0;
   this.pushed = 0;
   this.arr = arr;
  }

  // Get the virtual size of the stack which is how many elements have
  // been pushed into it but not popped.
  public int size(){
    return this.pushed - this.popped; 
  }

  // Get the size of the internal array used by the stack to store
  // elemnts. This number indicates how many elements can be stored in
  // the stack before an expansion must occur.
  public int getCapacity(){
   return this.capacity; 
  }

  // Add a new element to the top of the stack. Expand the internal
  // array if needed.
  
  public void push(T x){
    if(this.size() == 0){
      Object[] newArr = new Object[this.size()+1];
      newArr[0] = (T)x;
      this.arr = newArr;
      this.pushed += 1;
    }
      
    else if(this.size() == this.capacity){
      this.capacity = this.capacity * 2;
      Object[] newArr = new Object[this.size()+1];
      for(int i = 0;i < newArr.length;i++){
       if(i == newArr.length - 1)
         newArr[i] = (T)x;
       else
         newArr[i] = this.arr[i];
      }
      this.arr = newArr;
      this.pushed += 1;
    }
    else{
      Object[] newArr = new Object[this.size()+1];
      for(int i = 0;i < newArr.length;i++){
       if(i == newArr.length - 1)
         newArr[i] = (T)x;
       else
         newArr[i] = this.arr[i];
      }
      this.arr = newArr;
      this.pushed += 1;
    }
    
  }

  // Remove the top element of the stack and return it. If the stack
  // is emmpty, throw a RuntimeException with the message
  // "Stack empty"
  @SuppressWarnings("unchecked")
  public T pop(){
    Object[] newArr = new Object[this.size() - 1];
    for(int i = 0;i < newArr.length;i++){
         newArr[i] = this.arr[i];
      }
    Object popped = this.arr[-1];
    this.arr = newArr;
    this.popped += 1;
    return (T)popped;
   
   
  }

  // Return the top element of the stack .If the stack is emmpty,
  // throw a RuntimeException with the message "Stack empty"
  @SuppressWarnings("unchecked")
  public T top(){
    String msg = "Stack empty";
    RuntimeException e = new RuntimeException(msg);
    if(this.size() == 0)
      throw e;
    else
      return (T)this.arr[-1];
    
  }

  // Pretty print the stack as a string.  The string should reflect
  // the size() of the stack and not include elements of the internal
  // array which are not defined.  While building the string
  // representation you may use the String, StringBuilder, and
  // StringBuffer classes.
  public String toString(){
   int size = this.size();
   StringBuilder stack = new StringBuilder();
   int next;
   stack.append("[");
   for(int i = 0; i < size;i++){
     if(i ==0)
       stack.append(i);
     else{
       stack.append(", ");
       stack.append(i);
     }
   }
   stack.append("]");
   return stack.toString();
  }

  // Return a list of the elements in the stack. The bottom stack item
  // is at index 0 and the top stack item is at index size()-1. This
  // method does not change the stack. For this method only you may
  // use ArrayLists.
  public List<T> toList(){
    ArrayList<T> list = new ArrayList<T>();
    
    return list;
  }

}