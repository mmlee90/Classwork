// Represent a 2D maze with a single mouse and multipel cheese
// locations. Positions on the maze can be flagged or unflagged to
// help keep track of search paths.
public class Maze {

  // Construct a Maze from the contents of a file.  On construction,
  // all flags are cleared.  The file format should be similar to the
  // following.
  // 
  // ||||||||
  // |     M|
  // | | ||||
  // | |    |
  // | ||||||
  // |      |
  // |||||| |
  // | ||   |
  // |C|  |C|
  // ||||||||
  public Maze(File f) throws Exception;

  // Construct a maze form the given string. The format of the string
  // identical to the format of the file above.
  public Maze(String s) throws Exception;

  // Return how many rows are in the maze.
  public int getRows();

  // Return how many columns are in the maze.
  public int getCols();

  // Return the initial location of the mouse in the maze as a
  // coordinate (row,col).  Assume there is exactly one mouse. Return
  // null if no mouse is present.
  public Coord getMouseLocation();

  // Return a list of coordinates of all the chesse locations in the
  // maze. If no cheese is present, return an empty list.
  public List<Coord> getCheeseLocations();

  // Return true if the given coordinate is in bounds for the maze and
  // false otherwise.
  public boolean inBounds(Coord c);

  // Return true if the given coordinate is open space for the mouse
  // and false otherwise. The status of the flag (set or clear) at the
  // given location should not affect whether true/false is returned.
  public boolean isSpace(Coord c);

  // Return true if the given location refers to a wall and false
  // otherwise.
  public boolean isWall(Coord c);

  // Return true if the given location refers to the starting location
  // of the mouse and false otherwise.
  public boolean isMouse(Coord c);

  // Return true if the given location refers to cheese and false
  // otherwise.
  public boolean isCheese(Coord c);

  // Return true if the given location has its flag set and false
  // otherwise.
  public boolean isFlagged(Coord c);

  // Set the flag at the given location to true.  
  public void setFlag(Coord c);

  // Set the flag at the given location to false.  
  public void clearFlag(Coord c);

  // Clear all flags in the maze
  public void clearFlags();

  // Starting from the parameter start, flag all positions along the
  // path given by the directions list.
  public void flagPath(List<Direction> path, Coord start);

  // Create a display string for the maze. This is identical to the
  // input format of the maze except that any open spaces which are
  // flagged should display as periods (.) rather than spaces ( ). For
  // example, the following maze has some open spaces in a path
  // flagged which guide the mouse to the cheese.
  // 
  // ||||||||
  // |.....M|
  // |.| ||||
  // |.|    |
  // |.||||||
  // |......|
  // ||||||.|
  // | ||  .|
  // |C|  |C|
  // ||||||||
  public String toString();

}