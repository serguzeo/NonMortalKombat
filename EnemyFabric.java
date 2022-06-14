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
public class EnemyFabric {

    public Player create(int i/*, Player human, JLabel label, JProgressBar pr, JLabel label2, JLabel text, JLabel label3*/) {
        EnemyFabricInterface fabric = null;

        switch (i) {
            case 0:
                fabric = new BarakaFabric();
                break;
            case 1:
                fabric = new SubZiroFabric();
                break;
            case 2:
                fabric = new LuKanFabric();
                break;
            case 3:
                fabric = new SonyaBlaydFabric();
                break;
        }
        Player enemy = fabric.create(/*human, label, label2*/);
        /*pr.setMaximum(enemy.getMaxHealth());
        text.setText(Integer.toString(enemy.getDamage()));
        label3.setText(Integer.toString(enemy.getHealth()) + "/" + Integer.toString(enemy.getMaxHealth()));
        */return enemy;
    }
}
