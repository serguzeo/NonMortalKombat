package MortalCombat.Game.GUI;

import lombok.Getter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;

public class ImageManager {
    @Getter
    private final Icon defaultIcon;

    public ImageManager() {
        this.defaultIcon = getIconFromResources("img/gamaz.jpg");
    }

    public Icon getIcon(String filename) {
        Icon resourceIcon = getIconFromResources(filename);
        if (resourceIcon == null) {
            return getIconFromFile(filename);
        }
        return resourceIcon;
    }

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

    public Icon getIconFromResources(String resourceName) {
        try {
            java.net.URL resourceURL = getClass().getClassLoader().getResource(resourceName);
            Icon icon = new ImageIcon(resourceURL);
            return resizeIcon(icon, 182, 276);
        } catch (Exception e) {
            return null;
        }
    }

    public Icon resizeIcon(Icon icon, int width, int height) {
        if (icon == null) {
            return null;
        }
        Image img = ((ImageIcon) icon).getImage();
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
