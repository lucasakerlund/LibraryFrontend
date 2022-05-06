package com.stav.libraryfrontend.controllers.models.myPage;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import javafx.application.Platform;
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

    @FXML
    private Label confirmReturnLabel;


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
        clearMessage();

        borrowedBooksButton.setOnMousePressed(e -> {
            content.setCenter(LoanedBooksView.inst());
        });
    }

    public void inputMessage(String update){
        Thread th = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> clearMessage());
            }
        });


        confirmReturnLabel.setText(update);
        th.start();
    }

    public void clearMessage(){
        confirmReturnLabel.setText("");
    }

    public static MyPage inst(){
        return instance;
    }
}
