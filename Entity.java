/** Entity.java - Representation of a character in the game (hero or enemy)*/
public abstract class Entity
{
  private String name; //entity's name
  private int maxHp; //entity's max hit points
  private int hp; //entity's current hit points

  /**Constructor - initializes entity traits
  * @param n entity's name
  * @param mHp entity's max HP
  */
  public Entity( String n, int mHp)
  {
    name = n;
    maxHp = mHp;
    hp = mHp;
  }

  public abstract String attack( Entity e ); //object classes will override attack method

  /**@return entity's name*/
  public String getName()
  {
    return name;
  }
  /**@return entity's current HP*/
  public int getHp()
  {
    return hp;
  }
  /**@return entity's max HP*/
  public int getMaxHP()
  {
    return maxHp;
  }
  /**Increase entity's HP 
  @param h amount of HP increased*/
  public void heal( int h )
  {
    hp += h;
    if ( hp > maxHp)
    {
      hp = maxHp;
    }
  }
  /**Decreases HP via damage
  @param d amount of damage to decrease HP*/
  public void takeDamage( int d )
  {
    hp -= d;
    if ( hp < 0 )
    {
      hp = 0;
    }
  }
  /**Returns string of updated HP
  @return name , HP/mHP*/
  @Override
  public String toString()
  {
    return getName() + "\tHp: " + hp + "/" + maxHp + "\n";
  }
}
