import java.util.*;
import java.awt.Point;

/**Hero.java - Representation of the player*/
public class Hero extends Entity implements Magical{
  //***Not needed for Project 2** private Map map = new Map(); //textual representation of map layout
  private Point loc; //textual representation of current location on map
  private int level;  //representation of map level
  private int gold; //Represnts gold the player has
  private int key; //Represents the keys the player has 
  private Map map;

  /**Initializes hero's starting location
  @param n Hero's name*/
  public Hero (String n, Map dm){
    super( n, 25 );
    map = dm;
    dm.loadMap(1);
    level = 1;
    gold = 0;
    key = 0;
    loc = new Point( dm.findStart() );
    dm.reveal(loc);
  }
  /**Returns string of updated map and menu
  @return Name, level, HP/mHP, map*/
  @Override
  public String toString()
  {
    return "\n" + getName() + "\t|\tLevel: " + level +"\nGold: "+getGold()+"\nKey(s): "+ key +"\nHP: " + getHp()+ "/" + getMaxHP() + "\n" + map.mapToString(loc);
  }
  /**Returns current map level
  @return map level (1-3)*/
  public int getLevel() {
	  return level;
  }
  /**Returns hero's current location
  @return current location*/
  public Point getLoc(){
    return loc;
  }
  /**Loads in new map after each level*/
  public void levelUp()
  {
    level++;
    int num = level;
    if ( num > 3 )
    {
      num = level % 3;
      if ( num == 0 )
      {
        num = 3;
      }
    }
    if (level > 3){
      level = 1;
    }
    map.loadMap(num);
    map.reveal(loc);
  }

  /**Moves hero in the north direction
  @return the new current location of hero (up one line)*/
  public char goNorth()
  { 
    double temp_x = loc.getX();
    double temp_y = loc.getY();
    temp_y -= 1;
    if ( temp_y < 0 )
    {
      return 'x';
    }
    else
    {
      loc.setLocation(temp_x, temp_y);
      //map.reveal(loc);
      Map dungeonMap = map.getInstance();
      dungeonMap.reveal(loc);
      char letter = map.getCharAtLoc(loc);
      /*if ( letter != 'f' && letter != 's'){
        map.removeCharAtLoc(loc);
      }*/
      return letter;
    }
  }
  /**Moves hero in the south direction
  @return the new current location of hero (down one line)*/
  public char goSouth()
  {
    double temp_x = loc.getX();
    double temp_y = loc.getY();
    temp_y += 1;
    if ( temp_y > 4 )
    {
      return 'x';
    }
    else
    {
      loc.setLocation(temp_x, temp_y);
      //map.reveal(loc);
      Map dungeonMap = map.getInstance();
      dungeonMap.reveal(loc);
      char letter = map.getCharAtLoc(loc);
      /*if ( letter != 'f' && letter != 's')
      {
        map.removeCharAtLoc(loc);
      }*/
      return letter;
    }
  }
  /**Moves hero in the east direction
  @return the new current location of hero (next character)*/
  public char goEast()
  {
    double temp_x = loc.getX();
    double temp_y = loc.getY();
    temp_x += 1;
    if ( temp_x > 4 )
    {
      return 'x';
    }
    else
    {
      loc.setLocation(temp_x, temp_y);
      Map dungeonMap = map.getInstance();
      dungeonMap.reveal(loc);
      char letter = map.getCharAtLoc(loc);
      /*if ( letter != 'f' && letter != 's')
      {
        map.removeCharAtLoc(loc);
      }*/
      return letter;
    }
  }
  /**Moves hero in the west direction
  @return the new current location of hero (previous character)*/
  public char goWest()
  {
    double temp_x = loc.getX();
    double temp_y = loc.getY();
    temp_x -= 1;
    if ( temp_x < 0 )
    {
      return 'x';
    }
    else
    {
      loc.setLocation(temp_x, temp_y);
      Map dungeonMap = map.getInstance();
      dungeonMap.reveal(loc);
      char letter = map.getCharAtLoc(loc);
      /*if ( letter != 'f' && letter != 's')
      {
        map.removeCharAtLoc(loc);
      }*/
      return letter;
    }
  }

  /**Representation of hero's physical attack
   @param e enemy entity that takes damage from hero's attack*/
  @Override
  public String attack(Entity e)
  {
    int atk = ( int ) (Math.random() * 5) + 1;
    e.takeDamage( atk );
    return this.getName() + " attacks " + e.getName() + " for " + atk + " damage ";
  }

  /**Representation of hero's magic attack based on what spell they want
    @param e enemy entity that takes damage from hero's spell
    @param i is the number corresponding with spell */
  public String magicAttack(Entity e, int i)
  {
    String line = ""; 
    if ( i == 1)
    {
      line = this.magicMissle(e);
    }
    else if ( i == 2)
    {
      line = this.fireball(e);
    }
    else if ( i == 3 )
    {
      line = this.thunderclap(e);
    }
    return line;
  }

  /**Glowing darts of magical force 
    @param e will be enemy*/
  @Override
  public String magicMissle(Entity e)
  {
    int atk = ( int ) (Math.random() * 5) + 3;
    e.takeDamage( atk );
    return this.getName() + " hits " + e.getName() + " for " + atk + " damage with Magic Missile.";
  }
  /**A bright streak of explosive flame 
    @param e will be enemy*/
  @Override
  public String fireball(Entity e)
  {
    int atk = ( int ) (Math.random() * 3) + 4;
    e.takeDamage( atk );
    return this.getName() + " hits " + e.getName() + " for " + atk + " damage with Fireball.";
  }
  /**A mighty burst of thunder 
    @param e will be enemy*/
  @Override
  public String thunderclap(Entity e)
  {
    int atk = ( int ) (Math.random() * 7) + 3;
    e.takeDamage( atk );
    return this.getName() + " zaps " + e.getName() + " for " + atk + " damage with Thunderclap.";
  }

  /** Getter method to return amount of gold
  @return the amount of gold the player has
  */
  public int getGold()
  {
    return gold;
  }

  /** Adds gold to player's inventory 
  @param g amount of gold to add to total
  */
  public void collectGold(int g)
  {
    gold += g;

  }

  /** Spends player gold if they have sufficient funds
  @param g amount of gold to spend
  */
  public void spendGold(int g)
  {
    if(gold - g >= 0 )
    {
      System.out.println("Purchase complete.");
      gold -= g;
    }
    else{
      System.out.println("Insufficient amount of gold.");
    }
  }

  /** Tests to see if player has a key in inventory
  @return if player has a key in inventory 
  */
  public boolean hasKey()
  {
    if (key > 0){
      return true;
    }
    else{
      return false;
    }
  }

  /** Adds key to player's inventory
  */
  public void pickUpKey()
  {
    System.out.println("You picked up a key.");
    key++;
  }

  /** Uses a key from player's inventory
  @return if player has a key to use
  */
  public boolean useKey()
  {
    if (hasKey() == true)
    {
      key --;
      return true;
    }
    else{
      return false;
    }
  }
}
