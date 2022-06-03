/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

/**
 *
 * @author Мария
 */
public class Human extends Player{
    
    //private int level;
    //private int attack;
    //private int health;
    //private int damage;
    private int points;
    private int experience;
    private int win;
    
    
    
    public Human(int level, int health, int  damage, int attack){
        super (level, health, damage, attack);
        this.points=0;
        //this.health=80;
        //this.damage=16;
        this.experience=0;
        this.win=0;
        //this.attack=0;
    }
    
    /*public int getLevel(){
        return this.level;
    }*/
    public int getPoints(){
        return this.points;
    }
    /*public int getHealth(){
        return this.health;
    }
    public int getDamage(){
        return this.damage;
    }*/
    public int getExperience(){
        return this.experience;
    }
    public int getWin(){
        return this.win;
    }
    /*public int getAttack(){
        return this.attack;
    }
    
    public void setLevel(){
        this.level++;
    }*/
    public void setPoints(int p){
        this.points+=p;
    }
    /*public void setHealth(int h){
        this.health+=h;
    }
    public void setDamage(int d){
        this.damage+=d;
    }*/
    public void setExperience(int e){
        this.experience+=e;
    }
    public void setWin(){
        this.win++;
    }
    /*public void setAttack(int a){
        this.attack=a;
    }*/
    
}
