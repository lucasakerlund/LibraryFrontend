package com.stav.libraryfrontend.controllers.models.staffPage.topSeller;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.models.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class TopSellerPage extends BorderPane {

    private static TopSellerPage instance = new TopSellerPage();

    @FXML
    private FlowPane content;

    private TopSellerPage(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/topSeller/topSellerPage.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setup();
    }

    private void setup(){
        loadBooks();
    }

    public void loadBooks(){
        content.getChildren().clear();
        List<JSONObject> list = new ArrayList<>();
        for (Book book : BackendCaller.inst().getBooks("", "", "", "", "", "")) {
            int amount = BackendCaller.inst().getAmountOfBooksInStock(book.getIsbn());
            JSONObject o = new JSONObject();
            o.put("book", book);
            o.put("amount", amount);
            list.add(o);
        }
        list.sort(Comparator.comparing(o -> o.getInt("amount")));
        for (JSONObject jsonObject : list) {
            try {
                content.getChildren().add(new TopSellerBookCover(jsonObject));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static TopSellerPage inst(){
        return instance;
    }

}
