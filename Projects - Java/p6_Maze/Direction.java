// Simple class to represent the four cardinal directions 
public enum Direction{
  North(-1,0), South(+1,0), East(0,+1), West(0,-1);

  // Arrays of the directions in forward and reverse orders
  public final static Direction [] directions = {North, South, East, West};
  public final static Direction [] revDirections = {West, East, South, North};

  // Coordinate representing the change the direction induces
  private Coord dir;

  // Private constructor
  private Direction(int r, int c){
    this.dir = new Coord(r,c);
  }

  // Return the coordinate change induced by this direction
  public Coord getChange(){
    return this.dir;
  }
}
