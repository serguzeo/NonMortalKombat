package MKRefactor.Handlers;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImageFixer
{
    public ImageIcon getScaledImage(Image srcImg)
    {
        ImageIcon resizedImg = new ImageIcon(srcImg.getScaledInstance(300, 250,  java.awt.Image.SCALE_SMOOTH));
        return  resizedImg;
    }
}
