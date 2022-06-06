package com.stav.libraryfrontend.controllers.models.staffPage.bookSuggestions;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.controllers.models.staffPage.StaffMenu;
import com.stav.libraryfrontend.models.BookSuggestion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class StaffBookSuggestionsPage extends BorderPane {

    private static StaffBookSuggestionsPage instance = new StaffBookSuggestionsPage();

    @FXML
    private FlowPane content;

    @FXML
    private Label reloadButton;

    private StaffBookSuggestionsPage(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/bookSuggestions/staffBookSuggestionsPage.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setup();
    }

    public static StaffBookSuggestionsPage inst(){
        return instance;
    }

    private void setup(){
        reloadButton.setOnMousePressed(e -> {
            loadSuggestions();
        });
        loadSuggestions();
    }

    public void loadSuggestions(){
        content.getChildren().clear();
        for (BookSuggestion bookSuggestion : BackendCaller.inst().getBookSuggestions()) {
            content.getChildren().add(new StaffBookSuggestionCover(bookSuggestion));
        }
    }
}
