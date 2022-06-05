/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author Мария
 */
public class CharacterAction {

    int experience_for_next_level[] = {40, 90, 180, 260, 410, 1000};

    private final int kind_fight[][] = {{1, 0}, {1, 1, 0}, {0, 1, 0}, {1, 1, 1, 1}};

    /*public Player ChooseEmemy(Player human, JLabel label, JProgressBar pr, JLabel label2, JLabel text, JLabel label3) {
        int i = (int) (Math.random() * 4);
        Player enemy = new Player(0, 0, 0, 0);
        ImageIcon icon1 = null;
        switch (i) {
            case 0:
                enemy = new Baraka(human.getLevel() + 1, 100, 12, 1);
                icon1 = new ImageIcon("C:\\Users\\Мария\\Desktop\\Барака.jpg");
                label.setIcon(icon1);
                label2.setText("Барака (танк)");
                break;
            case 1:
                enemy = new SubZiro(human.getLevel() + 1, 60, 16, 1);
                icon1 = new ImageIcon("C:\\Users\\Мария\\Desktop\\Саб Зиро.jpg");
                label.setIcon(icon1);
                label2.setText("Саб-Зиро (маг)");
                break;
            case 2:
                enemy = new LuKan(human.getLevel() + 1, 70, 20, 1);
                icon1 = new ImageIcon("C:\\Users\\Мария\\Desktop\\Лю кан.jpg");
                label.setIcon(icon1);
                label2.setText("Лю Кан (боец)");
                break;
            case 3:
                enemy = new SonyaBlayd(human.getLevel() + 1, 80, 16, 1);
                icon1 = new ImageIcon("C:\\Users\\Мария\\Desktop\\Соня Блейд.jpg");
                label.setIcon(icon1);
                label2.setText("Соня Блейд (солдат)");
                break;
        }
        pr.setMaximum(enemy.getMaxHealth());
        text.setText(Integer.toString(enemy.getDamage()));
        label3.setText(Integer.toString(enemy.getHealth()) + "/" + Integer.toString(enemy.getMaxHealth()));
        return enemy;
    }*/
    public int[] EnemyBehavior(int k1, int k2, int k3, int k4, double i) {
        int arr[] = null;
        if (i < k1 * 0.01) {
            arr = kind_fight[0];
        }
        if (i >= k1 * 0.01 & i < (k1 + k2) * 0.01) {
            arr = kind_fight[1];
        }
        if (i >= (k1 + k2) * 0.01 & i < (k1 + k2 + k3) * 0.01) {
            arr = kind_fight[2];
        }
        if (i >= (k1 + k2 + k3) * 0.01 & i < 1) {
            arr = kind_fight[3];
        }
        return arr;
    }

    public void HP(Player player, JProgressBar progress) {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                progress.setMaximum(player.getMaxHealth());
                while (player.getHealth() > -20) {
                    if (player.getHealth() >= 0) {
                        progress.setValue(player.getHealth());
                    } else {
                        progress.setValue(0);
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        t.start();
    }

    public void AddPoints(Human human) {
        switch (human.getLevel()) {
            case 0:
                human.setExperience(20);
                human.setPoints(25 + human.getHealth() / 4);
                break;
            case 1:
                human.setExperience(25);
                human.setPoints(30 + human.getHealth() / 4);
                break;
            case 2:
                human.setExperience(30);
                human.setPoints(35 + human.getHealth() / 4);
                break;
            case 3:
                human.setExperience(40);
                human.setPoints(45 + human.getHealth() / 4);
                break;
            case 4:
                human.setExperience(50);
                human.setPoints(55 + human.getHealth() / 4);
                break;
        }
        for (int i = 0; i < 5; i++) {
            if (experience_for_next_level[i] == human.getExperience()) {
                human.setLevel();
                human.setNextExperience(experience_for_next_level[i + 1]);
                NewHealthHuman(human);
            }
        }
    }
    
    public void NewHealthHuman(Human human){
        int hp=0;
        int damage=0;
        switch (human.getLevel()){
            case 1:
                hp=25;
                damage=2;
                break;
            case 2:
                hp=30;
                damage=3;
                break;
            case 3:
                hp=30;
                damage=4;
                break;
            case 4:
                hp=40;
                damage=6;
                break;
        }
        human.setMaxHealth(hp);
        human.setDamage(damage);
    }
}
