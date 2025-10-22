package cs151.application.stage;

import cs151.application.model.Student;
import cs151.application.model.StudentList;
import cs151.application.services.Tools;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentsListPage extends Stage {
    StudentList database = StudentList.getInstance();
    VBox studentListBox = new VBox();
    Tools tool = new Tools();

    public StudentsListPage() {
        // set scene
        Scene pageScene = buildScene();
        this.setTitle("Student List");
        this.setScene(pageScene);
    }

    private void closeBtnAction() {
        this.close();
    }

    private void selectAct(Student std) {
        StudentInfoPage infoPage = new StudentInfoPage(std);
        database.save();
        infoPage.show();
    }

    private void deleteAct(Student std) {
        this.close();
        database.getList().remove(std);
        Scene reloadPage = buildScene();
        this.setScene(reloadPage);
        database.save();
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
        HBox btnLayout = new HBox(closeBtn);
        btnLayout.getStyleClass().add("buttonLayout");

        VBox contentBox = new VBox(studentListPane);
        contentBox.getStyleClass().add("inputLayout");

        // layout
        VBox pageLayout = new VBox(labelText, contentBox, btnLayout);
        Scene result = new Scene(pageLayout, 800, 600);
        tool.setPageStyle(result);
        return result;
    }

    private VBox makeStdLine() {
        int counter = 1;
        VBox block = new VBox();
        for (Student std : database.getList()) {
            Label text = new Label(" Student " + (counter++) + ": ");
            Label name = new Label("        " + std.getName());
            name.setPrefWidth(450);

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
