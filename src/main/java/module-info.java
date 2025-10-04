module org.example.cs151project {
    requires javafx.controls;
    requires javafx.fxml;


    opens cs151.application to javafx.fxml;
    exports cs151.application;
}