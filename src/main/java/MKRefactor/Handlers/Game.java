/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MKRefactor.Handlers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import MKRefactor.Game.Human;
import MKRefactor.Game.Player;
import MKRefactor.Game.Result;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Мария
 */
public class Game {
    public CharacterAction action = new CharacterAction();
    public ChangeTexts change = new ChangeTexts();
    public Fight fight = new Fight();
    private int locations_number;
    private ArrayList<Result> results = new ArrayList<>();
    /**
     * Создание противника и установка начальных параметров
     * @return Противник
     */
    public Player newEnemy(JLabel L1, JLabel L2, JLabel L3, JLabel L4, JProgressBar pr2) {
        action.setEnemies();
        Player enemy = action.chooseEnemy(L1, L2, L3, L4);
        action.hp(enemy, pr2);
        pr2.setMaximum(enemy.getMaxHealth());
        return enemy;
    }
    /**
     * Создание персонажа игрока
     * @param locations заданное кол-во локаций в игре
     * @return Персонаж игрока
     */
    public Human newHuman(JProgressBar pr1, int locations){
        Human human = new Human (0,80,16,1, locations);
        action.hp(human, pr1);
        pr1.setMaximum(human.getMaxHealth());
        return human;
    }
    /**
     * Добавление результатов в таблицу рекордов в конце успешной игры
     * @throws IOException
     */
    public void endGameTop(Human human, JTextField text, JTable table) throws IOException {
        results.add(new Result(text.getText(), human.getPoints()));
        results.sort(Comparator.comparing(Result::getPoints).reversed());
        writeToTable(table);
        try {
            writeToExcel();
        } catch (URISyntaxException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Запись результатов в файл
     * @throws IOException
     */
    public void writeToExcel() throws IOException, URISyntaxException{
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("Результаты ТОП-10");
        XSSFRow r = sheet.createRow(0);
        r.createCell(0).setCellValue("№");
        r.createCell(1).setCellValue("Имя");
        r.createCell(2).setCellValue("Количество баллов");
        for (int i = 0; i < results.size(); i++){
            if (i < 10){
                XSSFRow r2 = sheet.createRow(i + 1);
                r2.createCell(0).setCellValue(i + 1);
                r2.createCell(1).setCellValue(results.get(i).getName());
                r2.createCell(2).setCellValue(results.get(i).getPoints());
            }
        }
        //InputStream inputStream = Game.class.getClassLoader().getResourceAsStream("ResultsTable.xlsx");
        //URL url = this.getClass().getResource("/ResultsTable.xlsx");
        //File f = new File(url.toURI());
        // File file = new File("./ResultsTable.xlsx");
        //FileUtils.copyToFile(inputStream, f);
        System.out.println("1");
        File file = new File("./Results.xlsx");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        book.write(fileOutputStream);
        book.close();
    }

    public ArrayList<Result> getResults(){
        return this.results;
    }
    /**
     * Чтение результатов из файла
     * @throws IOException
     */
    public void readFromExcel() throws IOException {
        URL url = getClass().getClassLoader().getResource("Results.xlsx");
        XSSFWorkbook book = new XSSFWorkbook(url.openStream());

        XSSFSheet sh = book.getSheetAt(0);
        for (int i = 1; i <= sh.getLastRowNum(); i++) {
            results.add(
                    new Result(sh.getRow(i).getCell(1).getStringCellValue(),
                            (int)sh.getRow(i).getCell(2).getNumericCellValue()));
        }
        book.close();
    }
    /**
     * Запись результатов в таблицу пользовательского интерфейса
     */
    public void writeToTable(JTable table){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        for (int i = 0; i < results.size(); i++){
            if (i<10){
                model.setValueAt(results.get(i).getName(), i, 0);
                model.setValueAt(results.get(i).getPoints(), i, 1);
            }
        }
    }

    public void setLocations_number(int locations_number) {
        this.locations_number = locations_number;
    }
}