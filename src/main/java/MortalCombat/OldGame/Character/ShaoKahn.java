/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MortalCombat.OldGame.Character;

import MortalCombat.OldGame.Player;

import java.awt.*;

/**
 *
 * @author Мария
 */
public class ShaoKahn extends Player{
    
    public ShaoKahn(int level, int health, int  damage, int attack){
        super (level, health, damage, attack);
    }


    @Override
    public String getName(){
        return "Shao Kahn";
    }
    /**
     * Метод регенерации здоровья босса
     * @param a Успешность попытки восстановления здоровья
     */
    public void attemptToRecover(int a, int possibleDamage) {
        if (a == 0) {
            int expectedHealth = (int) (this.getHealth() + (this.getMaxHealth() - this.getHealth()) * 0.5);
            this.setHealth(expectedHealth);
        }
        if (a == 1) {
            int expectedHealth = this.getHealth() - possibleDamage * 2;
            if (expectedHealth >= 0) {
                this.setHealth(expectedHealth);
            } else {
                this.setHealth(0);
            }
        }}
}
