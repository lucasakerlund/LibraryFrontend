package com.stav.libraryfrontend.controllers.models.myPage;

import com.stav.libraryfrontend.controllers.models.myPage.groupRoomBookings.MyBookingsPage;
import com.stav.libraryfrontend.controllers.models.myPage.loanedBooks.LoanedBooksView;
import com.stav.libraryfrontend.controllers.models.myPage.reservedBooks.ReservedBooksView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

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

    private Label focused;

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
        content.setCenter(LoanedBooksView.inst());
        LoanedBooksView.inst().loadBooks();
        setFocused(borrowedBooksButton);
    }

    public void open(String id){
        if(id.equals("reservedBooks")){
            content.setCenter(ReservedBooksView.inst());
            ReservedBooksView.inst().loadBooks();
            setFocused(reservedBooksButton);
        }
        if(id.equals("borrowedBooks")){
            content.setCenter(LoanedBooksView.inst());
            LoanedBooksView.inst().loadBooks();
            setFocused(borrowedBooksButton);
        }
        if(id.equals("bookedRooms")){
            content.setCenter(MyBookingsPage.inst());
            MyBookingsPage.inst().loadBookings();
            setFocused(bookedRoomsButton);
        }
    }

    public void setFocused(Label focused) {
        if(this.focused != null){
            this.focused.setId("");
        }
        focused.setId("my-page-header-buttons-focused");
        this.focused = focused;
    }

    public void setup(){
        clearMessage();

        borrowedBooksButton.setOnMousePressed(e -> {
            open("borrowedBooks");
        });
        reservedBooksButton.setOnMousePressed(e -> {
            open("reservedBooks");
        });
        bookedRoomsButton.setOnMousePressed(e -> {
            open("bookedRooms");
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
