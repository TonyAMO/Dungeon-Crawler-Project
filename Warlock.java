/**Warlock.java - Warlock decorator*/
public class Warlock extends EnemyDecorator implements Magical{
  //private String name;
  /**Warlock decorator constructor
    @param e type of enemy to be decorated */
  public Warlock( Enemy e ){
    super( e, e.getName(), e.getHp() + 1);
    //name = e.getName();
  }

  /**Glowing darts of magical force 
    @param e will be enemy*/
  @Override
  public String magicMissle(Entity e){
    int atk = ( int ) (Math.random() * 2);
    e.takeDamage( atk );
    return this.getName() + " hits " + e.getName() + " for " + atk + " damage with Magic Missile.";
  }
  /**A bright streak of explosive flame 
    @param e will be hero*/
  @Override
  public String fireball(Entity e){
    int atk = ( int ) (Math.random() * 3);
    e.takeDamage( atk );
    return this.getName() + " hits " + e.getName() + " for " + atk + " damage with Fireball.";
  }
  /**A mighty burst of thunder 
    @param e will be hero*/
  @Override
  public String thunderclap(Entity e){
    int atk = ( int ) (Math.random() * 3) + 1;
    e.takeDamage( atk );
    return this.getName() + " zaps " + e.getName() + " for " + atk + " damage with Thunderclap.";
  }

  /**Representation of Magical enemy's attack (utlizes Magical interface)
    @param e representation of magical enemy*/
  public String attack( Entity e ){
    String line = ""; 
    int num = ( int ) (Math.random() * 3) + 1;
    if ( num == 1 ){
      line = magicMissle( e );
    }else if ( num == 2 ){
      line = fireball( e );
    }else if ( num == 3 ){
      line = thunderclap( e );
    }
    return line;
  }
  /**Prints additional decorator alongside enemy name
    @return enemy's full name with decorator */
  public String getName(){
    return super.getName() + " Warlock";
  }
}