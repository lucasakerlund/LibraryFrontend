package com.stav.libraryfrontend.controllers.models;

import com.stav.libraryfrontend.Library;
import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.abstracts.StaffDetails;
import com.stav.libraryfrontend.controllers.models.staffPage.StaffMenu;
import com.stav.libraryfrontend.models.Staff;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AdminScreen extends BorderPane {

    private static AdminScreen instance = new AdminScreen();

    @FXML
    private Label backButton;

    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Label adminLoginButton;

    @FXML
    private Label adminForgotPasswordButton;

    @FXML
    private Label messageLabel;

    @FXML
    private Label errorLabel;


    private AdminScreen() {
        instance = this;

        FXMLLoader fxmlLoader = new FXMLLoader(Library.class.getResource("/com/stav/libraryfrontend/fxml/staff/adminScreenLogin.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setup();
    }

    public void setup() {
        backButton.setOnMousePressed(e -> {
            Library.inst().setContent(LoginScreen.inst());
        });

        adminForgotPasswordButton.setOnMousePressed(e -> {
            Library.inst().setContent(AddStaffScreen.inst());
        });

        adminLoginButton.setOnMousePressed(e -> {
            if (usernameInput.getText().equals("") || passwordInput.getText().equals("")){
                errorLabel.setText("Vänligen fyll i båda textfälten...");
                errorLabel.setVisible(true);
                return;
            }

            Staff staff = BackendCaller.inst().loginStaff(usernameInput.getText(), passwordInput.getText());
            if(staff == null){
                errorLabel.setText("Felaktigt användarnamn eller lösenord...");
                errorLabel.setVisible(true);
                return;
            }
            StaffDetails.inst().setStaff(staff);
            Library.inst().setContent(StaffMenu.inst());
            usernameInput.setText("");
            passwordInput.setText("");
        });
    }

    public static AdminScreen inst() {
        return instance;
    }

}
