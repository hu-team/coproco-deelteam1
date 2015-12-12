package nl.hu.coproco.domain;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

public class DiskImage implements CachableImage {
    private String encodedImage;

    private Image image;

    public DiskImage(String encodedImage) {
        this.encodedImage = encodedImage;
        this.decodeImage(encodedImage);
    }

    @Override
    public Image getDisplayableImage() {
        return this.image;
    }

    @Override
    public String getEncodedImage() {
        return this.encodedImage;
    }

    private void decodeImage(String base64Image) {
        BufferedImage bufferedImage = null;

        byte[] imageByte;

        try {
            imageByte = Base64.decodeBase64(base64Image);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            bufferedImage = ImageIO.read(bis);
            bis.close();

            this.image = SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
