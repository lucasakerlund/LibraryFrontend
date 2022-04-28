package com.stav.libraryfrontend.controllers.models;

import com.stav.libraryfrontend.Library;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class AdminScreenLoginController {

    private static AdminScreenLoginController instance;

    //@FXML

    private AdminScreenLoginController(){
        instance = this;

        FXMLLoader fxmlLoader = new FXMLLoader(Library.class.getResource("fxml/loginScreen.fxml"));

    }
}
