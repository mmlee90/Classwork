import java.io.*;
import java.util.*;

// Main class to interactively run a path to cheese search. Specify
// the maze fil and the style of search to use on the command line.
// Uncomment lines pertaining to the search styles to enable their
// recognition.
public class MazeRunner{
  public static void main(String args[]) throws Exception{
    if(args.length < 2){
      System.out.println("usage: java MazeRunner <mazefile> <searchtype>");
      System.out.println("  <mazefile>   : text file containing a maze");
      System.out.println("  <searchtype> : 'recursive' or 'iterative'");
      return;
    }
    String mazeFile = args[0];
    String searchStyle = args[1];


    Maze maze = new Maze(new File(mazeFile));
    System.out.println("Maze Loaded: ");
    System.out.println(maze.toString());

    MazeSearch runner;
    // Uncomment the below lines to enable recursive and iterative search

    // if(searchStyle.equals("recursive")){
    //   runner = new RecursiveMazeSearch();
    // }
    // else if(searchStyle.equals("iterative")){
    //   runner = new IterativeMazeSearch();
    // }
    // else{
      System.out.printf("Unsupported search style '%s'\n",searchStyle);
      return;
    // }

    System.out.printf("Beginning search with style '%s'\n",searchStyle);

    List<Direction> path = runner.searchForCheese(maze);
    if(path == null){
      System.out.println("No path found. The mouse stands alone.");
      System.out.println("Maze flags:");
      System.out.println(maze.toString());
      return;
    }
    System.out.println("Path to cheese found. Get that Gouda!");
    System.out.println(path.toString());
    maze.clearFlags();
    maze.flagPath(path, maze.getMouseLocation());
    System.out.println(maze.toString());

    if(runner.isPathToCheese(maze, path)){
      System.out.println("Path verified: happy dining");
    }
    else{
      System.out.println("Rats, the cheese is a lie.");
    }
  }
}
    
