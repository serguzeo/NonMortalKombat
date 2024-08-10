package MortalCombat.Game.GUI;


import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class GameWindow extends JFrame {

    private MainWindow mainWindow;


    private JPanel mainPanel;
    private JPanel gamePanel;
    private JPanel enemyPanel;
    private JPanel gameInfoPanel;
    private JPanel playerPanel;
    private JPanel triggersPanel;

    private JButton inventoryButton;
    private JButton debuffButton;
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

    public GameWindow(MainWindow mainWindow, int locationNumber) {
        this.mainWindow = mainWindow;

        setContentPane(mainPanel);
        setTitle("Nonmortal Kombat");
        setSize(800, 600);


//        addListeners();

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

    }


}
