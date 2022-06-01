package com.stav.libraryfrontend.controllers.models.staffPage.topSeller;

import com.stav.libraryfrontend.abstracts.SubSceneHandler;
import com.stav.libraryfrontend.controllers.models.staffPage.books.StaffBookInfoView;
import com.stav.libraryfrontend.models.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.json.JSONObject;

import java.io.IOException;

public class TopSellerBookCover extends StackPane {

    @FXML
    private ImageView imageView;
    @FXML
    private Label amountLabel;

    private Book book;
    private int amount;

    public TopSellerBookCover(JSONObject object) throws IOException {
        this.book = (Book) object.get("book");
        this.amount = object.getInt("amount");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/topSeller/topSellerBookCover.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        loader.load();

        setup();
    }

    private void setup(){
        imageView.setImage(new Image(book.getImageSrc()));
        amountLabel.setText(amount+"");

        this.setOnMousePressed(e -> {
            SubSceneHandler.inst().show(new StaffBookInfoView(book));
        });
    }

}
