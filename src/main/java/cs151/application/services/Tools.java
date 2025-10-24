package cs151.application.services;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Tools {
    public Tools() {
    }

    public Alert popAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.setHeaderText(type.name());
        alert.setTitle("Attention");
        return alert;
    }

    public void setPageStyle(Scene scene) {
        String stylePath = "/style/homePage.css";
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(stylePath)).toExternalForm());
    }

    public String getTimeString() {
        ZonedDateTime now = ZonedDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
    }
}
