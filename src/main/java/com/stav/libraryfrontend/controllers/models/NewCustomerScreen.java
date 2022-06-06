package com.stav.libraryfrontend.controllers.models;

import com.stav.libraryfrontend.Library;
import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.abstracts.SubSceneHandler;
import com.stav.libraryfrontend.models.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class NewCustomerScreen extends BorderPane {

    private static NewCustomerScreen instance = new NewCustomerScreen();

    @FXML
    private Label createAccountButton;

    @FXML
    private Label backButton;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    private NewCustomerScreen(){
        instance = this;

        FXMLLoader fxmlLoader = new FXMLLoader(Library.class.getResource("fxml/newCustomerScreen.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setup();
    }

    public void setup(){
        errorLabel.setText("");

        backButton.setOnMousePressed(e -> {
            firstNameTextField.clear();
            lastNameTextField.clear();
            emailTextField.clear();
            passwordField.clear();
            confirmPasswordField.clear();
            errorLabel.setText("");
            Library.inst().setContent(LoginScreen.inst());
        });

        createAccountButton.setOnMousePressed(e -> {
            if(!passwordField.getText().equals(confirmPasswordField.getText())){
                errorLabel.setText("Lösenord och upprepat lösenord stämmer inte överens med varandra");
                return;
            }

            if (firstNameTextField.getText().equals("") || lastNameTextField.getText().equals("") || emailTextField.getText().equals("")
                    || passwordField.getText().equals("") || confirmPasswordField.getText().equals("")){
                errorLabel.setText("Samtliga textfält är obligatoriska och får inte lämnas blanka");
                return;
            }

            Customer customer = BackendCaller.inst().getCustomerByEmail(emailTextField.getText());

            if(customer != null) {
                errorLabel.setText("Email-adressen används redan av ett annat konto");
                return;
            }

            BankID.inst().setConfirmListener((s) -> {
                boolean response = BackendCaller.inst().createCustomer(firstNameTextField.getText(), lastNameTextField.getText(),
                        emailTextField.getText(), passwordField.getText());
                if(!response){
                    errorLabel.setText("Något fel inträffade, försök igen.");
                    return;
                }
                LoginScreen.inst().userAddedSuccessfully();
                Library.inst().setContent(LoginScreen.inst());
                SubSceneHandler.inst().hide();
            });
            SubSceneHandler.inst().show(BankID.inst());

            // LoginScreen.inst().userAddedSuccessfully();
            // Library.inst().setContent(LoginScreen.inst());
        });
    }

    public static NewCustomerScreen inst(){
        return instance;
    }

}
