package com.stav.libraryfrontend.controllers.models;

import com.stav.libraryfrontend.Library;
import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.controllers.models.books.Books;
import com.stav.libraryfrontend.controllers.models.myPage.MyPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.image.ImageView;
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
    private HBox logoutButton;

    @FXML
    private BorderPane content;

    @FXML
    private ImageView test;

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
            buttons.put("books", new MenuButton(booksButton, Books.inst()));
            buttons.put("myPage", new MenuButton(myPageButton, MyPage.inst()));
            buttons.put("locals", new MenuButton(localsButton, new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/local/local.fxml")).load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setup();
        open("books");
    }

    public static CustomerMenu inst(){
        return instance;
    }

    public void open(String id){
        setContent(buttons.get(id).content);
        setFocus(buttons.get(id));
    }

    private void setContent(Parent parent){
        content.setCenter(parent);
    }

    private void setFocus(MenuButton button){
        if(focused != null){
            focused.buttonBox.setId("");
        }
        button.buttonBox.setId("customer-menu-bar-button-focused");
        focused = button;
    }

    private void setup(){
        logoutButton.setOnMousePressed(e -> {
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
                setFocus(this);
            });
        }

    }

}
