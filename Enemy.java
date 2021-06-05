import java.util.*;

/**Enemy.java representation of enemy/enemies the hero will encounter*/
public abstract class Enemy extends Entity
{
  /** Representation of an enemy
  * @param n enemy's name
  * @param mHp enemy's max health
  */
  public Enemy( String n, int mHp)
  {
    super( n , mHp );
  }
 /** Representation of enemy's attack
  * @param e hero entity that takes damage from enemy's attack
  */
  @Override
  public String attack(Entity e)
  {
    int atk = ( int ) (Math.random() * 5) + 1;
    e.takeDamage( atk );
    return this.getName() + " attacks " + e.getName() + " for " + atk + " damage.";
  }
}