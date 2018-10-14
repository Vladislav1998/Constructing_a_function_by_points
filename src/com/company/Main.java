package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage  Stage) throws IOException {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Stage.setTitle("Function Graphs");
            Stage.setScene(new Scene(root, 775, 600));
            Stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
