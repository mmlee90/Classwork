import java.util.Scanner; // Required to get input
import java.io.File;  // Required to get input from files

// A 2D treasure map which stores locations of treasures in an array
// of coordinates
public class TreasureMap{
  // Prompt the user for info on the treasure map and then create it
  public TreasureMap(){
    int numTreasures;
    int tr; //row coordinate for treasure
    int tc; //column coordinate for treasure
    Scanner user_input = new Scanner(System.in);
    System.out.println("Enter map size (2 ints):");
    rows = user_input.nextInt();
    cols = user_input.nextInt();
    System.out.println("Enter number of treasures (1 int)");
    numTreasures = user_input.nextInt();
    treasureLocations = new Coord[numTreasures];
    for(int i = 0; i < numTreasures; i++){
     System.out.println(String.format("Enter treasure %d location (2 ints)", i));
     tr = user_input.nextInt();
     tc = user_input.nextInt();
     treasureLocations[i] = new Coord(tr,tc);
    }
    System.out.println("Treasure map created");
  }

  // Read the string representation of a map from a file
  public TreasureMap(String fileName) throws Exception{
    rows = 0;
    cols = 0;
    int numTreasures = 0;
    int tr = 0;
    int tc = 0;
    Scanner file = new Scanner(fileName);
    while(file.hasNextLine() == true){
      String nextLine = file.nextLine();
      char[] nextLineArray = nextLine.toCharArray();
      rows = rows + 1; //count rows by number of times while loop runs
      for (char x: nextLineArray){//count colums
        cols = 0;//reset 
        if (x != '\n')//don't count \n as a char
          cols = cols + 1; 
      }
      for (char x: nextLineArray){//count treasures
        if (x == 'X')
          numTreasures = numTreasures + 1;
      }
      rows = rows - 1;//bc zero indexing
      cols = cols - 1;
 
    }
    treasureLocations = new Coord[numTreasures];
    while(file.hasNext() == true){
      
      
      
    //}
      String next = file.next();
      int treasuresRemaining = numTreasures;
      if (next == "/n")
        tr = tr + 1;
      
      if (next != "/n")//count how many columns
        tc = tc + 1;
      else if(next == "/n" && file.hasNextLine()==false)//if it is final row retain numCols
        continue;
      else if (next == "/n")//reset numCols after end of each row
        tc = 0;
      
      if (next == "X")
        treasuresRemaining = treasuresRemaining - 1;
        treasureLocations[treasuresRemaining]= new Coord(tr,tc);
      
    }
  }
  
  
  
  int rows, cols;  // How big is the treasure map
  Coord [] treasureLocations; // The locations of treasures

  // true if there is treasure at the given (r,c) coordinates, false
  // otherwise
  // This method does not require modification
  public boolean treasureAt(int r, int c){
    for(int i=0; i<treasureLocations.length; i++){
      Coord coord = treasureLocations[i];
      if(coord.row == r && coord.col == c){
        return true;
      }
    }
    return false;
  }

  // Create a string representation of the treasure map
  // This method does not require modification
  public String toString(){
    String [][] map = new String[this.rows][this.cols];
    for(int i=0; i<rows; i++){
      for(int j=0; j<cols; j++){
        map[i][j] = ".";
      }
    }
    for(int i=0; i<treasureLocations.length; i++){
      Coord c = treasureLocations[i];
      map[c.row][c.col] = "X";
    }
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<rows; i++){
      for(int j=0; j<cols; j++){
 sb.append(map[i][j]);
      }
      sb.append("\n");
    }
    return sb.toString();
  }
    
}
