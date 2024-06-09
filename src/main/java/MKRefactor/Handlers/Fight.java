/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MKRefactor.Handlers;

//ADD IMAGE!!!
import MKRefactor.Fabric.EnemyFabric;
import MKRefactor.Game.Human;
import MKRefactor.Game.Items;
import MKRefactor.Game.Player;
import MKRefactor.Game.Result;
import MKRefactor.Character.ShaoKahn;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Класс описания возможных действий персонажей во время боя
 * @author Мария
 */
public class Fight {
    ChangeTexts change = new ChangeTexts();
    int[] kind_attack = {0};
    int[] experiences = {40, 90, 180, 260, 410};
    EnemyFabric fabric = new EnemyFabric();
    public int i = 1;
    int k = -1;
    int stun = 0;
    double v = 0.0;
    int current_location = 1;
    int numberOfRoundsInLocation = 2;
    /**
     * Установление кол-ва раундов для локации в зависимости от уровня игрока
     * @param level уровень игрока
     */
    private void setnumberOfRoundsInLocation(int level)
    {
        this.numberOfRoundsInLocation = level + 2;
    }
    /**
     * Описание результата выбранных действий игрока и противника
     */
    public void move(Player p1, Player p2, JLabel l, JLabel l2) {
        if (stun == 1) {
            p1.setAttack(-1);
        }
        switch (Integer.toString(p1.getAttack()) + Integer.toString(p2.getAttack())) {
            case "10":
                v = Math.random();
                if (p1 instanceof ShaoKahn & v < 0.15) {
                    p2.addHealth(-(int)(p1.getDamage() * 0.5));
                    l2.setText("Your block is broken");
                } else {
                    p1.addHealth(-(int)(p2.getDamage() * 0.5));
                    l2.setText(p2.getName() + " counterattacked");
                }
                break;
            case "11":
                p2.addHealth(-p1.getDamage());
                l2.setText(p1.getName() + " attacked");
                break;
            case "00":
                v = Math.random();
                if (v <= 0.5) {
                    stun = 1;
                }
                l2.setText("Both defended themselves");
                break;
            case "01":
                l2.setText(p1.getName() + " didn't attacked");
                break;
            case "-10":
                l.setText(p1.getName() + " was stunned");
                stun = 0;
                l2.setText(p2.getName() + " didn't attacked");
                break;
            case "-11":
                p1.addHealth(-p2.getDamage());
                l.setText(p1.getName() + " was stunned");
                stun = 0;
                l2.setText(p2.getName() + " attacked");
                break;
        }
    }
    /**
     * Описание действий при выборе игроком атаки/защиты
     * @param human Игрок
     * @param enemy Противник
     * @param a выбор игрока атаковать или защищаться
     * a - attack (1) or defend (0)
     * @param label Количество здоровья противника
     * @param label2 Количество здоровья игрока
     * @param dialog Окно с сообщением о завершении боя
     * @param label3 Надпись о завершении боя
     * @param pr1 Полоска здоровья игрока
     * @param pr2 Полоска здоровья противника
     * @param dialog1 Окно победы с попаданием в топ-10
     * @param dialog2 Окно победы без попадания в топ-10
     * @param frame Окно боя
     * @param results Результаты для внесения в таблицу рекордов
     * @param label4 Надпись о победе с попаданием в топ-10
     * @param label5 Надпись о победе без попадания в топ-10
     * @param label6 Надпись о том, чей сейчас ход
     * @param label8 Надпись о том, что произошло в бою
     * @param items Предметы в мешке
     * @param rb Кнопка, соответствующая кресту возрождения из мешка
     * @param changeLocationLabel Метка с информацией о локации
     * @param backgroundPanel Главное окно игры с боем
     */
    public void hit(Player human, Player enemy, int a, JLabel label, JLabel label2, JDialog dialog, JLabel label3, CharacterAction action,
                    JProgressBar pr1, JProgressBar pr2, JDialog dialog1, JDialog dialog2, JFrame frame, ArrayList<Result> results, JLabel label4, JLabel label5,
                    JLabel label6, JLabel label7, JLabel label8, Items[] items, JRadioButton rb, JLabel changeLocationLabel, JPanel backgroundPanel) {
        label7.setText("");
        human.setAttack(a);
        if(enemy.getRecoveryAttempt() & enemy instanceof ShaoKahn ){
            /*
           игрок защищается (a == 0) -> босс восстанавливает 50% от текущего урона
            игрок защищается (a == 1) -> босс получает двойной урон
            */
            ((ShaoKahn)enemy).attemptToRecover(a, human.getDamage());
            enemy.setRecoveryAttempt(false);
        }
        if (k < kind_attack.length - 1) {
            k++;
        } else {
            kind_attack = action.chooseBehavior(enemy, action);
            k = 0;
        }
        enemy.setAttack(kind_attack[k]);
        if (i % 2 == 1) {
            move(human, enemy, label7, label8);
        } else {
            move(enemy, human, label7, label8);
        }
        i++;
        change.roundTexts(human, enemy, label, label2, i, label6);
        action.hp(human, pr1);
        action.hp(enemy, pr2);
        if ((i + (int) (Math.random() * 5)) % 4 == 0 & enemy instanceof ShaoKahn) {
            // в рандомный момент Босс пытается восстановить здоровье
            enemy.setRecoveryAttempt(true);
        }
        if (human.getHealth() <= 0 & items[2].getAmount() > 0) {
            human.setHealth((int) (human.getMaxHealth() * 0.05));
            items[2].setAmount(-1);
            action.hp(human, pr1);
            label2.setText(human.getHealth() + "/" + human.getMaxHealth());
            rb.setText(items[2].getName() + ", " + items[2].getAmount() + " шт");
            label7.setText("Вы воскресли");
        }
        if (human.getHealth() <= 0 | enemy.getHealth() <= 0) {
            endRound(human, enemy, dialog, label3, action, items, changeLocationLabel, results, dialog1, dialog2, frame, label4, label5, backgroundPanel);
        }
    }
    /**
     * Описание действий при завершении раунда
     * @param human Игрок
     * @param enemy Противник
     * @param dialog Окно с сообщением о завершении боя
     * @param label Надпись о завершении боя
     * @param items Предметы в мешке
     * @param changeLocationLabel Метка с информацией о локации
     * @param results Результаты для внесения в таблицу рекордов
     * @param dialog1 Окно победы с попаданием в топ-10
     * @param dialog2 Окно победы без попадания в топ-10
     * @param frame Окно боя
     * @param label4 Надпись о победе с попаданием в топ-10
     * @param label5 Надпись о победе без попадания в топ-10
     * @param BackgroundPanel Главное окно игры с боем
     */
    public void endRound(Player human, Player enemy, JDialog dialog, JLabel label, CharacterAction action, Items[] items, JLabel changeLocationLabel,
                         ArrayList<Result> results, JDialog dialog1, JDialog dialog2, JFrame frame, JLabel label4, JLabel label5, JPanel BackgroundPanel) {
        ((Human)human).incrementCurrentRound();
        dialog.setVisible(true);
        dialog.setBounds(300, 150, 700, 600);
        if (human.getHealth() > 0) {
            label.setText("You win");
            ((Human) human).addWin();
            if (enemy instanceof ShaoKahn) {
                action.addItems(38, 23, 8, items);
                action.addPointsBoss(((Human) human), action.getEnemies());
            } else {
                action.addItems(25, 15, 5, items);
                action.addPoints(((Human) human), action.getEnemies());
            }
        } else {
            label.setText(enemy.getName() + " win");
        }
        // do we need to change location?
        if (((Human) human).getCurrent_round() == this.numberOfRoundsInLocation) {
            this.current_location++;
            // set up number of rounds for next location
            setnumberOfRoundsInLocation(human.getLevel());
            ((Human) human).setZeroRound();
            changeLocationLabel.setText("Location changed");
        }
        if (this.current_location == ((Human) human).getLocations_number() + 1) {
            System.out.println("End Final Round");
            endFinalRound(((Human) human), action, results, dialog1, dialog2, frame, label4, label5);
        } else {
            dialog.setVisible(true);
            dialog.setBounds(300, 150, 700, 600);
        }
        i = 1;
        k = -1;
        kind_attack = resetAttack();
    }
    /**
     * Описание действий при завершении финального раунда
     * @param human Игрок
     * @param results Результаты для внесения в таблицу рекордов
     * @param dialog1 Окно победы с попаданием в топ-10
     * @param dialog2 Окно победы без попадания в топ-10
     * @param frame Окно боя
     * @param label1 Надпись о победе с попаданием в топ-10
     * @param label2 Надпись о победе без попадания в топ-10
     */
    public void endFinalRound(Human human, CharacterAction action, ArrayList<Result> results, JDialog dialog1, JDialog dialog2, JFrame frame,
                              JLabel label1, JLabel label2) {
        String text = "Победа не на вашей стороне";
        if (human.getHealth() > 0) {
            human.addWin();
            action.addPoints(human, action.getEnemies());
            text = "Победа на вашей стороне";
        }
        boolean top = false;
        if (results == null) {
            top = true;
        } else {
            int i = 0;
            for (int j = 0; j < results.size(); j++) {
                if (human.getPoints() < results.get(j).getPoints()) {
                    i++;
                }
            }
            if (i < 10) {
                top = true;
            }
        }
        if (top) {
            dialog1.setVisible(true);
            dialog1.setBounds(150, 150, 600, 500);
            label1.setText(text);
        } else {
            dialog2.setVisible(true);
            dialog2.setBounds(150, 150, 470, 360);
            label2.setText(text);
        }
        frame.dispose();
    }

    public int[] resetAttack() {
        int a[] = {0};
        return a;
    }
    /**
     * Описание действий, производимых перед новым раундом
     * @param human Игрок
     * @param label Изображение противника
     * @param pr1 Полоска здоровья игрока
     * @param pr2 Полоска здоровья противника
     * @param label2 Надпись с именем противника
     * @param text Надпись с величиной урона от атаки противника
     * @param label3 Надпись с количеством здоровья противника
     * @return Противник для нового раунда
     */
    public Player newRound(Player human, JLabel label, JProgressBar pr1, JProgressBar pr2, JLabel label2, JLabel text, JLabel label3, CharacterAction action) {
        Player enemy1 = null;
        if (((Human) human).getWin() == 6 | ((Human) human).getWin() == 11) {
            enemy1 = action.chooseBoss(label, label2, text, label3, human.getLevel());
        } else {
            enemy1 = action.chooseEnemy(label, label2, text, label3);
        }
        pr1.setMaximum(human.getMaxHealth());
        pr2.setMaximum(enemy1.getMaxHealth());
        human.setHealth(human.getMaxHealth());
        enemy1.setHealth(enemy1.getMaxHealth());
        action.hp(human, pr1);
        action.hp(enemy1, pr2);
        return enemy1;
    }
}