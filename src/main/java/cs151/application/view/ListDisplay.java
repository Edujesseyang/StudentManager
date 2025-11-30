package cs151.application.view;

import cs151.application.services.ViewUtility;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.List;

public class ListDisplay extends Stage implements View {
    private final List<String> displayStrings;
    private final String title;

    public ListDisplay(List<String> displayStrings, String title) {
        this.displayStrings = displayStrings;
        this.title = title;
        Scene pageScene = buildScene();
        ViewUtility tool = new ViewUtility();
        tool.setPageStyle(pageScene);
        this.setTitle("List");
        this.setScene(pageScene);
    }

    public Scene buildScene() {
        Label titleLabel = new Label(title);

        TextFlow tx = new TextFlow();
        for (String displayString : displayStrings) {
            Text text = new Text(displayString + "   ");
            tx.getChildren().add(text);
        }

        HBox line = new HBox(tx);
        VBox sectionLayout = new VBox(titleLabel, new Separator(), line);
        sectionLayout.getStyleClass().add("sectionLayout");

        VBox pageLayout = new VBox(sectionLayout);
        return new Scene(pageLayout, 500, 200);
    }
}
