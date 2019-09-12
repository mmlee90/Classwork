// Search a maze for a path from mouse to cheese. The exact search
// method is left to child classes to implement.
public abstract class MazeSearch {

  // Constructor to initialize any state required to search mazes
  public MazeSearch();

  // Return true if the path given leads the mouse in maze to cheese.
  // Return false otherwise (walking through walls, not ending on
  // cheese). Throw an exception if the path would lead the mouse out
  // of bounds in the maze.
  public boolean isPathToCheese(Maze maze, List<Direction> path);

  // Overriden by children: Search for cheese in the given maze. If no
  // path to any cheese exists, return null.
  public abstract List<Direction> searchForCheese(Maze maze);

}