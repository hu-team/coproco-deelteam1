package nl.hu.coproco.controller;

import javafx.collections.FXCollections;
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
import nl.hu.coproco.domain.Purpose;
import nl.hu.coproco.domain.Scope;
import nl.hu.coproco.service.PatternService;
import nl.hu.coproco.service.PurposeService;
import nl.hu.coproco.service.ScopeService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectorController implements Initializable {

    @FXML private ComboBox<Scope> scopebox;
    @FXML private ComboBox<Purpose> purposebox;
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
        scopebox.setItems(FXCollections.observableArrayList(ScopeService.getAllScopes()));
        purposebox.setItems(FXCollections.observableArrayList(PurposeService.getAllPurposes()));
    }

    @FXML private void filterProblem() {
        problembox.getSelectionModel().clearSelection();
        problembox.getItems().clear();

        if(purposebox.getSelectionModel().getSelectedItem() == null ||
                scopebox.getSelectionModel().getSelectedItem() == null){
            return;
        }

        problembox.setItems(FXCollections
                .observableArrayList(PatternService
                        .getFilteredPatterns(
                                purposebox.getSelectionModel().getSelectedItem(),
                                scopebox.getSelectionModel().getSelectedItem())
                )
        );
    }

    @FXML private void openMainMenu() throws IOException {
        windowStage = (Stage) selectorcontainer.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/mainMenu.fxml"));
        windowStage.setScene(new Scene(root));
    }

}
