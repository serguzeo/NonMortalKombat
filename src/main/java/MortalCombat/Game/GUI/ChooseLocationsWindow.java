package MortalCombat.Game.GUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ChooseLocationsWindow extends JFrame {

    private MainWindow mainWindow;

    private JPanel mainPanel;
    private JSpinner locationsNumberInput;
    private JButton startGameButton;
    private JLabel locationsNumberTip;
    private JButton backToMainWindowButton;
    private JTextField nicknameInput;
    private JLabel nicknameTip;
    private JButton chooseIconButton;

    private int locationsNumber;
    private String playerIconPath;

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
            nicknameTip.setText("");
            try {
                locationsNumber = getValidatedLocationsNumber();

                if (nicknameInput.getText().length() > 10) {
                    nicknameTip.setText("Никнейм слишком длинный! (10 max)");
                    return;
                }

                setVisible(false);
                dispose();

                new GameWindow(mainWindow, locationsNumber, nicknameInput.getText(), playerIconPath);

            } catch (IllegalArgumentException exception) {
                locationsNumberTip.setText(exception.getMessage());
            }
        });

        chooseIconButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpeg", "jpg", "png"));

                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    playerIconPath = selectedFile.getAbsolutePath();
                }
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
