package com.youssef;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {

    HomeViewController homeViewController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.homeViewController = new HomeViewController();
        this.homeViewController.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
