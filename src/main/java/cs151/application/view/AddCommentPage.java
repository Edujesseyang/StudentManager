package cs151.application.view;

import cs151.application.controller.AddCommentPageController;
import cs151.application.model.Student;
import cs151.application.services.Tools;
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

        AddCommentPageController controller = new AddCommentPageController(this, targetStudent);
        Button addBtn = new Button("Add");
        addBtn.setOnAction(e -> controller.addAct(commentText));
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(e -> controller.cancelAct());
        HBox btnBox = new HBox(addBtn, cancelBtn);
        btnBox.getStyleClass().add("buttonLayout");
        btnBox.setAlignment(Pos.CENTER);

        VBox pageLayout = new VBox(title, textArea, btnBox);

        Scene pageScene = new Scene(pageLayout, 500, 400);
        tool.setPageStyle(pageScene);
        return pageScene;
    }


}
