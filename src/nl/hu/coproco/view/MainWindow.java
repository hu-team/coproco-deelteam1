package nl.hu.coproco.view;

import javafx.application.Application;
import javafx.stage.Stage;


public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setResizable(false);
        primaryStage.setTitle("CoProCo");
//        primaryStage.setScene();
        primaryStage.show();
    }

    public void openWindow(String args[]) {
        launch(args);
    }
}
