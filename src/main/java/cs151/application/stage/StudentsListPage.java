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
    StudentList database = StudentList.getInstance();
    VBox studentListBox = new VBox();

    public StudentsListPage(String title) {
        // set scene
        Scene pageScene = buildScene();
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

    private void deleteAct(Student std) {
        this.close();
        database.getList().remove(std);
        Scene reloadPage = buildScene();
        this.setScene(reloadPage);
        this.show();
    }

    private Scene buildScene() {
        // label
        Label labelText = new Label("   List Of Students   ");
        labelText.getStyleClass().add("subtitle");

        // bloc
        studentListBox = makeStdLine();
        studentListBox.getStyleClass().add("sectionLayout");

        // student Pane
        ScrollPane studentListPane = new ScrollPane(studentListBox);
        studentListPane.setFitToWidth(true);
        // close button
        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> closeBtnAction());

        // layout
        VBox pageLayout = new VBox(labelText, studentListPane, closeBtn);
        pageLayout.getStyleClass().add("pageLayout");
        Scene result = new Scene(pageLayout, 650, 600);
        result.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style/homePage.css")).toExternalForm());
        return result;
    }

    private VBox makeStdLine() {
        int counter = 1;
        VBox block = new VBox();
        for (Student std : database.getList()) {
            Label text = new Label(" Student " + (counter++) + ": ");
            Label name = new Label("        " + std.getName());
            name.setPrefWidth(400);

            Button select = new Button("Detail");
            select.setOnAction(event -> selectAct(std));

            Button delete = new Button("Remove");
            delete.setOnAction(event -> deleteAct(std));

            HBox nameLine = new HBox(name, select, delete);
            VBox section = new VBox(text, nameLine, new Label(), new Separator());
            block.getChildren().add(section);
        }
        return block;
    }

}
