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
                BasicInfo.inst().setInfo(c.getCustomerId(), c.getEmail(), c.getFirstName(), c.getLastName());
                CustomerContent.inst().setVisible();
                CustomerContent.inst().setCenter(BasicInfo.inst());

                List<LoanedBook> loanedBooks = BackendCaller.inst().getLoanedBooks(c.getCustomerId());
                for (int i = 0; i < loanedBooks.size(); i++) {
                    Book b = BackendCaller.inst().getBook(loanedBooks.get(i).getBookId());

                    String replace1 = Arrays.toString(b.getAuthors()).replace("[", "");
                    String authors = replace1.replace("]", "");

                    LoanedBookBox lbb = new LoanedBookBox(b.getTitle(), authors, b.getIsbn(), b.getImageSrc(), loanedBooks.get(i).getReturnDate());
                    LoanedBooks.inst().addBookBox(lbb);
                }

                List <BookQueue> reservedBooks = BackendCaller.inst().getReservedBooks(c.getCustomerId());
                for (int i = 0; i < reservedBooks.size(); i++) {
                    Book b = BackendCaller.inst().getBook(reservedBooks.get(i).getIsbn());

                    String replace1 = Arrays.toString(b.getAuthors()).replace("[", "");
                    String authors = replace1.replace("]", "");

                    ReservedBookBox rbb = new ReservedBookBox(b.getTitle(), authors, reservedBooks.get(i).getReservationDate(), b.getIsbn(), b.getImageSrc());
                    ReservedBooks.inst().addBookBox(rbb);
                }

                List<JSONObject> usersTimes = BackendCaller.inst().getUsersGroupRoomTimesById(c.getCustomerId());
                List<GroupRoom> allGroupRooms = BackendCaller.inst().getGroupRooms();
                int libraryId;

                BackendCaller.inst().getLibraryById(1);

                for (int i = 0; i < usersTimes.size(); i++) {
                    // JAG BEHÖVER FÅ TILLBAKA ETT LIBRARY NAMN PER LIBRARY ID
                    //GroupRoomBox box = new GroupRoomBox(usersTimes.get(i).getString("name"));
                    //GroupRoomBookings.inst().addRoomBox(box);
                }


                // Kötta in Lånade böcker, reserverade böcker och bokade grupprum HÄR
            }
        });
    }

    public static FindCustomerPage inst(){
        return instance;
    }

}
