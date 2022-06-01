package com.stav.libraryfrontend.controllers.models.staffPage.findCustomer;

import com.stav.libraryfrontend.abstracts.SubSceneHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class ReservedBookBox extends StackPane {

    @FXML
    private Label title;

    @FXML
    private ImageView image;

    public ReservedBookBox(String bookTitle, String author, String queueTime, String isbn, String imageSrc){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/findCustomer/reservedBookBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        title.setText(bookTitle);
        image.setImage(new Image(imageSrc));
        setup(bookTitle, author, queueTime, isbn, imageSrc);
    }

    public void setup(String bookTitle, String author, String queueTime, String isbn, String imageSrc){
        this.setOnMousePressed(e -> {
            SubSceneHandler.inst().show(new ReservedBooksView(bookTitle, author, queueTime, isbn, imageSrc));
        });
    }
}
