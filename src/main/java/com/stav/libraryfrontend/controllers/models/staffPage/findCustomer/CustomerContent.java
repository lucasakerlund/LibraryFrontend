package com.stav.libraryfrontend.controllers.models.staffPage.findCustomer;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class CustomerContent extends BorderPane {
    private static CustomerContent instance = new CustomerContent();

    private CustomerContent() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/findCustomer/customerContent.fxml"));
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

    public static CustomerContent inst(){
        return instance;
    }

    public void setInvisible(){
        this.setVisible(false);
    }

    public void setVisible(){
        this.setVisible(true);
    }
}
