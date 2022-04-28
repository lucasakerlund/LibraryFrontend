package com.stav.libraryfrontend.controllers.models;

import com.stav.libraryfrontend.Library;
import javafx.fxml.FXMLLoader;

public class AdminScreen {

    public class AdminScreen instance = new AdminScreen
    private AdminScreen(){
        instance = this;

        FXMLLoader fxmlLoader = new FXMLLoader(Library.class.getResource("fxml/loginScreen.fxml"));

    }
}
