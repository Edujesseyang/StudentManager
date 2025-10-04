package cs151.application.stage;

import cs151.application.model.Student;
import cs151.application.model.StudentList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentsShowPage extends Stage {
    private final StudentList db = StudentList.getInstance();
    private final Label labelText;
    private final TextArea display;
    private final Button closeBtn;

    public StudentsShowPage(int height, int width, String title) {
        // label
        labelText = new Label("All students' information: ");
        display = new TextArea();

        // content area
        int counter = 1;
        for (Student stdList : db.getList()) {
            display.appendText("Student " + counter++ + "\n");
            display.appendText(stdList.toString());
            display.appendText("\n\n");
        }
        display.setEditable(false);
        display.setWrapText(true);

        // close button
        closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> closeBtnAction());
        closeBtn.setAlignment(Pos.CENTER);

        // layout
        VBox pageLayout = new VBox(labelText, display, closeBtn);
        pageLayout.setPadding(new Insets(20));
        pageLayout.setSpacing(10);

        // set scene
        Scene pageScene = new Scene(pageLayout, height, width);
        this.setTitle(title);
        this.setScene(pageScene);
    }

    /**
     * close stage
     */
    private void closeBtnAction() {
        this.close();
    }
}
