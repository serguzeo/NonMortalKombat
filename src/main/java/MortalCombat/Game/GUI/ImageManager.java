package MortalCombat.Game.GUI;

import lombok.Getter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Класс ImageManager управляет загрузкой, хранением и изменением размеров изображений,
 * используемых в графическом интерфейсе игры.
 */
public class ImageManager {
    @Getter
    private final Icon defaultIcon;

    /**
     * Конструктор ImageManager инициализирует менеджер изображений с иконкой по умолчанию.
     */
    public ImageManager() {
        this.defaultIcon = getIconFromResources("img/gamaz.jpg");
    }

    /**
     * Метод getIcon(String filename) загружает иконку из ресурсов или файловой системы.
     *
     * @param filename имя файла или путь к изображению.
     * @return загруженная иконка или иконка по умолчанию, если файл не найден.
     */
    public Icon getIcon(String filename) {
        Icon resourceIcon = getIconFromResources(filename);
        if (resourceIcon == null) {
            return getIconFromFile(filename);
        }
        return resourceIcon;
    }

    /**
     * Метод getIconFromFile(String filename) загружает иконку из файловой системы.
     *
     * @param filename имя файла или путь к изображению.
     * @return загруженная иконка или иконка по умолчанию, если файл не найден или произошла ошибка при загрузке.
     */
    public Icon getIconFromFile(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                return defaultIcon;
            }
            Image image = ImageIO.read(file);
            return resizeIcon(new ImageIcon(image), 182, 276);

        } catch (Exception e) {
            return defaultIcon;
        }
    }

    /**
     * Метод getIconFromResources(String resourceName) загружает иконку из ресурсов проекта.
     *
     * @param resourceName имя ресурса (путь к изображению в ресурсах).
     * @return загруженная иконка или null, если ресурс не найден или произошла ошибка при загрузке.
     */
    public Icon getIconFromResources(String resourceName) {
        try {
            java.net.URL resourceURL = getClass().getClassLoader().getResource(resourceName);
            Icon icon = new ImageIcon(resourceURL);
            return resizeIcon(icon, 182, 276);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Метод resizeIcon(Icon icon, int width, int height) изменяет размер переданной иконки.
     *
     * @param icon   иконка для изменения размера.
     * @param width  новая ширина иконки.
     * @param height новая высота иконки.
     * @return иконка с измененным размером или null, если переданная иконка равна null.
     */
    public Icon resizeIcon(Icon icon, int width, int height) {
        if (icon == null) {
            return null;
        }
        Image img = ((ImageIcon) icon).getImage();
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
