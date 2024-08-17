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

public class RatingTable {
    private final SortedSet<Rating> ratings = new TreeSet<>(Comparator.comparingInt(Rating::getScore).reversed());
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public List<Rating> getRatings() {
        return new ArrayList<>(ratings);
    }

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

    private void createHeaderRow(Sheet sheet) {
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("#");
        header.createCell(1).setCellValue("Ник");
        header.createCell(2).setCellValue("Очки");
        header.createCell(3).setCellValue("Дата");
    }

    private void writeRatingToRow(Row row, Rating rating, int place) {
        row.createCell(0).setCellValue(place);
        row.createCell(1).setCellValue(rating.getNickname());
        row.createCell(2).setCellValue(rating.getScore());
        row.createCell(3).setCellValue(rating.getDate().format(formatter));
    }

    private Rating readRatingFromRow(Row row) {
        String nickname = row.getCell(1).getStringCellValue();
        int score = (int) row.getCell(2).getNumericCellValue();
        LocalDate date = LocalDate.parse(row.getCell(3).getStringCellValue(), formatter);

        return new Rating(nickname, score, date);
    }
}
