/**Magical.java - Interface containing magical spells*/
public interface Magical{
  public static final String MAGIC_MENU = "1. Magic Missle\n2. Fireball\n3. Thunderclap"; //magical spells menu

  public String magicMissle(Entity e);

  public String fireball(Entity e);

  public String thunderclap(Entity e);
}