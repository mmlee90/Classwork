// Fill in the definition for this class
public class SlopeInterceptLE {

  // Public constructor that accepts values for the slope m and
  // intercept b, defaults the value of x to 0.0 and sets y
  // appropriately.
  //
  
  public double m,x,b,y;
  // using the inputted/default values, use them to solve for y
  // if no x argument is given, set it to 0 as default
  public SlopeInterceptLE(double m, double b){
    this.x = 0.0;
    this.m = m;
    this.b = b;
    y = m * x + b; 
  }

  // Public constructor that accepts values for the slope m and
  // intercept b and initial value of x and sets y appropriately.
  //
  // if three variables are defined, use them to solve for y
  public SlopeInterceptLE(double m, double b, double x){
   this.m = m;
   this.b = b;
   this.x = x;
   y = m * x + b;
  }

  // Return the numeric value of the equation which is the numeric
  // quantity that should be on both left and right sides of the equal
  // sign.
  //
  // solve for y and return it
  public double value(){
   SlopeInterceptLE eq = new SlopeInterceptLE(m,x,b);
   y = m * x + b; 
   return y;
  }
  
  // Return the current value of x
  public double getX(){
    return this.x;
  }
  
  // Return the current value of y
  public double getY(){
    return this.y;
  }

  // Set the value of x and change y accordingly to preserve the
  // equation.
  public void setX(double x){
    this.x = x;
    this.y = m * x +b;
  }
  
  // Set the value of $y$ and change $x$ accordingly to preserve the
  // equation.
  public void setY(double y){
    this.y = y;
    this.x = (y - b)/m;
  }

  // Return a =String= version of the general form of the equation.
  // The pretty version's general format should be as follows.
  // 
  //   y = M.MM * x + B.BB
  //
  // M.MM is the slope with 2 digits of accuracy and B.BB is the
  // intercept with two digits of accuracy.  Look for a method of the
  // String class to assist with formatting.
  //

  public String toString(){
    String msg = String.format("y = %.2f * x + %.2f",m,b);
    return msg;
  }
}
