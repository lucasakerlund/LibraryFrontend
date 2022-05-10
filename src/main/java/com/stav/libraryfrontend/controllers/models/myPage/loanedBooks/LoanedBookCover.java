package com.stav.libraryfrontend.controllers.models.myPage.loanedBooks;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.controllers.models.myPage.MyPage;
import com.stav.libraryfrontend.models.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class LoanedBookCover extends StackPane {

    @FXML
    private ImageView imageView;

    @FXML
    private Label returnBookButton;

    @FXML
    private Label daysLeftLabel;

    private Book book;

    public LoanedBookCover(Book book){
        this.book = book;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/myPage/loanedBooks/loanedBookCover.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageView.setImage(new Image(book.getImageSrc()));

        setup();
    }

    public void setup(){
        returnBookButton.setOnMousePressed(e -> {
            boolean response = BackendCaller.inst().returnBook(book.getBookId());
            if(!response){
                //Ett fel har inträffat, försök igen!
                return;
            }
            LoanedBooksView.inst().deleteBook(this);
            MyPage.inst().inputMessage("Boken har återlämnats!");
        });
    }

    public void setDaysLeft(String days){ // This should be made to work with a set date.
        daysLeftLabel.setText(daysLeftLabel + days + " dagar");
    }

}
