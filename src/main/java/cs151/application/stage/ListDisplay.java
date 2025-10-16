package cs151.application.stage;

import cs151.application.tools.Tools;
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
    Tools tool = new Tools();

    public ListDisplay(List<String> displayStrings, String title) {
        Label titleLabel = new Label(title);

        HBox line = new HBox();
        for (int i = 1; i < displayStrings.size(); i++) {
            Text text = new Text(displayStrings.get(i) + "   ");
            line.getChildren().add(text);
        }

        VBox sectionLayout = new VBox(titleLabel, new Separator(), line);
        sectionLayout.getStyleClass().add("sectionLayout");

        VBox pageLayout = new VBox(sectionLayout);


        Scene pageScene = new Scene(pageLayout, 500, 200);
        tool.setPageStyle(pageScene);
        this.setTitle("List");
        this.setScene(pageScene);
    }
}
