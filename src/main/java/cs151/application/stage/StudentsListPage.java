package cs151.application.stage;

import cs151.application.model.Student;
import cs151.application.model.StudentList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Objects;

public class StudentsListPage extends Stage {
    public StudentsListPage(int width, int height, String title) {
        StudentList db = StudentList.getInstance();

        // label
        Label labelText = new Label("   List Of Students   ");
        labelText.getStyleClass().add("subtitle");
        // content area
        VBox studentListBox = new VBox();
        studentListBox.getStyleClass().add("sectionLayout");

        int counter = 1;
        for (Student std : db.getList()) {
            Label text = new Label("    Student " + counter + ": ");
            Label name = new Label("        " + std.getName());
            name.setPrefWidth(350);

            Button select = new Button("Information");
            select.setOnAction(event -> selectAct(std));

            HBox nameLine = new HBox(name, select);
            //setDisplay(nameLine);
            VBox section = new VBox(text, nameLine, new Label(), new Separator());
            //setDisplay(section);

            studentListBox.getChildren().add(section);
            counter++;
        }


        // student Pane
        ScrollPane studentListPane = new ScrollPane(studentListBox);
        studentListPane.setFitToWidth(true);
        // close button
        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> closeBtnAction());

        // layout
        VBox pageLayout = new VBox(labelText, studentListPane, closeBtn);
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

    private void selectAct(Student std) {
        StudentInfoPage infoPage = new StudentInfoPage(std);
        infoPage.show();
    }

}
