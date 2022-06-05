/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

/**
 *
 * @author Мария
 */
public class SubZiro extends Player{
    
    public SubZiro(int level, int health, int damage , int attack){
        super (level, health, damage, attack);
    }
    
    @Override
    public String getName(){
        return "Sub-Ziro";
    }
}
