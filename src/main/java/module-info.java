module com.stav.libraryfrontend {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.stav.libraryfrontend to javafx.fxml;
    exports com.stav.libraryfrontend;
}