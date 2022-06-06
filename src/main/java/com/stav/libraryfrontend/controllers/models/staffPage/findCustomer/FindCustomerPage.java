package com.stav.libraryfrontend.controllers.models.staffPage.findCustomer;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.models.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        //Makes a customer object to check if the email is valid or not


       searchButton.setOnMousePressed(e -> {
           Customer customer = BackendCaller.inst().getCustomerByEmail(searchBar.getText());
           // If email is valid, continue - Else, display error
           if (customer == null) {
               errorLabel.setVisible(true);
               errorLabel.setText("Kunde inte hitta kontot...");
               clearAll();
               CustomerContent.inst().setVisible(false);
           } else {
               errorLabel.setVisible(false);
               CustomerContent.inst().setVisible();
               getBasicInfo(customer);
               getLoanedBooksInfo(customer);
               getReservedBooksInfo(customer);
               getGroupRoomBookingsInfo(customer);
               CustomerContent.inst().setDefaultFocus();
               searchBar.setText("");
           }
       });
    }

    public void getBasicInfo(Customer customer) {
            BasicInfo.inst().setInfo(customer.getCustomerId(), customer.getEmail(), customer.getFirstName(), customer.getLastName());
            CustomerContent.inst().setCenter(BasicInfo.inst());
    }

    public void getLoanedBooksInfo(Customer customer){
        LoanedBooks.inst().clearInfo();
        List<LoanedBook> loanedBooks = BackendCaller.inst().getLoanedBooks(customer.getCustomerId());
        for (int i = 0; i < loanedBooks.size(); i++) {
            Book b = BackendCaller.inst().getBook(loanedBooks.get(i).getBookId());

            String replace1 = Arrays.toString(b.getAuthors()).replace("[", "");
            String authors = replace1.replace("]", "");

            LoanedBookBox lbb = new LoanedBookBox(b.getTitle(), authors, b.getIsbn(), b.getImageSrc(), loanedBooks.get(i).getReturnDate());
            LoanedBooks.inst().addBookBox(lbb);
        }
    }

    public void getReservedBooksInfo(Customer customer){
        ReservedBooks.inst().clearInfo();
        List <BookQueue> reservedBooks = BackendCaller.inst().getReservedBooks(customer.getCustomerId());
        for (int i = 0; i < reservedBooks.size(); i++) {
            Book b = BackendCaller.inst().getBook(reservedBooks.get(i).getIsbn());

            String replace1 = Arrays.toString(b.getAuthors()).replace("[", "");
            String authors = replace1.replace("]", "");

            ReservedBookBox rbb = new ReservedBookBox(b.getTitle(), authors, reservedBooks.get(i).getReservationDate(), b.getIsbn(), b.getImageSrc());
            ReservedBooks.inst().addBookBox(rbb);
        }
    }

    public void getGroupRoomBookingsInfo(Customer customer) {
        GroupRoomBookings.inst().clearInfo();

        // Gets all the users booked times and gets ALL group rooms, so we can find what the name of the room
        // of a particular booking is.
        List<JSONObject> usersTimes = BackendCaller.inst().getUsersGroupRoomTimesById(customer.getCustomerId());
        List<GroupRoom> allGroupRooms = BackendCaller.inst().getGroupRooms();

        int libraryID;
        LibraryModel l = null;

        for (int i = 0; i < usersTimes.size(); i++) {
            for (int j = 0; j < allGroupRooms.size(); j++) {
                if (allGroupRooms.get(j).getName().equalsIgnoreCase(usersTimes.get(i).getString("name"))){
                    libraryID = allGroupRooms.get(j).getLibrary_id();
                    l = BackendCaller.inst().getLibraryById(libraryID);
                    System.out.println("l = " + l);
                }
            }
            GroupRoomBox box = new GroupRoomBox(usersTimes.get(i).getString("name"), l.getName(),
                    usersTimes.get(i).getString("time"), usersTimes.get(i).getInt("time_id"),
                    customer);
            GroupRoomBookings.inst().addRoomBox(box);
        }
    }

    public void clearAll(){
        LoanedBooks.inst().clearInfo();
        ReservedBooks.inst().clearInfo();
        GroupRoomBookings.inst().clearInfo();
        searchBar.setText("");
    }

    public static FindCustomerPage inst(){
        return instance;
    }

}
