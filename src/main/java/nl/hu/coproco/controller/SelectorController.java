package nl.hu.coproco.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
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

    @FXML private VBox selectorcontainer;

    private Parent root;
    private Stage windowStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML private void openMainMenu() throws IOException {
        windowStage = (Stage) selectorcontainer.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/mainMenu.fxml"));
        windowStage.setScene(new Scene(root));
    }
}
