package com.stav.libraryfrontend.controllers.models;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class CustomerMenu {

    private static CustomerMenu instance;

    private BorderPane root;

    public CustomerMenu(){

    }

    public static CustomerMenu inst(){
        return instance;
    }

    public void setContent(Parent parent){
        root.setCenter(parent);
    }

}
