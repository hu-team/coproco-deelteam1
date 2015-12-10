package nl.hu.coproco.domain;

import javafx.scene.image.Image;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProxyImage implements CachableImage {
    private DiskImage diskImage;
    private String encodedImage;

    public ProxyImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }

    public ProxyImage(BufferedImage bufferedImage) {
        this.convertToBase64(bufferedImage);
    }

    @Override
    public Image getDisplayableImage() {
        if (this.diskImage == null) {
            this.diskImage = new DiskImage(this.encodedImage);
        }
        return this.diskImage.getDisplayableImage();
    }

    @Override
    public String getEncodedImage() {
        return this.encodedImage;
    }

    private void convertToBase64(BufferedImage image) {

        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, "jpg", bos);
            byte[] imageBytes = bos.toByteArray();

            this.encodedImage = Base64.encodeBase64String(imageBytes);
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
