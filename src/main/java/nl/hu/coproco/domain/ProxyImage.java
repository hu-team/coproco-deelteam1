package nl.hu.coproco.domain;

import javafx.scene.image.Image;

public class ProxyImage implements CachableImage {
    private DiskImage diskImage;
    private String filePath;

    public ProxyImage(String file) {
        this.filePath = file;
    }

    @Override
    public Image getDisplayableImage() {
        if (this.diskImage == null) {
            this.diskImage = new DiskImage(this.filePath);
        }
        return this.diskImage.getDisplayableImage();
    }
}
