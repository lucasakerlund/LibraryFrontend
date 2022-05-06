package com.stav.libraryfrontend.controllers.models.myPage;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class MyPage extends BorderPane {

    private static MyPage instance = new MyPage();

    @FXML
    private BorderPane content;


    @FXML
    private Label bookedRoomsButton;

    @FXML
    private Label borrowedBooksButton;

    @FXML
    private Label reservedBooksButton;


    public MyPage(){
        instance = this;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/myPage/myPage.fxml"));
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
        borrowedBooksButton.setOnMousePressed(e -> {
            content.setCenter(LoanedBooksView.inst());
        });

    }

    public static MyPage inst(){
        return instance;
    }
}
