package com.stav.libraryfrontend.controllers.models.staffPage.books;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.controllers.models.books.BookLoanView;
import com.stav.libraryfrontend.controllers.models.myPage.loanedBooks.LoanedBooksView;
import com.stav.libraryfrontend.models.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.io.IOException;

public class StaffBookInfoView extends StackPane {

    @FXML
    private ImageView imageView;
    @FXML
    private Label totalLabel;
    @FXML
    private Label stockLabel;
    @FXML
    private Label queueLabel;
    @FXML
    private VBox returnList;
    @FXML
    private Label titleLabel;
    @FXML
    private Label authorLabel;
    @FXML
    private Label genreLabel;
    @FXML
    private Label isbnLabel;
    @FXML
    private Label publishedLabel;
    @FXML
    private Label pagesLabel;
    @FXML
    private TextArea descriptionLabel;
    @FXML
    private Label languageLabel;

    private Book book;
    private ReturnItem focused;

    public StaffBookInfoView(Book book){
        this.book = book;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/books/staffBookInfoView.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setup();
    }

    public void addReturn(String location, int amount){
        if(!returnList.getChildren().isEmpty()){
            Separator line = new Separator();
            line.setOrientation(Orientation.HORIZONTAL);
            returnList.getChildren().add(line);
        }
        ReturnItem item = new ReturnItem(location, amount);
        returnList.getChildren().add(item);
    }

    private void setup(){
        imageView.setImage(new Image(book.getImageSrc()));
        titleLabel.setText(book.getTitle());
        authorLabel.setText(String.join(", ", book.getAuthors()));
        genreLabel.setText(String.join(", ", book.getGenre()));
        isbnLabel.setText(book.getIsbn());
        publishedLabel.setText(book.getPublished());
        pagesLabel.setText(book.getPages()+"");
        descriptionLabel.setText(book.getDescription());
        languageLabel.setText(book.getLanguage());
        //totalLabel.setText(BackendCaller.inst().getAmountOfBooks(book.getIsbn())+"");
        //stockLabel.setText(BackendCaller.inst().getAmountOfBooksInStock(book.getIsbn())+"");
        //queueLabel.setText(BackendCaller.inst().getAmountInQueue(book.getIsbn())+"");
    }

    private class ReturnItem extends HBox {

        private ReturnItem(String location, int amount){
            this.getStyleClass().add("staff-book-info-view-return-box");
            Label locationLabel = new Label(location);
            Label amountLabel = new Label(amount+"");
            locationLabel.getStyleClass().add("staff-book-info-view-return-label");
            amountLabel.getStyleClass().add("staff-book-info-view-return-label");
            locationLabel.setAlignment(Pos.CENTER);
            amountLabel.setAlignment(Pos.CENTER);
            locationLabel.setMinHeight(50);
            locationLabel.setMinWidth(122);
            amountLabel.setMinHeight(50);
            amountLabel.setMinWidth(68);
            locationLabel.setWrapText(true);
            amountLabel.setWrapText(true);
            locationLabel.setTextAlignment(TextAlignment.LEFT);
            amountLabel.setTextAlignment(TextAlignment.LEFT);
            this.getChildren().add(locationLabel);
            this.getChildren().add(amountLabel);


            addClickListener();
        }

        private void addClickListener(){
            this.setOnMousePressed(e -> {
                if(focused != null){
                    focused.setId("");
                }
                this.setId("book-loan-view-location-box-focused");
                focused = this;
            });
        }

    }

}
