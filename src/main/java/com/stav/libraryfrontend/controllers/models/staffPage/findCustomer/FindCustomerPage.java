package com.stav.libraryfrontend.controllers.models.staffPage.findCustomer;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.models.Customer;
import com.stav.libraryfrontend.models.Staff;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class FindCustomerPage extends BorderPane {

    @FXML
    private BorderPane contentPane;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField searchBar;

    @FXML
    private Label searchButton;

    private static FindCustomerPage instance = new FindCustomerPage();

    private FindCustomerPage(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/findCustomer/findCustomerPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        errorLabel.setVisible(false); //Default error label is off

        CustomerContent.inst().setInvisible();
        contentPane.setCenter(CustomerContent.inst());

        setup();
    }

    public void setup(){
       searchButton.setOnMousePressed(e -> {


            Customer c = BackendCaller.inst().getCustomerByEmail(searchBar.getText());
            if (c == null){
                errorLabel.setVisible(true);
                errorLabel.setText("Kunde inte hitta kontot...");
            } else{
                BasicInfo bI = new BasicInfo(c.getCustomerId(), c.getEmail(), c.getFirstName(), c.getLastName());
                CustomerContent.inst().setVisible();
                CustomerContent.inst().setCenter(bI);

                // Kötta in Lånade böcker, reserverade böcker och bokade grupprum HÄR
            }
        });
    }

    public static FindCustomerPage inst(){
        return instance;
    }

}
