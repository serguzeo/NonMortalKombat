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
public class BarakaFabric implements EnemyFabricInterface {

    @Override
    public Player create(Player human, JLabel label, JLabel label2) {
        Player enemy;
        ImageIcon icon1 = null;
        enemy = new Baraka(human.getLevel() + 1, 100, 12, 1);
        icon1 = new ImageIcon("C:\\Users\\Мария\\Desktop\\Барака.jpg");
        label.setIcon(icon1);
        label2.setText("Барака (танк)");
        return enemy;
    }
}
