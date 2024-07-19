package MortalCombat.GUI;

import javax.swing.*;

public class GameWindow extends JFrame {

    private MainWindow mainWindow;

    private JPanel mainPanel;

    public GameWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        setContentPane(mainPanel);
        setTitle("Nonmortal Kombat");
        setSize(500, 500);

//        addListeners();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addListeners() {

    }


}
