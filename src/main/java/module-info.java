module cs151project {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.graphics;


    opens cs151.application to javafx.fxml;
    opens cs151.application.model to com.google.gson;

    exports cs151.application;
}