package cs151.application.services;

import javafx.scene.control.Alert;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ControllerUtility {
    public ControllerUtility() {
    }

    public Alert popAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.setHeaderText(type.name());
        alert.setTitle("Attention");
        return alert;
    }

    public String getTimeString() {
        ZonedDateTime now = ZonedDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
    }
}
