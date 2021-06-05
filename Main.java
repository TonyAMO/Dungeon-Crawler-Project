/*
Group: 25
Members: Antonio Ojeda, Alyssa Faiferlick, and Tania Adame
Date: May 4, 2021
Description: In this program, the user would be able to explore a dungeon, 
which would have various different levels, monsters to fight, and items to find.
The program would display the map of the dungeon and the rooms that the user had
explored already. When the user fights monsters, they can choose to fight or run. 
*/

import java.io.*;
import java.util.*;
import java.awt.*;

public class Main {
  public static void main(String[] args) {
    Map dungeonMap = Map.getInstance();
    System.out.println("What is your name, Traveler?");
    String name = CheckInput.getString(); // gets name of user
    EnemyGenerator g = new EnemyGenerator(); // initial Enemy Generator for further use
    Hero h = new Hero( name, dungeonMap ); //creates Hero based off user's name 
    int choice = 0; //users choice of direction 
    char c = ' '; //character representing the room that the user found
    while ( choice != 5 ){ //loops until user quits
      System.out.println( h.toString() );
      System.out.println( "1. Go North ");
      System.out.println( "2. Go South ");
      System.out.println( "3. Go East ");
      System.out.println( "4. Go West ");
      System.out.println( "5. Quit ");
      choice = CheckInput.getIntRange( 1, 5);
      if ( choice == 1 ){
        c = h.goNorth();
      }else if ( choice == 2 ){
        c = h.goSouth();
      }else if ( choice == 3 ){
        c = h.goEast();
      }else if ( choice == 4 ){
        c = h.goWest();
      }else{
        break;
      }
      if ( c == 'x' ){
        System.out.println("Can't go that way, pick a different direction.");
      }else if ( c == 'n' ){
        System.out.println("There was nothing here.");
        dungeonMap.removeCharAtLoc(h.getLoc());
      }else if ( c == 's' ){
        System.out.println("You're back at the start where you find a small store. \nYou would like to enter? Y/N");
        boolean enterStore = CheckInput.getYesNo();
        if (enterStore == true) {
          store(h);
        }
      }else if ( c == 'f' ){
        if(h.hasKey() == true){
          h.useKey();
          System.out.println("You found the exit. The key you have unlocks the door. \nProceeding to the next level.");
          h.levelUp();
        }else{
          System.out.println("You found the exit. However the door is locked, you need a key to continue.");
        }
      }else if ( c == 'i' ){ 
        int item = ( int )(Math.random() * 2) + 1;
        switch(item){
          case 1:
          //Player finds a key
            h.pickUpKey();
            break;
          
          case 2:
          //Player finds a health potion
            Random hRand = new Random();
            System.out.println("You found a Health Potion! You drink it to restore your health.");
            int rHeal = hRand.nextInt(11) + 1;
            if (rHeal == 11){
              h.heal( 25 );
            }else{
              h.heal(rHeal);
            }
            break;

          default:
            System.out.println("Error.");
        }
        dungeonMap.removeCharAtLoc(h.getLoc());
      }else if ( c == 'm' ){
        Enemy e = g.generateEnemy(h.getLevel());
        System.out.println( "You've encountered a " + e.getName() );
        boolean alive = monsterRoom(h, e);
        if ( alive == false ){
          System.out.println(" You have died.");
          break;
        }
      }
    }
    System.out.println("Game Over.");
  }

  /*public static int choice(){
    System.out.println( "1. Attack ");
    System.out.println( "2. Run Away ");
    int ans = CheckInput.getIntRange( 1, 2);
    if ( ans == 1 ){
      return ans;
    }else{
      return ans;
    }  
  }*/
  /**General Combat
    @param h hero combatant
    @param e enemy combatant
    @param f first strike
    @return Returns true if hero is still alive, otherwise it's false and the game is over */
  public static boolean fight( Hero h, Enemy e, int f){
      if (f == 0){
        System.out.println( "1. Physical Attack ");
        System.out.println( "2. Magic Attack ");
        int ans = CheckInput.getIntRange( 1, 2);
        if ( ans == 1 ){
          System.out.println( h.attack( e ) );
        }else if ( ans == 2){
          System.out.println( Magical.MAGIC_MENU );
          int spell = CheckInput.getIntRange( 1, 3);
          System.out.println( h.magicAttack(e , spell ) );
        }
        if ( e.getHp() > 0){
          System.out.println( e.attack( h ) );
    }else{
          System.out.println( "You have defeated the " + e.getName() );
          int g = ( int )(Math.random() * 8) + 3;
          System.out.println("Monster Dropped " + g
          + " gold piece(s)");
          h.collectGold(g);
        }
      }
      else if(f == 1){
        if ( e.getHp() > 0){
          System.out.println( e.attack( h ) );
          
         }else{
            System.out.println( "You have defeated the " + e.getName() );
            int g = ( int )(Math.random() * 8) + 3;
            System.out.println("Monster Dropped " + g
            + " gold piece(s)");
            h.collectGold(g);
        }
        System.out.println( "1. Physical Attack ");
        System.out.println( "2. Magic Attack ");
        int ans = CheckInput.getIntRange( 1, 2);
        if ( ans == 1 ){
          System.out.println( h.attack( e ) );
          if (e.getHp() <= 0){
            System.out.println( "You have defeated the " + e.getName() );
            int g = ( int )(Math.random() * 8) + 3;
            System.out.println("Monster Dropped " + g
            + " gold piece(s)");
            h.collectGold(g);
          }
        }else if ( ans == 2){
          System.out.println( Magical.MAGIC_MENU );
          int spell = CheckInput.getIntRange( 1, 3);
          System.out.println( h.magicAttack(e , spell ) );
          if (e.getHp() <= 0){
            System.out.println( "You have defeated the " + e.getName() );
            int g = ( int )(Math.random() * 8) + 3;
            System.out.println("Monster Dropped " + g
            + " gold piece(s)");
            h.collectGold(g);
             }
        }
        
      }
      if ( h.getHp() > 0 ){
        return true;
      }
      else{
        return false;
      }
  }
  
  /**Store area where you exchange gold for upgrades
    @param h hero customer*/
  public static void store(Hero h){
     System.out.println("Welcome to Cleary's Store.");
     int storeOption = 0;
     do{
       int playerGold = h.getGold();
       System.out.println("You have " + playerGold + " gold piece(s).");
       System.out.println("1. Buy Health Potion (25 Gold)\n2. Buy Key (50 Gold)\n3. Exit Store");
       storeOption = CheckInput.getIntRange( 1, 3 );

       if(storeOption == 1){
         if (playerGold >= 25){
           h.spendGold(25);
           Random hRand = new Random();
           System.out.println("You drank the health potion and recovered some HP.");
           int rHeal = hRand.nextInt(11) + 1;
           if (rHeal == 11){
             h.heal( 25 );
           }else{
             h.heal(rHeal);
           }
         }else{
           System.out.println("Insufficient amount of gold. \nYou need 25 gold pieces to purchase a potion.");
         }
       }else if(storeOption ==2 ){
         if(playerGold >= 50){
            h.spendGold(50);
            h.pickUpKey();
            System.out.print("You can use this to unlock doors.");
         }else{
           System.out.println("Insufficient amount of gold. \nYou need 50 gold pieces to purchase a key.");
         }
       }else{
         System.out.println("Exiting store...");
         break;
       }
     }while(storeOption != 3);
  }
  
  //For if you want to have a run away feature
  /**Monster Room: when you encounter a monster
    @param h Hero
    @param e Enemy
    @return boolean value to determine whether hero is still alive or not*/
  public static boolean monsterRoom( Hero h, Enemy e ){
    //System.out.println( "You've encountered a " + e.getName() );
    //int ans = 0;
    boolean alive = true;
    Map dungeonMap = Map.getInstance();
    Random firstTurn = new Random();
    int ft = firstTurn.nextInt(2); //decides who goes first to fight
    boolean first = true; //triggers declaration of who attacks first
    System.out.println( e );
    int ans = 1;
    do{
      System.out.println( "1. Fight ");
      System.out.println( "2. Run Away ");
      ans = CheckInput.getIntRange( 1, 2);
      if ( ans == 1 ){
        if (ft == 0){
              if(first){
                System.out.println( "You attack first!");}
                alive = fight( h , e, ft);
                first = false;
        }else {
          if(first){
            System.out.println(e.getName() + " attacks first!");
            }
          alive = fight( h , e, ft);
          first = false;
        }
        dungeonMap.removeCharAtLoc(h.getLoc());
      }else{
        char r = 'x';
        while ( r == 'x'){
          int d = ( int ) (Math.random() * 4) + 1;
          if( d == 1 )
          {
            r = h.goNorth();
          }
          else if ( d == 2 )
          {
            r = h.goSouth();
          }
          else if ( d == 3 )
          {
            r = h.goWest();
          }
          else
          {
            r = h.goEast();
          }
        }
        if ( r == 'f' ){
          if(h.hasKey() == true){
          h.useKey();
          System.out.println("You found the exit. The key you have unlocks the door. \nProceeding to the next level.");
          h.levelUp();
        }else{
          System.out.println("You found the exit. However the door is locked, you need a key to continue.");
        }
        }
      }
      alive = true;
    }while ( e.getHp() > 0 && alive == true && ans == 1 );
    return alive;
  }
}