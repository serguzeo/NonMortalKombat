package MortalCombat.Game.GUI;


import MortalCombat.Game.Combatant.CombatantAction;
import MortalCombat.Game.Dto.CombatantDto;
import MortalCombat.Game.Dto.GameInfoDto;
import MortalCombat.Game.Game;
import MortalCombat.Game.Dto.GameMessageDto;
import MortalCombat.Game.GameState.Status;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSpinnerUI;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Класс GameWindow представляет игровое окно, в котором происходит взаимодействие пользователя с игрой.
 * Окно включает в себя панель информации об игроке, панель врага, панель управления, а также игровую панель,
 * отображающую текущее состояние игры и интерфейс управления.
 */
public class GameWindow extends JFrame {
    private final MainWindow mainWindow;
    private final ImageManager imageManager = new ImageManager();
    private final Game game;

    private JPanel mainPanel;
    private JPanel gamePanel;
    private JPanel enemyPanel;
    private JPanel gameInfoPanel;
    private JPanel playerPanel;
    private JPanel triggersPanel;

    private JButton inventoryButton;
    private JButton weakenButton;
    private JButton defendButton;
    private JButton attackButton;

    private JLabel playerLabel;
    private JLabel enemyLabel;
    private JLabel enemyImage;
    private JLabel playerImage;
    private JLabel enemyLevelLabel;
    private JLabel playerLevelLabel;

    private JProgressBar enemyHPprogressBar;
    private JProgressBar playerHPprogressBar;
    private JLabel enemyDamageLabel;
    private JLabel playerDamageLabel;
    private JLabel scoreLabel;
    private JLabel XPLabel;
    private JLabel locationLabel;
    private JLabel enemiesLeftLabel;
    private JLabel messageLabel;
    private JCheckBox enemyIsStunned;
    private JCheckBox playerIsStunned;
    private JSpinner enemyWeakenFor;
    private JSpinner playerWeakenFor;
    private JLabel stepLabel;


    /**
     * Конструктор GameWindow инициализирует игровое окно, включая его компоненты и слушателей событий.
     *
     * @param mainWindow      главное окно приложения, к которому можно вернуться после завершения игры.
     * @param locationNumber  количество локаций в игре.
     * @param playerName      имя игрока.
     * @param playerIconPath  путь к иконке игрока.
     */
    public GameWindow(MainWindow mainWindow, int locationNumber, String playerName, String playerIconPath) {
        this.mainWindow = mainWindow;

        setContentPane(mainPanel);
        setTitle("Nonmortal Kombat");
        setSize(950, 600);

        game = new Game();

        renderWindow(game.startGame(locationNumber, playerName, playerIconPath));

        addListeners();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });

        configureSpinners();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    private void addListeners() {
        attackButton.addActionListener(e -> renderWindow(game.processUserAction(CombatantAction.ATTACK)));
        weakenButton.addActionListener(e -> renderWindow(game.processUserAction(CombatantAction.WEAKEN)));
        defendButton.addActionListener(e -> renderWindow(game.processUserAction(CombatantAction.DEFEND)));
        inventoryButton.addActionListener(e -> {
            ItemBagDialog itemBagDialog = new ItemBagDialog(this, game);
            itemBagDialog.pack();
            itemBagDialog.setLocationRelativeTo(null);
            itemBagDialog.setVisible(true);
        });
    }

    /**
     * Обновляет интерфейс игрового окна в соответствии с текущим состоянием игры.
     *
     * @param gameMessageDto DTO, содержащий информацию о текущем состоянии игры.
     */
    protected void renderWindow(GameMessageDto gameMessageDto) {
        handleAlert(gameMessageDto.getGameInfo().getStatus(), gameMessageDto.getGameInfo().getAlert());
        renderGameInfo(gameMessageDto.getGameInfo());

        if (gameMessageDto.getGameInfo().getStatus() != Status.LEVEL_UP) {
            renderCombatantInfo(
                    gameMessageDto.getPlayer(),
                    playerLabel,
                    playerHPprogressBar,
                    playerLevelLabel,
                    playerDamageLabel,
                    playerImage,
                    playerIsStunned,
                    playerWeakenFor
            );
            renderCombatantInfo(
                    gameMessageDto.getEnemy(),
                    enemyLabel,
                    enemyHPprogressBar,
                    enemyLevelLabel,
                    enemyDamageLabel,
                    enemyImage,
                    enemyIsStunned,
                    enemyWeakenFor
            );
        }
    }

    /**
     * Отображает информацию о текущем состоянии игры на панели информации.
     *
     * @param gameInfoDto DTO, содержащий данные о текущем состоянии игры.
     */
    private void renderGameInfo(GameInfoDto gameInfoDto) {
        stepLabel.setText(gameInfoDto.isPlayerSteps() ? "⟽" : "⟾");
        scoreLabel.setText(String.valueOf(gameInfoDto.getScore()));
        XPLabel.setText(gameInfoDto.getXP() + "/" + gameInfoDto.getRequiredXP());
        locationLabel.setText("Локация: " + gameInfoDto.getCurrentLocation() + "/" + gameInfoDto.getTotalLocations());
        if (gameInfoDto.getEnemiesLeft() == 0) {
        enemiesLeftLabel.setText("<html><br><b><font color='red'>!!!</font>" +
                                              " БИТВА С БОССОМ " +
                                              "<font color='red'>!!!</font></b></html>"
        );
        } else {
            enemiesLeftLabel.setText("Осталось противников: " + gameInfoDto.getEnemiesLeft());
        }
        messageLabel.setText(gameInfoDto.getMessage());
    }

    /**
     * Обрабатывает различные типы оповещений и уведомлений в игре.
     *
     * @param status статус игры, требующий особого внимания.
     * @param alert  текст уведомления, если имеется.
     */
    private void handleAlert(Status status, String alert) {
        if (status == null) {
            return;
        }
        switch (status) {
            case LOSE -> {
                JOptionPane.showMessageDialog(null, status.getMessage(), "Проигрыш", JOptionPane.ERROR_MESSAGE);
                exit();
            }
            case LEVEL_UP -> {
                ChooseUpDialog dialog = new ChooseUpDialog(this, game);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
            case ALERT -> {
                if (alert != null)
                    JOptionPane.showMessageDialog(null,
                            alert, "Уведомление", JOptionPane.INFORMATION_MESSAGE
                    );
            }
            case JUST_VICTORY, TOP_VICTORY -> {
                JOptionPane.showMessageDialog(null, status.getMessage(), "Победа!", JOptionPane.INFORMATION_MESSAGE);
                exit();
            }
            default -> {}
        }
    }

    /**
     * Отображает информацию о состоянии бойцов в игре.
     *
     * @param combatantDto DTO, содержащий информацию о бойце.
     * @param label        метка, отображающая имя бойца.
     * @param progressBar  прогресс-бар, отображающий уровень здоровья бойца.
     * @param levelLabel   метка, отображающая уровень бойца.
     * @param damageLabel  метка, отображающая урон бойца.
     * @param image        метка, отображающая изображение бойца.
     * @param isStunned    чекбокс, отображающий состояние оглушения бойца.
     * @param weakenFor    спиннер, отображающий продолжительность ослабления бойца.
     */
    private void renderCombatantInfo(
            CombatantDto combatantDto,
            JLabel label,
            JProgressBar progressBar,
            JLabel levelLabel,
            JLabel damageLabel,
            JLabel image,
            JCheckBox isStunned,
            JSpinner weakenFor
    ) {
        label.setText(combatantDto.getName());
        progressBar.setValue(Math.round((float) combatantDto.getHealth() / (float) combatantDto.getMaxHealth() * 100));
        progressBar.setString(combatantDto.getHealth() + " / " + combatantDto.getMaxHealth());
        levelLabel.setText(combatantDto.getLevel() + " LVL");
        damageLabel.setText("Урон: " + combatantDto.getDamage());
        image.setIcon(imageManager.getIcon(combatantDto.getIconPath()));
        isStunned.setSelected(combatantDto.isStunned());
        weakenFor.setValue(combatantDto.getWeakenFor());
    }

    /**
     * Настраивает спиннеры, чтобы убрать кнопки увеличения и уменьшения.
     */
    private void configureSpinners() {
        enemyWeakenFor.setUI(new BasicSpinnerUI() {
            @Override
            protected Component createNextButton() {
                return null;
            }

            @Override
            protected Component createPreviousButton() {
                return null;
            }
        });
        playerWeakenFor.setUI(new BasicSpinnerUI() {
            @Override
            protected Component createNextButton() {
                return null;
            }

            @Override
            protected Component createPreviousButton() {
                return null;
            }
        });
    }

    private void exit() {
        setVisible(false);
        dispose();
        mainWindow.setVisible(true);
    }
}
