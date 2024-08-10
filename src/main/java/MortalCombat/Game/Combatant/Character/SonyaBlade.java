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
public class SonyaBlade extends Player implements Character {
    
    public SonyaBlade (int level, int health, int  damage, int attack){
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
        return "Sonya Blade";
    }
}
