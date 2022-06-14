/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author Мария
 */
public class Game {
    
    CharacterAction action = new CharacterAction();
    ChangeTexts change = new ChangeTexts();
    Fight fight = new Fight();
    
    public Player NewGame(Player human, Player enemy, JLabel L1, JLabel L2, 
                      JLabel L3, JLabel L4, JProgressBar pr1, JProgressBar pr2){
        action.setEnemyes();
        enemy = action.ChooseEnemy(L1, L2, L3, L4);
        action.HP(human, pr1);
        action.HP(enemy, pr2);
        pr2.setMaximum(enemy.getMaxHealth());
        return enemy;
    }
    
}
