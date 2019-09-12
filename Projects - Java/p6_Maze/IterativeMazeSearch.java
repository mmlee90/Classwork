// Search a maze for a path from mouse to cheese WITHOUT recursion.
// CONSTRAINT: You may not use ArrayLists to implement this. Use the
// AStack class instead.  You may import java.util.List to match the
// expected return types.
public class IterativeMazeSearch extends MazeSearch{

  // Constructor to initialize any state required to search mazes
  public IterativeMazeSearch();

  // Search for cheese in the given maze. Return a path of Directions
  // from mouse to cheese.  If no such path exists, return
  // null. CONSTRAINT: You may not use recursive calls or recursive
  // helper methods.
  public List<Direction> searchForCheese(Maze maze);

}