package nl.hu.coproco.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Optional;


public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("/mainMenu.fxml"));
        } catch(Exception e ) {
            e.printStackTrace();
            return;
        }

        primaryStage.setResizable(false);
        primaryStage.setTitle("CoProCo");

        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                // Don't allow closing it when not in other stage.

                // Ask for exit
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Exit application");
                alert.setContentText("Are you sure to exit the application and not export the patterns?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.CANCEL) {
                    event.consume();
                }
            }
        });
    }

    public void openWindow(String args[]) {
        launch(args);
    }

}
