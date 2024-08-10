package MortalCombat.Game.GUI;

import lombok.Getter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ImageManager {
    @Getter
    private final Icon defaultIcon;
    private final int defaultWidth = 182;
    private final int defaultHeight = 276;

    public ImageManager() {
        Icon defaultIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("img/gamaz.jpg")));
        this.defaultIcon = resizeIcon(defaultIcon, defaultWidth, defaultHeight);
    }

    public Icon getIcon(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                return defaultIcon;
            }
            Image image = ImageIO.read(file);
            return resizeIcon(new ImageIcon(image), defaultWidth, defaultHeight);

        } catch (Exception e) {
            return defaultIcon;
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
