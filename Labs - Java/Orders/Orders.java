import java.util.*;
public class Orders{

  // Produce all possible orders of specials with replacement;
  // currentOrder is the current order of specials and maxSize is
  // the maximum length desired. allOrderings accumulates string
  // results as they are found.
  public static void orders(ArrayList<String> specials,
                            ArrayList<String> currentOrder,
                            int maxSize,
                            ArrayList<String> allOrders){
    // If currentOrder contains enough specials, add it to the list of
    // allOrders that have been found
    if(currentOrder.size() == maxSize){
      allOrders.add(currentOrder.toString());
      return;
    }

    // Haven't reached maxSize so add each possible special to the
    // end of allOrders and recurse down to continue the
    // search. Remove the special after finishing the recursive call
    // to replace it with another special.
    for(String special : specials){
      currentOrder.add(special);
      orders(specials, currentOrder, maxSize, allOrders);
      currentOrder.remove( currentOrder.size()-1 );
    }
    return;
  }

  // Produce all possible orders of specials with replacement but
  // ensure that no adjacent specials are identical (no adjacent
  // repeats).
  public static void ordersNoAdj(ArrayList<String> specials,
                                 ArrayList<String> currentOrder,
                                 int maxSize,
                                 ArrayList<String> allOrders){
    // If the currentOrder is full, compare each special with the previous special in the order. 
    // If the special ever equals any special before it return without adding it to allOrders. 
    // add it to allOrders.
    if(currentOrder.size() == maxSize){
      for(int i = 0; i < currentOrder.size() - 1; i++){
        if(currentOrder.get(i).equals(currentOrder.get(i+1)) == false){
          continue;
        }
        else if(currentOrder.get(i).equals(currentOrder.get(i+1)))
          return;
      }
      allOrders.add(currentOrder.toString());
      return;  
    }
    // For each special in specials, if the currentOrder is empty add special otherwise check to see if current special
    // is the same as the last special in currentOrder or not. If it is continue to next special, if it isn't add it to
    // currentOrder
    for (String special : specials){
      if(currentOrder.size() == 0)
        currentOrder.add(special);
      else if(special.equals(currentOrder.get(currentOrder.size()-1)) == false)
        currentOrder.add(special);
      else
        continue;
     ordersNoAdj(specials, currentOrder,maxSize, allOrders);
     currentOrder.remove(currentOrder.size()-1);
      
    }  
    return;
  }

  // Produce all possible orders of specials WITHOUT replacement: each
  // special in an order in allOrders should be unique.
  public static void ordersNoRepeats(ArrayList<String> specials,
                                     ArrayList<String> currentOrder,
                                     int maxSize,
                                     ArrayList<String> allOrders){
    // If the currentOrder is full, compare each order with the previous one. If the special ever equals the special before/next to it
    // return without adding it to allOrders.
    if(currentOrder.size() == maxSize){
      for(int i = 0; i < currentOrder.size(); i++){
        for(int j = 0; j < currentOrder.size();j++){
          if(i == j)
            continue;
          else if(currentOrder.get(i).equals(currentOrder.get(j)))
            return;
        }
      }
      allOrders.add(currentOrder.toString());
      return;  
    }
    // For each special in specials, if the currentOrder is empty add special, otherwise check to see if the currentOrder
    // contains special. if so continue to check the next special in specials, if it isn't already in currentOrder
    // add it to currentOrder.
    for (String special : specials){
      if(currentOrder.size() == 0)
        currentOrder.add(special);
      else{
        if(currentOrder.contains(special))
          continue;
        else
          currentOrder.add(special);
              
      }
      ordersNoRepeats(specials, currentOrder,maxSize, allOrders);
      currentOrder.remove(currentOrder.size()-1);
    }
    
       
    return;
  }

  public static void main(String args[]){
    ArrayList<String> specials = new ArrayList<String>();
    specials.add("10 Coins Off");
    specials.add("Crushed Turtle");
    specials.add("Firey Flower Pasta");
    specials.add("Mushroom Veal");
    specials.add("Stewed Goomba");

    ArrayList<String> currentOrder = new ArrayList<String>();
    ArrayList<String> allOrders = new ArrayList<String>();
    int maxSize = 4;
    orders(specials, currentOrder, maxSize, allOrders);

    System.out.printf("%d orders\n",allOrders.size());
    for(String order : allOrders){
      System.out.println(order);
    }


    System.out.println();

    // Now without adjacent repeats
    allOrders.clear();
    currentOrder.clear();
    ordersNoAdj(specials, currentOrder, maxSize, allOrders);

    System.out.printf("%d orders\n",allOrders.size());
    for(String order : allOrders){
      System.out.println(order);
    }

    System.out.println();

    // Now without any repeats
    allOrders.clear();
    currentOrder.clear();
    ordersNoRepeats(specials, currentOrder, maxSize, allOrders);

    System.out.printf("%d orders\n",allOrders.size());
    for(String order : allOrders){
      System.out.println(order);
    }
    
  }



}
