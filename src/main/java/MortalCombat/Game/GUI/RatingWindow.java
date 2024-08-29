package MortalCombat.Game.GUI;

import MortalCombat.Game.Rating.Rating;
import MortalCombat.Game.Rating.RatingTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * Окно, в котором отображается таблица рейтингов игроков.
 */
public class RatingWindow extends JFrame {

    private final MainWindow mainWindow;

    private JPanel panel;
    private JButton backToMainWindowButton;
    private JTable ratingTable;

    /**
     * Конструктор RatingWindow создает новое окно для отображения таблицы рейтингов.
     *
     * @param mainWindow главное окно приложения, к которому можно вернуться из окна рейтингов.
     */
    public RatingWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        setContentPane(panel);
        setTitle("Nonmortal Kombat");
        setSize(500, 500);

        addListeners();
        fillRatingTable();

        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addListeners() {
        backToMainWindowButton.addActionListener(e -> {
            setVisible(false);
            dispose();
            mainWindow.setVisible(true);
        });
    }

    /**
     * Заполняет таблицу рейтингов данными из рейтингового файла.
     * Отображает место, никнейм, количество очков и дату игры каждого игрока.
     */
    private void fillRatingTable() {
        // Заголовки колонок
        String[] columnNames = {"#", "Ник", "Очки", "Дата"};

        // Форматтер для даты в формате "dd.MM.yyyy"
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        // Получаем данные рейтинга
        RatingTable ratingTableModel = new RatingTable();
        ratingTableModel.importRatings();  // Импортируем данные из файла
        List<Rating> ratings = ratingTableModel.getRatings();

        // Создаем модель таблицы
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Заполняем модель данными
        int place = 1;
        for (Rating rating : ratings) {
            String nickname = rating.getNickname();
            int score = rating.getScore();
            String date = rating.getDate().format(dateFormatter);

            Object[] row = {place, nickname, score, date};
            tableModel.addRow(row);
            place++;
        }

        ratingTable.setRowHeight(30);
        ratingTable.setModel(tableModel);
    }
}
