import java.io.*;
import java.util.*;
import java.awt.*;

/**Map.java - Representation of map layout and interaction*/
public class Map
{
  private char[][] map; //multi-dimensional array containing map elements
  private boolean [][] revealed; //multi-dimensional array containing revealed/unrevealed map elements
  private static Map instance = null;

  /**Initializes multi-dimensional arrays with empty spaces*/
  public Map(){
    map = new char[5][5];
    revealed = new boolean[5][5];
  }
  /**Creates a single copy of a Map instance
  @return dungeon map instance*/
  public static Map getInstance(){
    if( instance == null ){ 
      instance = new Map();
    }
    return instance;
  }

  /**Loads in maps from .txt files 
    @param mapNum level number; transitions to next map*/
  public void loadMap( int mapNum){
    String n = "";
    if (mapNum == 1){
      n = "Map1.txt";
    }else if (mapNum == 2){
      n = "Map2.txt";
    }else if (mapNum == 3){
      n = "Map3.txt";
    }
    try{
      Scanner read = new Scanner( new File( n ));
      int row = 0;
      while( read.hasNext() ){
        int col = 0;
        String line = read.nextLine();
        char[] ch = line.toCharArray();
        for ( int i = 0; i < ch.length; i++ ){
            if ( ch[i] != ' '){
              map[row][col] = ch[i];
              col++;
            }
          }
        row++;
      }
      revealed = new boolean[5][5];
      read.close();
    }
    catch( FileNotFoundException fnf)
    {
      System.out.println("File was not found");
    }
  }
  /**Reveals character at hero's current location 
    @param p hero's current location
    @return map element of current location*/
  public char getCharAtLoc( Point p )
  {
    int x = (int) p.getX(); //column
    int y = (int) p.getY(); //row
    return map[y][x];
  }

  /**Places hero at the start of a new map 
    @return starting location of new map*/
  public Point findStart()
  { 
    Point p = new Point();
    for( int i = 0; i < 5; i++)//row
    { 
      for( int j = 0; j < 5; j++)//column
      { 
        if (map[i][j] == 's')
        {
          p.setLocation( j, i);
          break;
        }
      }
    }
    return p;
  }

  /**Changes revealed map elements to true on separate multi-dimensional array 
    @param p hero's current location*/
  public void reveal( Point p )
  {
    int x = (int) p.getX(); //column
    int y = (int) p.getY(); //row
    revealed[y][x] = true;
  }

  /**Replaces current revealed map element with n (nothing) 
    @param p hero's current location*/
  public void removeCharAtLoc( Point p )
  {
    int x = (int) p.getX(); //column
    int y = (int) p.getY(); //row
    map[y][x] = 'n';
  }

  /**Displays entire map on-screen as a string 
    @param p hero's current location (represented as '*')
    @return string representation of map*/
  public String mapToString( Point p )
  {
    String maps = "";
    for (int i = 0; i < map.length; i ++)
    { 
      for (int j = 0; j < map.length; j++)
      { 
        if ( i == ( int ) p.getY() && j == ( int ) p.getX())
        {
          maps += "* ";
        }
        else if ( revealed[ i ][ j ] )
        {
          maps += String.valueOf( map[ i ][ j ] ) + " ";
        }
        else if ( !revealed[ i ][ j ] )
        {
          maps += "x ";
        }
      }
      maps += "\n";
    }
  return maps;
  }
}
