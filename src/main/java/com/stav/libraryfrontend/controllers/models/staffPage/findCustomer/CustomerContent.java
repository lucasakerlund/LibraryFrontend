package com.stav.libraryfrontend.controllers.models.staffPage.findCustomer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class CustomerContent extends BorderPane {

    @FXML
    private Label generalInfoButton;

    @FXML
    private Label loanedBooksButton;

    @FXML
    private Label reservedBooksButton;

    @FXML
    private Label bookedRoomsButton;

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
        generalInfoButton.setOnMousePressed(e ->{
            this.setCenter(BasicInfo.inst());
        });

        loanedBooksButton.setOnMousePressed(e -> {
            this.setCenter(LoanedBooks.inst());
        });

        reservedBooksButton.setOnMousePressed(e -> {
            this.setCenter(ReservedBooks.inst());
        });

        bookedRoomsButton.setOnMousePressed(e -> {
            this.setCenter(GroupRoomBookings.inst());
        });
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
