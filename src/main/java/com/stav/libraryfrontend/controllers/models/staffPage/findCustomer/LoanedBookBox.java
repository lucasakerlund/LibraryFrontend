package com.stav.libraryfrontend.controllers.models.staffPage.findCustomer;

import com.stav.libraryfrontend.abstracts.SubSceneHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class LoanedBookBox extends StackPane {

    @FXML
    private Label title;

    @FXML
    private ImageView image;

    public LoanedBookBox(String bookTitle, String authorName, String isbn, String imageSrc, String returnDate){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/findCustomer/loanedBookBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        title.setText(bookTitle);
        image.setImage(new Image(imageSrc));

        setup(bookTitle, authorName, isbn, imageSrc, returnDate);
    }

    public void setup(String bookTitle, String authorName, String isbn, String imageSrc, String returnDate){
        this.setOnMousePressed(e -> {
            SubSceneHandler.inst().show(new LoanedBookView(bookTitle, authorName, isbn, imageSrc, returnDate));
        });
    }
}
