package MortalCombat.Game.GUI;

import MortalCombat.Game.Game;

import javax.swing.*;
import java.awt.event.*;

public class ChooseUpDialog extends JDialog {
    private JPanel contentPane;
    private JButton healthButton;
    private JButton damageButton;
    private final Game game;
    private final GameWindow gameWindow;

    public ChooseUpDialog(GameWindow window, Game game) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(healthButton);

        this.game = game;
        this.gameWindow = window;

        healthButton.addActionListener(e -> upHealth());
        damageButton.addActionListener(e -> upDamage());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                upHealth();
            }
        });

        // call upHealth() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                upHealth();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void upHealth() {
        gameWindow.renderWindow(game.upPlayerHealth());
        dispose();
    }

    private void upDamage() {
        gameWindow.renderWindow(game.upPlayerDamage());
        dispose();
    }
}
