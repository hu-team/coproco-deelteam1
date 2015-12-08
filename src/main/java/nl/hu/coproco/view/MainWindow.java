package nl.hu.coproco.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("/editorScene.fxml"));
        } catch(Exception e ) {
            e.printStackTrace();
            return;
        }
        //EditorController editorController = (EditorController) fxmlLoader.getController();

        //primaryStage.setResizable(false);
        primaryStage.setTitle("CoProCo");

        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public void openWindow(String args[]) {
        launch(args);
    }

}
