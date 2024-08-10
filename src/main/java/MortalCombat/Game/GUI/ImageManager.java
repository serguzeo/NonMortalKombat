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

    public Icon getIconFromFile(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                return defaultIcon;
            }
            Image image = ImageIO.read(file);
            return resizeIcon(new ImageIcon(image));

        } catch (Exception e) {
            return defaultIcon;
        }
    }

    public Icon getIconFromResources(String resourceName) {
        Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(resourceName)));
        return resizeIcon(icon);
    }

    private Icon resizeIcon(Icon icon) {
        if (icon == null) {
            return null;
        }
        Image img = ((ImageIcon) icon).getImage();
        Image resizedImage = img.getScaledInstance(182, 276, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
