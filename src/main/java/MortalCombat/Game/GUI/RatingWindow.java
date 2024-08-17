package MortalCombat.Game.GUI;

import MortalCombat.Game.Rating.Rating;
import MortalCombat.Game.Rating.RatingTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static MortalCombat.Game.Constants.RESULTS_PATH;

public class RatingWindow extends JFrame {

    private MainWindow mainWindow;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private JPanel panel;
    private JButton backToMainWindowButton;
    private JTable ratingTable;

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

    public static void main(String[] args) {
        RatingWindow window = new RatingWindow(new MainWindow());
    }
}
