package com.stav.libraryfrontend.controllers.models.staffPage;

import com.stav.libraryfrontend.Library;
import com.stav.libraryfrontend.controllers.models.AdminScreen;
import com.stav.libraryfrontend.controllers.models.staffPage.addBook.StaffAddBookPage;
import com.stav.libraryfrontend.controllers.models.staffPage.bookSuggestions.StaffBookSuggestionsPage;
import com.stav.libraryfrontend.controllers.models.staffPage.books.StaffBookPage;
import com.stav.libraryfrontend.controllers.models.staffPage.findCustomer.FindCustomerPage;
import com.stav.libraryfrontend.controllers.models.staffPage.groupRooms.StaffGroupRooms;
import com.stav.libraryfrontend.controllers.models.staffPage.topSeller.TopSellerPage;
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
    private HBox staffAddBookButton;
    @FXML
    private HBox staffLocalsButton;
    @FXML
    private HBox topSellerButton;
    @FXML
    private HBox findCustomerButton;
    @FXML
    private HBox bookSuggestionsbutton;
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
            buttons.put("books", new MenuButton(allBooksButton, StaffBookPage.inst()));
            buttons.put("dunno", new MenuButton(staffAddBookButton, StaffAddBookPage.inst()));
            buttons.put("locals", new MenuButton(staffLocalsButton, StaffGroupRooms.inst()));
            buttons.put("topSeller", new MenuButton(topSellerButton, TopSellerPage.inst()));
            buttons.put("findCustomer", new MenuButton(findCustomerButton, FindCustomerPage.inst()));
            buttons.put("bookSuggestions", new MenuButton(bookSuggestionsbutton, StaffBookSuggestionsPage.inst()));

        setup();
        open("books");
    }

    public static StaffMenu inst(){
        return instance;
    }

    public void open(String id){
        setContent(buttons.get(id).content);
        setFocus(buttons.get(id));
    }

    private void setContent(Parent parent){
        staffContent.setCenter(parent);
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
            Library.inst().setContent(AdminScreen.inst());
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
