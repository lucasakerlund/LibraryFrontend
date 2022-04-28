package com.stav.libraryfrontend.controllers.models;

import com.stav.libraryfrontend.Library;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.HashMap;

public class CustomerMenu extends BorderPane {

    private static CustomerMenu instance = new CustomerMenu();

    @FXML
    private HBox booksButton;
    @FXML
    private HBox myPageButton;
    @FXML
    private HBox localsButton;
    @FXML
    private HBox aboutUsButton;
    @FXML
    private HBox logutButton;

    @FXML
    private BorderPane content;

    private MenuButton focused;
    private HashMap<String, MenuButton> buttons;

    private CustomerMenu() {
        buttons = new HashMap<>();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/customer/customerMenu.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            buttons.put("books", new MenuButton(booksButton, new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/books/books.fxml")).load()));
            buttons.put("books", new MenuButton(myPageButton, new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/myPage/myPage.fxml")).load()));
            buttons.put("books", new MenuButton(localsButton, new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/local/local.fxml")).load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setup();
    }

    public static CustomerMenu inst(){
        return instance;
    }

    public void setContent(Parent parent){
        content.setCenter(parent);
    }

    private void setup(){
        logutButton.setOnMousePressed(e -> {
            Library.inst().setContent(LoginScreen.inst());
        });
    }

    private class MenuButton {

        private HBox buttonBox;
        private Parent content;

        private MenuButton(HBox buttonBox, Parent content){
            this.buttonBox = buttonBox;
            this.content = content;
            addClickListener();
        }

        private void addClickListener(){
            buttonBox.setOnMousePressed(e -> {
                setContent(content);
                if(focused != null){
                    focused.buttonBox.setId("");
                }
                buttonBox.setId("customer-menu-bar-button-focused");
                focused = this;
            });
        }

    }

}
