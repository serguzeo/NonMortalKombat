/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author Мария
 */
public class SonyaBlaydFabric implements EnemyFabricInterface {

    @Override
    public Player create(Player human, JLabel label, JLabel label2) {
        Player enemy;
        ImageIcon icon1 = null;
        enemy = new SonyaBlayd(human.getLevel() + 1, 80, 16, 1);
        icon1 = new ImageIcon("C:\\Users\\Мария\\Desktop\\Соня Блейд.jpg");
        label.setIcon(icon1);
        label2.setText("Соня Блейд (солдат)");
        return enemy;
    }

}
