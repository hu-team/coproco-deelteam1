package nl.hu.coproco.domain;

import javafx.scene.image.Image;

public interface CachableImage {
    Image getDisplayableImage();
    String getFilePath();
}
