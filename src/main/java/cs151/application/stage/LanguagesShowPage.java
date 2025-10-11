package cs151.application.stage;

import cs151.application.model.Student;
import cs151.application.model.StudentList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class LanguagesShowPage extends Stage {
    public LanguagesShowPage(int width, int height, String title) {
        // label
        Label labelText = new Label("   Skilled Languages Status   ");
        labelText.getStyleClass().add("subtitle");
        VBox listBox = new VBox();
        listBox.getStyleClass().add("sectionLayout");;
        // content area
        StudentList db = StudentList.getInstance();
        for (Student std : db.getList()) {
            Label name = new Label(std.getName() + ":");
            Text text = new Text(std.getLanguageList_String());
            listBox.getChildren().addAll(name, text, new Separator());
        }

        // close button
        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> closeBtnAction());

        ScrollPane box = new ScrollPane(listBox);
        box.setFitToWidth(true);

        // layout
        VBox pageLayout = new VBox(labelText, box, closeBtn);
        pageLayout.getStyleClass().add("pageLayout");
        // set scene
        Scene pageScene = new Scene(pageLayout, width, height);
        pageScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style/homePage.css")).toExternalForm());
        this.setTitle(title);
        this.setScene(pageScene);
    }

    private void closeBtnAction() {
        this.close();
    }
}
