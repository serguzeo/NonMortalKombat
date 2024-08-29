package MortalCombat.Game.GUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Класс ChooseLocationsWindow представляет собой окно выбора параметров игры,
 * таких как количество локаций, никнейм игрока и иконка игрока.
 */
public class ChooseLocationsWindow extends JFrame {

    private final MainWindow mainWindow;

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

    /**
     * Конструктор ChooseLocationsWindow создает новое окно для выбора настроек игры.
     *
     * @param mainWindow главное окно приложения, к которому возвращаемся при нажатии кнопки "Назад".
     */
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

        chooseIconButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpeg", "jpg", "png"));

            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                playerIconPath = selectedFile.getAbsolutePath();
            }
        });

        backToMainWindowButton.addActionListener(e -> {
            setVisible(false);
            dispose();
            mainWindow.setVisible(true);
        });
    }

    /**
     * Проверяет введенное количество локаций и возвращает его, если оно валидно.
     *
     * @return Количество локаций, выбранное пользователем.
     * @throws IllegalArgumentException если введенное количество локаций неверно.
     */
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
}
