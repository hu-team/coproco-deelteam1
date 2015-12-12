package nl.hu.coproco.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nl.hu.coproco.service.ExportService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExporterController implements Initializable {

    @FXML private Button exportbutton;
    @FXML private Button cancelbutton;

    @FXML private ComboBox<String> exporttype;

    @FXML private VBox exportercontainer;

    private Parent root;
    private Stage windowStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exporttype.setItems(FXCollections.observableArrayList(ExportService.getAvailableTypes()));
    }


    @FXML public void onCancel(ActionEvent actionEvent) throws IOException {
        windowStage = (Stage) exportercontainer.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/mainMenu.fxml"));
        windowStage.setScene(new Scene(root));
    }

    @FXML public void onExport(ActionEvent actionEvent) {
        if (exporttype.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        File file = this.askForFileLocation(new FileChooser.ExtensionFilter(exporttype.getSelectionModel().getSelectedItem(), "*"));

        if (file != null) {
            ExportService.getExportForType(exporttype.getSelectionModel().getSelectedItem()).saveExport(file);
        }
    }

    private File askForFileLocation(FileChooser.ExtensionFilter... elements) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(elements);
        return fileChooser.showSaveDialog(windowStage);
    }
}
