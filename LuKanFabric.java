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
public class LuKanFabric implements EnemyFabricInterface {

    @Override
    public Player create(/*Player human, JLabel label, JLabel label2*/) {
        Player enemy;
        ImageIcon icon1 = null;
        enemy = new LuKan(1, 70, 20, 1);
        //icon1 = new ImageIcon("C:\\Users\\Мария\\Desktop\\Лю кан.jpg");
        //label.setIcon(icon1);
        //label2.setText("Лю Кан (боец)");
        return enemy;
    }
}
