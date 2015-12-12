package nl.hu.coproco.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nl.hu.coproco.service.ImportService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Developed by Arjan.
 */
public class MainMenuController implements Initializable {

    @FXML private Button editorbutton;
    @FXML private Button selectorbutton;
    @FXML private Button exportbutton;
    @FXML private Button importbutton;
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

    @FXML private void openExporter() throws IOException {

        windowStage = (Stage) mainmenucontainer.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/exporterScene.fxml"));
        windowStage.setScene(new Scene(root));
    }

    @FXML private void importAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Export Files", "*")
        );
        File file = fileChooser.showOpenDialog(windowStage);

        // Import and show confirm alert
        Alert alert = null;
        if (ImportService.executeImport(file)) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Imported!!");
            alert.setContentText("Your export has been imported!");
        }else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Import failed!");
            alert.setContentText("Error with importing the patterns! Look at the console for more information.");
        }

        alert.showAndWait();
    }
}
