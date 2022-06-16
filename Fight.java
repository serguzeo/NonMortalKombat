/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

//ADD IMAGE!!!
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;



/**
 *
 * @author Мария
 */
public class Fight {

    ChangeTexts change = new ChangeTexts();
    int kind_attack[] = {0};
    int experiences[] = {40, 90, 180, 260, 410};
    EnemyFabric fabric = new EnemyFabric();
    int i = 1;
    int k = -1;
    int stun = 0;

    public void Move(Player p1, Player p2, JLabel l, JLabel l2) {
        if (stun==1){
            p1.setAttack(-1);
        }
        switch (Integer.toString(p1.getAttack()) + Integer.toString(p2.getAttack())) {
            case "10":
                p1.setHealth(-(int) (p2.getDamage() * 0.5));
                l2.setText(p2.getName()+" counterattacked");
                break;
            case "11":
                p2.setHealth(-p1.getDamage());
                l2.setText(p1.getName()+" attacked");
                break;
            case "00": {
                double v = Math.random();
                if (v >= 0.5) {
                    stun=1;
                }
                l2.setText("Both defended themselves");
                break;
            }
            case "01":
                l2.setText(p1.getName()+" didn't attacked");
                break;
            case "-10":
                l.setText(p1.getName()+" was stunned");
                stun=0;
                l2.setText(p2.getName()+" didn't attacked");
                break;
            case "-11":
                p1.setHealth(-p2.getDamage());
                l.setText(p1.getName()+" was stunned");
                stun=0;
                l2.setText(p2.getName()+" attacked");
                break;
        }
    }

    public int[] ChooseBehavior(Player enemy, CharacterAction action) {
        int arr[] = null;
        double i = Math.random();
        if (enemy instanceof Baraka) {
            arr = action.EnemyBehavior(15, 15, 60, 10, i);
        }
        if (enemy instanceof SubZero) {
            arr = action.EnemyBehavior(25, 25, 0, 50, i);
        }
        if (enemy instanceof LiuKang) {
            arr = action.EnemyBehavior(13, 13, 10, 64, i);
        }
        if (enemy instanceof SonyaBlade) {
            arr = action.EnemyBehavior(25, 25, 50, 0, i);
        }
        return arr;
    }

    public void Hit(Player human, Player enemy, int a, JLabel label,
            JLabel label2, JDialog dialog, JLabel label3, CharacterAction action,
            JProgressBar pr1, JProgressBar pr2, JDialog dialog1, 
            JDialog dialog2, JFrame frame, ArrayList<Result> results, 
            JLabel label4, JLabel label5, JLabel label6, JLabel label7, JLabel label8) {
        label7.setText("");
        human.setAttack(a);

        if (k < kind_attack.length - 1) {
            k++;
        } else {
            kind_attack = ChooseBehavior(enemy, action);
            k = 0;
        }
        enemy.setAttack(kind_attack[k]);
        if (i % 2 == 1) {
            Move(human, enemy, label7, label8);
        } else {
            Move(enemy, human, label7, label8);
        }
        i++;
        change.RoundTexts(human, enemy, label, label2, i, label6);
        action.HP(human, pr1);
        action.HP(enemy, pr2);
        if (human.getHealth() <= 0 | enemy.getHealth() <= 0) {
            if (((Human)human).getWin()==11){
               EndFinalRound(((Human)human), action, results, dialog1, dialog2, 
                       frame, label4, label5);
            }
            else {
                EndRound(human, enemy, dialog, label3, action);
            }
        }
        

    }

    public void EndRound(Player human, Player enemy, JDialog dialog, JLabel label,
             CharacterAction action) {
        dialog.setVisible(true);
        dialog.setBounds(200, 150, 700, 600);
        if (human.getHealth() > 0) {
            label.setText("You win");

        } else {
            label.setText(enemy.getName() + " win");
        }
        i = 1;
        k = 0;
        kind_attack = ResetAttack();

        if (human.getHealth() > 0) {
            ((Human) human).setWin();
            action.AddPoints(((Human) human), action.getEnemyes());
        }

    }

    public void EndFinalRound(Human human, CharacterAction action, 
            ArrayList<Result> results, JDialog dialog1, JDialog dialog2, JFrame frame,
            JLabel label1, JLabel label2){
        String text="Победа не на вашей стороне";
        if (human.getHealth() > 0) {
                human.setWin();
                action.AddPoints(human, action.getEnemyes());
                text="Победа на вашей стороне";
        }
        boolean top = false;
        if (results==null){
            top=true;
        }
        else{
            int i=0;
            for (int j=0; j<results.size(); j++){
                if (human.getPoints()<results.get(j).getPoints()){
                    i++;
                }
            }
            if (i<10){
                top=true;
            }
        }
        if (top){
            dialog1.setVisible(true);
            dialog1.setBounds(150, 150, 600, 500);
            label1.setText(text);
        }
        else{
            dialog2.setVisible(true);
            dialog2.setBounds(150, 150, 600, 500);
            label2.setText(text);
        }
        frame.dispose();
    }
        

    public int[] ResetAttack() {
        int a[] = {0};
        return a;
    }

    public Player NewRound(Player human, JLabel label, JProgressBar pr1,
            JProgressBar pr2, JLabel label2, JLabel text, JLabel label3, CharacterAction action) {

        Player enemy1 = null;
        enemy1 = action.ChooseEnemy(label, label2, text, label3);
        pr1.setMaximum(human.getMaxHealth());
        pr2.setMaximum(enemy1.getMaxHealth());
        human.setNewHealth(human.getMaxHealth());
        enemy1.setNewHealth(enemy1.getMaxHealth());
        action.HP(human, pr1);
        action.HP(enemy1, pr2);
        return enemy1;
    }

}
