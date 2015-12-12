package nl.hu.coproco.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Developed by Arjan.
 */
public class MainMenuController implements Initializable {

    @FXML private Button editorbutton;
    @FXML private Button selectorbutton;
    @FXML private VBox mainmenucontainer;

    private Parent root;
    private Stage windowStage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML private void openEditor() throws IOException {

        windowStage = (Stage) mainmenucontainer.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/editorScene.fxml"));
        windowStage.setScene(new Scene(root));
    }

    @FXML private void openSelector() throws IOException {

        windowStage = (Stage) mainmenucontainer.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/selectorScene.fxml"));
        windowStage.setScene(new Scene(root));
    }
}
