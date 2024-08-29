package MortalCombat.Game.Rating;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static MortalCombat.Game.Constants.RESULTS_PATH;

/**
 * Класс RatingTable управляет рейтингами игроков,
 * предоставляя методы для добавления, экспорта и импорта данных о рейтингах.
 */
public class RatingTable {
    private final SortedSet<Rating> ratings = new TreeSet<>(Comparator.comparingInt(Rating::getScore).reversed());
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Возвращает список рейтингов в виде List<Rating>, отсортированный по очкам в порядке убывания.
     *
     * @return список объектов Rating.
     */
    public List<Rating> getRatings() {
        return new ArrayList<>(ratings);
    }

    /**
     * Регистрирует новый рейтинг игрока. Если игрок с таким ником уже существует в таблице и
     * новый рейтинг выше, старый заменяется.
     *
     * @param rating объект Rating, содержащий информацию о новом рейтинге игрока.
     * @return позиция нового рейтинга в таблице или -1, если рейтинг не был обновлен.
     */
    public int registerRating(Rating rating) {
        Rating existingRating = ratings.stream()
                .filter(r -> r.getNickname().equals(rating.getNickname()))
                .findFirst()
                .orElse(null);

        if (existingRating != null) {
            if (rating.getScore() > existingRating.getScore()) {
                ratings.remove(existingRating);
                ratings.add(rating);
            } else {
                return -1;
            }
        } else {
            ratings.add(rating);
        }

        int index = new ArrayList<>(ratings).indexOf(rating);
        exportRatings(new File(RESULTS_PATH));
        return index + 1;
    }

    /**
     * Импортирует рейтинги из файла, расположенного по пути, определенному в RESULTS_PATH.
     * Если файл не существует, создает новый файл и экспортирует текущие рейтинги.
     */
    public void importRatings() {
        File file = new File(RESULTS_PATH);
        if (!file.exists()) {
            exportRatings(file);
        }
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            ratings.clear();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    ratings.add(readRatingFromRow(row));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Экспортирует текущие рейтинги в файл Excel.
     *
     * @param file файл, в который будут экспортированы данные.
     */
    private void exportRatings(File file) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Results");
            createHeaderRow(sheet);
            int i = 0;
            for (Rating rating : ratings) {
                writeRatingToRow(sheet.createRow(++i), rating, i);
            }
            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Создает заголовочную строку в таблице Excel.
     *
     * @param sheet лист Excel, где создается заголовочная строка.
     */
    private void createHeaderRow(Sheet sheet) {
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("#");
        header.createCell(1).setCellValue("Ник");
        header.createCell(2).setCellValue("Очки");
        header.createCell(3).setCellValue("Дата");
    }

    /**
     * Записывает данные рейтинга в строку таблицы Excel.
     *
     * @param row строка, в которую записываются данные.
     * @param rating объект Rating с данными игрока.
     * @param place место игрока в рейтинге.
     */
    private void writeRatingToRow(Row row, Rating rating, int place) {
        row.createCell(0).setCellValue(place);
        row.createCell(1).setCellValue(rating.getNickname());
        row.createCell(2).setCellValue(rating.getScore());
        row.createCell(3).setCellValue(rating.getDate().format(formatter));
    }

    /**
     * Считывает данные рейтинга из строки таблицы Excel.
     *
     * @param row строка Excel, из которой считываются данные.
     * @return объект Rating с данными, считанными из строки.
     */
    private Rating readRatingFromRow(Row row) {
        String nickname = row.getCell(1).getStringCellValue();
        int score = (int) row.getCell(2).getNumericCellValue();
        LocalDate date = LocalDate.parse(row.getCell(3).getStringCellValue(), formatter);

        return new Rating(nickname, score, date);
    }
}
