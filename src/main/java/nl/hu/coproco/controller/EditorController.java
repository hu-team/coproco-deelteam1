package nl.hu.coproco.controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class EditorController implements Initializable{

    @FXML private TextField namefield;
    @FXML private ComboBox scopebox;
    @FXML private ComboBox purposebox;
    @FXML private TextField problemfield;
    @FXML private TextField solutionfield;
    @FXML private TextField consequencesfield;

    @FXML private ImageView diagramfield;
    @FXML private Pane imagepane;

    @FXML private Button browsebutton;
    @FXML private Button cancelbutton;
    @FXML private Button addbutton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
            System.out.println("IT FUCKING WORKED!");
        }
    }

    @FXML private void browsePicture(){

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter filterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter filterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(filterJPG, filterPNG);

        File file = fileChooser.showOpenDialog(null);

        try {

            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            diagramfield.setImage(image);
            diagramfield.setFitWidth(imagepane.getWidth());
            diagramfield.setPreserveRatio(true);

        }catch (Exception e) {
            System.out.println(e);
        }

    }
}
