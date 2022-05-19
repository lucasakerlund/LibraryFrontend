package com.stav.libraryfrontend.controllers.models.myPage.reservedBooks;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.abstracts.UserDetails;
import com.stav.libraryfrontend.controllers.models.myPage.loanedBooks.LoanedBookCover;
import com.stav.libraryfrontend.models.BookQueue;
import com.stav.libraryfrontend.models.LoanedBook;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.List;

public class ReservedBooksView extends BorderPane {

    private static ReservedBooksView instance = new ReservedBooksView();

    @FXML
    private FlowPane box;

    private ReservedBooksView(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/myPage/reservedBooks/reservedBooksView.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setup();
    }

    public static ReservedBooksView inst(){
        return instance;
    }

    private void setup(){
        loadBooks();
    }

    public void loadBooks(){
        box.getChildren().clear();
        List<BookQueue> list = BackendCaller.inst().getReservedBooks(UserDetails.inst().getCustomer().getCustomerId());
        for (BookQueue reservedBook : list) {
            try {
                addBook(new ReservedBookCover(BackendCaller.inst().getBook(reservedBook.getBookId())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addBook(ReservedBookCover reservedBookCover){
        box.getChildren().add(reservedBookCover);
    }

    public void deleteBook (ReservedBookCover reservedBookCover){
        box.getChildren().remove(reservedBookCover);
    }

}
