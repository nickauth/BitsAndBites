/*
package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Rezept;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        try {

            //Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
            //Scene mainScene = new Scene(root);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainView.fxml"));
            Parent root = loader.load();
            Scene mainScene = new Scene(root);

            stage.setScene(mainScene);
            stage.setTitle("Bits & Bites");
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {}

    }

}
*/

package org.bnb.ViewController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/resources/org/bnb/viewcontroller/mainView.fxml"));
            Parent root = loader.load();
            Scene mainScene = new Scene(root);
            stage.setScene(mainScene);
            stage.setTitle("Bits & Bites");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
