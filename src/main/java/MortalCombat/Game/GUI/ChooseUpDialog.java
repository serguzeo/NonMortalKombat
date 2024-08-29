package MortalCombat.Game.GUI;

import MortalCombat.Game.Game;

import javax.swing.*;
import java.awt.event.*;


/**
 * Класс ChooseUpDialog представляет диалоговое окно, в котором игрок может выбрать улучшение для своего персонажа.
 * Игрок может выбрать между увеличением здоровья и увеличением урона.
 */
public class ChooseUpDialog extends JDialog {
    private JPanel contentPane;
    private JButton healthButton;
    private JButton damageButton;
    private final Game game;
    private final GameWindow gameWindow;

    /**
     * Конструктор ChooseUpDialog инициализирует диалоговое окно для выбора улучшений.
     *
     * @param window окно игры, которое вызвало этот диалог.
     * @param game   текущий объект игры.
     */
    public ChooseUpDialog(GameWindow window, Game game) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(healthButton);

        this.game = game;
        this.gameWindow = window;

        healthButton.addActionListener(e -> upHealth());
        damageButton.addActionListener(e -> upDamage());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                upHealth();
            }
        });

        contentPane.registerKeyboardAction(e -> upHealth(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Метод upHealth() вызывается, когда игрок выбирает увеличение здоровья.
     * Он обновляет состояние игры и закрывает диалоговое окно.
     */
    private void upHealth() {
        gameWindow.renderWindow(game.upPlayerHealth());
        dispose();
    }

    /**
     * Метод upDamage() вызывается, когда игрок выбирает увеличение урона.
     * Он обновляет состояние игры и закрывает диалоговое окно.
     */
    private void upDamage() {
        gameWindow.renderWindow(game.upPlayerDamage());
        dispose();
    }
}
