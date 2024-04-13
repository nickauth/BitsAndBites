
/*
package com.example.demo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Rezept;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PreviewRezeptController implements Initializable {
    @FXML
    private Label name;

    @FXML
    public ImageView img;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private Rezept rezept;
    public void setData(Rezept rezept) {
        this.rezept = rezept;
        name.setText(rezept.getName());
        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResourceAsStream(rezept.getImgSrc()));
        img.setImage(image);
    }



    private List<Rezept> rezepts = new ArrayList<>();

    private List<Rezept> getData(){
        List<Rezept> rezepts = new ArrayList<>();
        Rezept rezept;

        for (int i = 0; i < 12; i++){
            rezept = new Rezept();
            rezept.setName("Waffeln");
            rezept.setImgSrc("/img/waffeln.png");
            rezepts.add(rezept);
        }
        return rezepts;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rezepts.addAll(getData());
        int column =0;
        int row =0;
        try {
            for (int i = 0; i < rezepts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("com.example.demo.fxml/previewRezept.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PreviewRezeptController rezeptController = fxmlLoader.getController();
                rezeptController.setData(rezepts.get(i));

                if (column==3){
                    column=0;
                    row++;
                }
                grid.add(anchorPane,column++,row);
                GridPane.setMargin(anchorPane, new Insets(10));

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
*/

package org.bnb.ViewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.bnb.Model.Recipe;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PreviewRezeptController implements Initializable {

    @FXML
    private GridPane grid;

    private List<Recipe> rezepts = new ArrayList<>();

    private List<Recipe> getData() {
        List<Recipe> rezepts = new ArrayList<>();
        Recipe rezept;

        for (int i = 0; i < 12; i++) {
            rezept = new Recipe();
            rezept.setName("Waffeln");
            //rezept.set("/img/waffeln.png");
            rezepts.add(rezept);
        }
        return rezepts;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rezepts.addAll(getData());
        int column = 0;
        int row = 0;
        try {
            for (int i = 0; i < rezepts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("src/main/resources/com/example/demo/previewRezept.fxml"));
                //fxmlLoader.setLocation(getClass().getResource("src/main/resources/com/example/demo/previewRezept.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PreviewRezeptController rezeptController = fxmlLoader.getController();
                rezeptController.setData(rezepts.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setData(Recipe rezept) {
        // Hier können Sie die Daten des Rezepts setzen, wenn nötig
    }
}
