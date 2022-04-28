module com.stav.libraryfrontend {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.stav.libraryfrontend to javafx.fxml;
    opens com.stav.libraryfrontend.controllers.models to javafx.fxml; // Förklarar för controller klasser vart FXML filer finns (typ)
    exports com.stav.libraryfrontend;
}