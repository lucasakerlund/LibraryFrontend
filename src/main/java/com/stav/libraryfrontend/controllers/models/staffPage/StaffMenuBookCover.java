package com.stav.libraryfrontend.controllers.models.staffPage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class StaffMenuBookCover extends StackPane{

    @FXML
    private ImageView imageView;

    public StaffMenuBookCover(String imageSrc){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/staffMenuBookCover.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageView.setImage(new Image(imageSrc));

        setup();
    }

    public void setup(){
        imageView.setOnMousePressed(e-> {
            System.out.println("Här ska vi ha en såndär lucas-meny! ;)))");
        });
    }
}
