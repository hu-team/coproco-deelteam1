package nl.hu.coproco.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import nl.hu.coproco.domain.Pattern;
import nl.hu.coproco.domain.Purpose;
import nl.hu.coproco.domain.Scope;
import nl.hu.coproco.service.PatternService;
import nl.hu.coproco.service.PurposeService;
import nl.hu.coproco.service.ScopeService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class SelectorController implements Initializable, Observer, EventHandler<ActionEvent> {

    @FXML private ComboBox<Scope> scopebox;
    @FXML private ComboBox<Purpose> purposebox;
    @FXML private ComboBox<Pattern> problembox;
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

        // Set empty combobox contents
        problembox.setItems(FXCollections.observableArrayList(new ArrayList<Pattern>()));

        // On Change events
        scopebox.setOnAction(this);
        purposebox.setOnAction(this);
        problembox.setOnAction(this);

        PatternService.setObserver(this);
    }

    @FXML private void selectProblem(){
        //TODO Alle fields moeten gevuld worden.
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

    @Override
    public void update(Observable o, Object arg) {
        // Update the combobox selectors
        System.out.println("TEST");
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource().equals(scopebox)) {
            this.problembox.getSelectionModel().clearSelection();
            this.reloadPurposeFilter();
            this.reloadProblemFilter();
        }

        if (event.getSource().equals(purposebox)) {
            this.problembox.getSelectionModel().clearSelection();
            this.reloadProblemFilter();
        }

        if (event.getSource().equals(problembox)) {
            this.loadProblemIntoDetailsPane();
        }
    }

    private void reloadPurposeFilter() {
        // Reload the purpose combobox, based on the scope box.

        // For now this will do nothing, because there is no relationship between scope and purpose.
    }

    private void reloadProblemFilter() {
        // Will reload the problems based on the selected scope and purpose

        // Get selections
        Purpose selectedPurpose = this.purposebox.getSelectionModel().getSelectedItem();
        Scope selectedScope = this.scopebox.getSelectionModel().getSelectedItem();

        // Get the filtered results from our service. And update the problem combobox
        this.problembox.setItems(FXCollections.observableArrayList(PatternService.getFilteredPatterns(selectedPurpose, selectedScope)));
    }

    private void loadProblemIntoDetailsPane() {
        // Load selected problem into gui window
        Pattern pattern = problembox.getSelectionModel().getSelectedItem();

        if (pattern == null) {
            this.namefield.setText("");
            this.solutionfield.setText("");
            this.consequencesfield.setText("");
            this.diagramview.setImage(null);
            return;
        }

        // Load into gui
        this.namefield.setText(pattern.getName());
        this.solutionfield.setText(pattern.getSolution());
        this.consequencesfield.setText(pattern.getConsequences());

        this.diagramview.setImage(pattern.getDiagram().getDisplayableImage());
    }
}
