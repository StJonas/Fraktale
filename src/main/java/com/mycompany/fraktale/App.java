package com.mycompany.fraktale;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class App extends Application {

    @Override
    public void start(Stage stage) {
       
        Fraktalepane frakPane = new Fraktalepane();
        var scene = new Scene(frakPane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}