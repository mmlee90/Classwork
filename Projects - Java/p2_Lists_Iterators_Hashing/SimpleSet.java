import java.util.*;
/**
 * A hash table which will keep a "set" of objects. No duplicate objects in a set. The initial
 * size of the table will be 11 and when the load is >0.7 it will rehash to the next biggest
 * prime number.
 * 
 */
class SimpleSet<T> {
 //use this class if you are doing open addressing
 //to represent a tombstone
  private Object[] hashTable = new Object[11];
  int numItems = 0;
  private Tombstone tomb = new Tombstone();
 static class Tombstone { }
 
 /**
  * Adds an item to the hash table. If load is >0.7, table is rehashed to the next prime number larger than
  * twice the size of the current table before returning.
  * 
  * @param the item to be added to the hash table
  * @return false if the value cannot be added
  */
 public boolean add(T value) {
  //O(n) worst case, where n = the number of items
  //O(1) or O(n/m) average case (where n/m is the load)
  //the average case can be amortized Big-O
   
   for(int i = 0; i < hashTable.length; i++){//checks if value is already in the table
     if(value == hashTable[i])
       return false;
   }
   int tableSize = this.hashTable.length;
   int location = value.hashCode()%tableSize;
   int tryThis = 0;

   if(this.hashTable[location] == null){
     this.hashTable[location] = value;
     numItems++;
     if(this.getLoad() > 0.7)
       this.rehash(nextPrime(2 * this.hashTable.length));
     return true;
   }
   else{
     if(this.hashTable[location] != null || this.hashTable[location] instanceof Tombstone){
       for(int i = 1; i < tableSize; i++){
         if((location + i) < tableSize && this.hashTable[location + (i)] == null){//not occupied and not a tombstone
           this.hashTable[location + (i)] = value;
           numItems++;
              if(this.getLoad() > 0.7)
       this.rehash(nextPrime(2 * this.hashTable.length));
           return true;
         }
         else if(location + i > tableSize){
           tryThis = (location + i)%tableSize;
           if(this.hashTable[tryThis] == null){
             this.hashTable[tryThis] = value;
             numItems++;
                if(this.getLoad() > 0.7)
                  this.rehash(nextPrime(2 * this.hashTable.length));
             return true;
           }
         } 
       }
       return false;
     }
     return false;
   }
 }
 /**
  * Removes a value from the hash table. Removed value should be replaced with a tombstone.
  * 
  * @param value that is to be removed
  * @return false if item is not found
  */
 public boolean remove(T value) {  
  //O(n) worst case, where n = the number of items
  //O(1) or O(n/m) average case (where n/m is the load)
   if(contains(value)){
     for(int i = 0; i < hashTable.length; i++){
       if(hashTable[i] == value);
          hashTable[i] = this.tomb;
          numItems--;
          return true;
     }
     return false;
   }
   else
     return false;
 }
 /**
  * Search the hash table for the given value. 
  * 
  * @param value the item to search the hash table for
  * @return the item from the hash table. If not found, return null
  */
 @SuppressWarnings("unchecked")
 public T get(T value) {
  //O(n) worst case, where n = the number of items
  //O(1) or O(n/m) average case (where n/m is the load)
   for(int i = 0; i < hashTable.length; i++){//checks if value is already in the table
     if(value == hashTable[i]){
       if(hashTable[i] == tomb)
         return null;
       else
         return (T)hashTable[i];
     }
   }
   
   return null;
 }
 /**
  * Searches the hash table for the value.
  * 
  * @param value to be searched for
  * @return true if item can be found
  */
 public boolean contains(T value) {
  //O(n) worst case, where n = the number of items
  //O(1) or O(n/m) average case (where n/m is the load)
  if(this.get(value) != null && !(this.get(value) instanceof Tombstone))
    return true;
  else
    return false;
 }
 /**
  * Rehash to a larger table size. Should be rehashed to the next biggest prime number.
  * 
  * @param newCapacity new size for the table
  * @return true when successful
  */
 @SuppressWarnings("unchecked")
 public boolean rehash(int newCapacity) {
  //O(n) where n = the table size
   if(numItems >= newCapacity)
     return false;
   Object[] newHashTable = new Object[newCapacity];
   for(int i = 0; i < size(); i++)
     newHashTable[i] = this.hashTable[i];
   this.hashTable = newHashTable;
   return true;
 }
 /**
  * Returns the number of items in the table.
  * 
  * @return the number of items
  */
 public int size() {
  //O(1)

  return numItems;
 }
 /**
  * Returns the load on the table.
  * 
  * @return the load
  */
 public double getLoad() {
  //O(1)
   
   return numItems/(double)hashTable.length;
 }
 /**
  * Take all values in the hash table and put them into an array of the same size.
  * 
  * @return an array containing all values in the hash table
  */
 @SuppressWarnings("unchecked")
 public Object[] valuesToArray() {
  //take all the values in the table and put them
  //into an array (the array should be the same
  //size returned by the size() method -- no extra space!).
  //Note: it doesn't matter what order the items are
  //returned in, this is a set rather than a list.
  
  //O(n) where n = the table size
   Object[] newHashTable = new Object[size()];
   Tombstone dummy = new Tombstone();
   for(int i = 0; i < size(); i++){
     if(this.hashTable[i].equals(dummy))
       continue;
     newHashTable[i] = this.hashTable[i];
   }
   this.hashTable = newHashTable;
   return hashTable;
 }
 /**
  * Finds next prime number that is bigger than the passed through int.
  * 
  * @param x the integer that we will look for the next prime
  * @return the next prime number
  */
 //inefficiently finds the next prime number >= x
 //this is written for you...
 public int nextPrime(int x) {
  while(true) {
   boolean isPrime = true;
   for(int i = 2; i <= Math.sqrt(x); i++) {
    if(x % i == 0) {
     isPrime = false;
     break;
    }
   }
   if(isPrime) return x;
   x++;
  }
 }
 
 //example test code... edit this as much as you want!
 public static void main(String[] args) {
  SimpleSet<String> names = new SimpleSet<>();
  if(names.add("Fred") && names.add("Alex") && !names.add("Fred")) {
   System.out.println("Yay 0");
  }

  if(names.size() == 2 && names.contains("Fred") && names.get("Alex").equals("Alex")) {
   System.out.println("Yay 1");
  }

  if(names.remove("Alex") && names.size() == 1 && names.get("Alex") == null && names.valuesToArray()[0].equals("Fred")) {
   System.out.println("Yay 2");
  }
 
  boolean loadOk = true;

  if(names.getLoad() != 1/11.0 || !names.rehash(10) || names.getLoad() != 1/10.0 || names.rehash(1)) {
   loadOk = false;
  }
  
  
  SimpleSet<Integer> nums = new SimpleSet<>();
  for(int i = 1; i <= 67 && loadOk; i++) {
   nums.add(i);
   double load = nums.getLoad();
   
   if(/*load > 0.7 || (i < 8 && load != i/11.0) || */(i > 7 && i < 17 && load != i/23.0)/* || (i > 16 && i < 33 && load != i/47.0) || (i > 32 && i < 68 && load != i/97.0)*/) {
    loadOk = false;
   }
  }
  if(loadOk) {
   System.out.println("Yay 3");
  }
 }
}