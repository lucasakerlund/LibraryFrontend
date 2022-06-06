module com.stav.libraryfrontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires com.fasterxml.jackson.annotation;

    opens com.stav.libraryfrontend to javafx.fxml;
    opens com.stav.libraryfrontend.controllers.models to javafx.fxml; // Förklarar för controller klasser vart FXML filer finns (typ)
    opens com.stav.libraryfrontend.controllers.models.books to javafx.fxml;
    opens com.stav.libraryfrontend.controllers.models.myPage to javafx.fxml;
    opens com.stav.libraryfrontend.controllers.models.staffPage to javafx.fxml;
    opens com.stav.libraryfrontend.controllers.models.groupRooms to javafx.fxml;
    exports com.stav.libraryfrontend;
    opens com.stav.libraryfrontend.controllers.models.staffPage.books to javafx.fxml;
    opens com.stav.libraryfrontend.controllers.models.staffPage.topSeller to javafx.fxml;
    opens com.stav.libraryfrontend.controllers.models.myPage.loanedBooks to javafx.fxml;
    opens com.stav.libraryfrontend.controllers.models.myPage.reservedBooks to javafx.fxml;
    opens com.stav.libraryfrontend.controllers.models.userSuggestions to javafx.fxml, javafx.graphics;
    opens com.stav.libraryfrontend.controllers.models.myPage.groupRoomBookings to javafx.fxml, javafx.graphics;
    opens com.stav.libraryfrontend.controllers.models.staffPage.findCustomer to javafx.fxml, javafx.graphics;
    opens com.stav.libraryfrontend.controllers.models.staffPage.bookSuggestions to javafx.fxml, javafx.graphics;
    opens com.stav.libraryfrontend.controllers.models.staffPage.groupRooms to javafx.fxml, javafx.graphics;
    opens com.stav.libraryfrontend.controllers.models.staffPage.addBook to javafx.fxml;
}