package com.stav.libraryfrontend.controllers.models;

import com.stav.libraryfrontend.Library;
import javafx.fxml.FXMLLoader;

public class AdminScreen {

    private static AdminScreen instance;

    private AdminScreen(){
        instance = this;

        FXMLLoader fxmlLoader = new FXMLLoader(Library.class.getResource("fxml/loginScreen.fxml"));

    }
}
