/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MortalCombat.Game.Combatant.Character;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Player;

import java.awt.*;

/**
 *
 * @author Мария
 */
public class SubZero extends Player implements Character {
    
    public SubZero(int level, int health, int damage , int attack){
        super (level, health, damage, attack);
    }

    @Override
    public void doAction(Combatant combatant) {

    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public String getName(){
        return "Sub-Zero";
    }
}
