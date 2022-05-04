package com.stav.libraryfrontend.controllers.models;

import com.stav.libraryfrontend.Library;
import com.stav.libraryfrontend.controllers.models.books.Books;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.HashMap;

public class StaffMenu extends BorderPane {

    private static StaffMenu instance = new StaffMenu();

    @FXML
    private HBox allBooksButton;
    @FXML
    private HBox staffPageButton;
    @FXML
    private HBox staffLocalsButton;
    @FXML
    private HBox logoutButton;

    @FXML
    private BorderPane staffContent;

    @FXML
    private ImageView test;

    private MenuButton focused;
    private HashMap<String, MenuButton> buttons;

    private StaffMenu() {
        buttons = new HashMap<>();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staff/staffMenu.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            buttons.put("books", new MenuButton(allBooksButton, null));
            buttons.put("books", new MenuButton(staffPageButton, new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/myPage/myPage.fxml")).load()));
            buttons.put("books", new MenuButton(staffLocalsButton, new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/local/local.fxml")).load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setup();
    }

    public static StaffMenu inst(){
        return instance;
    }

    public void setContent(Parent parent){
        staffContent.setCenter(parent);
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
                if(focused != null){
                    focused.buttonBox.setId("");
                }
                buttonBox.setId("staff-menu-bar-button-focused");
                focused = this;
            });
        }

    }

}
