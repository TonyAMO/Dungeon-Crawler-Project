/**Warrior.java - Warior decorator*/
public class Warrior extends EnemyDecorator{
  //private String name;
  /**Warrior decorator constructor
    @param e type of enemy to be decorated */
  public Warrior( Enemy e ){
    super( e, e.getName(), e.getHp() + 2);
    //name = e.getName();
  }
  /**Enemy's attack method
    @param e hero entity that enemy is fighting against */
  public String attack( Entity e )
  {
    int atk = ( int ) (Math.random() * 3) + 1;
    return atk + super.attack(e);
  }
  /**Prints additional decorator alongside enemy name
    @return enemy's full name with decorator */
  public String getName(){
    return super.getName() + " Warrior";
  }
}