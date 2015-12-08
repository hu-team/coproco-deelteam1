package nl.hu.coproco.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Developed by Arjan.
 */
public class EditorController implements Initializable{

    @FXML private TextField namefield;
    @FXML private ComboBox scopebox;
    @FXML private ComboBox purposebox;
    @FXML private TextField problemfield;
    @FXML private TextField solutionfield;
    @FXML private TextField consequencesfield;

    @FXML private ImageView diagramfield;

    @FXML private Button browsebutton;
    @FXML private Button cancelbutton;
    @FXML private Button addbutton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert addbutton != null : "fx:id\"addbutton\" was not injected";

        addbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("TEST");
            }
        });
    }
}
