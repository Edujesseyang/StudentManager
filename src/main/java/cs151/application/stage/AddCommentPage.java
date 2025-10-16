package cs151.application.stage;

import cs151.application.model.Student;
import cs151.application.tools.Tools;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddCommentPage extends Stage {
    Tools tool = new Tools();
    Student targetStudent;
    TextArea commentText;

    public AddCommentPage(Student target) {
        this.targetStudent = target;
        this.setScene(buildStage());
        this.show();
    }

    private Scene buildStage() {
        Label title = new Label("Add new comment");
        title.getStyleClass().add("subtitle");
        commentText = new TextArea();
        commentText.setPromptText("Write comment");
        commentText.setWrapText(true);
        commentText.setMaxWidth(400);
        commentText.setPrefHeight(300);
        VBox textArea = new VBox(commentText);

        Button addBtn = new Button("Add");
        addBtn.setOnAction(e -> addAct());
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(e -> cancelAct());
        HBox btnBox = new HBox(addBtn, cancelBtn);
        btnBox.getStyleClass().add("buttonLayout");
        btnBox.setAlignment(Pos.CENTER);

        VBox pageLayout = new VBox(title, textArea, btnBox);

        Scene pageScene = new Scene(pageLayout, 500, 400);
        tool.setPageStyle(pageScene);
        return pageScene;
    }

    private void addAct() {
        String comText = commentText.getText();
        if (comText.isBlank()) {
            tool.popAlert(Alert.AlertType.ERROR, "Comment can not be blank").showAndWait();
            return;
        }
        targetStudent.addComment(" <" + tool.getTimeString() + ">\n" + comText);
        tool.popAlert(Alert.AlertType.INFORMATION, "Added successfully").showAndWait();
        StudentInfoPage newInfoPage = new StudentInfoPage(targetStudent);
        this.close();
        newInfoPage.show();
    }

    private void cancelAct() {
        this.close();
    }
}
