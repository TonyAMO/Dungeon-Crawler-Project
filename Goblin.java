/**Goblin.java - Enemy type*/
public class Goblin extends Enemy{
  /**Goblin enemy object constructor*/
  public Goblin(){
    super("Goblin", 2);
  }
  /**Goblin's attack 
    @param e hero entity that goblin will be attacking*/
  public String attack(Entity e)
  {
    int atk = ( int ) (Math.random() * 2) + 1;
    e.takeDamage( atk );
    return this.getName() + " attacks " + e.getName() + " for " + atk + " damage.";
  }
}