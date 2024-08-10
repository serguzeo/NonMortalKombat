package MortalCombat.Game.GUI;
import javax.swing.*;

public class ChooseLocationsWindow extends JFrame {

    private MainWindow mainWindow;

    private JPanel mainPanel;
    private JSpinner locationsNumberInput;
    private JButton startGameButton;
    private JLabel locationsNumberTip;
    private JButton backToMainWindowButton;
    private int locationsNumber;

    public ChooseLocationsWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

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
            locationsNumberTip.setText("");
            try {
                locationsNumber = getValidatedLocationsNumber();

                setVisible(false);
                dispose();
                new GameWindow(mainWindow, locationsNumber);

            } catch (IllegalArgumentException exception) {
                locationsNumberTip.setText(exception.getMessage());
            }
        });

        backToMainWindowButton.addActionListener(e -> {
            setVisible(false);
            dispose();
            mainWindow.setVisible(true);
        });
    }

    private int getValidatedLocationsNumber() throws IllegalArgumentException {
        if (locationsNumberInput.getValue() == null) {
            throw new IllegalArgumentException("Необходимо указать число!");
        }

        int locationsNumber = Integer.parseInt(locationsNumberInput.getValue().toString());
        if (locationsNumber < 1 || locationsNumber > 20) {
            throw new IllegalArgumentException("Неверное число локаций!");
        }

        return locationsNumber;
    }

    public static void main(String[] args) {
        ChooseLocationsWindow window = new ChooseLocationsWindow(new MainWindow());
    }
}
