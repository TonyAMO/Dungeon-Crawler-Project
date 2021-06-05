/**Decorates enemies with additional names*/
public abstract class EnemyDecorator extends Enemy{
  private Enemy enemy;
  /**EnemyDecorator constructor
  @param e Enemy type
  @param n Enemy's name
  @param hp Enemy's hp*/
  public EnemyDecorator(Enemy e, String n, int hp){
    super( n, hp );
    enemy = e;
  }
  /**Representation of enemy's attack (from original enemy)
    @param e hero entity enemy is fighting against*/
  public String attack(Entity e){
    return enemy.attack(e);
  }
}