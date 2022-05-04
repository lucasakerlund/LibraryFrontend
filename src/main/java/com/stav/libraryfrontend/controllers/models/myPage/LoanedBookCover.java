package com.stav.libraryfrontend.controllers.models.myPage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class LoanedBookCover extends StackPane {

    @FXML
    private ImageView imageView;

    public LoanedBookCover(String imageSrc){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/myPage/loanedBookCover.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageView.setImage(new Image(imageSrc));
    }

}
