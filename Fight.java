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

    //CharacterAction action = new CharacterAction();
    ChangeTexts change = new ChangeTexts();
    int kind_attack[]={0};
    int experiences[] = {40, 90, 180, 260, 410};
    EnemyFabric fabric = new EnemyFabric();
    int i = 1;
    int k = -1;
    
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
                //System.out.println(0);
                break;
            }
            case "01":
                //System.out.println(0);
                break;
        }
    }

    public int[] ChooseBehavior(Player enemy, CharacterAction action) {
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

    public void Round( Player human, Player enemy, int a, JLabel label, 
            JLabel label2, JDialog dialog, JLabel label3, CharacterAction action, 
            JProgressBar pr1, JProgressBar pr2) {

        human.setAttack(a);
        
        if (k < kind_attack.length - 1) {
            k++;
        } else {
            kind_attack = ChooseBehavior(enemy, action);
            k = 0;
        }
        enemy.setAttack(kind_attack[k]);
        
        if (i % 2 == 1) {
            Move(human, enemy);
        } else {
            Move(enemy, human);
        }
        
        change.RoundTexts(human, enemy, label, label2);
        action.HP(human, pr1);
        action.HP(enemy, pr2);
        EndRound(human, enemy, dialog, label3);
        i++;

    }

    public void EndRound(Player human, Player enemy, JDialog dialog, JLabel label) {
        if (human.getHealth() <= 0 | enemy.getHealth() <= 0) {
            dialog.setVisible(true);
            dialog.setBounds(150, 150, 700, 600);
            if (human.getHealth() > 0) {
                label.setText("You win");

            } else {
                label.setText(enemy.getName() + " win");
            }
            i=1;
            k=0;
            kind_attack=ResetAttack();
            
            
        }
    }
    
    public int[] ResetAttack(){
        int a[]={0};
        return a;
    }

    public Player NewRound(Player human, JLabel label, JProgressBar pr1, 
            JProgressBar pr2, JLabel label2, JLabel text, JLabel label3, CharacterAction action) {
        if (human.getHealth() > 0) {
            ((Human) human).setWin();
            action.AddPoints(((Human) human), action.getEnemyes());
        }
        
        
        Player enemy1 = null;
        //int e = (int) (Math.random() * 4);
        enemy1 = action.ChooseEnemy(label, label2, text, label3);
                //action.ChooseEmemy(human, label, pr2, label2, text, label3);
        
        pr1.setMaximum(human.getMaxHealth());
        pr2.setMaximum(enemy1.getMaxHealth());
        human.setNewHealth(human.getMaxHealth());
        enemy1.setNewHealth(enemy1.getMaxHealth());
        action.HP(human, pr1);
        action.HP(enemy1, pr2);
        return enemy1;
    }

}
