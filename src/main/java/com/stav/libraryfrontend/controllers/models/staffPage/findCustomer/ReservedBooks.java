package com.stav.libraryfrontend.controllers.models.staffPage.findCustomer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class ReservedBooks extends ScrollPane {

    private static ReservedBooks instance = new ReservedBooks();

    @FXML
    private FlowPane content;

    private ReservedBooks(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/findCustomer/reservedBooks.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setup();
    }

    public void setup(){

    }

    public void clearInfo(){
        content.getChildren().clear();
    }

    public void addBookBox(ReservedBookBox rbb){
        content.getChildren().add(rbb);
    }

    public static ReservedBooks inst(){
        return instance;
    }
}
