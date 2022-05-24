package com.stav.libraryfrontend.controllers.models.books;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.abstracts.SubSceneHandler;
import com.stav.libraryfrontend.abstracts.UserDetails;
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
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class BookLoanView extends StackPane {

    @FXML
    private ImageView imageView;

    @FXML
    private VBox locations;

    @FXML
    private Label errorLabel;

    @FXML
    private VBox box;
    @FXML
    private Label lendButton;
    @FXML
    private Label queueButton;
    @FXML
    private Label inQueueButton;

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
    private LocationItem focused;

    public BookLoanView(Book book){
        this.book = book;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/books/bookLoanView.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setup();
    }

    public void addLocation(int libraryId, String location, int amount){
        if(!locations.getChildren().isEmpty()){
            Separator line = new Separator();
            line.setOrientation(Orientation.HORIZONTAL);
            locations.getChildren().add(line);
        }
        LocationItem item = new LocationItem(libraryId, location, amount);
        locations.getChildren().add(item);
    }

    private void setup(){
        lendButton.setOnMousePressed(e -> {
            if(focused == null){
                errorLabel.setText("Välj ett bibliotek.");
                errorLabel.setVisible(true);
                return;
            }
            if(!BackendCaller.inst().loanBook(book, UserDetails.inst().getCustomer(), focused.libraryId)){
                errorLabel.setText("Något fel inträffade.");
                errorLabel.setVisible(true);
                return;
            }
            LoanedBooksView.inst().loadBooks();
            SubSceneHandler.inst().hide();
        });
        inQueueButton.setOnMousePressed(e -> {
            BackendCaller.inst().reserveBook(book.getIsbn(), UserDetails.inst().getCustomer().getCustomerId());
        });
        queueButton.setOnMousePressed(e -> {
            if (!BackendCaller.inst().reserveBook(book.getIsbn(), UserDetails.inst().getCustomer().getCustomerId())) {
                errorLabel.setText("Något fel inträffade.");
                errorLabel.setVisible(true);
                return;
            }
            SubSceneHandler.inst().hide();
        });

        setupBookInformation();

        JSONArray array = BackendCaller.inst().getAmountOfBookInLibraries(book.getIsbn());
        boolean outOfStock = true;
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            addLocation(object.getInt("library_id"), object.getString("name"), object.getInt("amount"));
            if(object.getInt("amount") > 0){
                outOfStock = false;
            }
        }
        box.getChildren().remove(lendButton);
        box.getChildren().remove(queueButton);
        box.getChildren().remove(inQueueButton);
        if(outOfStock){
            if (BackendCaller.inst().isInQueue(book.getIsbn(), UserDetails.inst().getCustomer().getCustomerId())) {
                box.getChildren().add(1, inQueueButton);
                return;
            }
            box.getChildren().add(1, queueButton);
        }else{
            if(BackendCaller.inst().getAmountInQueue(book.getIsbn()) >= 1){
                //Perhaps check if the customer is first in the queue and then let him be able to lend from here.
                return;
            }
            box.getChildren().add(1, lendButton);
        }

        SubSceneHandler.inst().hide();
    }

    private void setupBookInformation() {
        imageView.setImage(new Image(book.getImageSrc()));
        titleLabel.setText(book.getTitle());
        authorLabel.setText(String.join(", ", book.getAuthors()));
        genreLabel.setText(String.join(", ", book.getGenre()));
        isbnLabel.setText(book.getIsbn());
        publishedLabel.setText(book.getPublished());
        pagesLabel.setText(book.getPages()+"");
        descriptionLabel.setText(book.getDescription());
        languageLabel.setText(book.getLanguage());
    }

    private class LocationItem extends HBox {

        private int libraryId;

        private LocationItem(int libraryId, String location, int amount){
            this.libraryId = libraryId;
            this.getStyleClass().add("book-loan-view-location-box");
            Label locationLabel = new Label(location);
            Label amountLabel = new Label(amount+"");
            locationLabel.getStyleClass().add("book-loan-view-location-label");
            amountLabel.getStyleClass().add("book-loan-view-location-label");
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
