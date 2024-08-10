/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MortalCombat.OldGame.Handlers;

import MortalCombat.OldGame.Character.*;
import MortalCombat.OldGame.Fabric.EnemyFabric;
import MortalCombat.OldGame.Human;
import MortalCombat.OldGame.Items;
import MortalCombat.OldGame.Player;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JOptionPane;
import java.util.Objects;

/**
 *
 * @author Мария
 */
public class CharacterAction {
    private final int[] experience_for_next_level = {40, 90, 180, 260, 410, 1000};
    private final int[][] kind_fight = {{1, 0}, {1, 1, 0}, {0, 1, 0}, {1, 1, 1, 1}};
    private Player[] enemies = new Player[5];
    EnemyFabric fabric = new EnemyFabric();
    private Player enemyy = null;
    /**
     * Создание вражеских персонажей
     */
    public void setEnemies() {
        enemies[0] = fabric.create(0);
        enemies[1] = fabric.create(1);
        enemies[2] = fabric.create(2);
        enemies[3] = fabric.create(3);
        enemies[4] = fabric.create(4);
    }
    public Player[] getEnemies() {
        return this.enemies;
    }
    /**
     * Случайная генерация вражеского персонажа
     * @param label Изображение противника
     * @param label2 Надпись с именем противника
     * @param text Надпись с величиной урона от атаки противника
     * @param label3 Надпись с количеством здоровья противника
     * @return вражеский персонаж
     */
    public Player chooseEnemy(JLabel label, JLabel label2, JLabel text, JLabel label3){// throws IOException {
        int i = (int) (Math.random() * 4);
        ImageIcon icon1 = null;
        switch (i) {
            case 0:
                enemyy = enemies[0];
                icon1 = new ImageIcon(getClass().getClassLoader().getResource("img/baraka.jpeg"));
                icon1 = new ImageIcon(icon1.getImage().getScaledInstance(
                        160, 290, java.awt.Image.SCALE_SMOOTH));
                label2.setText("Baraka (танк)");
                break;
            case 1:
                enemyy = enemies[1];
                icon1 = new ImageIcon(getClass().getClassLoader().getResource("img/subzero.png"));
                icon1 = new ImageIcon(icon1.getImage().getScaledInstance(
                        160, 290, java.awt.Image.SCALE_SMOOTH));
                label2.setText("Sub-Zero (маг)");
                break;
            case 2:
                enemyy = enemies[2];
                icon1 =
                        new ImageIcon(getClass().getClassLoader().getResource("img/liukang.jpg"));
                icon1 = new ImageIcon(icon1.getImage().getScaledInstance(
                        160, 290, java.awt.Image.SCALE_SMOOTH));
                label2.setText("Liu Kang (боец)");
                break;
            case 3:
                enemyy = enemies[3];
                icon1 = new ImageIcon(
                        getClass().getClassLoader().getResource("img/sonyablade.png"));
                icon1 = new ImageIcon(icon1.getImage().getScaledInstance(
                        160, 290, java.awt.Image.SCALE_SMOOTH));
                label2.setText("Sonya Blade (солдат)");
                break;
        }
        label.setIcon(icon1);
        text.setText(Integer.toString(enemyy.getDamage()));
        label3.setText(Integer.toString(enemyy.getHealth()) + "/" + Integer.toString(enemyy.getMaxHealth()));
        return enemyy;
    }
    /**
     * Генерация босса
     * @param label Изображение противника
     * @param label2 Надпись с именем противника
     * @param text Надпись с величиной урона от атаки противника
     * @param label3 Надпись с количеством здоровья противника
     * @return вражеский персонаж
     */
    public Player chooseBoss(JLabel label, JLabel label2, JLabel text, JLabel label3, int i) {
        ImageIcon icon1 = null;
        icon1 = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("img/kao.jpeg")));
        label2.setText("Shao Kahn (босс)");
        enemyy = enemies[4];
        label.setIcon(icon1);
        text.setText(Integer.toString(enemyy.getDamage()));
        label3.setText(Integer.toString(enemyy.getHealth()) + "/" + Integer.toString(enemyy.getMaxHealth()));
        return enemyy;
    }
    /**
     * Выбор базовой стратегии врага в зависимости от его типа
     * @return вид стратегии врага
     */
    public int[] enemyBehavior(int k1, int k2, int k3, int k4, double i) {
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

    public int[] chooseBehavior(Player enemy, CharacterAction action) {
        int arr[] = null;
        double i = Math.random();
        if (enemy instanceof Baraka) {
            arr = action.enemyBehavior(15, 15, 60, 10, i);
        }
        if (enemy instanceof SubZero) {
            arr = action.enemyBehavior(25, 25, 0, 50, i);
        }
        if (enemy instanceof LiuKang) {
            arr = action.enemyBehavior(13, 13, 10, 64, i);
        }
        if (enemy instanceof SonyaBlade) {
            arr = action.enemyBehavior(25, 25, 50, 0, i);
        }
        if (enemy instanceof ShaoKahn) {
            arr = action.enemyBehavior(10, 45, 0, 45, i);
        }
        return arr;
    }
    /**
     * Обновление полосы здоровья игрока в пользовательском интерфейсе
     * @param player Персонаж
     * @param progress Полоса здоровья персонажа
     */
    public void hp(Player player, JProgressBar progress) {
        if (player.getHealth() >= 0) {
            progress.setValue(player.getHealth());
        } else {
            progress.setValue(0);
        }
    }

    /**
     * Выбор улучшения игрока
     * @return Выбранное улучшение
     */
    public int chooseUpgrade()
    {
        String[] options = {"Здоровье", "Урон"};
        int answer = JOptionPane.showOptionDialog(null, "Выбери, какой параметр улучшить", "Выбирай с умом!", 0,1,null, options, options[0]);
        return answer;
    }
    /**
     * Добавление очков и опыта за победу над врагом
     * @param human Игрок
     * @param enemyes противники
     */
    public void addPoints(Human human, Player[] enemyes) {
        switch (human.getLevel()) {
            case 0:
                human.addExperience(20);
                human.addPoints(25 + human.getHealth() / 4);
                break;
            case 1:
                human.addExperience(25);
                human.addPoints(30 + human.getHealth() / 4);
                break;
            case 2:
                human.addExperience(30);
                human.addPoints(35 + human.getHealth() / 4);
                break;
            case 3:
                human.addExperience(40);
                human.addPoints(45 + human.getHealth() / 4);
                break;
            case 4:
                human.addExperience(50);
                human.addPoints(55 + human.getHealth() / 4);
                break;
        }
        for (int i = 0; i < 5; i++) {
            if (experience_for_next_level[i] == human.getExperience()) {
                human.incrementLevel();
                human.setNextExperience(experience_for_next_level[i + 1]);
                int promotion = chooseUpgrade();
                newHealthHuman(human, promotion);
                for (int j = 0; j < 4; j++) {
                    newHealthEnemy(enemyes[j], human);
                }
            }
        }
    }
    /**
     * Добавление очков и опыта за победу над боссом
     * @param human Игрок
     * @param enemyes противники
     */
    public void addPointsBoss(Human human, Player[] enemyes) {
        switch (human.getLevel()) {
            case 2:
                human.addExperience(30);
                human.addPoints(45 + human.getHealth() / 2);
                break;
            case 4:
                human.addExperience(50);
                human.addPoints(65 + human.getHealth() / 2);
                break;
        }
        for (int i = 0; i < 5; i++) {
            if (experience_for_next_level[i] == human.getExperience()) {
                human.incrementLevel();
                human.setNextExperience(experience_for_next_level[i + 1]);
                int promotion = chooseUpgrade();
                newHealthHuman(human, promotion);
                for (int j = 0; j < 4; j++) {
                    newHealthEnemy(enemyes[j], human);
                }
            }
        }
    }
    /**
     * Добавление предметов в мешок
     */
    public void addItems(int k1, int k2, int k3, Items[] items) {
        double i = Math.random();
        if (i < k1 * 0.01) {
            items[0].setAmount(1);
        }
        if (i >= k1 * 0.01 & i < (k1 + k2) * 0.01) {
            items[1].setAmount(1);
        }
        if (i >= (k1 + k2) * 0.01 & i < (k1 + k2 + k3) * 0.01) {
            items[2].setAmount(1);
        }
    }
    /**
     * Результат выбора улучшения характеристики
     * @param human Игрок
     * @param chosen_option выбранное улучшение
     */
    public void newHealthHuman(Human human, int chosen_option) {
        int hp = 0;
        int damage = 0;
        switch (human.getLevel()) {
            case 1:
                hp = 25;
                damage = 3;
                break;
            case 2:
                hp = 30;
                damage = 3;
                break;
            case 3:
                hp = 30;
                damage = 4;
                break;
            case 4:
                hp = 40;
                damage = 6;
                break;
        }
        if (chosen_option == 0) {
            human.addMaxHealth(hp + (human.getLevel() + 1) * 5);
        } else {
            human.addDamage(damage +(human.getLevel() + 1) * 5);
        }
    }
    /**
     * Обновление характеристик противника при переходе на новый уровень
     * @param enemy Противник
     * @param human Игрок
     */
    public void newHealthEnemy(Player enemy, Human human) {
        int hp = 0;
        int damage = 0;
        switch (human.getLevel()) {
            case 1:
                hp = 32;
                damage = 25;
                break;
            case 2:
                hp = 30;
                damage = 20;
                break;
            case 3:
                hp = 23;
                damage = 24;
                break;
            case 4:
                hp = 25;
                damage = 26;
                break;
        }
        enemy.addMaxHealth((int) enemy.getMaxHealth() * hp / 100);
        enemy.addDamage((int) enemy.getDamage() * damage / 100);
        enemy.incrementLevel();
    }
    /**
     * Использование предмета из мешка
     * @param human Игрок
     * @param items Предметы в мешке
     * @param name Название кнопки
     * @param dialog Окно с сообщением о невозможности использовать выбранный предмет
     * @param dialog1 Окно с мешком предметов
     */
    public void useItem(Player human, Items[] items, String name, JDialog dialog, JDialog dialog1) {
        switch (name) {
            case "jRadioButton1":
                if (items[0].getAmount() > 0) {
                    human.addHealth((int) (human.getMaxHealth() * 0.25));
                    items[0].setAmount(-1);
                } else {
                    dialog.setVisible(true);
                    dialog.setBounds(300, 200, 400, 300);
                }
                break;
            case "jRadioButton2":
                if (items[1].getAmount() > 0) {
                    human.addHealth((int) (human.getMaxHealth() * 0.5));
                    items[1].setAmount(-1);
                } else {
                    dialog.setVisible(true);
                    dialog.setBounds(300, 200, 400, 300);
                }
                break;
            case "jRadioButton3":
                dialog.setVisible(true);
                dialog.setBounds(300, 200, 400, 300);
                break;
        }
        if(dialog.isVisible() == false){
            dialog1.dispose();
        }
    }
}