package cs151.application.view;

import cs151.application.controller.StudentsListPageController;
import cs151.application.services.Tools;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class StudentsListPage extends Stage {

    VBox studentListBox = new VBox();
    Tools tool = new Tools();
    private final StudentsListPageController controller;

    public StudentsListPage() {
        // set scene
        this.controller = new StudentsListPageController(this);

        Scene pageScene = buildScene(controller.getStudentNameList());
        this.setTitle("Student List");
        this.setScene(pageScene);
    }

    public Scene buildScene(List<String> allStudent) {
        // label
        Label labelText = new Label("   List Of Students   ");
        labelText.getStyleClass().add("subtitle");

        // bloc
        studentListBox = makeStdLine(allStudent);
        studentListBox.getStyleClass().add("sectionLayout");

        // student Pane
        ScrollPane studentListPane = new ScrollPane(studentListBox);
        studentListPane.setFitToWidth(true);
        // close button
        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> controller.closeBtnAction());
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

    private VBox makeStdLine(List<String> allStudent) {
        int counter = 1;
        VBox block = new VBox();
        for (String std : allStudent) {
            Label text = new Label(" Student " + (counter++) + ": ");
            Label name = new Label("        " + std);
            name.setPrefWidth(450);

            Button select = new Button("Detail");
            select.setOnAction(event -> controller.selectAct(std));

            Button delete = new Button("Remove");
            delete.setOnAction(event -> controller.deleteAct(std));

            HBox nameLine = new HBox(name, select, delete);
            VBox section = new VBox(text, nameLine, new Label(), new Separator());
            block.getChildren().add(section);
        }
        return block;
    }

}
