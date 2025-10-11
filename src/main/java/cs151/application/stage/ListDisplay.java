package cs151.application.stage;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

public class ListDisplay extends Stage {
    public ListDisplay(List<String> displayStrings) {
        Label title = new Label(displayStrings.get(0));

        HBox line = new HBox();
        line.getStyleClass().add("sectionLayout");
        for (int i = 1; i < displayStrings.size(); i++) {
            Text text = new Text(displayStrings.get(i));
            line.getChildren().add(text);
        }

        VBox sectionLayout = new VBox(title,new Separator(), line);
        sectionLayout.getStyleClass().add("sectionLayout");

        VBox pageLayout = new VBox(sectionLayout);
        pageLayout.getStyleClass().add("pageLayout");

        Scene pageScene = new Scene(pageLayout, 500, 200);
        pageScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style/homePage.css")).toExternalForm());
        this.setTitle("List");
        this.setScene(pageScene);
    }
}
