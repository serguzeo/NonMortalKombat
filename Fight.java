/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

//ADD IMAGE!!!

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;


/*    ImageIcon icon1 = createIcon("images/settings.jpg");
    JLabel label = new JLabel(icon1);
    panel.add(label).setBounds(10,10,27,30);
 */
/**
 *
 * @author Мария
 */
public class Fight {

    CharacterAction action = new CharacterAction();
    ChangeTexts change = new ChangeTexts();
    int experiences[] = {40, 90, 180, 260, 410};
    EnemyFabric fabric = new EnemyFabric();
    
    public void Move(Player p1, Player p2) {

        switch (Integer.toString(p1.getAttack()) + Integer.toString(p2.getAttack())) {
            case "10":
                p1.setHealth(-(int) (p2.getDamage() * 0.5));
                //System.out.println(p1.getDamage() * 0.5);
                break;
            case "11":
                p2.setHealth(-p1.getDamage());
                //System.out.println(p1.getDamage());
                break;
            case "00": {
                double i = Math.random();
                if (i >= 0.5) {
                    //оглушение
                }
                System.out.println(0);
                break;
            }
            case "01":
                System.out.println(0);
                break;
        }
    }

    public int[] ChooseBehavior(Player enemy) {
        int arr[] = null;
        double i = Math.random();
        if (enemy instanceof Baraka) {
            arr = action.EnemyBehavior(15, 15, 60, 10, i);
        }
        if (enemy instanceof SubZiro) {
            arr = action.EnemyBehavior(25, 25, 0, 50, i);
        }
        if (enemy instanceof LuKan) {
            arr = action.EnemyBehavior(13, 13, 10, 64, i);
        }
        if (enemy instanceof SonyaBlayd) {
            arr = action.EnemyBehavior(25, 25, 50, 0, i);
        }
        return arr;
    }

    public void Round(int i, int k, Player human, Player enemy, int a, JLabel label, JLabel label2, JDialog dialog, JLabel label3) {
        //int i = 1;
        //int k = 0;
        int kind_attack[] = ChooseBehavior(enemy);
        //System.out.println("human " + human.getHealth() + "    enemy " + enemy.getHealth());
        //while (human.getHealth()>0 & enemy.getHealth()>0){
        human.setAttack(a);
        enemy.setAttack(kind_attack[k]);
        if (i % 2 == 1) {
            Move(human, enemy);
        } else {
            Move(enemy, human);
        }
        i++;
        if (k < kind_attack.length - 1) {
            k++;
        } else {
            kind_attack = ChooseBehavior(enemy);
            k = 0;
        }
        change.RoundTexts(human, enemy, label, label2);
        //label.setText(Integer.toString(enemy.getHealth()) + "/" + Integer.toString(enemy.getMaxHealth()));
        //label2.setText(Integer.toString(human.getHealth()) + "/" + Integer.toString(human.getMaxHealth()));
        //System.out.println("human " + human.getHealth() + "    enemy " + enemy.getHealth());
        EndRound(human, enemy, dialog, label3);
        //}
    }

    public void EndRound(Player human, Player enemy, JDialog dialog, JLabel label) {
        if (human.getHealth() <= 0 | enemy.getHealth() <= 0) {
            dialog.setVisible(true);
            dialog.setBounds(200, 200, 450, 350);
            if (human.getHealth() > 0) {
                label.setText("You win");

            } else {
                label.setText(enemy.getName() + " win");
            }
        }
    }

    public Player NewRound(Player human, JLabel label, JProgressBar pr1, JProgressBar pr2, JLabel label2, JLabel text, JLabel label3) {
        if (human.getHealth() > 0) {
            ((Human) human).setWin();
            action.AddPoints(((Human) human));
        }

        human.setNewHealth(human.getMaxHealth());
        Player enemy1 = null;
        int e = (int) (Math.random() * 4);
        enemy1 = fabric.create(e, human, label, pr2, label2, text, label3);
                //action.ChooseEmemy(human, label, pr2, label2, text, label3);
        action.HP(human, pr1);
        action.HP(enemy1, pr2);
        //System.out.println(enemy1.getLevel());
        return enemy1;
    }

}
