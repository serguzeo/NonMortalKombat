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
public class ChangeTexts {

    CharacterAction action = new CharacterAction();

    public void NewRoundTexts(Player human, Player enemy, JProgressBar pr1, 
            JProgressBar pr2, JLabel label, JLabel label2, JLabel label3, 
            JLabel label4, JLabel label5, JLabel label6, JLabel label7) {
        //action.HP(human, pr1);
        //action.HP(enemy, pr2);
        label.setText(Integer.toString(((Human) human).getPoints()));
        label2.setText(Integer.toString(((Human) human).getExperience()) + "/" + ((Human) human).getNextExperience());
        label3.setText(Integer.toString(human.getLevel()) + " уровень");
        label4.setText(Integer.toString(enemy.getLevel()) + " уровень");
        label5.setText(Integer.toString(human.getMaxHealth()) + "/" + Integer.toString(human.getMaxHealth()));
        label6.setText(Integer.toString(enemy.getMaxHealth()) + "/" + Integer.toString(enemy.getMaxHealth()));
        label7.setText(Integer.toString(human.getDamage()));
    }

    public void RoundTexts(Player human, Player enemy, JLabel label, JLabel label2) {
        if (enemy.getHealth() >= 0) {
            label.setText(Integer.toString(enemy.getHealth()) + "/" + Integer.toString(enemy.getMaxHealth()));
        } else {
            label.setText("0/" + Integer.toString(enemy.getMaxHealth()));
        }
        if (human.getHealth() >= 0) {
            label2.setText(Integer.toString(human.getHealth()) + "/" + Integer.toString(human.getMaxHealth()));
        } else {
            label2.setText("0/" + Integer.toString(human.getMaxHealth()));
        }
    }

}
