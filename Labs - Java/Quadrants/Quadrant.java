// An enumeration which models the four quadrants of the 2D Cartesian
// plane.  It has exactly four elements: Q1, Q2, Q3, Q4.  It is likely
// that giving each some fields will make the implementation easier.
// A private constructor is also useful.
public enum Quadrant {   
  Q1("+","+"),
  Q2("-", "+"),
  Q3("-", "-"),
  Q4("+","-");
  private String x;
  private String y;
  private Quadrant(String x,String y){
   this.x = x;
   this.y=y;
  }
  // true if x-coordinates are positive in the quadrant, false otherwise
  public boolean xPositive(){
    if (this.x.equals("+"))
      return true;
    else
      return false;
  }

  // true if y-coordinates are positive in the quadrant, false otherwise
  public boolean yPositive(){
   if (this.y.equals("+"))
     return true;
   else
     return false;
  }

  // Return a String which represents the signs of the coordinates in
  // the Quadrant as
  //   (+,+) for Q1
  //   (-,+) for Q2
  //   (-,-) for Q3
  //   (+,-) for Q4
  public String signPair(){
   if (this.xPositive() == true && this.yPositive() == true)//Q1
     return "(+,+)";
   else if (this.xPositive() == false && this.yPositive() == true)//Q2
     return "(-,+)";
   else if (this.xPositive() == false && this.yPositive() == false) //Q3
     return "(-,-)";
   else //if (this.xPositive() == true && this.yPositive() == false)// Q4
     return "(+,-)";
  }

  // Return the Quadrant that would result from flipping the sign (pos
  // to neg or neg to pos) of the x-coordinate in this Quadrant..
  public Quadrant flipX(){
    if (this.signPair().equals("(+,+)"))
      return Quadrant.Q2;
    else if(this.signPair().equals("(-,+)"))
      return Quadrant.Q1;
    else if(this.signPair().equals("(-,-)"))
      return Quadrant.Q4;
    else //if(this.signPair().equals("(+,-)"))
      return Quadrant.Q3;
  }

  // Given two integers, determine return the quadrant in which they
  // reside. If either x or y is 0, return one of the valid quadrants
  // it might be assigned to (this case is not tested).
  public static Quadrant fromInts(int x, int y){    
    if (x >= 0 && y >= 0)
     return Quadrant.Q1;
   else if (x <= 0 && y >= 0)
     return Quadrant.Q2;
   else if (x <= 0 && y <= 0)
     return Quadrant.Q3;
   else //if (x >= 0 && y <= 0)
     return Quadrant.Q4;
  }

  // Accept an arbitrary number of command line arguments. Adjacent
  // pairs of arguments are treated as (x,y) coordinates.  Print the
  // quadrant in which the pair resides along with the signPair(). If
  // an odd number of arguments is given, ignore the last
  // argument. Any argument that cannot be converted to an integer
  // should raise an exception on encountering it.
  public static void main(String [] args){
    int xC = 0;//x coordinate of pair
    int yC = 0; //y coordinate of pair
    Quadrant q; //make quadrant
    if (args.length%2==0){//if even number of arguements
      for(int i = 0; i < args.length; i++){
        if (i==0)//very first arguement at index 0
          xC = Integer.parseInt(args[i]);
        else if (i == args.length-1){//if i is last index, this is the last pair
          yC = Integer.parseInt(args[i]);
          q = Quadrant.fromInts(xC,yC);
          System.out.println(String.format("(%d,%d) has signs %s and is in %s", xC, yC, q.signPair(), q));
        }
        else if (i%2 != 0)//if index is odd it is the y coordinate
          yC = Integer.parseInt(args[i]);
        else if (i%2 == 0){//this value is start of new pair. print string for old pair then assign current args[i] to xC for next pair 
          q = Quadrant.fromInts(xC,yC);
          System.out.println(String.format("(%d,%d) has signs %s and is in %s", xC, yC, q.signPair(), q));
          xC = Integer.parseInt(args[i]);
        }    
      }
    }
    else if (args.length%2 !=0){//odd number of arguements
      for (int i = 0; i < args.length-1;i++){
        if (i==0)//first arguement
          xC = Integer.parseInt(args[i]);
        else if (i == args.length-2){//last pair
          yC = Integer.parseInt(args[i]);
          q = Quadrant.fromInts(xC,yC);
          System.out.println(String.format("(%d,%d) has signs %s and is in %s", xC, yC, q.signPair(), q));
        }
        else if (i%2 != 0)//odd
          yC = Integer.parseInt(args[i]);
        else if (i%2 == 0){//this value is start of new pair. print string for old pair then assign current args[i] to xC for next
          q = Quadrant.fromInts(xC,yC);
          System.out.println(String.format("(%d,%d) has signs %s and is in %s", xC, yC, q.signPair(), q));
          xC = Integer.parseInt(args[i]);
        }   
      }
    }
  }

}