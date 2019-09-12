/**
 * @author Michelle Lee
 * CS310
 * Fall 2017
 */

import java.util.*; //for Queue interface

/**
 * Creates a linked list queue that will be used to hold balls that the juggler will then catch.
 * 
 * For the Queue interface, see http://docs.oracle.com/javase/8/docs/api/java/util/Queue.html
 * and http://docs.oracle.com/javase/tutorial/collections/interfaces/queue.html
 */
class Air<T> implements Queue<T>
{
 /**
  * Wikipedia claims max solo record is 13
  * see http://en.wikipedia.org/wiki/Juggling_world_records#Solo_records
  * Allowing room for growth...
  */
 public static final int MAX_CAPACITY = 15;
 ListItem<T> head;
 ListItem<T> tail;
 int size = 0;
  /**
   * Node to help keep track of what items are in the air and what order balls were thrown into the air.
   */
 private class ListItem<T>
 {
  T data;
  ListItem<T> next;
  
  private ListItem(T data){
    this.data = data;
  }
 }
 
 /**
  * Adds a ball to the linked list queue (air). Balls are added to the end of the list.
  * 
  * @param item what will be added to the linked list queue (air)
  * 
  */
 public boolean add(T item){
   ListItem<T> temp = new ListItem<>(item);
   if(temp == null)
     throw new NullPointerException();
   else if(head == null){
     head = tail = temp;
     size++;
     return true;
   }
   else if(size == MAX_CAPACITY)
     throw new IllegalStateException();
   else{
     tail.next = temp;
     tail = tail.next;  
     size++;
     return true;
   }
 }
 /**
  * Inserts the specified item into the queue and increments the size of the linked list
  * 
  * @param item what will be added into the queue
  * @return true if the item can be added
  */
 public boolean offer(T item){
   if (size < MAX_CAPACITY){
     add(item);
     size++;
     return true;
   }
   else
     return false;
 }
 /**
  * Removes the first item (head) from the linked list queue(air) 
  * 
  * @return the item that was removed
  */
 public T remove(){
   T temp = null;
   if(size == 1){//when there is exactly one ball in air
     temp = head.data;
     head = tail = null;
     size--;
   }
   else if(size > 1){
     temp = head.data;
     head = head.next;
     size--;
   }
   else 
     throw new NullPointerException();
   return temp;
 }
 
 /**
  * Retrieves and removes the head of the queue and return null if the queue is empty.
  * 
  * @return the head of the queue or null if empty
  */
 public T poll(){
   if(head != null){
     size--;
     return this.remove();
   }
   else
     return null;
 }
 
 /**
  * Returns the head of the queue without removing it. If the queue is empty a NoSuchElementException is thrown.
  * 
  * @return head of the queue
  */
 public T element(){
   if(head != null)
     return head.data;
   else
     throw new NoSuchElementException();
 }
 
 /**
  * Returns the head of the queue without removing it. If the queue is empty, null is returned.
  * 
  * @return head of the queue or null if the queue is empty
  */
 public T peek(){
   if(head != null)
     return head.data;
   else
     return null;
 }
 
 /**
  * Returns the string representation of the balls in the air.
  * 
  * @return the string representation of the balls in the air
  */
 public String toString(){
   ListItem<T> temp = null;
   temp = head;
   String s = "";
   while(temp != null){
     s = temp.data.toString() + s;
     temp = temp.next;
   }
   return s;
 }
 
 /**
  * Removes all items from the air.
  */
 public void clear(){
   head = null;
   tail = null;
   size = 0;
 }
 /**
  * Determins if air contains any balls.
  * 
  * @return true if there are no balls in the air
  */
 public boolean isEmpty(){
   if(size == 0)
     return true;
   else
     return false;
 }
 /**
  * Return the number of balls in the air.
  * 
  * @return the number of balls in the air
  */
 public int size(){
   return size;
 }
 
 /**
  * Returns an array with all objects in the air.
  * 
  * @return an array with all objects in the air
  */
 public Object[] toArray(){
   int a = 0;
   Object n[] = new Object[size];
   ListItem<T> temp = null;
   while(a < size){
     temp = head;
     n[a] = temp;
     a++;
     temp = head.next;
   }
   return n;
 }
 
 
 /*-------------- DO NOT CHANGE ANYTHING BELOW THIS LINE --------------*/
 
 /**
  * DO NOT CHANGE THIS, no comment needed
  */
 public boolean addAll(Collection<? extends T> c)  
 {
  throw new UnsupportedOperationException();
 }
 
 /**
  * DO NOT CHANGE THIS, no comment needed
  */
 public boolean contains(Object o)
 {
  throw new UnsupportedOperationException();
 }
 
 /**
  * DO NOT CHANGE THIS, no comment needed
  */
 public boolean containsAll(Collection<?> c)
 {
  throw new UnsupportedOperationException();
 }
 
 /**
  * DO NOT CHANGE THIS, no comment needed
  */
 public boolean equals(Object o)
 {
  throw new UnsupportedOperationException();
 }
 
 /**
  * DO NOT CHANGE THIS, no comment needed
  */
 public int hashCode()
 {
  throw new UnsupportedOperationException();
 }
 
 /**
  * DO NOT CHANGE THIS, no comment needed
  */
 public Iterator<T> iterator()
 {
  throw new UnsupportedOperationException();
 }
 
 /**
  * DO NOT CHANGE THIS, no comment needed
  */
 public boolean remove(Object o)
 {
  throw new UnsupportedOperationException();
 }
 
 /**
  * DO NOT CHANGE THIS, no comment needed
  */
 public boolean removeAll(Collection<?> c)
 {
  throw new UnsupportedOperationException();
 }
 
 /**
  * DO NOT CHANGE THIS, no comment needed
  */
 public boolean retainAll(Collection<?> c)
 {
  throw new UnsupportedOperationException();
 }
 
 /**
  * DO NOT CHANGE THIS, no comment needed
  */
 public <E> E[] toArray(E[] a)
 {
  throw new UnsupportedOperationException();
 }
}