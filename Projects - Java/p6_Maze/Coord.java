// Represents coordinates in a Matrix-like fashion: r is the row
// index, 0 is at the top, c is the column index, 0 is at the left. 
public class Coord {

  // Immutable fields
  public final int row;
  public final int col;

  // Constructor, 
  public Coord(int ir, int ic){
    this.row = ir;
    this.col = ic;
  }

  // Are coordinates equal
  public boolean equals(Object o){
    if(o==null || !(o instanceof Coord)){
      return false;
    }
    Coord c = (Coord) o;
    return this.row==c.row && this.col==c.col;
  }

  // Pretty version of the coord in format (row,col)
  public String toString(){
    return String.format("(%d,%d)",row,col);
  }

  // Add the two coordinates together by row/col and return the result
  // as a new coordinate.
  public static Coord add(Coord x, Coord y){
    return new Coord(x.row+y.row, x.col+y.col);
  }

  // Subtract the two coordinates by row/col and return the result as
  // a new coordinate.
  public static Coord subtract(Coord x, Coord y){
    return new Coord(x.row-y.row, x.col-y.col);
  }

}
