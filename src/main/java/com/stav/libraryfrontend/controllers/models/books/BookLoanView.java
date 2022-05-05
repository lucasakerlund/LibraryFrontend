package com.stav.libraryfrontend.controllers.models.books;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.io.IOException;

public class BookLoanView extends StackPane {

    @FXML
    private ImageView imageView;

    @FXML
    private VBox locations;

    @FXML
    private Label errorLabel;

    @FXML
    private Label lendButton;

    private LocationItem focused;

    public BookLoanView(String imageSrc){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/books/bookLoanView.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageView.setImage(new Image(imageSrc));

        setup();
    }

    public void addLocation(String location, int amount){
        if(!locations.getChildren().isEmpty()){
            Separator line = new Separator();
            line.setOrientation(Orientation.HORIZONTAL);
            locations.getChildren().add(line);
        }
        LocationItem item = new LocationItem(location, amount);
        locations.getChildren().add(item);
    }

    private void setup(){
        lendButton.setOnMousePressed(e -> {
            if(focused == null){
                errorLabel.setVisible(true);
                return;
            }
        });
        addLocation("Tjena111111111111111", 3);
        addLocation("Yoooo", 6);
        addLocation("Yoooo", 6);
        addLocation("Yoooo", 6);
        addLocation("Yoooo", 6);
        addLocation("Yoooo", 6);
    }

    private class LocationItem extends HBox {

        private LocationItem(String location, int amount){
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
            locationLabel.setTextAlignment(TextAlignment.CENTER);
            amountLabel.setTextAlignment(TextAlignment.CENTER);
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
