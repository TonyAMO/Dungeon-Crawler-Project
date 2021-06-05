/**Orc.java - Enemy type*/
public class Orc extends Enemy{
  /**Orc enemy object constructor*/
  public Orc(){
    super("Orc", 4);
  }
  /**Orc's attack 
    @param e hero entity that orc will be attacking*/
  public String attack(Entity e)
  {
    int atk = ( int ) (Math.random() * 5);
    e.takeDamage( atk );
    return this.getName() + " attacks " + e.getName() + " for " + atk + " damage.";
  }
}