package nl.hu.coproco.controller;

import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nl.hu.coproco.domain.Pattern;
import nl.hu.coproco.domain.ProxyImage;
import nl.hu.coproco.domain.Purpose;
import nl.hu.coproco.domain.Scope;
import nl.hu.coproco.service.PatternService;
import nl.hu.coproco.service.PurposeService;
import nl.hu.coproco.service.ScopeService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditorController implements Initializable{

    @FXML private TextField namefield;
    @FXML private ComboBox<Scope> scopebox;
    @FXML private ComboBox<Purpose> purposebox;
    @FXML private TextField problemfield;
    @FXML private TextField solutionfield;
    @FXML private TextField consequencesfield;

    @FXML private ImageView diagramfield;
    @FXML private Pane imagepane;

    @FXML private Button browsebutton;
    @FXML private Button cancelbutton;
    @FXML private Button addbutton;

    @FXML private VBox editorcontainer;

    private Parent root;
    private Stage windowStage;

    private BufferedImage image;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scopebox.setItems(FXCollections.observableArrayList(ScopeService.getAllScopes()));
        purposebox.setItems(FXCollections.observableArrayList(PurposeService.getAllPurposes()));
    }

    @FXML private void addPatern(ActionEvent event){

        if(namefield.getText().isEmpty() || namefield == null || scopebox.getValue() == null || purposebox.getValue() == null ||
                problemfield.getText().isEmpty() || problemfield == null || solutionfield.getText().isEmpty() ||
                solutionfield == null || consequencesfield.getText().isEmpty() || consequencesfield == null ||
                diagramfield.getImage() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid");
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();

        }else{
            Pattern newPattern = new Pattern(namefield.getText());
            newPattern.setScope(new Scope(scopebox.getValue().toString()));
            newPattern.setPurpose(new Purpose(purposebox.getValue().toString()));
            newPattern.setConsequences(consequencesfield.getText());
            newPattern.setProblem(problemfield.getText());
            newPattern.setSolution(solutionfield.getText());

            ProxyImage image = new ProxyImage(this.image);
            newPattern.setDiagram(image);

            PatternService.addPattern(newPattern);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Added!");
            alert.setContentText("Pattern has been added!");
            alert.showAndWait();
        }
    }

    @FXML private void browsePicture(){

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        File file = fileChooser.showOpenDialog(null);

        try {
            this.image = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(this.image, null);
            diagramfield.setImage(image);
            diagramfield.setFitWidth(imagepane.getWidth());
            diagramfield.setPreserveRatio(true);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML private void openMainMenu() throws IOException {
        windowStage = (Stage) editorcontainer.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/mainMenu.fxml"));
        windowStage.setScene(new Scene(root));
    }
}
