module cs151project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.desktop;
    requires cs151project;

    opens cs151.application to javafx.fxml;
    opens cs151.application.model to com.google.gson;

    exports cs151.application;
}