package nl.hu.coproco.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editor/editorScene.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/editorScene.fxml"));
        //EditorController editorController = (EditorController) fxmlLoader.getController();

        //primaryStage.setResizable(false);
        primaryStage.setTitle("CoProCo");

        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public void openWindow(String args[]) {
        launch(args);
    }

}
