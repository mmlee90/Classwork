import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class AStackTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("AStackTests");
  }
  
  @Test
  public void astack_constructorSize1(){
    AStack<Integer> as = new AStack<Integer>();
    assertEquals(0, as.size());
  }
  
  @Test
  public void astack_constructorSize2(){
    AStack<Integer> as = new AStack<Integer>(5);
    // capacity is five, but size is still zero.
    assertEquals(0, as.size());
  }
  
  @Test
  public void astack_constructorCapacity1(){
    AStack<Integer> as = new AStack<Integer>();
    assertEquals(10, as.getCapacity());
  }
  
  @Test
  public void astack_constructorCapacity2(){
    AStack<Integer> as = new AStack<Integer>(100);
    assertEquals(100, as.getCapacity());
  }
  
  @Test
  public void astack_constructorCapacity3(){
    AStack<Integer> as = new AStack<Integer>(1);
    assertEquals(1, as.getCapacity());
  }
  
  @Test
  public void astack_constructorCapacity4(){
    AStack<String> as = new AStack<String>(1);
    assertEquals(1, as.getCapacity());
  }
  
  // size
  
  @Test
  public void astack_size1(){
    AStack<Integer> as = new AStack<Integer>();
    as.push(1);
    assertEquals(1, as.size());
  }
  
  @Test
  public void astack_size2(){
    AStack<Integer> as = new AStack<Integer>();
    as.push(1);
    as.push(2);
    as.push(3);
    as.push(4);
    as.push(5);
    assertEquals(5, as.size());
  }
  
  @Test
  public void astack_size3(){
    AStack<Integer> as = new AStack<Integer>();
    as.push(1);
    as.push(2);
    as.push(3);
    as.push(4);
    as.push(5);
    as.pop();
    as.pop();
    assertEquals(3, as.size());
  }
  
  
  // getCapacity - starts at?
  
  @Test
  public void astack_capacity1(){
    AStack<Integer> as = new AStack<Integer>(3);
    as.push(1);
    as.push(2);
    as.push(3); // completely filled
    
    as.push(4); // triggers a doubling in capacity
    assertEquals(6, as.getCapacity());
  }
  
  
  // check capacity each time we add something.
  @Test
  public void astack_capacity2(){
    AStack<Integer> as = new AStack<Integer>(1);
    as.push(1);
    assertEquals(1,as.getCapacity());
    // triggers capacity doubling
    as.push(2);
    assertEquals(2, as.getCapacity());
    
    // triggers capacity doubling
    as.push(3);
    assertEquals(4, as.getCapacity());
    as.push(4);
    assertEquals(4, as.getCapacity());
    
    // triggers capacity doubling
    as.push(5);
    assertEquals(8, as.getCapacity());
    as.push(6);
    assertEquals(8, as.getCapacity());
    as.push(7);
    assertEquals(8, as.getCapacity());
    as.push(8);
    assertEquals(8, as.getCapacity());
    
    // triggers capacity doubling
    as.push(9);
    assertEquals(16, as.getCapacity());
  }
  
  @Test
  public void astack_capacity3(){
    AStack<Integer> as = new AStack<Integer>(5);
    as.push(1);
    as.push(2);
    as.push(3);
    as.push(4);
    as.push(5);
    as.push(6);
    assertEquals(10, as.getCapacity());
    
    // when we pop things, we don't try to reduce the capacity.
    as.pop();
    as.pop();
    as.pop();
    assertEquals(10, as.getCapacity()); // should still be ten.
  }
  
  // pop
  
  
  @Test
  public void astack_pop1(){
    AStack<Integer> as = new AStack<Integer>();
    try{
      as.pop();
      fail("shouldn't survive popping from an empty stack, and your code did.");
    }
    catch(RuntimeException e){
     assertEquals("Stack empty",e.getMessage()); 
    }
  }  
  
  @Test
  public void astack_pop2(){
    AStack<Integer> as = new AStack<Integer>();
      as.push(5);
      as.push(6);
      as.pop();
      as.pop();
    try{
      as.pop();
      fail("shouldn't survive popping from an empty stack, and your code did.");
    }
    catch(RuntimeException e){
     assertEquals("Stack empty",e.getMessage()); 
    }
  }  
  
  // top errors
  
  
  @Test
  public void astack_top1(){
    AStack<Integer> as = new AStack<Integer>();
    try{
      as.top();
      fail("shouldn't survive topping from an empty stack, and your code did.");
    }
    catch(RuntimeException e){
     assertEquals("Stack empty",e.getMessage()); 
    }
  }  
  
  @Test
  public void astack_top2(){
    AStack<Integer> as = new AStack<Integer>();
      as.push(5);
      as.push(6);
      
      // none of these should have any effect on your stack.
      as.top();
      as.top();
      as.top();
      as.top();
      as.top();
      as.top();
      
      // but popping does.
      as.pop();
      as.pop();
    try{
      as.top();
      fail("shouldn't survive popping from an empty stack, and your code did.");
    }
    catch(RuntimeException e){
     assertEquals("Stack empty",e.getMessage()); 
    }
  }  
  
  @Test
  public void astack_multi1(){
    AStack<Integer> as = new AStack<Integer>();
    as.push(4);
    int i = as.pop();
    assertEquals(4, i);
  }
  
  @Test
  public void astack_multi2(){
    AStack<Integer> as = new AStack<Integer>();
    as.push(1);
    as.push(2);
    as.push(3);
    int i = as.pop();
    assertEquals(3, i);
    i = as.pop();
    assertEquals(2,i);
    i = as.pop();
    assertEquals(1,i);
  }
  @Test
  public void astack_multi3(){
    AStack<Integer> as = new AStack<Integer>();
    as.push(6);
    as.push(7);
    as.push(8);
    int i = as.pop();
    assertEquals(8, i);
    i = as.pop();
    assertEquals(7,i);
    as.push(9);
    as.push(10);
    i = as.pop();
    assertEquals(10,i);
    i = as.pop();
    assertEquals(9,i);
    
    // there should still be a six left on the stack.
    i = as.pop();
    assertEquals(6,i);
  }
  
  @Test
  public void astack_multi4(){
    AStack<String> as = new AStack<>();
    as.push("pancake");
    as.push("middle");
    as.push("sekacnap");
    String s = as.top();
    assertEquals("sekacnap",s);
    s = as.top();
    assertEquals("sekacnap",s);
    s = as.pop();
    assertEquals("sekacnap",s);
    s = as.pop();
    assertEquals("middle",s);
  }

  @Test
  public void astack_multi5(){
    AStack<Integer> as = new AStack<>();
    as.push(3);
    as.push(null);
    as.push(null);
    as.push(5);
    assertEquals(4,as.size());
    assertEquals(10,as.getCapacity());
    int i = as.pop();
    assertEquals(5, i);
    Integer I = as.pop();
    assertEquals(null, I);
    I = as.pop();
    assertEquals(null, I);
    I = as.pop();
    assertEquals((Integer)3, I); // note this one time we need a cast - I is already an Integer, so we upgrade 3 to ((Integer)3).
    
  }
  
  // toString
  @Test
  public void astack_toString1(){
   AStack<Integer> as = new AStack<>();
   assertEquals("[]",as.toString());
  }
  
  @Test
  public void astack_toString2(){
   AStack<Integer> as = new AStack<>();
   as.push(21);
   assertEquals("[21]",as.toString());
  }
  
  @Test
  public void astack_toString3(){
   AStack<Integer> as = new AStack<>();
   as.push(21);
   as.push(24);
   assertEquals("[21, 24]",as.toString());
  }
  
  @Test
  public void astack_toString4(){
   AStack<Boolean> as = new AStack<>();
   as.push(true);
   as.push(true);
   as.push(false);
   as.push(true);
   as.push(false);
   as.push(false);
   assertEquals("[true, true, false, true, false, false]", as.toString());
  }
  
  @Test
  public void astack_toString5(){
   AStack<String> as = new AStack<>();
   as.push("hello");
   as.push("stack");
   as.push("world!");
   
   assertEquals("[hello, stack, world!]",as.toString());
  }
  
  @Test
  public void astack_toString6(){
   AStack<Coord> as = new AStack<>();
   assertEquals("[]",as.toString());
   as.push(new Coord(1,2));
   assertEquals("[(1,2)]",as.toString());
   as.push(new Coord(3,4));
   assertEquals("[(1,2), (3,4)]",as.toString());
   as.push(new Coord(5,6));
   assertEquals("[(1,2), (3,4), (5,6)]",as.toString());
  }
  
  // toList
  @Test
  public void astack_toList1(){
    AStack<Integer> as = new AStack<>();
    List<Integer> ans = Arrays.asList();
    assertEquals(ans,as.toList());
  }
  
  @Test
  public void astack_toList2(){
    AStack<Integer> as = new AStack<>();
    as.push(1);
    List<Integer> ans = Arrays.asList(1);
    assertEquals(ans,as.toList());
  }
  
  @Test
  public void astack_toList3(){
    AStack<Integer> as = new AStack<>();
    as.push(1);
    as.push(2);
    List<Integer> ans = Arrays.asList(1,2);
    assertEquals(ans,as.toList());
  }
  
  @Test
  public void astack_toList4(){
    AStack<Integer> as = new AStack<>();
    as.push(1);
    as.push(1);
    as.push(2);
    as.push(3);
    as.push(5);
    as.push(8);
    List<Integer> ans = Arrays.asList(1,1,2,3,5,8);
    assertEquals(ans,as.toList());
  }
  
  @Test
  public void astack_toList5(){
    AStack<Integer> as = new AStack<>();
    as.push(1);
    as.push(1);
    as.push(2);
    as.push(3);
    as.push(5);
    as.push(8);
    as.pop();
    as.pop();
    as.push(7);
    as.push(11);
    as.push(13);
    List<Integer> ans = Arrays.asList(1,1,2,3,7,11,13);
    assertEquals(ans,as.toList());
  }
  
  @Test
  public void astack_toList6(){
    AStack<String> as = new AStack<>();
    as.push("A");
    as.push("S");
    as.push("T");
    as.push("A");
    as.push("C");
    as.push("K");
    List<String> ans = Arrays.asList("A","S","T","A","C","K");
    assertEquals(ans,as.toList());
  }
  
  
}
