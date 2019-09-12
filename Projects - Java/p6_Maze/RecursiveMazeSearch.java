// Search a maze for a path from mouse to cheese using recursion.
// CONSTRAINT: You may not use ArrayLists to implement this. Use the
// AStack class instead.  You may import java.util.List to match the
// expected return types.
public class RecursiveMazeSearch extends MazeSearch{

  // Constructor to initialize any state required to search mazes
  public RecursiveMazeSearch();

  // Search for cheese in the given maze. Return a path of Directions
  // from mouse to cheese.  If no such path exists, return
  // null. Employ a recursive helper function to assist with the
  // search.
  public List<Direction> searchForCheese(Maze maze);

}