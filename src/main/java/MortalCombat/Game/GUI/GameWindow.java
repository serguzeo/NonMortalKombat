package MortalCombat.Game.GUI;


import MortalCombat.Game.Combatant.CombatantAction;
import MortalCombat.Game.Game;
import MortalCombat.Game.Dto.GameMessageDto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class GameWindow extends JFrame {

    private final Game game;

    private MainWindow mainWindow;

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

    private final ImageManager imageManager = new ImageManager();


    public GameWindow(MainWindow mainWindow, int locationNumber, String playerName, String playerIconPath) {
        this.mainWindow = mainWindow;

        setContentPane(mainPanel);
        setTitle("Nonmortal Kombat");
        setSize(800, 600);

        game = new Game();

        renderWindow(game.startGame(locationNumber, playerName, playerIconPath));

        addListeners();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
                mainWindow.setVisible(true);
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    private void addListeners() {
        attackButton.addActionListener(e -> renderWindow(game.processUserAction(CombatantAction.ATTACK)));
        weakenButton.addActionListener(e -> renderWindow(game.processUserAction(CombatantAction.WEAKEN)));
        defendButton.addActionListener(e -> renderWindow(game.processUserAction(CombatantAction.DEFEND)));
    }

    private void renderWindow(GameMessageDto gameMessageDto) {
        scoreLabel.setText(gameMessageDto.getScore());
        XPLabel.setText(gameMessageDto.getXP());
        locationLabel.setText(gameMessageDto.getLocation());
        enemiesLeftLabel.setText(gameMessageDto.getEnemiesLeft());
        messageLabel.setText(gameMessageDto.getMessage());

        playerLabel.setText(gameMessageDto.getPlayerName());
        playerHPprogressBar.setValue(gameMessageDto.getPlayerProgressBarValue());
        playerHPprogressBar.setString(gameMessageDto.getPlayerProgressBarString());
        playerLevelLabel.setText(gameMessageDto.getPlayerLevel());
        playerDamageLabel.setText(gameMessageDto.getPlayerDamage());
        playerImage.setIcon(imageManager.getIconFromFile(gameMessageDto.getPlayerIconPath()));

        enemyLabel.setText(gameMessageDto.getEnemyName());
        enemyHPprogressBar.setValue(gameMessageDto.getEnemyProgressBarValue());
        enemyHPprogressBar.setString(gameMessageDto.getEnemyProgressBarString());
        enemyLevelLabel.setText(gameMessageDto.getEnemyLevel());
        enemyDamageLabel.setText(gameMessageDto.getEnemyDamage());
        enemyImage.setIcon(imageManager.getIconFromResources(gameMessageDto.getEnemyIconPath()));
    }
}
