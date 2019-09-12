public class GeneralLE {

  // Public constructor that accepts values for the three constants a,
  // b, and c, as well as the variable x. This is the only public
  // constructor.
  // ax + by = c
  public double a,b,c,x,y;
  public GeneralLE(double a, double b, double c, double x){
   this.a = a;
   this.b = b;
   this.c = c;
   this.x = x;
  }

  // Return the numeric value of the equation which is the numeric
  // quantity that should be on both left and right sides of the equal
  // sign.
  // First solve for y and then solve for c in general equation
  public double value(){
   GeneralLE eq = new GeneralLE(a,b,c,x);
   this.y = (c - (a * x))/b;
   this.c = a * x + b * y;
   return this.c;
  }
  // Return the current value of x
  //if a y value has been set (not default value of 0) recalculate x and return it
  public double getX(){
    if (this.y != 0){
      this.x = (this.c - (this.b * this.y))/this.a;
      return this.x;
    }
    else
      return this.x;
  }

  // Return the current value of y
  public double getY(){
    return (this.c - (this.a * this.x))/this.b;
  }

  // Set the value of x and update other values as appropriate to
  // preserve the equation.
  // set this.x as new inputted x and then recalculate y
  // y = (c-(a*x))/b
  public void setX(double x){
    this.x = x;
    this.y = (this.c - (this.a * this.x))/this.b;
  }

  // Set the value of y and update other values as appropriate to
  // preserve the equation.
  // set this.y as new inputted y and recalculate x
  // x = (c-(b*y))/a
  public void setY(double y){
   this.y = y;
   this.x = (this.c - (this.b * this.y))/this.a;

  }
  // Return a String version of the general form of the equation.  The
  // pretty version's general format should be
  // 
  //   A.AA * x + B.BB * y = C.CC
  // 
  // A.AA, B.BB, and C.CC are the coefficients for the linear equation
  // with 2 decimal digits of accuracy. Examples:
  // 
  //   1.23 * x + 45.68 * y = 2.00
  //   -54.99 * x + -9.86 * y = 42.41
  public String toString(){
   String msg = String.format("%.2f * x + %.2f * y = %.2f",a,b,c);
    return msg; 
    
  }
   
  // Produce a version of this GeneralLE in slope intercept form.  The
  // values of x and y must be preserved but the coefficients should
  // be converted to slope intercept form. The resulting
  // SlopeInterceptLE is not connected to the generating GeneralLE in
  // any way: changes to one do not affect the other.
  //
  // create new arguements m and intercept and solve for them
  // create new SlopeInterceptLE 
  public SlopeInterceptLE toSlopeInterceptLE(){
   double m = (-this.a)/this.b;
   double intercept = this.c/this.b;
   SlopeInterceptLE eq = new SlopeInterceptLE(m,intercept,x);
    return eq;  
  }
}