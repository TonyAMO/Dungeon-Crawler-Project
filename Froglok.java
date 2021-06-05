/**Froglok.java - Enemy type*/
public class Froglok extends Enemy{
  /**Froglock enemy object constructor*/
  public Froglok(){
    super("Froglok", 3);
  }
  /**Froglok's attack 
    @param e hero entity that froglok will be attacking*/
  public String attack(Entity e)
  {
    int atk = ( int ) (Math.random() * 3) + 1;
    e.takeDamage( atk );
    return this.getName() + " attacks " + e.getName() + " for " + atk + " damage.";
  }
}