/**Troll.java - Enemy type*/
public class Troll extends Enemy{
  /**Troll enemy object constructor*/
  public Troll(){
    super("Troll", 5);
  }
  /**Troll's attack 
    @param e hero entity that troll will be attacking*/
  public String attack(Entity e)
  {
    int atk = ( int ) (Math.random() * 6);
    e.takeDamage( atk );
    return this.getName() + " attacks " + e.getName() + " for " + atk + " damage.";
  }
}