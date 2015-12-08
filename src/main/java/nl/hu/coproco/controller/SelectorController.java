package nl.hu.coproco.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Developed by Arjan.
 */
public class SelectorController implements Initializable {

    @FXML private ComboBox scopebox;
    @FXML private ComboBox purposebox;
    @FXML private ComboBox problembox;
    @FXML private Label namefield;
    @FXML private Label solutionfield;
    @FXML private Label consequencesfield;
    @FXML private ImageView diagramview;

    @FXML private Button cancelbutton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
