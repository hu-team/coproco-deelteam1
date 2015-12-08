package nl.hu.coproco.domain;

import javafx.scene.image.Image;

public class DiskImage implements CachableImage {
    private String filePath;

    private Image image;

    public DiskImage(String file) {
        this.filePath = file;
        this.loadFromDisk(file);
    }

    @Override
    public Image getDisplayableImage() {
        return this.image;
    }

    private void loadFromDisk(String file) {
        this.image = new Image(file);
    }
}
