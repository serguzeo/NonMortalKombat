package MortalCombat.Game.GUI;

import javax.swing.*;

public class MainWindow extends JFrame {

    private JPanel mainPanel;
    private JButton showScoreTableButton;
    private JButton startGameButton;
    private JPanel buttonsPanel;

    public MainWindow() {
        setContentPane(mainPanel);
        setTitle("Nonmortal Kombat");
        setSize(500, 500);

        addListeners();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void addListeners() {
        startGameButton.addActionListener(e -> {
            setVisible(false);

            ChooseLocationsWindow chooseLocationsWindow = new ChooseLocationsWindow(this);
        });
    }
}
